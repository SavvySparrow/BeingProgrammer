package pj.ess.dee.beingaprogrammer.Adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pj.ess.dee.beingaprogrammer.MainPageFragments.HomePageFragment1;
import pj.ess.dee.beingaprogrammer.MainPageFragments.HomePageFragment2;

/**
 * Created by Sahil_Jalan on 31-03-2015.
 */
public class MyPagerAdaptor extends FragmentPagerAdapter {

    private Fragment mFragment = null;


    public MyPagerAdaptor(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                mFragment=new HomePageFragment2();
                break;
            case 1:
                mFragment=new HomePageFragment1();
                break;
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}