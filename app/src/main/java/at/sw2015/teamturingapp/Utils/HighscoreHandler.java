package at.sw2015.teamturingapp.Utils;


import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.MainGameActivity;

public class HighscoreHandler {

    public static String current_player_name = "Player1";

    public static void setCurrentPlayerName(String new_current_player_name)
    {
        current_player_name = new_current_player_name;
        if(MainGameActivity.out_writer != null)
            MainGameActivity.out_writer.writeCurrentPlayerNameToTXT(current_player_name);
    }

    public static String getCurrentPlayerName()
    {
        if(MainGameActivity.out_writer != null)
          current_player_name = MainGameActivity.out_writer.getCurrentPlayerNameFromTXT();
        return current_player_name;
    }

}
