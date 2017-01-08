package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import pj.ess.dee.beingaprogrammer.R;


public class SplashActivity extends Activity {

public static RelativeLayout splash_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        splash_bg=(RelativeLayout) findViewById(R.id.splash_bg);
        SharedPreferences sharedPref = getSharedPreferences("ThemePref", 0);
        MainActivity.Set_Theme = sharedPref.getBoolean("SetTheme", MainActivity.Set_Theme);
        MainActivity.Theme_id = sharedPref.getInt("Theme_id", MainActivity.Theme_id);
        if (MainActivity.Set_Theme == true) {
            switch (MainActivity.Theme_id) {
                case 1:
                    SplashActivity.splash_bg.setBackgroundResource(R.color.colorTealAccent);
                    //TODO Progress Bar Custimizable
                    break;
                case 2:
                    SplashActivity.splash_bg.setBackgroundResource(R.color.colorIndegoAccent);
                    //TODO Progress Bar Custimizable
                    break;
                case 3:
                    SplashActivity.splash_bg.setBackgroundResource(R.color.colorOrangeAccent);
                    //TODO Progress Bar Custimizable
                    break;
                default:
                    SplashActivity.splash_bg.setBackgroundResource(R.color.colorAccent);
                    //TODO Progress Bar Custimizable
                    break;
            }
        }else{
            SplashActivity.splash_bg.setBackgroundResource(R.color.colorAccent);
            //TODO Progress Bar Custimizable
        }

        Thread timer = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        };
        timer.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
