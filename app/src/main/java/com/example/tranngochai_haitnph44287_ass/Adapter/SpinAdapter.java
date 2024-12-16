package com.example.tranngochai_haitnph44287_ass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranngochai_haitnph44287_ass.DAO.SpinDao;
import com.example.tranngochai_haitnph44287_ass.DTO.SpinDTO;
import com.example.tranngochai_haitnph44287_ass.R;

import java.util.ArrayList;

public class SpinAdapter extends RecyclerView.Adapter<SpinAdapter.ViewHolder> {
    ArrayList<SpinDTO> list;
    Context context;
    SpinDao dao;
    SpinDTO spinDTO;
    public SpinAdapter(Context context, ArrayList<SpinDTO> list) {
        this.context = context;
        this.list = list;
        dao = new SpinDao(context);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spin,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpinDTO spinDTO = list.get(position);
        holder.tvNgayNhan.setText("Ngày Nhận: "+ spinDTO.getNgaynhan());
        holder.tvThuong.setText("Thưởng: " + String.valueOf(spinDTO.getThuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNgayNhan,tvThuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayNhan = itemView.findViewById(R.id.tv_ngaynhan);
            tvThuong = itemView.findViewById(R.id.tv_thuong);
        }
    }

}
