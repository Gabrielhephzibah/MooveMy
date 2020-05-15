package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaintenanceScheduleData {
    @Expose
    @SerializedName("Engine Oil")
    private String engineOil;

    @Expose
    @SerializedName("Oil Filter")
    private String oilFilter;

    @Expose
    @SerializedName("Air Filter")
    private String airFilter;

    @Expose
    @SerializedName("Pollen(A/C) Filter")
    private String pollenFilter;

    @Expose
    @SerializedName("Brake Pad/Disc/Caliper")
    private String brakePad;

    @Expose
    @SerializedName("Suspension System")
    private String suspensionSystem;

    @Expose
    @SerializedName("Various Belts")
    private String variousBelts;

    @Expose
    @SerializedName("Spark Plug")
    private String sparkPlug;

    @Expose
    @SerializedName("Fuel Filter")
    private String fuelFilter;

    @Expose
    @SerializedName("Engine Cooling System")
    private String EngineCoolingSystem;

    @Expose
    @SerializedName("Wiper Blades/Washer")
    private String wiperBlades;

    @Expose
    @SerializedName("Charging System")
    private String chargingSystem;

    @Expose
    @SerializedName("Power Steering Fluid")
    private String powerSteeringFluid;

    @Expose
    @SerializedName("Transmission Oil")
    private String transmissionOil;

    @Expose
    @SerializedName("Transmission Filter")
    private String transmissionFilter;

    @Expose
    @SerializedName("Brake Fluid")
    private String brakeFluid;

    @Expose
    @SerializedName("Fuel System")
    private String fuelSystem;

    @Expose
    @SerializedName("Tyre Rotation")
    private String tyreRotation;

    @Expose
    @SerializedName("Alignment")
    private String alignment;

    @Expose
    @SerializedName("Air Flow System")
    private String airFlow;

    @Expose
    @SerializedName("Air Conditioning System")
    private String airConditioningSystem;

    @Expose
    @SerializedName("Electronic/Ligthng")
    private String electronic;

    @Expose
    @SerializedName("Exhaust Pipe")
    private String exhaustPipe;

    @Expose
    @SerializedName("Tyre Inflation/Tread")
    private String tireInflation;

    @Expose
    @SerializedName("Wheel Rim")
    private String wheelRim;


    public String getEngineOil() {
        return engineOil;
    }

    public String getOilFilter() {
        return oilFilter;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public String getPollenFilter() {
        return pollenFilter;
    }

    public String getBrakePad() {
        return brakePad;
    }

    public String getSuspensionSystem() {
        return suspensionSystem;
    }

    public String getVariousBelts() {
        return variousBelts;
    }

    public String getSparkPlug() {
        return sparkPlug;
    }

    public String getFuelFilter() {
        return fuelFilter;
    }

    public String getEngineCoolingSystem() {
        return EngineCoolingSystem;
    }

    public String getWiperBlades() {
        return wiperBlades;
    }

    public String getChargingSystem() {
        return chargingSystem;
    }

    public String getPowerSteeringFluid() {
        return powerSteeringFluid;
    }

    public String getTransmissionOil() {
        return transmissionOil;
    }

    public String getTransmissionFilter() {
        return transmissionFilter;
    }

    public String getBrakeFluid() {
        return brakeFluid;
    }

    public String getFuelSystem() {
        return fuelSystem;
    }

    public String getTyreRotation() {
        return tyreRotation;
    }

    public String getAlignment() {
        return alignment;
    }

    public String getAirFlow() {
        return airFlow;
    }

    public String getAirConditioningSystem() {
        return airConditioningSystem;
    }

    public String getElectronic() {
        return electronic;
    }

    public String getExhaustPipe() {
        return exhaustPipe;
    }

    public String getTireInflation() {
        return tireInflation;
    }

    public String getWheelRim() {
        return wheelRim;
    }
}
