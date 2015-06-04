package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.Utils.TMConfiguration;

public class EditFragmentTabEngineTest extends TestCase {

    public void testReadTMConfig() throws Exception {
        TMConfiguration current_tm_config = EditFragmentTab.readTMConfig();
        assertTrue(current_tm_config.getTMName().equalsIgnoreCase("TMTestV1.0"));
        assertTrue(current_tm_config.getAuthor().equalsIgnoreCase("Lukas Gregori and Vanessa Stanje"));
        assertTrue(current_tm_config.getCurrentState().equalsIgnoreCase("S0"));
        assertTrue(current_tm_config.getInitialState().equalsIgnoreCase("S0"));
        assertTrue(current_tm_config.getAllRules().size() == 7);
        assertTrue(current_tm_config.getAllTapes().size() == 1);
    }


}
