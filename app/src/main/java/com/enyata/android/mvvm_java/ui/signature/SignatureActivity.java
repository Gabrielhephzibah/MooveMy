package com.enyata.android.mvvm_java.ui.signature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivitySignatureBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.BrakingFragment;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;

import javax.inject.Inject;

public class SignatureActivity extends BaseActivity<ActivitySignatureBinding,SignatureViewModel>implements SignatureNavigator {

    @Inject
    ViewModelProviderFactory factory;
    SignatureViewModel signatureViewModel;
    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signature;
    }

    @Override
    public SignatureViewModel getViewModel() {
        signatureViewModel = ViewModelProviders.of(this,factory).get(SignatureViewModel.class);
        return signatureViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signatureViewModel.setNavigator(this);
    }

    @Override
    public void onSubmit() {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);

    }
}
