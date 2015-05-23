
package at.sw2015.teamturingapp.EditCards;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import at.sw2015.teamturingapp.EditRuleActivity;
import at.sw2015.teamturingapp.R;

public class RuleCardsAdapter extends RecyclerView.Adapter<RuleCardsAdapter.RulesViewHolder> {

    private static List<RuleInfo> rule_list;

    public RuleCardsAdapter(List<RuleInfo> contactList) {
        rule_list = contactList;
    }

    @Override
    public int getItemCount() {
        return rule_list.size();
    }

    @Override
    public void onBindViewHolder(RulesViewHolder contactViewHolder, int i) {
        RuleInfo rule_info = rule_list.get(i);
        contactViewHolder.title.setText("RULE" + " #" + rule_info.rule_counter);
        contactViewHolder.curr_state.setText(rule_info.current_state);
        contactViewHolder.reads_sign.setText(rule_info.reads_sign);
        contactViewHolder.writes_sign.setText(rule_info.writes_sign);
        contactViewHolder.moves.setText(rule_info.moves);
        contactViewHolder.next_state.setText(rule_info.next_state);
        contactViewHolder.current_info = rule_info;
    }

    @Override
    public RulesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);
        return new RulesViewHolder(itemView);
    }

    public static class RulesViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView curr_state;
        protected TextView reads_sign;
        protected TextView writes_sign;
        protected TextView moves;
        protected TextView next_state;
        protected RuleInfo current_info;

        public View view;

        public RulesViewHolder(View v) {
            super(v);
            view = v;

            view.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v) {
                    // item clicked
                    System.err.println("CLICKED: " + current_info.current_state);
                    Intent intent=new Intent(view.getContext(),EditRuleActivity.class);
                    intent.putExtra("test", "testextra");
                    view.getContext().startActivity(intent);
                }
            });

            title = (TextView) v.findViewById(R.id.title);
            curr_state = (TextView) v.findViewById(R.id.currState);
            reads_sign = (TextView) v.findViewById(R.id.readsSign);
            writes_sign = (TextView) v.findViewById(R.id.writesSign);
            moves = (TextView) v.findViewById(R.id.moves);
            next_state = (TextView) v.findViewById(R.id.nextState);
        }


    }
}