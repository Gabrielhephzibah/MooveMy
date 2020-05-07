package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntakeRuleRequest {

    private IntakeRuleRequest(){
        //don't instantiate
    }

    public  static  class Request{


        @Expose
        @SerializedName("make")
        private String make;

        @Expose
        @SerializedName("model")
        private String model;

        @Expose
        @SerializedName("year")
        private String year;

        @Expose
        @SerializedName("color")
        private String color;

        @Expose
        @SerializedName("VIN")
        private String vin;

        @Expose
        @SerializedName("mileage")
        private String mileage;

        @Expose
        @SerializedName("registration_number")
        private String registrationNumber;


        public Request(String make, String model, String year, String color, String vin, String mileage, String registrationNumber) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.color = color;
            this.vin = vin;
            this.mileage = mileage;
            this.registrationNumber = registrationNumber;
        }


        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public String getYear() {
            return year;
        }

        public String getColor() {
            return color;
        }

        public String getVin() {
            return vin;
        }

        public String getMileage() {
            return mileage;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            IntakeRuleRequest.Request request = (IntakeRuleRequest.Request) obj;

            if (make != null ? !make.equals(request.make) : request.make != null) {
                return false;
            }

            if (model != null ? !model.equals(request.model) : request.model != null) {
                return false;
            }

            if (year != null ? !year.equals(request.year) : request.year != null) {
                return false;
            }

            if (color != null ? !color.equals(request.color) : request.color != null) {
                return false;
            }

            if (vin!= null ? !vin.equals(request.vin) : request.vin != null) {
                return false;
            }

            if (mileage != null ? !mileage.equals(request.mileage) : request.mileage != null) {
                return false;
            }

            return registrationNumber != null ? !registrationNumber.equals(request.registrationNumber) : request.registrationNumber != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (make != null ? make.hashCode() : 0);
            result = 31 * result + (model != null ? model.hashCode() : 0);
            result = 31 * result + (year != null ? year.hashCode() : 0);
            result = 31 * result + (color != null ? color.hashCode() : 0);
            result = 31 * result + (vin != null ? vin.hashCode() : 0);
            result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
            result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
            return result;
        }

    }


}
