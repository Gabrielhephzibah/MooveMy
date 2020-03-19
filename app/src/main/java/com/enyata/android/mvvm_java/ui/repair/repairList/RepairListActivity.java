package com.enyata.android.mvvm_java.ui.repair.repairList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityRepairListBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;

import javax.inject.Inject;

public class RepairListActivity extends BaseActivity<ActivityRepairListBinding,RepairListViewModel>implements RepairListNavigator {
    @Inject
    ViewModelProviderFactory factory;
    RepairListViewModel repairListViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_list;
    }

    @Override
    public RepairListViewModel getViewModel() {
        repairListViewModel = ViewModelProviders.of(this,factory).get(RepairListViewModel.class);
        return repairListViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repairListViewModel.setNavigator(this);
    }

    @Override
    public void onProceedRepair() {
        Intent intent = new Intent(getApplicationContext(), RepairsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExterior() {

    }
}
