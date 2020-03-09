package com.enyata.android.mvvm_java.ui.createReport;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class CreateReportViewModel extends BaseViewModel<CreateReportNavigator> {
    public CreateReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().back();
    }

    public void onInspectFeature(){ getNavigator().onInspectFeature(); }

    public void onAddSignature(){
        getNavigator().onAddSignature();
    }

    public void onExteriorFeature(){getNavigator().onExteriorFeature();}

    public void onGlassFeature(){ getNavigator().onGlassFeature(); }

    public void  onTiresFeature(){getNavigator().onTiresFeature();}

    public void onUnderBodyFeature(){ getNavigator().onUnderBodyFeature(); }

    public void  onUnderHoodFeature(){getNavigator().onUnderHoodFeature();}

    public void onInteriorFeature(){getNavigator().onInteriorFeature();}

    public void onElectricFeature(){getNavigator().onElectricFeature();}

    public void onRoadTestFeature(){getNavigator().onRoadTestFeature();}

    public void onSignatureFeature(){getNavigator().onSignatureFeature();}
}
