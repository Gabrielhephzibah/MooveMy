package com.enyata.android.mvvm_java.ui.signature;

import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;

public interface SignatureNavigator {
    void onSubmit();
    void onSaveInspectorSign();
    void onClearInspectorSign();
    void onSaveSupplierSign();
    void onClearSupplierSign();
    void onLoading();
    void handleError(Throwable throwable);
    void onResponse(CreateReportResponse response);
}
