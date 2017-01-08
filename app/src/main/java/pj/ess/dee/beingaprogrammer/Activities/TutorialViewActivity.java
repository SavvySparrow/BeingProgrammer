package pj.ess.dee.beingaprogrammer.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.Adaptors.TutorialWebViewRecyclerAdapter;
import pj.ess.dee.beingaprogrammer.Fragments.TabFragment1;
import pj.ess.dee.beingaprogrammer.Fragments.WebViewNavigationDrawerFragment;
import pj.ess.dee.beingaprogrammer.R;
import pj.ess.dee.beingaprogrammer.Toolbars.InitializeDefaultStatus_ToolBarFragment;

/**
 * Created by Sahil Jalan on 4/24/2015.
 */
public class TutorialViewActivity extends ActionBarActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    private TextView textView;
    private int progressStatus = 0;
    private Handler mHandler = new Handler();
    private RelativeLayout primaryDark, progresslayout;
    private RecyclerView mRecycleView;
    private TutorialWebViewRecyclerAdapter adapter;
    private String[] titles = {"Java", "JavaScript", "C#", "HTML 5", "CSS 3", "JQuery", "HTML", "PHP", "C++"};
    private String[] loadUrl = null;
    private int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("ThemePref", 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (sharedPref.getBoolean("FullScreen", MainActivity.isfullscreen)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //editor.putBoolean("FullScreen",MainActivity.isfullscreen);
            //editor.apply();
        }
        setContentView(R.layout.tutorial_view_recycler_activity);

        initializeDefaultStatusBar_ToolBarFragment();


        //mRecycleView.setVisibility(View.VISIBLE);
        //progresslayout.setVisibility(View.GONE);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        adapter = new TutorialWebViewRecyclerAdapter(TutorialViewActivity.this, getData());
        mRecycleView = (RecyclerView) findViewById(R.id.tutorial_view_recycler_view_list_id);
        mRecycleView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(TutorialViewActivity.this);
        mRecycleView.setLayoutManager(llm);
        progresslayout = (RelativeLayout) findViewById(R.id.progress_bar_layout_id);
        // textView = (TextView) findViewById(R.id.textView1);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progresslayout.setVisibility(View.GONE);
            }
        }, 900);
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
        WebViewNavigationDrawerFragment drawerFragment = (WebViewNavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.tutorial_web_view_navigation_drawer);
        drawerFragment.setUp(R.id.tutorial_web_view_navigation_drawer, (DrawerLayout) findViewById(R.id.web_view_drawer_layout), toolbar);
    }


    @Override
    protected void onStart() {
        super.onStart();
        toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        primaryDark = (RelativeLayout) findViewById(R.id.primary_dark);
        if (MainActivity.Set_Theme) {
            switch (MainActivity.Theme_id) {
                case 1:
                    toolbar.setBackgroundResource(R.color.colorTeal);
                    primaryDark.setBackgroundResource(R.color.colorTealDark);
                    break;
                case 2:
                    toolbar.setBackgroundResource(R.color.colorIndego);
                    primaryDark.setBackgroundResource(R.color.colorIndegoDark);
                    break;
                case 3:
                    toolbar.setBackgroundResource(R.color.colorOrange);
                    primaryDark.setBackgroundResource(R.color.colorOrangeDark);
                    break;
                default: //Default Theme Case
                    toolbar.setBackgroundResource(R.color.colorPrimary);
                    primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
                    break;
            }
        } else { // Default Theme
            toolbar.setBackgroundResource(R.color.colorPrimary);
            primaryDark.setBackgroundResource(R.color.colorPrimaryDark);
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            for (int i = 0; i < titles.length; i++) {
                if (TutorialListAdaptor.myTutorialPosition == i)
                    toolbar.setTitle(titles[i]);
            }
            toolbar.setSubtitle("Learn.Do.Share");
            toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (WebViewNavigationDrawerFragment.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }//This let's the toggle handle the icon button press on the ActionBar, causing the Drawer to slide out.

        return super.onOptionsItemSelected(item);
    }

    //getting Data from Strings in Recycler View
    public List<TutorialWebViewRecyclerAdapter.TutorialWebViewInformation> getData() {
        List<TutorialWebViewRecyclerAdapter.TutorialWebViewInformation> data = new ArrayList<>();
        TutorialWebViewRecyclerAdapter.TutorialWebViewInformation current;
        switch (TutorialListAdaptor.myTutorialPosition) {
            case 0: //Java Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/java basics/topic1.html", "file:///android_asset/java basics/topic2.html",
                            "file:///android_asset/java basics/topic3.html", "file:///android_asset/java basics/topic4.html",
                            "file:///android_asset/java basics/pitfall_question1.html", "file:///android_asset/java basics/Type conversion.html"};
                    length = loadUrl.length;
                } else {
                    loadUrl = new String[]{"file:///android_asset/java oop/oopjava1.htm", "file:///android_asset/java oop/oopjava2.htm",
                            "file:///android_asset/java oop/oopjava3.htm", "file:///android_asset/java oop/oopjava4.htm",
                            "file:///android_asset/java oop/oopjava5.htm", "file:///android_asset/java oop/oopjava6.htm",
                            "file:///android_asset/java oop/oopjava7.htm", "file:///android_asset/java oop/oopjava8.htm"};
                    length = loadUrl.length;

                }

                break;

            case 1: //Java Script Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/javascript/intro1.html", "file:///android_asset/javascript/intro2.html",
                            "file:///android_asset/javascript/intro3.html", "file:///android_asset/javascript/statements.html",
                            "file:///android_asset/javascript/statements_internal.html", "file:///android_asset/javascript/External_JavaScript.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/javascript/statements.html",
                            "file:///android_asset/javascript/External_JavaScript.html"};
                    length = loadUrl.length;

                }
                break;

            case 2:  // C++ Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;
            case 3: //HTML 5 Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;

            case 4: //CSS 3 Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;

            case 5: //Jquery Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;

            case 6: //HTML Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;

            case 7: //PHP Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                }
                break;

            case 8: //C# Tutorial
                if (TabFragment1.ListPosition == 0) {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;

                } else {
                    loadUrl = new String[]{"file:///android_asset/Comming Soon.html"};
                    length = loadUrl.length;
                }
                break;
        }
        for (int i = 0; i < length; i++) {
            current = new TutorialWebViewRecyclerAdapter.TutorialWebViewInformation();
            current.LoadUrl = loadUrl[i];
            data.add(current);
        }
        return data;
    }


}
