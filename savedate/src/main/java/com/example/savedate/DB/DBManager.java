package com.example.savedate.DB;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="Create table Book("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"price real, "
            +"pages integer,"
            +"author text)";
    public static final String CREATE_CATE="Create table CATE("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"code integer)";
    private Context context;
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATE);
        Log.d("DB", "DBCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists CATE");
        onCreate(db);

    }
}
