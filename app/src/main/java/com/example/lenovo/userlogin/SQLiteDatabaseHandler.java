package com.example.lenovo.userlogin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="UsersDB.db";
    public static final String TABLE_NAME="Users";
    public static final String COL_1="id";
    public static final String COL_2="firstName";
    public static final String COL_3="lastName";
    public static final String COL_4="userId";
    public static final String COL_5="password";
    public static final String COL_6="Email";
    SQLiteDatabase db;

    public SQLiteDatabaseHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + "( id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, userId TEXT, password TEXT, Email Text)";
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public String SearchPass(String Userid){
        db = this.getReadableDatabase();
        String query="SELECT userId,password from Users";
        Cursor cursor=db.rawQuery(query,null);
        String a,b;
        b="Not Found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(Userid))
                {
                    b= cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }
    public void ClearRecords()
    {
        db=this.getWritableDatabase();
        String query="delete from "+TABLE_NAME;
        db.execSQL(query);
        String query1="drop table "+TABLE_NAME;
        db.execSQL(query1);
        String query2="CREATE TABLE " + TABLE_NAME + "( id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, userId TEXT, password TEXT, Email Text)";
        db.execSQL(query2);
    }
}
