package at.sw2015.teamturingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewRuleActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rule);

        Button reload = (Button) findViewById(R.id.button_cancel);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelButtonClicked();
            }
        });

        Button save = (Button) findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClicked();
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

    private void cancelButtonClicked()
    {
        finish();
    }

    private String saveButtonClicked()
    {
        EditText current_state_text = (EditText) findViewById(R.id.curr_state_edit);
        EditText reads_sign_text = (EditText) findViewById(R.id.reads_sign_edit);
        EditText writes_sign_text = (EditText) findViewById(R.id.writes_sign_edit);
        EditText moves_text = (EditText) findViewById(R.id.moves_edit);
        EditText next_state_text = (EditText) findViewById(R.id.next_state_edit);

        String new_current_state = current_state_text.getText().toString();
        String new_reads_sign = reads_sign_text.getText().toString();
        String new_writes_sign = writes_sign_text.getText().toString();
        String new_moves = moves_text.getText().toString();
        String new_next_state = next_state_text.getText().toString();

        String new_rule = new_current_state + "-" + new_reads_sign +
                "-" + new_writes_sign + "-" + new_moves + "-" + new_next_state;

        System.out.println("NEW RULE: " + new_rule);

        if(new_current_state.length() == 0 || new_reads_sign.length() == 0 ||
                new_writes_sign.length() == 0 || new_moves.length() == 0 ||
                new_next_state.length() == 0)
        {
            Toast.makeText(getApplicationContext(), (String) "Please fill out all of the fields before" +
                    " the rule can be saved. Thanks :)", Toast.LENGTH_LONG).show();
            return "ERROR";
        }

        boolean could_add = XMLParser.addNewRule(new_rule);

        if(could_add) {
            Toast.makeText(getApplicationContext(), "Rule " + new_rule + " saved!",
                    Toast.LENGTH_LONG).show();
            EditFragmentTab.update();
            ViewPagerAdapter.current_run_fragment.reset();
        }
        else
            Toast.makeText(getApplicationContext(),"ERROR, Could not save rule!",
                    Toast.LENGTH_LONG).show();


        finish();
        return new_rule;
    }
}