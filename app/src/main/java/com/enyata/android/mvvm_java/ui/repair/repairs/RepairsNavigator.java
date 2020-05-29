package com.enyata.android.mvvm_java.ui.repair.repairs;

import android.view.View;

import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;

public interface RepairsNavigator {
    void onBack();
    void onExterior();
    void onGlass();
    void onTires();
    void onUnderBody();
    void onUnderHood();
    void onInterior();
    void onElectric();
    void onRoadTest();
    void onSignature();
    void onAddSignature();
    void onBoxChecked(View view);
    void onSaveHood();
    void onSaveGlass();
    void onSaveTires();
    void onSaveUnderBody();
    void onSaveUnderHood();
    void onSaveInterior();
    void onSaveElectric();
    void onSaveRoadTest();
    void handleError(Throwable throwable);
    void onResponse(CreateReportResponse response);
    void onStarting();
    void onInspectorHandleError(Throwable throwable);
    void onInspectorResponse(InspectorDetailReport response);
    void onCheckRepairsResponse(CreateReportResponse response);
    void  onCheckRepairError(Throwable throwable);
    void onStartingCheck();

}
