package com.enyata.android.mvvm_java.ui.signature.MonthlySignature;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class MonthlySignatureViewModel extends BaseViewModel<MonthlySignatureNavigator> {
    public MonthlySignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSubmit(){
        getNavigator().onSubmit();
    }

    public void onSaveSupplierSign(){getNavigator().onSaveSupplierSign();}

    public void onClearSupplierSign(){getNavigator().onClearSupplierSign();}

    public void onSaveInspectorSign(){getNavigator().onSaveInspectorSign();}

    public void onClearInspectorSign(){getNavigator().onClearInspectorSign();}
}
