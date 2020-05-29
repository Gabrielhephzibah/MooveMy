package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport;

import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;

public interface MonthlyReportNavigator {
    void  back();
    void onAddSignature();
    void  onInspectFeature();
    void  onExteriorFeature();
    void  onGlassFeature();
    void  onTiresFeature();
    void  onUnderBodyFeature();
    void  onUnderHoodFeature();
    void  onInteriorFeature();
    void  onElectricFeature();
    void onRoadTestFeature();
    void onSignatureFeature();
    void onSaveVehicleInfo();
    void  onSubmitVin();
    void electricSliderDash(int current_position);
    void  createSliderDash(int current_position);
    void glassSliderDash(int current_position);
    void interiorSliderDash(int current_position);
    void roadTestSliderDash(int current_position);
    void tiresSliderDash(int current_position);
    void undeBodySliderDash(int current_position);
    void underHoodSliderDash(int current_position);
    void  onVehicleMaint();
    void onStarting();
    void onResponse(CreateReportResponse response);
    void handleError(Throwable throwable);
    void onGetMonthlyResult();
    void onAcceptanceMonthlyResult(CreateReportResponse response);
    void onAcceptanceMonthlyResultError(Throwable throwable);

}
