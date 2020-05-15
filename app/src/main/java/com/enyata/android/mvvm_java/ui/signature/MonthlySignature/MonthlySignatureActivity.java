package com.enyata.android.mvvm_java.ui.signature.MonthlySignature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityMonthlySignatureBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MonthlySignatureActivity extends BaseActivity<ActivityMonthlySignatureBinding, MonthlySignatureViewModel>implements MonthlySignatureNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityMonthlySignatureBinding activityMonthlySignatureBinding;
    MonthlySignatureViewModel monthlySignatureViewModel;

    @Inject
    Gson gson;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_monthly_signature;
    }

    @Override
    public MonthlySignatureViewModel getViewModel() {
        monthlySignatureViewModel = ViewModelProviders.of(this, factory).get(MonthlySignatureViewModel.class);
        return monthlySignatureViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlySignatureViewModel.setNavigator(this);
        activityMonthlySignatureBinding = getViewDataBinding();

    }

    @Override
    public void onSubmit() {

    }

    @Override
    public void onSaveInspectorSign() {

    }

    @Override
    public void onClearInspectorSign() {

    }

    @Override
    public void onSaveSupplierSign() {

    }

    @Override
    public void onClearSupplierSign() {

    }
}
