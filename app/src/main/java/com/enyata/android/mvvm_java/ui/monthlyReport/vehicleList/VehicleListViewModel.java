package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.List;

public class VehicleListViewModel extends BaseViewModel<VehicleListNavigator> {
    public VehicleListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onBack(){getNavigator().onBack();}

    public void onVehicleList(){getNavigator().onVehicleList();}

    public void getMonthlyVehicleList(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMonthlyVehicleList()
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

    public void onDispose(){
        onCleared();
    }

    public void setMooveId (String mooveId){
        getDataManager().setMooveId(mooveId);
    }

    public  void  setCarYearMaint( String carYearMaint){
        getDataManager().setCarYearMaint(carYearMaint);
    }

    public  void  setCarMakeMaint(String  carMakeMaint){
        getDataManager().setCarMakeMaint(carMakeMaint);
    }
    public  void  setcarModelMaint(String  carModelMaint){
        getDataManager().setCarModelMaint(carModelMaint);
    }

    public  void  setVehicleIdMaint(String  vehicleIdMaint){
        getDataManager().setVehicleIdMaint(vehicleIdMaint);
    }

    public void  deleteMonthlyReport(List<VehicleCollection> vehicleCollections){
        getDataManager().deleteMonthlyReport(vehicleCollections);
    }

    public List<VehicleCollection>getMonthlyVehicleReport(){
        return  getDataManager().getMonthlyVehicleReport();
    }

    public void setInitialMileage(String initialMileage){
        getDataManager().setInitialMileage(initialMileage);
    }

    public String getInitialMileage(){
        return  getDataManager().getInitialMileage();
    }
}
