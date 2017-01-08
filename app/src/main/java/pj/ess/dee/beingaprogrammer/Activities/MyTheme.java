package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import pj.ess.dee.beingaprogrammer.Adaptors.ThemePagerAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 10-03-2015.
 */

public class MyTheme extends ActionBarActivity {

    public static ViewPager themePager;
    Dialog my_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_theme);

        themePager = (ViewPager) findViewById(R.id.theme_pager);
        themePager.setAdapter(new ThemePagerAdaptor(getSupportFragmentManager()));
        themePager.setCurrentItem(0);

        //MainActivity.primaryDark.setBackgroundResource(R.color.colorGreen);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(this,MainActivity.class));
        //finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

