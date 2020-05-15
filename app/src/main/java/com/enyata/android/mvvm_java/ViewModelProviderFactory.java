package com.enyata.android.mvvm_java;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportViewModel;
import com.enyata.android.mvvm_java.ui.login.LoginViewModel;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivityViewModel;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListViewModel;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance.MaintenanceActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance.MaintenanceViewModel;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportViewModel;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListViewModel;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsViewModel;
import com.enyata.android.mvvm_java.ui.response.ResponseViewModel;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedViewModel;
import com.enyata.android.mvvm_java.ui.signature.MonthlySignature.MonthlySignatureViewModel;
import com.enyata.android.mvvm_java.ui.signature.SignatureViewModel;
import com.enyata.android.mvvm_java.ui.splash.SplashViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new MainActivityViewModel(dataManager, schedulerProvider);
        }
         else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager,schedulerProvider);
            }
        else if (modelClass.isAssignableFrom(LoginViewModel.class)){
        //noinspection unchecked
        return (T) new LoginViewModel(dataManager, schedulerProvider);
    }

        else if (modelClass.isAssignableFrom(CreateReportViewModel .class)) {
            //noinspection unchecked
            return (T) new CreateReportViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(SignatureViewModel.class)) {
            //noinspection unchecked
            return (T) new SignatureViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(ResponseViewModel.class)) {
            //noinspection unchecked
            return (T) new ResponseViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(FailedViewModel.class)) {
            //noinspection unchecked
            return (T) new FailedViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(RepairListViewModel.class)) {
            //noinspection unchecked
            return (T) new RepairListViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(RepairsViewModel.class)) {
            //noinspection unchecked
            return (T) new RepairsViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(MonthlyReportViewModel.class)) {
            //noinspection unchecked
            return (T) new MonthlyReportViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(MonthlySignatureViewModel.class)) {
            //noinspection unchecked
            return (T) new MonthlySignatureViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(VehicleListViewModel.class)) {
            //noinspection unchecked
            return (T) new VehicleListViewModel(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(MaintenanceViewModel.class)) {
            //noinspection unchecked
            return (T) new MaintenanceViewModel(dataManager,schedulerProvider);
        }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());

    }
}
