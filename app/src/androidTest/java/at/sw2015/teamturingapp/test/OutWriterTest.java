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

    public void testOutWriteCurrPlayerName() throws Exception {
        OutWriter out_writer = new OutWriter("/TMConfigs/");
        assertNotNull(out_writer);
        String old_player_name = out_writer.getCurrentPlayerNameFromTXT();
        out_writer.writeCurrentPlayerNameToTXT("TestPlayer");
        String current_player_name = out_writer.getCurrentPlayerNameFromTXT();
        assertTrue("TestPlayer".equalsIgnoreCase(current_player_name));
        out_writer.writeCurrentPlayerNameToTXT(old_player_name);
        current_player_name = out_writer.getCurrentPlayerNameFromTXT();
        assertEquals(old_player_name,current_player_name);
    }
}
