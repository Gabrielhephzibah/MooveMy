package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceErrorResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleCountData;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleData;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleResponse;
import com.enyata.android.mvvm_java.databinding.ActivityMaintenanceBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Objects;

import javax.inject.Inject;

public class MaintenanceActivity extends BaseActivity<ActivityMaintenanceBinding, MaintenanceViewModel> implements MaintenanceNavigator {
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    MaintenanceViewModel maintenanceViewModel;
    ActivityMaintenanceBinding activityMaintenanceBinding;
    TextView mooveID, yearmakemodel, initialMileage, currentMileage;
    ImageView engineOilImage,brakePadImage,fuelSystemImage,oilFilterImage,airFilterImage,pollenFilterImage,suspensionSystemImage,variousBeltImage, sparkPlugImage,fuelFilterImage,engineCoolingImage,tireInflationImage,transmissionFilterImage;
    ImageView wiperBladeImage,chargingSytemImage,powerSteeringImage,transmissionOilImage,brakeFluidImage,tireRotationImage,alignmentImage,airFlowImage,airConditionImage,electronicImage,exhaustPipeImage,wheelRimImage;
    BottomSheetDialog addbottomSheet;
    CardView engineOilBox,brakePadBox,fuelSystemBox,oilFilterBox,airFilterBox,pollenFilterBox,suspensionSystemBox,variousBeltBox, sparkPlugBox,fuelFilterBox,engineCoolingaBox,tireInflationBox,transmissionFilterBox;
    CardView  wiperBladeBox,chargingSytemBox,powerSteeringBox,transmissionOilBox,brakeFluidBox,tireRotationBox,alignmentBox,airFlowBox,airConditionBox,electronicBox,exhaustPipeBox,wheelRimBox;
    TextView engineOilText, brakePadText, fuelSystemText,oilFilterText,airFilterText,pollenFilterText,suspensionSystemText,variousBeltText, sparkPlugText,fuelFilterText,engineCoolingaText,tireInflationText,transmissionFilterText;
    TextView  wiperBladeText,chargingSytemText,powerSteeringText,transmissionOilText,brakeFluidText,tireRotationText,alignmentText,airFlowText,airConditionText,electronicText,exhaustPipeText,wheelRimText;
    LinearLayout maintenanceListWrapper,maintenanceListLayout;

    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_maintenance;
    }

    @Override
    public MaintenanceViewModel getViewModel() {
        maintenanceViewModel = ViewModelProviders.of(this, factory).get(MaintenanceViewModel.class);
        return maintenanceViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maintenanceViewModel.setNavigator(this);
        activityMaintenanceBinding = getViewDataBinding();
        mooveID = activityMaintenanceBinding.mooveIdmaint;
        initialMileage = activityMaintenanceBinding.initialMileage;
        currentMileage = activityMaintenanceBinding.currentMileage;
        yearmakemodel = activityMaintenanceBinding.makeyearmodel;
        engineOilBox = activityMaintenanceBinding.engineOil;
        engineOilImage = activityMaintenanceBinding.engineOilImage;
        engineOilText = activityMaintenanceBinding.engineOilText;
        brakePadBox = activityMaintenanceBinding.brakePadBox;
        brakePadImage = activityMaintenanceBinding.brakePadImage;
        brakePadText = activityMaintenanceBinding.brakePadText;
        fuelSystemBox = activityMaintenanceBinding.fuelSystemBox;
        fuelSystemImage = activityMaintenanceBinding.fuelSystemImage;
        fuelSystemText = activityMaintenanceBinding.fuelSystemText;
        oilFilterBox = activityMaintenanceBinding.oilFilterBox;
        oilFilterImage = activityMaintenanceBinding.oilFilterImage;
        oilFilterText = activityMaintenanceBinding.oilFilterText;
        airFilterBox = activityMaintenanceBinding.airFilterBox;
        airFilterImage = activityMaintenanceBinding.airFilterImage;
        airFilterText = activityMaintenanceBinding.airFilterText;
        pollenFilterBox = activityMaintenanceBinding.pollenFilterBox;
        pollenFilterImage = activityMaintenanceBinding.pollenFilterImage;
        pollenFilterText = activityMaintenanceBinding.pollenFilterText;
        suspensionSystemBox = activityMaintenanceBinding.suspensionSystemBox;
        suspensionSystemImage = activityMaintenanceBinding.suspensionSystemImage;
        suspensionSystemText = activityMaintenanceBinding.suspensionSystemText;
        variousBeltBox = activityMaintenanceBinding.variousBeltBox;
        variousBeltImage = activityMaintenanceBinding.variousBeltImage;
        variousBeltText = activityMaintenanceBinding.variousBeltText;
        sparkPlugBox = activityMaintenanceBinding.sparkPlugBox;
        sparkPlugImage = activityMaintenanceBinding.sparkPlugImage;
        sparkPlugText = activityMaintenanceBinding.sparkPlugText;
        fuelFilterBox = activityMaintenanceBinding.fuelFilterBox;
        fuelFilterImage = activityMaintenanceBinding.fuelFilterImage;
        fuelFilterText = activityMaintenanceBinding.fuelFilterText;
        engineCoolingaBox = activityMaintenanceBinding.engineCoolingBox;
        engineCoolingImage = activityMaintenanceBinding.engineCoolingImage;
        engineCoolingaText = activityMaintenanceBinding.engineCoolingText;
        wiperBladeBox = activityMaintenanceBinding.wiperBladeBox;
        wiperBladeImage = activityMaintenanceBinding.wiperBladeImage;
        wiperBladeText = activityMaintenanceBinding.wiperBladeText;
        chargingSytemBox = activityMaintenanceBinding.chargingSystemBox;
        chargingSytemImage = activityMaintenanceBinding.chargingSystemImage;
        chargingSytemText = activityMaintenanceBinding.chargingSystemText;
        powerSteeringBox = activityMaintenanceBinding.powerSteeringBox;
        powerSteeringImage = activityMaintenanceBinding.powerSteeringImage;
        powerSteeringText = activityMaintenanceBinding.powerSteeringText;
        transmissionOilBox = activityMaintenanceBinding.transmissionOilBox;
        transmissionOilImage = activityMaintenanceBinding.transmissionOilImage;
        transmissionOilText = activityMaintenanceBinding.transmissionOilText;
        brakeFluidBox = activityMaintenanceBinding.breakFluidBox;
        brakeFluidImage = activityMaintenanceBinding.brakeFluidImage;
        brakeFluidText = activityMaintenanceBinding.brakeFluidText;
        tireRotationBox = activityMaintenanceBinding.tireRotationBox;
        tireRotationImage = activityMaintenanceBinding.tireRotationImage;
        tireRotationText = activityMaintenanceBinding.tireRotationText;
        alignmentBox = activityMaintenanceBinding.alignmentBox;
        alignmentImage = activityMaintenanceBinding.alignmentImage;
        alignmentText = activityMaintenanceBinding.alignmentText;
        airFlowBox = activityMaintenanceBinding.airflowBox;
        airFlowImage = activityMaintenanceBinding.airFlowImage;
        airFlowText = activityMaintenanceBinding.airFlowText;
        airConditionBox = activityMaintenanceBinding.airConditionBox;
        airConditionImage = activityMaintenanceBinding.airConditionImage;
        airConditionText = activityMaintenanceBinding.airConditionText;
        electronicBox = activityMaintenanceBinding.electronicBox;
        electronicImage = activityMaintenanceBinding.electronicImage;
        electronicText = activityMaintenanceBinding.electronicText;
        exhaustPipeBox = activityMaintenanceBinding.exhaustBox;
        exhaustPipeImage = activityMaintenanceBinding.exhaustImage;
        exhaustPipeText = activityMaintenanceBinding.exhaustText;
        tireInflationBox = activityMaintenanceBinding.tireInflationBox;
        tireInflationImage = activityMaintenanceBinding.tireInflationImage;
        tireInflationText = activityMaintenanceBinding.tireInflationText;
        wheelRimBox = activityMaintenanceBinding.wheelBox;
        wheelRimImage = activityMaintenanceBinding.wheelImage;
        wheelRimText = activityMaintenanceBinding.wheelText;
        transmissionFilterBox = activityMaintenanceBinding.transmissionFilterBox;
        transmissionFilterImage = activityMaintenanceBinding.transmissionFilterImage;
        transmissionFilterText = activityMaintenanceBinding.transmissionFilterText;
        maintenanceListWrapper = activityMaintenanceBinding.maintenanceListWrapper;
        maintenanceListLayout = activityMaintenanceBinding.maintenanceListLayout;
        mooveID.setText(maintenanceViewModel.getMooveId());
        yearmakemodel.setText(String.format("%s %s %s", maintenanceViewModel.getCarYearMaint(), maintenanceViewModel.getCarMakeMaint(), maintenanceViewModel.getCarModelMaint()));
        setViewAndChildrenEnabled(maintenanceListLayout,false);
        maintenanceListWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert.showInfo(getApplicationContext(),"Please enter current mileage");
            }
        });


    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), MonthlyReportActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onEngineOil() {
        Log.i("ENGINEOILBOX", engineOilBox.getCardBackgroundColor().toString());
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(engineOilBox,checkedTextView);
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onOilFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(oilFilterBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_oilfilter);
        name.setText("Oil filter");
        message.setText("Change oil filter as per recommended\nservice interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onAirFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(airFilterBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_airfilter);
        name.setText("Air filter");
        message.setText("Clean and Change air filter as per recommended\nservice interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onPollenFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(pollenFilterBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_pollenfilter);
        name.setText("Pollen filter");
        message.setText("Check and Change Pollen (A/C) Filter as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBrakePad() {
        Log.i("BRAKEPAD", brakePadBox.getCardBackgroundColor().toString());
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(brakePadBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_brakepad);
        name.setText("Brake pad");
        message.setText("Check Brake Pads, Discs, Caliper and Wheel Bearing ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onSuspensionSystem() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(suspensionSystemBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_suspension_system);
        name.setText("Suspension system");
        message.setText("Check All Suspension Parts as per recommended\nservice interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onVariousBelt() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(variousBeltBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_variousbelt);
        name.setText("Various belts");
        message.setText("Check All Belts and Hoses as per recommended\nservice interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onSparkPlug() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(sparkPlugBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_sparkplug);
        name.setText("Spark Plug");
        message.setText("Change Spark Plugs as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onFuelFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(fuelFilterBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_fuelfilter);
        name.setText("Fuel filter");
        message.setText("Check and Change Fuel Filter as per recommended service interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onEngineCooling() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(engineCoolingaBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_enginecooling);
        name.setText("Engine cooling system");
        message.setText("Check all heater-related components. Change radiator coolant as per interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onWiperBlade() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(wiperBladeBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_wiperblade);
        name.setText("Wiper blade");
        message.setText("Check and Change Wiper Blades/Washer as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onChargingSystem() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(chargingSytemBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_chargingsystem);
        name.setText("Charging system");
        message.setText("Check Starting and Charging System as well Battery Condition");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onPowerSteering() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(powerSteeringBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_powersteering);
        name.setText("Power steering fluid");
        message.setText("Check and Change Power Steering Fluid as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onTransmissionOil() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(transmissionOilBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_transmissionfluid);
        name.setText("Transmission fluid");
        message.setText("Check and Change Transmission Fluid as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onBrakeFluid() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(brakeFluidBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_brakefluid);
        name.setText("Brake fluid");
        message.setText("Bleed brakes to renew fluid and remove contamination from wear and tear");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onFuelSystem() {
        Log.i("FUELSYSTEM", fuelSystemBox.getCardBackgroundColor().toString());
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(fuelSystemBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_fuelsystem);
        name.setText("Fuel system");
        message.setText("Clean Fuel System Parts as per recommended service interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onTireRotation() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(tireRotationBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_tirerotation);
        name.setText("Tire rotation");
        message.setText("Check and Rotate Tires as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onAlignment() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(alignmentBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_alignment);
        name.setText("Alignment");
        message.setText("Check and Re-align All Tires as per recommended service interval ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                  onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onAirFlow() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(airFlowBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_airflow);
        name.setText("Air flow system");
        message.setText("Clean Air Flow System as per recommended service interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onAirCondition() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(airConditionBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_aircondition);
        name.setText("Air conditioning system");
        message.setText("Check Car Heating, Ventilating and Air Conditioning System");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onElectronic() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(electronicBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_electronic);
        name.setText("Electronic/ Lighting");
        message.setText("Check working conditions of Lights and Car Electronics ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });


    }

    @Override
    public void onExhaustPipe() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(exhaustPipeBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_exhaust);
        name.setText("Exhaust pipe");
        message.setText("Check for loose or broken exhaust clamps/supports and holes in pipe");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onTireInflation() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(tireInflationBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_tireinflation);
        name.setText("Tire inflation/ thread");
        message.setText("Check tires pressure. Check for cuts, bulges and excessive tread wear.");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onWheel() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(wheelRimBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_wheel);
        name.setText("Wheel rim");
        message.setText("Check tires pressure. Check for cuts, bulges and excessive tread wear. ");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @Override
    public void onTransmissionFilter() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
        CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
        LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
        bottomViewShowColor(transmissionFilterBox,checkedTextView);
        ImageView image = bottomView.findViewById(R.id.image);
        TextView name = bottomView.findViewById(R.id.name);
        TextView message = bottomView.findViewById(R.id.message);
        image.setImageResource(R.drawable.ic_wheel);
        name.setText("Transmission Filter");
        message.setText("Check and Change Transmission Filter as per recommended\nservice interval");
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout);
                } else {
                    checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                    checkedTextView.setTextColor(Color.parseColor("#373737"));
                    linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResponse(MaintenanceScheduleResponse response) {
        hideLoading();
        addbottomSheet.dismiss();
        setViewAndChildrenEnabled(maintenanceListLayout,true);
        maintenanceListWrapper.setEnabled(false);
        Log.i("RESPONSE", "REPONSE SUCCESSFUL" + response);

        MaintenanceScheduleCountData countData = response.getCount();
        initialMileage.setText(countData.getInitialMileage());
        currentMileage.setText(countData.getCurrentMileage());
        MaintenanceScheduleData data = response.getData();
        switch (data.getEngineOil()) {
            case "Change":{
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
               engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getOilFilter()) {
            case "Change":{
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                oilFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                oilFilterBox .setCardBackgroundColor(getResources().getColor(R.color.check_color));
                oilFilterImage .setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText .setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                oilFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getPollenFilter()) {
            case "Change":{
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getAirFilter()) {
            case "Change":{
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getBrakePad()){
            case "Change":{
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
               brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getSuspensionSystem()) {
            case "Change":{
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getVariousBelts()) {
            case "Change":{
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getSparkPlug()) {
            case "Change":{
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getFuelFilter()) {
            case "Change":{
                fuelFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                fuelFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                fuelFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                fuelFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                fuelFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                fuelFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getEngineCoolingSystem()) {
            case "Change":{
                engineCoolingaBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                engineCoolingImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineCoolingaText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                engineCoolingaBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                engineCoolingImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineCoolingaText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                engineCoolingaBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                engineCoolingImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineCoolingaText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getWiperBlades()) {
            case "Change":{
                wiperBladeBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                wiperBladeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wiperBladeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                wiperBladeBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                wiperBladeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wiperBladeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                wiperBladeBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                wiperBladeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wiperBladeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getChargingSystem()) {
            case "Change":{
                chargingSytemBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                chargingSytemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                chargingSytemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                chargingSytemBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                chargingSytemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                chargingSytemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                chargingSytemBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                chargingSytemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                chargingSytemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getPowerSteeringFluid()) {
            case "Change":{
                powerSteeringBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                powerSteeringImage.setColorFilter(Color.argb(255, 255, 255, 255));
                powerSteeringText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                powerSteeringBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                powerSteeringImage.setColorFilter(Color.argb(255, 255, 255, 255));
                powerSteeringText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                powerSteeringBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                powerSteeringImage.setColorFilter(Color.argb(255, 255, 255, 255));
                powerSteeringText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getTransmissionOil()) {
            case "Change":{
                transmissionOilBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                transmissionOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                transmissionOilBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                transmissionOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                transmissionOilBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                transmissionOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getBrakeFluid()) {
            case "Change":{
                brakeFluidBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                brakeFluidImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakeFluidText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                brakeFluidBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                brakeFluidImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakeFluidText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                brakeFluidBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                brakeFluidImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakeFluidText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getFuelSystem()){
            case "Change":{
               fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getTyreRotation()) {
            case "Change":{
                tireRotationBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                tireRotationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireRotationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                tireRotationBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                tireRotationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireRotationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                tireRotationBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                tireRotationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireRotationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getAlignment()) {
            case "Change":{
                alignmentBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                alignmentImage.setColorFilter(Color.argb(255, 255, 255, 255));
                alignmentText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                alignmentBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                alignmentImage.setColorFilter(Color.argb(255, 255, 255, 255));
                alignmentText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                alignmentBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                alignmentImage.setColorFilter(Color.argb(255, 255, 255, 255));
                alignmentText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getAirFlow()) {
            case "Change":{
                airFlowBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                airFlowImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFlowText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                airFlowBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                airFlowImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFlowText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                airFlowBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                airFlowImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFlowText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getAirConditioningSystem()) {
            case "Change":{
                airConditionBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                airConditionImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airConditionText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                airConditionBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                airConditionImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airConditionText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                airConditionBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                airConditionImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airConditionText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getElectronic()) {
            case "Change":{
                electronicBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                electronicImage.setColorFilter(Color.argb(255, 255, 255, 255));
                electronicText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                electronicBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                electronicImage.setColorFilter(Color.argb(255, 255, 255, 255));
                electronicText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                electronicBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                electronicImage.setColorFilter(Color.argb(255, 255, 255, 255));
                electronicText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getExhaustPipe()) {
            case "Change":{
                exhaustPipeBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                exhaustPipeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                exhaustPipeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                exhaustPipeBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                exhaustPipeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                exhaustPipeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                exhaustPipeBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                exhaustPipeImage.setColorFilter(Color.argb(255, 255, 255, 255));
                exhaustPipeText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getTireInflation()) {
            case "Change":{
                tireInflationBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                tireInflationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireInflationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                tireInflationBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                tireInflationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireInflationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                tireInflationBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                tireInflationImage.setColorFilter(Color.argb(255, 255, 255, 255));
                tireInflationText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getWheelRim()) {
            case "Change":{
                wheelRimBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                wheelRimImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wheelRimText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                wheelRimBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                wheelRimImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wheelRimText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                wheelRimBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                wheelRimImage.setColorFilter(Color.argb(255, 255, 255, 255));
                wheelRimText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getTransmissionFilter()) {
            case "Change":{
                transmissionFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                transmissionFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                transmissionFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                transmissionFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                transmissionFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                transmissionFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                transmissionFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

    }

    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        Log.i("Throwable", throwable.getMessage());
        Log.i("ERROR", "ERROR");
        try {
            if (throwable != null) {
                ANError error = (ANError) throwable;
                MaintenanceErrorResponse response = gson.fromJson(error.getErrorBody(), MaintenanceErrorResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getResponseMessage());
                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to Connect to the Internet");
                }
            }

        } catch (IllegalStateException | JsonSyntaxException exception) {
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }

    }


    @Override
    public void onAddCurrentMileage() {
        addbottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.add_currentmileage_bottom_modal, null);
        EditText editText = bottomView.findViewById(R.id.enterCurrentMileage);
        Button submit = bottomView.findViewById(R.id.submitMileage);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();
                if (InternetConnection.getInstance(MaintenanceActivity.this).isOnline()) {
                    showLoading();
                    MaintenanceScheduleRequest.Request request = new MaintenanceScheduleRequest.Request(maintenanceViewModel.getMooveId(), Integer.valueOf(value));
                    maintenanceViewModel.getMaintenanceSchedule(request);
                    Log.i("LOG", value);
                } else {
                    Alert.showFailed(getApplicationContext(), "No Internet Connection");
                }
            }
        });

        addbottomSheet.setContentView(bottomView);
        addbottomSheet.show();

    }

    public  void bottomViewShowColor(CardView cardView,CheckedTextView checkedTextView){
        if (cardView.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.change_color)){
            Log.i("TRUE","TRRUE");
            checkedTextView.setText("Confirm Change");
            checkedTextView.setCheckMarkDrawable(R.drawable.ic_confirmchangeicon);
        }else if (cardView.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.check_color)){
            checkedTextView.setText("Confirm Check");
            checkedTextView.setCheckMarkDrawable(R.drawable.ic_confirmcheck_icon);

        }else if (cardView.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.clean_color)){
            checkedTextView.setText("Confirm Clean");
            checkedTextView.setCheckMarkDrawable(R.drawable.ic_confirmclean);
        }else {
            Log.i("NONE","None of the above");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClickOfCheckTextView(CheckedTextView checkedTextView, LinearLayout linearLayout){
        if (checkedTextView.getText().toString() == "Confirm Change"){
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.change_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.change_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.change_color)));
        }else if (checkedTextView.getText().toString() == ("Confirm Check")){
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.check_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.check_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.check_color)));
        }else if (checkedTextView.getText().toString() == "Confirm Clean"){
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.clean_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.clean_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.clean_color)));
        }

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
}
