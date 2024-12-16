package com.example.tranngochai_haitnph44287_ass.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tranngochai_haitnph44287_ass.R;
import com.example.tranngochai_haitnph44287_ass.Spin;

public class Welcome extends Fragment {

    public Welcome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        Button btnSpin = view.findViewById(R.id.btnSpin);
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang SpinActivity
                Intent intent = new Intent(getActivity(), Spin.class);
                startActivity(intent);
            }
        });

        return view;
    }

}