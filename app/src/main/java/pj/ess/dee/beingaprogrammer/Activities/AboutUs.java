package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.R;


public class AboutUs extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    //public Dialog dialog;
    public TextView ok,Dev,copyright_thanks_txt,title1,title2,title3,title4,subtitle;

    public AboutUs(Activity a) {
        super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_us);
        ok = (TextView) findViewById(R.id.btn_dismiss);
        Dev = (TextView) findViewById(R.id.btn_thanks);
        title1 = (TextView) findViewById(R.id.About_title1);
        title2 = (TextView) findViewById(R.id.About_title2);
        title3 = (TextView) findViewById(R.id.About_title3);
        title4 = (TextView) findViewById(R.id.About_title4);
        subtitle = (TextView) findViewById(R.id.About_Subtitle);
        copyright_thanks_txt = (TextView) findViewById(R.id.copyright__Dev_TXT);
        if(MainActivity.Set_Theme==true){
            switch (MainActivity.Theme_id){
                case 1:
                    title1.setTextColor(c.getResources().getColor(R.color.colorTeal));
                    //title1.getResources().getColor(R.color.colorTeal);
                    title2.setTextColor(c.getResources().getColor(R.color.colorTeal));
                    title3.setTextColor(c.getResources().getColor(R.color.colorTeal));
                    title4.setTextColor(c.getResources().getColor(R.color.colorTeal));
                    subtitle.setTextColor(c.getResources().getColor(R.color.colorTealAccent));
                    break;
                case 2:
                    title1.setTextColor(c.getResources().getColor(R.color.colorIndego));
                    //title1.getResources().getColor(R.color.colorIndego);
                    title2.setTextColor(c.getResources().getColor(R.color.colorIndego));
                    title3.setTextColor(c.getResources().getColor(R.color.colorIndego));
                    title4.setTextColor(c.getResources().getColor(R.color.colorIndego));
                    subtitle.setTextColor(c.getResources().getColor(R.color.colorIndegoAccent));
                    break;
                case 3:
                    title1.setTextColor(c.getResources().getColor(R.color.colorOrange));
                    //title1.getResources().getColor(R.color.colorIndego);
                    title2.setTextColor(c.getResources().getColor(R.color.colorOrange));
                    title3.setTextColor(c.getResources().getColor(R.color.colorOrange));
                    title4.setTextColor(c.getResources().getColor(R.color.colorOrange));
                    subtitle.setTextColor(c.getResources().getColor(R.color.colorOrangeAccent));
                    break;
                default: //Default Theme Case
                    title1.setTextColor(c.getResources().getColor(R.color.colorPrimary));
                    //title1.getResources().getColor(R.color.colorTeal);
                    title2.setTextColor(c.getResources().getColor(R.color.colorPrimary));
                    title3.setTextColor(c.getResources().getColor(R.color.colorPrimary));
                    title4.setTextColor(c.getResources().getColor(R.color.colorPrimary));
                    subtitle.setTextColor(c.getResources().getColor(R.color.colorAccent));
                    break;
            }

        }else{
            //Default Theme
            title1.setTextColor(c.getResources().getColor(R.color.colorPrimary));
            //title1.getResources().getColor(R.color.colorTeal);
            title2.setTextColor(c.getResources().getColor(R.color.colorPrimary));
            title3.setTextColor(c.getResources().getColor(R.color.colorPrimary));
            title4.setTextColor(c.getResources().getColor(R.color.colorPrimary));
            subtitle.setTextColor(c.getResources().getColor(R.color.colorAccent));
        }
            Dev.setText("Licence");
            copyright_thanks_txt.setText(R.string.Special_thanks);
        //TODO Custimize Text In About Us

        ok.setOnClickListener(this);
        Dev.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_thanks:
                //copyright_thanks_txt.setText(R.string.Licence);
                if(Dev.getText().toString()!="Licence"){
                    Dev.setText("Licence");
                    copyright_thanks_txt.setText(R.string.Special_thanks);
                }
                else{
                    Dev.setText("Special Thanks");
                    copyright_thanks_txt.setText(R.string.Licence);
                }
                break;
            case R.id.btn_dismiss:
                Thread timer2 =new Thread(){
                    public void run(){
                        try {
                            sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            dismiss();
                        }
                    }
                };timer2.start();
                break;
        }

    }
}
