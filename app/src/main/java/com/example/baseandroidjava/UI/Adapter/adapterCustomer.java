package com.example.baseandroidjava.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseandroidjava.databinding.ItemCustomerBinding;
import com.example.baseandroidjava.models.entity.Customer;

import java.util.List;

public class adapterCustomer extends RecyclerView.Adapter<adapterCustomer.ViewHolder> {
    public List<Customer> listCustomer;
    Context context;
    onEventCustomerAdapter monEvent;

    public adapterCustomer(List<Customer> listCustomer, Context context, onEventCustomerAdapter monEvent) {
        this.listCustomer = listCustomer;
        this.context = context;
        this.monEvent = monEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCustomerBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer = listCustomer.get(position);
        holder.binding.txtid.setText(""+customer.getId());
        holder.binding.txtname.setText(customer.getName());
        holder.binding.txtaddress.setText(customer.getAddress());

        holder.binding.itemcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monEvent.onClickItem(listCustomer.get(position), position);
            }
        });

        holder.binding.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              monEvent.onRemoveItem(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listCustomer == null ? 0 :
                listCustomer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCustomerBinding binding;

        public ViewHolder(ItemCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}