package at.sw2015.teamturingapp;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ImageView;
import java.util.Vector;
import com.robotium.solo.Solo;


public class TMViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo mySolo;

    public TMViewTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testPrintTMState() throws Exception{
        Vector<Integer> head_pos = new Vector<>();
        head_pos.add(0);
        Vector<String> all_tapes = new Vector<>();
        all_tapes.add("1-0-0-1-0-1");
        Vector<String> rule_1 = new Vector<String>();
        rule_1.add("S0");
        rule_1.add("1");
        rule_1.add("0");
        rule_1.add("R");
        rule_1.add("S1");
        Vector<Vector<String>> all_rules = new Vector<>();
        all_rules.add(rule_1);
        TMConfiguration new_tm_config = new TMConfiguration("Test Author",1,"S0",head_pos,all_tapes,all_rules);

        Vector<ImageView> all_image_views = new Vector<ImageView>();
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9));

        TMView new_view = new TMView(all_image_views,
                mySolo.getCurrentActivity().getDrawable(R.mipmap.base),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.one),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.one_sel),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.zero),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.zero_sel),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.dollar),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.dollar_sel),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.underline),
                mySolo.getCurrentActivity().getDrawable(R.mipmap.underline_sel));

        String tm_out = new_view.printTMState(new_tm_config,mySolo.getCurrentActivity().getBaseContext());
        assertTrue(tm_out.length() > 0);

        String should_be =  "--------------TM OUT--------------\n"+
                            "TAPE T0-> 1-0-0-1-0-1\n"+
                            "CURRENT STATE: S0\n"+
                            "HEAD 0 ON POS: 0\n"+
                            "RULE R0-> [S0, 1, 0, R, S1]\n"+
                            "--------------TM OUT--------------\n";

        if(!tm_out.equalsIgnoreCase(should_be))
          Log.d("TM_OUT:\n",tm_out + "\n" + should_be);

        assertEquals(tm_out,should_be);
    }
}