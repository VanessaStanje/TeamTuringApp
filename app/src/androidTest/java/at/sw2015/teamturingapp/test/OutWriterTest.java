package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import at.sw2015.teamturingapp.Utils.OutWriter;

public class OutWriterTest extends TestCase {

    public void testConstructor() throws Exception {
        OutWriter out_writer = new OutWriter("TMConfigs");
        assertTrue(out_writer.directory.equalsIgnoreCase("TMConfigs"));
    }

    public void testOutWriteError() throws Exception {
        OutWriter out_writer = new OutWriter("");
        assertTrue(!out_writer.writeXMLToFileName(null,"test"));
    }
}
