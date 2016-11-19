package com.example.myempire.lab_07;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteGrade extends Activity{

    GradesDBHelper gradesDBHelper;
    ArrayList<Integer> gradesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grade);

        gradesDBHelper = new GradesDBHelper(this);
        gradesList = new ArrayList<>(gradesDBHelper.getAllStudentIDs());

        Log.d("grades", String.valueOf(gradesList.get(0)));


        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gradesList);

        final Spinner grade_spinner = (Spinner)findViewById(R.id.grade_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        grade_spinner.setAdapter(adapter);

        Button delete = (Button)findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteGrade.this, ShowGrades.class);
                gradesDBHelper.deleteGrade(gradesList.get(grade_spinner.getSelectedItemPosition()));
                startActivity(intent);
            }
        });

    }
}
