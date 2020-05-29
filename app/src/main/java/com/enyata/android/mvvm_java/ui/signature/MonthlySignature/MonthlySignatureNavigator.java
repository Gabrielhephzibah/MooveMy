package com.enyata.android.mvvm_java.ui.signature.MonthlySignature;

import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;

import retrofit2.Response;

public interface MonthlySignatureNavigator {
    void onSubmit();
    void onSaveInspectorSign();
    void onClearInspectorSign();
    void onSaveSupplierSign();
    void onClearSupplierSign();
    void onStarting();
    void onResponse(CreateReportResponse response);
    void handleError(Throwable response);
}
