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
        mySolo.clickOnButton("CANCEL");
        mySolo.clickOnButton("SAVE");
        mySolo.enterText(0,"AUTHOR");
        mySolo.enterText(1,"INITIAL STATE");
        mySolo.enterText(2,"1");
        mySolo.enterText(3,"2");
    }


}

