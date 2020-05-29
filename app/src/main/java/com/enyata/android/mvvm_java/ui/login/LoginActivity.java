package com.enyata.android.mvvm_java.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.databinding.ActivityLoginBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>implements LoginNavigator {
    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    ActivityLoginBinding activityLoginBinding;
    String decoded;
    int backButtonPressed = 0;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    public static LoginActivity newInstance() {
        return new LoginActivity();
    }

    private   LoginViewModel loginViewModel;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return loginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel.setNavigator(this);
        activityLoginBinding = getViewDataBinding();

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.i("ERROR","ERRROr");
        if (throwable != null ) {
            try {

                ANError error = (ANError) throwable;
                LoginResponse response = gson.fromJson(error.getErrorBody(), LoginResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                } else {
                    if (error.getErrorBody() == null) {
                        Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
                    } else {
                        Alert.showFailed(getApplicationContext(), "Internet Connection Error");
                    }
                }

            }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }


    }

    @Override
    public void login() {
        String password = activityLoginBinding.passwordTextView.getText().toString().trim();
        String email = activityLoginBinding.emailTextView.getText().toString().trim();
        LoginRequest.Request request = new LoginRequest.Request(email,password);

       if (!loginViewModel.isEmailAndPasswordValid(email,password)){
            Alert.showFailed(getApplicationContext(),"Please enter a valid email and password");
        }else if (!loginViewModel.isLengthEqualsToSeven(password)){
            Alert.showFailed(getApplicationContext(),"Password length must be at least 7 characters");
        }else {
            if (InternetConnection.getInstance(this).isOnline()){
                hideKeyboard();
                loginViewModel.onLoginInspector(request);
            }else {
                Alert.showFailed(getApplicationContext(),"Please check your Internet Connection and try again");
            }

        }
    }

    @Override
    public void onResponse(LoginResponse response) {
        String firstName = response.getData().getFirstName();
        Log.i("First Name", firstName);
        loginViewModel.setCurrentUserName(firstName);
        Log.i("Login", "Login was Successful");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

    @Override
    public void openMainActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel.onDispose();
    }
}
