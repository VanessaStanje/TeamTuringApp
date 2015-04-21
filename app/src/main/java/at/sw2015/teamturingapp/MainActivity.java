package at.sw2015.teamturingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button step_button = (Button) findViewById(R.id.button_step);
        Button show_button = (Button) findViewById(R.id.button_show);
        Button reset_button = (Button) findViewById(R.id.button_reset);

        step_button.setOnClickListener(step_click_listener);
        show_button.setOnClickListener(show_click_listener);
        reset_button.setOnClickListener(reset_click_listener);

    }

    View.OnClickListener step_click_listener = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };


    View.OnClickListener show_click_listener = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };

    View.OnClickListener reset_click_listener = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
