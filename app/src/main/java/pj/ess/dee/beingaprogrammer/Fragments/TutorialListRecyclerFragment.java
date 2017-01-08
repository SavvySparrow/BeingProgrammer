package pj.ess.dee.beingaprogrammer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 16-03-2015.
 */
public class TutorialListRecyclerFragment extends Fragment {

    private RecyclerView mRecycleView;
    private TutorialListAdaptor adaptor;
    public TutorialListRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tutorial_list_recycler_fragment, container, false);
        mRecycleView = (RecyclerView) layout.findViewById(R.id.tutorial_list_recycler_view_list_id);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptor = new TutorialListAdaptor(getActivity(), getData());
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adaptor);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = (int) Math.floor(dpWidth/120);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),columns));

        //llm.setOrientation(LinearLayoutManager.VERTICAL);
    }
    //getting Data from Strings in Recycler View
    public static List<TutorialListAdaptor.TutorialListInformation> getData() {
        List<TutorialListAdaptor.TutorialListInformation> data = new ArrayList<>();

        int[] Images = {R.drawable.javalogo, R.drawable.javascriptlogo, R.drawable.cpluslogo,
                R.drawable.html5logo, R.drawable.css3logo, R.drawable.jquerylogo,
                R.drawable.htmllogo, R.drawable.phplogo, R.drawable.chashlogo,
                R.drawable.javascriptlogo, R.drawable.html5logo, R.drawable.javalogo,
                R.drawable.javascriptlogo, R.drawable.css3logo, R.drawable.javalogo,
                //Repeated List
                R.drawable.javalogo, R.drawable.javascriptlogo, R.drawable.chashlogo,
                R.drawable.html5logo, R.drawable.css3logo, R.drawable.jquerylogo,
                R.drawable.htmllogo, R.drawable.phplogo, R.drawable.cpluslogo,
                R.drawable.javascriptlogo, R.drawable.html5logo, R.drawable.javalogo,
                R.drawable.javascriptlogo, R.drawable.css3logo, R.drawable.javalogo};

        //String[] Titles = {"Java", "C++", "C", "HTML", "PHP", "Data Structure",};


        for (int i = 0; i <Images.length; i++) {
            TutorialListAdaptor.TutorialListInformation current = new TutorialListAdaptor.TutorialListInformation();
            current.ImageId = Images[i];
           // current.TitleTextId=Titles[i];
            data.add(current);
        }

        return data;
    }



}
