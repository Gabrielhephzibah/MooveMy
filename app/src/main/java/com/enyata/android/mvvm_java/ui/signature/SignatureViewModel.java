package com.enyata.android.mvvm_java.ui.signature;

import android.content.Intent;
import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.net.PortUnreachableException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignatureViewModel extends BaseViewModel<SignatureNavigator> {
    public SignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

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

    public  String getReportType(){
        return getDataManager().getReportType();
    }

    public void deleteIntakeVehicleReportFromSharedPref(){
        getDataManager().deleteIntakeVehicleReport();
    }


//    public void createIntakeReport(CreateReportRequest request){
//        getCompositeDisposable().add(getDataManager()
//                .createIntakeReport(request)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(response -> {
//                    getNavigator().onResponse(response);
//                }, throwable -> {
//                    getNavigator().handleError(throwable);
//
//                }));
//
//    }

    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }

    public void createVehicleReport(CreateReportRequest request) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();

//        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
////        startActivity(intent);// RxJava

        disposable.add(
                mAPIService.savePost(getDataManager().getAccessToken(), request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onResponse(response);
//                            Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
//                            startActivity(i);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().handleError(throwable);

//                            Intent intent1 = new Intent(getApplicationContext(), FailedActivity.class);
//                            startActivity(intent1);
                        }));
    }
}
