package com.enyata.android.mvvm_java.ui.response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.databinding.ActivityResponseBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;

import java.util.List;

import javax.inject.Inject;

public class ResponseActivity extends BaseActivity<ActivityResponseBinding,ResponseViewModel>implements ResponseNavigator {


    @Inject
    ViewModelProviderFactory factory;
    ResponseViewModel responseViewModel;
    List<VehicleCollection> intakeVehicleDetails;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_response;
    }

    @Override
    public ResponseViewModel getViewModel() {
        responseViewModel = ViewModelProviders.of(this,factory).get(ResponseViewModel.class);
        return responseViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        responseViewModel.setNavigator(this);
        intakeVehicleDetails = responseViewModel.vehincleReportArray();

//        responseViewModel.delete();
//        intakeVehicleDetails = responseViewModel.vehincleReportArray();
//        responseViewModel.getDataManager().deleteIntakeVehicleReport();
        Log.i("ARRAY", String.valueOf(intakeVehicleDetails));
    }

    @Override
    public void onContinue() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}
