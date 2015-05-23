package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import java.util.Vector;

import at.sw2015.teamturingapp.EditFragmentTab;
import at.sw2015.teamturingapp.TMConfiguration;
import at.sw2015.teamturingapp.TMEngine;

public class EditFragmentTabEngineTest extends TestCase {

    public void testReadTMConfig() throws Exception {
        TMConfiguration current_tm_config = EditFragmentTab.readTMConfig();
        assertTrue(current_tm_config.getAuthor().equalsIgnoreCase("Lukas Gregori and Vanessa Stanje"));
        assertTrue(current_tm_config.getCurrentState().equalsIgnoreCase("S0"));
        assertTrue(current_tm_config.getInitialState().equalsIgnoreCase("S0"));
        assertTrue(current_tm_config.getAllRules().size() == 3);
        assertTrue(current_tm_config.getAllTapes().size() == 1);
    }


}