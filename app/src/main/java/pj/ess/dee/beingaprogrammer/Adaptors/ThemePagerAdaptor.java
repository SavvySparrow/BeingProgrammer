package pj.ess.dee.beingaprogrammer.Adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pj.ess.dee.beingaprogrammer.ThemePageFragemnts.DefaultThemePageFragment;
import pj.ess.dee.beingaprogrammer.ThemePageFragemnts.ThemePageFragment1;
import pj.ess.dee.beingaprogrammer.ThemePageFragemnts.ThemePageFragment2;
import pj.ess.dee.beingaprogrammer.ThemePageFragemnts.ThemePageFragment3;

/**
 * Created by Sahil_Jalan on 18-04-2015.
 */
public class ThemePagerAdaptor extends FragmentPagerAdapter {

    private Fragment mFragment = null;


    public ThemePagerAdaptor(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                mFragment=new DefaultThemePageFragment();
                break;
            case 1:
                mFragment=new ThemePageFragment1();
                break;
            case 2:
                mFragment=new ThemePageFragment2();
                break;
            case 3:
                mFragment=new ThemePageFragment3();
                break;

        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
