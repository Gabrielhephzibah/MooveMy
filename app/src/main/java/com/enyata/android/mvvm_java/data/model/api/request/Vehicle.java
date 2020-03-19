package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vehicle {

    @Expose
    @SerializedName("vehicle_part")
    private List<VehicleCollection> vehiclePart;

    public Vehicle(List<VehicleCollection> vehiclePart) {
        this.vehiclePart = vehiclePart;
    }

    public List<VehicleCollection> getVehiclePart() {
        return vehiclePart;
    }

    public void setVehiclePart(List<VehicleCollection> vehiclePart) {
        this.vehiclePart = vehiclePart;
    }
}
