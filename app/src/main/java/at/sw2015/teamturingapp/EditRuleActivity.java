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

import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class EditRuleActivity extends Activity{

    private String init_curr_state = "";
    private String init_reads_sign = "";
    private String init_writes_sign = "";
    private String init_moves = "";
    private String init_next_state = "";
    private int rule_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rule);

        Intent intent = getIntent();

        init_curr_state = intent.getStringExtra("CURRENT_STATE");
        init_reads_sign = intent.getStringExtra("READS_SIGN");
        init_writes_sign = intent.getStringExtra("WRITES_SIGN");
        init_moves = intent.getStringExtra("MOVES");
        init_next_state = intent.getStringExtra("NEXT_STATE");

        try{
          rule_id = Integer.parseInt(intent.getStringExtra("RULE_ID"));
        }catch (NumberFormatException e)
        {
          rule_id = 0;
        }

        EditText curr_state_text = (EditText) findViewById(R.id.curr_state_edit);
        EditText reads_sign_text = (EditText) findViewById(R.id.reads_sign_edit);
        EditText writes_sign_text = (EditText) findViewById(R.id.writes_sign_edit);
        EditText moves_text = (EditText) findViewById(R.id.moves_edit);
        EditText next_state_text = (EditText) findViewById(R.id.next_state_edit);


        curr_state_text.setText(init_curr_state, TextView.BufferType.EDITABLE);
        reads_sign_text.setText(init_reads_sign, TextView.BufferType.EDITABLE);
        writes_sign_text.setText(init_writes_sign, TextView.BufferType.EDITABLE);
        moves_text.setText(init_moves, TextView.BufferType.EDITABLE);
        next_state_text.setText(init_next_state, TextView.BufferType.EDITABLE);

        Button reload = (Button) findViewById(R.id.button_reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadButtonClicked();
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

    private void reloadButtonClicked()
    {
        EditText curr_state_text = (EditText) findViewById(R.id.curr_state_edit);
        EditText reads_sign_text = (EditText) findViewById(R.id.reads_sign_edit);
        EditText writes_sign_text = (EditText) findViewById(R.id.writes_sign_edit);
        EditText moves_text = (EditText) findViewById(R.id.moves_edit);
        EditText next_state_text = (EditText) findViewById(R.id.next_state_edit);

        curr_state_text.setText(init_curr_state, TextView.BufferType.EDITABLE);
        reads_sign_text.setText(init_reads_sign, TextView.BufferType.EDITABLE);
        writes_sign_text.setText(init_writes_sign, TextView.BufferType.EDITABLE);
        moves_text.setText(init_moves, TextView.BufferType.EDITABLE);
        next_state_text.setText(init_next_state, TextView.BufferType.EDITABLE);
    }

    private void saveButtonClicked()
    {
        EditText curr_state_text = (EditText) findViewById(R.id.curr_state_edit);
        EditText reads_sign_text = (EditText) findViewById(R.id.reads_sign_edit);
        EditText writes_sign_text = (EditText) findViewById(R.id.writes_sign_edit);
        EditText moves_text = (EditText) findViewById(R.id.moves_edit);
        EditText next_state_text = (EditText) findViewById(R.id.next_state_edit);
        boolean no_problem_writing = false;
        try
        {
            String curr_state = curr_state_text.getText().toString();
            String reads_sign = reads_sign_text.getText().toString();
            String writes_sign = writes_sign_text.getText().toString();
            String moves = moves_text.getText().toString();
            String next_state = next_state_text.getText().toString();

            if(!reads_sign.equalsIgnoreCase("0") && !reads_sign.equalsIgnoreCase("1") &&
                    !reads_sign.equalsIgnoreCase("$") && !reads_sign.equalsIgnoreCase("_"))
            {
                Toast.makeText(this.getBaseContext(), "Invalid read sign, use 0,1,$ or _ only.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if(!writes_sign.equalsIgnoreCase("0") && !writes_sign.equalsIgnoreCase("1") &&
                    !writes_sign.equalsIgnoreCase("$") && !writes_sign.equalsIgnoreCase("_"))
            {
                Toast.makeText(this.getBaseContext(), "Invalid write sign, use 0,1,$ or _ only.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if(!moves.equalsIgnoreCase("R") && !moves.equalsIgnoreCase("L") && !moves.equalsIgnoreCase("H"))
            {
                Toast.makeText(this.getBaseContext(), "Invalid move direction, use R,L,or H only.",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            no_problem_writing = XMLParser.saveTMRule(curr_state, reads_sign, writes_sign,
                    moves, next_state, rule_id);

        } catch (Exception pce) {
            pce.printStackTrace();
        }

        if(no_problem_writing) {
            EditFragmentTab.update();
            ViewPagerAdapter.current_run_fragment.reset();
        }else
            System.err.println("ERROR; WRITING PROBLEM, COULD NOT UPDATE!");

        finish();
    }
}