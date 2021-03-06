package at.sw2015.teamturingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import at.sw2015.teamturingapp.SlidingTab.SlidingTabLayout;
import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.FileHandler;
import at.sw2015.teamturingapp.Utils.HighScoreEntry;
import at.sw2015.teamturingapp.Utils.HighscoreHandler;
import at.sw2015.teamturingapp.Utils.OutWriter;
import at.sw2015.teamturingapp.Utils.TMConfiguration;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class MainGameActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager view_pager;
    ViewPagerAdapter view_pager_adapter;
    SlidingTabLayout sliding_tab_layout;
    String headers[] = {"Run TM", "Edit TM"};
    int number_of_tabs = 2;
    AlertDialog settings_dialog = null;

    // Made public to check in MainActivityTest
    // which test file was loaded
    public static int resource_id = R.raw.tmtestconfig;
    public static String curr_tm_file_name_path = Environment.
            getExternalStorageDirectory() + "/TMConfigs/" + "tmtestconfig" + ".xml";

    public TMConfiguration current_tm_config = null;
    public static OutWriter out_writer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        out_writer = new OutWriter("/TMConfigs/");

        if (!out_writer.playerFileExists())
            HighscoreHandler.setCurrentPlayerName("Player1");
        else
            System.out.println("Current Player: " +
                    HighscoreHandler.getCurrentPlayerName());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        InputStream raw = getResources().openRawResource(resource_id);
        curr_tm_file_name_path = Environment.
                getExternalStorageDirectory() + "/TMConfigs/" +
                getResources().getResourceEntryName(resource_id) + ".xml";

        try {
            out_writer.writeXMLToFileName(XMLParser.readRawXMLInput(raw), getResources().getResourceEntryName(resource_id));
            raw = getResources().openRawResource(resource_id);
            org.w3c.dom.Document raw_xml_input = XMLParser.readRawXMLInput(raw);
            current_tm_config = XMLParser.readTMConfig(raw_xml_input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view_pager_adapter = new ViewPagerAdapter(getSupportFragmentManager(), headers, number_of_tabs);
        view_pager = (ViewPager) findViewById(R.id.pager);
        view_pager.setAdapter(view_pager_adapter);

        sliding_tab_layout = (SlidingTabLayout) findViewById(R.id.tabs);
        sliding_tab_layout.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        sliding_tab_layout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        sliding_tab_layout.setViewPager(view_pager);
        out_writer.clearHighScore(current_tm_config.getTMName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showSettingsDialog();
                return true;
            case R.id.action_load:
                createFileChooser();
                return true;
            case R.id.action_delete_tm:
                showDeleteTMDialog();
                return true;
            case R.id.action_new:
                createNewTMDialog();
                return true;
            case R.id.action_highscore:
                showHighScoreDialog();
                return true;
            default:
                return item.getItemId() == R.id.action_settings
                        || super.onOptionsItemSelected(item);
        }
    }

    private void createFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Choose a TM File to run"),
                    0);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No File Manager found, please install one and try again",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        String path = FileHandler.getPath(this, uri);
                        org.w3c.dom.Document raw = XMLParser.readXMLInputFromFile(new File(path));
                        current_tm_config = XMLParser.readTMConfig(raw);
                        curr_tm_file_name_path = path;
                        EditFragmentTab.update();
                        ViewPagerAdapter.current_run_fragment.reset();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createNewTMDialog() {
        Intent intent = new Intent(this, NewTMActivity.class);
        this.startActivity(intent);
    }

    private void showHighScoreDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                this);

        TextView title = new TextView(this);
        title.setText("Highscore List");
        title.setBackgroundColor(Color.parseColor("#990012"));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(24);
        builderSingle.setCustomTitle(title);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, R.layout.list_item);

        ArrayList<HighScoreEntry> all_scores = out_writer.getHighScore(current_tm_config.getTMName());
        Collections.sort(all_scores,new CustomComparator());

        for (HighScoreEntry curr_entry : all_scores)
            arrayAdapter.add(curr_entry.player_name + " - " + curr_entry.step_counter);

        builderSingle.setNegativeButton("CONTINUE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        builderSingle.show();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                this);

        TextView title = new TextView(this);
        title.setText("Settings");
        title.setBackgroundColor(Color.parseColor("#990012"));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(24);
        builderSingle.setCustomTitle(title);

        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.settings_layout, null);

        Button save_player = (Button) view.findViewById(R.id.button_save_player);
        Button cancel_player = (Button) view.findViewById(R.id.button_cancel_player);
        final EditText player_edit = (EditText) view.findViewById(R.id.player_name_edit);

        if (player_edit != null)
            player_edit.setText(HighscoreHandler.getCurrentPlayerName());

        save_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player_edit != null) {
                    String new_player_name = player_edit.getText().toString();

                    if (new_player_name.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please fill out all of the fields before" +
                                " the settings can be saved. Thanks :)", Toast.LENGTH_LONG).show();
                        return;
                    }

                    HighscoreHandler.setCurrentPlayerName(new_player_name);
                }

                if (settings_dialog != null)
                    settings_dialog.cancel();
            }
        });

        cancel_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings_dialog != null)
                    settings_dialog.cancel();
            }
        });

        builderSingle.setView(view);
        settings_dialog = builderSingle.create();
        settings_dialog.show();
    }

    private void showDeleteTMDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if(out_writer.deleteTM(current_tm_config.getTMName())) {
                            Toast.makeText(getApplicationContext(),R.string.delete_succeed, Toast.LENGTH_LONG).show();
                            finish();
                        }else
                            Toast.makeText(getApplicationContext(),R.string.delete_failed, Toast.LENGTH_LONG).show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_question).setPositiveButton("YES", dialogClickListener)
                .setNegativeButton("NO", dialogClickListener).show();
    }

    public class CustomComparator implements Comparator<HighScoreEntry> {
        @Override
        public int compare(HighScoreEntry o1, HighScoreEntry o2) {
            return o1.step_counter - o2.step_counter;
        }
    }
}