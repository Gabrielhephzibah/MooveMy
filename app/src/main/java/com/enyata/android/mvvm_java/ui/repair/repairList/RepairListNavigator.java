package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.view.View;
import android.view.ViewOutlineProvider;

import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;

public interface RepairListNavigator {
    void onProceedRepair();
    void  onBack();
    void onExterior();
    void  handleError(Throwable throwable);
    void onResponse(InspectorListResponse response);
}
