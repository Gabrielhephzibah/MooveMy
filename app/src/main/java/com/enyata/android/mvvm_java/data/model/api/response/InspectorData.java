package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InspectorData {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("vehicle_id")
    private String vehicleId;

    @Expose
    @SerializedName("moove_id")
    private String mooveId;

    @Expose
    @SerializedName("make")
    private String make;

    @Expose
    @SerializedName("model")
    private String model;

    @Expose
    @SerializedName("year")
    private String year;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("inspector_comment")
    private String inspectorComment;

    @Expose
    @SerializedName("type")
    private String type;



    public int getId() {
        return id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getMooveId() {
        return mooveId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }

    public String getInspectorComment() {
        return inspectorComment;
    }

    public String getType() {
        return type;
    }
}
