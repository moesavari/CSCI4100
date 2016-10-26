package com.example.myempire.questionnaire;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SummaryActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        String[] array_of_answers = intent.getStringArrayExtra("answered_array");

        ListView questions_list = (ListView) findViewById(R.id.Questions_List);

        String[] array_of_questions = getResources().getStringArray(R.array.questionnaire);
        ArrayAdapter<String> adapterQ = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array_of_questions);

        questions_list.setAdapter(adapterQ);

        ListView answers_list = (ListView) findViewById(R.id.Answers_List);

        ArrayAdapter<String> adapterA = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array_of_answers);

        answers_list.setAdapter(adapterA);
    }
}
