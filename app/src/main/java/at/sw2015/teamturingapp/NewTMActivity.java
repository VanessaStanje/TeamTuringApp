package at.sw2015.teamturingapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class NewTMActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tm);

        Button save_button = (Button) findViewById(R.id.new_tm_button_save);
        Button cancel_button = (Button) findViewById(R.id.new_tm_button_cancel);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name;
                if ((file_name=save()) != null) {
                    MainActivity.curr_tm_file_name_path = Environment.
                            getExternalStorageDirectory() + "/TMConfigs/" + file_name;
                    EditFragmentTab.update();
                    if(ViewPagerAdapter.current_run_fragment != null)
                      ViewPagerAdapter.current_run_fragment.reset();
                    finish();
                }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public String save() {
        EditText tm_name = (EditText) findViewById(R.id.new_tm_name_edit);
        EditText tm_tape_content = (EditText) findViewById(R.id.new_tm_tape_content_edit);
        EditText tm_author = (EditText) findViewById(R.id.new_tm_author_edit);
        EditText tm_initial_state = (EditText) findViewById(R.id.new_tm_initial_state_edit);
        EditText tm_tape_count = (EditText) findViewById(R.id.new_tm_tape_count_edit);
        EditText tm_heads_position = (EditText) findViewById(R.id.new_tm_head_pos_edit);

        String name = tm_name.getText().toString();
        String tape_content = tm_tape_content.getText().toString();
        String author = tm_author.getText().toString();
        String initial_state = tm_initial_state.getText().toString();
        String tape_count = tm_tape_count.getText().toString();
        String heads_position = tm_heads_position.getText().toString();

        if (name.length() == 0 || author.length() == 0 ||
                initial_state.length() == 0 || tape_count.length() == 0 ||
                heads_position.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please fill out all of the fields before" +
                    " the TM can be saved. Thanks :)", Toast.LENGTH_LONG).show();
            return null;
        }

        String[] tape_split = tape_content.split("-");
        if (tape_split.length != 5) {
            Toast.makeText(getApplicationContext(), "Wrong format for the type, needs to be: X-X-X-X-X",
                    Toast.LENGTH_LONG).show();
            return null;
        }

        if (!XMLParser.writeNewTM(name, author, initial_state, tape_count, heads_position, tape_content)) {
            Toast.makeText(getApplicationContext(), "ERROR, Could not save the TM!",
                    Toast.LENGTH_LONG).show();
            return null;
        }

        Toast.makeText(getApplicationContext(), "Saved the new TM!",
                Toast.LENGTH_LONG).show();
        return name + ".xml";
    }
}