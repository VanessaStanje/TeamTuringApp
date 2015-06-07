package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import at.sw2015.teamturingapp.NewTMActivity;


public class NewTMActivityTest extends ActivityInstrumentationTestCase2<NewTMActivity> {

    private Solo mySolo;

    public NewTMActivityTest(){
        super(NewTMActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testButtons(){
        mySolo.enterText(0,"NEW TM NAME");
        mySolo.enterText(1,"0-1-1-0-0");
        mySolo.enterText(2,"0-1-0-1-0");
        mySolo.enterText(3,"AUTHOR");
        mySolo.enterText(4,"INITIAL STATE");
        mySolo.enterText(5,"1");
        mySolo.enterText(6,"0");
        mySolo.clickOnButton("CANCEL");
    }
}

