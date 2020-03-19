package com.enyata.android.mvvm_java.data.remote;

import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;

import java.util.Observable;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("vehicle/reports")
    Flowable<CreateReportRequest> savePost(@Header("Authorization") String authorization, @Body CreateReportRequest collection);
}
