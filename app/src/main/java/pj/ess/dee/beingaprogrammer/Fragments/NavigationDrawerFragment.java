package pj.ess.dee.beingaprogrammer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.Adaptors.NavigationDrawerAdaptor;
import pj.ess.dee.beingaprogrammer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView mRecycleView,mRecycleView1;
    public static Switch fullScreenSwitch;
    private RelativeLayout drawer_image_layout;
    private TextView title1, title2, title3, title4, subtitle;
    SharedPreferences sharedPref;

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    public static ActionBarDrawerToggle mDrawerToggle;
    public static DrawerLayout mDrawerLayout;
    private NavigationDrawerAdaptor adaptor ,adaptor1;
    private boolean mUserLearnedDrawer;
    private View container_view;
    private boolean mFromSavedInstanceState;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title1.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        //title2.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        title3.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        //title4.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        subtitle.setTextColor(getResources().getColor(R.color.offWhite));
        if (MainActivity.Set_Theme) { //MainActivity.Set_Theme can be equal to MainActivity.Set_Theme==true
            switch (MainActivity.Theme_id) {
                case 1:
                    //title1.setTextColor(getResources().getColor(R.color.colorTeal));
                    title2.setTextColor(getResources().getColor(R.color.colorTeal));
                    //title3.setTextColor(getResources().getColor(R.color.colorTeal));
                    title4.setTextColor(getResources().getColor(R.color.colorTeal));
                    //subtitle.setTextColor(getResources().getColor(R.color.colorTealAccent));
                    drawer_image_layout.setBackgroundColor(getResources().getColor(R.color.colorTealAccent));

                    break;
                case 2:
                    //title1.setTextColor(getResources().getColor(R.color.colorIndego));
                    title2.setTextColor(getResources().getColor(R.color.colorIndego));
                    //title3.setTextColor(getResources().getColor(R.color.colorIndego));
                    title4.setTextColor(getResources().getColor(R.color.colorIndego));
                    //subtitle.setTextColor(getResources().getColor(R.color.colorIndegoAccent));
                    drawer_image_layout.setBackgroundColor(getResources().getColor(R.color.colorIndegoAccent));
                    break;
                case 3:
                    //title1.setTextColor(getResources().getColor(R.color.colorIndego));
                    title2.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                    //title3.setTextColor(getResources().getColor(R.color.colorIndego));
                    title4.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                    //subtitle.setTextColor(getResources().getColor(R.color.colorIndegoAccent));
                    drawer_image_layout.setBackgroundColor(getResources().getColor(R.color.colorOrangeAccent));
                    break;
                default: //Default Theme Case
                    //title1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    title2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    //title3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    title4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    //subtitle.setTextColor(getResources().getColor(R.color.colorAccent));
                    drawer_image_layout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    break;
            }

        } else {
            //Default Theme
            //title1.setTextColor(getResources().getColor(R.color.colorPrimary));
            title2.setTextColor(getResources().getColor(R.color.colorPrimary));
            //title3.setTextColor(getResources().getColor(R.color.colorPrimary));
            title4.setTextColor(getResources().getColor(R.color.colorPrimary));
            //subtitle.setTextColor(getResources().getColor(R.color.colorAccent));
            drawer_image_layout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        title1 = (TextView) layout.findViewById(R.id.Drawer_title1);
        title2 = (TextView) layout.findViewById(R.id.Drawer_title2);
        title3 = (TextView) layout.findViewById(R.id.Drawer_title3);
        title4 = (TextView) layout.findViewById(R.id.Drawer_title4);
        subtitle = (TextView) layout.findViewById(R.id.Drawer_subtitle);
        fullScreenSwitch = (Switch) layout.findViewById(R.id.fullscreen_switch);
        sharedPref = getActivity().getSharedPreferences("ThemePref", 0);
        final SharedPreferences.Editor editor = sharedPref.edit();
        //fullScreenSwitch.setChecked(MainActivity.isfullscreen);
        fullScreenSwitch.setChecked(sharedPref.getBoolean("FullScreen", MainActivity.isfullscreen));
        fullScreenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.isfullscreen = true;
                    editor.putBoolean("FullScreen", MainActivity.isfullscreen);
                    editor.apply();
                    // if (TabActivity.isRunning){
                    Intent fullscreenIntent = new Intent(getActivity(), MainActivity.class);
                    fullscreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(fullscreenIntent);
                    /*}else{
                        Intent fullscreenIntent1= new Intent(getActivity(),MainActivity.class);
                        fullscreenIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(fullscreenIntent1);
                    }*/

                } else {
                    MainActivity.isfullscreen = false;
                    editor.putBoolean("FullScreen", MainActivity.isfullscreen);
                    editor.apply();
                    /*if (TabActivity.isRunning){
                        Intent fullscreenIntent= new Intent(getActivity(),TabActivity.class);
                        fullscreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(fullscreenIntent);
                    }else{*/
                    Intent fullscreenIntent1 = new Intent(getActivity(), MainActivity.class);
                    fullscreenIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(fullscreenIntent1);
                    //}
                }
            }
        });
        drawer_image_layout = (RelativeLayout) layout.findViewById(R.id.drawer_container);
        mRecycleView = (RecyclerView) layout.findViewById(R.id.drawer_list);
        //mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //mRecycleView.setItemAnimator(new DefaultItemAnimator());
        adaptor = new NavigationDrawerAdaptor(getActivity(), getData());
        mRecycleView.setAdapter(adaptor);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    //getting Data from Strings in Recycler View
    public static List<NavigationDrawerAdaptor.DrawerInformation> getData() {
        List<NavigationDrawerAdaptor.DrawerInformation> data = new ArrayList<>();


        int[] icons1 = {R.drawable.ic_home_teal, R.drawable.ic_format_paint_teal, R.drawable.ic_settings_teal,
                R.drawable.ic_message_teal, R.drawable.ic_info_teal};

        int[] icons2 = {R.drawable.ic_home_indego, R.drawable.ic_format_paint_indego, R.drawable.ic_settings_indego,
                R.drawable.ic_message_indego, R.drawable.ic_info_indego};

        int[] icons3 = {R.drawable.ic_home_orange, R.drawable.ic_format_paint_orange, R.drawable.ic_settings_orange,
                R.drawable.ic_message_orange, R.drawable.ic_info_orange};


        int[] icons_def = {R.drawable.ic_home_primary, R.drawable.ic_format_paint_primary, R.drawable.ic_settings_primary,
                R.drawable.ic_message_primary, R.drawable.ic_info_primary};


        String[] titles = {"Home", "Choose Theme", "Settings",
                "Help & Feedback", "About Us"};

       // String[] SubTitles = {"Go back to home", "Customize Your Appearance and Change the look the way you want", "Control different activities and customize tutorial libraries",
        //        "Send Bug Report or give us your feedback", "Who's Developer? Wanna Know!"};


        for (int i = 0; i < icons1.length && i < titles.length; i++) {
            NavigationDrawerAdaptor.DrawerInformation current = new NavigationDrawerAdaptor.DrawerInformation();

            current.iconId_def = icons_def[i];
            current.iconId1 = icons1[i];
            current.iconId2 = icons2[i];
            current.iconId3 = icons3[i];
            current.textId = titles[i];
            //current.SubTextId = SubTitles[i];
            data.add(current);
        }
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        container_view = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.Drawer_Open, R.string.Drawer_Close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }

                //toolbar.setTitle("Navigation Drawer");
                //IT WILL RE DRAW THE ACTION BAR/TOOLBAR AGAIN WHEN IT OPENED/CLOSED
                getActivity().invalidateOptionsMenu();

                saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //IT WILL RE DRAW THE ACTION BAR/TOOLBAR AGAIN WHEN IT OPENED/CLOSED
                getActivity().invalidateOptionsMenu();


            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(container_view);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //navigation button
        //mDrawerLayout.post(new Runnable() {
        //  @Override
        //  public void run() {
        //
        //  mDrawerToggle.syncState();
        //}
        //});


    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


}

