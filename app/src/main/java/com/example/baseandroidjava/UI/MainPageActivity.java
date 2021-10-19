package com.example.baseandroidjava.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baseandroidjava.UI.Adapter.adapterCustomer;
import com.example.baseandroidjava.UI.Adapter.onEventCustomerAdapter;
import com.example.baseandroidjava.Utils.Commons;
import com.example.baseandroidjava.Utils.EndlessRecyclerViewScrollListener;
import com.example.baseandroidjava.Utils.Progressdialog;
import com.example.baseandroidjava.Utils.SharedPref;
import com.example.baseandroidjava.ViewModel.CustomerViewModel;
import com.example.baseandroidjava.ViewModel.loginViewModel;
import com.example.baseandroidjava.databinding.ActivityLoginBinding;
import com.example.baseandroidjava.databinding.ActivityMainPageBinding;
import com.example.baseandroidjava.models.entity.Customer;
import com.example.baseandroidjava.models.response.BaseResponse;
import com.example.baseandroidjava.models.response.Content;
import com.example.baseandroidjava.models.response.CustomerRes;
import com.example.baseandroidjava.models.response.CustomerResponse;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity implements onEventCustomerAdapter {
    ActivityMainPageBinding binding;
    CustomerViewModel customerViewModel;
    adapterCustomer adapterCustomer;
    List<Customer> listCustomer = new ArrayList<>();
    int pos = 0;

    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        SharedPref.init(getApplicationContext());
        toolbarEvent();
        loadList();
        addOrEdit();
    }

    private void addOrEdit() {
        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtname.getText().toString();
                String address = binding.edtaddress.getText().toString();
                Customer customer = new Customer(name,address);
                customerViewModel.createCustomer(customer).observe(MainPageActivity.this, new Observer<CustomerRes<Customer>>() {
                    @Override
                    public void onChanged(CustomerRes<Customer> customerCustomerRes) {
                        listCustomer.add(customerCustomerRes.getData());
                        adapterCustomer.notifyItemChanged(listCustomer.size());
                    }
                });
            }
        });

        binding.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = Commons.customer;
                customer.setName(binding.edtname.getText().toString());
                customer.setAddress(binding.edtaddress.getText().toString());
                customerViewModel.updateCustomer(customer).observe(MainPageActivity.this, new Observer<BaseResponse>() {
                    @Override
                    public void onChanged(BaseResponse baseResponse) {
                        Toast.makeText(MainPageActivity.this, "đã update thành công", Toast.LENGTH_SHORT).show();
                        listCustomer.set(pos,customer);
                        adapterCustomer.notifyItemChanged(pos);
                    }
                });
            }
        });
    }


    private void loadList() {
        adapterCustomer = new adapterCustomer(listCustomer, this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycleView.setAdapter(adapterCustomer);
        binding.recycleView.setLayoutManager(linearLayoutManager);
        loadNextDataFromApi(0);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        binding.recycleView.addOnScrollListener(scrollListener);
    }

    public void loadNextDataFromApi(int offset) {
        Progressdialog progressdialog = new Progressdialog();
        progressdialog.showDialog("đang load",this);
        customerViewModel.loadCustomer(offset, 10, "DESC").observe(this, new Observer<CustomerResponse>() {
            @Override
            public void onChanged(CustomerResponse customerResponse) {
                List<Content> listct = customerResponse.getContent();
                for (Content content : listct) {
                    listCustomer.add(new Customer(content.getId(),content.getName(),content.getAddress()));
                }
                adapterCustomer.notifyDataSetChanged();
                progressdialog.dismissDialog();
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

    @Override
    public void onClickItem(Customer customer, int position) {
        pos = position;
        Commons.customer = customer;
        Toast.makeText(this, customer.getName(), Toast.LENGTH_SHORT).show();
        binding.edtname.setText(customer.getName());
        binding.edtaddress.setText(customer.getAddress());
//        startActivity(new Intent(getActivity(), reportActivity.class));
    }

    @Override
    public void onRemoveItem(int position) {
        customerViewModel.deleteCustomer(listCustomer.get(position).getId()).observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                listCustomer.remove(position);
                adapterCustomer.notifyItemRemoved(position);
                adapterCustomer.notifyItemRangeChanged(position, listCustomer.size() - position);
                Toast.makeText(MainPageActivity.this, "đã xóa ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}