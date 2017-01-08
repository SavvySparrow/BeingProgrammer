package pj.ess.dee.beingaprogrammer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.Fragments.TabFragment3;
import pj.ess.dee.beingaprogrammer.QuizLevels.QuizBeginner;
import pj.ess.dee.beingaprogrammer.QuizLevels.QuizExpert;
import pj.ess.dee.beingaprogrammer.QuizLevels.QuizIntermediate;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by deepak on 4/25/2015.
 */
public class CustomDialogQuiz extends QuestionActivity implements View.OnClickListener {
    protected static TextView positive;
    protected static TextView negative;
    protected static TextView neutral;
    protected static TextView title;
    protected static TextView subtitle;
    public static int SetTitle, SetNeutralText, SetPositiveText, SetNegativeText;
    protected static String Title, SubTitle, Positive, Negative, Neutral;
    public static boolean NO = false;
    public static boolean NoTitle;
    public static boolean NoReview;
    public static boolean PlayAgain;
    private boolean clicked = false;
    QuestionActivity q;
    RelativeLayout dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_quiz);
        this.setFinishOnTouchOutside(false);
        dialog = (RelativeLayout) findViewById(R.id.dialog_quiz);
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        dialog.getLayoutParams().height =dp;
        positive = (TextView) findViewById(R.id.Positive);
        negative = (TextView) findViewById(R.id.Negative);
        subtitle = (TextView) findViewById(R.id.SubTitle);
        title = (TextView) findViewById(R.id.Title);
        neutral = (TextView) findViewById(R.id.Neutral);
        if (NoTitle) {
            Title = null;

        }
        if (NoReview) {
            neutral.setVisibility(View.GONE);
        } else {
            neutral.setVisibility(View.VISIBLE);

        }
        Working();

        positive.setOnClickListener(this);
        neutral.setOnClickListener(this);
        negative.setOnClickListener(this);


    }

    private void Working() {
        title.setText(Title);
        subtitle.setText(SubTitle);
        negative.setText(Negative);
        neutral.setText(Neutral);
        positive.setText(Positive);
           }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Positive:
                if (PlayAgain == true) {
                    Intent i = new Intent(CustomDialogQuiz.this, QuizFetchActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                finish();
                break;
            case R.id.Negative:
                finish();
                if (TabFragment3.selectedPosition == 0) {
                    Intent i = new Intent(CustomDialogQuiz.this, QuizBeginner.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else if (TabFragment3.selectedPosition == 1) {
                    Intent i = new Intent(CustomDialogQuiz.this, QuizIntermediate.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else if (TabFragment3.selectedPosition == 2) {
                    Intent i = new Intent(CustomDialogQuiz.this, QuizExpert.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

                break;
            case R.id.Neutral:
                finish();


        }

    }

    @Override
    public void finish() {
        super.finish();

    }

}

