package com.example.tranngochai_haitnph44287_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tranngochai_haitnph44287_ass.DBHelper.MyDbHelper;
import com.example.tranngochai_haitnph44287_ass.DTO.CongViecDTO;

import java.util.ArrayList;

public class CongViecDao {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    CongViecDTO congViec;
    Context context;

    public CongViecDao(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public boolean deleteDao(int CongViecId) {
        int result = db.delete("tb_congviec", "id = ?", new String[]{String.valueOf(CongViecId)});
        return result > 0; // Trả về true nếu xóa thành công
    }

    public int addRow(CongViecDTO objCV) {
        ContentValues v = new ContentValues();
        v.put("name", objCV.getName());
        v.put("content", objCV.getContent());
        v.put("status", objCV.getStatus());
        v.put("start_date", objCV.getStart_date());
        v.put("end_date", objCV.getEnd_date());
        int kq = (int) db.insert("tb_congviec", null, v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq;
    }
    // hiển thị
    public ArrayList<CongViecDTO> getList() {
        ArrayList<CongViecDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_congviec", null);
        if (c != null && c.getCount() > 0) {
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String name = c.getString(1);
                String content = c.getString(2);
                int status = c.getInt(3);
                String start_date = c.getString(4);
                String end_date = c.getString(5);
                CongViecDTO objCV = new CongViecDTO();
                objCV.setId(id);
                objCV.setName(name);
                objCV.setContent(content);
                objCV.setStatus(status);
                objCV.setStart_date(start_date);
                objCV.setEnd_date(end_date);
                //cho vao list
                list.add(objCV);
            } while (c.moveToNext());


        } else {
            //log: khong lay dc du lieu
            Log.d("zzzzzz", "CatDAO::getList: Khong lay duoc du lieu");
        }
        return list;
    }
    public boolean updateCV(CongViecDTO congViec) {
        ContentValues values = new ContentValues();
        values.put("name", congViec.getName());
        values.put("content", congViec.getContent());
        values.put("status", congViec.getStatus());
        values.put("start_date", congViec.getStart_date());
        values.put("end_date", congViec.getEnd_date());
//        Toast.makeText(context ,product.getId()+"zzz", Toast.LENGTH_SHORT).show();

        int result = db.update("tb_congviec", values, "id = ?", new String[]{String.valueOf(congViec.getId())});
        return result > 0; // Trả về true nếu cập nhật thành công
    }



}
