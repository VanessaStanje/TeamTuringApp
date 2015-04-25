package at.sw2015.teamturingapp;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

public class XMLParser {

    public XMLParser(){
    }

    public org.w3c.dom.Document readRawXMLInput(InputStream input_stream)
            throws XmlPullParserException, IOException,
            ParserConfigurationException, SAXException {
        org.w3c.dom.Document document;
        DocumentBuilderFactory db_factory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder db = db_factory.newDocumentBuilder();
        InputSource input_source = new InputSource(input_stream);
        document = db.parse(input_source);
        return document;
    }
}
