package com.example.tranngochai_haitnph44287_ass.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tranngochai_haitnph44287_ass.Adapter.CongViecAdapter;
import com.example.tranngochai_haitnph44287_ass.DAO.CongViecDao;
import com.example.tranngochai_haitnph44287_ass.DTO.CongViecDTO;
import com.example.tranngochai_haitnph44287_ass.MainActivity;
import com.example.tranngochai_haitnph44287_ass.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


public class QL_CongViec extends Fragment {
    CongViecDTO congViecDTO;
    CongViecAdapter congViecAdapter;
    CongViecDao congViecDAO;
    ArrayList<CongViecDTO> list = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    int newStatus;


    public QL_CongViec() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_q_l__cong_viec, container, false);
        AnhXa(v);
        XuLySuKien();
        return v;

    }
    private void XuLySuKien(){
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }
    private void AnhXa(View  v){
        recyclerView = v.findViewById(R.id.lv_congviec);
        actionButton = v.findViewById(R.id.btn_add);
        congViecDTO = new CongViecDTO();
        congViecDAO = new CongViecDao(getContext());
        list = congViecDAO.getList();
        congViecAdapter = new CongViecAdapter(getContext(),list);
        recyclerView.setAdapter(congViecAdapter);

    }
    private void showAddDialog() {
        // Tạo dialog từ layout
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
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

        llStatus.setVisibility(View.GONE); // Ẩn hoàn toàn và giải phóng không gian


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
        spStatus.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listStatus));
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
                        getContext(),
                        (v, selectedYear, selectedMonth, selectedDay) -> {
                            // Xử lý khi người dùng chọn ngày
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            edtStartDate.setText(selectedDate);

                            // Hiển thị thông báo
//                            Toast.makeText(getContext(), "Bạn đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
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
                        getContext(),
                        (v, selectedYear, selectedMonth, selectedDay) -> {
                            // Xử lý khi người dùng chọn ngày
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            edtEndDate.setText(selectedDate);

                            // Hiển thị thông báo
//                            Toast.makeText(getContext(), "Bạn đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật thông tin sản phẩm
            congViecDTO.setName(newName);
            congViecDTO.setContent(newContent);
            congViecDTO.setStatus(newStatus);
            congViecDTO.setStart_date(newStartDate);
            congViecDTO.setEnd_date(newEndDate);

            int isADD = congViecDAO.addRow(congViecDTO);
            if (isADD > 0) {
                showNotificationAdd("Thêm Công Việc", "Thêm Công Việc thành công");
                list.clear();
                list.addAll(congViecDAO.getList());
                congViecAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Đã thêm Công Việc", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Thêm Công Việc thất bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
    }
    private void showNotificationAdd(String title, String message) {
        // Tạo kênh thông báo (chỉ cần thực hiện một lần, nếu đã có thì bỏ qua bước này)
        String channelId = "job_channel";
        String channelName = "Job Notifications";
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Thông báo khi thêm công việc");
            notificationManager.createNotificationChannel(channel);
        }

        // Tạo nội dung Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Thay bằng icon của bạn
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Đặt mức độ ưu tiên cao
                .setAutoCancel(true); // Tự động hủy thông báo khi người dùng nhấn vào
        // Hiển thị Notification
        notificationManager.notify(2, builder.build());
    }

}