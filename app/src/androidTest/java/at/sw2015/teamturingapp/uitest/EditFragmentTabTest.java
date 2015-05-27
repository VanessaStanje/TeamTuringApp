package at.sw2015.teamturingapp.uitest;

import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ImageView;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import at.sw2015.teamturingapp.EditCards.RuleInfo;
import at.sw2015.teamturingapp.EditFragmentTab;
import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;
import at.sw2015.teamturingapp.TMConfiguration;


public class EditFragmentTabTest extends ActivityInstrumentationTestCase2<MainActivity> {

    // Source : http://blogs.steeplesoft.com/posts/2013/simulating-swipes-in-your-android-tests.html
    public enum Direction {
        Left, Right
    }

    private Solo mySolo;

    public EditFragmentTabTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testInitRulesView() throws Exception {
        Vector<Integer> head_pos = new Vector<>();
        head_pos.add(0);
        Vector<String> all_tapes = new Vector<>();
        all_tapes.add("1-0-0-1-0-1");
        Vector<String> rule_1 = new Vector<>();
        rule_1.add("S0");
        rule_1.add("1");
        rule_1.add("0");
        rule_1.add("R");
        rule_1.add("S1");
        Vector<Vector<String>> all_rules = new Vector<>();
        all_rules.add(rule_1);
        TMConfiguration new_tm_config = new TMConfiguration("Test Author", 1, "S0", head_pos, all_tapes, all_rules);

        List<RuleInfo> result = EditFragmentTab.initRulesView(new_tm_config);

        assertTrue(result.size() == 1);
        assertTrue(result.get(0).rule_counter == 0);
        assertTrue(result.get(0).current_state.equalsIgnoreCase("S0"));
        assertTrue(result.get(0).reads_sign.equalsIgnoreCase("1"));
        assertTrue(result.get(0).writes_sign.equalsIgnoreCase("0"));
        assertTrue(result.get(0).moves.equalsIgnoreCase("R"));
        assertTrue(result.get(0).next_state.equalsIgnoreCase("S1"));
    }

    // Smartphone has to be awake for this
    // test to pass. If display is off, the test
    // will fail
    public void testSwipe() {
        ViewPager  view_pager = (ViewPager) mySolo.getCurrentActivity() .findViewById(R.id.pager);
        int old_item = view_pager.getCurrentItem();
        swipe(Direction.Left);
        mySolo.sleep(1500);
        int curr_item = view_pager.getCurrentItem();
        Log.d("HERE",old_item + "-" + curr_item);
        assertTrue(old_item + 1 == curr_item);
        swipe(Direction.Right);
        mySolo.sleep(1500);
        curr_item = view_pager.getCurrentItem();
        assertTrue(old_item == curr_item);
    }


    public void testRuleChange() {
        swipe(Direction.Left);
        mySolo.clickOnText("RULE #0");

        mySolo.enterText(0, "S0");
        mySolo.enterText(1, "1");
        mySolo.enterText(2, "$");
        mySolo.enterText(3, "R");
        mySolo.enterText(4, "S1");

        mySolo.clickOnButton("RELOAD");

        boolean found1 = mySolo.searchEditText("S0");
        boolean found2 = mySolo.searchEditText("1");
        boolean found3 = mySolo.searchEditText("0");
        boolean found4 = mySolo.searchEditText("R");
        boolean found5 = mySolo.searchEditText("S1");

        assertEquals(found1, true);
        assertEquals(found2, true);
        assertEquals(found3, true);
        assertEquals(found4, true);
        assertEquals(found5, true);

        mySolo.clearEditText(0);
        mySolo.clearEditText(1);
        mySolo.clearEditText(2);
        mySolo.clearEditText(3);
        mySolo.clearEditText(4);

        mySolo.enterText(0, "S0");
        mySolo.enterText(1, "1");
        mySolo.enterText(2, "$");
        mySolo.enterText(3, "R");
        mySolo.enterText(4, "S1");

        mySolo.clickOnButton("SAVE");
        swipe(Direction.Right);

        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");

        MainActivity activity = getActivity();
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);

        ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
        assertTrue(current_ImageView.contains(field2));

        assertTrue(field2.getDrawable().getConstantState().equals
                (activity.getResources().getDrawable(R.mipmap.dollar).getConstantState()));
    }



    public void testAddRule() {
        swipe(Direction.Left);
        mySolo.clickOnText("+");

        mySolo.enterText(0, "HELLO");
        mySolo.enterText(1, "I");
        mySolo.enterText(2, "AM");
        mySolo.enterText(3, "TEXT");
        mySolo.enterText(4, "TEST");

        mySolo.clickOnButton("CANCEL");

        mySolo.clickOnText("+");

        mySolo.enterText(0, "S0");
        mySolo.enterText(1, "1");
        mySolo.enterText(2, "$");
        mySolo.enterText(3, "R");
        mySolo.enterText(4, "S1");

        mySolo.clickOnButton("SAVE");

        mySolo.clickOnText("RULE #0");
        mySolo.clearEditText(2);
        mySolo.enterText(2, "1");
        mySolo.clearEditText(3);
        mySolo.enterText(3, "H");
        mySolo.clearEditText(4);
        mySolo.enterText(4, "S0");
        mySolo.clickOnButton("SAVE");

        swipe(Direction.Right);

        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");

        MainActivity activity = getActivity();
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);

        ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
        assertTrue(current_ImageView.contains(field2));

        assertTrue(field2.getDrawable().getConstantState().equals
                (activity.getResources().getDrawable(R.mipmap.dollar).getConstantState()));
    }

    public void testRemoveRule()
    {
        swipe(Direction.Left);
        mySolo.clickLongOnText("RULE #0");
        mySolo.sleep(150);
        mySolo.clickOnButton("YES");

        swipe(Direction.Right);

        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("STEP");

        MainActivity activity = getActivity();
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);

        ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
        assertTrue(current_ImageView.contains(field2));

        assertTrue(field2.getDrawable().getConstantState().equals
                (activity.getResources().getDrawable(R.mipmap.one_sel).getConstantState()));
    }

    // Source : http://blogs.steeplesoft.com/posts/2013/simulating-swipes-in-your-android-tests.html
    protected void swipe(final Direction direction) {
        Point size = new Point();
        mySolo.getCurrentActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        float xStart = ((direction == Direction.Left) ? (width - 10) : 10);
        float xEnd = ((direction == Direction.Left) ? 10 : (width - 10));
        // The value for y doesn't change, as we want to swipe straight across
        mySolo.drag(xStart, xEnd, size.y / 2, size.y / 2, 1);
    }


}
