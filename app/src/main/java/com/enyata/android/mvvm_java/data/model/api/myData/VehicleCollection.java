package com.enyata.android.mvvm_java.data.model.api.myData;

import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VehicleCollection {


//    @SerializedName("part")
//    @Expose
//    private String part;

    @SerializedName("component")
    @Expose
    private String part;

    @SerializedName("section")
    @Expose
    private String section;

    @SerializedName("image_url")
    @Expose
    private List<String> imageUrl = null;

    @SerializedName("remark")
    @Expose
    private String remark;

    public VehicleCollection(String part, String section, List<String> imageUrl, String remark) {
        this.part = part;
        this.section = section;
        this.imageUrl = imageUrl;
        this.remark = remark;
    }

//    public VehicleCollection(String part, List<String> imageUrl, String remark) {
//        this.part = part;
//        this.imageUrl = imageUrl;
//        this.remark = remark;
//
//    }


    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleCollection request = (VehicleCollection) o;
        return Objects.equals(remark, request.remark) &&
                Objects.equals(imageUrl, request.imageUrl) &&
                Objects.equals(section, request.section) &&
                Objects.equals(part, request.part);

    }


    @Override
    public int hashCode() {
        int result = Objects.hash(remark, section, part);
        result = 31 * result + Objects.hashCode(imageUrl);

        return result;
    }

    @Override
    public String toString() {
        return "VehicleCollection{" +
                "component='" + part + '\'' +
                ", section='" + section + '\'' +
                ", imageUrl=" + imageUrl +
                ", remark='" + remark + '\'' +
                '}';
    }

}







