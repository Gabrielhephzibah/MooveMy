package com.enyata.android.mvvm_java.ui.response.failedResponse;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class FailedViewModel extends BaseViewModel<FailedNavigator> {
    public FailedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void  onTryAgain(){
        getNavigator().ontryAgain();
    }
}
