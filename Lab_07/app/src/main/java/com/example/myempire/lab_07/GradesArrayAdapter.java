package com.example.myempire.lab_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GradesArrayAdapter extends BaseAdapter {
    private Context context = null;
    private List<Grades> grades = null;

    public GradesArrayAdapter(Context context, List<Grades> grades) {
        this.context = context;
        this.grades = grades;
    }

    @Override
    public int getCount() {
        return grades.size();
    }

    @Override
    public Object getItem(int position) {
        return grades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Grades grade = grades.get(position);

        if (convertView == null) {
            // no previous view:  create a new view, based on our custom list item layout
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grades_list_layout, parent, false);
        }


        // populate the UI elements with our data
        TextView lblSID = (TextView)convertView.findViewById(R.id.studentID_layout);
        lblSID.setText(Integer.toString(grade.getStudentID()));

        TextView lblCC = (TextView)convertView.findViewById(R.id.course_txt_layout);
        lblCC.setText(grade.getCourseComponent());

        TextView lblMark = (TextView)convertView.findViewById(R.id.grade_txt_layout);
        lblMark.setText(Float.toString(grade.getMark()));

        return convertView;
    }
}

