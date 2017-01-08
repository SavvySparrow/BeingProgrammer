package pj.ess.dee.beingaprogrammer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Adaptors.HomePageAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 16-03-2015.
 */
public class HomePageRecyclerFragment extends Fragment {

    private RecyclerView mRecycleView;
    private HomePageAdaptor adaptor;

    public HomePageRecyclerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.home_page_recycler_fragment, container, false);
        mRecycleView = (RecyclerView) layout.findViewById(R.id.home_page_recycler_view_list_id);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptor = new HomePageAdaptor(getActivity(), getData());
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adaptor);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = (int) Math.floor(dpWidth/250);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),columns));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
    }
    //getting Data from Strings in Recycler View
    public static List<HomePageAdaptor.HomePageListInformation> getData() {
        List<HomePageAdaptor.HomePageListInformation> data = new ArrayList<>();

        int[] Images = {R.drawable.library, R.drawable.chakboard2, R.drawable.programs};

        String[] MainTitles = {"Programming Libraries", "Programming Quiz", "Solved Examples",};

        String[] Titles = {"Programming Libraries", "Quiz ?", "Programs",};

        String[] SubTitles = {"Wide range of Programming Tutorials with best in class Examples.....", "Prove yourself here & become expert.....",
                "Large Numbers of solved Examples.....",};

        String[] Action = {"Start Tutorial", "Prove IT!", "GO",};


        for (int i = 0; i <Images.length && i<MainTitles.length && i<SubTitles.length; i++) {
            HomePageAdaptor.HomePageListInformation current = new HomePageAdaptor.HomePageListInformation();
            current.ImageId = Images[i];
            current.MainTitleTextId = MainTitles[i];
            current.TitleTextId=Titles[i];
            current.SubtitleTextId=SubTitles[i];
            current.ShareId="Share";
            current.LearnMoreId=Action[i];
            data.add(current);
        }

        return data;
    }
}
