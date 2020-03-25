package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VinResponseData {
    @Expose
    @SerializedName("message")
    private VinMessage message;

    @Expose
    @SerializedName("data")
    private VinData data;


    public VinMessage getMessage() {
        return message;
    }

    public void setMessage(VinMessage message) {
        this.message = message;
    }

    public VinData getData() {
        return data;
    }

    public void setData(VinData data) {
        this.data = data;
    }
}
