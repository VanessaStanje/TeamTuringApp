package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

import java.util.Vector;

import at.sw2015.teamturingapp.TMConfiguration;


public class TMConfigurationTest extends TestCase {

    public  void testAuthorSetGet(){
        String expected_author = "Lukas Gregori and Vanessa Stanje";
        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setAuthor(expected_author);
        String result = test_class.getAuthor();
        assertEquals(expected_author, result);
    }

    public  void testTapeCountSetGet(){
        int expected_TapeCount = 1;
        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setTapeCount(expected_TapeCount);
        int result = test_class.getTapeCount();
        assertEquals(expected_TapeCount, result);
    }

    public  void testInitialStateSetGet(){
        String expected_InitialState = "S0";
        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setInitialState(expected_InitialState);
        String result = test_class.getInitialState();
        assertEquals(expected_InitialState, result);
    }

    public  void testHeadPositionsSetGet(){
        Vector<Integer> expected_HeadPosition = new Vector<>();
        expected_HeadPosition.add(1);
        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setHeadPositions(expected_HeadPosition);
        Vector<Integer> result = test_class.getHeadPositions();
        assertEquals(expected_HeadPosition, result);
    }

    public  void testAllTapesSetGet(){
        Vector<String> expected_AllTapes = new Vector<>();
        expected_AllTapes.add("1-0-0-1-0-1");
        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setAllTapes(expected_AllTapes);
        Vector<String> result = test_class.getAllTapes();
        assertEquals(expected_AllTapes, result);
    }

    public  void testAllRulesSetGet(){
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

        TMConfiguration test_class = new TMConfiguration("Test", 0, "", null, null, null);
        test_class.setAllRules(expected_AllRules);
        Vector<Vector<String>> result = test_class.getAllRules();
        assertEquals(expected_AllRules, result);
    }
}