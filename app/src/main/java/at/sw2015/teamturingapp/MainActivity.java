package at.sw2015.teamturingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    final int HELP_RUN = 0;
    final int HELP_NEW_TM = 1;
    final int HELP_LOAD_TM = 2;
    final int HELP_CHANGE_RULE = 3;
    final int HELP_DELETE_RULE = 4;
    final int HELP_ADD_RULE = 5;
    final int HELP_SHOW = 6;
    final int HELP_HIGHSCORE = 7;
    final int HELP_CHANGE_NAME = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenu);

        Button play_game_button = (Button) findViewById(R.id.button_play);
        play_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainGameActivity.class);
                startActivity(intent);
            }
        });

        Button about_button = (Button) findViewById(R.id.button_about);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Game was created by Lukas Gregori and" +
                                " Vanessa Stanje as part of the SW course.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button help_button = (Button) findViewById(R.id.button_help);
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              createDialog().show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public Dialog createDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                this);

        TextView title = new TextView(this);
        title.setText("HELP");
        title.setBackgroundColor(Color.parseColor("#990012"));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(24);

        String[] input = {"RUN TM","CREATE NEW TM","LOAD TM","CHANGE RULE",
                "DELETE RULE","ADD RULE","SHOW TM STATE", "HIGHSCORE", "CHANGE NAME"};
        builderSingle.setCustomTitle(title).setItems(input, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case HELP_RUN:
                        showHelpDialogImage(R.mipmap.run_help,getString(R.string.help_run));
                        break;
                    case HELP_NEW_TM:
                        showHelpDialogImage(R.mipmap.create_tm_help,getString(R.string.help_new_tm));
                        break;
                    case HELP_LOAD_TM:
                        showHelpDialogImage(R.mipmap.load_tm_help,getString(R.string.help_load_tm));
                        break;
                    case HELP_CHANGE_RULE:
                        showHelpDialogImage(R.mipmap.edit_rule_help,getString(R.string.help_change_rule));
                        break;
                    case HELP_DELETE_RULE:
                        showHelpDialogImage(R.mipmap.delete_rule_help,getString(R.string.help_delete_rule));
                        break;
                    case HELP_ADD_RULE:
                        showHelpDialogImage(R.mipmap.add_rule_help,getString(R.string.help_add_rule));
                        break;
                    case HELP_SHOW:
                        showHelpDialogImage(R.mipmap.show_help,getString(R.string.help_show_tm_state));
                        break;
                    case HELP_HIGHSCORE:
                        showHelpDialogImage(R.mipmap.highscore_help,getString(R.string.help_high_scores));
                        break;
                    case HELP_CHANGE_NAME:
                        showHelpDialogImage(R.mipmap.change_name_help,getString(R.string.help_change_name));
                        break;
                    default:
                }
            }
        });

        return builderSingle.create();
    }

    void showHelpDialogImage(int res_id,String help_message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.help_image, null);

        ImageView help_img = (ImageView) view.findViewById(R.id.image);
        if(help_img != null) {
            help_img.setImageResource(res_id);
            help_img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        TextView help_text = (TextView) view.findViewById(R.id.textViewHelp);
        if(help_text != null) {
            help_text.setText(help_message);
        }

        alert.setView(view);
        alert.show();
    }
}