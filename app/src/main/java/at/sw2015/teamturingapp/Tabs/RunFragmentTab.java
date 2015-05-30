package at.sw2015.teamturingapp.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import at.sw2015.teamturingapp.MainActivity;
import at.sw2015.teamturingapp.R;
import at.sw2015.teamturingapp.Utils.TMConfiguration;
import at.sw2015.teamturingapp.TMEngine;
import at.sw2015.teamturingapp.TMView;
import at.sw2015.teamturingapp.Utils.XMLParser;

public class RunFragmentTab extends Fragment implements View.OnClickListener {

    // Made public to check in MainActivityTest
    // which test file was loaded
    private TMEngine tm_engine = new TMEngine();
    private TMView tm_view = null;
    private TMConfiguration current_tm_config = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.activity_run, container, false);

        try {
            org.w3c.dom.Document raw_xml_input = XMLParser.
                    readXMLInputFromSD(MainActivity.curr_tm_file_name_path);
            System.err.println(MainActivity.curr_tm_file_name_path);
            current_tm_config = XMLParser.readTMConfig(raw_xml_input);
        } catch (XmlPullParserException | IOException
                | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        Button step_button = (Button) root_view.findViewById(R.id.button_step);
        Button show_button = (Button) root_view.findViewById(R.id.button_show);
        Button reset_button = (Button) root_view.findViewById(R.id.button_reset);

        step_button.setOnClickListener(this);
        show_button.setOnClickListener(this);
        reset_button.setOnClickListener(this);

        Vector<ImageView> all_image_views = new Vector<>();
        all_image_views.add((ImageView) root_view.findViewById(R.id.field1));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field2));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field3));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field4));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field5));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field6));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field7));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field8));
        all_image_views.add((ImageView) root_view.findViewById(R.id.field9));

        MainActivity activity = (MainActivity) getActivity();

        tm_view = new TMView(all_image_views, ((activity)).getResources().getDrawable(R.mipmap.base),
                ((activity)).getResources().getDrawable(R.mipmap.one),
                ((activity)).getResources().getDrawable(R.mipmap.one_sel),
                ((activity)).getResources().getDrawable(R.mipmap.zero),
                ((activity)).getResources().getDrawable(R.mipmap.zero_sel),
                ((activity)).getResources().getDrawable(R.mipmap.dollar),
                ((activity)).getResources().getDrawable(R.mipmap.dollar_sel),
                ((activity)).getResources().getDrawable(R.mipmap.underline),
                ((activity)).getResources().getDrawable(R.mipmap.underline_sel));

        tm_view.printTMState(current_tm_config, getActivity().getBaseContext());
        tm_view.updateView(current_tm_config);

        return root_view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_step:
                tm_engine.step(current_tm_config);
                tm_view.updateView(current_tm_config);
                break;
            case R.id.button_show:
                if (current_tm_config != null)
                    tm_view.printTMState(current_tm_config, getActivity().getBaseContext());
                break;
            case R.id.button_reset:
                reset();
                break;
        }
    }

    public void reset(){
        try {
            org.w3c.dom.Document raw_xml_input = XMLParser.
                    readXMLInputFromSD(MainActivity.curr_tm_file_name_path);
            current_tm_config = XMLParser.readTMConfig(raw_xml_input);
        } catch (XmlPullParserException | IOException
                | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        tm_view.updateView(current_tm_config);
    }
}