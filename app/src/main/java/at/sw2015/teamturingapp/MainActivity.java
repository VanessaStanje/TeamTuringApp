package at.sw2015.teamturingapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import at.sw2015.teamturingapp.SlidingTab.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager view_pager;
    ViewPagerAdapter view_pager_adapter;
    SlidingTabLayout sliding_tab_layout;
    String headers[] = {"Run TM", "Edit TM"};
    int number_of_tabs = 2;

    // Made public to check in MainActivityTest
    // which test file was loaded
    public int resource_id = R.raw.tmtestconfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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
       return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}