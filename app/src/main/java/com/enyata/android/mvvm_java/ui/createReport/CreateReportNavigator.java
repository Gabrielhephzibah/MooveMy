package com.enyata.android.mvvm_java.ui.createReport;

import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.VinResponseData;

public interface CreateReportNavigator {
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
   void  validateData(CreateReportResponse response);
   void  onResponse(VinResponseData response);
   void handleError(Throwable throwable);
   void handleValidateError(Throwable throwable);
   void onStarting();
   void  createSliderDash(int current_position);
   void glassSliderDash(int current_position);
   void roadTestSliderDash(int current_position);
   void tiresSliderDash(int current_position);
   void undeBodySliderDash(int current_position);
   void underHoodSliderDash(int current_position);
   void electricSliderDash(int current_position);
   void interiorSliderDash(int current_position);




}
