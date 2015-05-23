package at.sw2015.teamturingapp;

import android.os.Environment;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class OutWriter {

    public String directory = "";

    public  OutWriter(String directory)
    {
    }

    public boolean writeXMLToFile(org.w3c.dom.Document content,String file_name) {
        return true;
    }
}