package com.example.tranngochai_haitnph44287_ass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tranngochai_haitnph44287_ass.DAO.UserDao;
import com.example.tranngochai_haitnph44287_ass.DTO.UserDTO;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserDao userDao;
    private UserDTO userDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Anhxa();

    }
    private void Anhxa(){
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        userDao = new UserDao(this);
        userDTO = new UserDTO();
        loginButton.setOnClickListener(v -> Login());
    }
    private void Login(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Kiểm tra xem username và password có trống không
        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        } else if (username.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Tên đăng nhập không được để trống", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        userDTO.setUsername(username);
        userDTO.setEmail(username);
        userDTO.setPassword(password);
        if(userDao.login(userDTO)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }

    }


}