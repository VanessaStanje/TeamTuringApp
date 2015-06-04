package at.sw2015.teamturingapp.Utils;


import at.sw2015.teamturingapp.MainActivity;

public class HighscoreHandler {

    private static String current_player_name = "Player1";

    public static void setCurrentPlayerName(String new_current_player_name)
    {
        current_player_name = new_current_player_name;
        if(MainActivity.out_writer != null)
            MainActivity.out_writer.writeCurrentPlayerNameToTXT(current_player_name);
    }

    public static String getCurrentPlayerName()
    {
        if(MainActivity.out_writer != null)
          current_player_name = MainActivity.out_writer.getCurrentPlayerNameFromTXT();
        return current_player_name;
    }

}
