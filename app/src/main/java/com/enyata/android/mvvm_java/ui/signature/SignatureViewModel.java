package com.enyata.android.mvvm_java.ui.signature;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.net.PortUnreachableException;
import java.util.List;

public class SignatureViewModel extends BaseViewModel<SignatureNavigator> {
    public SignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onSubmit(){
        getNavigator().onSubmit();
    }

    public void onSaveSupplierSign(){getNavigator().onSaveSupplierSign();}

    public void onClearSupplierSign(){getNavigator().onClearSupplierSign();}

    public void onSaveInspectorSign(){getNavigator().onSaveInspectorSign();}

    public void onClearInspectorSign(){getNavigator().onClearInspectorSign();}

    public String getCarModel() {
        return getDataManager().getCarModel();
    }

    public String getCarMake() {
        return getDataManager().getCarMake();
    }

    public String getCarColor() {
        return getDataManager().getCarColor();
    }

    public String getMilage() {
        return getDataManager().getCurrentMilege();
    }

    public String getregNo() {
        return getDataManager().getRegistrationNo();
    }

    public String getVin() {
        return getDataManager().getVin();
    }

    public String getCarYear() {
        return getDataManager().getCarYear();
    }

    public String getIntakeFinalComment(){return  getDataManager().getIntakeFinalComment();}

    public  String getIntakeFinalStatus(){return  getDataManager().getIntakeFinalStatus();}

    public List<VehicleCollection>vehincleReportArray(){
      return   getDataManager().getInTakeVehicleReport();
    }


    public void createIntakeReport(CreateReportRequest request){
        getCompositeDisposable().add(getDataManager()
                .createIntakeReport(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().onResponse(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);

                }));

    }

    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }
}
