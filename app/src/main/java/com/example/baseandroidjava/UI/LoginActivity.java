package com.example.baseandroidjava.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baseandroidjava.R;
import com.example.baseandroidjava.Utils.Commons;
import com.example.baseandroidjava.Utils.SharedPref;
import com.example.baseandroidjava.ViewModel.loginViewModel;
import com.example.baseandroidjava.databinding.ActivityLoginBinding;
import com.example.baseandroidjava.models.request.LoginRequest;
import com.example.baseandroidjava.models.response.TokenResponse;

public class LoginActivity extends AppCompatActivity {
    loginViewModel loginViewModel;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        loginViewModel = new ViewModelProvider(this).get(loginViewModel.class);
        SharedPref.init(getApplicationContext());

        binding.edtUser.setText("" + SharedPref.read(SharedPref.USER, "admin"));
        binding.edtPass.setText("" + SharedPref.read(SharedPref.PASS, "admin"));

        loginhandle();
        registerhandle();
    }

    private void registerhandle() {
        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginhandle() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest request = new LoginRequest(binding.edtUser.getText().toString(), binding.edtPass.getText().toString(), false);
                loginViewModel.postLogin(request).observe(LoginActivity.this, new Observer<TokenResponse>() {
                    @Override
                    public void onChanged(TokenResponse tokenResponse) {
                        if (tokenResponse.getIdToken().length() > 10) {
                            Commons.auth = "Bearer " + tokenResponse.getIdToken();
                            SharedPref.write(SharedPref.USER,binding.edtUser.getText().toString());
                            SharedPref.write(SharedPref.PASS,binding.edtPass.getText().toString());
                            startActivity(new Intent(LoginActivity.this, MainPageActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "sai tên đăng nhập hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

}