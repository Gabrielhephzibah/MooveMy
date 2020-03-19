package com.enyata.android.mvvm_java.ui.repair.repairList;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class RepairListViewModel extends BaseViewModel<RepairListNavigator> {
    public RepairListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceedRepair(){
        getNavigator().onProceedRepair();
    }

    public void onBack(){getNavigator().onBack();}

    public void onExterior(){getNavigator().onExterior();}
}
