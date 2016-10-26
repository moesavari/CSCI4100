package com.example.myempire.questionnaire;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AskActivity extends Activity {

    String[] question_array;
    int count = 0;
    String[] answer = new String[5];

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        question_array = getResources().getStringArray(R.array.questionnaire);

        TextView question_txt = (TextView) findViewById(R.id.questions);
        question_txt.setText(question_array[count]);

        Button yes_btn = (Button) findViewById(R.id.YesBTN);
        yes_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count <= question_array.length) {
                    answer[count] = "Yes";
                    count++;
                    if (count == 5) {
                        summaryIntent();
                    } else
                        updateQuestion();
                }
            }
        });

        Button no_btn = (Button) findViewById(R.id.NoBTN);
        no_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count < 5) {
                    answer[count] = "No";
                    count++;
                    if (count == 5) {
                        summaryIntent();
                    } else
                        updateQuestion();
                }
            }
        });
    }
    private void updateQuestion() {

        TextView question_txt = (TextView) findViewById(R.id.questions);
        question_txt.setText(question_array[count]);
    }

    private void summaryIntent() {
        Intent intent = new Intent(AskActivity.this, SummaryActivity.class);
        intent.putExtra("answered_array", answer);
        startActivity(intent);
    }
}