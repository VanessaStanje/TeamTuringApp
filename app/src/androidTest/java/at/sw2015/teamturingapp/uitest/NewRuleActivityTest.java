package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import at.sw2015.teamturingapp.NewRuleActivity;


public class NewRuleActivityTest extends ActivityInstrumentationTestCase2<NewRuleActivity> {

    private Solo mySolo;

    public NewRuleActivityTest(){
        super(NewRuleActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testButtons(){
        mySolo.clickOnButton("SAVE");

        mySolo.enterText(0, "1");
        mySolo.enterText(1, "2");
        mySolo.enterText(2, "3");
        mySolo.enterText(3, "4");
        mySolo.enterText(4, "5");

        mySolo.clickOnButton("CANCEL");
    }
}