package com.emarkova.session16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
    private static final String NAME = "CAL";
    private static final String DB_NAME = "calculator_results";
    private static final int VERSION_DB = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {

        this(context, DB_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE "+ NAME +" (id integer PRIMARY KEY, result text NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        deleteTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    private void deleteTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NAME);
    }

    public void insertTable(SQLiteDatabase sqLiteDatabase, String result) {
        try{
            sqLiteDatabase.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("result", result);
            sqLiteDatabase.insert(NAME, null, values);
            sqLiteDatabase.setTransactionSuccessful();
        }
        catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        }
        finally {
            if(sqLiteDatabase.inTransaction())
                sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }

    public String selectRow(SQLiteDatabase readableDatabase, String result) {
        String query = "SELECT * FROM " + NAME + " WHERE result='"+ result + "'";
        Cursor cursor = readableDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("result"));
    }
}
