package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InspectorDetailReport {
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
    private List<InspectorDetailData> data;

    @Expose
    @SerializedName("count")
    private int count;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InspectorDetailData> getData() {
        return data;
    }

    public void setData(List<InspectorDetailData> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InspectorDetailReport{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}
