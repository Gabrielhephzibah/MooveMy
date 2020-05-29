package com.enyata.android.mvvm_java.data.model.api.myData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MaintenanceListData {


    @SerializedName("vehicle_system")
    @Expose
    private String vehicleSystem;

    @SerializedName("routine_activity")
    @Expose
    private String routineActivity;

    public MaintenanceListData(String vehicleSystem, String routineActivity) {
        this.vehicleSystem = vehicleSystem;
        this.routineActivity = routineActivity;
    }

    public String getVehicleSystem() {
        return vehicleSystem;
    }

    public String getRoutineActivity() {
        return routineActivity;
    }

    public void setVehicleSystem(String vehicleSystem) {
        this.vehicleSystem = vehicleSystem;
    }

    public void setRoutineActivity(String routineActivity) {
        this.routineActivity = routineActivity;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceListData request = (MaintenanceListData) o;
        return Objects.equals(vehicleSystem, request.vehicleSystem) &&
                Objects.equals(routineActivity, request.routineActivity);

    }


    @Override
    public int hashCode() {
        int result = Objects.hash(vehicleSystem);
        result = 31 * result + Objects.hashCode(routineActivity);

        return result;
    }

    @Override
    public String toString() {
        return "VehicleMaintenanceListData{" +
                "vehicleSystem='" + vehicleSystem + '\'' +
                ", routineActivity='" + routineActivity + '\'' +
                '}';
    }
}
