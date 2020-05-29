package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport;

import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.GetAcceptanceResultRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MonthlyReportViewModel extends BaseViewModel<MonthlyReportNavigator> {


    public MonthlyReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

    public void onBack() {
        getNavigator().back();
    }

    public void onInspectFeature() {
        getNavigator().onInspectFeature();
    }

    public void onAddSignature() {
        getNavigator().onAddSignature();
    }

    public void onExteriorFeature() {
        getNavigator().onExteriorFeature();
    }

    public void onGlassFeature() {
        getNavigator().onGlassFeature();
    }

    public void onTiresFeature() {
        getNavigator().onTiresFeature();
    }

    public void onUnderBodyFeature() {
        getNavigator().onUnderBodyFeature();
    }

    public void onUnderHoodFeature() {
        getNavigator().onUnderHoodFeature();
    }

    public void onInteriorFeature() {
        getNavigator().onInteriorFeature();
    }

    public void onElectricFeature() {
        getNavigator().onElectricFeature();
    }

    public void onRoadTestFeature() {
        getNavigator().onRoadTestFeature();
    }

    public void onSignatureFeature() {
        getNavigator().onSignatureFeature();
    }

    public void onSaveVehicleInfo() {
        getNavigator().onSaveVehicleInfo();
    }

    public void onSubmitVin() {
        getNavigator().onSubmitVin();
    }

    public  void  onVehicleMaint(){
        getNavigator().onVehicleMaint();
    }

    public void setMooveId (String mooveId){
        getDataManager().setMooveId(mooveId);
    }

    public void onGetMonthlyResult(){
        getNavigator().onGetMonthlyResult();
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

    public  String getMooveId(){
        return  getDataManager().getMooveId();
    }

    public  String getCarYearMaint(){
        return  getDataManager().getCarYearMaint();
    }

    public  String getCarModelMaint(){
        return  getDataManager().getCarModelMaint();
    }

    public  String getCarMakeMaint(){
        return  getDataManager().getCarMakeMaint();
    }

    public  String getVehicleIdMaint(){
        return  getDataManager().getVehicleIdMaint();
    }

    public boolean checkIfMonthlyVehicleReportIsEmpty() {
        return getMonthlyVehicleReport() == null;
    }

    public void setMonthlyVehicleReport(List<VehicleCollection> vehiclePart) {
        getDataManager().setMonthlyVehicleReport(vehiclePart);
    }

    public List<VehicleCollection> getMonthlyVehicleReport() {
        return getDataManager().getMonthlyVehicleReport();
    }

    public void checkMonthlyReport(String vehicleId){
        getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                .checkMonthlyReport(vehicleId)
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



    public void saveMonthlyReportToLocalStorage(VehicleCollection vehiclePart) {
        if (checkIfMonthlyVehicleReportIsEmpty()) {
            List<VehicleCollection> newArray = new ArrayList<>();
            newArray.add(vehiclePart);
            setMonthlyVehicleReport(newArray);

        } else {
            List<VehicleCollection> oldArray = getMonthlyVehicleReport();
            Log.i("jjjjjj", "Already");
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getPart().equals(vehiclePart.getPart())) {
                    oldArray.get(i).setRemark(vehiclePart.getRemark());
                    oldArray.get(i).setImageUrl(vehiclePart.getImageUrl());
                    oldArray.get(i).setSection(vehiclePart.getSection());
                    Log.i("part", vehiclePart.getPart());
                    setMonthlyVehicleReport(oldArray);
                    Log.i("NEW NEW", String.valueOf(getMonthlyVehicleReport()));

                    break;
                } else {
                    if (getMonthlyVehicleReport().contains(vehiclePart)) {
                    }
                    List<VehicleCollection> sameArray = getMonthlyVehicleReport();
                    setMonthlyVehicleReport(sameArray);


                }
            }

        }
        if (!getMonthlyVehicleReport().contains(vehiclePart)) {
            List<VehicleCollection> addArray = getMonthlyVehicleReport();
            addArray.add(vehiclePart);
            setMonthlyVehicleReport(addArray);

        }


        Log.i("MonthlyReportArray", String.valueOf(getMonthlyVehicleReport()));

    }



    public void getAcceptanceResult(GetAcceptanceResultRequest request) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();
        disposable.add(
                mAPIService.getAcceptanceResult(getDataManager().getAccessToken(), request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onAcceptanceMonthlyResult(response);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().onAcceptanceMonthlyResultError(throwable);

                        }));
    }


    public  void setFinalAssessment( String finalAssessment){
        getDataManager().setMonthlyFinalAssessment(finalAssessment);
    }

    public  void setFinalComment(String finalComment){
        getDataManager().setMonthlyFinalComment(finalComment);
    }

    public  void setMonthlyAcceptanceValue(String monthlyAcceptanceValue){
        getDataManager().setMonthlyAcceptanceValue(monthlyAcceptanceValue);
    }

    public  String getInitialmileage(){
        return  getDataManager().getInitialMileage();
    }

    public void onDispose(){
        onCleared();
    }
}
