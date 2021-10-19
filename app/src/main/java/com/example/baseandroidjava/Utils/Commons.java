package com.example.baseandroidjava.Utils;

import com.example.baseandroidjava.models.entity.Customer;
import com.example.baseandroidjava.models.entity.User;

import java.util.List;

public class Commons {
    public static String auth ="";
    public static final String API_BASE_URL = "http://192.168.1.237:8080/";

    public static User user = null;

    public static Customer customer = null;

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String TEACHER = "ROLE_TEACHER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}