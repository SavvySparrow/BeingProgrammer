package pj.ess.dee.beingaprogrammer.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pj.ess.dee.beingaprogrammer.R;


public class HelpAndFeedbackActivity extends ActionBarActivity implements View.OnClickListener {

    EditText nameET, feedbackET;
    public TextView title1,title2,title3,title4;
    TextView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.help_and_feedback_activity);

        title1 = (TextView) findViewById(R.id.Feedback_title1);
        title2 = (TextView) findViewById(R.id.Feedback_title2);
        title3 = (TextView) findViewById(R.id.Feedback_title3);
        title4 = (TextView) findViewById(R.id.Feedback_title4);
        nameET = (EditText) findViewById(R.id.name);
        feedbackET = (EditText) findViewById(R.id.feedback);
        send = (TextView) findViewById(R.id.send);
        send.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.Set_Theme==true){
            switch (MainActivity.Theme_id){
                case 1:
                    title1.setTextColor(getResources().getColor(R.color.colorTeal));
                    //title1.getResources().getColor(R.color.colorTeal);
                    title2.setTextColor(getResources().getColor(R.color.colorTeal));
                    title3.setTextColor(getResources().getColor(R.color.colorTeal));
                    title4.setTextColor(getResources().getColor(R.color.colorTeal));
                    nameET.setHintTextColor(getResources().getColor(R.color.colorTealAccent));
                    feedbackET.setHintTextColor(getResources().getColor(R.color.colorTealAccent));
                    send.setBackgroundResource(R.drawable.feedback_dw1);
                    break;
                case 2:
                    title1.setTextColor(getResources().getColor(R.color.colorIndego));
                    //title1.getResources().getColor(R.color.colorIndego);
                    title2.setTextColor(getResources().getColor(R.color.colorIndego));
                    title3.setTextColor(getResources().getColor(R.color.colorIndego));
                    title4.setTextColor(getResources().getColor(R.color.colorIndego));
                    nameET.setHintTextColor(getResources().getColor(R.color.colorIndegoAccent));
                    feedbackET.setHintTextColor(getResources().getColor(R.color.colorIndegoAccent));
                    send.setBackgroundResource(R.drawable.feedback_dw2);
                    break;
                case 3:
                    title1.setTextColor(getResources().getColor(R.color.colorOrange));
                    //title1.getResources().getColor(R.color.colorIndego);
                    title2.setTextColor(getResources().getColor(R.color.colorOrange));
                    title3.setTextColor(getResources().getColor(R.color.colorOrange));
                    title4.setTextColor(getResources().getColor(R.color.colorOrange));
                    nameET.setHintTextColor(getResources().getColor(R.color.colorOrangeAccent));
                    feedbackET.setHintTextColor(getResources().getColor(R.color.colorOrangeAccent));
                    send.setBackgroundResource(R.drawable.feedback_dw3);
                    break;
                default: //Default Theme Case
                    title1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    //title1.getResources().getColor(R.color.colorTeal);
                    title2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    title3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    title4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    nameET.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    feedbackET.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    send.setBackgroundResource(R.drawable.feedback_dw_def);
                    break;
            }

        }else{//Default Theme
            title1.setTextColor(getResources().getColor(R.color.colorPrimary));
            //title1.getResources().getColor(R.color.colorTeal);
            title2.setTextColor(getResources().getColor(R.color.colorPrimary));
            title3.setTextColor(getResources().getColor(R.color.colorPrimary));
            title4.setTextColor(getResources().getColor(R.color.colorPrimary));
            nameET.setHintTextColor(getResources().getColor(R.color.colorAccent));
            feedbackET.setHintTextColor(getResources().getColor(R.color.colorAccent));
            send.setBackgroundResource(R.drawable.feedback_dw_def);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share_button) {
            return true;
        }
        // if (id == android.R.id.home) {

        //NavUtils.navigateUpFromSameTask(this);
        // startActivity(new Intent(this,MainActivity.class));
        // }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Thread timer1 =new Thread(){
            public void run(){
                try {
                    sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sendMail();
                }
            }
        };timer1.start();


    }

    private void sendMail() {
        String name = new String(nameET.getText().toString());
        String feedback = new String(feedbackET.getText().toString());
        //String email = new String("deepak.pandey007@yahoo.com");

        Intent FeedbackMail = new Intent(android.content.Intent.ACTION_SEND, Uri.parse("mailto:"));
        FeedbackMail.setType("message/rfc822");
        FeedbackMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@SD_Developers.com"});
        FeedbackMail.putExtra(Intent.EXTRA_SUBJECT, "<Being Developers>Feedback");
        //FeedbackMail.putExtra(Intent.EXTRA_TEXT, "<" + name + ">" + "Being Developers\n");
        FeedbackMail.putExtra(Intent.EXTRA_TEXT, "<" + name + ">" + "Being Developers\n"+"\n"+feedback);

        try {
            startActivity(FeedbackMail);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HelpAndFeedbackActivity.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

