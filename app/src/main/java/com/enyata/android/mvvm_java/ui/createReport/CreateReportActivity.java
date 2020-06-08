package com.enyata.android.mvvm_java.ui.createReport;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CheckIntakeRequest;
import com.enyata.android.mvvm_java.data.model.api.request.GetAcceptanceResultRequest;
import com.enyata.android.mvvm_java.data.model.api.request.IntakeRuleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.RegNumberCheckRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.VinResponseData;
import com.enyata.android.mvvm_java.data.remote.RetrofitClient;
import com.enyata.android.mvvm_java.databinding.ActivityCreateReportBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.electric.ElectricPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.exterior.ExteriorViewPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.glass.GlassPagerAdater;
import com.enyata.android.mvvm_java.ui.createReport.interior.InteriorPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.interior.InteriorTrimFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.RoadTestPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.tires.TirePagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.underbody.UnderbodyPagerAdapter;
import com.enyata.android.mvvm_java.ui.createReport.underhood.UnderHoodPagerAdapter;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CreateReportActivity extends BaseActivity<ActivityCreateReportBinding, CreateReportViewModel> implements CreateReportNavigator, AdapterView.OnItemSelectedListener {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    Gson gson;
    private static final int REQUEST_CAMERA = 1;
    CreateReportViewModel createReportViewModel;
    ActivityCreateReportBinding activityCreateReportBinding;
    ViewPager exteriorPager, glassPager, tiresPager, underBodyPager, underHoodPager, interiorPager, electricPager, roadTestPager;
    RelativeLayout exteriorLayout, glassLayout;
    LinearLayout inspectFeature, exteriorFeature, glassfeature, tiresFeature, underBodyFeature, underHoodFeature, interiorFeature, electricFeature, roadTestFeature, signatureFeature;
    ImageView inspectToggle, exteriorToggle, glassToggle, tiresToggle, underBodyToggle, underHoodToggle, interiorToggle, electricToggle, roadTestToggle, signatureToggle;
    Spinner finalAssessSpinner;
    String[] intakeFinalAssess = {"", "accepted", "not accepted"};
    String[] monthlyFinalAssess = {"", "failed", "passed"};
    EditText carColoredit, mileageEdit, regNumEdit, finalCommentEditText, editVin;
    String carColor, mileage, regNum, vin, finalComment, editVinNo;
    Button save;
    int[] exteriorLayouts = {R.layout.hood_layout, R.layout.front_bumper_layout, R.layout.fenders_layout, R.layout.door_layout, R.layout.roof_layout, R.layout.rear_layout, R.layout.rear_bumper_layout, R.layout.trunk_layout, R.layout.trim_layout, R.layout.fuel_door_layout, R.layout.paint_layout};
    int[] glassLayouts = {R.layout.windshield_layout, R.layout.window_layout, R.layout.mirrors_layout, R.layout.rear_window_layout};
    int[] tiresLayouts = {R.layout.tires_layout, R.layout.wheel_layout, R.layout.spare_tire_layout};
    int[] underBodyLayouts = {R.layout.frame_layout, R.layout.exhaust_layout, R.layout.transmission_layout, R.layout.drive_axle_layout, R.layout.suspension_layout, R.layout.brake_system_layout};
    int[] underHoodLayouts = {R.layout.engine_com_layout, R.layout.battery_layout, R.layout.oil_layout, R.layout.fluid_layout, R.layout.wiring_layout, R.layout.belt_layout, R.layout.hoses_layout};
    int[] interiorLayouts = {R.layout.seats_layout, R.layout.headliner_layout, R.layout.carpet_layout, R.layout.door_panel_layout, R.layout.glove_box_layout, R.layout.vanity_mirror_layout, R.layout.interior_trim_layout, R.layout.dashboard_layout, R.layout.dash_guage_layout, R.layout.air_cond_layout, R.layout.heater_layout, R.layout.defroster_layout};
    int[] electricalLayouts = {R.layout.power_lock_layout, R.layout.power_seat_layout, R.layout.power_stering_layout, R.layout.power_window_layout, R.layout.power_mirror_layout, R.layout.audio_system_layout, R.layout.computer_layout, R.layout.headlight_layout, R.layout.tail_light_layout, R.layout.signal_light_layout,};
    int[] roadTestLayouts = {R.layout.starting_layout, R.layout.idling_layout, R.layout.engine_layout, R.layout.accelaration_layout, R.layout.transmisssion_shift_layout, R.layout.steering_layout, R.layout.braking_layout, R.layout.suspension_performance_layout};
    ImageView[] slider_dash;
    ImageView vehincleInfoCheck, glasscheck, exteriorCheck, tiresCheck, underBodyCheck, underHoodCheck, interiorCheck, roadTestCheck, electricCheck;
    TextView yearText, modelText, makeText,acceptanceResult;
    LinearLayout exteriorSlide, glassSlide, tireSlide, underBodySlide, underHoodSlide, interiorSlide, electricSlide, roadTestSlide, allInspection, allinspectionWraper;
    ProgressDialog dialog;
    String selectedYear, selectedModel, selectedMake, selectedFinalAssess;
    AlertDialog alert;
    Dialog dialogAlert;
    RetrofitClient retrofitClient = new RetrofitClient();

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

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission() && checkExternalPermission()) {
            } else {
                requestPermission();
            }
        }

        inspectToggle = activityCreateReportBinding.inspectToggle;
        exteriorLayout = activityCreateReportBinding.exteriorLayout;
        signatureToggle = activityCreateReportBinding.signatureToggle;
        exteriorPager = activityCreateReportBinding.exteriorPager;
        finalCommentEditText = activityCreateReportBinding.finalCommentEditText;
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
        yearText = activityCreateReportBinding.yearText;
        modelText = activityCreateReportBinding.modelText;
        makeText = activityCreateReportBinding.makeText;
        carColoredit = activityCreateReportBinding.editColor;
        finalAssessSpinner = activityCreateReportBinding.spinnerFinalAssess;
        editVin = activityCreateReportBinding.editVinNumber;
        mileageEdit = activityCreateReportBinding.editMillage;
        regNumEdit = activityCreateReportBinding.editCarRegNum;
        exteriorSlide = activityCreateReportBinding.slideLayout;
        glassSlide = findViewById(R.id.glassSlideLayout);
        interiorSlide = findViewById(R.id.interiorSliderLayout);
        tireSlide = findViewById(R.id.tireSlideLayout);
        underBodySlide = findViewById(R.id.underBodySlideLayout);
        underHoodSlide = findViewById(R.id.underHoodSlideLayout);
        roadTestSlide = findViewById(R.id.roadTestSlideLayout);
        electricSlide = findViewById(R.id.electricSlideLayout);
        vehincleInfoCheck = findViewById(R.id.vehicleInfoCheck);
        glasscheck = findViewById(R.id.glassCheck);
        exteriorCheck = findViewById(R.id.exteriorCheck);
        tiresCheck = findViewById(R.id.tiresCheck);
        electricCheck = findViewById(R.id.electricCheck);
        roadTestCheck = findViewById(R.id.roadTestCheck);
        underBodyCheck = findViewById(R.id.underBodyCheck);
        underHoodCheck = findViewById(R.id.underHoodCheck);
        interiorCheck = findViewById(R.id.interiorCheck);
        acceptanceResult = activityCreateReportBinding.acceptanceResulTextView;
        createReportViewModel.checkExterior(exteriorCheck);
        createReportViewModel.checkGlass(glasscheck);
        createReportViewModel.checkTires(tiresCheck);
        createReportViewModel.checkUnderBody(underBodyCheck);
        createReportViewModel.checkUnderHood(underHoodCheck);
        createReportViewModel.checkRoadTest(roadTestCheck);
        createReportViewModel.checkElectric(electricCheck);
        createReportViewModel.checkInterior(interiorCheck);

        allInspection = findViewById(R.id.allInspectionLayout);
        allinspectionWraper = findViewById(R.id.allInspectionWrapper);
        if (createReportViewModel.getVehincleInfo()) {
            vehincleInfoCheck.setVisibility(View.VISIBLE);
            editVin.setText(createReportViewModel.getVin());
            yearText.setText(createReportViewModel.getCarYear());
            modelText.setText(createReportViewModel.getCarModel());
            makeText.setText(createReportViewModel.getCarMake());
            mileageEdit.setText(createReportViewModel.getMilage());
            regNumEdit.setText(createReportViewModel.getregNo());
            carColoredit.setText(createReportViewModel.getCarColor());
            setViewAndChildrenEnabled(allInspection, true);
        } else {
            setViewAndChildrenEnabled(allInspection, false);
            allinspectionWraper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Alert.showFailed(getApplicationContext(), "Please fill Vehicle Information");
                }
            });
        }

        Log.i("BIG ARRAY", String.valueOf(createReportViewModel.getIntakeVehicleReport()));
        finalAssessSpinner.setOnItemSelectedListener(this);
        dialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait .....");
        dialog.setCancelable(false);

        ExteriorViewPagerAdapter exteriorViewPagerAdapter = new ExteriorViewPagerAdapter(this, getSupportFragmentManager());
        exteriorPager.setAdapter(exteriorViewPagerAdapter);
        createSliderDash(0);
        exteriorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                createSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        GlassPagerAdater glassPagerAdater = new GlassPagerAdater(this, getSupportFragmentManager());
        glassPager.setAdapter(glassPagerAdater);
        glassSliderDash(0);
        glassPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                glassSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TirePagerAdapter tirePagerAdapter = new TirePagerAdapter(this, getSupportFragmentManager());
        tiresPager.setAdapter(tirePagerAdapter);
        tiresSliderDash(0);
        tiresPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tiresSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        UnderbodyPagerAdapter underbodyPagerAdapter = new UnderbodyPagerAdapter(this, getSupportFragmentManager());
        underBodyPager.setAdapter(underbodyPagerAdapter);
        undeBodySliderDash(0);
        underBodyPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                undeBodySliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        UnderHoodPagerAdapter underHoodPagerAdapter = new UnderHoodPagerAdapter(this, getSupportFragmentManager());
        underHoodPager.setAdapter(underHoodPagerAdapter);
        underHoodSliderDash(0);
        underHoodPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                underHoodSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        InteriorPagerAdapter interiorPagerAdapter = new InteriorPagerAdapter(this, getSupportFragmentManager());
        interiorPager.setAdapter(interiorPagerAdapter);
        interiorSliderDash(0);
        interiorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                interiorSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ElectricPagerAdapter electricPagerAdapter = new ElectricPagerAdapter(this, getSupportFragmentManager());
        electricPager.setAdapter(electricPagerAdapter);
        electricSliderDash(0);
        electricPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                electricSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        RoadTestPagerAdapter roadTestPagerAdapter = new RoadTestPagerAdapter(this, getSupportFragmentManager());
        roadTestPager.setAdapter(roadTestPagerAdapter);
        roadTestSliderDash(0);
        roadTestPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                roadTestSliderDash(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        switch (createReportViewModel.getReportType()) {
            case "intake": {
                ArrayAdapter<String> finalAssessAdapter = new ArrayAdapter<>(CreateReportActivity.this, android.R.layout.simple_spinner_item, intakeFinalAssess);
                finalAssessSpinner.setAdapter(finalAssessAdapter);
                break;
            }
            case "monthly": {
                ArrayAdapter<String> finalAssessAdapter2 = new ArrayAdapter<>(CreateReportActivity.this, android.R.layout.simple_spinner_item, monthlyFinalAssess);
                finalAssessSpinner.setAdapter(finalAssessAdapter2);
                break;
            }
        }

    }


    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAddSignature() {
        hideKeyboard();
        finalComment = activityCreateReportBinding.finalCommentEditText.getText().toString();
        selectedFinalAssess = finalAssessSpinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(finalComment)){
           finalCommentEditText.setError("Final Comment is required");
           finalCommentEditText.requestFocus();
           return;
        }else if (selectedFinalAssess.isEmpty()){
            Alert.showFailed(getApplicationContext(),"Final Assessment is required");
            return;
        }else if (createReportViewModel.getIntakeVehicleReport()==null){
//           exteriorCheck.getVisibility()!=View.VISIBLE || glasscheck.getVisibility()!=View.VISIBLE || tiresCheck.getVisibility()!=View.VISIBLE || underBodyCheck.getVisibility()!=View.VISIBLE || underHoodCheck.getVisibility()!=View.VISIBLE || interiorCheck.getVisibility()!=View.VISIBLE || electricCheck.getVisibility()!=View.VISIBLE  || roadTestCheck.getVisibility()!=View.VISIBLE)
            Alert.showFailed(getApplicationContext(),"Intake report cannot be empty");
            return;
        }else if (acceptanceResult.getText().toString().equals("0")){
            Alert.showFailed(getApplicationContext(),"Total score must be greater than 0");
            acceptanceResult.requestFocus();
            return;
        }
        else {
            Log.i("Final Comment", finalComment);
            createReportViewModel.setFinalComment(finalComment);
            Log.i("FINAL ASSESS", selectedFinalAssess);
            createReportViewModel.setFinalStatus(selectedFinalAssess);
            Intent intent = new Intent(getApplicationContext(), SignatureActivity.class);
            startActivity(intent);
        }
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
            createReportViewModel.checkExterior(exteriorCheck);
        } else {
            exteriorFeature.setVisibility(View.GONE);
            exteriorToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkExterior(exteriorCheck);

        }

    }

    @Override
    public void onGlassFeature() {
        if (glassfeature.getVisibility() == View.GONE) {
            glassfeature.setVisibility(View.VISIBLE);
            glassToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkGlass(glasscheck);
        } else {
            glassfeature.setVisibility(View.GONE);
            glassToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkGlass(glasscheck);
        }

    }

    @Override
    public void onTiresFeature() {
        if (tiresFeature.getVisibility() == View.GONE) {
            tiresFeature.setVisibility(View.VISIBLE);
            tiresToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkTires(tiresCheck);

        } else {
            tiresFeature.setVisibility(View.GONE);
            tiresToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkTires(tiresCheck);


        }
    }

    @Override
    public void onUnderBodyFeature() {
        if (underBodyFeature.getVisibility() == View.GONE) {
            underBodyFeature.setVisibility(View.VISIBLE);
            underBodyToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkUnderBody(underBodyCheck);

        } else {
            underBodyFeature.setVisibility(View.GONE);
            underBodyToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkUnderBody(underBodyCheck);

        }
    }

    @Override
    public void onUnderHoodFeature() {
        if (underHoodFeature.getVisibility() == View.GONE) {
            underHoodFeature.setVisibility(View.VISIBLE);
            underHoodToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkUnderHood(underHoodCheck);

        } else {
            underHoodFeature.setVisibility(View.GONE);
            underHoodToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkUnderHood(underHoodCheck);

        }

    }

    @Override
    public void onInteriorFeature() {
        if (interiorFeature.getVisibility() == View.GONE) {
            interiorFeature.setVisibility(View.VISIBLE);
            interiorToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkInterior(interiorCheck);

        } else {
            interiorFeature.setVisibility(View.GONE);
            interiorToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkInterior(interiorCheck);

        }
    }

    @Override
    public void onElectricFeature() {
        if (electricFeature.getVisibility() == View.GONE) {
            electricFeature.setVisibility(View.VISIBLE);
            electricToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkElectric(electricCheck);
        } else {
            electricFeature.setVisibility(View.GONE);
            electricToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkElectric(electricCheck);
        }


    }

    @Override
    public void onRoadTestFeature() {
        if (roadTestFeature.getVisibility() == View.GONE) {
            roadTestFeature.setVisibility(View.VISIBLE);
            roadTestToggle.setImageResource(R.drawable.icon);
            createReportViewModel.checkRoadTest(roadTestCheck);
        } else {
            roadTestFeature.setVisibility(View.GONE);
            roadTestToggle.setImageResource(R.drawable.ic_icon);
            createReportViewModel.checkRoadTest(roadTestCheck);
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
        hideKeyboard();
        carColor = activityCreateReportBinding.editColor.getText().toString();
        mileage = activityCreateReportBinding.editMillage.getText().toString();
        regNum = activityCreateReportBinding.editCarRegNum.getText().toString();
        vin = activityCreateReportBinding.editVinNumber.getText().toString();
        if (TextUtils.isEmpty(carColor)) {
            carColoredit.setError(" Vehicle color is required");
            carColoredit.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mileage)) {
            mileageEdit.setError("Vehicle Mileage is required");
            mileageEdit.requestFocus();
            return;
        } else if (TextUtils.isEmpty(regNum)) {
            regNumEdit.setError("Registration Number is required");
            regNumEdit.requestFocus();
            return;
        } else if (yearText.getText().toString().isEmpty()) {
            Alert.showFailed(getApplicationContext(), "Vehicle year is required");
            yearText.requestFocus();
            return;
        } else if (modelText.getText().toString().isEmpty()) {
            Alert.showFailed(getApplicationContext(), "Vehicle model is required");
            modelText.requestFocus();
            return;
        } else if (makeText.getText().toString().isEmpty()) {
            Alert.showFailed(getApplicationContext(), "Vehicle make is required");
            makeText.requestFocus();
            return;
        } else {
            if (InternetConnection.getInstance(this).isOnline()) {
                Log.i("Intake", "INTAKE");
                CheckIntakeRequest.Request request = new CheckIntakeRequest.Request(regNum);
                createReportViewModel.checkIntakeReport(request);
            }else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }
        }
        Log.i("Button clicked", "Buttonnnnnnn");
    }

    @Override
    public void onSubmitVin() {
        hideKeyboard();
        editVinNo = activityCreateReportBinding.editVinNumber.getText().toString();
        if (TextUtils.isEmpty(editVinNo)) {
            editVin.setError("VIN number is required");
            editVin.requestFocus();
            return;
        }
        createReportViewModel.getCarVin(activityCreateReportBinding.editVinNumber.getText().toString());
    }

    @Override
    public void validateData(CreateReportResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(), response.getMessage());
        Log.i("REG REQUEST", "REG NUMBER VALIDATED");
        setViewAndChildrenEnabled(allInspection, true);
        allinspectionWraper.setEnabled(false);
        Alert.showSuccess(getApplicationContext(), "Vehicle Information Saved Successfully");
        createReportViewModel.setCarYear(selectedYear);
        createReportViewModel.setCarModel(selectedModel);
        createReportViewModel.setCarMake(selectedMake);
        createReportViewModel.setCarColor(carColor);
        createReportViewModel.setMillage(mileage);
        createReportViewModel.setVin(vin);
        createReportViewModel.setRegNo(regNum);
        inspectFeature.setVisibility(View.GONE);
        createReportViewModel.setVehicleInfo(true);
        vehincleInfoCheck.setVisibility(View.VISIBLE);

    }



    @Override
    public void onResponse(VinResponseData response) {
        hideLoading();
        selectedYear = response.getData().getYear();
        selectedModel = response.getData().getModel();
        selectedMake = response.getData().getMake();
        yearText.setText(selectedYear);
        modelText.setText(selectedModel);
        makeText.setText(selectedMake);

        Log.i("Response Success", "VIN RESPONSE SUCCESSful");

    }


    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        if (throwable != null) {
            try {
                yearText.setText("");
                modelText.setText("");
                makeText.setText("");
                ANError error = (ANError) throwable;
                VinResponseData response = gson.fromJson(error.getErrorBody(), VinResponseData.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage().getMessage());
                } else {
                    Alert.showFailed(getApplicationContext(), "Invalid Vin number or poor internet connection");
                }
            }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void handleValidateError(Throwable throwable) {
        hideLoading();
        if (throwable != null) {
            try {
                ANError error = (ANError) throwable;
                CreateReportResponse response = gson.fromJson(error.getErrorBody(), CreateReportResponse.class);
                if (error.getErrorBody() != null) {
                    dialogAlert = new Dialog(this,android.R.style.Theme_Material_Dialog_Alert);
                    dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogAlert.setCancelable(false);
                    dialogAlert.setContentView(R.layout.report_check_modal);
                    TextView title = dialogAlert.findViewById(R.id.title);
                    TextView message = dialogAlert.findViewById(R.id.messageText);
                    TextView back = dialogAlert.findViewById(R.id.button);
                    title.setText("Vehicle Exist");
                    message.setText(response.getMessage());
                    dialogAlert.show();
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogAlert.dismiss();
                        }
                    });
                } else {
                    Alert.showFailed(getApplicationContext(), "Poor internet connection");
                }

            }catch (IllegalStateException | JsonSyntaxException  | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }

    }

    @Override
    public void onStarting() {
//        dialog.show();
        showLoading();
    }

    @Override
    public void createSliderDash(int current_position) {
        if (exteriorSlide != null)
            exteriorSlide.removeAllViews();

        slider_dash = new ImageView[exteriorLayouts.length];
        for (int i = 0; i < exteriorLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            exteriorSlide.setLayoutParams(params);


            exteriorSlide.addView(slider_dash[i], params);
        }


    }

    @Override
    public void glassSliderDash(int current_position) {
        if (glassSlide != null)
            glassSlide.removeAllViews();

        slider_dash = new ImageView[glassLayouts.length];
        for (int i = 0; i < glassLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            glassSlide.setLayoutParams(params);


            glassSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void roadTestSliderDash(int current_position) {
        if (roadTestSlide != null)
            roadTestSlide.removeAllViews();

        slider_dash = new ImageView[roadTestLayouts.length];
        for (int i = 0; i < roadTestLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            roadTestSlide.setLayoutParams(params);


            roadTestSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void tiresSliderDash(int current_position) {
        if (tireSlide != null)
            tireSlide.removeAllViews();

        slider_dash = new ImageView[tiresLayouts.length];
        for (int i = 0; i < tiresLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            tireSlide.setLayoutParams(params);
            tireSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void undeBodySliderDash(int current_position) {

        if (underBodySlide != null)
            underBodySlide.removeAllViews();

        slider_dash = new ImageView[underBodyLayouts.length];
        for (int i = 0; i < underBodyLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            underBodySlide.setLayoutParams(params);


            underBodySlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void underHoodSliderDash(int current_position) {
        if (underHoodSlide != null)
            underHoodSlide.removeAllViews();

        slider_dash = new ImageView[underHoodLayouts.length];
        for (int i = 0; i < underHoodLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            underHoodSlide.setLayoutParams(params);


            underHoodSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void electricSliderDash(int current_position) {
        if (electricSlide != null)
            electricSlide.removeAllViews();

        slider_dash = new ImageView[electricalLayouts.length];
        for (int i = 0; i < electricalLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            electricSlide.setLayoutParams(params);


            electricSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void interiorSliderDash(int current_position) {
        if (interiorSlide != null)
            interiorSlide.removeAllViews();

        slider_dash = new ImageView[interiorLayouts.length];
        for (int i = 0; i < interiorLayouts.length; i++) {
            slider_dash[i] = new ImageView(this);
            if (i == current_position) {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_slider_dash));
            } else {
                slider_dash[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_slider_dash));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            interiorSlide.setLayoutParams(params);
            interiorSlide.addView(slider_dash[i], params);
        }

    }

    @Override
    public void onGetIntakeResult() {
        if ( exteriorCheck.getVisibility()!=View.VISIBLE || glasscheck.getVisibility()!=View.VISIBLE || tiresCheck.getVisibility()!=View.VISIBLE || underBodyCheck.getVisibility()!=View.VISIBLE || underHoodCheck.getVisibility()!=View.VISIBLE || interiorCheck.getVisibility()!=View.VISIBLE || electricCheck.getVisibility()!=View.VISIBLE  || roadTestCheck.getVisibility()!=View.VISIBLE){
            Alert.showFailed(getApplicationContext(),"Make sure all vehicle components are saved");
            return;
        }else {
            if (InternetConnection.getInstance(this).isOnline()){
                GetAcceptanceResultRequest resultRequest = new GetAcceptanceResultRequest(createReportViewModel.getIntakeVehicleReport());
                createReportViewModel.getAcceptanceResult(resultRequest);
            }else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }
        }
//        if (createReportViewModel.getIntakeVehicleReport()!= null) {
//            if (InternetConnection.getInstance(this).isOnline()) {
//                GetAcceptanceResultRequest resultRequest = new GetAcceptanceResultRequest(createReportViewModel.getIntakeVehicleReport());
//                createReportViewModel.getAcceptanceResult(resultRequest);
//            } else {
//                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
//            }
//
//        }else {
//            Alert.showFailed(getApplicationContext(),"Intake report cannot be empty");
//        }

    }

    @Override
    public void onAcceptanceResultResponse(CreateReportResponse response) {
        hideLoading();
        acceptanceResult.setText(response.getData());
        createReportViewModel.setIntakeAcceptanceValue(response.getData());
        Log.i("RESPONSE", "This is the response"+response);

    }

    @Override
    public void onAcceptanceResultError(Throwable throwable) {
        hideLoading();
        Log.i("AcceptanceError", "AcceptanceError");
        if (throwable!=null){
            try {
                if (throwable instanceof HttpException) {
                    Log.i("HTTP", "HTTP");
                    Converter<ResponseBody, CreateReportResponse> errorConverter = retrofitClient.getRetrofit().responseBodyConverter(CreateReportResponse.class, new Annotation[0]);
                    try {
                        CreateReportResponse error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                        Alert.showFailed(getApplicationContext(), error.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to connect to internet");
                }
            }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }


        }

    }

    @Override
    public void onClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MESSAGE");
        builder.setMessage("Are you sure you want to delete all saved components and start again?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(CreateReportActivity.this, new String[]{CAMERA, WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean externalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && externalStorage) {
                        Alert.showSuccess(getApplicationContext(), "Permission Granted, Now you can access camera");
                    } else {
                        Alert.showSuccess(getApplicationContext(), "Permission Denied, You cannot access camera");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA) && shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow access to both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }


                    }

                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(CreateReportActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);


            }
        }
    }

    public boolean checkIfAllCheckMarkAreVisible(){
        if (exteriorCheck.getVisibility()==View.VISIBLE && glasscheck.getVisibility()==View.VISIBLE && tiresCheck.getVisibility()==View.VISIBLE && underBodyCheck.getVisibility()==View.VISIBLE && underHoodCheck.getVisibility()==View.VISIBLE && interiorCheck.getVisibility()==View.VISIBLE && electricCheck.getVisibility()==View.VISIBLE  && roadTestCheck.getVisibility()==View.VISIBLE){
            return true;

        }
        Alert.showFailed(getApplicationContext(),"Make sure all vehicle Component has been saved");
    return false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createReportViewModel.onDispose();
        if (dialogAlert!=null && dialogAlert.isShowing() ){
            dialogAlert.cancel();
        }
    }
}
