package at.sw2015.teamturingapp;
import java.util.Vector;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;
public class TMView {

    private Vector<ImageView> all_image_views_ = null;
    private Drawable base_ = null;
    private Drawable one_ = null;
    private Drawable one_sel_ = null;
    private Drawable zero_ = null;
    private Drawable zero_sel_ = null;
    private Drawable dollar_ = null;
    private Drawable dollar_sel_ = null;
    private Drawable underline_ = null;
    private Drawable underline_sel_ = null;
    private final int VISIBLE_TAPE_LENGTH = 9;

    public TMView(Vector<ImageView> all_image_views, Drawable base,
                  Drawable one, Drawable one_sel, Drawable zero, Drawable zero_sel,
                  Drawable dollar, Drawable dollar_sel, Drawable underline,
                  Drawable underline_sel) {
        this.all_image_views_ = all_image_views;
        this.base_ = base;
        this.one_ = one;
        this.one_sel_ = one_sel;
        this.zero_ = zero;
        this.zero_sel_ = zero_sel;
        this.dollar_ = dollar;
        this.dollar_sel_ = dollar_sel;
        this.underline_ = underline;
        this.underline_sel_ = underline_sel;
    }

    public String printTMState(TMConfiguration tm_config, Context ctx) {
        String tm_out = "--------------TM OUT--------------\n";

        for (int tape_counter = 0; tape_counter < tm_config.getAllTapes()
                .size(); tape_counter++)
            tm_out += "TAPE T" + tape_counter + "-> "
                    + tm_config.getAllTapes().get(tape_counter) + "\n";

        tm_out += "CURRENT STATE: " + tm_config.getCurrentState() + "\n";

        for (int head_counter = 0; head_counter < tm_config.getHeadPositions()
                .size(); head_counter++)
            tm_out += "HEAD " + head_counter + " ON POS: "
                    + tm_config.getHeadPositions().get(head_counter) + "\n";

        for (int rule_counter = 0; rule_counter < tm_config.getAllRules()
                .size(); rule_counter++)
            tm_out += "RULE R" + rule_counter + "-> "
                    + tm_config.getAllRules().get(rule_counter) + "\n";

        tm_out += "--------------TM OUT--------------\n";

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(ctx, tm_out, duration);
        toast.show();

        return tm_out;
    }

    public void updateView(TMConfiguration tm_config)
    {
        String tape_one_in = tm_config.getAllTapes().get(0);
        String[] tape_one = tape_one_in.split("-");

        int diff = VISIBLE_TAPE_LENGTH - tape_one.length;
        int counter = 0;

        if(diff < 0)
        {
            counter = diff;
            diff = 0;
        }

        for(diff/=2; counter < VISIBLE_TAPE_LENGTH; counter++)
        {
            if(counter < diff || counter >= tape_one.length+diff)
                all_image_views_.get(counter).setImageDrawable(underline_);
            else
            {
                if(tape_one[counter-diff].equalsIgnoreCase("0") && counter-diff != tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(zero_);
                else if(tape_one[counter-diff].equalsIgnoreCase("0") && counter-diff == tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(zero_sel_);
                else if(tape_one[counter-diff].equalsIgnoreCase("1") && counter-diff != tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(one_);
                else if(tape_one[counter-diff].equalsIgnoreCase("1") && counter-diff == tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(one_sel_);
                else if(tape_one[counter-diff].equalsIgnoreCase("$") && counter-diff != tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(dollar_);
                else if(tape_one[counter-diff].equalsIgnoreCase("$") && counter-diff == tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(dollar_sel_);
                else if(tape_one[counter-diff].equalsIgnoreCase("_") && counter-diff != tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(underline_);
                else if(tape_one[counter-diff].equalsIgnoreCase("_") && counter-diff == tm_config.getHeadPositions().get(0))
                    all_image_views_.get(counter).setImageDrawable(underline_sel_);
            }
        }
    }

}