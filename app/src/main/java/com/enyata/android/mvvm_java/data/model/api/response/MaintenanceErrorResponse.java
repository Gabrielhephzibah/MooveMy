package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaintenanceErrorResponse {

    @Expose
    @SerializedName("responseCode")
    private String responseCode;

    @Expose
    @SerializedName("responseMessage")
    private String responseMessage;


    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
