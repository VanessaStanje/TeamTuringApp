
package at.sw2015.teamturingapp.EditCards;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import at.sw2015.teamturingapp.Tabs.EditFragmentTab;
import at.sw2015.teamturingapp.EditRuleActivity;
import at.sw2015.teamturingapp.R;
import at.sw2015.teamturingapp.Tabs.ViewPagerAdapter;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class RuleCardsAdapter extends RecyclerView.Adapter<RuleCardsAdapter.RulesViewHolder> {

    private static List<RuleInfo> rule_list;
    private static int rule_counter = 0;

    public RuleCardsAdapter(List<RuleInfo> contactList) {
        rule_list = contactList;
        rule_counter = 0;
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
        contactViewHolder.rule_id = rule_counter++;
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
        protected int rule_id;
        public View view;

        public RulesViewHolder(View v) {
            super(v);
            view = v;

            view.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v) {
                    Intent intent=new Intent(view.getContext(),EditRuleActivity.class);
                    intent.putExtra("CURRENT_STATE", curr_state.getText());
                    intent.putExtra("READS_SIGN", reads_sign.getText());
                    intent.putExtra("WRITES_SIGN", writes_sign.getText());
                    intent.putExtra("MOVES", moves.getText());
                    intent.putExtra("NEXT_STATE", next_state.getText());
                    intent.putExtra("RULE_ID", ""+rule_id);
                    view.getContext().startActivity(intent);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener(){
                public boolean onLongClick(View v) {
                    showDeleteDialog(v.getContext());
                    return true;
                }
            });

            title = (TextView) v.findViewById(R.id.title);
            curr_state = (TextView) v.findViewById(R.id.currState);
            reads_sign = (TextView) v.findViewById(R.id.readsSign);
            writes_sign = (TextView) v.findViewById(R.id.writesSign);
            moves = (TextView) v.findViewById(R.id.moves);
            next_state = (TextView) v.findViewById(R.id.nextState);
        }


        public void showDeleteDialog(Context ctx)
        {
            AlertDialog.Builder alter_dialog_builder =
                    new AlertDialog.Builder(ctx,AlertDialog.THEME_HOLO_LIGHT);
            alter_dialog_builder.setMessage("Do you want to delete the rule #" + rule_id + "?");
            alter_dialog_builder.setCancelable(true);
            alter_dialog_builder.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            XMLParser.removeRule(rule_id);
                            EditFragmentTab.update();
                            ViewPagerAdapter.current_run_fragment.reset();
                            dialog.cancel();
                        }
                    });
            alter_dialog_builder.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alter_dialog = alter_dialog_builder.create();
            alter_dialog.show();
        }
    }
}