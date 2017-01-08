package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.Adaptors.HomePageAdaptor;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 10-04-2015.
 */
public class CustomShareDialog extends Dialog implements View.OnClickListener {

public Activity c;
//public Dialog dialog;
String shareID=null;
public TextView share_txt;

public CustomShareDialog (Activity a) {
        super(a);
        this.c=a;
        }

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.share_dialog);
    share_txt =(TextView) findViewById(R.id.Share_text);

    switch(HomePageAdaptor.mShareIdPosition){
        case 0:
            shareID = "\nProgramming Libraries\n\nWide Range of Programming Tutorials With best in class examples\nDownload Now! & Become a Developer\n";
            break;
        case 1:
            shareID = "\nProgramming Quiz\n\nA Wonderful collections of MCQ\nDownload Now! & Test YourSelf , Become Expert\n";
            break;
    }

    share_txt.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        try
        { Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sTit= "******** Being Programmer ********\n\n";
            i.putExtra(Intent.EXTRA_SUBJECT, "Being Programmer");
            i.putExtra(Intent.EXTRA_TITLE, "******** Being Programmer ********\n\n");

            String sAux = "\nLet me Recommend you this Wonderful Tutorial Application.Worlds First Well Designed Programming tutorial Android App!\n";
            sAux =  sAux + sTit + shareID +"\nPlay Store: http://www.PlayStore.in/details?BeingP \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            //i.putExtra(Intent.EXTRA_TEXT,"\n\nDevelopers:\nSahil Jalan & Deppak Pandey\n");
            c.startActivity(Intent.createChooser(i, "Choose One(Being Programmer"));
        }
        catch(Exception e)
        {
            e.toString();
        }
    }
}
