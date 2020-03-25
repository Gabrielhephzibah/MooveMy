package com.enyata.android.mvvm_java.data.remote;

import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.VehicleRepairReport;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;

import java.util.Observable;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("vehiclereports/reports")
    Flowable<CreateReportResponse> savePost(@Header("Authorization") String authorization, @Body CreateReportRequest collection);

    @POST("vehiclereports/reports/repairs/{vehicle_id}")
    Flowable<VehicleRepairReport>repairReport(@Header("Authorization")String authorization, @Path("vehicle_id") String VehicleId, @Body VehicleRepairReport repairReport);



}
