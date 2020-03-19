package com.enyata.android.mvvm_java.ui.repair.repairs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.ViewModelProviders;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityRepairsBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;

import javax.inject.Inject;

public class RepairsActivity extends BaseActivity<ActivityRepairsBinding,RepairsViewModel>implements RepairsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    RepairsViewModel repairsViewModel;
    ImageView extriorToggle;
    LinearLayout exteriorFragment;
    ActivityRepairsBinding activityRepairsBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repairs;
    }

    @Override
    public RepairsViewModel getViewModel() {
        repairsViewModel = ViewModelProviders.of(this,factory).get(RepairsViewModel.class);
        return repairsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repairsViewModel.setNavigator(this);
        activityRepairsBinding = getViewDataBinding();
      extriorToggle = activityRepairsBinding.exteriorToggle;
      exteriorFragment = activityRepairsBinding.exteriorFragment;

    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), RepairListActivity.class);
        startActivity(intent);
    }
}
