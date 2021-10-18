package com.example.baseandroidjava.models.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("address")
@Expose
private String address;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

    @Override
    public String toString() {
        return id+"-"+name+"-"+address;
    }
}