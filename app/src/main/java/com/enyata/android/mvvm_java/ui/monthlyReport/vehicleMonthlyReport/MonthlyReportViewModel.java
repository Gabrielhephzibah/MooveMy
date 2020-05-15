package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class MonthlyReportViewModel extends BaseViewModel<MonthlyReportNavigator> {


    public MonthlyReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

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
}
