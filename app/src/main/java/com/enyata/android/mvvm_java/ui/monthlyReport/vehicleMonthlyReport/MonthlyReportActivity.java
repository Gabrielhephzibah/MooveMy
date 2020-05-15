package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.databinding.ActivityMonthlyReport2Binding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListItem;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance.MaintenanceActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.electricMonthly.ElectricPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.exteriorMonthly.ExteriorViewPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.glassMonthly.GlassPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.interiorMonthly.InteriorPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.roadtestMonthly.RoadTestPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly.TirePagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underbodMonthly.UnderbodyPagerAdapterM;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underhodMonthly.UnderHoodPagerAdapterM;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.signature.MonthlySignature.MonthlySignatureActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MonthlyReportActivity extends BaseActivity<ActivityMonthlyReport2Binding,MonthlyReportViewModel> implements  MonthlyReportNavigator {
    @Inject
    ViewModelProviderFactory factory;
   MonthlyReportViewModel monthlyReportViewModel;
    ActivityMonthlyReport2Binding activityMonthlyReportBinding;
    @Inject
    Gson gson;
    RelativeLayout exteriorLayout;
    VehicleListItem vehicleListItem;
    ViewPager exteriorPager, glassPager, tiresPager, underBodyPager, underHoodPager, interiorPager, electricPager, roadTestPager;
    LinearLayout inspectFeature, exteriorFeature, glassfeature, tiresFeature, underBodyFeature, underHoodFeature, interiorFeature, electricFeature, roadTestFeature, signatureFeature;
    ImageView inspectToggle, exteriorToggle, glassToggle, tiresToggle, underBodyToggle, underHoodToggle, interiorToggle, electricToggle, roadTestToggle, signatureToggle;
    int[] electricalLayouts = {R.layout.power_lock_layout, R.layout.power_seat_layout, R.layout.power_stering_layout, R.layout.power_window_layout, R.layout.power_mirror_layout, R.layout.audio_system_layout, R.layout.computer_layout, R.layout.headlight_layout, R.layout.tail_light_layout, R.layout.signal_light_layout, R.layout.brake_light_layout, R.layout.parking_light_layout};
    LinearLayout exteriorSlide, glassSlide, tireSlide, underBodySlide, underHoodSlide, interiorSlide, electricSlide, roadTestSlide, allInspection, allinspectionWraper;
    ImageView[] slider_dash;
    TextView carMooveId, yearMakeModel;
    int[] exteriorLayouts = {R.layout.hood_layout, R.layout.front_bumper_layout, R.layout.fenders_layout, R.layout.door_layout, R.layout.roof_layout, R.layout.rear_layout, R.layout.rear_bumper_layout, R.layout.trunk_layout, R.layout.trim_layout, R.layout.fuel_door_layout, R.layout.paint_layout};
    int[] glassLayouts = {R.layout.windshield_layout, R.layout.window_layout, R.layout.mirrors_layout, R.layout.rear_window_layout};
    int[] interiorLayouts = {R.layout.seats_layout, R.layout.headliner_layout, R.layout.carpet_layout, R.layout.door_panel_layout, R.layout.glove_box_layout, R.layout.vanity_mirror_layout, R.layout.interior_trim_layout, R.layout.dashboard_layout, R.layout.dash_guage_layout, R.layout.air_cond_layout, R.layout.heater_layout, R.layout.defroster_layout};
    int[] roadTestLayouts = {R.layout.starting_layout, R.layout.idling_layout, R.layout.engine_layout, R.layout.accelaration_layout, R.layout.transmisssion_shift_layout, R.layout.steering_layout, R.layout.braking_layout, R.layout.suspension_performance_layout};
    int[] tiresLayouts = {R.layout.tires_layout, R.layout.wheel_layout, R.layout.spare_tire_layout};
    int[] underBodyLayouts = {R.layout.frame_layout, R.layout.exhaust_layout, R.layout.transmission_layout, R.layout.drive_axle_layout, R.layout.suspension_layout, R.layout.brake_system_layout};
    int[] underHoodLayouts = {R.layout.engine_com_layout, R.layout.battery_layout, R.layout.oil_layout, R.layout.fluid_layout, R.layout.wiring_layout, R.layout.belt_layout, R.layout.hoses_layout};

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
        inspectToggle = activityMonthlyReportBinding.inspectToggle;
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
        inspectFeature = activityMonthlyReportBinding.inspectFeature;
        exteriorSlide = activityMonthlyReportBinding.slideLayout;
        carMooveId = activityMonthlyReportBinding.mooveCarId;
        yearMakeModel = activityMonthlyReportBinding.yearMakeModel;
        glassSlide = findViewById(R.id.glassSlideLayout);
        interiorSlide = findViewById(R.id.interiorSliderLayout);
        tireSlide = findViewById(R.id.tireSlideLayout);
        underBodySlide = findViewById(R.id.underBodySlideLayout);
        underHoodSlide = findViewById(R.id.underHoodSlideLayout);
        roadTestSlide = findViewById(R.id.roadTestSlideLayout);
        electricSlide = findViewById(R.id.electricSlideLayout);
        Log.i("ID", monthlyReportViewModel.getMooveId());
        Log.i("Make", monthlyReportViewModel.getCarMakeMaint());
        Log.i("MODEL", monthlyReportViewModel.getCarModelMaint());
        Log.i("YEAR", monthlyReportViewModel.getCarYearMaint());
        carMooveId.setText(monthlyReportViewModel.getMooveId());
        yearMakeModel.setText(String.format("%s %s %s", monthlyReportViewModel.getCarYearMaint(), monthlyReportViewModel.getCarMakeMaint(), monthlyReportViewModel.getCarModelMaint()));

//        vehicleListItem = (VehicleListItem) getIntent().getSerializableExtra("data");
//        try {
//            carMooveId.setText(vehicleListItem.getMooveId());
//            yearMakeModel.setText(String.format("%s %s %s", vehicleListItem.getYear(), vehicleListItem.getMake(), vehicleListItem.getModel()));
//            monthlyReportViewModel.setMooveId(vehicleListItem.getMooveId());
//            monthlyReportViewModel.setcarModelMaint(vehicleListItem.getModel());
//            monthlyReportViewModel.setCarMakeMaint(vehicleListItem.getMake());
//            monthlyReportViewModel.setCarYearMaint(vehicleListItem.getYear());
//        }catch (NullPointerException e){
//            e.printStackTrace();
//            Log.i("EXCEPTION ERROR","EXCEPTION ERROR");
//        }





        ExteriorViewPagerAdapterM exteriorViewPagerAdapter = new ExteriorViewPagerAdapterM(this, getSupportFragmentManager());
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

        TirePagerAdapterM tirePagerAdapter = new TirePagerAdapterM(this, getSupportFragmentManager());
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
        Intent intent = new Intent(getApplicationContext(), MonthlySignatureActivity.class);
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
//            createReportViewModel.checkExterior(exteriorCheck);
        } else {
            exteriorFeature.setVisibility(View.GONE);
            exteriorToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkExterior(exteriorCheck);
        }

    }

    @Override
    public void onGlassFeature() {
        if (glassfeature.getVisibility() == View.GONE) {
            glassfeature.setVisibility(View.VISIBLE);
            glassToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkGlass(glasscheck);
        } else {
            glassfeature.setVisibility(View.GONE);
            glassToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkGlass(glasscheck);
        }


    }

    @Override
    public void onTiresFeature() {
        if (tiresFeature.getVisibility() == View.GONE) {
            tiresFeature.setVisibility(View.VISIBLE);
            tiresToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkTires(tiresCheck);

        } else {
            tiresFeature.setVisibility(View.GONE);
            tiresToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkTires(tiresCheck);


        }


    }

    @Override
    public void onUnderBodyFeature() {
        if (underBodyFeature.getVisibility() == View.GONE) {
            underBodyFeature.setVisibility(View.VISIBLE);
            underBodyToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkUnderBody(underBodyCheck);

        } else {
            underBodyFeature.setVisibility(View.GONE);
            underBodyToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkUnderBody(underBodyCheck);

        }

    }

    @Override
    public void onUnderHoodFeature() {
        if (underHoodFeature.getVisibility() == View.GONE) {
            underHoodFeature.setVisibility(View.VISIBLE);
            underHoodToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkUnderHood(underHoodCheck);

        } else {
            underHoodFeature.setVisibility(View.GONE);
            underHoodToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkUnderHood(underHoodCheck);

        }

    }

    @Override
    public void onInteriorFeature() {
        if (interiorFeature.getVisibility() == View.GONE) {
            interiorFeature.setVisibility(View.VISIBLE);
            interiorToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkInterior(interiorCheck);

        } else {
            interiorFeature.setVisibility(View.GONE);
            interiorToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkInterior(interiorCheck);

        }

    }

    @Override
    public void onElectricFeature() {
        if (electricFeature.getVisibility() == View.GONE) {
            electricFeature.setVisibility(View.VISIBLE);
            electricToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkElectric(electricCheck);
        } else {
            electricFeature.setVisibility(View.GONE);
            electricToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkElectric(electricCheck);
        }

    }

    @Override
    public void onRoadTestFeature() {
        if (roadTestFeature.getVisibility() == View.GONE) {
            roadTestFeature.setVisibility(View.VISIBLE);
            roadTestToggle.setImageResource(R.drawable.icon);
//            createReportViewModel.checkRoadTest(roadTestCheck);
        } else {
            roadTestFeature.setVisibility(View.GONE);
            roadTestToggle.setImageResource(R.drawable.ic_icon);
//            createReportViewModel.checkRoadTest(roadTestCheck);
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
    public void onSaveVehicleInfo() {

    }

    @Override
    public void onSubmitVin() {

    }


}
