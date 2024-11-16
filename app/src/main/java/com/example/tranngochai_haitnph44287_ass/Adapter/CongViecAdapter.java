package com.example.tranngochai_haitnph44287_ass.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranngochai_haitnph44287_ass.DAO.CongViecDao;
import com.example.tranngochai_haitnph44287_ass.DTO.CongViecDTO;
import com.example.tranngochai_haitnph44287_ass.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder> {
    ArrayList<CongViecDTO> list;
    Context context;
    CongViecDao dao;
    CongViecDTO congViecDTO;
    int newStatus;

    public CongViecAdapter(Context context, ArrayList<CongViecDTO> list) {
        this.context = context;
        this.list = list;
        dao = new CongViecDao(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cv,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CongViecDTO congViecDTO = list.get(position);
        String status = "";
        if(congViecDTO.getStatus() == 0){
            status = "Mới Tạo";
        }else if(congViecDTO.getStatus() == 1){
            status = "Hoàn thành";
        }else if(congViecDTO.getStatus() == 2){
            status = "Chưa hoàn thành";
        }else if(congViecDTO.getStatus() == -1){
            status = "Cho vào thùng rác";
        }
        holder.tvId.setText(String.valueOf("Id: "+congViecDTO.getId()));
        holder.tvName.setText("Id: "+congViecDTO.getName());
        holder.tvContent.setText("Content: "+congViecDTO.getContent());
        holder.tvStatus.setText("Status: "+status);
        holder.tvStartDate.setText("Start Date: "+congViecDTO.getStart_date());
        holder.tvEndDate.setText("End Date: "+congViecDTO.getEnd_date());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(congViecDTO);

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDeleteDialog(congViecDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId,tvName,tvContent,tvStatus,tvStartDate,tvEndDate;
        ImageButton btnDelete,btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvStartDate = itemView.findViewById(R.id.tv_start_date);
            tvEndDate = itemView.findViewById(R.id.tv_end_date);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);


        }
    }
    private void showEditDialog(CongViecDTO congViecDTO) {
        // Tạo dialog từ layout
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.item_dialog_add_cv, null);
        builder.setView(dialogView);

        EditText edtName = dialogView.findViewById(R.id.edt_name);
        EditText edtContent = dialogView.findViewById(R.id.edt_content);
        Spinner spStatus = dialogView.findViewById(R.id.sp_status);
        EditText edtStartDate = dialogView.findViewById(R.id.sp_start_date);
        EditText edtEndDate = dialogView.findViewById(R.id.sp_end_date);
        Button btnSave = dialogView.findViewById(R.id.btn_Luu);
        Button btnHuy = dialogView.findViewById(R.id.btn_Huy);
        LinearLayout llStatus = dialogView.findViewById(R.id.ll_status);
        TextView tv = dialogView.findViewById(R.id.tv_title);
        tv.setText("Sửa Công Việc");

        edtName.setText(congViecDTO.getName());
        edtContent.setText(congViecDTO.getContent());
        edtStartDate.setText(congViecDTO.getStart_date());
        edtEndDate.setText(congViecDTO.getEnd_date());

//        llStatus.setVisibility(View.GONE); // Ẩn hoàn toàn và giải phóng không gian


        // Gán giá trị hiện tại vào các EditText
        // Tạo AlertDialog và hiển thị
        AlertDialog dialog = builder.create();
        dialog.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ArrayList<String> listStatus = new ArrayList<>();
        listStatus.add("Mới Tạo");
        listStatus.add("Hoàn thành");
        listStatus.add("Chưa hoàn thành");
        listStatus.add("Cho vào thùng rác");
        spStatus.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listStatus));

        if(congViecDTO.getStatus() == 0){
            spStatus.setSelection(0);
        }else if(congViecDTO.getStatus() == 1){
            spStatus.setSelection(1);
        }else if(congViecDTO.getStatus() == 2){
            spStatus.setSelection(2);
        }else if(congViecDTO.getStatus() == -1){
            spStatus.setSelection(3);
        }


        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Gán giá trị được chọn vào biến toàn cục newStatus
                newStatus = i;

                // Hiển thị giá trị (tuỳ chọn)
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Không làm gì nếu không có lựa chọn
            }
        });

        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH); // Lưu ý: tháng bắt đầu từ 0
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Hiển thị DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context,
                        (v, selectedYear, selectedMonth, selectedDay) -> {
                            // Xử lý khi người dùng chọn ngày
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            edtStartDate.setText(selectedDate);

                            // Hiển thị thông báo
//                            Toast.makeText(context, "Bạn đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH); // Lưu ý: tháng bắt đầu từ 0
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Hiển thị DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context,
                        (v, selectedYear, selectedMonth, selectedDay) -> {
                            // Xử lý khi người dùng chọn ngày
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            edtEndDate.setText(selectedDate);

                            // Hiển thị thông báo
//                            Toast.makeText(context, "Bạn đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });





        btnSave.setOnClickListener(v -> {
            // Lấy giá trị mới từ các EditText
            String newName = edtName.getText().toString();
            String newContent = edtContent.getText().toString();
            String newStartDate = edtStartDate.getText().toString();
            String newEndDate = edtEndDate.getText().toString();

            if (newName.isEmpty() || newContent.isEmpty() || newStartDate.isEmpty() || newEndDate.isEmpty()){
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật thông tin sản phẩm
            congViecDTO.setName(newName);
            congViecDTO.setContent(newContent);
            congViecDTO.setStatus(newStatus);
            congViecDTO.setStart_date(newStartDate);
            congViecDTO.setEnd_date(newEndDate);



            boolean isUP = dao.updateCV(congViecDTO);
            if (isUP) {
                list.clear();
                list.addAll(dao.getList());
                notifyDataSetChanged();
                Toast.makeText(context, "Đã sửa Công Việc", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Sửa Công Việc thất bại", Toast.LENGTH_SHORT).show();
            }


            dialog.dismiss();
        });
    }
    private void showDeleteDialog( CongViecDTO congViecDTO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa? Hành động này không thể hoàn tác.");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            boolean isDeleted = dao.deleteDao(congViecDTO.getId()); // Xóa trong cơ sở dữ liệu
            if (isDeleted) {
                list.clear();
                list.addAll(dao.getList());
                notifyDataSetChanged();
                Toast.makeText(context, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
