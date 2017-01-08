package pj.ess.dee.beingaprogrammer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 25-04-2015.
 */
public class WebViewNavigationDrawerFragment extends Fragment {


        public static final String PREF_FILE_NAME = "testpref";
        public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
        public static ActionBarDrawerToggle mDrawerToggle;
        public static DrawerLayout mDrawerLayout;
        private String[] titles = {"Java", "JavaScript", "C#", "HTML 5", "CSS 3", "JQuery", "HTML", "PHP", "C++"};
        private boolean mUserLearnedDrawer;
        private View container_view;
        private TextView webview_title;
        private boolean mFromSavedInstanceState;

        public WebViewNavigationDrawerFragment() {
            // Required empty public constructor
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View layout = inflater.inflate(R.layout.web_view_navigation_drawer, container, false);
            webview_title=(TextView) layout.findViewById(R.id.web_view_drawer_title);
            return layout;
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < titles.length; i++) {
            if (TutorialListAdaptor.myTutorialPosition == i)
                webview_title.setText(titles[i]);
        }
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
                @Override
                public boolean onOptionsItemSelected(MenuItem item) {
                    if (item != null && item.getItemId() == android.R.id.home) {
                        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                            mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        } else {
                            mDrawerLayout.openDrawer(Gravity.RIGHT);
                        }
                    }
                    return false;
                }
            };


            if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
                mDrawerLayout.openDrawer(container_view);
            }

            mDrawerLayout.setDrawerListener(mDrawerToggle);




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

