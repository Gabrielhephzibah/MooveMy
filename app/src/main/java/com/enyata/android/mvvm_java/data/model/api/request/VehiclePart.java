package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehiclePart {


    @Expose
    @SerializedName("part")
    private String part;

    @Expose
    @SerializedName("image_url")
    private String[] imageUrl;

    @Expose
    @SerializedName("remark")
    private String remark;

    public VehiclePart(String part, String[] imageUrl, String remark) {
        this.part = part;
        this.imageUrl = imageUrl;
        this.remark = remark;
    }


    public String getPart() {
        return part;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Request{" +
                "part='" + part + '\'' +
                ", imageUrl=" + Arrays.toString(imageUrl) +
                ", remark='" + remark + '\'' +
                '}';
    }


}
