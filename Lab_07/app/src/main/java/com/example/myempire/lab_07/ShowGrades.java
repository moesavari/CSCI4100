package com.example.myempire.lab_07;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowGrades extends AppCompatActivity {

    GradesDBHelper gradesDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grade);

        gradesDBHelper = new GradesDBHelper(this);

        gradesDBHelper.addNewGrade(10000006, "CSCI2000", 85);
        gradesDBHelper.addNewGrade(20000001, "CSCI3230", 72);
        gradesDBHelper.addNewGrade(12222212, "CSCI4100", 74);

        Button addGrade = (Button)findViewById((R.id.add_grade_btn));
        addGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowGrades.this, AddGrade.class);
                startActivity(intent);
            }
        });

        Button deleteGrade = (Button)findViewById(R.id.delete_grade_btn);
        deleteGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowGrades.this, DeleteGrade.class);
                startActivity(intent);
            }
        });

        List<Grades> allGrades = gradesDBHelper.getAllGrades();

        if (allGrades.size() > 0) {
            showGrades(allGrades);
        }

    }


    private void showGrades(List<Grades> gradesList) {
        ListView listView = (ListView) findViewById(R.id.list_of_grades);
        listView.setAdapter(new GradesArrayAdapter(this, gradesList));
    }
}
