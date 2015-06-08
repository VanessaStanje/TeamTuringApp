package at.sw2015.teamturingapp;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import at.sw2015.teamturingapp.SlidingTab.SlidingTabLayout;
import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.FileHandler;
import at.sw2015.teamturingapp.Utils.HighScoreEntry;
import at.sw2015.teamturingapp.Utils.HighscoreHandler;
import at.sw2015.teamturingapp.Utils.OutWriter;
import at.sw2015.teamturingapp.Utils.TMConfiguration;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class MainActivity extends ActionBarActivity {

    final int HELP_RUN = 0;
    final int HELP_NEW_TM = 1;
    final int HELP_LOAD_TM = 2;
    final int HELP_CHANGE_RULE = 3;
    final int HELP_DELETE_RULE = 4;

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
              //Todo: Implement Help View
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


        return builderSingle.create();
    }



}