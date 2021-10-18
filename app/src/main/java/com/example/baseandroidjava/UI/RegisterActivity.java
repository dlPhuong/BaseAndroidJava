package com.example.baseandroidjava.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.baseandroidjava.Utils.SharedPref;
import com.example.baseandroidjava.ViewModel.loginViewModel;
import com.example.baseandroidjava.databinding.ActivityLoginBinding;
import com.example.baseandroidjava.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
        ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SharedPref.init(getApplicationContext());

        binding.toolbar.toolbarTitle.setText("đăng ký tài khoản");
        binding.toolbar.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}