package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 08-04-2015.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    //public Dialog dialog;
    public TextView yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (TextView) findViewById(R.id.btn_yes);
        no = (TextView) findViewById(R.id.btn_no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_yes:
               Thread timer1 =new Thread(){
                   public void run(){
                       try {
                           sleep(100);
                       } catch (Exception e) {
                           e.printStackTrace();
                       } finally {
                           c.finish();
                       }
                   }
               };timer1.start();

                break;
            case R.id.btn_no:

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
            default:
                break;
        }
    }
}
