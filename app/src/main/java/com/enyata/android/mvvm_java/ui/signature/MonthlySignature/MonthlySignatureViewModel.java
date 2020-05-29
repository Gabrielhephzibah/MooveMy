package com.enyata.android.mvvm_java.ui.signature.MonthlySignature;

import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MonthlyReportRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MonthlySignatureViewModel extends BaseViewModel<MonthlySignatureNavigator> {
    public MonthlySignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

    public  String getMonthlyAcceptanceValue(){
        return  getDataManager().getMonthlyAcceptanceValue();
    }
    public void deleteMonthlyAcceptanceValue(String acceptanceValue){
        getDataManager().deleteMonthlyAcceptanceValue(acceptanceValue);
    }


    public void onSubmit(){
        getNavigator().onSubmit();
    }

    public void onSaveSupplierSign(){getNavigator().onSaveSupplierSign();}

    public void onClearSupplierSign(){getNavigator().onClearSupplierSign();}

    public void onSaveInspectorSign(){getNavigator().onSaveInspectorSign();}

    public void onClearInspectorSign(){getNavigator().onClearInspectorSign();}

    public  String getFinalComment(){
      return  getDataManager().getMonthlyFinalComment();
    }

    public String getFinalAssesMent(){
       return getDataManager().getMonthlyFinalAssessment();
    }

    public List<VehicleCollection>getMonthlyVehicleReport(){
        return  getDataManager().getMonthlyVehicleReport();
    }

    public String getVehicleId(){
       return getDataManager().getVehicleIdMaint();
    }


    public void createMonthlyReportt(MonthlyReportRequest request, String vehicleId) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();
        disposable.add(
                mAPIService.createMonthlyReport(getDataManager().getAccessToken(),vehicleId, request )
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

//
                        }));
    }


    public void onDispose(){
        onCleared();
    }

    public void  deleteMonthlyReport(List<VehicleCollection>vehicleCollections){
        getDataManager().deleteMonthlyReport(vehicleCollections);
    }

}
