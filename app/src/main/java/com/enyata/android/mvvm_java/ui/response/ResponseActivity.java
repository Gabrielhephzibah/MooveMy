package com.enyata.android.mvvm_java.ui.response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityResponseBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;

import javax.inject.Inject;

public class ResponseActivity extends BaseActivity<ActivityResponseBinding,ResponseViewModel>implements ResponseNavigator {


    @Inject
    ViewModelProviderFactory factory;
    ResponseViewModel responseViewModel;
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
    }

    @Override
    public void onContinue() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}
