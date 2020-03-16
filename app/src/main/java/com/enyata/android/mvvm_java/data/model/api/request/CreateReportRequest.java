package com.enyata.android.mvvm_java.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateReportRequest {

    private CreateReportRequest(){
        //don't instantaiate
    }

    public static class Request{
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
        @SerializedName("registrationNumber")
        private String registrationNumber;

        @Expose
        @SerializedName("signature")
        private List<String> signature;

        @Expose
        @SerializedName("status")
        private String status;

        @Expose
        @SerializedName("comment")
        private String comment;

        @Expose
        @SerializedName("report_type")
        private String reportType;

        @Expose
        @SerializedName("vehicle_part")
        private List<VehiclePart> vehiclePart;


        public Request(String make, String model, String year, String color, String vin, String mileage, String registrationNumber, List<String> signature, String status, String comment, String reportType, List<VehiclePart> vehiclePart) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.color = color;
            this.vin = vin;
            this.mileage = mileage;
            this.registrationNumber = registrationNumber;
            this.signature = signature;
            this.status = status;
            this.comment = comment;
            this.reportType = reportType;
            this.vehiclePart = vehiclePart;
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

        public List<String> getSignature() {
            return signature;
        }

        public String getStatus() {
            return status;
        }

        public String getComment() {
            return comment;
        }

        public String getReportType() {
            return reportType;
        }

        public List<VehiclePart> getVehiclePart() {
            return vehiclePart;
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

            if (vin != null ? !vin.equals(request.vin) : request.vin != null) {
                return false;
            }

            if (mileage != null ? !mileage.equals(request.mileage) : request.mileage != null) {
                return false;
            }

            if (registrationNumber != null ? !registrationNumber.equals(request.registrationNumber) : request.registrationNumber != null) {
                return false;
            }

            if (signature != null ? !signature.equals(request.signature) : request.signature != null) {
                return false;
            }

            if (status != null ? !status.equals(request.status) : request.status != null) {
                return false;
            }

            if (comment != null ? !comment.equals(request.comment) : request.comment != null) {
                return false;
            }

            if (reportType != null ? !reportType.equals(request.reportType) : request.reportType != null) {
                return false;
            }

            return vehiclePart != null ? !vehiclePart.equals(request.vehiclePart) : request.vehiclePart != null;


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
            result = 31 * result + (signature != null ? signature.hashCode() : 0);
            result = 31 * result + (status != null ? status.hashCode() : 0);
            result = 31 * result + (comment != null ? comment.hashCode() : 0);
            result = 31 * result + (reportType != null ? reportType.hashCode() : 0);
            result = 31 * result + (vehiclePart != null ? vehiclePart.hashCode() : 0);

            return result;

        }
    }





}
