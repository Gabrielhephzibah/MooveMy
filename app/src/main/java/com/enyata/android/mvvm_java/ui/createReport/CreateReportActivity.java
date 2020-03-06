package com.enyata.android.mvvm_java.ui.createReport;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityCreateReportBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.exterior.ExteriorViewPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.glass.GlassPagerAdater;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;

import javax.inject.Inject;

public class CreateReportActivity extends BaseActivity<ActivityCreateReportBinding,CreateReportViewModel>implements CreateReportNavigator {

    @Inject
    ViewModelProviderFactory factory;
    CreateReportViewModel createReportViewModel;
    ActivityCreateReportBinding activityCreateReportBinding;
    ViewPager exteriorPager,glassPager;

    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_report;
    }

    @Override
    public CreateReportViewModel getViewModel() {
        createReportViewModel = ViewModelProviders.of(this, factory).get(CreateReportViewModel.class);
        return createReportViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createReportViewModel.setNavigator(this);
        activityCreateReportBinding = getViewDataBinding();
        ImageView openArrow = activityCreateReportBinding.openArrow;
        ImageView signatureArrow = activityCreateReportBinding.signatureIcon;
       exteriorPager = activityCreateReportBinding.exteriorPager;
       glassPager = activityCreateReportBinding.glassPager;
        LinearLayout signAndConfirm = activityCreateReportBinding.signAndConfirm;
        LinearLayout vehincleDetails = activityCreateReportBinding.vehincleDetails;

        ExteriorViewPagerAdapter exteriorViewPagerAdapter = new ExteriorViewPagerAdapter(this, getSupportFragmentManager());
        exteriorPager.setAdapter(exteriorViewPagerAdapter);

        GlassPagerAdater glassPagerAdater = new GlassPagerAdater(this, getSupportFragmentManager());
        glassPager.setAdapter(glassPagerAdater);

        openArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vehincleDetails.getVisibility()==View.GONE){
                    vehincleDetails.setVisibility(View.VISIBLE);
                openArrow.setImageResource(R.drawable.icon);
                }else {
                    vehincleDetails.setVisibility(View.GONE);
                    openArrow.setImageResource(R.drawable.ic_icon);
                }


            }
        });
        signatureArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signAndConfirm.getVisibility()==View.GONE){
                    signAndConfirm.setVisibility(View.VISIBLE);
                    signatureArrow.setImageResource(R.drawable.icon);
                }else {
                    signAndConfirm.setVisibility(View.GONE);
                    signatureArrow.setImageResource(R.drawable.ic_icon);
                }
            }
        });

    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onAddSignature() {
        Intent intent = new Intent(getApplicationContext(), SignatureActivity.class);
        startActivity(intent);
    }
}
