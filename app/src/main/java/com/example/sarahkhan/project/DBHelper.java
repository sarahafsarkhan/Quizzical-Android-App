package com.example.sarahkhan.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COL_USERNAME + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);

        long result = MyDB.insert(TABLE_NAME, null, contentValues);
        MyDB.close(); // Close database connection

        return result != -1;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COL_USERNAME + " = ?", new String[]{username});

        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Close cursor
        MyDB.close();   // Close database connection

        return exists;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                        COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?",
                new String[]{username, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close(); // Close cursor
        MyDB.close();   // Close database connection

        return isValid;
    }

    // Additional method to get all users (for debugging)
    public Cursor getAllUsers() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        return MyDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Method to delete a user
    public Boolean deleteUser(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int result = MyDB.delete(TABLE_NAME, COL_USERNAME + " = ?", new String[]{username});
        MyDB.close();

        return result > 0;
    }

    // Method to update password
    public Boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PASSWORD, newPassword);

        int result = MyDB.update(TABLE_NAME, contentValues, COL_USERNAME + " = ?",
                new String[]{username});
        MyDB.close();

        return result > 0;
    }
}