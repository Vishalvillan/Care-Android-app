package com.example.care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper2 extends SQLiteOpenHelper {
    public  DBHelper2(Context context)
    {
        super(context,params.DB_NAME,null,params.DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+params.TABLE_NAME+"("+params.APPOINT_NAME+" TEXT "+")";
        Log.d("dbvishal","Query being run is : "+create);
        db.execSQL(create);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists "+params.TABLE_NAME);
    }
    public  void addAppointment(Contact2 contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("dbvishal2",contact.getName());
        ContentValues values=new ContentValues();
        values.put(params.APPOINT_NAME,contact.getName());
        Log.d("name",contact.getName());
        db.insert(params.TABLE_NAME,null,values);
        Log.d("dbvishal1","successfully inserted");
        //db.close();
    }
    public Cursor viewd()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+params.TABLE_NAME,null);
        return cursor;
    }
    public List<Contact2> getAllContacts(String v){
        List<Contact2> contactList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("hi","yes");
        Log.d("yes","hii");
        Cursor cursor=db.rawQuery(" select * from "+params.TABLE_NAME,null);
        Log.d("thus","hii");
        //loop now
         if(cursor.moveToFirst()){
            do {
                Contact2 contact2=new Contact2();
                if(cursor.getString(0).contains(v)){
                contact2.setName(cursor.getString(0));
                contactList.add(contact2);}
            }while (cursor.moveToNext());
        }
        Log.d("hua","yes");
        return contactList;
    }
    public List<Contact2> getContacts(String v){
        List<Contact2> contactList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("hi","hi");
        Log.d("yes","yes");
        Cursor cursor=db.rawQuery(" select * from "+params.TABLE_NAME,null);
        Log.d("thus","hii");
        //String arr[]=v.split(" ",',');
        //String a= arr[arr.length-1];
        Log.d("yes2",v);
        //loop now
        if(cursor.moveToFirst()){
            do {
                Contact2 contact2=new Contact2();
                String b=cursor.getString(0);
                String arr2[]=b.split(" ",',');
                String c= arr2[arr2.length-1];
                String d=arr2[2]+" "+arr2[3]+" "+arr2[4];
                Log.d("hi",c);
                Log.d("yes",v);
                if(c.equals(v)){
                    Log.d("same",c+" "+v);
                    contact2.setName("You have invitation placed by "+d);
                    contactList.add(contact2);}
            }while (cursor.moveToNext());
        }
        Log.d("hua","yes");
        return contactList;
    }
}
