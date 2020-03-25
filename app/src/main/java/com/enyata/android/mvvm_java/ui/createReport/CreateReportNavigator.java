package com.enyata.android.mvvm_java.ui.createReport;

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
   void  onResponse(VinResponseData response);
   void handleError(Throwable throwable);
   void onStarting();



}
