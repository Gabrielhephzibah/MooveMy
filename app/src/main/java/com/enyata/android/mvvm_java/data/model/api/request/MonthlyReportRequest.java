package com.enyata.android.mvvm_java.data.model.api.request;

import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonthlyReportRequest {

    @SerializedName("inspector_signature")
    @Expose
    private String inspectorSignature;

    @SerializedName("driver_signature")
    @Expose
    private String driverSignature;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("report_type")
    @Expose
    private String reportType;

    @SerializedName("acceptance_value")
    @Expose
    private String acceptanceValue;


    @SerializedName("vehicle_part")
    @Expose
    private List<VehicleCollection> vehiclePart = null;


    public MonthlyReportRequest(String inspectorSignature, String driverSignature, String status, String comment, String reportType, String acceptanceValue, List<VehicleCollection> vehiclePart) {
        this.inspectorSignature = inspectorSignature;
        this.driverSignature = driverSignature;
        this.status = status;
        this.comment = comment;
        this.reportType = reportType;
        this.vehiclePart = vehiclePart;
        this.acceptanceValue = acceptanceValue;
    }

    public String getInspectorSignature() {
        return inspectorSignature;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }

    public String getDriverSignature() {
        return driverSignature;
    }

    public void setDriverSignature(String driverSignature) {
        this.driverSignature = driverSignature;
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

    public String getAcceptanceValue() {
        return acceptanceValue;
    }

    public void setAcceptanceValue(String acceptanceValue) {
        this.acceptanceValue = acceptanceValue;
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

        MonthlyReportRequest request = (MonthlyReportRequest) obj;

        if (driverSignature != null ? !driverSignature.equals(request.driverSignature) : request.driverSignature != null) {
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
        if (acceptanceValue != null ? !acceptanceValue.equals(request.acceptanceValue) : request.acceptanceValue!= null) {
            return false;
        }

        return vehiclePart != null ? !vehiclePart.equals(request.vehiclePart) : request.vehiclePart != null;


    }




    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (inspectorSignature != null ? inspectorSignature.hashCode() : 0);
        result = 31 * result + (driverSignature != null ? driverSignature.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (reportType != null ? reportType.hashCode() : 0);
        result = 31 * result + (acceptanceValue != null ? acceptanceValue.hashCode() : 0);
        result = 31 * result + (vehiclePart != null ? vehiclePart.hashCode() : 0);

        return result;

    }

    @Override
    public String toString() {
        return "CreateReportRequest{" +
                ", inspectorSignature='" + inspectorSignature + '\'' +
                ", supplierSignature='" + driverSignature + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportType='" + acceptanceValue + '\'' +
                ", vehiclePart=" + vehiclePart +
                '}';
    }
}
