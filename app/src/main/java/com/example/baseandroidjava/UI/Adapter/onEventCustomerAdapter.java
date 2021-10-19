package com.example.baseandroidjava.UI.Adapter;

import com.example.baseandroidjava.models.entity.Customer;

public interface onEventCustomerAdapter {
    void onClickItem(Customer customer, int position);
    void onRemoveItem(int position);
}