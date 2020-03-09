package com.enyata.android.mvvm_java.ui.mainActivity;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class MainActivityViewModel extends BaseViewModel<MainNavigator> {
    public MainActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public  void onCreateReport(){
        getNavigator().CreateReport();
    }

    public String getCurrentUserName(){
      return   getDataManager().getCurrentUserName();
    }
}
