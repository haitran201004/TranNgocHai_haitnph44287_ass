package com.example.tranngochai_haitnph44287_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.tranngochai_haitnph44287_ass.DBHelper.MyDbHelper;
import com.example.tranngochai_haitnph44287_ass.DTO.SpinDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SpinDao {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    SpinDTO spinDTO;
    Context context;

    public SpinDao(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Boolean checkNgay(){
        Cursor c = db.rawQuery("SELECT * FROM tb_spin WHERE date = ? ", new String[]{getTodayDate()});
        boolean isChecked = c.getCount() > 0;
        return isChecked;

    }
//    public  boolean login (UserDTO objUser) {
//        Cursor c = db.rawQuery("SELECT * FROM tb_users WHERE username = ? AND password = ?  OR email = ? AND password = ?", new String[]{objUser.getUsername(),objUser.getPassword(), objUser.getEmail(), objUser.getPassword()});
//        boolean isLogin = c.getCount() > 0;
//        return isLogin;
//    }
    public String getTodayDate() {
        // Định dạng ngày theo "dd/MM/yyyy"
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Lấy ngày hiện tại
        Date today = new Date();
        // Trả về chuỗi ngày đã định dạng
        return sdf.format(today);
    }
    public int addRow(SpinDTO spin) {
        ContentValues v = new ContentValues();
        v.put("date", spin.getNgaynhan());
        v.put("thuong", spin.getThuong());
        int kq = (int) db.insert("tb_spin", null, v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq;
    }
    public ArrayList<SpinDTO> getList(){
        ArrayList<SpinDTO> listSpin = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_spin", null);
        if(c != null && c.getCount()>0){
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String date= c.getString(1);
                int thuong = c.getInt(2);
                SpinDTO objUser = new SpinDTO();
                objUser.setId(id);
                objUser.setNgaynhan(date);
                objUser.setThuong(thuong);
                //cho vao list
                listSpin.add(objUser);
            }while (c.moveToNext());

        }else{
            //log: khong lay dc du lieu
            Log.d("zzzzzz","CatDAO::getList: Khong lay duoc du lieu");
        }
        return  listSpin;
    }



}
