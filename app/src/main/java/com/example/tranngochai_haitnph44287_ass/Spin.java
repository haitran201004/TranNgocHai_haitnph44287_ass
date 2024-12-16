package com.example.tranngochai_haitnph44287_ass;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;
import com.example.tranngochai_haitnph44287_ass.Adapter.SpinAdapter;
import com.example.tranngochai_haitnph44287_ass.DAO.SpinDao;
import com.example.tranngochai_haitnph44287_ass.DTO.SpinDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spin extends AppCompatActivity {
    LuckyWheel luckyWheel;
    List<WheelItem> list = new ArrayList<WheelItem>();
    String points;
    Button btnQuay;
    SpinDao spinDao;
    SpinDTO spinDTO;
    ArrayList<SpinDTO> listSpin = new ArrayList<>();
    RecyclerView rcv;
    SpinAdapter spinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        luckyWheel = findViewById(R.id.lwv);
        btnQuay = findViewById(R.id.btnQuay);
        rcv = findViewById(R.id.rcvnhanthuong);
        spinDao = new SpinDao(this);
        spinDTO = new SpinDTO();
        Toast.makeText(this, spinDao.getTodayDate(), Toast.LENGTH_SHORT).show();
        btnQuay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnClick(view);
            }
        });
        if (spinDao.checkNgay()) {
            btnQuay.setBackgroundResource(R.drawable.btn_disable);
            btnQuay.setEnabled(false);
            Toast.makeText(this, spinDao.checkNgay() + "", Toast.LENGTH_SHORT).show();
        }
        HienThiNhanThuong();

        WheelItem item = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "10000");
        WheelItem item2 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "9000");
        WheelItem item3 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "8000");
        WheelItem item4 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "7000");
        WheelItem item5 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "6000");
        WheelItem item6 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "5000");
        WheelItem item7 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "4000");
        WheelItem item8 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "3000");
        WheelItem item9 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "2000");
        WheelItem item10 = new WheelItem(ResourcesCompat.getColor(getResources(),
                R.color.black, null), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "1000");
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);
        list.add(item10);
        luckyWheel.addWheelItems(list);
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                WheelItem itemSelected = list.get(Integer.parseInt(points) - 1);
                String point_amount = itemSelected.text;
                Toast.makeText(Spin.this, point_amount, Toast.LENGTH_SHORT).show();
                spinDTO.setNgaynhan(spinDao.getTodayDate());
                spinDTO.setThuong(Float.parseFloat(point_amount));
                spinDao.addRow(spinDTO);
                HienThiNhanThuong();

            }
        });

    }
    public void btnClick(View v) {
        Random random = new Random();
        points = String.valueOf(random.nextInt(10));
        if (points.equals("0")) {
            points = String.valueOf(1);
        }
        luckyWheel.rotateWheelTo(Integer.parseInt(points));
    }

    public void HienThiNhanThuong() {
        listSpin = spinDao.getList();
        spinAdapter = new SpinAdapter(this, listSpin);
        rcv.setAdapter(spinAdapter);
    }
}


