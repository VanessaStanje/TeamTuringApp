package at.sw2015.teamturingapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ImageView;
import java.util.Vector;
import com.robotium.solo.Solo;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.MainGameActivity;
import at.sw2015.teamturingapp.R;
import at.sw2015.teamturingapp.Utils.TMConfiguration;
import at.sw2015.teamturingapp.TMView;


public class TMViewTest extends ActivityInstrumentationTestCase2<MainGameActivity> {

    private Solo mySolo;

    public TMViewTest()
    {
        super(MainGameActivity.class);
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
        Vector<String> all_goals = new Vector<>();
        all_goals.add("0-0-0-0-0-0");
        Vector<String> rule_1 = new Vector<>();
        rule_1.add("S0");
        rule_1.add("1");
        rule_1.add("0");
        rule_1.add("R");
        rule_1.add("S1");
        Vector<Vector<String>> all_rules = new Vector<>();
        all_rules.add(rule_1);
        TMConfiguration new_tm_config = new TMConfiguration("TMTEST","Test Author",1,"S0",head_pos,all_tapes,all_goals,all_rules);

        Vector<ImageView> all_image_views = new Vector<>();
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8));
        all_image_views.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9));


        Vector<ImageView> all_image_views_goals = new Vector<>();
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9_correct));


        MainGameActivity activity = getActivity();

        TMView new_view = new TMView(all_image_views, ((activity)).getResources().getDrawable(R.mipmap.base),
                ((activity)).getResources().getDrawable(R.mipmap.one),
                ((activity)).getResources().getDrawable(R.mipmap.one_sel),
                ((activity)).getResources().getDrawable(R.mipmap.zero),
                ((activity)).getResources().getDrawable(R.mipmap.zero_sel),
                ((activity)).getResources().getDrawable(R.mipmap.dollar),
                ((activity)).getResources().getDrawable(R.mipmap.dollar_sel),
                ((activity)).getResources().getDrawable(R.mipmap.underline),
                ((activity)).getResources().getDrawable(R.mipmap.underline_sel),
                all_image_views_goals,
                ((activity)).getResources().getDrawable(R.mipmap.base_correct),
                ((activity)).getResources().getDrawable(R.mipmap.one_correct),
                ((activity)).getResources().getDrawable(R.mipmap.zero_correct),
                ((activity)).getResources().getDrawable(R.mipmap.dollar_correct),
                ((activity)).getResources().getDrawable(R.mipmap.underline_correct));


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

    public void testUpdateView() throws Exception {
        Vector<Integer> head_pos = new Vector<>();
        head_pos.add(0);
        Vector<String> all_tapes = new Vector<>();
        all_tapes.add("1-0-0-1-0-1");
        Vector<String> all_goals = new Vector<>();
        all_goals.add("0-0-0-0-0-0");
        Vector<String> rule_1 = new Vector<>();
        rule_1.add("S0");
        rule_1.add("1");
        rule_1.add("0");
        rule_1.add("R");
        rule_1.add("S1");
        Vector<Vector<String>> all_rules = new Vector<>();
        all_rules.add(rule_1);
        final TMConfiguration new_tm_config = new TMConfiguration("TMTEST","Test Author",1,"S0",head_pos,all_tapes,all_goals,all_rules);

        ImageView field1 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1);
        ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
        ImageView field3 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3);
        ImageView field4 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4);
        ImageView field5 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5);
        ImageView field6 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6);
        ImageView field7 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7);
        ImageView field8 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8);
        ImageView field9 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9);

        Vector<ImageView> all_image_views = new Vector<>();
        all_image_views.add(field1);
        all_image_views.add(field2);
        all_image_views.add(field3);
        all_image_views.add(field4);
        all_image_views.add(field5);
        all_image_views.add(field6);
        all_image_views.add(field7);
        all_image_views.add(field8);
        all_image_views.add(field9);

        Vector<ImageView> all_image_views_goals = new Vector<>();
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8_correct));
        all_image_views_goals.add((ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9_correct));

        MainGameActivity activity = getActivity();

        final TMView new_view = new TMView(all_image_views, ((activity)).getResources().getDrawable(R.mipmap.base),
                ((activity)).getResources().getDrawable(R.mipmap.one),
                ((activity)).getResources().getDrawable(R.mipmap.one_sel),
                ((activity)).getResources().getDrawable(R.mipmap.zero),
                ((activity)).getResources().getDrawable(R.mipmap.zero_sel),
                ((activity)).getResources().getDrawable(R.mipmap.dollar),
                ((activity)).getResources().getDrawable(R.mipmap.dollar_sel),
                ((activity)).getResources().getDrawable(R.mipmap.underline),
                ((activity)).getResources().getDrawable(R.mipmap.underline_sel),
                all_image_views_goals,
                ((activity)).getResources().getDrawable(R.mipmap.base_correct),
                ((activity)).getResources().getDrawable(R.mipmap.one_correct),
                ((activity)).getResources().getDrawable(R.mipmap.zero_correct),
                ((activity)).getResources().getDrawable(R.mipmap.dollar_correct),
                ((activity)).getResources().getDrawable(R.mipmap.underline_correct));

        // To avoid the called from wrong thread exception
        // Only thread that created the view can manipulate them
        mySolo.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new_view.updateView(new_tm_config);
            }
        });

        mySolo.sleep(300);

        // Should be _100101__ , first 1 selected
        assertTrue(field1.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.underline).getConstantState()));
        assertTrue(field2.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.one_sel).getConstantState()));
        assertTrue(field3.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.zero).getConstantState()));
        assertTrue(field4.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.zero).getConstantState()));
        assertTrue(field5.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.one).getConstantState()));
        assertTrue(field6.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.zero).getConstantState()));
        assertTrue(field7.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.one).getConstantState()));
        assertTrue(field8.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.underline).getConstantState()));
        assertTrue(field9.getDrawable().getConstantState().equals
                (((activity)).getResources().getDrawable(R.mipmap.underline).getConstantState()));
    }
}