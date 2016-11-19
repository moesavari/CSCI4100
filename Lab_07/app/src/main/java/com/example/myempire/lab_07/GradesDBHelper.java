package com.example.myempire.lab_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class GradesDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "grades.db";

    private static final String CREATE_STATEMENT = "" +
            "create table grades(" +
            "  studentId integer primary key," +
            "  courseComponent varchar(100) not null," +
            "  mark decimal not null)";

    private static final String DROP_STATEMENT = "" +
            "drop table grades";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_STATEMENT); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_STATEMENT);
    }

    public GradesDBHelper(Context context){
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    public List<Grades> getAllGrades(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Grades> gradeArrayList = new ArrayList<>();

        String[] columns = new String[] {"studentId", "courseComponent", "mark"};

        String where = "";
        String[] whereArgs = new String[] {};
        String groupBy = "";
        String groupArgs = "";
        String orderBy = "studentId";

        Cursor cursor = sqLiteDatabase.query("grades", columns, where, whereArgs,
                groupBy, groupArgs, orderBy);

        cursor.moveToFirst();


        while(!cursor.isAfterLast()){
            int studentID = cursor.getInt(0);
            String course = cursor.getString(1);
            float mark = cursor.getFloat(2);

            gradeArrayList.add(new Grades(studentID, course, mark));

            cursor.moveToNext();
        }
        cursor.close();

        return gradeArrayList;
    }

    public List<Integer> getAllStudentIDs(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Integer> IDList = new ArrayList<>();

        String[] columns = new String[] {"studentId"};

        String where = "";
        String[] whereArgs = new String[] {};
        String groupBy = "";
        String groupArgs = "";
        String orderBy = "studentId";

        Cursor cursor = sqLiteDatabase.query("grades", columns, where, whereArgs,
                groupBy, groupArgs, orderBy);

        cursor.moveToFirst();


        while(!cursor.isAfterLast()){
            int studentID = cursor.getInt(0);

            IDList.add(studentID);

            cursor.moveToNext();
        }
        cursor.close();

        return IDList;
    }

    public void deleteGrade(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("grades", "studentId=?", new String[] {Integer.toString(studentID)});
    }

    public Grades addNewGrade(int SID, String courseComponent, float mark){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("studentId", SID);
        contentValues.put("courseComponent", courseComponent);
        contentValues.put("mark", mark);
        sqLiteDatabase.insert("grades", null, contentValues);

        Grades grade = new Grades(SID, courseComponent, mark);
        return grade;
    }
}
