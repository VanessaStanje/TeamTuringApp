package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;


import com.robotium.solo.Solo;


import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;


public class StartMenuTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo mySolo;

    public StartMenuTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {

    }

    public void testButtons() {
        mySolo.clickOnButton("ABOUT");
        mySolo.clickOnButton("HELP");
        mySolo.goBack();
        mySolo.clickOnButton("PLAY");
    }

    public void testHelpMenu() {
        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("RUN TM");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.run_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("CREATE NEW TM");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.create_tm_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("LOAD TM");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.load_tm_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("CHANGE RULE");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.edit_rule_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("DELETE RULE");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.delete_rule_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("ADD RULE");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.add_rule_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("SHOW TM STATE");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.show_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("HIGHSCORE");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.highscore_help).isVisible());
        mySolo.goBack();

        mySolo.clickOnButton("HELP");
        mySolo.clickOnText("CHANGE NAME");
        mySolo.sleep(150);
        assertTrue(mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.change_name_help).isVisible());
        mySolo.goBack();
    }

}