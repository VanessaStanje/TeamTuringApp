package at.sw2015.teamturingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import at.sw2015.teamturingapp.EditCards.RuleCardsAdapter;
import at.sw2015.teamturingapp.EditCards.RuleInfo;

public class EditFragmentTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.activity_edit, container, false);

        RecyclerView recyclerView = (RecyclerView) root_view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RuleCardsAdapter ca = new RuleCardsAdapter(initRulesView(((MainActivity)getActivity()).current_tm_config));
        recyclerView.setAdapter(ca);

        return  root_view;
    }

    public List<RuleInfo> initRulesView(TMConfiguration current_config) {
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


    public static TMConfiguration readTMConfig()
    {
        return null;
    }
}