package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VinData {

    @Expose
    @SerializedName("year")
    private String year;

    @Expose
    @SerializedName("make")
    private String make;

    @Expose
    @SerializedName("model")
    private String model;

    @Expose
    @SerializedName("manufacturer")
    private String manufacturer;

    @Expose
    @SerializedName("engine")
    private String engine;

    @Expose
    @SerializedName("trim")
    private String trim;

    @Expose
    @SerializedName("transmission")
    private String transmission;


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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
