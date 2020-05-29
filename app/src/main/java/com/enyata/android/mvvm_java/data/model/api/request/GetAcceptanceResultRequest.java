package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAcceptanceResultRequest {

    @Expose
    @SerializedName("vehicle_parts")
    private List<VehicleCollection> vehiclePart;

    public GetAcceptanceResultRequest(List<VehicleCollection> vehiclePart) {
        this.vehiclePart = vehiclePart;
    }

    public List<VehicleCollection> getVehiclePart() {
        return vehiclePart;
    }

    public void setVehiclePart(List<VehicleCollection> vehiclePart) {
        this.vehiclePart = vehiclePart;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GetAcceptanceResultRequest request = (GetAcceptanceResultRequest) obj;

        return vehiclePart != null ? !vehiclePart.equals(request.vehiclePart) : request.vehiclePart != null;

    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (vehiclePart != null ? vehiclePart.hashCode() : 0);
        return result;

    }

    @Override
    public String toString() {
        return "GetAcceptanceResultRequest{" +
                "vehiclePart=" + vehiclePart +
                '}';
    }
}
