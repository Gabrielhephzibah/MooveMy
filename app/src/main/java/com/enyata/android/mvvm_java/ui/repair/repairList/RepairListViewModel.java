package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.util.Log;

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

    public void getInspectorHistoty(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getInspectorHistory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));

    }

    public String getAccess(){
       return getDataManager().getAccessToken();
    }

    public void onDispose(){
        onCleared();
    }
}
