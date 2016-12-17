package com.example.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BikeDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "Bikes.db";
    private static final String CREATE_STATEMENT = "" +
            "create table Bikes(" +
            " _id integer primary key autoincrement," +
            " bikeShareId int not null," +
            " latitude double not null," +
            " longitude double not null," +
            " name text not null," +
            " numBikes int not null," +
            " address text null)";
    private static final String DROP_DATABASE = "" +
            "drop table Bikes";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_STATEMENT);}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_DATABASE);
        sqLiteDatabase.execSQL(CREATE_STATEMENT);
    }

    public BikeDBHelper(Context context){
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    public Bike createBike(int bikeShareId, double latitude, double longitude, String name, int numBikes, String address){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bikeShareId", bikeShareId);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("name", name);
        contentValues.put("numBikes", numBikes);
        contentValues.put("address", address);

        int id = (int)sqLiteDatabase.insert("Bikes", null, contentValues);
        Bike newBike = new Bike(bikeShareId, latitude, longitude, name, numBikes, address);
        newBike.setId(id);

        return newBike;
    }

    public List<Bike> getAllBikes(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Bike> bikeResults = new ArrayList<>();

        String[] columns = new String[] {"_id", "bikeShareId", "latitude", "longitude", "name", "numBikes", "address"};
        String where = "";
        String[] whereArgs = new String[]{};
        String groupBy = "";
        String groupArgs = "";
        String orderBy = "bikeShareId";

        Cursor cursor = sqLiteDatabase.query("Bikes", columns, where, whereArgs, groupBy, groupArgs, orderBy);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            int bikeShareId = cursor.getInt(1);
            double lat = cursor.getDouble(2);
            double lng = cursor.getDouble(3);
            String name = cursor.getString(4);
            int numBikes = cursor.getInt(5);
            String address = cursor.getString(6);

            bikeResults.add(new Bike(bikeShareId, lat, lng, name, numBikes, address));

            cursor.moveToNext();
        }
        return bikeResults;
    }

    public void updateBike(Bike bike){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", bike.getName());

        sqLiteDatabase.update("Bikes", contentValues, "_id=?", new String[] {""+bike.getId()});
    }

    public void deleteAllBikes(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Bikes", "", new String[] {});
    }

}
