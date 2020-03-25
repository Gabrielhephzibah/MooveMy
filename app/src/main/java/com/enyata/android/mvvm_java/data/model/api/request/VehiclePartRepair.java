package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class VehiclePartRepair {
    @SerializedName("part")
    @Expose
    private String part;

    @SerializedName("comment")
    @Expose
    private String comment;


    public VehiclePartRepair(String part, String comment) {
        this.part = part;
        this.comment = comment;
    }




    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        VehiclePartRepair that = (VehiclePartRepair) object;
        return Objects.equals(getPart(), that.getPart()) &&
                Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (part != null ? part.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VehiclePartRepair{" +
                "part='" + part + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
