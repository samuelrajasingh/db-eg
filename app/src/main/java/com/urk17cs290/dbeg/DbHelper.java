package com.urk17cs290.dbeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users(name varchar(20),mobile integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query="drop table if exists users";
    db.execSQL(query);
    onCreate(db);
    }
    //saveData
    public long saveData(String userName,int num){

        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("NAME",userName);
        cv.put("MOBILE",num);
        long recds= db.insert("users",null,cv);
        return recds;
    }
    //getData
    public String viewAll(){
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users",null);
        String out="";
        while (cursor.moveToNext()){
            String name= cursor.getString(0);
            int ph= cursor.getInt(1);
            out=out+name+" - "+"ph"+"\n";
        }
        return out;
    }
}
