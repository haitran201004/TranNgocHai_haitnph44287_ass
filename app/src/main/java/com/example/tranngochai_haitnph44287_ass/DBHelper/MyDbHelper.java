package com.example.tranngochai_haitnph44287_ass.DBHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context){
        super(context, "QLCV.db",null,5);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String sqlUsers ="CREATE TABLE tb_users ( id_user  INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, email    TEXT    NOT NULL UNIQUE, password TEXT  NOT NULL, fullname TEXT  NOT NULL)";
    String sqlvalueUser = "INSERT INTO tb_users (username, email, password, fullname) VALUES ('haihai','hai@gmail.com','123456','haideptrai')";
    sqLiteDatabase.execSQL(sqlUsers);
    sqLiteDatabase.execSQL(sqlvalueUser);
    String sqlWork = "CREATE TABLE tb_congviec ( id INTEGER PRIMARY KEY AUTOINCREMENT, name  TEXT  NOT NULL, content TEXT  NOT NULL, status  INTEGER DEFAULT (0), start_date TEXT   NOT NULL  DEFAULT (DATE('now')), end_date TEXT    NOT NULL)";
    String sqlvalueWork = "INSERT INTO tb_congviec (name, content, start_date, end_date) VALUES ('dev','dsjhgvjmxhcxvxcb','2024/07/08','2024/07/09')";
    sqLiteDatabase.execSQL(sqlWork);
    sqLiteDatabase.execSQL(sqlvalueWork);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 > i) { // i1 là pban mới, i là phiên bản cũ
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_users");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_congviec");
            onCreate(sqLiteDatabase);
        }
    }
}
