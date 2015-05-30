package at.sw2015.teamturingapp.test;

import android.content.Context;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.Utils.FileHandler;

public class FileHandlingTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo mySolo;

    public FileHandlingTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testGetPath() throws Exception {
        Uri uri = Uri.parse("file:///storage/emulated/0/TMConfigs/tmtestconfig.xml");
        Context ctx = mySolo.getCurrentActivity().getBaseContext();

        String path = FileHandler.getPath(ctx, uri);
        assertTrue(path.equalsIgnoreCase("/storage/emulated/0/TMConfigs/tmtestconfig.xml"));
    }


}
