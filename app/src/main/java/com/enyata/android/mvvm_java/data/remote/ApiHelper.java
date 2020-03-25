package com.enyata.android.mvvm_java.data.remote;

import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.model.api.response.VinResponseData;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ApiHelper {
    ApiHeader getApiHeader();
    Single<LoginResponse>loginInspector(LoginRequest.Request request);

    Single<CreateReportResponse>createIntakeReport(CreateReportRequest request);

    Flowable<InspectorListResponse>getInspectorHistory();

    Flowable<InspectorDetailReport>getInspectorDetail(String id);

    Flowable<VinResponseData>getVinData(String vinNo);




//    Single<> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);
//
//    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);
//
//    Single<LogoutResponse> doLogoutApiCall();
//
//    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);
//
//    ApiHeader getApiHeader();
//
//    Single<BlogResponse> getBlogApiCall();
//
//    Single<OpenSourceResponse> getOpenSourceApiCall();

}
