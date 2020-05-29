package com.enyata.android.mvvm_java.data.remote;

import android.util.Log;

import com.enyata.android.mvvm_java.data.model.api.myData.VinHeaders;
import com.enyata.android.mvvm_java.data.model.api.request.CheckIntakeRequest;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.IntakeRuleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.RegNumberCheckRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailData;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleResponse;
import com.enyata.android.mvvm_java.data.model.api.response.VinData;
import com.enyata.android.mvvm_java.data.model.api.response.VinResponseData;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class AppApiHelper implements  ApiHelper  {
    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<LoginResponse>loginInspector(LoginRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_INSPECTOR)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<CreateReportResponse> createIntakeReport(CreateReportRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.INTAKE_CREATE_REPORT)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);
    }

    @Override
    public Flowable<InspectorListResponse> getInspectorHistory() {
        return Rx2AndroidNetworking.get(ApiEndPoint.INSPECTOR_LIST)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(InspectorListResponse.class);

    }

    @Override
    public Flowable<InspectorDetailReport> getInspectorDetail(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.INSPECTOR_DETAILS_REPORT + "/" + id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(InspectorDetailReport.class);
    }

    @Override
    public Flowable<VinResponseData> getVinData(String vinNo) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("accept","application/json");
        headers.put("accept-encoding","gzip, deflate");
        headers.put("accept-language","en-US,en;q=0.8");
        headers.put("authorization","Basic NzgxN2ZhNWUtZDgxZS00Nzg5LWExMmEtZTI1YTAzYjU2NDMw");
        headers.put("partner-token","e117fbea0a9445fc94449a59874f76db");
        return Rx2AndroidNetworking.get(ApiEndPoint.VIN_API + vinNo)
                .addHeaders(headers)
                .build()
                .getObjectFlowable(VinResponseData.class);
    }

    @Override
    public Single<CreateReportResponse> checkRegNo(RegNumberCheckRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.REG_NUMBER_CHECK)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);

    }

    @Override
    public Single<CreateReportResponse> checkIntakeRule(IntakeRuleRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.INTAKE_RULE_CHECK)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);

    }

    @Override
    public Flowable<InspectorListResponse>getMonthlyVehicleList() {
        return Rx2AndroidNetworking.get(ApiEndPoint.MONTHLY_VEHICLE_LIST)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(InspectorListResponse.class);
    }

    @Override
    public Flowable<MaintenanceScheduleResponse> getMaintenanceSchedule(MaintenanceScheduleRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.MAINTENANCE_SCHEDULE)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(MaintenanceScheduleResponse.class);
    }

    @Override
    public Single<CreateReportResponse> checkIntakeReport(CheckIntakeRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CHECK_INTAKE_REPORT)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);
    }

    @Override
    public Single<CreateReportResponse> checkMonthlyReport(String vehicleId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CHECK_MONTHLY_REPORT + "/" + vehicleId + "/" + "check" )
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);
    }

    @Override
    public Single<CreateReportResponse> checkMaintenanceReport(String vehicleId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CHECK_MAINTENANCE_REPORT + "/" + vehicleId + "/" + "check" )
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);
    }

    @Override
    public Single<CreateReportResponse> checkRepairsReport(String vehicleId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CHECK_REPAIRS_REPORT + "/" + vehicleId + "/" + "check" )
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CreateReportResponse.class);
    }


}
