package com.example.savedate;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.savedate.Bean.Book;
import com.example.savedate.DB.DBManager;

import org.litepal.tablemanager.Connector;

import java.sql.Connection;

public class DBSave extends AppCompatActivity {
    private Button createtable, adddate, update, delete, read;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbsave);
        createtable = (Button) findViewById(R.id.btn_createtable);
        adddate = (Button) findViewById(R.id.btn_adddate);
        update = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);
        read = (Button) findViewById(R.id.btn_read);
        dbManager = new DBManager(this, "BookStore.db", null, 2);
        createtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.getWritableDatabase();
                Connector.getDatabase();
            }

        });
        adddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbManager.getWritableDatabase();
                ContentValues values = new ContentValues();
                for (int i = 0; i <= 9; i++) {
                    values.put("name", "李四" + i);
                    values.put("price", 32.3 + i);
                    values.put("pages", i + 100);
                    values.put("author", "rrrrrrrrrrrrr");
                    db.insert("Book", null, values);
                    values.clear();
                }
/**
 * litepal 操作数据库
 *
 * */
                Book book=new Book();
                book.setName("李四");
                book.setEditor("memory");
                book.save();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbManager.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 111.1);
                db.update("Book", values, "name=?", new String[]{"李四1"});
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbManager.getWritableDatabase();
                db.delete("Book", "pages=?", new String[]{"103"});
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbManager.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d("BOOK", "book name is " +name);
                        Log.d("BOOK", "book author is " +author);
                        Log.d("BOOK", "book price is " +price);
                        Log.d("BOOK", "book pages is " +pages);
                    } while (cursor.moveToNext());

                }
                cursor.close();
            }
        });
    }
}
