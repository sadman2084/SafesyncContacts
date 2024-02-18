package com.flarestudio.safesynccontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table on database
        db.execSQL(Constants.CREATE_TABLE);
        Log.d("DatabaseLOG", "Database Created :: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade table if any structure change in db

        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        Log.d("DatabaseLOG", "Database Updated ::");

        // create table again
        onCreate(db);

    }

    // Insert Function to insert data in database
    public long insertContact(String image, String name, String phone, String email, String note, String addedTime,
                              String updatedTime) {

        // get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("DatabaseLOG", "Database Accessed ::");

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.C_IMAGE, image);
        contentValues.put(Constants.C_NAME, name);
        contentValues.put(Constants.C_PHONE, phone);
        contentValues.put(Constants.C_EMAIL, email);
        contentValues.put(Constants.C_NOTE, note);
        contentValues.put(Constants.C_ADDED_TIME, addedTime);
        contentValues.put(Constants.C_UPDATED_TIME, updatedTime);

        // insert data in row, It will return id of record
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        Log.d("DatabaseLOG", "New data Inserted ::");

        // close db
        db.close();
        Log.d("DatabaseLOG", "Database Closed ::");

        // return id
        return id;

    }
}
