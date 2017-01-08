package pj.ess.dee.beingaprogrammer.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

import pj.ess.dee.beingaprogrammer.R;

import static android.util.Log.e;


public class QuestionActivity extends ActionBarActivity implements OnClickListener {
    public static int Start;
    public static int End;
    public static int NegativeMarking = 0;
    /**
     * Called when the activity is first created.
     */
    TextView question = null;
    LinearLayout answer1 = null;
    LinearLayout answer2 = null;
    LinearLayout answer3 = null;
    LinearLayout answer4 = null;
    LinearLayout Quiz_Question_Bg;
    LinearLayout nextLinear = null;
    int tick = 0;
    TextView finish, next, back;
    LinearLayout answers = null;
    RippleView rippleView1, rippleView2, rippleView3, rippleView4;
    int score = 0;
    int quesIndex = 0;
    int selected[] = null;
    int correctAns[] = null;
    Random r = new Random();
    int min = Start, max = End;
    int random = 0;
    private int QuesNumber;
    private int TotalQuestions;
    private TextView answer1tv, answer2tv, answer3tv, answer4tv;
    private TextView title, answerA, answerB, answerC, answerD;
    private int review = 0, i;
    private int[] randomArray = new int[max + 1];
    private int qTemp;
    private int b = 0;
    private int quit = 0;
    private String comment;
    private boolean found;
    private int correct = 0;
    private int wrong = 0;
    private int notAttempted = 0;
    private int progressStatus = max_timer;
    private Handler mHandler = new Handler();
    private RelativeLayout timer_progresslayout;
    private TextView timer_textView;
    private ProgressBar progressBar;
    Thread timer;
    public static int max_timer;
    private boolean isRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.question);

        try {
            i = 0;
            question = (TextView) findViewById(R.id.question);
            title = (TextView) findViewById(R.id.QuesTitle);

            Quiz_Question_Bg = (LinearLayout) findViewById(R.id.quiz_question_id);
            answer1 = (LinearLayout) findViewById(R.id.a0);
            answer2 = (LinearLayout) findViewById(R.id.a1);
            answer3 = (LinearLayout) findViewById(R.id.a2);
            answer4 = (LinearLayout) findViewById(R.id.a3);

            answerA = (TextView) findViewById(R.id.ans_a);
            answerB = (TextView) findViewById(R.id.ans_b);
            answerC = (TextView) findViewById(R.id.ans_c);
            answerD = (TextView) findViewById(R.id.ans_d);

            answer1tv = (TextView) findViewById(R.id.a0tv);
            answer2tv = (TextView) findViewById(R.id.a1tv);
            answer3tv = (TextView) findViewById(R.id.a2tv);
            answer4tv = (TextView) findViewById(R.id.a3tv);

            rippleView1 = (RippleView) findViewById(R.id.ripple_id1);
            rippleView2 = (RippleView) findViewById(R.id.ripple_id2);
            rippleView3 = (RippleView) findViewById(R.id.ripple_id3);
            rippleView4 = (RippleView) findViewById(R.id.ripple_id4);

            answers = (LinearLayout) findViewById(R.id.answers);

            finish = (TextView) findViewById(R.id.finishLL);
            finish.setOnClickListener(this);

            timer_progresslayout = (RelativeLayout) findViewById(R.id.timer_layout_id);
            timer_textView = (TextView) findViewById(R.id.timer_textView);
            progressBar = (ProgressBar) findViewById(R.id.timer_progressBar);
            if (max_timer == 0)
                timer_progresslayout.setVisibility(View.GONE);

            else {
                progressStatus=max_timer+1;
                progressBar.setMax(max_timer);
                progressBar.setProgress(progressStatus);
                isRunning = true;
                TimerStart();
            }
            back = (TextView) findViewById(R.id.BackLL);
            back.setOnClickListener(this);
            back.setClickable(false);
            back.setTextColor(getResources().getColor(R.color.colorGrey));
            back.setAlpha((float) 0.8);

            next = (TextView) findViewById(R.id.nextLL);
            next.setOnClickListener(this);

            answer1.setOnClickListener(this);
            answer2.setOnClickListener(this);
            answer3.setOnClickListener(this);
            answer4.setOnClickListener(this);

            selected = new int[QuizFetchActivity.getQuesList().length()];
            java.util.Arrays.fill(selected, -1);
            correctAns = new int[QuizFetchActivity.getQuesList().length()];
            java.util.Arrays.fill(correctAns, -1);

            quesIndex = Start;

            setQuesNum();

            showQuestion(quesIndex);

        } catch (Exception e) {
            e("", e.getMessage().toString(), e.getCause());
        }

    }

    private void TimerStart() {
        //progressStatus=0;

        timer = new Thread(new Runnable() {

            public void run() {

                //mRecycleView.setVisibility(View.GONE);
                while (progressStatus > -1) {
                    if (isRunning) {

                        progressStatus -= 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        mHandler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                timer_textView.setText(progressStatus + "s");
                                if (progressStatus==-1) {
                                    NextClicked();
                                }
                                //timer_progresslayout.setVisibility(View.GONE);
                            }
                        });

                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        timer.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DefaultTheme();


    }

    private void DefaultTheme() {
        if (MainActivity.Set_Theme) {
            switch (MainActivity.Theme_id) {
                case 1:
                    Quiz_Question_Bg.setBackgroundResource(R.color.colorTeal);
                    title.setBackgroundResource(R.color.colorTealDark);
                    back.setBackgroundResource(R.color.colorTeal);
                    next.setBackgroundResource(R.color.colorTeal);
                    finish.setBackgroundResource(R.color.colorTeal);
                    answer1tv.setTextColor(getResources().getColor(R.color.colorTeal));
                    answer2tv.setTextColor(getResources().getColor(R.color.colorTeal));
                    answer3tv.setTextColor(getResources().getColor(R.color.colorTeal));
                    answer4tv.setTextColor(getResources().getColor(R.color.colorTeal));
                    answerA.setTextColor(getResources().getColor(R.color.colorTealDark));
                    answerB.setTextColor(getResources().getColor(R.color.colorTealDark));
                    answerC.setTextColor(getResources().getColor(R.color.colorTealDark));
                    answerD.setTextColor(getResources().getColor(R.color.colorTealDark));
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.dw_timer_teal));
                    timer_textView.setTextColor(getResources().getColor(R.color.colorTealAccent));
                    break;
                case 2:
                    Quiz_Question_Bg.setBackgroundResource(R.color.colorIndego);
                    title.setBackgroundResource(R.color.colorIndegoDark);
                    back.setBackgroundResource(R.color.colorIndego);
                    next.setBackgroundResource(R.color.colorIndego);
                    finish.setBackgroundResource(R.color.colorIndego);
                    answer1tv.setTextColor(getResources().getColor(R.color.colorIndego));
                    answer2tv.setTextColor(getResources().getColor(R.color.colorIndego));
                    answer3tv.setTextColor(getResources().getColor(R.color.colorIndego));
                    answer4tv.setTextColor(getResources().getColor(R.color.colorIndego));
                    answerA.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    answerB.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    answerC.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    answerD.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.dw_timer_indego));
                    timer_textView.setTextColor(getResources().getColor(R.color.colorIndegoAccent));
                    break;
                case 3:
                    Quiz_Question_Bg.setBackgroundResource(R.color.colorOrange);
                    title.setBackgroundResource(R.color.colorOrangeDark);
                    back.setBackgroundResource(R.color.colorOrange);
                    next.setBackgroundResource(R.color.colorOrange);
                    finish.setBackgroundResource(R.color.colorOrange);
                    answer1tv.setTextColor(getResources().getColor(R.color.colorOrange));
                    answer2tv.setTextColor(getResources().getColor(R.color.colorOrange));
                    answer3tv.setTextColor(getResources().getColor(R.color.colorOrange));
                    answer4tv.setTextColor(getResources().getColor(R.color.colorOrange));
                    answerA.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    answerB.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    answerC.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    answerD.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.dw_timer_orange));
                    timer_textView.setTextColor(getResources().getColor(R.color.colorOrangeAccent));
                    break;
                default: //Default Theme Case
                    Quiz_Question_Bg.setBackgroundResource(R.color.colorPrimary);
                    title.setBackgroundResource(R.color.colorPrimaryDark);
                    back.setBackgroundResource(R.color.colorPrimary);
                    next.setBackgroundResource(R.color.colorPrimary);
                    finish.setBackgroundResource(R.color.colorPrimary);
                    answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    answerA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    answerB.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    answerC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    answerD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.dw_timer_primary));
                    timer_textView.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
            }
        } else { // Default Theme
            Quiz_Question_Bg.setBackgroundResource(R.color.colorPrimary);
            title.setBackgroundResource(R.color.colorPrimaryDark);
            back.setBackgroundResource(R.color.colorPrimary);
            next.setBackgroundResource(R.color.colorPrimary);
            finish.setBackgroundResource(R.color.colorPrimary);
            answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answerA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            answerB.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            answerC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            answerD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.dw_timer_primary));
            timer_textView.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    private void setQuesNum() {
        if (Start == 0 && End == 84) {
            QuesNumber = 1;
            TotalQuestions = 25;
        } else if (Start == 0 && End == 69) {
            QuesNumber = 1;
            TotalQuestions = 15;
        } else {
            QuesNumber = 1;
            TotalQuestions = 10;
        }
        random = 1;
        random();

    }


    private void showQuestion(int qIndex) {
        try {
            if (QuesNumber == 1) {
                back.setClickable(false);
                back.setTextColor(getResources().getColor(R.color.colorGrey));
                back.setAlpha((float) 0.8);
            } else if (QuesNumber != 1 && review == 1) {
                back.setClickable(true);
                back.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                back.setAlpha(1);
            }


            if (review == 0)
                title.setText(" " + QuesNumber + "/" + TotalQuestions);
            else
                title.setText(" " + QuesNumber + "/" + TotalQuestions + "                           Review");

            next.setClickable(true);
            score = 0;
            correct = 0;
            wrong = 0;
            JSONObject aQues = QuizFetchActivity.getQuesList().getJSONObject(qIndex);
            String quesValue = aQues.getString("Question");

            if (correctAns[qIndex] == -1) {
                String correctAnsStr = aQues.getString("CorrectAnswer");
                correctAns[qIndex] = Integer.parseInt(correctAnsStr);
            }
            String quesWithNum = new String(" " + quesValue).replaceAll("    ", "");
            question.setText(quesWithNum.toCharArray(), 0, quesWithNum.length());

            JSONArray ansList = aQues.getJSONArray("Answers");

            String aAns = ansList.getJSONObject(0).getString("Answer").replaceAll("  ", "");

            answer1tv.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(1).getString("Answer").replaceAll("  ", "");
            answer2tv.setText(aAns.toCharArray(), 0, aAns.length());

            aAns = ansList.getJSONObject(2).getString("Answer").replaceAll("  ", "");
            answer3tv.setText(aAns.toCharArray(), 0, aAns.length());
            aAns = ansList.getJSONObject(3).getString("Answer").replaceAll("  ", "");
            answer4tv.setText(aAns.toCharArray(), 0, aAns.length());


            if (review == 1) {
                timer_progresslayout.setVisibility(View.GONE);
                review();
            }


        } catch (Exception e) {
            e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.a0:
                Option1Clicked();
                break;

            case R.id.a1:
                Option2Clicked();
                break;

            case R.id.a2:
                Option3Clicked();
                break;

            case R.id.a3:
                Option4Clicked();
                break;


            case R.id.nextLL:
                NextClicked();
                break;

            case R.id.BackLL:
                BackClicked();
                break;


            case R.id.finishLL:
                Submit();
                break;

        }


    }

    private void Submit() {
        score = 0;
        for (int i = 0; i < correctAns.length; i++) {

            if ((correctAns[i] != -1) && (correctAns[i] == selected[i])) {
                score = score + 1;
                correct = correct + 1;
            }
            if ((selected[i] != -1) && (correctAns[i] != selected[i])) {
                if (NegativeMarking == 1)
                    score = score - 1;
                wrong = wrong + 1;
            }


            Log.d("tag11", " " + score + " " + correct + " " + wrong);
        }
        notAttempted = TotalQuestions - (correct + wrong);
        AlertBoxOpen();

    }

    private void BackClicked() {
        if (selected[quesIndex] != 0 || selected[quesIndex] != 1 || selected[quesIndex] != 2 || selected[quesIndex] != 3) {
            DefaultTheme();
        }
        if (QuesNumber <= TotalQuestions) {
            //answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answer4.setBackgroundResource(R.drawable.options_shape);
            answer2.setBackgroundResource(R.drawable.options_shape);
            answer3.setBackgroundResource(R.drawable.options_shape);
            answer1.setBackgroundResource(R.drawable.options_shape);
        }

        b = 1;
        if (review == 1) {
            if (QuesNumber > 1 && i > 0) {
                QuesNumber = QuesNumber - 1;
                i--;
                quesIndex = randomArray[QuesNumber - 1];
                next.setAlpha(1);
                next.setClickable(false);
                showQuestion(quesIndex);
            }
        }

    }


    private void NextClicked() {
        if (review != 1) {
            progressStatus = max_timer;
            progressBar.setProgress(progressStatus);
            timer_textView.setText(progressStatus + "s");
        }
        if (selected[quesIndex] != 0 || selected[quesIndex] != 1 || selected[quesIndex] != 2 || selected[quesIndex] != 3) {
            DefaultTheme();
        }
        int temp = selected[quesIndex];


        if (QuesNumber != TotalQuestions) {
            //answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            //answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            answer4.setBackgroundResource(R.drawable.options_shape);
            answer2.setBackgroundResource(R.drawable.options_shape);
            answer3.setBackgroundResource(R.drawable.options_shape);
            answer1.setBackgroundResource(R.drawable.options_shape);
        }


        qTemp = quesIndex;

        if (i <= max && review != 1)
            random();

        if (QuesNumber >= TotalQuestions && review != 1) {
            isRunning = false;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Submit();
                }
            },0);

            quesIndex = qTemp;
            selected[quesIndex] = temp;
            QuesNumber--;
            i--;


            finish.setText("Submit");
            next.setAlpha((float) 0.8);
            next.setClickable(false);


        } else if (QuesNumber >= TotalQuestions && review == 1) {

            selected[quesIndex] = temp;
            QuesNumber--;
            i--;
            quesIndex = randomArray[QuesNumber];
            next.setAlpha((float) 0.8);
        }
        QuesNumber++;
        Log.d("tag1", "ques" + QuesNumber + " " + i);
        showQuestion(quesIndex);


    }


    private void Option1Clicked() {
        selected[quesIndex] = 0;
        if (tick == 0) {
            answer1.setBackgroundResource(R.drawable.onselectoptions);
            //defaultColorScheme1();
            tick = 1;
        } else {

            answer1.setBackgroundResource(R.drawable.onselectoptions);
            //answer1tv.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            //answerA.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            answer2.setBackgroundResource(R.drawable.options_shape);
            answer3.setBackgroundResource(R.drawable.options_shape);
            answer4.setBackgroundResource(R.drawable.options_shape);
        }
        if (review == 1) {
            if (correctAns[quesIndex] == 0) {
                Toast toast = Toast.makeText(QuestionActivity.this, "Correct Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(QuestionActivity.this, "Wrong Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    }

    private void Option2Clicked() {
        selected[quesIndex] = 1;
        if (tick == 0) {
            answer2.setBackgroundResource(R.drawable.onselectoptions);
            //defaultColorScheme2();
            tick = 1;
        } else {
            answer2.setBackgroundResource(R.drawable.onselectoptions);
            //answer2tv.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            //answerB.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            answer1.setBackgroundResource(R.drawable.options_shape);
            answer3.setBackgroundResource(R.drawable.options_shape);
            answer4.setBackgroundResource(R.drawable.options_shape);
        }
        if (review == 1) {
            if (correctAns[quesIndex] == 1) {

                Toast toast = Toast.makeText(QuestionActivity.this, "Correct Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(QuestionActivity.this, "Wrong Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

    }

    private void Option3Clicked() {
        selected[quesIndex] = 2;
        if (tick == 0) {
            answer3.setBackgroundResource(R.drawable.onselectoptions);
            //defaultColorScheme3();
            tick = 1;
        } else {

            answer3.setBackgroundResource(R.drawable.onselectoptions);
            //answer3tv.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            //answerC.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            answer2.setBackgroundResource(R.drawable.options_shape);
            answer1.setBackgroundResource(R.drawable.options_shape);
            answer4.setBackgroundResource(R.drawable.options_shape);
        }
        if (review == 1) {
            if (correctAns[quesIndex] == 2) {
                Toast toast = Toast.makeText(QuestionActivity.this, "Correct Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(QuestionActivity.this, "Wrong Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

    }

    private void Option4Clicked() {
        selected[quesIndex] = 3;
        if (tick == 0) {
            answer4.setBackgroundResource(R.drawable.onselectoptions);
            //defaultColorScheme4();
            tick = 1;
        } else {

            answer4.setBackgroundResource(R.drawable.onselectoptions);
            //answer4tv.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            //answerD.setTextColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
            answer2.setBackgroundResource(R.drawable.options_shape);
            answer3.setBackgroundResource(R.drawable.options_shape);
            answer1.setBackgroundResource(R.drawable.options_shape);
        }
        if (review == 1) {
            if (correctAns[quesIndex] == 3) {

                Toast toast = Toast.makeText(QuestionActivity.this, "Correct Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(QuestionActivity.this, "Wrong Answer", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

    }

    private void defaultColorScheme1() {

        switch (MainActivity.Theme_id) {
            case 1:
                answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer2tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer3tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer4tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerB.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerC.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerD.setTextColor(getResources().getColor(R.color.colorTealDark));
                break;
            case 2:
                answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer2tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer3tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer4tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerB.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerC.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerD.setTextColor(getResources().getColor(R.color.colorIndegoDark));

                break;
            case 3:
                answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer2tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer3tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer4tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerB.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerC.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerD.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                break;
            default: //Default Theme Case
                answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerB.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    private void defaultColorScheme2() {
        switch (MainActivity.Theme_id) {
            case 1:
                answer1tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer3tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer4tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answerA.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerC.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerD.setTextColor(getResources().getColor(R.color.colorTealDark));
                break;
            case 2:
                answer1tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer3tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer4tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answerA.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerC.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerD.setTextColor(getResources().getColor(R.color.colorIndegoDark));

                break;
            case 3:
                answer1tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer3tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer4tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answerA.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerC.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerD.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                break;
            default: //Default Theme Case
                answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answerA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    private void defaultColorScheme3() {
        switch (MainActivity.Theme_id) {
            case 1:
                answer1tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer2tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer4tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answerA.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerB.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerD.setTextColor(getResources().getColor(R.color.colorTealDark));
                break;
            case 2:
                answer1tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer2tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer4tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answerA.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerB.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerD.setTextColor(getResources().getColor(R.color.colorIndegoDark));

                break;
            case 3:
                answer1tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer2tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer4tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answerA.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerB.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerD.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                break;
            default: //Default Theme Case
                answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answer4tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answerA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerB.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerD.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    private void defaultColorScheme4() {
        switch (MainActivity.Theme_id) {
            case 1:
                answer1tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer2tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer3tv.setTextColor(getResources().getColor(R.color.colorTeal));
                answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerA.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerB.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerC.setTextColor(getResources().getColor(R.color.colorTealDark));
                answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                break;
            case 2:
                answer1tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer2tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer3tv.setTextColor(getResources().getColor(R.color.colorIndego));
                answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerA.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerB.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerC.setTextColor(getResources().getColor(R.color.colorIndegoDark));
                answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));

                break;
            case 3:
                answer1tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer2tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer3tv.setTextColor(getResources().getColor(R.color.colorOrange));
                answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerA.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerB.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerC.setTextColor(getResources().getColor(R.color.colorOrangeDark));
                answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                break;
            default: //Default Theme Case
                answer1tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer2tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer3tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                answerA.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerB.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerC.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));
                break;
        }
    }


    @Override
    public void onBackPressed() {

        AlertBoxOpen();
    }


    private void setComment() {
        if (score <= 2)
            comment = new String("Poor Score!! Work hard.");
        else if (score <= 5)
            comment = new String("Bad Score!! Not good for a Programmer.");
        else if (score <= 7)
            comment = new String("Not Bad!! Keep trying.");
        else if (score <= 9)
            comment = new String("Good Score!! Keep it up.");
        else if (score == 10)
            comment = new String("Excellent!!! You are what i am looking for..");


    }

    private void review() {

        answer4.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer1.setClickable(false);

        if (selected[quesIndex] == 0 && correctAns[quesIndex] != 0) {

            /*if (correctAns[quesIndex] == 0)
                answer1.setBackgroundResource(R.drawable.correctselected);
            else */
            answer1.setBackgroundResource(R.drawable.wrongselected);
            defaultColorScheme1();

            //answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 1 && correctAns[quesIndex] != 1) {
            //defaultColorScheme2();
            /*if (correctAns[quesIndex] == 1)
                answer2.setBackgroundResource(R.drawable.correctselected);
            else*/
            answer2.setBackgroundResource(R.drawable.wrongselected);
            defaultColorScheme2();

            //answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 2 && correctAns[quesIndex] != 2) {
            //defaultColorScheme3();
            /*if (correctAns[quesIndex] == 2)
                answer3.setBackgroundResource(R.drawable.correctselected);
            else*/
            answer3.setBackgroundResource(R.drawable.wrongselected);
            defaultColorScheme3();

            //answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 3 && correctAns[quesIndex] != 3) {
            //defaultColorScheme4();
            /*if (correctAns[quesIndex] == 3)
                answer4.setBackgroundResource(R.drawable.correctselected);
            else*/
            answer4.setBackgroundResource(R.drawable.wrongselected);
            defaultColorScheme4();

            //answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 0 && correctAns[quesIndex] == 0) {
            answer1.setBackgroundResource(R.drawable.correctselected);
            defaultColorScheme1();
            //answer1tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerA.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 1 && correctAns[quesIndex] == 1) {
            answer2.setBackgroundResource(R.drawable.correctselected);
            defaultColorScheme2();
            //answer2tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerB.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 2 && correctAns[quesIndex] == 2) {
            answer3.setBackgroundResource(R.drawable.correctselected);
            defaultColorScheme3();
            //answer3tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerC.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }
        if (selected[quesIndex] == 3 && correctAns[quesIndex] == 3) {
            answer4.setBackgroundResource(R.drawable.correctselected);
            defaultColorScheme4();
            //answer4tv.setTextColor(getResources().getColor(R.color.PrimaryWhite));
            //answerD.setTextColor(getResources().getColor(R.color.PrimaryWhite));
        }


        Toast toast = new Toast(this);


        if (selected[quesIndex] == -1)
            Toast.makeText(QuestionActivity.this, "Question not Attempted", Toast.LENGTH_SHORT).show();


        if (i < max)
            i++;
        if (b == 1 && i > 0)
            i--;


        quesIndex = randomArray[i];
        b = 0;

    }


    private void AlertBoxOpen() {
        if (QuesNumber < TotalQuestions) {

            if (review == 0) {
                CustomDialogQuiz.Title = new String("Are you sure?");
                CustomDialogQuiz.SubTitle = new String("All progress will be lost.");
                CustomDialogQuiz.NoReview = true;

            } else {
                CustomDialogQuiz.SubTitle = new String("Do you want to cancel review?");
                CustomDialogQuiz.NoTitle = true;
                CustomDialogQuiz.NoReview = true;
            }
            quit = 1;

        } else {
            setComment();
            CustomDialogQuiz.Title = new String(comment);
            CustomDialogQuiz.SubTitle = new String((score) + " out of " + TotalQuestions + "\n" + correct + " Correct  " + wrong + " Wrong  \n" + notAttempted + " Not Attempted ");
        }

        CustomDialogQuiz.PlayAgain = false;
        if (QuesNumber >= TotalQuestions) {
            CustomDialogQuiz.Positive = new String("PLAY AGAIN");
            CustomDialogQuiz.PlayAgain = true;
            CustomDialogQuiz.NoTitle = false;
            CustomDialogQuiz.NoReview = false;

        } else if (quit == 1) {
            CustomDialogQuiz.NO = true;
            CustomDialogQuiz.Positive = new String("NO");
            quit = 0;

        }

        CustomDialogQuiz.Negative = new String("QUIT");


        if (QuesNumber >= TotalQuestions) {
            if (review == 0) {
                CustomDialogQuiz.Neutral = new String("REVIEW");
                CustomDialogQuiz.NoTitle = false;
                CustomDialogQuiz.NoReview = false;
                ReviewClicked();


            }
        }
        Intent i = new Intent(QuestionActivity.this, CustomDialogQuiz.class);
        startActivity(i);

    }

    protected void ReviewClicked() {
        finish.setText("Quit");
        answer4.setBackgroundResource(R.drawable.options_shape);
        answer2.setBackgroundResource(R.drawable.options_shape);
        answer3.setBackgroundResource(R.drawable.options_shape);
        answer1.setBackgroundResource(R.drawable.options_shape);
        review = 1;
        quesIndex = Start;

        setQuesNum();
        i = 0;
        quesIndex = randomArray[i];
        showQuestion(quesIndex);
        back.setClickable(false);
        back.setTextColor(getResources().getColor(R.color.colorGrey));
        back.setAlpha((float) 0.8);
        next.setAlpha(1);
        next.setClickable(true);
    }

    void random() {
        quesIndex = r.nextInt(max - min + 1) + min;
        checkDuplicate();
        if (found) {
            while (found) {
                quesIndex = r.nextInt(max - min + 1) + min;
                found = false;
                checkDuplicate();
            }
        }

        randomArray[i] = quesIndex;
        i++;
    }

    private void checkDuplicate() {
        for (int j = 0; j <= i; j++) {
            if (randomArray[j] == quesIndex) {
                found = true;
                break;
            }
        }
    }

}
