package at.sw2015.teamturingapp.test;

import java.io.InputStream;
import java.util.Vector;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.w3c.dom.NodeList;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;


import at.sw2015.teamturingapp.TMConfiguration;
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
        InputStream in = mySolo.getCurrentActivity().getResources().openRawResource(R.raw.tmtestconfig);
        org.w3c.dom.Document new_doc = XMLParser.readRawXMLInput(in);
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

    public void testReadTMConfig() throws Exception
    {
        InputStream in = mySolo.getCurrentActivity().getResources().openRawResource(R.raw.tmtestconfig);
        org.w3c.dom.Document raw_xml_input = XMLParser.readRawXMLInput(in);
        TMConfiguration new_tm_config = XMLParser.readTMConfig(raw_xml_input);
        assertNotNull(new_tm_config);

        assertEquals(new_tm_config.getAuthor(),"Lukas Gregori and Vanessa Stanje");
        assertEquals(new_tm_config.getTapeCount(),1);
        assertEquals(new_tm_config.getInitialState(),"S0");
        assertEquals(new_tm_config.getHeadPositions().size(),1);
        assertEquals((int)new_tm_config.getHeadPositions().get(0),0);
        assertEquals(new_tm_config.getAllTapes().size(),1);
        assertEquals(new_tm_config.getAllTapes().get(0),"1-0-0-1-0-1");
        assertEquals(new_tm_config.getAllRules().size(),3);

        Vector<String> rule1 = new_tm_config.getAllRules().get(0);
        Vector<String> rule2 = new_tm_config.getAllRules().get(1);
        Vector<String> rule3 = new_tm_config.getAllRules().get(2);

        assertEquals(rule1.size(),5);
        assertEquals(rule2.size(),5);
        assertEquals(rule3.size(),5);

        assertEquals(rule1.get(0),"S0");
        assertEquals(rule1.get(1),"1");
        assertEquals(rule1.get(2),"0");
        assertEquals(rule1.get(3),"R");
        assertEquals(rule1.get(4),"S1");

        assertEquals(rule2.get(0),"S1");
        assertEquals(rule2.get(1),"0");
        assertEquals(rule2.get(2),"1");
        assertEquals(rule2.get(3),"R");
        assertEquals(rule2.get(4),"S2");

        assertEquals(rule3.get(0),"S2");
        assertEquals(rule3.get(1),"0");
        assertEquals(rule3.get(2),"1");
        assertEquals(rule3.get(3),"H");
        assertEquals(rule3.get(4),"S2");
    }

}