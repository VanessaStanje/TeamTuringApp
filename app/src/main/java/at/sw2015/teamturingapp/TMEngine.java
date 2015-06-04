package at.sw2015.teamturingapp;

import java.util.Vector;

import at.sw2015.teamturingapp.Utils.TMConfiguration;

public class TMEngine {

    private static final int CURRENT_STATE = 0;
    private static final int READ_SIGN = 1;
    private static final int WRITE_SIGN = 2;
    private static final int DIRECTION = 3;
    private static final int STATE = 4;

    public TMEngine(){
    }

    public void step(TMConfiguration current_tm_config) {
        // Have to update every tape
        for (int tape_counter = 0; tape_counter < current_tm_config
                .getTapeCount(); tape_counter++) {

            String[] current_tape = current_tm_config.getAllTapes().get(
                    tape_counter).split("-");

            int current_head_position = current_tm_config.getHeadPositions()
                    .get(tape_counter);

            String read_sign = current_tape[current_head_position];
            String current_state = current_tm_config.getCurrentState();

            // Search for matching rule
            for(Vector<String> current_rule : current_tm_config.getAllRules())
            {
                if (current_rule.get(CURRENT_STATE).equalsIgnoreCase(current_state)
                        && current_rule.get(READ_SIGN).equalsIgnoreCase(read_sign)) {

                    // FOUND MATCHING RULE TO CONTINUE
                    // SET NEW TAPE
                    current_tape[current_head_position] = current_rule.get(WRITE_SIGN);

                    String new_tape = "";
                    for (int field_counter = 0; field_counter < current_tape.length - 1; field_counter++)
                        new_tape += current_tape[field_counter] + "-";

                    new_tape += current_tape[current_tape.length - 1];

                    Vector<String> tmp_tapes = current_tm_config.getAllTapes();
                    tmp_tapes.set(tape_counter, new_tape);
                    current_tm_config.setAllTapes(tmp_tapes);

                    // SET CURRENT STATE
                    current_tm_config.setCurrentState(current_rule.get(STATE));

                    // SET NEW HEAD POSITION
                    Vector<Integer> tmp_head_positions = current_tm_config
                            .getHeadPositions();

                    tmp_head_positions.set(tape_counter,
                            moveHead(current_head_position,current_rule.get(DIRECTION)));

                    current_tm_config.setHeadPositions(tmp_head_positions);
                }
            }
        }
    }



    public int moveHead(int current_head_position, String direction)
    {
        if (direction.equalsIgnoreCase("R"))
            return current_head_position + 1;
        else if (direction.equalsIgnoreCase("L"))
            return current_head_position - 1;

        return current_head_position;
    }

    public boolean checkIfGameWon(TMConfiguration current_tm_config)
    {
        int done_tapes_count = 0;
        for(String curr_tape : current_tm_config.getAllTapes())
        {
            for(String curr_goal : current_tm_config.getAllGoals())
            {
                if(curr_tape.equalsIgnoreCase(curr_goal))
                    done_tapes_count++;
            }
        }

        if(done_tapes_count == current_tm_config.getAllTapes().size())
            return true;

        return false;
    }

}