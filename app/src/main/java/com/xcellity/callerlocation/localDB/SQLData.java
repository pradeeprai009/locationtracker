package com.xcellity.callerlocation.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLData extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "location_tracker";

    public static final String TABLE_NAME_LOCATION = "location_data";
    public static final String ID = "id";
    public static final String LOCATION = "title";


    String YOURLOCATION = "CREATE TABLE " + TABLE_NAME_LOCATION + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + LOCATION + " TEXT" + ")";


    public SQLData(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(YOURLOCATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOCATION);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String location) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLData.LOCATION, location);
        db.insert(SQLData.TABLE_NAME_LOCATION, null, contentValues);
        return true;
    }

//    public ArrayList<NotiModel> getExpenseData() {
//        ArrayList<NotiModel> arrayList =new ArrayList<>();
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME_EXPENSE,null);
//
//        while (cursor.moveToNext()){
//            String id=cursor.getString(0);
//            String title=cursor.getString(1);
//            String message=cursor.getString(2);
//            String packagesNa=cursor.getString(3);
//            String date=cursor.getString(4);
//            NotiModel expense=new NotiModel(id,title,message,packagesNa,date);
//            arrayList.add(expense);
//        }
//        return arrayList;
//    }


}
