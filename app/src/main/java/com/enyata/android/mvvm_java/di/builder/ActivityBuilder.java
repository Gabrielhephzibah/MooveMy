package com.enyata.android.mvvm_java.di.builder;

import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.login.LoginActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance.MaintenanceActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.ui.signature.MonthlySignature.MonthlySignatureActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;
import com.enyata.android.mvvm_java.ui.splash.SplashActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract CreateReportActivity bindCreateReportActivity();

    @ContributesAndroidInjector
    abstract SignatureActivity bindSignatureActivity();

    @ContributesAndroidInjector
    abstract ResponseActivity bindResponseActivity();

    @ContributesAndroidInjector
    abstract FailedActivity bindFailedActivity();

    @ContributesAndroidInjector
    abstract RepairListActivity bindRepairListActivity();

    @ContributesAndroidInjector
    abstract RepairsActivity bindRepairsActivity();

    @ContributesAndroidInjector
    abstract MonthlyReportActivity bindMonthlyReportActivity();

    @ContributesAndroidInjector
    abstract MonthlySignatureActivity bindMonthlySignatureActivity();

    @ContributesAndroidInjector
    abstract VehicleListActivity bindVehicleListActivity();

    @ContributesAndroidInjector
    abstract MaintenanceActivity bindMaintenanceActivity();
}
