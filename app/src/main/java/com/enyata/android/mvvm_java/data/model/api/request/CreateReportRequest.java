package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class CreateReportRequest {
        @SerializedName("make")
        @Expose
        private String make;

        @SerializedName("model")
        @Expose
        private String model;

        @SerializedName("year")
        @Expose
        private String year;

        @SerializedName("color")
        @Expose
        private String color;

        @SerializedName("VIN")
        @Expose
        private String vIN;

        @SerializedName("mileage")
        @Expose
        private String mileage;

        @SerializedName("registration_number")
        @Expose
        private String registrationNumber;

        @SerializedName("inspector_signature")
        @Expose
        private String inspectorSignature;

        @SerializedName("supplier_signature")
        @Expose
        private String supplierSignature;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("comment")
        @Expose
        private String comment;

        @SerializedName("report_type")
        @Expose
        private String reportType;

        @SerializedName("vehicle_part")
        @Expose
        private List<VehicleCollection> vehiclePart = null;


    public CreateReportRequest(String make, String model, String year, String color, String vIN, String mileage, String registrationNumber, String inspectorSignature, String supplierSignature,  String status, String comment, String reportType, List<VehicleCollection> vehiclePart) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vIN = vIN;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
       this.supplierSignature = supplierSignature;
       this.inspectorSignature = inspectorSignature;
        this.status = status;
        this.comment = comment;
        this.reportType = reportType;
        this.vehiclePart = vehiclePart;
    }

    public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getVIN() {
            return vIN;
        }

        public void setVIN(String vIN) {
            this.vIN = vIN;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getInspectorSignature() {
            return inspectorSignature;
        }

        public void setInspectorSignature(String inspectorSignature) {
            this.inspectorSignature = inspectorSignature;
        }

        public String getSupplierSignature() {
            return supplierSignature;
        }

        public void setSupplierSignature(String supplierSignature) {
            this.supplierSignature = supplierSignature;
        }

        public String getStatus() {
                return status;
            }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getReportType() {
            return reportType;
        }

        public void setReportType(String reportType) {
            this.reportType = reportType;
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

            CreateReportRequest request = (CreateReportRequest) obj;

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

            if (vIN != null ? !vIN.equals(request.vIN) : request.vIN != null) {
                return false;
            }

            if (mileage != null ? !mileage.equals(request.mileage) : request.mileage != null) {
                return false;
            }

            if (registrationNumber != null ? !registrationNumber.equals(request.registrationNumber) : request.registrationNumber != null) {
                return false;
            }

            if (supplierSignature != null ? !supplierSignature.equals(request.supplierSignature) : request.supplierSignature != null) {
                return false;
            }

        if (inspectorSignature != null ? !inspectorSignature.equals(request.inspectorSignature) : request.inspectorSignature != null) {
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
            result = 31 * result + (vIN != null ? vIN.hashCode() : 0);
            result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
            result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
            result = 31 * result + (inspectorSignature != null ? inspectorSignature.hashCode() : 0);
            result = 31 * result + (supplierSignature != null ? supplierSignature.hashCode() : 0);
            result = 31 * result + (status != null ? status.hashCode() : 0);
            result = 31 * result + (comment != null ? comment.hashCode() : 0);
            result = 31 * result + (reportType != null ? reportType.hashCode() : 0);
            result = 31 * result + (vehiclePart != null ? vehiclePart.hashCode() : 0);

            return result;

        }

    @Override
    public String toString() {
        return "CreateReportRequest{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", vIN='" + vIN + '\'' +
                ", mileage='" + mileage + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", inspectorSignature='" + inspectorSignature + '\'' +
                ", supplierSignature='" + supplierSignature + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", reportType='" + reportType + '\'' +
                ", vehiclePart=" + vehiclePart +
                '}';
    }
}



//    private CreateReportRequest(){
//        //don't instantaiate
//    }
//
//    public static class Request{
//        @Expose
//        @SerializedName("make")
//        private String make;
//
//
//        @Expose
//        @SerializedName("model")
//        private String model;
//
//        @Expose
//        @SerializedName("year")
//        private String year;
//
//
//        @Expose
//        @SerializedName("color")
//        private String color;
//
//        @Expose
//        @SerializedName("VIN")
//        private String vin;
//
//        @Expose
//        @SerializedName("mileage")
//        private String mileage;
//
//
//        @Expose
//        @SerializedName("registrationNumber")
//        private String registrationNumber;
//
//        @Expose
//        @SerializedName("signature")
//        private List<String> signature;
//
//        @Expose
//        @SerializedName("status")
//        private String status;
//
//        @Expose
//        @SerializedName("comment")
//        private String comment;
//
//        @Expose
//        @SerializedName("report_type")
//        private String reportType;
//
//        @Expose
//        @SerializedName("vehicle_part")
//        private List<VehicleCollection> vehiclePart;
//
//
//        public Request(String make, String model, String year, String color, String vin, String mileage, String registrationNumber, List<String> signature, String status, String comment, String reportType, List<VehicleCollection> vehiclePart) {
//            this.make = make;
//            this.model = model;
//            this.year = year;
//            this.color = color;
//            this.vin = vin;
//            this.mileage = mileage;
//            this.registrationNumber = registrationNumber;
//            this.signature = signature;
//            this.status = status;
//            this.comment = comment;
//            this.reportType = reportType;
//            this.vehiclePart = vehiclePart;
//        }
//
//
//        public String getMake() {
//            return make;
//        }
//
//        public String getModel() {
//            return model;
//        }
//
//        public String getYear() {
//            return year;
//        }
//
//        public String getColor() {
//            return color;
//        }
//
//        public String getVin() {
//            return vin;
//        }
//
//        public String getMileage() {
//            return mileage;
//        }
//
//        public String getRegistrationNumber() {
//            return registrationNumber;
//        }
//
//        public List<String> getSignature() {
//            return signature;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public String getComment() {
//            return comment;
//        }
//
//        public String getReportType() {
//            return reportType;
//        }
//
//        public List<VehicleCollection> getVehiclePart() {
//            return vehiclePart;
//        }
//
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//
//            if (obj == null || getClass() != obj.getClass()) {
//                return false;
//            }
//
//            Request request = (Request) obj;
//
//            if (make != null ? !make.equals(request.make) : request.make != null) {
//                return false;
//            }
//
//            if (model != null ? !model.equals(request.model) : request.model != null) {
//                return false;
//            }
//
//            if (year != null ? !year.equals(request.year) : request.year != null) {
//                return false;
//            }
//
//            if (color != null ? !color.equals(request.color) : request.color != null) {
//                return false;
//            }
//
//            if (vin != null ? !vin.equals(request.vin) : request.vin != null) {
//                return false;
//            }
//
//            if (mileage != null ? !mileage.equals(request.mileage) : request.mileage != null) {
//                return false;
//            }
//
//            if (registrationNumber != null ? !registrationNumber.equals(request.registrationNumber) : request.registrationNumber != null) {
//                return false;
//            }
//
//            if (signature != null ? !signature.equals(request.signature) : request.signature != null) {
//                return false;
//            }
//
//            if (status != null ? !status.equals(request.status) : request.status != null) {
//                return false;
//            }
//
//            if (comment != null ? !comment.equals(request.comment) : request.comment != null) {
//                return false;
//            }
//
//            if (reportType != null ? !reportType.equals(request.reportType) : request.reportType != null) {
//                return false;
//            }
//
//            return vehiclePart != null ? !vehiclePart.equals(request.vehiclePart) : request.vehiclePart != null;
//
//
//        }
//
//
//
//
//        @Override
//        public int hashCode() {
//            int result = 0;
//            result = 31 * result + (make != null ? make.hashCode() : 0);
//            result = 31 * result + (model != null ? model.hashCode() : 0);
//            result = 31 * result + (year != null ? year.hashCode() : 0);
//            result = 31 * result + (color != null ? color.hashCode() : 0);
//            result = 31 * result + (vin != null ? vin.hashCode() : 0);
//            result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
//            result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
//            result = 31 * result + (signature != null ? signature.hashCode() : 0);
//            result = 31 * result + (status != null ? status.hashCode() : 0);
//            result = 31 * result + (comment != null ? comment.hashCode() : 0);
//            result = 31 * result + (reportType != null ? reportType.hashCode() : 0);
//            result = 31 * result + (vehiclePart != null ? vehiclePart.hashCode() : 0);
//
//            return result;
//
//        }

//        @Override
//        public String toString() {
//            return "intakeReport{" +
//                    "firstname='" + make + '\'' +
//                    ", lastname='" + model + '\'' +
//                    ", phoneNumber='" + year + '\'' +
//                    ", email='" + color + '\'' +
//                    ", address='" + vin + '\'' +
//                    "firstname='" + mileage + '\'' +
//                    ", lastname='" + registrationNumber + '\'' +
//                    ", phoneNumber='" + signature + '\'' +
//                    ", email='" + status+ '\'' +
//                    ", address='" + comment + '\'' +
//                    "firstname='" + reportType + '\'' +
//                    ", lastname='" + vehiclePart + '\'' +
//                    '}';
//        }









