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
        mySolo.clickOnButton("Step");
        mySolo.clickOnButton("Reset");
        mySolo.clickOnButton("Show");
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
        Log.d("test", "OUUUT: " + current_ImageView.size());

        if (current_ImageView.size() >= 11){

            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.border_l)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field1)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field2)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field3)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field4)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field5)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field6)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field7)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field8)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.field9)));
            assertTrue(current_ImageView.contains(mySolo.getCurrentActivity().findViewById(R.id.border_r)));

        }
        else
        {
            Log.d("test", "Test Failed");
            assertTrue(false);
        }




    }



}