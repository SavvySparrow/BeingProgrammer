package pj.ess.dee.beingaprogrammer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import pj.ess.dee.beingaprogrammer.Adaptors.TutorialListAdaptor;
import pj.ess.dee.beingaprogrammer.R;

public class QuizFetchActivity extends Activity {

    BufferedReader bReader = null;
    static JSONArray quesList = null;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        loadQuestions();
        Intent intent = new Intent(QuizFetchActivity.this, QuestionActivity.class);
        startActivity(intent);

        finish();
    }

    private void loadQuestions() {
        try {

            InputStream questions;
            if(TutorialListAdaptor.myTutorialPosition == 0)
            {
                questions = this.getBaseContext().getResources()
                        .openRawResource(R.raw.questions);
            }  else {
                questions = this.getBaseContext().getResources()
                        .openRawResource(R.raw.cquestions);
            }
            bReader = new BufferedReader(new InputStreamReader(questions));
            StringBuffer quesString = new StringBuffer();
            String aJsonLine = null;
            while ((aJsonLine = bReader.readLine()) != null) {
                quesString.append(aJsonLine);
            }

            JSONObject quesObj = new JSONObject(quesString.toString());
            quesList = quesObj.getJSONArray("Questions");

        } catch (Exception e) {
        } finally {
            try {
                bReader.close();
            } catch (Exception e) {
                Log.e("", e.getMessage().toString(), e.getCause());
            }

        }
    }

    public static JSONArray getQuesList() {
        return quesList;
    }

}