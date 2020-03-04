package com.enyata.android.mvvm_java.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivitySpashBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.login.LoginActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySpashBinding, SplashViewModel> implements  SplashNavigator {

   private SplashViewModel splashViewModel;
    private static int SPLASH_TIME_OUT = 2000;

   @Inject
    ViewModelProviderFactory factory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_spash;
    }

    @Override
    public SplashViewModel getViewModel() {
         splashViewModel = ViewModelProviders.of(this,factory ).get(SplashViewModel.class);
        return splashViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        splashViewModel.setNavigator(this);
         //will hide the title
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        } else {

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
}
