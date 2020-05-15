package com.enyata.android.mvvm_java.ui.mainActivity;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityMainBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;
import com.enyata.android.mvvm_java.utils.Alert;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel>implements MainNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityMainBinding activityMainBinding;
    int backButtonPressed = 0;


private MainActivityViewModel mainActivityViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        return mainActivityViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel.setNavigator(this);
        activityMainBinding = getViewDataBinding();
        TextView inspectorName = activityMainBinding.inspectorName;
        String currentUserName = mainActivityViewModel.getCurrentUserName();
        inspectorName.setText(currentUserName);

    }


    @Override
    public void onRepairReport() {

        Intent intent = new Intent(getApplicationContext(), RepairListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMonthlyReport() {
        String reportType = "monthly";
        mainActivityViewModel.setReportType(reportType);
        Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
        startActivity(intent);

    }

    @Override
    public void onIntakeReport() {
        String reportType = "intake";
        mainActivityViewModel.setReportType(reportType);
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (backButtonPressed >= 2) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Alert.showInfo(getApplicationContext(),"Please press the back button twice to close the application");
            backButtonPressed++;

        }

    }
}
