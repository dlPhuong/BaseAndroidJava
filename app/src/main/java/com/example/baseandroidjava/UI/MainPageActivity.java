package com.example.baseandroidjava.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.baseandroidjava.Utils.SharedPref;
import com.example.baseandroidjava.ViewModel.CustomerViewModel;
import com.example.baseandroidjava.ViewModel.loginViewModel;
import com.example.baseandroidjava.databinding.ActivityLoginBinding;
import com.example.baseandroidjava.databinding.ActivityMainPageBinding;
import com.example.baseandroidjava.models.response.CustomerResponse;

public class MainPageActivity extends AppCompatActivity {
    ActivityMainPageBinding binding;
    CustomerViewModel customerViewModel;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        SharedPref.init(getApplicationContext());
        toolbarEvent();

        customerViewModel.loadCustomer(page, 10, "DESC").observe(this, new Observer<CustomerResponse>() {
            @Override
            public void onChanged(CustomerResponse customerResponse) {
                System.out.println(customerResponse);
            }
        });

    }

    private void toolbarEvent() {
        binding.toolbar.toolbarTitle.setText("mainpage");
        binding.toolbar.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}