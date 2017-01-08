package pj.ess.dee.beingaprogrammer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.QuizLevels.BeforeLevelsIntroduction;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by deepak on 3/31/2015.
 */
public  class TabFragment3 extends Fragment {

    public static ViewPager mpager;
    public static int selectedPosition;
    private boolean setToast=true;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.quizlevel_list, container, false);
            final String categories[] = {"Beginner", "Intermediate", "Expert"};
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.text_list, R.id.item, categories);
            ListView listView = (ListView) layout.findViewById(R.id.list_items);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(TutorialListAdaptor.myTutorialPosition == 0||TutorialListAdaptor.myTutorialPosition == 2){

                        setToast=false;
                    }
                    if(position==0) {

                      selectedPosition=position;

                  }
                    else if(position==1){
                      selectedPosition=position;

                  }
                    else if (position==2){
                      selectedPosition=position;

                  }if (!setToast)
                    startActivity(new Intent(getActivity(),BeforeLevelsIntroduction.class));
                    else
                        setToast();
                }
            });

        return layout;
      //  View rootView=inflater.inflate(R.layout.quizpager_layout,container,false);
       // mpager=(ViewPager)rootView.findViewById(R.id.quizpager);
        //mpager.setAdapter(new QuizPageAdapter(getActivity().getSupportFragmentManager()));
        //mpager.setCurrentItem(1);
        //return rootView;

    }

    private void setToast() {
        Toast.makeText(getActivity(),"Coming Soon ..Wait for Update.....",Toast.LENGTH_SHORT).show();
    }
}

