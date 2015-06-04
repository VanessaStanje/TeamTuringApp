package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import at.sw2015.teamturingapp.Utils.HighScoreEntry;
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
        assertEquals(old_player_name, current_player_name);
    }

    public void testOutWriteHighscores() throws Exception {
        OutWriter out_writer = new OutWriter("/TMConfigs/");
        assertNotNull(out_writer);
        out_writer.writeHighScore("TMTEST", "Player1", 2);
        out_writer.writeHighScore("TMTEST", "Player2", 22);
        out_writer.writeHighScore("TMTEST", "Player3", 223);

        ArrayList<HighScoreEntry> all_entries = out_writer.getHighScore("TMTEST");
        assertNotNull(all_entries);
        assertEquals(all_entries.size(), 3);
        assertEquals(all_entries.get(0).player_name, "Player1");
        assertEquals(all_entries.get(1).player_name, "Player2");
        assertEquals(all_entries.get(2).player_name,"Player3");
        assertEquals(all_entries.get(0).step_counter,2);
        assertEquals(all_entries.get(1).step_counter,22);
        assertEquals(all_entries.get(2).step_counter,223);

        out_writer.clearHighScore("TMTEST");
    }
}
