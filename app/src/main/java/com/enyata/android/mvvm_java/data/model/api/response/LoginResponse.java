package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("statusCode")
    private String statusCode;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("token")
    private String token;



    @Expose
    @SerializedName("data")
    private LoginData data;

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public LoginData getData() {
        return data;
    }
}
