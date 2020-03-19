package com.enyata.android.mvvm_java.data.remote;

import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;

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
}
