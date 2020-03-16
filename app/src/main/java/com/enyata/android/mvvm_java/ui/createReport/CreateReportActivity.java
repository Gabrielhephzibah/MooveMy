package com.enyata.android.mvvm_java.ui.createReport;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.cloudinary.android.MediaManager;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityCreateReportBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.electric.ElectricPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.exterior.ExteriorViewPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.glass.GlassPagerAdater;
import com.enyata.android.mvvm_java.ui.createReport.interior.InteriorPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.RoadTestPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.tires.TirePagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.underbody.UnderbodyPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.underhood.UnderHoodPagerAdapter;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class CreateReportActivity extends BaseActivity<ActivityCreateReportBinding, CreateReportViewModel> implements CreateReportNavigator,AdapterView.OnItemSelectedListener {

    @Inject
    ViewModelProviderFactory factory;
    CreateReportViewModel createReportViewModel;
    ActivityCreateReportBinding activityCreateReportBinding;
    ViewPager exteriorPager, glassPager, tiresPager, underBodyPager, underHoodPager, interiorPager, electricPager, roadTestPager;
    LinearLayout inspectFeature, exteriorFeature, glassfeature, tiresFeature, underBodyFeature, underHoodFeature, interiorFeature, electricFeature, roadTestFeature, signatureFeature;
    ImageView inspectToggle, exteriorToggle, glassToggle, tiresToggle, underBodyToggle, underHoodToggle, interiorToggle, electricToggle, roadTestToggle, signatureToggle;
    Spinner yearSpinner, modelSpinner, makeSpinner;
    String[] yearNumber = {"", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"};
    String[] model = {" ", "Honda", "Hyundai", "Kia", "Toyota", "Volkswagen"};
    String[]make ={"","Accord","Civic", "City", "Accent","Elantra","Forte","Rio","Camry","Hilux", "RAV4","Yaris","Vento"};
    EditText carColoredit, mileageEdit,regNumEdit,vinEdit;
    String carColor,mileage, regNum,vin;
    Button save;
    String selectedYear, selectedModel,selectedMake;

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
        Map config = new HashMap();
        config.put("cloud_name", "dtt1nmogz");
        config.put("api_key", "754277299533971");
        config.put("api_secret", "hwuDlRgCtSpxKOg9rcY43AtsZvw");
        MediaManager.init(getApplicationContext().getApplicationContext(), config);
        Log.d("oooooo", "ffffff");
        inspectToggle = activityCreateReportBinding.inspectToggle;
        signatureToggle = activityCreateReportBinding.signatureToggle;
        exteriorPager = activityCreateReportBinding.exteriorPager;
        glassPager = activityCreateReportBinding.glassPager;
        tiresPager = activityCreateReportBinding.tiresPager;
        underHoodPager = activityCreateReportBinding.underHoodPager;
        underBodyPager = activityCreateReportBinding.underBodyPager;
        interiorPager = activityCreateReportBinding.interiorPager;
        electricPager = activityCreateReportBinding.electricPager;
        roadTestPager = activityCreateReportBinding.roadTestPager;
        exteriorFeature = activityCreateReportBinding.exteriorFeature;
        exteriorToggle = activityCreateReportBinding.exteriorToggle;
        glassfeature = activityCreateReportBinding.glassFeature;
        glassToggle = activityCreateReportBinding.glassToggle;
        tiresToggle = activityCreateReportBinding.tiresToggle;
        tiresFeature = activityCreateReportBinding.tiresFeature;
        underBodyFeature = activityCreateReportBinding.underBodyFeature;
        underBodyToggle = activityCreateReportBinding.underBodyToggle;
        underHoodFeature = activityCreateReportBinding.underHoodFeature;
        underHoodToggle = activityCreateReportBinding.underHoodToggle;
        interiorFeature = activityCreateReportBinding.interiorFeature;
        interiorToggle = activityCreateReportBinding.interiorToggle;
        electricToggle = activityCreateReportBinding.electricToggle;
        electricFeature = activityCreateReportBinding.electricFeature;
        roadTestFeature = activityCreateReportBinding.roadTestFeature;
        roadTestToggle = activityCreateReportBinding.roadTestToggle;
        signatureFeature = activityCreateReportBinding.signatureFeature;
        inspectFeature = activityCreateReportBinding.inspectFeature;
        yearSpinner = activityCreateReportBinding.yearSpinner;
        modelSpinner = activityCreateReportBinding.modelSpinner;
        makeSpinner = activityCreateReportBinding.makeSpinner;
        yearSpinner.setOnItemSelectedListener(this);
        modelSpinner.setOnItemSelectedListener(this);
        makeSpinner.setOnItemSelectedListener(this);




        ExteriorViewPagerAdapter exteriorViewPagerAdapter = new ExteriorViewPagerAdapter(this, getSupportFragmentManager());
        exteriorPager.setAdapter(exteriorViewPagerAdapter);

        GlassPagerAdater glassPagerAdater = new GlassPagerAdater(this, getSupportFragmentManager());
        glassPager.setAdapter(glassPagerAdater);

        TirePagerAdapter tirePagerAdapter = new TirePagerAdapter(this, getSupportFragmentManager());
        tiresPager.setAdapter(tirePagerAdapter);

        UnderbodyPagerAdapter underbodyPagerAdapter = new UnderbodyPagerAdapter(this, getSupportFragmentManager());
        underBodyPager.setAdapter(underbodyPagerAdapter);

        UnderHoodPagerAdapter underHoodPagerAdapter = new UnderHoodPagerAdapter(this, getSupportFragmentManager());
        underHoodPager.setAdapter(underHoodPagerAdapter);

        InteriorPagerAdapter interiorPagerAdapter = new InteriorPagerAdapter(this, getSupportFragmentManager());
        interiorPager.setAdapter(interiorPagerAdapter);

        ElectricPagerAdapter electricPagerAdapter = new ElectricPagerAdapter(this, getSupportFragmentManager());
        electricPager.setAdapter(electricPagerAdapter);

        RoadTestPagerAdapter roadTestPagerAdapter = new RoadTestPagerAdapter(this, getSupportFragmentManager());
        roadTestPager.setAdapter(roadTestPagerAdapter);

        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<String>(CreateReportActivity.this, android.R.layout.simple_spinner_item, yearNumber);
        yearSpinner.setAdapter(yearSpinnerAdapter);

        ArrayAdapter<String> modelSpinnerAdapter = new ArrayAdapter<String>(CreateReportActivity.this, android.R.layout.simple_spinner_item, model);
        modelSpinner.setAdapter(modelSpinnerAdapter);

        ArrayAdapter<String> makeSpinnerAdpter = new ArrayAdapter<String>(CreateReportActivity.this, android.R.layout.simple_spinner_item, make);
        makeSpinner.setAdapter(makeSpinnerAdpter);
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

    @Override
    public void onInspectFeature() {
        if (inspectFeature.getVisibility() == View.GONE) {
            inspectFeature.setVisibility(View.VISIBLE);
            inspectToggle.setImageResource(R.drawable.icon);
        } else {
            inspectFeature.setVisibility(View.GONE);
            inspectToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onExteriorFeature() {
        if (exteriorFeature.getVisibility() == View.GONE) {
            exteriorFeature.setVisibility(View.VISIBLE);
            exteriorToggle.setImageResource(R.drawable.icon);
        } else {
            exteriorFeature.setVisibility(View.GONE);
            exteriorToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onGlassFeature() {
        if (glassfeature.getVisibility() == View.GONE) {
            glassfeature.setVisibility(View.VISIBLE);
            glassToggle.setImageResource(R.drawable.icon);
        } else {
            glassfeature.setVisibility(View.GONE);
            glassToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onTiresFeature() {
        if (tiresFeature.getVisibility() == View.GONE) {
            tiresFeature.setVisibility(View.VISIBLE);
            tiresToggle.setImageResource(R.drawable.icon);
        } else {
            tiresFeature.setVisibility(View.GONE);
            tiresToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onUnderBodyFeature() {
        if (underBodyFeature.getVisibility() == View.GONE) {
            underBodyFeature.setVisibility(View.VISIBLE);
            underBodyToggle.setImageResource(R.drawable.icon);
        } else {
            underBodyFeature.setVisibility(View.GONE);
            underBodyToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onUnderHoodFeature() {
        if (underHoodFeature.getVisibility() == View.GONE) {
            underHoodFeature.setVisibility(View.VISIBLE);
            underHoodToggle.setImageResource(R.drawable.icon);
        } else {
            underHoodFeature.setVisibility(View.GONE);
            underHoodToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onInteriorFeature() {
        if (interiorFeature.getVisibility() == View.GONE) {
            interiorFeature.setVisibility(View.VISIBLE);
            interiorToggle.setImageResource(R.drawable.icon);
        } else {
            interiorFeature.setVisibility(View.GONE);
            interiorToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onElectricFeature() {
        if (electricFeature.getVisibility() == View.GONE) {
            electricFeature.setVisibility(View.VISIBLE);
            electricToggle.setImageResource(R.drawable.icon);
        } else {
            electricFeature.setVisibility(View.GONE);
            electricToggle.setImageResource(R.drawable.ic_icon);
        }


    }

    @Override
    public void onRoadTestFeature() {
        if (roadTestFeature.getVisibility() == View.GONE) {
            roadTestFeature.setVisibility(View.VISIBLE);
            roadTestToggle.setImageResource(R.drawable.icon);
        } else {
            roadTestFeature.setVisibility(View.GONE);
            roadTestToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onSignatureFeature() {
        if (signatureFeature.getVisibility() == View.GONE) {
            signatureFeature.setVisibility(View.VISIBLE);
            signatureToggle.setImageResource(R.drawable.icon);
        } else {
            signatureFeature.setVisibility(View.GONE);
            signatureToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onSaveVehicleInfo() {
        Log.i("Button clicked","Buttonnnnnnn");
        carColor = activityCreateReportBinding.editColor.getText().toString();
        mileage = activityCreateReportBinding.editMillage.getText().toString();
        regNum = activityCreateReportBinding.editRegNum.getText().toString();
     vin = activityCreateReportBinding.editVin.getText().toString();
     selectedYear = yearSpinner.getSelectedItem().toString();
     selectedModel = modelSpinner.getSelectedItem().toString();
     selectedMake = makeSpinner.getSelectedItem().toString();
     Log.i("Carcolor",carColor );
        Log.i("mileage",mileage );
        Log.i("regNum",regNum );
       Log.i("vin",vin );
        Log.i("SelectedYear",selectedYear);
        Log.i("SelectedModel",selectedModel);
        Log.i("SelectedMake",selectedMake);


        createReportViewModel.setCarYear(selectedYear);
        createReportViewModel.setCarModel(selectedModel);
        createReportViewModel.setCarMake(selectedMake);
        createReportViewModel.setCarColor(carColor);
        createReportViewModel.setMillage(mileage);
        createReportViewModel.setRegNo(regNum);
        createReportViewModel.setVin(vin);
        inspectFeature.setVisibility(View.GONE);





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


}
