package at.sw2015.teamturingapp;
import java.util.Vector;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
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
        return "";
    }

}
