package at.sw2015.teamturingapp.test;

import java.io.InputStream;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.w3c.dom.NodeList;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;


import at.sw2015.teamturingapp.XMLParser;

public class XMLParserTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private Solo mySolo;

    public XMLParserTest(){
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
    }

    public void testXMLReadRawInput() throws Exception
    {
        XMLParser new_xml_parser = new XMLParser();
        InputStream in = mySolo.getCurrentActivity().getResources().openRawResource(R.raw.tmtestconfig);
        org.w3c.dom.Document new_doc = new_xml_parser.readRawXMLInput(in);
        assertNotNull(new_doc);

        NodeList author = new_doc.getElementsByTagName("AUTHOR");
        assertEquals(1,author.getLength());

        NodeList tape_count = new_doc.getElementsByTagName("TAPE_COUNT");
        assertEquals(1,tape_count.getLength());

        NodeList initial_states = new_doc.getElementsByTagName("INITIAL_STATE");
        assertEquals(1,initial_states.getLength());

        NodeList heads = new_doc.getElementsByTagName("H");
        assertEquals(1,heads.getLength());

        NodeList tapes = new_doc.getElementsByTagName("T");
        assertEquals(1,tapes.getLength());

        NodeList rules = new_doc.getElementsByTagName("R");
        assertEquals(3,rules.getLength());
    }

}