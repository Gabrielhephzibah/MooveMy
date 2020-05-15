package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceRoutineListData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateMaintenanceReportRequest {

    private CreateMaintenanceReportRequest(){

    }

    public  static  class  Request{

        @Expose
        @SerializedName("vehicle_id")
        private String vehicleId;

        @Expose
        @SerializedName("data")
        private List<MaintenanceRoutineListData> data;

        public Request(String vehicleId, List<MaintenanceRoutineListData> data) {
            this.vehicleId = vehicleId;
            this.data = data;
        }


        public String getVehicleId() {
            return vehicleId;
        }

        public List<MaintenanceRoutineListData> getData() {
            return data;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Request request = (Request) obj;

            if (vehicleId != null ? !vehicleId.equals(request.vehicleId) : request.vehicleId != null) {
                return false;
            }

            return data != null ? !data.equals(request.data) : request.data != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (vehicleId != null ? vehicleId.hashCode() : 0);
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }
    }



}
