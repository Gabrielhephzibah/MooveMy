package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleRepairReport {

    @SerializedName("supervisor_signature")
    @Expose
    private String supervisorSignature;

    @SerializedName("mechanic_signature")
    @Expose
    private String mechanicSignature;

    @SerializedName("vehicle_parts_reports")
    @Expose
    private List<VehiclePartRepair> vehiclePartsRepair = null;


    public VehicleRepairReport(String supervisorSignature, String mechanicSignature, List<VehiclePartRepair> vehiclePartsRepair) {
        this.supervisorSignature = supervisorSignature;
        this.mechanicSignature = mechanicSignature;
        this.vehiclePartsRepair = vehiclePartsRepair;
    }



    public String getSupervisorSignature() {
        return supervisorSignature;
    }

    public void setSupervisorSignature(String supervisorSignature) {
        this.supervisorSignature = supervisorSignature;
    }

    public String getMechanicSignature() {
        return mechanicSignature;
    }

    public void setMechanicSignature(String mechanicSignature) {
        this.mechanicSignature = mechanicSignature;
    }

    public List<VehiclePartRepair> getVehiclePartsReports() {
        return vehiclePartsRepair;
    }

    public void setVehiclePartsReports(List<VehiclePartRepair> vehiclePartsReports) {
        this.vehiclePartsRepair = vehiclePartsReports;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        VehicleRepairReport request = (VehicleRepairReport) obj;

        if (supervisorSignature != null ? !supervisorSignature.equals(request.supervisorSignature) : request.supervisorSignature != null) {
            return false;
        }

        if (mechanicSignature != null ? !mechanicSignature.equals(request.mechanicSignature) : request.mechanicSignature != null) {
            return false;
        }

        return vehiclePartsRepair != null ? !vehiclePartsRepair.equals(request.vehiclePartsRepair) : request.vehiclePartsRepair != null;


    }



    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (supervisorSignature != null ? supervisorSignature.hashCode() : 0);
        result = 31 * result + (mechanicSignature != null ? mechanicSignature.hashCode() : 0);
        result = 31 * result + (vehiclePartsRepair != null ? vehiclePartsRepair.hashCode() : 0);

        return result;

    }




}

