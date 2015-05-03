package at.sw2015.teamturingapp.test;

import junit.framework.TestCase;

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
}