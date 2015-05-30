package at.sw2015.teamturingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import at.sw2015.teamturingapp.SlidingTab.SlidingTabLayout;
import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.FileHandler;
import at.sw2015.teamturingapp.Utils.OutWriter;
import at.sw2015.teamturingapp.Utils.TMConfiguration;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager view_pager;
    ViewPagerAdapter view_pager_adapter;
    SlidingTabLayout sliding_tab_layout;
    String headers[] = {"Run TM", "Edit TM"};
    int number_of_tabs = 2;

    // Made public to check in MainActivityTest
    // which test file was loaded
    public static int resource_id = R.raw.tmtestconfig;
    public static String curr_tm_file_name_path = "tmtestconfig";

    public TMConfiguration current_tm_config = null;
    public static OutWriter out_writer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        out_writer = new OutWriter("/TMConfigs/");
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        InputStream raw = getResources().openRawResource(resource_id);
        curr_tm_file_name_path = Environment.
                getExternalStorageDirectory() + "/TMConfigs/" + getResources().getResourceEntryName(resource_id) + ".xml";

        try {
            out_writer.writeXMLToFileName(XMLParser.readRawXMLInput(raw), getResources().getResourceEntryName(resource_id));
            raw = getResources().openRawResource(resource_id);
            org.w3c.dom.Document raw_xml_input = XMLParser.readRawXMLInput(raw);
            current_tm_config = XMLParser.readTMConfig(raw_xml_input);
        } catch (XmlPullParserException | IOException
                | ParserConfigurationException | SAXException e) {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_load:
                createFileChooser();
                return true;
            case R.id.action_new:
                createNewTMDialog();
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


}