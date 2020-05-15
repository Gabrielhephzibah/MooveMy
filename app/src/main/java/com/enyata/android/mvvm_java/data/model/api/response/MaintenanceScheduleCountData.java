package com.enyata.android.mvvm_java.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaintenanceScheduleCountData {

    @Expose
    @SerializedName("current_mileage")
    private String currentMileage;

    @Expose
    @SerializedName("initial_mileage")
    private String initialMileage;


    public String getCurrentMileage() {
        return currentMileage;
    }

    public String getInitialMileage() {
        return initialMileage;
    }
}
