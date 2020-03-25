package com.enyata.android.mvvm_java.data.model.api.myData;

import android.util.Log;

import com.enyata.android.mvvm_java.data.model.api.request.VehiclePart;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class RepairReportArray {
    @Expose
    @SerializedName("vehicle_parts_reports")
    private List<VehiclePartRepair> partRepair;


    public RepairReportArray(List<VehiclePartRepair> partRepair) {
        this.partRepair = partRepair;
    }

    public List<VehiclePartRepair> getPartRepair() {
        return partRepair;
    }

    public void setPartRepair(List<VehiclePartRepair> partRepair) {
        this.partRepair = partRepair;
    }

    @Override
    public String toString() {
        return "RepairPart{" +
                "part='" + partRepair + '\'' +
                '}';
    }


    public boolean isRepairArrayEmpty() {
        if (partRepair.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void addNewObject(VehiclePartRepair vehicle) {
        partRepair.add(vehicle);
    }

    public boolean arrayContainsObject(VehiclePartRepair vehiclePart) {
        if (arrayContainsObject(vehiclePart)) {
            return true;
        } else {
            return false;
        }
    }

    public void addToArray(VehiclePartRepair vehiclePartRepair) {
        if (isRepairArrayEmpty()) {
            partRepair.add(vehiclePartRepair);
        } else {
            for (int i = 0; i < partRepair.size(); i++) {
                if (partRepair.get(i).getPart().equals(vehiclePartRepair.getPart())) {
                    partRepair.get(i).setComment(vehiclePartRepair.getComment());
                    Log.i("part", vehiclePartRepair.getPart());
                    Log.i("NEW NEW", String.valueOf(partRepair));
                    break;
                }
            }


        }
    }
}
