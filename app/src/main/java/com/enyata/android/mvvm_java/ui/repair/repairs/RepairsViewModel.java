package com.enyata.android.mvvm_java.ui.repair.repairs;

import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.data.model.api.request.VehicleRepairReport;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RepairsViewModel extends BaseViewModel<RepairsNavigator> {
    public RepairsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;


    public void onBack(){
        getNavigator().onBack();
    }

    public void onExterior(){
        getNavigator().onExterior();
    }

    public void onGlass(){
        getNavigator().onGlass();
    }

    public void onTires(){
        getNavigator().onTires();
    }

    public void onUnderBody(){
            getNavigator().onUnderBody();
    }

    public void onUnderHood(){
        getNavigator().onUnderHood();
    }

    public void onInterior(){
        getNavigator().onInterior();
    }

    public void onElectric(){getNavigator().onElectric();}

    public void onRoadTest(){getNavigator().onRoadTest();}

    public  void onSignature(){getNavigator().onSignature();}

    public void onAddSignature(){getNavigator().onAddSignature();}

    public void setVehicleId(String vehicleId){
        getDataManager().setVehicleId(vehicleId);
    }

    public  String getVehincleId(){
       return getDataManager().getVehicleId();
    }

    public void onSaveHood(){
        getNavigator().onSaveHood();
    }

    public void onSaveGlass(){ getNavigator().onSaveGlass(); }

    public void onSaveTires(){getNavigator().onSaveTires();}

    public void onSaveUnderBody(){getNavigator().onSaveUnderBody();}

    public void onSaveUnderHood(){getNavigator().onSaveUnderHood();}

    public void onSaveInterior(){getNavigator().onSaveInterior();}

    public void onSaveElectric(){getNavigator().onSaveElectric();}

    public void onSaveRoadTest(){getNavigator().onSaveRoadTest();}

    public String getAcessToken(){ return  getDataManager().getAccessToken();}


    public boolean checkIfVehicleRepairIsEmpty() {
        return getRepairReport() == null;
    }

    public void setReportRepair(List<VehiclePartRepair>partRepair) {
        getDataManager().setRepairReport(partRepair);
    }

    public List<VehiclePartRepair> getRepairReport() {
        return getDataManager().getRepairReport();
    }

    public void saveRepairReportToLocalStorage(VehiclePartRepair repairPart) {
        if (checkIfVehicleRepairIsEmpty()) {
            List<VehiclePartRepair> newArray = new ArrayList<>();
            newArray.add(repairPart);
            setReportRepair(newArray);
        } else {
            List<VehiclePartRepair> oldArray = getRepairReport();
//            Log.i("jjjjjj", "Already");
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getPart().equals(repairPart.getPart())) {
                    oldArray.get(i).setComment(repairPart.getComment());
                    Log.i("part", repairPart.getPart());
                    setReportRepair(oldArray);
//                    Log.i("NEW NEW", String.valueOf(getRepairReport()));
                    break;
                } else {
                    if (getRepairReport().contains(repairPart)) {
                    }
                    List<VehiclePartRepair> sameArray = getRepairReport();
                    setReportRepair(sameArray);

                }
            }

        }
        if (!getRepairReport().contains(repairPart)) {
            List<VehiclePartRepair> addArray = getRepairReport();
            addArray.add(repairPart);
            setReportRepair(addArray);
        }


        Log.i("Big Array", String.valueOf(getRepairReport()));


    }

    public void onDispose(){
        onCleared();
    }

    public void deleteRepairReport(List<VehiclePartRepair> repair){
        getDataManager().deleteRepairReport(repair);
    }

    public  void deleteAll(){
        getDataManager().deleteAll();
    }

    public void sendRepair(VehicleRepairReport request, String vehicleId) {
        mAPIService = ApiUtils.getAPIService();
        getNavigator().onStarting();
        disposable.add(
                mAPIService.repairReport(getDataManager().getAccessToken(),vehicleId,request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(reportRequest -> {
                            getNavigator().onResponse(reportRequest);
                        },throwable -> {
                            getNavigator().handleError(throwable);
                        }));
    }

    public void getInspectorDetails(String id){
        getCompositeDisposable().add(getDataManager()
                .getInspectorDetail(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().onInspectorResponse(response);
                }, throwable -> {
                    getNavigator().onInspectorHandleError(throwable);
                }));
    }

    public void checkRepairsReport(String vehicleId){
        getNavigator().onStartingCheck();
        getCompositeDisposable().add(getDataManager()
                .checkRepairsReport(vehicleId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().onCheckRepairsResponse(response);
                }, throwable -> {
                    getNavigator().onCheckRepairError(throwable);
                }));
    }

    public String  getMooveIdRepairs(){
        return getDataManager().getMooveIdRepair();
    }

    public String getCarYearRepairs(){
        return  getDataManager().getCarYearRepair();
    }

    public String getCarModelRepairs(){
        return  getDataManager().getCarModelRepair();
    }

    public  String getCarMakeRepairs(){
        return getDataManager().getCarMakeRepair();
    }

    public String getVehicleIdRepairs(){
        return getDataManager().getVehicleIdRepair();
    }


}
