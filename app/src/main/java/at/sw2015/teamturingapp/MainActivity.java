package at.sw2015.teamturingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.widget.ImageView;
public class MainActivity extends ActionBarActivity {

    private TMView tm_view = null;
    private TMConfiguration current_tm_config = null;
    private XMLParser xp = new XMLParser();
    private InputStream raw = null;

    // Made public to check in MainActivityTest
    // which test file was loaded
    public int resource_id = R.raw.tmtestconfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raw = getResources().openRawResource(resource_id);

        try {
            current_tm_config = xp.readTMConfig(raw);
        } catch (XmlPullParserException | IOException
                | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        Button step_button = (Button) findViewById(R.id.button_step);
        Button show_button = (Button) findViewById(R.id.button_show);
        Button reset_button = (Button) findViewById(R.id.button_reset);

        step_button.setOnClickListener(step_click_listener);
        show_button.setOnClickListener(show_click_listener);
        reset_button.setOnClickListener(reset_click_listener);

        Vector<ImageView> all_image_views = new Vector<>();
        all_image_views.add((ImageView) findViewById(R.id.field1));
        all_image_views.add((ImageView) findViewById(R.id.field2));
        all_image_views.add((ImageView) findViewById(R.id.field3));
        all_image_views.add((ImageView) findViewById(R.id.field4));
        all_image_views.add((ImageView) findViewById(R.id.field5));
        all_image_views.add((ImageView) findViewById(R.id.field6));
        all_image_views.add((ImageView) findViewById(R.id.field7));
        all_image_views.add((ImageView) findViewById(R.id.field8));
        all_image_views.add((ImageView) findViewById(R.id.field9));

        tm_view = new TMView(all_image_views, getDrawable(R.mipmap.base),
                getDrawable(R.mipmap.one), getDrawable(R.mipmap.one_sel),
                getDrawable(R.mipmap.zero), getDrawable(R.mipmap.zero_sel),
                getDrawable(R.mipmap.dollar),
                getDrawable(R.mipmap.dollar_sel),
                getDrawable(R.mipmap.underline),
                getDrawable(R.mipmap.underline_sel));

        tm_view.printTMState(current_tm_config, getBaseContext());
        tm_view.updateView(current_tm_config);
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
