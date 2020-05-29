package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import java.io.Serializable;

public class VehicleListItem implements Serializable {
    private String mooveId;
    private  String year;
    private String make;
    private String model;
    private  String vehicleId;
    private  String id;
    private  String mileage;

    public VehicleListItem(String mooveId, String year, String make, String model,String vehicleId,String id, String mileage ) {
        this.mooveId = mooveId;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleId = vehicleId;
        this.id = id;
        this.mileage = mileage;

    }


    public String getMooveId() {
        return mooveId;
    }

    public void setMooveId(String mooveId) {
        this.mooveId = mooveId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
