package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.request.GetAcceptanceResultRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.remote.RetrofitClient;
import com.enyata.android.mvvm_java.databinding.ActivityMonthlyReport2Binding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.createReport.exterior.HoodFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.SpareTireFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.TiresFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.WheelFragment;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListItem;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance.MaintenanceActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.electricMonthly.ElectricPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.exteriorMonthly.ExteriorViewPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.glassMonthly.GlassPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.interiorMonthly.InteriorPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.roadtestMonthly.RoadTestPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly.SpareTireFragmentM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly.TirePagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly.TiresFragmentM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly.WheelFragmentM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underbodMonthly.UnderbodyPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underhodMonthly.UnderHoodPagerAdapterM;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.ui.signature.MonthlySignature.MonthlySignatureActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MonthlyReportActivity extends BaseActivity<ActivityMonthlyReport2Binding,MonthlyReportViewModel> implements  MonthlyReportNavigator, AdapterView.OnItemSelectedListener {
    @Inject
    ViewModelProviderFactory factory;
   MonthlyReportViewModel monthlyReportViewModel;
    private static final int REQUEST_CAMERA = 1;
    ActivityMonthlyReport2Binding activityMonthlyReportBinding;
    @Inject
    Gson gson;
   public boolean tire,wheel,spare = false;
   public  boolean audioSystem, brakeLight, computer, headlight, parkingLight,powerLock,powerMirror,powerSeat,powerSteering,powerWindow,signalLight,tailLight = false;
   public boolean door,fenders,fronBumper,fuelDoor,hood,paint,rearBumper,rear,roof,trim,trunk = false;
   public  boolean mirror,rearWindow,window,windShield = false;
   public  boolean airCondition,carpet,dashboard,dashguages,defroster,doorPanel,gloveBox,headLiner,heater,intriorTrim,seat,vanityMirror = false;
   public  boolean acceleration,braking,enginePerf,idling,starting,steering,suspensionPerf,transmissionShift = false;
   public  boolean brakeSystem,driveAxle,exhaust,frame,suspension,transmission = false;
   public  boolean battery, belt,engineComp,fluid,hoses,oil,wiring = false;
//   public boolean wheel = false;
//   public boolean spare = false;

    String status,tireStatus,wheelStatus;


    String finalComment,finalAssessment;
    RelativeLayout exteriorLayout;
    Spinner finalAssessSpinner;
    String[] monthlyFinalAssess = {"", "failed", "passed"};
    ViewPager exteriorPager, glassPager, tiresPager, underBodyPager, underHoodPager, interiorPager, electricPager, roadTestPager;
    LinearLayout inspectFeature, exteriorFeature, glassfeature, tiresFeature, underBodyFeature, underHoodFeature, interiorFeature, electricFeature, roadTestFeature, signatureFeature;
    ImageView inspectToggle, exteriorToggle, glassToggle, tiresToggle, underBodyToggle, underHoodToggle, interiorToggle, electricToggle, roadTestToggle, signatureToggle;
    int[] electricalLayouts = {R.layout.power_lock_layout, R.layout.power_seat_layout, R.layout.power_stering_layout, R.layout.power_window_layout, R.layout.power_mirror_layout, R.layout.audio_system_layout, R.layout.computer_layout, R.layout.headlight_layout, R.layout.tail_light_layout, R.layout.signal_light_layout};
    LinearLayout exteriorSlide, glassSlide, tireSlide, underBodySlide, underHoodSlide, interiorSlide, electricSlide, roadTestSlide, allInspection, allinspectionWraper;
    ImageView[] slider_dash;
    TextView carMooveId, yearMakeModel, monthlyResultTextView;
    int[] exteriorLayouts = {R.layout.hood_layout, R.layout.front_bumper_layout, R.layout.fenders_layout, R.layout.door_layout, R.layout.roof_layout, R.layout.rear_layout, R.layout.rear_bumper_layout, R.layout.trunk_layout, R.layout.trim_layout, R.layout.fuel_door_layout, R.layout.paint_layout};
    int[] glassLayouts = {R.layout.windshield_layout, R.layout.window_layout, R.layout.mirrors_layout, R.layout.rear_window_layout};
    int[] interiorLayouts = {R.layout.seats_layout, R.layout.headliner_layout, R.layout.carpet_layout, R.layout.door_panel_layout, R.layout.glove_box_layout, R.layout.vanity_mirror_layout, R.layout.interior_trim_layout, R.layout.dashboard_layout, R.layout.dash_guage_layout, R.layout.air_cond_layout, R.layout.heater_layout, R.layout.defroster_layout};
    int[] roadTestLayouts = {R.layout.starting_layout, R.layout.idling_layout, R.layout.engine_layout, R.layout.accelaration_layout, R.layout.transmisssion_shift_layout, R.layout.steering_layout, R.layout.braking_layout, R.layout.suspension_performance_layout};
    int[] tiresLayouts = {R.layout.tires_layout, R.layout.wheel_layout, R.layout.spare_tire_layout};
    int[] underBodyLayouts = {R.layout.frame_layout, R.layout.exhaust_layout, R.layout.transmission_layout, R.layout.drive_axle_layout, R.layout.suspension_layout, R.layout.brake_system_layout};
    int[] underHoodLayouts = {R.layout.engine_com_layout, R.layout.battery_layout, R.layout.oil_layout, R.layout.fluid_layout, R.layout.wiring_layout, R.layout.belt_layout, R.layout.hoses_layout};
    LinearLayout maintenanceReportLayout;
    EditText finalCommentEditText;
    ImageView tireCheck, glasscheck, exteriorCheck,  underBodyCheck, underHoodCheck, interiorCheck, roadTestCheck, electricCheck;;
    ExteriorViewPagerAdapterM exteriorViewPagerAdapter;
    TirePagerAdapterM tirePagerAdapter;
    RetrofitClient retrofitClient = new RetrofitClient();

    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_monthly_report2;
    }

    @Override
    public MonthlyReportViewModel getViewModel() {
        monthlyReportViewModel = ViewModelProviders.of(this, factory).get(MonthlyReportViewModel.class);
        return monthlyReportViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlyReportViewModel.setNavigator(this);
        activityMonthlyReportBinding = getViewDataBinding();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission() && checkExternalPermission()) {
            } else {
                requestPermission();
            }
        }
        exteriorLayout =activityMonthlyReportBinding.exteriorLayout;
        signatureToggle = activityMonthlyReportBinding.signatureToggle;
        exteriorPager = activityMonthlyReportBinding.exteriorPager;
        glassPager = activityMonthlyReportBinding.glassPager;
        tiresPager = activityMonthlyReportBinding.tiresPager;
        underHoodPager = activityMonthlyReportBinding.underHoodPager;
        underBodyPager = activityMonthlyReportBinding.underBodyPager;
        interiorPager = activityMonthlyReportBinding.interiorPager;
        electricPager = activityMonthlyReportBinding.electricPager;
        roadTestPager = activityMonthlyReportBinding.roadTestPager;
        exteriorFeature = activityMonthlyReportBinding.exteriorFeature;
        exteriorToggle = activityMonthlyReportBinding.exteriorToggle;
        glassfeature = activityMonthlyReportBinding.glassFeature;
        glassToggle = activityMonthlyReportBinding.glassToggle;
        tiresToggle = activityMonthlyReportBinding.tiresToggle;
        tiresFeature = activityMonthlyReportBinding.tiresFeature;
        underBodyFeature = activityMonthlyReportBinding.underBodyFeature;
        underBodyToggle = activityMonthlyReportBinding.underBodyToggle;
        underHoodFeature = activityMonthlyReportBinding.underHoodFeature;
        underHoodToggle = activityMonthlyReportBinding.underHoodToggle;
        interiorFeature = activityMonthlyReportBinding.interiorFeature;
        interiorToggle = activityMonthlyReportBinding.interiorToggle;
        electricToggle = activityMonthlyReportBinding.electricToggle;
        electricFeature = activityMonthlyReportBinding.electricFeature;
        roadTestFeature = activityMonthlyReportBinding.roadTestFeature;
        roadTestToggle = activityMonthlyReportBinding.roadTestToggle;
        signatureFeature = activityMonthlyReportBinding.signatureFeature;
        exteriorSlide = activityMonthlyReportBinding.slideLayout;
        finalAssessSpinner = activityMonthlyReportBinding.spinnerFinalAssess;
        carMooveId = activityMonthlyReportBinding.mooveCarId;
        yearMakeModel = activityMonthlyReportBinding.yearMakeModel;
        monthlyResultTextView = activityMonthlyReportBinding.acceptanceResulTextView;
        tireCheck = findViewById(R.id.tiresCheck);
        glasscheck = findViewById(R.id.glassCheck);
        exteriorCheck = findViewById(R.id.exteriorCheck);
        electricCheck = findViewById(R.id.electricCheck);
        roadTestCheck = findViewById(R.id.roadTestCheck);
        underBodyCheck = findViewById(R.id.underBodyCheck);
        underHoodCheck = findViewById(R.id.underHoodCheck);
        interiorCheck = findViewById(R.id.interiorCheck);
        glassSlide = findViewById(R.id.glassSlideLayout);
        interiorSlide = findViewById(R.id.interiorSliderLayout);
        tireSlide = findViewById(R.id.tireSlideLayout);
        underBodySlide = findViewById(R.id.underBodySlideLayout);
        underHoodSlide = findViewById(R.id.underHoodSlideLayout);
        roadTestSlide = findViewById(R.id.roadTestSlideLayout);
        electricSlide = findViewById(R.id.electricSlideLayout);
        maintenanceReportLayout = activityMonthlyReportBinding.maintenanceReportLayout;
        finalCommentEditText = activityMonthlyReportBinding.finalCommentEditText;
        Log.i("MONTHLYLISTARRAY", String.valueOf(monthlyReportViewModel.getMonthlyVehicleReport()));

        finalAssessSpinner.setOnItemSelectedListener(this);
        Log.i("ID_Monthly", monthlyReportViewModel.getMooveId());
        Log.i("Make_MONTHLY", monthlyReportViewModel.getCarMakeMaint());
        Log.i("MODEL_MONthly", monthlyReportViewModel.getCarModelMaint());
        Log.i("YEAR_Monthly", monthlyReportViewModel.getCarYearMaint());
        Log.i("VEHICLE ID_Monthly", monthlyReportViewModel.getVehicleIdMaint());
        Log.i("MILeage_Monthly", monthlyReportViewModel.getInitialmileage());
        if (InternetConnection.getInstance(this).isOnline()){
            monthlyReportViewModel.checkMonthlyReport(monthlyReportViewModel.getVehicleIdMaint());
        }else {
            Alert.showFailed(getApplicationContext(),"Unable to connect to internet");
        }

        carMooveId.setText(monthlyReportViewModel.getMooveId());
        yearMakeModel.setText(String.format("%s %s %s", monthlyReportViewModel.getCarYearMaint(), monthlyReportViewModel.getCarMakeMaint(), monthlyReportViewModel.getCarModelMaint()));
        ArrayAdapter<String> finalAssessAdapter2 = new ArrayAdapter<>(MonthlyReportActivity.this, android.R.layout.simple_spinner_item, monthlyFinalAssess);
        finalAssessSpinner.setAdapter(finalAssessAdapter2);


         exteriorViewPagerAdapter = new ExteriorViewPagerAdapterM(this, getSupportFragmentManager());
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


        GlassPagerAdapterM glassPagerAdater = new GlassPagerAdapterM(this, getSupportFragmentManager());
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


        InteriorPagerAdapterM interiorPagerAdapter = new InteriorPagerAdapterM(this, getSupportFragmentManager());
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


        ElectricPagerAdapterM electricPagerAdapter = new ElectricPagerAdapterM(this, getSupportFragmentManager());
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

        UnderbodyPagerAdapterM underbodyPagerAdapter = new UnderbodyPagerAdapterM(this, getSupportFragmentManager());
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

        RoadTestPagerAdapterM roadTestPagerAdapter = new RoadTestPagerAdapterM(this, getSupportFragmentManager());
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

        tirePagerAdapter = new TirePagerAdapterM(this, getSupportFragmentManager());
        tiresPager.setAdapter(tirePagerAdapter);
        tiresSliderDash(0);

        tiresPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tiresSliderDash(position);
                if (tiresPager.getCurrentItem() == 0 ) {
                    Log.i("Tire","TIRE");
                    TiresFragmentM tiresFragmentM = (TiresFragmentM)tirePagerAdapter.getItem(position);
                    status = tiresFragmentM.getSavedStatus();
                    Log.i("SPARE tIRE", status);
                }else if (tiresPager.getCurrentItem() == 1){
                    Log.i("RRRR","JJNJKKK");
                }else if (tiresPager.getCurrentItem() == 2){
                    Log.i("reeeee","hhhhhhh");
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        UnderHoodPagerAdapterM underHoodPagerAdapter = new UnderHoodPagerAdapterM(this, getSupportFragmentManager());
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


    }

    @Override
    public void back() {
        Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
        startActivity(intent);

    }

    @Override
    public void onAddSignature() {
        finalComment = activityMonthlyReportBinding.finalCommentEditText.getText().toString();
        finalAssessment = (String) finalAssessSpinner.getSelectedItem();

        if (TextUtils.isEmpty(finalComment)){
            finalCommentEditText.setError("Comment is required");
            finalCommentEditText.requestFocus();
            return;
        }
        else  if (finalAssessment.isEmpty()){
            Alert.showFailed(getApplicationContext(),"Final Assessment is required");
            return;
        }else if (monthlyReportViewModel.getMonthlyVehicleReport() == null) {
            Alert.showFailed(getApplicationContext(),"Monthly report cannot be empty");
            return;
        }else if (monthlyResultTextView.getText().toString().equals("0")){
            Alert.showFailed(getApplicationContext(), "Monthly result must be greater than 0");

        } else {
            Log.i("final comment", finalComment);
            Log.i("Final Assessment", finalAssessSpinner.getSelectedItem().toString());
            Log.i("Array of REport", monthlyReportViewModel.getMonthlyVehicleReport().toString());
            monthlyReportViewModel.setFinalAssessment(finalAssessSpinner.getSelectedItem().toString());
            monthlyReportViewModel.setFinalComment(finalComment);
            Intent intent = new Intent(getApplicationContext(), MonthlySignatureActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onInspectFeature() {
        if (inspectFeature.getVisibility() == View.GONE) {
            inspectFeature.setVisibility(View.VISIBLE);
            inspectToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);
        } else {
            inspectFeature.setVisibility(View.GONE);
            inspectToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onExteriorFeature() {
        if (exteriorFeature.getVisibility() == View.GONE) {
            exteriorFeature.setVisibility(View.VISIBLE);
            exteriorToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);
        } else {
            exteriorFeature.setVisibility(View.GONE);
            exteriorToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGlassFeature() {
        if (glassfeature.getVisibility() == View.GONE) {
            glassfeature.setVisibility(View.VISIBLE);
            glassToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            glassfeature.setVisibility(View.GONE);
            glassToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public void onTiresFeature() {
        if (tiresFeature.getVisibility() == View.GONE) {
            tiresFeature.setVisibility(View.VISIBLE);
            tiresToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            tiresFeature.setVisibility(View.GONE);
            tiresToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onUnderBodyFeature() {
        if (underBodyFeature.getVisibility() == View.GONE) {
            underBodyFeature.setVisibility(View.VISIBLE);
            underBodyToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            underBodyFeature.setVisibility(View.GONE);
            underBodyToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onUnderHoodFeature() {
        if (underHoodFeature.getVisibility() == View.GONE) {
            underHoodFeature.setVisibility(View.VISIBLE);
            underHoodToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            underHoodFeature.setVisibility(View.GONE);
            underHoodToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onInteriorFeature() {
        if (interiorFeature.getVisibility() == View.GONE) {
            interiorFeature.setVisibility(View.VISIBLE);
            interiorToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            interiorFeature.setVisibility(View.GONE);
            interiorToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onElectricFeature() {
        if (electricFeature.getVisibility() == View.GONE) {
            electricFeature.setVisibility(View.VISIBLE);
            electricToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);
        } else {
            electricFeature.setVisibility(View.GONE);
            electricToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onRoadTestFeature() {
        if (roadTestFeature.getVisibility() == View.GONE) {
            roadTestFeature.setVisibility(View.VISIBLE);
            roadTestToggle.setImageResource(R.drawable.icon);
          maintenanceReportLayout.setVisibility(View.GONE);
        } else {
            roadTestFeature.setVisibility(View.GONE);
            roadTestToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onSignatureFeature() {
        if (signatureFeature.getVisibility() == View.GONE) {
            signatureFeature.setVisibility(View.VISIBLE);
            signatureToggle.setImageResource(R.drawable.icon);
            maintenanceReportLayout.setVisibility(View.GONE);

        } else {
            signatureFeature.setVisibility(View.GONE);
            signatureToggle.setImageResource(R.drawable.ic_icon);
            maintenanceReportLayout.setVisibility(View.VISIBLE);
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
    public void onVehicleMaint() {
        Intent intent = new Intent(getApplicationContext(), MaintenanceActivity.class);
        startActivity(intent);

    }

    @Override
    public void onStarting() {
        showLoading();
    }

    @Override
    public void onResponse(CreateReportResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(),response.getMessage());

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        if (throwable != null) {
            try {
                ANError error = (ANError) throwable;
                CreateReportResponse response = gson.fromJson(error.getErrorBody(), CreateReportResponse.class);
                if (error.getErrorBody() != null) {
                    Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Dialog_Alert);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.report_check_button_modal);
                    Button button = dialog.findViewById(R.id.goToMaintenance);
                    TextView message = dialog.findViewById(R.id.messageText);
                    TextView back = dialog.findViewById(R.id.backButton);
                    message.setText(response.getMessage());
                    dialog.show();
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
                            startActivity(intent);

                        }
                    });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),MaintenanceActivity.class);
                            startActivity(intent);

                        }
                    });
                } else {
                    Alert.showFailed(getApplicationContext(), "Poor internet connection");
                }

            }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }


    }

    @Override
    public void onGetMonthlyResult() {
        if ( exteriorCheck.getVisibility()!=View.VISIBLE || glasscheck.getVisibility()!=View.VISIBLE || tireCheck.getVisibility()!=View.VISIBLE || underBodyCheck.getVisibility()!=View.VISIBLE || underHoodCheck.getVisibility()!=View.VISIBLE || interiorCheck.getVisibility()!=View.VISIBLE || electricCheck.getVisibility()!=View.VISIBLE  || roadTestCheck.getVisibility()!=View.VISIBLE){
            Alert.showFailed(getApplicationContext(),"Make sure all vehicle components are saved");
            return;
        }else {
            if (InternetConnection.getInstance(this).isOnline()){
                GetAcceptanceResultRequest resultRequest = new GetAcceptanceResultRequest(monthlyReportViewModel.getMonthlyVehicleReport());
               monthlyReportViewModel.getAcceptanceResult(resultRequest);
            }else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }
        }
        }




    @Override
    public void onAcceptanceMonthlyResult(CreateReportResponse response) {
        hideLoading();
        monthlyResultTextView.setText(response.getData());
        monthlyReportViewModel.setMonthlyAcceptanceValue(response.getData());
        Log.i("SUCCESSFUL", "this is the response" +response);

    }

    @Override
    public void onAcceptanceMonthlyResultError(Throwable throwable) {
        hideLoading();
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
    public void onSaveVehicleInfo() {

    }

    @Override
    public void onSubmitVin() {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        monthlyReportViewModel.onDispose();
    }

    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MonthlyReportActivity.this, new String[]{CAMERA, WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);
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
        new AlertDialog.Builder(MonthlyReportActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void checkTireFragment(){
        if (tire && wheel && spare){
            Log.i("TRUE", "SHOW CHECK");
            tireCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkElectricFragment(){
        if (audioSystem  && computer && headlight && powerLock && powerMirror && powerSeat && powerSteering && powerWindow && signalLight && tailLight){
            Log.i("TRUE", "SHOW CHECK");
            electricCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkExteriorFragment(){
        if (door && fenders && fronBumper && fuelDoor && hood && paint && rearBumper && rear && roof && trim && trunk ){
            Log.i("TRUE", "SHOW CHECK");
            exteriorCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkGlassFragment(){
        if (mirror && rearWindow && window && windShield ){
            Log.i("TRUE", "SHOW CHECK");
            glasscheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkInteriorFragment(){
        if (airCondition && carpet && dashboard && dashguages && defroster && doorPanel && gloveBox && headLiner && heater && intriorTrim && seat && vanityMirror){
            Log.i("TRUE", "SHOW CHECK");
            interiorCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkRoadTestFragment(){
        if (acceleration && braking && enginePerf && idling && starting && steering && suspensionPerf && transmissionShift){
            Log.i("TRUE", "SHOW CHECK");
            roadTestCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }

    public void checkUnderBodyFragment(){
        if (brakeSystem && driveAxle && exhaust && frame &&suspension && transmission ){
            Log.i("TRUE", "SHOW CHECK");
            underBodyCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }
    public void checkUnderHoodFragment(){
        if (battery && belt && engineComp && fluid && hoses && oil && wiring ){
            Log.i("TRUE", "SHOW CHECK");
            underHoodCheck.setVisibility(View.VISIBLE);
        }else {
            Log.i("FALSE", "DO NOT SHOW CHECK");
        }
    }
}
