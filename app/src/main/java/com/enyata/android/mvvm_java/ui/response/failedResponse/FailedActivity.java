package com.enyata.android.mvvm_java.ui.response.failedResponse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityFailedBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;

import javax.inject.Inject;

public class FailedActivity extends BaseActivity<ActivityFailedBinding, FailedViewModel>implements FailedNavigator {

    @Inject
    ViewModelProviderFactory factory;

    FailedViewModel failedViewModel;
    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_failed;
    }

    @Override
    public FailedViewModel getViewModel() {
        failedViewModel = ViewModelProviders.of(this,factory).get(FailedViewModel.class);
        return failedViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        failedViewModel.setNavigator(this);

    }

    @Override
    public void ontryAgain() {
        Intent intent = new Intent(getApplicationContext(), SignatureActivity.class);
        startActivity(intent);
    }
}
