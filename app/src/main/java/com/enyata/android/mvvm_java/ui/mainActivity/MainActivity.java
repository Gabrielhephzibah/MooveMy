package com.enyata.android.mvvm_java.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityMainBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel>implements MainNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityMainBinding activityMainBinding;


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
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        startActivity(intent);

    }

    @Override
    public void onIntakeReport() {
        String reportType = "intake";
        mainActivityViewModel.setReportType(reportType);
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        startActivity(intent);

    }
}
