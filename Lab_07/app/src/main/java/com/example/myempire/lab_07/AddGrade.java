package com.example.myempire.lab_07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGrade extends Activity {

    private GradesDBHelper gradesDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        gradesDBHelper = new GradesDBHelper(this);

        Button add = (Button)findViewById(R.id.add_grade_act_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGrade.this, ShowGrades.class);

                EditText SID = (EditText)findViewById(R.id.student_id_edit);
                int studentID = Integer.parseInt(SID.getText().toString());

                EditText course = (EditText)findViewById(R.id.course_component_edit);
                String courseComponent = course.getText().toString();

                EditText mark = (EditText)findViewById(R.id.grade_edit);
                float mark_add = Float.parseFloat(mark.getText().toString());

                gradesDBHelper.addNewGrade(studentID, courseComponent, mark_add);

                finish();
                startActivity(intent);
            }
        });
    }
}