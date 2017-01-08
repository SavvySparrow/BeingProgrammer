package pj.ess.dee.beingaprogrammer.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import pj.ess.dee.beingaprogrammer.Adaptors.MyPagerAdaptor;
import pj.ess.dee.beingaprogrammer.Fragments.NavigationDrawerFragment;
import pj.ess.dee.beingaprogrammer.R;
import pj.ess.dee.beingaprogrammer.Toolbars.InitializeDefaultStatus_ToolBarFragment;
import pj.ess.dee.beingaprogrammer.util.Screen;


public class MainActivity extends ActionBarActivity {

    public static Toolbar toolbar;
    public static LinearLayout primaryDark;
    public static ViewPager myPager;
    public static boolean Set_Theme;
    private Handler mHandler = new Handler();
    public static boolean isRunning,isfullscreen;
    //public static boolean Default_Theme;
    public static int Theme_id;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (myPager.getCurrentItem() == 1)
            Screen.lockOrientation(this);
        //else
        // toolbar.setTitle("Programming Libraries")
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("ThemePref",0);
        SharedPreferences.Editor editor = sharedPref.edit();

      /*  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            isfullscreen = true;
            editor.putBoolean("FullScreen",isfullscreen);
            editor.apply();
            //NavigationDrawerFragment.fullScreenSwitch.setChecked(isfullscreen);
        }*/
        if(sharedPref.getBoolean("FullScreen",isfullscreen)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //editor.putBoolean("FullScreen",isfullscreen);
            //editor.apply();
        }
        setContentView(R.layout.activity_main);

        //if (savedInstanceState!=null) {
        //     SharedPreferences sharedPref = getSharedPreferences("ThemePref", 0);
        //   Set_Theme = sharedPref.getBoolean("SetTheme", false);

        //Default_Theme = sharedPref.getBoolean("DefaultTheme",Default_Theme);
        //}

        /** Status Bar And Tool Bar Fragment Initializer*/
        initializeDefaultStatusBar_ToolBarFragment();
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
                getFragmentManager().findFragmentById(R.id.fragment_drawer_navigation);
        drawerFragment.setUp(R.id.fragment_drawer_navigation, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        myPager = (ViewPager) findViewById(R.id.mypager);
        myPager.setAdapter(new MyPagerAdaptor(getSupportFragmentManager()));
        myPager.setCurrentItem(1,true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeDefaultStatusBar_ToolBarFragment();
        isRunning = true;
        toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        primaryDark = (LinearLayout) findViewById(R.id.main_layout);
        SharedPreferences sharedPref = getSharedPreferences("ThemePref",0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("FullScreen",isfullscreen);
        //Set_Theme = sharedPref.getBoolean("SetTheme", true);
        //Theme_id = my
        if (Set_Theme) {
            switch (Theme_id) {
                case 1:
                    MainActivity.toolbar.setBackgroundResource(R.color.colorTeal);
                    MainActivity.primaryDark.setBackgroundResource(R.color.colorTealDark);
                    editor.putBoolean("SetTheme", Set_Theme);
                    editor.putInt("Theme_id", Theme_id);
                    editor.apply();
                    break;
                case 2:
                    MainActivity.toolbar.setBackgroundResource(R.color.colorIndego);
                    MainActivity.primaryDark.setBackgroundResource(R.color.colorIndegoDark);
                    //editor.clear();
                    editor.putBoolean("SetTheme", Set_Theme);
                    editor.putInt("Theme_id", Theme_id);
                    editor.apply();
                    break;
                case 3:
                    MainActivity.toolbar.setBackgroundResource(R.color.colorOrange);
                    MainActivity.primaryDark.setBackgroundResource(R.color.colorOrangeDark);
                    //editor.clear();
                    editor.putBoolean("SetTheme", Set_Theme);
                    editor.putInt("Theme_id", Theme_id);
                    editor.apply();
                    break;
                default: //Default Theme Case
                    MainActivity.toolbar.setBackgroundResource(R.color.colorPrimary);
                    MainActivity.primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
                    editor.putBoolean("SetTheme", Set_Theme);
                    editor.putInt("Theme_id", Theme_id);
                    editor.apply();
                    break;
            }
        } else { // Default Theme
            MainActivity.toolbar.setBackgroundResource(R.color.colorPrimary);
            MainActivity.primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            MainActivity.toolbar.setNavigationIcon(R.drawable.ic_action_menu);
            MainActivity.toolbar.setTitle("Being Programmer");
            MainActivity.toolbar.setSubtitle("Learn.Design.Market");
        }


       //not in use ----- Extra Statement
       // if (myPager.getCurrentItem() != 1) {myPager.setCurrentItem(0);}
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning =false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (NavigationDrawerFragment.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }//This let's the toggle handle the icon button press on the ActionBar, causing the Drawer to slide out.

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //get menu id

        if (id == R.id.search_button) {         //Compare Menu item ID which is get from menu_main.xml
            Toast.makeText(this, "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

            //Intent search = new Intent(this, SubSearchActivity.class);
            //startActivity(search);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        //NavigationDrawerFragment.mDrawerLayout.closeDrawers();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (myPager.getCurrentItem() != 1) {
            TestMethod();
        } else {
            CustomDialogClass cdd = null;
            cdd = new CustomDialogClass(MainActivity.this);
            cdd.show();
        }
    }

    private void TestMethod() {
        myPager.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_pager));
        toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_pager));
        myPager.setCurrentItem(1);
        Screen.lockOrientation(this);
        toolbar.setTitle("Being Programmer");
        //MainActivity.toolbar.setScaleY((float) 1.0);
        //MainActivity.toolbar.setScaleX((float) 1.0);
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
        toolbar.getLayoutParams().height = dp;
        toolbar.getPaddingBottom();
        //closeOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*final int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,128,getResources().getDisplayMetrics());
        //Unlock Screen Orientation
        //Screen.unlockOrientation(((MainActivity)context));
        if (HomePageAdaptor.LearnMoreIsClicked) {
            switch (HomePageAdaptor.mHomePagePosition) {
                case 1:

                    MainActivity.toolbar.setTitle("Programming Quiz");
                    MainActivity.toolbar.getLayoutParams().height = dip;
                    MainActivity.myPager.setCurrentItem(0, true);

                    break;
                case 0:

                    MainActivity.toolbar.setTitle("Programming Libraries");
                    MainActivity.toolbar.getLayoutParams().height = dip;
                    MainActivity.myPager.setCurrentItem(0, true);

                    break;
                case 2:
                    MainActivity.toolbar.setTitle("Programs");
                    MainActivity.toolbar.getLayoutParams().height = dip;
                    MainActivity.myPager.setCurrentItem(0, true);

                    break;
            }
        }
        if(TabActivity.ifTabBackButtonPressed){
            myPager.setCurrentItem(0);
        }*/
    }
}