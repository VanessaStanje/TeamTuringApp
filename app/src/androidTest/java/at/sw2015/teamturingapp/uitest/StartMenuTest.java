package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import at.sw2015.teamturingapp.MainActivity;


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
        mySolo.clickOnButton("PLAY");
    }

}