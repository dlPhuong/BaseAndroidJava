package com.example.baseandroidjava.models.request;

import com.example.baseandroidjava.models.DTO.UserDTO;

public class ManagedUserVM extends UserDTO {

    private String password;

    public ManagedUserVM() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}