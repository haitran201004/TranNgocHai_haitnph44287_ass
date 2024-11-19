package com.example.tranngochai_haitnph44287_ass.Fragment;

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
import com.example.tranngochai_haitnph44287_ass.R;


public class FragRegister extends Fragment {
    EditText edit_usename, edit_email, edit_password, edit_fullname;
    Button btn_register;
    UserDao dao;
    UserDTO userDTO;


    public FragRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_register, container, false);

        Anhxa(v);
        return v;
    }
    private void Anhxa(View v){
        edit_usename = v.findViewById(R.id.edit_usename);
        edit_email = v.findViewById(R.id.edit_email);
        edit_password = v.findViewById(R.id.edit_password);
        edit_fullname = v.findViewById(R.id.edit_fullname);
        btn_register = v.findViewById(R.id.btn_register);
        dao = new UserDao(getContext());
        userDTO = new UserDTO();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
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
            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        }
    }

}