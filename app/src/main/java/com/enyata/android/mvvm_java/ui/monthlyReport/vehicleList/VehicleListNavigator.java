package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;

public interface VehicleListNavigator {
    void  onBack();
    void onVehicleList();
    void onResponse(InspectorListResponse response);
    void handleError(Throwable throwable);
}
