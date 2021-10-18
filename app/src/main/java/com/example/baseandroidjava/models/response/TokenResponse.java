package com.example.baseandroidjava.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {

@SerializedName("id_token")
@Expose
private String idToken;

public String getIdToken() {
return idToken;
}

public void setIdToken(String idToken) {
this.idToken = idToken;
}

}