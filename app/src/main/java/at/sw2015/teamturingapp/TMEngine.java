package at.sw2015.teamturingapp;

/**
 * Created by Lukas Gregori and Vanessa Stanje on 03.05.15.
 */
public class TMEngine {

    public TMEngine(){
    }


    public int moveHead(int current_head_position, String direction)
    {
        if (direction.equalsIgnoreCase("R"))
            return current_head_position + 1;
        else if (direction.equalsIgnoreCase("L"))
            return current_head_position - 1;

        return current_head_position;
    }

}
