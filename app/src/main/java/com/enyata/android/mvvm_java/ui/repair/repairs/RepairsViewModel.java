package com.enyata.android.mvvm_java.ui.repair.repairs;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class RepairsViewModel extends BaseViewModel<RepairsNavigator> {
    public RepairsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }
}
