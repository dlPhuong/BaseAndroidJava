package com.example.baseandroidjava.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.CDATASection;

public class CustomerRes<V> extends BaseResponse {

    @SerializedName("data")
    @Expose
    private V data;

    public CustomerRes(String status, String mess, V data) {
        super(status, mess);
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }
}
