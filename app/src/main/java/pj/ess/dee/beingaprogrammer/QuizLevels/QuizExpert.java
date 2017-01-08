package pj.ess.dee.beingaprogrammer.QuizLevels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.Activities.QuestionActivity;
import pj.ess.dee.beingaprogrammer.Activities.QuizFetchActivity;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by deepak on 3/31/2015.
 */
public class QuizExpert extends ActionBarActivity {
    Toolbar toolbar;
LinearLayout toolbar_background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.quizmenu_list);
        toolbar_background= (LinearLayout) findViewById(R.id.toolbar_background);
        toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        setSupportActionBar(toolbar);
        if(MainActivity.Set_Theme){
            switch (MainActivity.Theme_id){
                case 1:
                    toolbar_background.setBackgroundResource(R.color.colorTeal);
                    toolbar.setBackgroundResource(R.color.colorTeal);
                    break;
                case 2:
                    toolbar.setBackgroundResource(R.color.colorIndego);
                    toolbar_background.setBackgroundResource(R.color.colorIndego);
                    break;
                case 3:
                    toolbar.setBackgroundResource(R.color.colorOrange);
                    toolbar_background.setBackgroundResource(R.color.colorOrange);

                    break;
                default://Default Theme
                    toolbar.setBackgroundResource(R.color.colorPrimary);
                    toolbar_background.setBackgroundResource(R.color.colorPrimary);

            }
        }
        else{//Default Theme
            toolbar.setBackgroundResource(R.color.colorPrimary);
            toolbar_background.setBackgroundResource(R.color.colorPrimary);
        }
        setTitle("Quiz Test-Expert");
        final String categories[] = {"Quiz Set 1", "Quiz Set 2", "Quiz Set 3"};
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.text_list, R.id.item, categories);
        ListView listView = (ListView) findViewById(R.id.list_items);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                QuestionActivity.Start = 0;
                QuestionActivity.End = 84;
                QuestionActivity.NegativeMarking = 1;
                QuestionActivity.max_timer=10;
                startActivity(new Intent(QuizExpert.this, QuizFetchActivity.class));
            }
        });
    }

}