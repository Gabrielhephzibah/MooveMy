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

    public void onAddSignature(){
        getNavigator().onAddSignature();
    }
}
