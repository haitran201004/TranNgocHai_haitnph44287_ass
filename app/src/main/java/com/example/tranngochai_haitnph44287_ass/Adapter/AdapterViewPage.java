package com.example.tranngochai_haitnph44287_ass.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tranngochai_haitnph44287_ass.Fragment.FragLogin;
import com.example.tranngochai_haitnph44287_ass.Fragment.FragRegister;

public class AdapterViewPage extends FragmentStateAdapter {

    FragLogin fragLogin;
    FragRegister fragRegister;

    public AdapterViewPage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragLogin = new FragLogin();
        fragRegister = new FragRegister();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return fragRegister;
            default:
                return fragLogin;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
