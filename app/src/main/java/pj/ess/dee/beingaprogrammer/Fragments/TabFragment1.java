package pj.ess.dee.beingaprogrammer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pj.ess.dee.beingaprogrammer.Activities.TutorialViewActivity;
import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by deepak on 4/1/2015.
 */
@SuppressWarnings("deprecation")
public class TabFragment1 extends android.support.v4.app.Fragment {
    public static String chapters[]=new String[10];
    public static int ListPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.quizlevel_list, container, false);



        if (TutorialListAdaptor.myTutorialPosition == 0) {
            chapters=new String[]{"Java Basics","Java Object Oriented"};

        } else if (TutorialListAdaptor.myTutorialPosition == 1) {
            chapters=new String[]{"JavaSript Basics","Javascript Advanced"};

        } else if (TutorialListAdaptor.myTutorialPosition == 2) {
            chapters=new String[]{"C++ Basics","C++ ObjectOriented"};

        } else if (TutorialListAdaptor.myTutorialPosition == 3) {
            chapters=new String[]{"HTML 5 Basics","HTML 5 Advanced"};

        } else if (TutorialListAdaptor.myTutorialPosition == 4) {
            chapters=new String[]{"CSS 3 Basics","CSS 3 Advanced"};

        } else if (TutorialListAdaptor.myTutorialPosition == 5) {
            chapters=new String[]{"JQuery Basics","JQuery Advanced"};

        }else if (TutorialListAdaptor.myTutorialPosition == 6) {
            chapters=new String[]{"HTML Basics","HTML Advanced"};

        } else if (TutorialListAdaptor.myTutorialPosition == 7) {
            chapters=new String[]{"PHP Basics","PHP ObjectOriented"};

        } else if (TutorialListAdaptor.myTutorialPosition == 8) {
            chapters=new String[]{"C# Basics","C# Advanced"};

        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.text_list, R.id.item, chapters);
        ListView listView = (ListView) layout.findViewById(R.id.list_items);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListPosition=position;
                Intent i=new Intent(getActivity(),TutorialViewActivity.class);
                startActivity(i);
            }
        });


        return layout;

    }
}
