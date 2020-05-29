package com.enyata.android.mvvm_java.ui.splash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivitySpashBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.login.LoginActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySpashBinding, SplashViewModel> implements  SplashNavigator {
    ImageView mooveLogo,splash1,splash2,splash3;
    ActivitySpashBinding activitySpashBinding;

   private SplashViewModel splashViewModel;
    private static int SPLASH_TIME_OUT = 1000;

   @Inject
    ViewModelProviderFactory factory;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        splashViewModel.setNavigator(this);
        activitySpashBinding = getViewDataBinding();
        mooveLogo = activitySpashBinding.mooveLogo;
        splash1 = activitySpashBinding.splash1;
        splash2 = activitySpashBinding.splash2;
        splash3 = activitySpashBinding.splash3;
        mooveLogo.setX(-2000);
        mooveLogo.animate().translationXBy(2000).setDuration(1000);
        splash1.animate().translationY(-100).translationX(50).setDuration(1500);
        splash2.animate().translationY(-20).translationX(200).setDuration(1500);
        splash3.animate().translationY(-100).translationXBy(50).setDuration(1500);

//        if (getIntent().getBooleanExtra("EXIT", false)) {
//            finish();
//        } else {

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    splashViewModel.decideNextActivity();
                }
            }, SPLASH_TIME_OUT);
//        }

    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void openMainActivity() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (getIntent().getBooleanExtra("EXIT", false)) {
//            finish();
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (getIntent().getBooleanExtra("EXIT", false)) {
//            finish();
//        }
//    }
}
