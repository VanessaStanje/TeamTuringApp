package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import java.util.Vector;

import at.sw2015.teamturingapp.TMConfiguration;
import at.sw2015.teamturingapp.TMEngine;

public class TMEngineTest extends TestCase {

    public void testMoveHead() throws Exception {
        TMEngine new_tm_engine = new TMEngine();
        int current_head_position = 1;

        String direction_R = "R";
        int new_head_pos = new_tm_engine.moveHead(current_head_position,direction_R);
        assertEquals(current_head_position + 1,new_head_pos);

        String direction_L = "L";
        new_head_pos = new_tm_engine.moveHead(new_head_pos,direction_L);
        assertEquals(current_head_position,new_head_pos);

        String direction_S = "S";
        new_head_pos = new_tm_engine.moveHead(new_head_pos,direction_S);
        assertEquals(current_head_position,new_head_pos);
    }

    public void testStep() throws Exception{
        TMEngine new_tm_engine = new TMEngine();
        Vector<Vector<String>> expected_AllRules = new Vector<>();
        Vector<String> ruleOne = new Vector<>();
        ruleOne.add("S0");
        ruleOne.add("1");
        ruleOne.add("0");
        ruleOne.add("R");
        ruleOne.add("S1");
        Vector<String> ruleTwo = new Vector<>();
        ruleTwo.add("S1");
        ruleTwo.add("0");
        ruleTwo.add("1");
        ruleTwo.add("R");
        ruleTwo.add("S2");
        Vector<String> ruleThree = new Vector<>();
        ruleThree.add("S2");
        ruleThree.add("0");
        ruleThree.add("1");
        ruleThree.add("H");
        ruleThree.add("S2");
        expected_AllRules.add(ruleOne);
        expected_AllRules.add(ruleTwo);
        expected_AllRules.add(ruleThree);

        Vector<Integer> expected_HeadPosition = new Vector<>();
        expected_HeadPosition.add(0);

        Vector<String> expected_AllTapes = new Vector<>();
        expected_AllTapes.add("1-0-0-1-0-1");

        TMConfiguration test_config = new TMConfiguration("Lukas Gregori and Vanessa Stanje",
                1, "S0", expected_HeadPosition, expected_AllTapes, expected_AllRules);

        new_tm_engine.step(test_config);
        assertTrue(test_config.getCurrentState().equalsIgnoreCase("S1"));
        assertEquals((int)test_config.getHeadPositions().get(0),1);

        new_tm_engine.step(test_config);
        assertTrue(test_config.getCurrentState().equalsIgnoreCase("S2"));
        assertEquals((int)test_config.getHeadPositions().get(0),2);

        new_tm_engine.step(test_config);
        assertTrue(test_config.getCurrentState().equalsIgnoreCase("S2"));
        assertEquals((int)test_config.getHeadPositions().get(0),2);
    }
}