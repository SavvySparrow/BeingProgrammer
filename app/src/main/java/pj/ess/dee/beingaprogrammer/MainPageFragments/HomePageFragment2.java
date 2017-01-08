package pj.ess.dee.beingaprogrammer.MainPageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pj.ess.dee.beingaprogrammer.Fragments.TutorialListRecyclerFragment;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 27-03-2015.
 */
public class HomePageFragment2 extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_list_view_fragment, container,
                false);


        /**if (MainActivity.toolbar != null) {
            //((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);

         if(MainActivity.myPager.getCurrentItem()==0)
         MainActivity.toolbar.setTitle("Programming Libraries");
         else
         MainActivity.toolbar.setTitle("Being Programmer");
            MainActivity.toolbar.setNavigationIcon(R.drawable.ic_action_menu);
            MainActivity.toolbar.setSubtitle("Learn.Design.Market");
        }*/

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.Tutoriallist_Recycle, new TutorialListRecyclerFragment())
                .commit();
        return rootView;
    }
}