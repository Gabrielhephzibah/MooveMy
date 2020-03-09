package com.enyata.android.mvvm_java.ui.signature;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class SignatureViewModel extends BaseViewModel<SignatureNavigator> {
    public SignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onSubmit(){
        getNavigator().onSubmit();
    }
}
