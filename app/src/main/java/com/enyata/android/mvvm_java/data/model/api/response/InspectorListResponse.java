package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InspectorListResponse {
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
    @SerializedName("data")
    private List<InspectorData> data;

    @Expose
    @SerializedName("count")
    private int count;


    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public List<InspectorData> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }
}
