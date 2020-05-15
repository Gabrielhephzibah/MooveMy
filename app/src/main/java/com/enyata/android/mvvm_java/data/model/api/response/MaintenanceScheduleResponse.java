package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaintenanceScheduleResponse {
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
    private MaintenanceScheduleData data;

    @Expose
    @SerializedName("count")
    private MaintenanceScheduleCountData count;


    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public MaintenanceScheduleData getData() {
        return data;
    }

    public MaintenanceScheduleCountData getCount() {
        return count;
    }
}
