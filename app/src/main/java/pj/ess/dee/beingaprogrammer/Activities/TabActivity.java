package pj.ess.dee.beingaprogrammer.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import pj.ess.dee.beingaprogrammer.Adaptors.HomePageAdaptor;
import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.Fragments.NavigationDrawerFragment;
import pj.ess.dee.beingaprogrammer.Fragments.TabFragment1;
import pj.ess.dee.beingaprogrammer.Fragments.TabFragment2;
import pj.ess.dee.beingaprogrammer.Fragments.TabFragment3;
import pj.ess.dee.beingaprogrammer.R;
import pj.ess.dee.beingaprogrammer.TabLayout.SlidingTabLayout;
import pj.ess.dee.beingaprogrammer.Toolbars.InitializeDefaultStatus_ToolBarFragment;


public class TabActivity extends ActionBarActivity {

    public static ViewPager mPager;
    public static boolean isRunning;
    public static Toolbar toolbar;
    public static boolean ifTabBackButtonPressed,firstRun;
    LinearLayout primaryDark;
    SharedPreferences sharedPref;
    public static SlidingTabLayout mTabs;
    String[] titles = {"Java", "JavaScript", "C#", "HTML 5", "CSS 3", "JQuery", "HTML", "PHP", "C++"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("ThemePref",0);
        SharedPreferences.Editor editor = sharedPref.edit();


        if(sharedPref.getBoolean("FullScreen",MainActivity.isfullscreen)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //editor.putBoolean("FullScreen",MainActivity.isfullscreen);
            //editor.apply();
        }
        setContentView(R.layout.tab_activity);


        /** Status Bar And Tool Bar Fragment Initializer*/
        initializeDefaultStatusBar_ToolBarFragment();


    }

    @Override
    protected void onStart() {
        super.onStart();
        toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        primaryDark = (LinearLayout) findViewById(R.id.main_tab_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        for (int i = 0; i < titles.length; i++) {
            if (TutorialListAdaptor.myTutorialPosition == i)
                toolbar.setTitle(titles[i]);
        }
        toolbar.setSubtitle("Learn.Do.Share");
        isRunning = true;
        firstRun=true;
        //TabActivity.toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_pager));
        //TabActivity.mPager.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_pager));
        //TabActivity.mTabs.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_pager));
        CheckAndSetTheme();

    }

    private void CheckAndSetTheme() {
        sharedPref = getSharedPreferences("ThemePref", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (MainActivity.Set_Theme) {
            switch (MainActivity.Theme_id) {
                case 1:
                    toolbar.setBackgroundResource(R.color.colorTeal);
                    primaryDark.setBackgroundResource(R.color.colorTealDark);
                    mTabs.setBackgroundColor(getResources().getColor(R.color.colorTeal));
                    mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorTealAccent));
                    editor.putBoolean("SetTheme", MainActivity.Set_Theme);
                    editor.putInt("Theme_id", MainActivity.Theme_id);
                    editor.apply();
                    break;
                case 2:
                    toolbar.setBackgroundResource(R.color.colorIndego);
                    primaryDark.setBackgroundResource(R.color.colorIndegoDark);
                    mTabs.setBackgroundColor(getResources().getColor(R.color.colorIndego));
                    mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorIndegoAccent));
                    editor.putBoolean("SetTheme", MainActivity.Set_Theme);
                    editor.putInt("Theme_id", MainActivity.Theme_id);
                    editor.apply();
                    break;
                case 3:
                    toolbar.setBackgroundResource(R.color.colorOrange);
                    primaryDark.setBackgroundResource(R.color.colorOrangeDark);
                    mTabs.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                    mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorOrangeAccent));
                    editor.putBoolean("SetTheme", MainActivity.Set_Theme);
                    editor.putInt("Theme_id", MainActivity.Theme_id);
                    editor.apply();
                    break;
                default: //Default Theme Case
                    toolbar.setBackgroundResource(R.color.colorPrimary);
                    primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
                    mTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
                    editor.putBoolean("SetTheme", MainActivity.Set_Theme);
                    editor.putInt("Theme_id", MainActivity.Theme_id);
                    editor.apply();
                    break;
            }
        } else { // Default Theme
            toolbar.setBackgroundResource(R.color.colorPrimary);
            primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
            mTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (NavigationDrawerFragment.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //  if (id == R.id.Home_Button) {
        //    return true;
        //}
        if (id == R.id.search_button1) {
            return true;
        }
        if (id == R.id.share_button) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sTit= "******** Being Programmer ********\n\n\n" +
                    "Complete Programming Libraries\n" +
                    "Programming Quiz\n" +"Best In Class Examples\n\n" +
                    "Download Now! & Become a Programmer\n";
            i.putExtra(Intent.EXTRA_SUBJECT, "Being Programmer");
            i.putExtra(Intent.EXTRA_TITLE, "******** Being Programmer ********\n\n");

            String sAux = "\nLet me Recommend you this Wonderful Tutorial Application.Worlds First Well Designed Programming tutorial Android App!\n\n";
            sAux =  sAux + sTit + "\nDownload Link : https://www.mediafire.com/?yiei58s4vhuf48y \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            //i.putExtra(Intent.EXTRA_TEXT,"\n\nDevelopers:\nSahil Jalan & Deppak Pandey\n");
            this.startActivity(Intent.createChooser(i, "Choose One"));
            return true;
        }

        //  if (id == android.R.id.home) {
        //     NavUtils.navigateUpFromSameTask(this);

        //  }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ifTabBackButtonPressed=true;
        Intent myTab = new Intent(this, MainActivity.class);
        myTab.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myTab);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }

    private void initializeDefaultStatusBar_ToolBarFragment() {
        Fragment fragment = new InitializeDefaultStatus_ToolBarFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, "InitializeDefaultStatus_ToolBarFragment");
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        //Calling Home Navigation Drawer and pass different arguments includes toolbar ,
        //navigation fragment xml layout and drawer layout id
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer_navigation1);
        drawerFragment.setUp(R.id.fragment_drawer_navigation1, (DrawerLayout) findViewById(R.id.drawer_layout1), toolbar);


        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));

        // mHomePageAdaptor Home Page Clicked Position
        if (HomePageAdaptor.mHomePagePosition == 0) {
            mPager.setCurrentItem(0);
            mTabs.setSelected(true);
        } else if (HomePageAdaptor.mHomePagePosition == 1) {
            mPager.setCurrentItem(2);
            mTabs.setSelected(true);
        } else if (HomePageAdaptor.mHomePagePosition == 2) {
            mPager.setCurrentItem(1);
            mTabs.setSelected(true);
        }

        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setViewPager(mPager);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        NavigationDrawerFragment.mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    class TabPagerAdapter extends FragmentPagerAdapter {

        String[] tabs;


        public TabPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new TabFragment1();
                    break;
                case 1:
                    fragment = new TabFragment2();
                    break;
                case 2:
                    fragment = new TabFragment3();
                    break;
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String string = new String("  ");
            if (position == 0) {
                string = new String("Tutorial");
            } else if (position == 1) {
                string = new String("Programs");
            } else if (position == 2) {
                string = new String("Quiz");
            }

            return string;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

