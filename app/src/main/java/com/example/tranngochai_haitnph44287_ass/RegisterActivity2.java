package com.example.tranngochai_haitnph44287_ass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tranngochai_haitnph44287_ass.DAO.UserDao;
import com.example.tranngochai_haitnph44287_ass.DTO.UserDTO;

public class RegisterActivity2 extends AppCompatActivity {
    EditText edit_usename, edit_email, edit_password, edit_fullname;
    Button btn_register;
    UserDao dao;
    UserDTO userDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Anhxa();
    }
    private void Anhxa(){
        edit_usename = findViewById(R.id.edit_usename);
        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        edit_fullname = findViewById(R.id.edit_fullname);
        btn_register = findViewById(R.id.btn_register);
        dao = new UserDao(this);
        userDTO = new UserDTO();
        btn_register.setOnClickListener(v -> Register());
    }
    private void Register(){
        String username = edit_usename.getText().toString();
        String email = edit_email.getText().toString();
        String password = edit_password.getText().toString();
        String fullname = edit_fullname.getText().toString();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setFullname(fullname);

        if(dao.register(userDTO)){
            finish();
        }
    }
}