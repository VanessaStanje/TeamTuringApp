package at.sw2015.teamturingapp.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import at.sw2015.teamturingapp.MainGameActivity;


public class XMLParser {

    final static String TMNAME = "TMNAME";
    final static String AUTHOR = "AUTHOR";
    final static String TAPE_COUNT = "TAPE_COUNT";
    final static String INITIAL_STATE = "INITIAL_STATE";
    final static String HEADS = "H";
    final static String TAPES = "T";
    final static String GOALS = "G";
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

    public static org.w3c.dom.Document readXMLInputFromSD(String file_path) {
        Document doc = null;
        try {
            File file = new File(file_path);
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

    public static org.w3c.dom.Document readXMLInputFromFile(File file) {
        Document doc = null;
        try {
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

        String tm_name = raw_xml_input.getElementsByTagName(TMNAME).item(0)
                .getTextContent();

        String author = raw_xml_input.getElementsByTagName(AUTHOR).item(0)
                .getTextContent();

        String initial_state = raw_xml_input.getElementsByTagName(INITIAL_STATE).item(0)
                .getTextContent();

        int tape_count = Integer.parseInt(raw_xml_input
                .getElementsByTagName(TAPE_COUNT).item(0).getTextContent());

        NodeList heads_list = raw_xml_input.getElementsByTagName(HEADS);
        NodeList tapes_list = raw_xml_input.getElementsByTagName(TAPES);
        NodeList goals_list = raw_xml_input.getElementsByTagName(GOALS);
        NodeList rules_list = raw_xml_input.getElementsByTagName(RULES);

        Vector<Integer> head_positions = new Vector<>();
        Vector<String> all_tapes = new Vector<>();
        Vector<String> all_goals = new Vector<>();
        Vector<Vector<String>> all_rules = new Vector<>();

        for (int heads_counter = 0; heads_counter < heads_list.getLength(); heads_counter++)
            head_positions.add(Integer.parseInt(heads_list.item(heads_counter)
                    .getTextContent()));

        for (int tapes_counter = 0; tapes_counter < tapes_list.getLength(); tapes_counter++)
            all_tapes.add(tapes_list.item(tapes_counter).getTextContent());

        for (int goals_counter = 0; goals_counter < tapes_list.getLength(); goals_counter++)
            all_goals.add(goals_list.item(goals_counter).getTextContent());

        for (int rules_counter = 0; rules_counter < rules_list.getLength(); rules_counter++) {
            Vector<String> current_rule = new Vector<>();
            String current_rule_string = rules_list.item(rules_counter)
                    .getTextContent();
            Collections.addAll(current_rule, current_rule_string.split("-"));
            all_rules.add(current_rule);
        }

        return new TMConfiguration(tm_name,author, tape_count, initial_state, head_positions,
                all_tapes,all_goals, all_rules);
    }

    public static boolean saveTMRule(String current_state, String reads_sign, String writes_sign,
                                     String moves, String next_state, int index)
            throws XmlPullParserException, IOException,
            ParserConfigurationException, SAXException {

        org.w3c.dom.Document raw_xml_input = readXMLInputFromSD(MainGameActivity.curr_tm_file_name_path);

        NodeList rules_list = raw_xml_input.getElementsByTagName(RULES);

        String new_content = current_state + "-" +
                reads_sign + "-" + writes_sign + "-" + moves + "-" + next_state;

        rules_list.item(index).setTextContent(new_content);

        String file_path = MainGameActivity.curr_tm_file_name_path;
        return MainGameActivity.out_writer.writeXMLToFilePath(raw_xml_input, file_path);
    }

    public static boolean addNewRule(String new_rule) {
        org.w3c.dom.Document raw_xml_input = readXMLInputFromSD(MainGameActivity.curr_tm_file_name_path);

        NodeList rules_list = raw_xml_input.getElementsByTagName("RULES");
        Node new_child = raw_xml_input.createElement("R");
        new_child.setTextContent(new_rule);
        Element rule = (Element) rules_list.item(0);
        rule.appendChild(new_child);

        String file_path = MainGameActivity.curr_tm_file_name_path;
        return MainGameActivity.out_writer.writeXMLToFilePath(raw_xml_input, file_path);
    }

    public static boolean removeRule(int index) {
        org.w3c.dom.Document raw_xml_input = readXMLInputFromSD(MainGameActivity.curr_tm_file_name_path);

        NodeList main_rule = raw_xml_input.getElementsByTagName("RULES");
        NodeList rules_list = raw_xml_input.getElementsByTagName(RULES);

        if (index >= rules_list.getLength() || main_rule.getLength() == 0)
            return false;

        main_rule.item(0).removeChild(rules_list.item(index));
        String file_path = MainGameActivity.curr_tm_file_name_path;
        return MainGameActivity.out_writer.writeXMLToFilePath(raw_xml_input, file_path);
    }

    public static boolean writeNewTM(String tm_name, String author,
                                     String initial_state, String tape_count,
                                     String heads_position,String tape_content,String goal_content) {
        try{
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc=parser.newDocument();

            Element root=doc.createElement("TM_CONFIG");
            doc.appendChild(root);

            Element TM_NAME=doc.createElement("TMNAME");
            TM_NAME.setTextContent(tm_name);
            root.appendChild(TM_NAME);

            Element AUTHOR=doc.createElement("AUTHOR");
            AUTHOR.setTextContent(author);
            root.appendChild(AUTHOR);

            Element TAPE_COUNT=doc.createElement("TAPE_COUNT");
            TAPE_COUNT.setTextContent(tape_count);
            root.appendChild(TAPE_COUNT);

            Element INITIAL_STATE=doc.createElement("INITIAL_STATE");
            INITIAL_STATE.setTextContent(initial_state);
            root.appendChild(INITIAL_STATE);

            Element HEADS=doc.createElement("HEADS");
            root.appendChild(HEADS);

            Element H1 = doc.createElement("H");
            H1.setTextContent(heads_position);
            HEADS.appendChild(H1);

            Element TAPES=doc.createElement("TAPES");
            root.appendChild(TAPES);

            Element T1 = doc.createElement("T");
            T1.setTextContent(tape_content);
            TAPES.appendChild(T1);

            Element GOALS=doc.createElement("GOALS");
            root.appendChild(GOALS);

            //Todo: Update Test and func.
            Element G1 = doc.createElement("G");
            G1.setTextContent(goal_content);
            GOALS.appendChild(G1);

            Element RULES=doc.createElement("RULES");
            root.appendChild(RULES);

            Element INITIAL_RULE = doc.createElement("R");
            INITIAL_RULE.setTextContent(initial_state + "-0-0-H-" + initial_state);
            RULES.appendChild(INITIAL_RULE);

            MainGameActivity.out_writer.writeXMLToFileName(doc,tm_name);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

}