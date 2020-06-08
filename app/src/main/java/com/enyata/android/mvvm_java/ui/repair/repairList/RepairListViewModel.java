package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.List;

public class RepairListViewModel extends BaseViewModel<RepairListNavigator> {
    public RepairListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceedRepair(){
        getNavigator().onProceedRepair();
    }

    public void onBack(){getNavigator().onBack();}

    public void onExterior(){getNavigator().onExterior();}

//    public void getInspectorHistoty(){
//        setIsLoading(true);
//        getCompositeDisposable().add(getDataManager()
//                .getInspectorHistory()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(response -> {
//                    setIsLoading(false);
//                    getNavigator().onResponse(response);
//                }, throwable -> {
//                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
//
//                }));
//
//    }

        public void getAllVehicleInDataBase(int limit, int offset){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllVehicleInDataBase(limit,offset)
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

    public void setMooveIdRepairs(String mooveIdRepairs){
        getDataManager().setMooveIdRepairs(mooveIdRepairs);
    }

    public void setCarYearRepairs(String  carYearRepairs){
        getDataManager().setCarYearRepairs(carYearRepairs);
    }

    public void setCarMakeRepairs(String  carMakeRepairs){
        getDataManager().setCarMakeRepairs(carMakeRepairs);
    }

    public void setCarModelRepairs(String carModelRepairs){
        getDataManager().setCarModelRepairs(carModelRepairs);
    }

    public void setVehicleIdRepairs(String  vehicleIdRepairs){
        getDataManager().setVehicleIdRepairs(vehicleIdRepairs);
    }

    public void deleteRepairReport(List<VehiclePartRepair> repair){
        getDataManager().deleteRepairReport(repair);
    }


    public List<VehiclePartRepair> getRepairReport() {

        return getDataManager().getRepairReport();
    }

    public String getAccess(){
       return getDataManager().getAccessToken();
    }

    public void onDispose(){
        onCleared();
    }
}
