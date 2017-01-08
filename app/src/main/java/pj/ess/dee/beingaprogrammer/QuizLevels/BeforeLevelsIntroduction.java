package pj.ess.dee.beingaprogrammer.QuizLevels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.Fragments.TabFragment3;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by deepak on 4/18/2015.
 */
public class BeforeLevelsIntroduction extends Activity implements View.OnClickListener {

    LinearLayout start,quizMode,quizTitleBg;
    TextView title, Total, Marks,negativeView,tagLine,questionmark1,questionmark2,timerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.before_ques_levels);
        start = (LinearLayout) findViewById(R.id.Start_Quiz);
        quizMode = (LinearLayout) findViewById(R.id.quiz_mode_id);
        quizTitleBg = (LinearLayout) findViewById(R.id.quiz_title_bg_id);
        title = (TextView) findViewById(R.id.quiz_title);
        questionmark1 = (TextView) findViewById(R.id.quesmark1);
        questionmark2 = (TextView) findViewById(R.id.quesmark2);
        tagLine = (TextView) findViewById(R.id.complete_quiz_lines);
        Total = (TextView) findViewById(R.id.totalQues);
        Marks = (TextView) findViewById(R.id.totalMarks);
        timerTitle=(TextView)findViewById(R.id.quiz_timer);
        negativeView= (TextView) findViewById(R.id.neg_text);
        if (TabFragment3.selectedPosition == 1) {
            timerTitle.setText("TIMER - 15 Seconds per Question");
            title.setText("Mode-INTERMEDIATE");
            Marks.setText("15");
            Total.setText("15");
            negativeView.setText("Incorrect Answer -1");
            negativeView.setTextColor(getResources().getColor(R.color.colorRed));

        } else if (TabFragment3.selectedPosition == 2) {

            timerTitle.setText("TIMER - 10 Seconds per Question");
            title.setText("Mode-EXPERT");
            Marks.setText("25");
            Total.setText("25");
            negativeView.setText("Incorrect Answer -1");
            negativeView.setTextColor(getResources().getColor(R.color.colorRed));
        }

        start.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.Set_Theme) {
            switch (MainActivity.Theme_id) {
                case 1:
                    quizMode.setBackgroundResource(R.color.colorTealDark);
                    quizTitleBg.setBackgroundResource(R.color.colorTealDark);
                    tagLine.setTextColor(getResources().getColor(R.color.colorTealDark));
                    questionmark1.setTextColor(getResources().getColor(R.color.colorTealDark));
                    questionmark2.setTextColor(getResources().getColor(R.color.colorTealDark));
                    break;
                case 2:
                    quizMode.setBackgroundResource(R.color.colorIndegoDark);
                    quizTitleBg.setBackgroundResource(R.color.colorIndegoDark);
                    tagLine.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    questionmark1.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    questionmark2.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    break;
                case 3:
                    quizMode.setBackgroundResource(R.color.colorOrangeDark);
                    quizTitleBg.setBackgroundResource(R.color.colorOrangeDark);
                    tagLine.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    questionmark1.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    questionmark2.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    break;
                default: //Default Theme Case
                    quizMode.setBackgroundResource(R.color.colorPrimaryDark);
                    quizTitleBg.setBackgroundResource(R.color.colorPrimaryDark);
                    tagLine.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    questionmark1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    questionmark2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    break;
            }
        } else { // Default Theme
            quizMode.setBackgroundResource(R.color.colorPrimaryDark);
            quizTitleBg.setBackgroundResource(R.color.colorPrimaryDark);
            tagLine.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            questionmark1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            questionmark2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Start_Quiz:


                if (TabFragment3.selectedPosition == 0) {

                    startActivity(new Intent(BeforeLevelsIntroduction.this, QuizBeginner.class));
                    finish();
                } else if (TabFragment3.selectedPosition == 1) {

                    startActivity(new Intent(BeforeLevelsIntroduction.this, QuizIntermediate.class));
                    finish();
                } else if (TabFragment3.selectedPosition == 2) {

                    startActivity(new Intent(BeforeLevelsIntroduction.this, QuizExpert.class));
                    finish();
                }
        }
    }

}
