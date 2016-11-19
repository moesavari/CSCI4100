package com.example.myempire.lab_07;


public class Grades {
    private int studentID = 0;
    private String courseComponent = null;
    private float mark = 0;

    public Grades(int studentID, String courseComponent, float mark){
        setStudentID(studentID);
        setCourseComponent(courseComponent);
        setMark(mark);
    }

    public void setStudentID(int studentID){this.studentID = studentID;}
    public void setCourseComponent(String courseComponent){this.courseComponent = courseComponent;}
    public void setMark(float mark){this.mark = mark;}

    public int getStudentID(){return studentID;}
    public String getCourseComponent(){return courseComponent;}
    public float getMark(){return mark;}
}
