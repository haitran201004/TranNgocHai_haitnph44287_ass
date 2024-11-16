package com.example.tranngochai_haitnph44287_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tranngochai_haitnph44287_ass.DBHelper.MyDbHelper;
import com.example.tranngochai_haitnph44287_ass.DTO.UserDTO;

import java.util.ArrayList;

public class UserDao {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    UserDTO user;

    public UserDao(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<UserDTO> getList(){
        ArrayList<UserDTO> listUser = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_users", null);
        if(c != null && c.getCount()>0){
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String name = c.getString(1);
                String email = c.getString(2);
                String password = c.getString(3);
                String fullname = c.getString(4);
                UserDTO objUser = new UserDTO();
                objUser.setId_user(id);
                objUser.setUsername(name);
                objUser.setEmail(email);
                objUser.setPassword(password);
                objUser.setFullname(fullname);
                //cho vao list
                listUser.add(objUser);
            }while (c.moveToNext());

        }else{
            //log: khong lay dc du lieu
            Log.d("zzzzzz","CatDAO::getList: Khong lay duoc du lieu");
        }
        return  listUser;
    }

    public boolean register (UserDTO objUser){
        ContentValues v = new ContentValues();
        v.put("username", objUser.getUsername());
        v.put("email", objUser.getEmail());
        v.put("password", objUser.getPassword());
        v.put("fullname", objUser.getFullname());
        int kq = (int)db.insert("tb_users",null,v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq>0;
    }
    public  boolean login (UserDTO objUser) {
        Cursor c = db.rawQuery("SELECT * FROM tb_users WHERE username = ? AND password = ? OR email = ? AND password = ?", new String[]{objUser.getUsername(), objUser.getPassword(), objUser.getEmail(), objUser.getPassword()});
        boolean isLogin = c.getCount() > 0;
        return isLogin;
    }



}
