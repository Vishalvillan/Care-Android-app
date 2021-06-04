package com.example.care;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="sign.db";
    public DBHelper( Context context) {
        super(context, "sign.db", null, 1);
    }

       public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key,pass TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }
    public boolean insertData(String email,String pass){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        long result= MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean checkuseremail(String email){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("Select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public boolean checkuserpass(String email,String pass){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where email=? and pass=?",new String[]{email,pass});
        if(cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
}
