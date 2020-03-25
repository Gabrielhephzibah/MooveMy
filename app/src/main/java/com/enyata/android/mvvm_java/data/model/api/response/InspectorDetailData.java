package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InspectorDetailData {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("part")
    private String part;

    @Expose
    @SerializedName("class")
    private String classPart;

    @Expose
    @SerializedName("remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getClassPart() {
        return classPart;
    }

    public void setClassPart(String classPart) {
        this.classPart = classPart;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "InspectorDetailData{" +
                "id=" + id +
                ", part='" + part + '\'' +
                ", classPart='" + classPart + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
