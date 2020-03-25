package com.enyata.android.mvvm_java.ui.mainActivity;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class MainActivityViewModel extends BaseViewModel<MainNavigator> {
    public MainActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


//    public  void onCreateReport(){
//        getNavigator().CreateReport();
//    }

    public String getCurrentUserName(){
      return   getDataManager().getCurrentUserName();
    }

    public void onRepairReport(){getNavigator().onRepairReport();}

    public void onMonthlyReport(){getNavigator().onMonthlyReport();}

    public void onIntakeReport(){getNavigator().onIntakeReport();}

    public void setReportType(String ReportType){
        getDataManager().setReportType(ReportType);
    }

    public String getReportType(){
        return  getDataManager().getReportType();
    }

    public  String getApiHeader(){
        return getDataManager().getAccessToken();
    }




}
