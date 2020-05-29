package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceListData;
import com.enyata.android.mvvm_java.data.model.api.request.CreateMaintenanceReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceErrorResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleCountData;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleData;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleResponse;
import com.enyata.android.mvvm_java.data.remote.RetrofitClient;
import com.enyata.android.mvvm_java.databinding.ActivityMaintenanceBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;

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
    ImageView brakePadCheck, fuelSystemCheck, engineOilCheck,oilFilterCheck,airFilterCheck,pollenFilterCheck,suspensionSystemCheck,variousBeltCheck, sparkPlugCheck,fuelFilterCheck,engineCoolingCheck,tireInflationCheck,transmissionFilterCheck;
    ImageView wiperBladeCheck,chargingSytemCheck,powerSteeringCheck,transmissionOilCheck,brakeFluidCheck,tireRotationCheck,alignmentCheck,airFlowCheck,airConditionCheck,electronicCheck,exhaustPipeCheck,wheelRimCheck;
    String status,engineOilColor,brakePadColor, fuelSystemColor;
    RetrofitClient retrofitClient = new RetrofitClient();


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
        brakePadCheck = activityMaintenanceBinding.brakePadCheck;
        engineOilCheck = activityMaintenanceBinding.engineOilCheck;
        fuelSystemCheck = activityMaintenanceBinding.fuelSystemCheck;
        oilFilterCheck = activityMaintenanceBinding.oilFilterCheck;
        airFilterCheck = activityMaintenanceBinding.airFilterCheck;
        pollenFilterCheck = activityMaintenanceBinding.pollenFilterCheck;
        brakePadCheck = activityMaintenanceBinding.brakePadCheck;
        suspensionSystemCheck = activityMaintenanceBinding.suspensionSystemCheck;
        variousBeltCheck = activityMaintenanceBinding.variousBeltCheck;
        sparkPlugCheck = activityMaintenanceBinding.sparkPlugCheck;
        fuelFilterCheck = activityMaintenanceBinding.fuelFilterCheck;
        engineCoolingCheck = activityMaintenanceBinding.engineCoolingCheck;
        wiperBladeCheck = activityMaintenanceBinding.wiperBladeCheck;
        chargingSytemCheck = activityMaintenanceBinding.chargingSystemCheck;
        powerSteeringCheck = activityMaintenanceBinding.powerSteeringCheck;
        transmissionOilCheck = activityMaintenanceBinding.transmissionOilCheck;
        brakeFluidCheck = activityMaintenanceBinding.brakefluidCheck;
        tireRotationCheck = activityMaintenanceBinding.tireRotationCheck;
        alignmentCheck = activityMaintenanceBinding.alignmentCheck;
        airFlowCheck = activityMaintenanceBinding.airFlowCheck;
        airConditionCheck = activityMaintenanceBinding.airConditionCheck;
        electronicCheck = activityMaintenanceBinding.electronicCheck;
        exhaustPipeCheck = activityMaintenanceBinding.exhaustCheck;
        tireInflationCheck = activityMaintenanceBinding.tireInflationCheck;
        wheelRimCheck = activityMaintenanceBinding.wheelCheck;
        transmissionFilterCheck = activityMaintenanceBinding.transmissionFilterCheck;
        initialMileage.setText(maintenanceViewModel.getInitialmileage());

        transmissionFilterBox = activityMaintenanceBinding.transmissionFilterBox;
        transmissionFilterImage = activityMaintenanceBinding.transmissionFilterImage;
        transmissionFilterText = activityMaintenanceBinding.transmissionFilterText;
        maintenanceListWrapper = activityMaintenanceBinding.maintenanceListWrapper;
        maintenanceListLayout = activityMaintenanceBinding.maintenanceListLayout;

        if (InternetConnection.getInstance(this).isOnline()){
            maintenanceViewModel.checkMaintenanceReport(maintenanceViewModel.getVehicleIdMaint());
        }else {
            Alert.showFailed(getApplicationContext(),"Unable to connect to internet");
        }

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
        if (engineOilBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && engineOilCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
      else if (engineOilCheck.getVisibility() == View.VISIBLE && engineOilBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white )) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
            return;
        }
        else {
            Log.i("ENGINEOILBOX", engineOilBox.getCardBackgroundColor().toString());
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(engineOilBox, checkedTextView);
            bottomSheet.setContentView(bottomView);
            bottomSheet.show();
            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    checkedTextView.toggle();
                    if (checkedTextView.isChecked()) {
                        onClickOfCheckTextView(checkedTextView, linearLayout, saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Save Report", "Save Report");
                    Log.i("Engine Status", status);
                    onSaveReport(engineOilBox,engineOilImage,engineOilText,engineOilCheck);
                    MaintenanceListData engineOil = new MaintenanceListData("Engine Oil", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(engineOil);
                    bottomSheet.dismiss();
                }
            });


        }

    }

    @Override
    public void onOilFilter() {
        if (oilFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && oilFilterCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (oilFilterCheck.getVisibility() == View.VISIBLE && oilFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ){
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
            return;
        }
        else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(oilFilterBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });
            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(oilFilterBox,oilFilterImage,oilFilterText,oilFilterCheck);
                    MaintenanceListData oilFilter = new MaintenanceListData("Oil Filter", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(oilFilter);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onAirFilter() {
        if (airFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && airFilterCheck.getVisibility()==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (airFilterCheck.getVisibility() == View.VISIBLE && airFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(airFilterBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(airFilterBox,airFilterImage,airFilterText,airFilterCheck);
                    MaintenanceListData airFilter = new MaintenanceListData("Air Filter", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(airFilter);
                    bottomSheet.dismiss();
                }
            });

        }
    }

    @Override
    public void onPollenFilter() {
        if (pollenFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && pollenFilterCheck.getVisibility()==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (pollenFilterCheck.getVisibility() == View.VISIBLE && pollenFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saverReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(pollenFilterBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saverReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saverReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(pollenFilterBox,pollenFilterImage,pollenFilterText,pollenFilterCheck);
                    MaintenanceListData pollenFilter = new MaintenanceListData("Pollen Filter", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(pollenFilter);
                    bottomSheet.dismiss();
                }
            });
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBrakePad() {
        if (brakePadBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && brakePadCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (brakePadCheck.getVisibility() == View.VISIBLE && brakePadBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ){
            Alert.showSuccess(getApplicationContext(),"Maintenance report Taken");
        }else {
            Log.i("BRAKEPAD", brakePadBox.getCardBackgroundColor().toString());
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(brakePadBox, checkedTextView);
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
                        onClickOfCheckTextView(checkedTextView, linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(brakePadBox,brakePadImage,brakePadText,brakePadCheck);
                    MaintenanceListData brakePad = new MaintenanceListData("Brake Pad", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(brakePad);
                    bottomSheet.dismiss();
                }
            });

        }
    }

    @Override
    public void onSuspensionSystem() {
        if (suspensionSystemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && suspensionSystemCheck.getVisibility()==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
        else if (suspensionSystemCheck.getVisibility() == View.VISIBLE && suspensionSystemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(suspensionSystemBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(suspensionSystemBox,suspensionSystemImage,suspensionSystemText,suspensionSystemCheck);
                    MaintenanceListData suspensionSystem = new MaintenanceListData("Suspension System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(suspensionSystem);
                    bottomSheet.dismiss();
                }
            });
        }
    }

    @Override
    public void onVariousBelt() {
        if (variousBeltBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && variousBeltCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (variousBeltCheck.getVisibility() == View.VISIBLE && variousBeltBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(variousBeltBox, checkedTextView);
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
                        onClickOfCheckTextView(checkedTextView, linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });
            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(variousBeltBox,variousBeltImage,variousBeltText,variousBeltCheck);
                    MaintenanceListData variousBelt = new MaintenanceListData("Various Belt", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(variousBelt);
                    bottomSheet.dismiss();
                }
            });


        }

    }

    @Override
    public void onSparkPlug() {
        if (sparkPlugBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && sparkPlugCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (sparkPlugCheck.getVisibility() == View.VISIBLE && sparkPlugBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(sparkPlugBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(sparkPlugBox,sparkPlugImage,sparkPlugText,sparkPlugCheck);
                    MaintenanceListData sparkPlug = new MaintenanceListData("SparkPlug", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(sparkPlug);
                    bottomSheet.dismiss();
                }
            });
        }


    }

    @Override
    public void onFuelFilter() {
        if (fuelFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && fuelFilterCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
        else if (fuelFilterCheck.getVisibility() == View.VISIBLE && fuelFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(fuelFilterBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });
            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(fuelFilterBox,fuelFilterImage,fuelFilterText,fuelFilterCheck);
                    MaintenanceListData fuelFilter = new MaintenanceListData("Fuel Filter", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(fuelFilter);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onEngineCooling() {
        if (engineCoolingaBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && engineCoolingCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
      else if (engineCoolingCheck.getVisibility() == View.VISIBLE && engineCoolingaBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(engineCoolingaBox, checkedTextView);
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
                        onClickOfCheckTextView(checkedTextView, linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(engineCoolingaBox,engineCoolingImage,engineCoolingaText,engineCoolingCheck);
                    MaintenanceListData engineCoolingSystem = new MaintenanceListData("EngineCooling System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(engineCoolingSystem);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onWiperBlade() {
        if (wiperBladeBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && wiperBladeCheck.getVisibility()==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (wiperBladeCheck.getVisibility() == View.VISIBLE && wiperBladeBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(wiperBladeBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(wiperBladeBox,wiperBladeImage,wiperBladeText,wiperBladeCheck);
                    MaintenanceListData wiperBlade = new MaintenanceListData("Wiper Blade", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(wiperBlade);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onChargingSystem() {
        if (chargingSytemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)&& chargingSytemCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
      else if (chargingSytemCheck.getVisibility() == View.VISIBLE && chargingSytemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(chargingSytemBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(chargingSytemBox,chargingSytemImage,chargingSytemText,chargingSytemCheck);
                    MaintenanceListData chargingSystem = new MaintenanceListData("Charging System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(chargingSystem);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onPowerSteering() {
        if (powerSteeringBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && powerSteeringCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (powerSteeringCheck.getVisibility() == View.VISIBLE && powerSteeringBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(powerSteeringBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(powerSteeringBox,powerSteeringImage,powerSteeringText,powerSteeringCheck);
                    MaintenanceListData powerSteeringFluid = new MaintenanceListData("PowerSteering Fluid", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(powerSteeringFluid);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onTransmissionOil() {
        if (transmissionOilBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && transmissionOilCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (transmissionOilCheck.getVisibility() == View.VISIBLE && transmissionOilBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(transmissionOilBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(transmissionOilBox,transmissionOilImage,transmissionOilText,transmissionOilCheck);
                    MaintenanceListData transmissionOil = new MaintenanceListData("Transmission Oil", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(transmissionOil);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onBrakeFluid() {
        if (brakeFluidBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && brakeFluidCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
      else if (brakeFluidCheck.getVisibility() == View.VISIBLE && brakeFluidBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(brakeFluidBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(brakeFluidBox,brakeFluidImage,brakeFluidText,brakeFluidCheck);
                    MaintenanceListData brakeFluid = new MaintenanceListData("Brake Fluid", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(brakeFluid);
                    bottomSheet.dismiss();
                }
            });
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onFuelSystem() {
        if (fuelSystemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && fuelSystemCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
        else if (fuelSystemCheck.getVisibility() == View.VISIBLE && fuelSystemBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)){
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            Log.i("FUELSYSTEM", fuelSystemBox.getCardBackgroundColor().toString());
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(fuelSystemBox, checkedTextView);
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
                        onClickOfCheckTextView(checkedTextView, linearLayout, saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("FUEL SYSTEM", status);
                    onSaveReport(fuelSystemBox,fuelSystemImage,fuelSystemText,fuelSystemCheck);
                    MaintenanceListData fuelSystem = new MaintenanceListData("Fuel System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(fuelSystem);
                    bottomSheet.dismiss();
                }
            });


        }
    }

    @Override
    public void onTireRotation() {
        if (tireRotationBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && tireRotationCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (tireRotationCheck.getVisibility() == View.VISIBLE && tireRotationBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(tireRotationBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(tireRotationBox,tireRotationImage,tireRotationText,tireRotationCheck);
                    MaintenanceListData tireRotation = new MaintenanceListData("Tire Rotation", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(tireRotation);
                    bottomSheet.dismiss();
                }
            });
        }


    }

    @Override
    public void onAlignment() {
        if (alignmentBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && alignmentCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (alignmentCheck.getVisibility() == View.VISIBLE && alignmentBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(alignmentBox, checkedTextView);
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
                  onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(alignmentBox,alignmentImage,alignmentText,alignmentCheck);
                    MaintenanceListData alignment = new MaintenanceListData("Alignment", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(alignment);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onAirFlow() {
        if (airFlowBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && airFlowCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (airFlowCheck.getVisibility() == View.VISIBLE && airFlowBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(airFlowBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(airFlowBox,airFlowImage,airFlowText,airFlowCheck);
                    MaintenanceListData airFlowSystem = new MaintenanceListData("AirFlow System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(airFlowSystem);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onAirCondition() {
        if (airConditionBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && airConditionCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (airConditionCheck.getVisibility() == View.VISIBLE && airConditionBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(airConditionBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });
            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(airConditionBox,airConditionImage,airConditionText,airConditionCheck);
                    MaintenanceListData airCondition = new MaintenanceListData("Air Conditioning System", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(airCondition);
                    bottomSheet.dismiss();
                }
            });
        }


    }

    @Override
    public void onElectronic() {
        if (electronicBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && electronicCheck.getVisibility()==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (electronicCheck.getVisibility() == View.VISIBLE && electronicBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(electronicBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(electronicBox,electronicImage,electronicText,electronicCheck);
                    MaintenanceListData electronic = new MaintenanceListData("Electronic/Lightning", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(electronic);
                    bottomSheet.dismiss();
                }
            });
        }


    }

    @Override
    public void onExhaustPipe() {
        if (exhaustPipeBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && exhaustPipeCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (exhaustPipeCheck.getVisibility() == View.VISIBLE && exhaustPipeBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(exhaustPipeBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(exhaustPipeBox,exhaustPipeImage,exhaustPipeText,exhaustPipeCheck);
                    MaintenanceListData exhaustPipe = new MaintenanceListData("Exhaust Pipe", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(exhaustPipe);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onTireInflation() {
        if (tireInflationBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && tireInflationCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (tireInflationCheck.getVisibility() == View.VISIBLE && tireInflationBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white)) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(tireInflationBox, checkedTextView);
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
                    onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(tireInflationBox,tireInflationImage,tireInflationText,tireInflationCheck);
                    MaintenanceListData tireInflation = new MaintenanceListData("Tire Inflation/Thread", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(tireInflation);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onWheel() {
        if (wheelRimBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && wheelRimCheck.getVisibility() == View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
      else if (wheelRimCheck.getVisibility() == View.VISIBLE && wheelRimBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(wheelRimBox, checkedTextView);
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
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(wheelRimBox,wheelRimImage,wheelRimText,wheelRimCheck);
                    MaintenanceListData wheelRim = new MaintenanceListData("Wheel Rim", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(wheelRim);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @Override
    public void onTransmissionFilter() {
        if (transmissionFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) && transmissionFilterCheck.getVisibility() ==View.GONE){
            Alert.showSuccess(getApplicationContext(),"Maintenance not needed");
            return;
        }
       else if (transmissionFilterCheck.getVisibility() == View.VISIBLE && transmissionFilterBox.getCardBackgroundColor().getDefaultColor() == getResources().getColor(R.color.white) ) {
            Alert.showSuccess(getApplicationContext(),"Maintenance report taken");
        }else {
            BottomSheetDialog bottomSheet = new BottomSheetDialog(MaintenanceActivity.this);
            View bottomView = getLayoutInflater().inflate(R.layout.maintenance_bottom_modal, null);
            CheckedTextView checkedTextView = bottomView.findViewById(R.id.checkTextView);
            LinearLayout linearLayout = bottomView.findViewById(R.id.checkTextViewBorder);
            Button saveReport = bottomView.findViewById(R.id.saveReport);
            bottomViewShowColor(transmissionFilterBox, checkedTextView);
            ImageView image = bottomView.findViewById(R.id.image);
            TextView name = bottomView.findViewById(R.id.name);
            TextView message = bottomView.findViewById(R.id.message);
            image.setImageResource(R.drawable.ic_wheel);
            name.setText("Transmission Filter");
            message.setText("Check and Change Transmission Filter as per recommended service interval");
            bottomSheet.setContentView(bottomView);
            bottomSheet.show();
            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    checkedTextView.toggle();
                    if (checkedTextView.isChecked()) {
                   onClickOfCheckTextView(checkedTextView,linearLayout,saveReport);
                    } else {
                        checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(Color.parseColor("#BEBEBE")));
                        checkedTextView.setTextColor(Color.parseColor("#373737"));
                        linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                    }
                }
            });

            saveReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSaveReport(transmissionFilterBox,transmissionFilterImage,transmissionFilterText,transmissionFilterCheck);
                    MaintenanceListData transmissionFilter = new MaintenanceListData("TransmissionFilter", status);
                    maintenanceViewModel.saveMaintenanceReportToLocalStorage(transmissionFilter);
                    bottomSheet.dismiss();
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResponse(MaintenanceScheduleResponse response) {
        hideLoading();
        addbottomSheet.dismiss();
        makeAllCheckMarkGone();

        setViewAndChildrenEnabled(maintenanceListLayout, true);
        maintenanceListWrapper.setEnabled(false);
        Log.i("RESPONSE", "REPONSE SUCCESSFUL" + response);

        MaintenanceScheduleCountData countData = response.getCount();
        initialMileage.setText(countData.getInitialMileage());
        currentMileage.setText(countData.getCurrentMileage());
        MaintenanceScheduleData data = response.getData();

        switch (data.getEngineOil()) {
            case "None" : {
                engineOilColor = "None";
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                engineOilImage.setColorFilter(Color.argb(0, 0, 0, 0));
                engineOilText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                engineOilColor = "Change";
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                engineOilColor = "Check";
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                engineOilColor = "Clean";
                engineOilBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                engineOilImage.setColorFilter(Color.argb(255, 255, 255, 255));
                engineOilText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getOilFilter()) {
            case "None" : {
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                oilFilterImage.setColorFilter(Color.argb(0, 0, 0, 0));
                oilFilterText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                oilFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                oilFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                oilFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                oilFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                oilFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getPollenFilter()) {
            case "None" : {
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                pollenFilterImage.setColorFilter(Color.argb(0, 0, 0, 0));
                pollenFilterText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                pollenFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                pollenFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                pollenFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getAirFilter()) {
            case "None" : {
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                airFilterImage.setColorFilter(Color.argb(0, 0, 0, 0));
                airFilterText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                airFilterBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                airFilterImage.setColorFilter(Color.argb(255, 255, 255, 255));
                airFilterText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getBrakePad()) {
            case "None" : {
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                brakePadImage.setColorFilter(Color.argb(0, 0, 0, 0));
                brakePadText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                brakePadColor = "Change";
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                brakePadColor = "Check";
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                brakePadColor = "Clean";
                brakePadBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                brakePadImage.setColorFilter(Color.argb(255, 255, 255, 255));
                brakePadText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getSuspensionSystem()) {
            case "None" : {
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                suspensionSystemImage.setColorFilter(Color.argb(0, 0, 0, 0));
                suspensionSystemText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                suspensionSystemBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                suspensionSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                suspensionSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        switch (data.getVariousBelts()) {
            case "None" : {
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                variousBeltImage.setColorFilter(Color.argb(0, 0, 0, 0));
                variousBeltText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                variousBeltBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                variousBeltImage.setColorFilter(Color.argb(255, 255, 255, 255));
                variousBeltText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getSparkPlug()) {
            case "None" : {
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                sparkPlugImage.setColorFilter(Color.argb(0, 0, 0, 0));
                sparkPlugText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change": {
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check": {
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean": {
                sparkPlugBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                sparkPlugImage.setColorFilter(Color.argb(255, 255, 255, 255));
                sparkPlugText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getFuelFilter()) {
            case "None" : {
                fuelFilterBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                fuelFilterImage.setColorFilter(Color.argb(0, 0, 0, 0));
                fuelFilterText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                engineCoolingaBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                engineCoolingImage.setColorFilter(Color.argb(0, 0, 0, 0));
                engineCoolingaText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                wiperBladeBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                wiperBladeImage.setColorFilter(Color.argb(0, 0, 0, 0));
                wiperBladeText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                chargingSytemBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                chargingSytemImage.setColorFilter(Color.argb(0, 0, 0, 0));
                chargingSytemText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                powerSteeringBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                powerSteeringImage.setColorFilter(Color.argb(0, 0, 0, 0));
                powerSteeringText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                transmissionOilBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                transmissionOilImage.setColorFilter(Color.argb(0, 0, 0, 0));
                transmissionOilText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                brakeFluidBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                brakeFluidImage.setColorFilter(Color.argb(0, 0, 0, 0));
                brakeFluidText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                fuelSystemImage.setColorFilter(Color.argb(0, 0, 0, 0));
                fuelSystemText.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case "Change":{
                fuelSystemColor = "Change";
               fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.change_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Check":{
                fuelSystemColor = "Check";
                fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.check_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
            case "Clean":{
                fuelSystemColor = "Clean";
                fuelSystemBox.setCardBackgroundColor(getResources().getColor(R.color.clean_color));
                fuelSystemImage.setColorFilter(Color.argb(255, 255, 255, 255));
                fuelSystemText.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }

        switch (data.getTyreRotation()) {
            case "None" : {
                tireRotationBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                tireRotationImage.setColorFilter(Color.argb(0, 0, 0, 0));
                tireRotationText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                alignmentBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                alignmentImage.setColorFilter(Color.argb(0, 0, 0, 0));
                alignmentText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                airFlowBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                airFlowImage.setColorFilter(Color.argb(0, 0, 0, 0));
                airFlowText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                airConditionBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                airConditionImage.setColorFilter(Color.argb(0, 0, 0, 0));
                airConditionText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                electronicBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                electronicImage.setColorFilter(Color.argb(0, 0, 0, 0));
                electronicText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                exhaustPipeBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                exhaustPipeImage.setColorFilter(Color.argb(0, 0, 0, 0));
                exhaustPipeText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                tireInflationBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                tireInflationImage.setColorFilter(Color.argb(0, 0, 0, 0));
                tireInflationText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                wheelRimBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                wheelRimImage.setColorFilter(Color.argb(0, 0, 0, 0));
                wheelRimText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
            case "None" : {
                transmissionFilterBox.setCardBackgroundColor(getResources().getColor(R.color.white));
                transmissionFilterImage.setColorFilter(Color.argb(0, 0, 0, 0));
                transmissionFilterText.setTextColor(Color.parseColor("#000000"));
                break;
            }
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
        if (throwable != null) {

            try {
                ANError error = (ANError) throwable;
                CreateReportResponse response = gson.fromJson(error.getErrorBody(), CreateReportResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to Connect to the Internet");
                }
            }
            catch (IllegalStateException | JsonSyntaxException  | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }

        }

        }


    @Override
    public void onSaveMaintenanceReport() {
        if (maintenanceViewModel.getMaintenanceReport() != null) {
            if (InternetConnection.getInstance(this).isOnline()) {
                CreateMaintenanceReportRequest reportRequest = new CreateMaintenanceReportRequest(maintenanceViewModel.getVehicleIdMaint(), maintenanceViewModel.getMaintenanceReport());
                maintenanceViewModel.createMaintenanceReport(reportRequest);
            } else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }
        }else {
            Alert.showFailed(getApplicationContext(),"Report cannot be empty");
        }

    }

    @Override
    public void maintenanceResponse(CreateReportResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(), response.getMessage());
        Intent intent = new Intent(getApplicationContext(), ResponseActivity.class);
        startActivity(intent);
        maintenanceViewModel.deleteMaintenanceReportArray(maintenanceViewModel.getMaintenanceReport());

    }

    @Override
    public void onStarting() {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);

    }

    @Override
    public void handleMaintError(Throwable throwable) {
        maintenanceViewModel.deleteMaintenanceReportArray(maintenanceViewModel.getMaintenanceReport());
        Intent intent = new Intent(getApplicationContext(), FailedActivity.class);
        startActivity(intent);
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

    @Override
    public void onCheckMaintenanceResponse(CreateReportResponse response) {
        hideLoading();
        Alert.showSuccess(getApplicationContext(),response.getMessage());

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void handleCheckMaintenanceError(Throwable throwable) {
        hideLoading();
        if (throwable != null) {
            try {
                ANError error = (ANError) throwable;
                CreateReportResponse response = gson.fromJson(error.getErrorBody(), CreateReportResponse.class);
                if (error.getErrorBody() != null) {
                    Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Dialog_Alert);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.report_check_modal);
                    TextView title = dialog.findViewById(R.id.title);
                    TextView message = dialog.findViewById(R.id.messageText);
                    TextView back = dialog.findViewById(R.id.button);
                    message.setText(response.getMessage());
                    dialog.show();
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
                           startActivity(intent);
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
    public void onStartingCheck() {
        showLoading();
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
                if (TextUtils.isEmpty(value)){
                    editText.setError("Current mileage is required");
                    editText.requestFocus();
                    return;
                }
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
    public  void onClickOfCheckTextView(CheckedTextView checkedTextView, LinearLayout linearLayout,Button button){
        if (checkedTextView.getText().toString() == "Confirm Change"){
            status = "Change";
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.change_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.change_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.change_color)));
            button.setEnabled(true);
        }else if (checkedTextView.getText().toString() == ("Confirm Check")){
            status = "Check";
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.check_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.check_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.check_color)));
            button.setEnabled(true);
        }else if (checkedTextView.getText().toString() == "Confirm Clean"){
            status = "Clean";
            checkedTextView.setCheckMarkTintList(ColorStateList.valueOf(getResources().getColor(R.color.clean_color)));
            checkedTextView.setTextColor(getResources().getColor(R.color.clean_color));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.clean_color)));
            button.setEnabled(true);
        }

    }

    public void onSaveReport(CardView cardView, ImageView boxImage, TextView textView, ImageView checkMark){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
        boxImage.setColorFilter(Color.argb(0, 0, 0, 0));
        textView.setTextColor(Color.parseColor("#000000"));
        checkMark.setVisibility(View.VISIBLE);
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

    public  void  makeAllCheckMarkGone(){
        engineOilCheck.setVisibility(View.GONE);
        oilFilterCheck.setVisibility(View.GONE);
        airFilterCheck.setVisibility(View.GONE);
        pollenFilterCheck.setVisibility(View.GONE);
        brakePadCheck.setVisibility(View.GONE);
        suspensionSystemCheck.setVisibility(View.GONE);
        variousBeltCheck.setVisibility(View.GONE);
        sparkPlugCheck.setVisibility(View.GONE);
        fuelFilterCheck.setVisibility(View.GONE);
        engineCoolingCheck.setVisibility(View.GONE);
        wiperBladeCheck.setVisibility(View.GONE);
        chargingSytemCheck.setVisibility(View.GONE);
        powerSteeringCheck.setVisibility(View.GONE);
        transmissionOilCheck.setVisibility(View.GONE);
        brakeFluidCheck.setVisibility(View.GONE);
        fuelSystemCheck.setVisibility(View.GONE);
        tireRotationCheck.setVisibility(View.GONE);
        alignmentCheck.setVisibility(View.GONE);
        airFlowCheck.setVisibility(View.GONE);
        airConditionCheck.setVisibility(View.GONE);
        electronicCheck.setVisibility(View.GONE);
        exhaustPipeCheck.setVisibility(View.GONE);
        tireInflationCheck.setVisibility(View.GONE);
        wheelRimCheck.setVisibility(View.GONE);
        transmissionFilterCheck.setVisibility(View.GONE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        maintenanceViewModel.onDispose();
    }
}
