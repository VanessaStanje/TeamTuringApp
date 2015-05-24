package at.sw2015.teamturingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.Environment;


public class XMLParser {

    final static String AUTHOR = "AUTHOR";
    final static String TAPE_COUNT = "TAPE_COUNT";
    final static String INITIAL_STATE = "INITIAL_STATE";
    final static String HEADS = "H";
    final static String TAPES = "T";
    final static String RULES = "R";

    public XMLParser() {
    }

    public static org.w3c.dom.Document readRawXMLInput(InputStream input_stream)
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

    public static org.w3c.dom.Document readXMLInputFromSD(String file_name)
    {
        Document doc = null;
        try {
            File file = new File(Environment.
                    getExternalStorageDirectory()+ "/TMConfigs/" + file_name + ".xml");
            InputStream input_stream = new FileInputStream(file.getPath());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc_builder = dbf.newDocumentBuilder();
            doc = doc_builder.parse(new InputSource(input_stream));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("ERROR WHILE READING FROM SD; " + e);
        }
        return doc;
    }

    public static TMConfiguration readTMConfig(org.w3c.dom.Document raw_xml_input)
            throws XmlPullParserException, IOException,
            ParserConfigurationException, SAXException {

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

        return new TMConfiguration(author, tape_count, initial_state, head_positions,
                all_tapes, all_rules);
    }

    public static boolean saveTMRule(String current_state, String reads_sign, String writes_sign,
                                     String moves, String next_state, int index, Context ctx)
            throws XmlPullParserException, IOException,
            ParserConfigurationException, SAXException{

        org.w3c.dom.Document raw_xml_input = readXMLInputFromSD(MainActivity.curr_tm_file_name);

        NodeList rules_list = raw_xml_input.getElementsByTagName(RULES);

        String new_content = current_state + "-" +
                reads_sign + "-" + writes_sign + "-" + moves + "-" + next_state;

        rules_list.item(index).setTextContent(new_content);

        String file_name = MainActivity.curr_tm_file_name;
        return MainActivity.out_writer.writeXMLToFile(raw_xml_input,file_name);
    }

    public static boolean addNewRule(String new_rule)
    {
        org.w3c.dom.Document raw_xml_input = readXMLInputFromSD(MainActivity.curr_tm_file_name);

        NodeList rules_list = raw_xml_input.getElementsByTagName("RULES");
        Node new_child = raw_xml_input.createElement("R");
        new_child.setTextContent(new_rule);
        Element rule = (Element)rules_list.item(0);
        rule.appendChild(new_child);

        String file_name = MainActivity.curr_tm_file_name;
        return MainActivity.out_writer.writeXMLToFile(raw_xml_input,file_name);
    }
}