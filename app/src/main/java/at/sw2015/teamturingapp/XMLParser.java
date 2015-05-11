package at.sw2015.teamturingapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;


public class XMLParser {

    final String AUTHOR = "AUTHOR";
    final String TAPE_COUNT = "TAPE_COUNT";
    final String INITIAL_STATE = "INITIAL_STATE";
    final String HEADS = "H";
    final String TAPES = "T";
    final String RULES = "R";

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

    public TMConfiguration readTMConfig(InputStream input_stream)
            throws XmlPullParserException, IOException,
            ParserConfigurationException, SAXException {

        org.w3c.dom.Document raw_xml_input = readRawXMLInput(input_stream);

        String author = raw_xml_input.getElementsByTagName(AUTHOR).item(0)
                .getTextContent();

        String initial_state = raw_xml_input.getElementsByTagName(INITIAL_STATE).item(0)
                .getTextContent();

        int tape_count = Integer.parseInt(raw_xml_input
                .getElementsByTagName(TAPE_COUNT).item(0).getTextContent());

        NodeList heads_list = raw_xml_input.getElementsByTagName(HEADS);
        NodeList tapes_list = raw_xml_input.getElementsByTagName(TAPES);
        NodeList rules_list = raw_xml_input.getElementsByTagName(RULES);

        Vector<Integer> head_positions = new Vector<>();
        Vector<String> all_tapes = new Vector<>();
        Vector<Vector<String>> all_rules = new Vector<>();

        for (int heads_counter = 0; heads_counter < heads_list.getLength(); heads_counter++)
            head_positions.add(Integer.parseInt(heads_list.item(heads_counter)
                    .getTextContent()));

        for (int tapes_counter = 0; tapes_counter < tapes_list.getLength(); tapes_counter++)
            all_tapes.add(tapes_list.item(tapes_counter).getTextContent());

        for (int rules_counter = 0; rules_counter < rules_list.getLength(); rules_counter++) {
            Vector<String> current_rule = new Vector<>();
            String current_rule_string = rules_list.item(rules_counter)
                    .getTextContent();
            String[] splitted_rule = current_rule_string.split("-");
            for (int elem_counter = 0; elem_counter < splitted_rule.length; elem_counter++)
                current_rule.add(splitted_rule[elem_counter]);
            all_rules.add(current_rule);
        }

        return new TMConfiguration(author, tape_count,initial_state, head_positions,
                all_tapes, all_rules);
    }

}
