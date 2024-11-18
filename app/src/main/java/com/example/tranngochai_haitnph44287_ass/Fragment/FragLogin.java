package com.example.tranngochai_haitnph44287_ass.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tranngochai_haitnph44287_ass.DAO.UserDao;
import com.example.tranngochai_haitnph44287_ass.DTO.UserDTO;
import com.example.tranngochai_haitnph44287_ass.MainActivity;
import com.example.tranngochai_haitnph44287_ass.R;

public class FragLogin extends Fragment {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserDao userDao;
    private UserDTO userDTO;

    public FragLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_login, container, false);
        Anhxa(view);
        return view;
        //
    }
    private void Anhxa(View view ){
        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        userDao = new UserDao(getContext());
        userDTO = new UserDTO();
        loginButton.setOnClickListener(v -> Login());
    }
    private void Login(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Kiểm tra xem username và password có trống không
        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(getContext(), "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        } else if (username.isEmpty()) {
            Toast.makeText(getContext(), "Tên đăng nhập không được để trống", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(getContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        userDTO.setUsername(username);
        userDTO.setEmail(username);
        userDTO.setPassword(password);
        if(userDao.login(userDTO)){
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getContext(), "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }

    }

}
