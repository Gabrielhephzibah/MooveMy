package com.enyata.android.mvvm_java.ui.response;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.List;

public class ResponseViewModel extends BaseViewModel<ResponseNavigator> {
    public ResponseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onContinue(){
        getNavigator().onContinue();
    }

    public void delete(){
        getDataManager().deleteIntakeVehicleReport();
    }
    public List<VehicleCollection> vehincleReportArray(){
        return   getDataManager().getInTakeVehicleReport();
    }
}
