package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MaintenanceScheduleRequest {

    private  MaintenanceScheduleRequest(){
        //do not instantiate
    }

    public  static  class  Request{
        @Expose
        @SerializedName("moove_id")
        private String mooveId;

        @Expose
        @SerializedName("current_mileage")
        private int currentMileage;

        public Request(String mooveId, int currentMileage) {
            this.mooveId = mooveId;
            this.currentMileage = currentMileage;
        }

        public String getMooveId() {
            return mooveId;
        }

        public int getCurrentMileage() {
            return currentMileage;
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

            if (mooveId != null ? !mooveId.equals(request.mooveId) : request.mooveId != null) {
                return false;
            }

            return !Objects.equals(currentMileage, request.currentMileage);
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (mooveId != null ? mooveId.hashCode() : 0);
//            result = 31 * result + (currentMileage != null ? currentMileage.hashCode() : 0);
            return result;
        }
    }
}
