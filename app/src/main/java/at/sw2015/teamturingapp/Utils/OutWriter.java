package at.sw2015.teamturingapp.Utils;

import android.os.Environment;

import java.io.File;

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
            System.out.println("ERROR; " + e);
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
            System.out.println("ERROR; " + e);
            return false;
        }
        return true;
    }
}