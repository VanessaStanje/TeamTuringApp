package at.sw2015.teamturingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import at.sw2015.teamturingapp.EditCards.RuleCardsAdapter;
import at.sw2015.teamturingapp.EditCards.RuleInfo;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class EditFragmentTab extends Fragment {

    private static RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.activity_edit, container, false);

        recyclerView = (RecyclerView) root_view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        TMConfiguration current_tm_config = readTMConfig();
        RuleCardsAdapter ca = new RuleCardsAdapter(initRulesView(current_tm_config));
        recyclerView.setAdapter(ca);

        Button new_rule_button = (Button) root_view.findViewById(R.id.button_new_rule);
        new_rule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),NewRuleActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        return  root_view;
    }

    public static List<RuleInfo> initRulesView(TMConfiguration current_config) {
        List<RuleInfo> result = new ArrayList<>();
        Vector<Vector<String>> all_rules = current_config.getAllRules();

        for(int rules_counter = 0; rules_counter < all_rules.size(); rules_counter++)
        {
            RuleInfo new_rule_info = new RuleInfo();
            new_rule_info.rule_counter = rules_counter;
            new_rule_info.current_state = all_rules.get(rules_counter).get(0);
            new_rule_info.reads_sign = all_rules.get(rules_counter).get(1);
            new_rule_info.writes_sign = all_rules.get(rules_counter).get(2);
            new_rule_info.moves = all_rules.get(rules_counter).get(3);
            new_rule_info.next_state = all_rules.get(rules_counter).get(4);
            result.add(new_rule_info);
        }
        return result;
    }

    public static void update()
    {
        TMConfiguration current_tm_config = readTMConfig();
        RuleCardsAdapter ca = new RuleCardsAdapter(initRulesView(current_tm_config));
        recyclerView.setAdapter(ca);
    }

    public static TMConfiguration readTMConfig()
    {
        TMConfiguration current_tm_config = null;
        try {
            org.w3c.dom.Document raw_xml_input = XMLParser.
                    readXMLInputFromSD(MainActivity.curr_tm_file_name_path);
            current_tm_config = XMLParser.readTMConfig(raw_xml_input);
        } catch (XmlPullParserException | IOException
                | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return current_tm_config;
    }


}