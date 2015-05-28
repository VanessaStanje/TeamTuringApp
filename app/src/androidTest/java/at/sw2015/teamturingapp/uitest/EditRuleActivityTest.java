package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import at.sw2015.teamturingapp.EditRuleActivity;



public class EditRuleActivityTest extends ActivityInstrumentationTestCase2<EditRuleActivity> {

    private Solo mySolo;

    public EditRuleActivityTest(){
        super(EditRuleActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testButtons(){
        mySolo.clickOnButton("RELOAD");

        mySolo.clickOnEditText(0);
        mySolo.clickOnEditText(1);
        mySolo.clickOnEditText(2);
        mySolo.clickOnEditText(3);
        mySolo.clickOnEditText(4);

        mySolo.enterText(0, "first field");
        mySolo.enterText(1, "second field");
        mySolo.enterText(2, "third field");
        mySolo.enterText(3, "fourth field");
        mySolo.enterText(4, "fifth field");

        boolean found1 = mySolo.searchEditText("first field");
        boolean found2 = mySolo.searchEditText("second field");
        boolean found3 = mySolo.searchEditText("third field");
        boolean found4 = mySolo.searchEditText("fourth field");
        boolean found5 = mySolo.searchEditText("fifth field");

        assertEquals(found1, true);
        assertEquals(found2, true);
        assertEquals(found3, true);
        assertEquals(found4, true);
        assertEquals(found5, true);

        mySolo.clickOnButton("SAVE");
    }
}