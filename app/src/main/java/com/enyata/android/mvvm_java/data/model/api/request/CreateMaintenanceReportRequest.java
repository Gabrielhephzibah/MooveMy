package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceListData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateMaintenanceReportRequest {
        @Expose
        @SerializedName("vehicle_id")
        private String vehicleId;

        @Expose
        @SerializedName("data")
        private List<MaintenanceListData> data;

        public CreateMaintenanceReportRequest(String vehicleId, List<MaintenanceListData> data) {
            this.vehicleId = vehicleId;
            this.data = data;
        }


        public String getVehicleId() {
            return vehicleId;
        }

        public List<MaintenanceListData> getData() {
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

            CreateMaintenanceReportRequest request = (CreateMaintenanceReportRequest) obj;

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
