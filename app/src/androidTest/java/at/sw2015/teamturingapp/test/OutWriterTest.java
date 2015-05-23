package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import at.sw2015.teamturingapp.EditFragmentTab;
import at.sw2015.teamturingapp.OutWriter;
import at.sw2015.teamturingapp.TMConfiguration;

public class OutWriterTest extends TestCase {

    public void testConstructor() throws Exception {
        OutWriter out_writer = new OutWriter("TMConfigs");
        assertTrue(out_writer.directory.equalsIgnoreCase("TMConfigs"));
    }

    public void testOutWriteError() throws Exception {
        OutWriter out_writer = new OutWriter("");
        assertTrue(!out_writer.writeXMLToFile(null,"test"));
    }
}
