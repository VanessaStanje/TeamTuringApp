package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ImageView;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo mySolo;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {

    }

    public void testButtons(){
        mySolo.clickOnButton("STEP");
        mySolo.clickOnButton("RESET");
        mySolo.clickOnButton("SHOW");
    }

    public void testStartViewsVisible(){

        boolean isVisible_half_l = mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.half_l).isVisible();
        assertTrue(isVisible_half_l);

        boolean isVisible_base = mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.base).isVisible();
        assertTrue(isVisible_base);

        boolean isVisible_half_r = mySolo.getCurrentActivity().getResources().getDrawable(R.mipmap.half_r).isVisible();
        assertTrue(isVisible_half_r);
    }

    public void testStartViews(){
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);
        Log.d("test", "ViewsSize: " + current_ImageView.size());

        if (current_ImageView.size() >= 11){
            ImageView fieldl = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_l);
            ImageView field1 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1);
            ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
            ImageView field3 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3);
            ImageView field4 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4);
            ImageView field5 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5);
            ImageView field6 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6);
            ImageView field7 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7);
            ImageView field8 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8);
            ImageView field9 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9);
            ImageView fieldr = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_r);

            assertTrue(current_ImageView.contains(fieldl));
            assertTrue(current_ImageView.contains(field1));
            assertTrue(current_ImageView.contains(field2));
            assertTrue(current_ImageView.contains(field3));
            assertTrue(current_ImageView.contains(field4));
            assertTrue(current_ImageView.contains(field5));
            assertTrue(current_ImageView.contains(field6));
            assertTrue(current_ImageView.contains(field7));
            assertTrue(current_ImageView.contains(field8));
            assertTrue(current_ImageView.contains(field9));
            assertTrue(current_ImageView.contains(fieldr));
        }
        else
        {
            Log.d("test", "Test Failed");
            assertTrue(false);
        }
    }

    public void testOnCreateViews(){
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);
        Log.d("test", "ViewsSize: " + current_ImageView.size());

        if (current_ImageView.size() >= 11){
            ImageView fieldl = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_l);
            ImageView field1 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1);
            ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
            ImageView field3 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3);
            ImageView field4 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4);
            ImageView field5 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5);
            ImageView field6 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6);
            ImageView field7 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7);
            ImageView field8 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8);
            ImageView field9 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9);
            ImageView fieldr = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_r);

            assertTrue(current_ImageView.contains(fieldl));
            assertTrue(current_ImageView.contains(field1));
            assertTrue(current_ImageView.contains(field2));
            assertTrue(current_ImageView.contains(field3));
            assertTrue(current_ImageView.contains(field4));
            assertTrue(current_ImageView.contains(field5));
            assertTrue(current_ImageView.contains(field6));
            assertTrue(current_ImageView.contains(field7));
            assertTrue(current_ImageView.contains(field8));
            assertTrue(current_ImageView.contains(field9));
            assertTrue(current_ImageView.contains(fieldr));

            // Loaded the tmtestconfig file
            if(MainActivity.resource_id == R.raw.tmtestconfig){
                MainActivity activity = getActivity();

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

                mySolo.clickOnButton("STEP");
                mySolo.clickOnButton("RESET");
                mySolo.clickOnButton("SHOW");
            }
        }
        else
        {
            Log.d("test", "Test Failed");
            assertTrue(false);
        }
    }


    public void testTMFunctionality() {
        ArrayList<ImageView> current_ImageView =  mySolo.getCurrentViews(ImageView.class);
        Log.d("test", "ViewsSize: " + current_ImageView.size());

        if (current_ImageView.size() >= 11){
            ImageView fieldl = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_l);
            ImageView field1 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field1);
            ImageView field2 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field2);
            ImageView field3 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field3);
            ImageView field4 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field4);
            ImageView field5 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field5);
            ImageView field6 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field6);
            ImageView field7 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field7);
            ImageView field8 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field8);
            ImageView field9 = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.field9);
            ImageView fieldr = (ImageView) mySolo.getCurrentActivity().findViewById(R.id.border_r);

            assertTrue(current_ImageView.contains(fieldl));
            assertTrue(current_ImageView.contains(field1));
            assertTrue(current_ImageView.contains(field2));
            assertTrue(current_ImageView.contains(field3));
            assertTrue(current_ImageView.contains(field4));
            assertTrue(current_ImageView.contains(field5));
            assertTrue(current_ImageView.contains(field6));
            assertTrue(current_ImageView.contains(field7));
            assertTrue(current_ImageView.contains(field8));
            assertTrue(current_ImageView.contains(field9));
            assertTrue(current_ImageView.contains(fieldr));

            // Loaded the tmtestconfig file
            if(MainActivity.resource_id == R.raw.tmtestconfig){
                MainActivity activity = getActivity();

                //Check if initial config matches expected
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

                // Simulate the TM run
                mySolo.clickOnButton("STEP");
                mySolo.sleep(150);

                assertTrue(field2.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.zero).getConstantState()));
                assertTrue(field3.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.zero_sel).getConstantState()));

                mySolo.clickOnButton("STEP");
                mySolo.sleep(150);

                assertTrue(field3.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.one).getConstantState()));
                assertTrue(field4.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.zero_sel).getConstantState()));

                mySolo.clickOnButton("STEP");
                mySolo.sleep(150);

                assertTrue(field3.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.one).getConstantState()));
                assertTrue(field4.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.one_sel).getConstantState()));

                mySolo.clickOnButton("STEP");
                mySolo.sleep(150);

                assertTrue(field3.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.one).getConstantState()));
                assertTrue(field4.getDrawable().getConstantState().equals
                        (((activity)).getResources().getDrawable(R.mipmap.one_sel).getConstantState()));

                // Reset and again check if initial values
                mySolo.clickOnButton("RESET");
                mySolo.sleep(150);

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
        else
        {
            Log.d("test", "Test Failed");
            assertTrue(false);
        }
    }

    public void testLoadTMFunctionalityButton() {
        mySolo.clickOnActionBarItem(R.id.action_load);
        mySolo.sleep(1500);
    }
}

