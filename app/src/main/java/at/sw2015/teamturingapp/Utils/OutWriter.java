package at.sw2015.teamturingapp.Utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class OutWriter {

    public String directory = "";

    public  OutWriter(String directory)
    {
        this.directory = directory;
    }

    public boolean writeXMLToFileName(org.w3c.dom.Document content,String file_name){
        try {

            if(directory.length() == 0) {
                System.err.println("ERROR; NO DIRECTORY SELECTED!");
                return false;
            }

            File newFolder = new File(Environment.
                    getExternalStorageDirectory(), directory);
            if (!newFolder.exists() && !newFolder.mkdir()) {
                System.err.println("ERROR; COULD NOT CREATE FOLDER!");
                return false;
            }

            File file = new File(newFolder, file_name + ".xml");
            if(!file.exists() && !file.createNewFile())
            {
                System.err.println("ERROR; COULD NOT CREATE FILE!");
                return false;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(content);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean writeXMLToFilePath(org.w3c.dom.Document content,String file_path){
        try {

            if(directory.length() == 0) {
                System.err.println("ERROR; NO DIRECTORY SELECTED!");
                return false;
            }

            File file = new File(file_path);
            if(!file.exists() && !file.createNewFile())
            {
                System.err.println("ERROR; COULD NOT CREATE FILE!");
                return false;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(content);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void writeCurrentPlayerNameToTXT(String curr_player_name) {
        try {
            FileWriter file_writer = new FileWriter(Environment.
                    getExternalStorageDirectory()+ "/" + directory + "/curr_player.txt", false);
            BufferedWriter out = new BufferedWriter(file_writer);
            out.write(curr_player_name.trim());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentPlayerNameFromTXT()
    {
        String player_name = "NOT FOUND";
        BufferedReader buffered_reader = null;

        try {
            buffered_reader = new BufferedReader(new FileReader(Environment.
                    getExternalStorageDirectory()+ "/" + directory + "/curr_player.txt"));

            StringBuilder string_builder = new StringBuilder();
            String line = buffered_reader.readLine();

            while (line != null) {
                string_builder.append(line);
                line = buffered_reader.readLine();
            }
            player_name = string_builder.toString().trim();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            if (buffered_reader != null)
                buffered_reader.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return player_name;
    }

    public boolean playerFileExists()
    {
        File curr_player_file = new File(Environment.
                getExternalStorageDirectory()+
                "/" + directory + "/curr_player.txt");
        return curr_player_file.exists() && !curr_player_file.isDirectory();
    }

    public void writeHighScore(String tm_name, String player_name, int steps)
    {
        try {
            FileWriter file_writer = new FileWriter(Environment.
                    getExternalStorageDirectory()+ "/" + directory + "/"+
                    tm_name + "_scores.txt", true);
            BufferedWriter out = new BufferedWriter(file_writer);
            out.write(player_name.trim() + "-" + steps + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HighScoreEntry> getHighScore(String tm_name)
    {
        ArrayList<HighScoreEntry> all_scores = new ArrayList<>();
        BufferedReader buffered_reader = null;

        try {
            buffered_reader = new BufferedReader(new FileReader(Environment.
                    getExternalStorageDirectory()+ "/" + directory + "/"+
                    tm_name + "_scores.txt"));

            String line = buffered_reader.readLine();

            while (line != null) {
                String[] current_score = line.trim().split("-");
                if(current_score.length == 2)
                {
                    HighScoreEntry new_entry = new HighScoreEntry();
                    new_entry.player_name = current_score[0];
                    new_entry.step_counter = Integer.parseInt(current_score[1]);
                    all_scores.add(new_entry);
                }else
                  System.err.println("ERROR PARSING HIGHSCORE: ILLEGAL SCORE FORMAT!");

                line = buffered_reader.readLine();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            if (buffered_reader != null)
                buffered_reader.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

      return all_scores;
    }

    public void clearHighScore(String tm_name)
    {
        try {
            FileWriter file_writer = new FileWriter(Environment.
                    getExternalStorageDirectory()+ "/" + directory + "/"+
                    tm_name + "_scores.txt", false);
            BufferedWriter out = new BufferedWriter(file_writer);
            out.write("");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteTM(String tm_name)
    {
        File file = new File(Environment.
                getExternalStorageDirectory()+ "/" + directory + "/"+
                tm_name + ".xml");
        return file.delete();
    }
}