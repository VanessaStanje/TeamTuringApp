package at.sw2015.teamturingapp.uitest;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
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


}