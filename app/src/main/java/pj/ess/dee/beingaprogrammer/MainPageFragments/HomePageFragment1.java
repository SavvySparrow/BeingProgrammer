package pj.ess.dee.beingaprogrammer.MainPageFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pj.ess.dee.beingaprogrammer.Fragments.HomePageRecyclerFragment;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 25-03-2015.
 */
public class HomePageFragment1 extends Fragment {



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_screen_slide_page, container, false);


          getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_Recycle, new HomePageRecyclerFragment())
                    .commit();
            return rootView;
        }
    }