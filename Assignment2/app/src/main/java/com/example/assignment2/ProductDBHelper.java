package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ProductDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "products.db";

    private static final String CREATE_STATEMENT = "" +
            "create table products(" +
            "  pid integer primary key autoincrement," +
            "  name text not null," +
            "  description text null," +
            "  price float null)";

    private static final String DROP_STATEMENT = "" +
            "drop table products";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_STATEMENT); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_STATEMENT);
    }

    public ProductDBHelper(Context context){
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    public List<Product> getAllProducts(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Product> productResults = new ArrayList<>();

        String[] columns = new String[] {"pid", "name", "description", "price"};

        String where = "";
        String[] whereArgs = new String[] {};
        String groupBy = "";
        String groupArgs = "";
        String orderBy = "name";

        Cursor cursor = sqLiteDatabase.query("products", columns, where, whereArgs,
                groupBy, groupArgs, orderBy);

        cursor.moveToFirst();


        while(!cursor.isAfterLast()){
            int PID = cursor.getInt(0);
            String Pname = cursor.getString(1);
            String Pdesc = cursor.getString(2);
            float Pprice = cursor.getFloat(3);

            productResults.add(new Product(PID, Pname, Pdesc, Pprice));

            cursor.moveToNext();
        }
        cursor.close();

        return productResults;
    }

    public void deleteProduct(int PID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("products", "pid=?", new String[] {Integer.toString(PID)});
    }

    public Product addNewProduct(String name, String desc, float price){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("description", desc);
        contentValues.put("price", price);

        int PID = (int) sqLiteDatabase.insert("products", null, contentValues);

        Product product = new Product(PID, name, desc, price);
        return product;
    }
}
