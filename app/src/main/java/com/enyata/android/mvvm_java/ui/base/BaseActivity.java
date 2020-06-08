/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.enyata.android.mvvm_java.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.auth0.android.jwt.JWT;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ui.login.LoginActivity;
import com.enyata.android.mvvm_java.ui.splash.SplashActivity;
import com.enyata.android.mvvm_java.utils.CommonUtils;
import com.enyata.android.mvvm_java.utils.NetworkUtils;


import java.util.Calendar;

import dagger.android.AndroidInjection;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {

    // TODO
    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;
    private long pausedMillis;
    private long currentMillis;
    JWT jwt;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();

    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.newIntent(this));
        finish();

    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        pausedMillis = Calendar.getInstance().getTimeInMillis();
        Log.i("StopCurrentTime", String.valueOf(pausedMillis));
        mViewModel.setTimeOnStop(pausedMillis);
        Log.i("sharePrefrenece", String.valueOf(mViewModel.getTimeOnStop()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            currentMillis = Calendar.getInstance().getTimeInMillis();
            Log.i("ResumeCurrentTime", String.valueOf(currentMillis));
            Log.i("New StopCurrentTime", String.valueOf(mViewModel.getTimeOnStop()));
            Log.i("What is the difference", String.valueOf(currentMillis - mViewModel.getTimeOnStop()));

            if ( !(this instanceof LoginActivity) && currentMillis - mViewModel.getTimeOnStop()  > 1000 * 60 * 30 ) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                Toast.makeText(BaseActivity.this, "You have left the app idle for some time, please login again", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}

