package at.sw2015.teamturingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    String headers[];
    int number_of_tabs;

    public static RunFragmentTab current_run_fragment = null;
    public static EditFragmentTab current_edit_fragment = null;

    public ViewPagerAdapter(FragmentManager fragment_manager, String headers[], int number_of_tabs) {
        super(fragment_manager);
        this.headers = headers;
        this.number_of_tabs = number_of_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            current_run_fragment = new RunFragmentTab();
            return current_run_fragment;
        } else {
            current_edit_fragment = new EditFragmentTab();
            return current_edit_fragment;
        }
    }

    @Override
    public String getPageTitle(int position) {
        return headers[position];
    }


    @Override
    public int getCount() {
        return number_of_tabs;
    }
}