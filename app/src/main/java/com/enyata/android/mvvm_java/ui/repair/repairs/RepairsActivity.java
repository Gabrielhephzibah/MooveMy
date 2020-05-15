package com.enyata.android.mvvm_java.ui.repair.repairs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.RepairReportArray;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.data.model.api.request.VehicleRepairReport;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailData;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.databinding.ActivityRepairsBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.ui.signature.SignatureActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.enyata.android.mvvm_java.BR.viewModel;

public class RepairsActivity extends BaseActivity<ActivityRepairsBinding, RepairsViewModel> implements RepairsNavigator {
    @Inject
    Gson gson;
    @Inject
    ViewModelProviderFactory factory;
    RepairsViewModel repairsViewModel;
    ImageView extriorToggle, glassToggle, tiresToggle, underbodyToggle, underHoodToggle, interiorToggle, electricToggle, roadTestToggle, signatureToggle;
    LinearLayout exteriorFragment, glassFragment, tiresFragment, underbodyFragment, underHoodFragment, interiorFragment, electricFragment, roadTestFragment, signatureFragment;
    LinearLayout hoodComment, frontBumperComment, fenderComment, doorComment, roofComment, rearComment, rearBumperComment, trunkComment, trimComment, fuelDoorComment, paintComment, windShieldComment, windowComment, mirrorComment, rearWindowComment, tireComment, wheelComment, spareTireComment, frameComment, exhaustComment, transmissionComment, driveAxleComment, suspensionComment, brakeSystemComment,engineCompComment, batteryComment, oilComment, fluidComment, wiringComment, beltComment, hosesComment, seatsComment, headLinerComment, carpetComment, doorPanelComment, gloveBoxComment, vanityMirrorComment, interiorTrimComment, dashboardComment,dashGuageComment, airCondComment, heaterComment, defrosterComment, powerLockComment, powerSeatComment, powerSteeringComment, powerWindowComment, powerMirrorComment, audioSystemComment, computerComment, headLightComment, tailLightComment, signalLightComment, brakeLightComment, parkingLightComment, startingComment, idlingComment, enginePerfComment, accelerationComment, transmissionShiftComment, steeringComment, brakingComment, suspensionPrefComment;
    ActivityRepairsBinding activityRepairsBinding;
    RepairItemList repairItemList;
    ProgressDialog dialog;
    String imageURL,status;
    TextView textView;
    String mechanicSignatureUrl, supervisorSignatureUrl;
    CompositeDisposable disposable = new CompositeDisposable();
    TextView hoodStatus,fontBumperStatus,fenderStatus,doorStatus,roofStatus,rearStatus,rearBumperStatus,trunkStatus,trimStatus,fuelDoorStatus,paintStatus,windShieldStatus,windowStatus,mirrorStatus,rearWindowStatus,tyresStatus,wheelStatus,spareTyreStatus,frameStatus,exhaustStatus,transmissionStatus,driveAxleStatus,suspensionStatus,brakeSystemStatus,engineCompartmentStatus,batteryStatus,oilStatus,fluidStatus,wiringStatus,beltStatus,hosesStatus,seatStatus,headlinerStatus,carpetStatus,doorPanelStatus,gloveBoxStatus,vanityMirrorStatus,interiorTrimStatus,dashboardStatus,dashGuagesStatus,airConditionerStatus,heaterStatus,defrosterStatus,powerLockStatus,powerSeatStatus,powerSteeringStatus,powerWindowStatus,powerMirrorStatus,audioSystemStatus,computerStatus,headlightStatus,tailLightStatus,signalLightStatus,brakeLightStatus,parkingLightStatus,startingStatus,idlingStatus,enginePerformanceStatus,accelerationStatus,transmissionShiftStatus,steeringStatus,brakingStatus,suspensionPerformanceStatus;
    private ApiService mAPIService;
    TextView carMooveId, yearMakeModel;

    String hoodEditComment, fontBumperEditComment, fenderEditComment, doorEditComment, roofEditComment, rearEditComment, rearBumperEditComment, trunkEditComment, trimEditComment, fuelDoorEditComment, paintEditComment, windShieldEditComment, windowEditComment, mirrorsEditComment, rearWindowEditComment, tiresEditComment, wheelEditComment, spareTireEditComment, frameEditComment, exhaustEditComment, transmissionEditComment, driveAxleEditComment, suspensionEditComment, brakeSystemEditComment, engineCompEditComment, batteryEditComment, oilEditComment, fluidEditComment, wiringEditComment, beltEditComment, hosesEditComment, seatsEditComment, headlinerEditComment, carpetEditComment, doorPanelEditComment, gloveBoxEditComment, vanityMirrorEditComment, interiorTrimEdittComment, dashBoardEditComment, dashGuagesEditComment, airCondEditComment, heaterEditComment, defrosterEditComment, powerLockEditComment, powerSeatEditComment, powerSteeringEditComment, powerWindowEditComment, powerMirrorEditComment, audioSystemEditComment, computerEditComment, headLightEditComment, tailLightEditComment, signalLightEditComment, brakeLightEditComment, parkingLightEditComment, startingEditComment, idlingEditComment, enginePerfEditComment, accelerationEditComment, transShiftEditComment, steeringEditComment, brakingEditComment, suspensionPerfEditComment;


    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repairs;
    }

    @Override
    public RepairsViewModel getViewModel() {
        repairsViewModel = ViewModelProviders.of(this, factory).get(RepairsViewModel.class);
        return repairsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repairsViewModel.setNavigator(this);
        activityRepairsBinding = getViewDataBinding();
        Map config = new HashMap();
        config.put("cloud_name", "dtt1nmogz");
        config.put("api_key", "754277299533971");
        config.put("api_secret", "hwuDlRgCtSpxKOg9rcY43AtsZvw");


        mAPIService = ApiUtils.getAPIService();


        extriorToggle = activityRepairsBinding.exteriorToggle;
        exteriorFragment = activityRepairsBinding.exteriorFragment;
        glassFragment = activityRepairsBinding.glassFragment;
        glassToggle = activityRepairsBinding.glassToggle;
        tiresFragment = activityRepairsBinding.tiresFragment;
        tiresToggle = activityRepairsBinding.tiresToggle;
        underbodyFragment = activityRepairsBinding.underBodyFragment;
        underbodyToggle = activityRepairsBinding.underBodyToggle;
        underHoodFragment = activityRepairsBinding.underHoodFragment;
        underHoodToggle = activityRepairsBinding.underHoodToggle;
        interiorFragment = activityRepairsBinding.interiorFragment;
        interiorToggle = activityRepairsBinding.interiorToggle;
        electricFragment = activityRepairsBinding.electricFragment;
        electricToggle = activityRepairsBinding.electricToggle;
        roadTestToggle = activityRepairsBinding.roadTestToggle;
        roadTestFragment = activityRepairsBinding.roadTestFragment;
        signatureToggle = activityRepairsBinding.signatureToggle;
        signatureFragment = activityRepairsBinding.signatureFragment;
        carMooveId = activityRepairsBinding.mooveCarId;
        yearMakeModel = activityRepairsBinding.yearMakeModel;
        hoodComment = activityRepairsBinding.hoodComment;
        frontBumperComment = activityRepairsBinding.fontBumperComment;
        fenderComment = activityRepairsBinding.fenderComment;
        doorComment = activityRepairsBinding.doorComment;
        roofComment = activityRepairsBinding.roofComment;
        rearComment = activityRepairsBinding.rearComment;
        rearBumperComment = activityRepairsBinding.rearBumperComment;
        trunkComment = activityRepairsBinding.trunkComment;
        trimComment = activityRepairsBinding.trimComment;
        fuelDoorComment = activityRepairsBinding.fuelDoorComment;
        paintComment = activityRepairsBinding.paintComment;
        windShieldComment = activityRepairsBinding.windShieldComment;
        windowComment = activityRepairsBinding.windowComment;
        mirrorComment = activityRepairsBinding.mirrorComment;
        rearWindowComment = activityRepairsBinding.rearWindowComment;
        tireComment = activityRepairsBinding.tiresComment;
        wheelComment = activityRepairsBinding.wheelComment;
        spareTireComment = activityRepairsBinding.spareTireComment;
        frameComment = activityRepairsBinding.frameComment;
        exhaustComment = activityRepairsBinding.exhaustComment;
        transmissionComment = activityRepairsBinding.transmissionComment;
        driveAxleComment = activityRepairsBinding.driveAxleComment;
        suspensionComment = activityRepairsBinding.suspensionComment;
        brakeSystemComment = activityRepairsBinding.brakeSystemComment;
        engineCompComment = activityRepairsBinding.engineCompComment;
        batteryComment = activityRepairsBinding.batteryComment;
        oilComment = activityRepairsBinding.oilComment;
        fluidComment = activityRepairsBinding.fluidComment;
        wiringComment = activityRepairsBinding.wiringComment;
        beltComment = activityRepairsBinding.beltComment;
        hosesComment = activityRepairsBinding.hosesComment;
        seatsComment = activityRepairsBinding.seatComment;
        headLinerComment = activityRepairsBinding.headLinerComment;
        carpetComment = activityRepairsBinding.carpetComment;
        doorPanelComment = activityRepairsBinding.doorPanelComment;
        gloveBoxComment = activityRepairsBinding.gloveBoxComment;
        vanityMirrorComment = activityRepairsBinding.vanityMirrorComment;
        interiorTrimComment = activityRepairsBinding.interiorTrimComment;
        dashboardComment = activityRepairsBinding.dashBoardComment;
        dashGuageComment = activityRepairsBinding.dashGuageComment;
        airCondComment = activityRepairsBinding.airCondComment;
        heaterComment = activityRepairsBinding.heaterComment;
        defrosterComment = activityRepairsBinding.defrosterComment;
        powerLockComment = activityRepairsBinding.powerLockComment;
        powerSeatComment = activityRepairsBinding.powerSeatComment;
        powerSteeringComment = activityRepairsBinding.powerSteeringComment;
        powerWindowComment = activityRepairsBinding.powerWindowComment;
        powerMirrorComment = activityRepairsBinding.powerMirrorComment;
        audioSystemComment = activityRepairsBinding.audioComment;
        computerComment = activityRepairsBinding.computerComment;
        headLightComment = activityRepairsBinding.headLightComment;
        tailLightComment = activityRepairsBinding.tailLightComment;
        signalLightComment = activityRepairsBinding.signalLightComment;
        brakeLightComment = activityRepairsBinding.brakeLightComment;
        parkingLightComment = activityRepairsBinding.parkingLightComment;
        startingComment = activityRepairsBinding.startingComment;
        idlingComment = activityRepairsBinding.idlingComment;
        enginePerfComment = activityRepairsBinding.engPerfComment;
        accelerationComment = activityRepairsBinding.accelerationComment;
        transmissionShiftComment = activityRepairsBinding.transmissionShiftComment;
        steeringComment = activityRepairsBinding.steeringComment;
        brakingComment = activityRepairsBinding.brakingComment;
        suspensionPrefComment = activityRepairsBinding.suspensionPrefComment;


        repairItemList = (RepairItemList) getIntent().getSerializableExtra("data");

        Log.i("ID", repairItemList.getMooveId());
        Log.i("Make", repairItemList.getMake());
        Log.i("MODEL", repairItemList.getModel());
        Log.i("YEAR", repairItemList.getYear());
        Log.i("VehincleId", repairItemList.getVehicleId());
        Log.i("IDD", repairItemList.getId());
        repairsViewModel.setVehicleId(repairItemList.getVehicleId());
        repairsViewModel.getInspectorDetails(repairItemList.getId());

        carMooveId.setText(repairItemList.getMooveId());
        yearMakeModel.setText(String.format("%s %s %s", repairItemList.getYear(), repairItemList.getMake(), repairItemList.getModel()));


    }


    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), RepairListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExterior() {
        if (exteriorFragment.getVisibility() == View.GONE) {
            exteriorFragment.setVisibility(View.VISIBLE);
            extriorToggle.setImageResource(R.drawable.icon);
        } else if (exteriorFragment.getVisibility() == View.VISIBLE) {
            exteriorFragment.setVisibility(View.GONE);
            extriorToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onGlass() {
        if (glassFragment.getVisibility() == View.GONE) {
            glassFragment.setVisibility(View.VISIBLE);
            glassToggle.setImageResource(R.drawable.icon);
        } else if (glassFragment.getVisibility() == View.VISIBLE) {
            glassFragment.setVisibility(View.GONE);
            glassToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onTires() {
        if (tiresFragment.getVisibility() == View.GONE) {
            tiresFragment.setVisibility(View.VISIBLE);
            tiresToggle.setImageResource(R.drawable.icon);
        } else if (tiresFragment.getVisibility() == View.VISIBLE) {
            tiresFragment.setVisibility(View.GONE);
            tiresToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onUnderBody() {
        if (underbodyFragment.getVisibility() == View.GONE) {
            underbodyFragment.setVisibility(View.VISIBLE);
            underbodyToggle.setImageResource(R.drawable.icon);
        } else if (underbodyFragment.getVisibility() == View.VISIBLE) {
            underbodyFragment.setVisibility(View.GONE);
            underbodyToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onUnderHood() {
        if (underHoodFragment.getVisibility() == View.GONE) {
            underHoodFragment.setVisibility(View.VISIBLE);
            underHoodToggle.setImageResource(R.drawable.icon);
        } else if (underHoodFragment.getVisibility() == View.VISIBLE) {
            underHoodFragment.setVisibility(View.GONE);
            underHoodToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onInterior() {
        if (interiorFragment.getVisibility() == View.GONE) {
            interiorFragment.setVisibility(View.VISIBLE);
            interiorToggle.setImageResource(R.drawable.icon);
        } else if (interiorFragment.getVisibility() == View.VISIBLE) {
            interiorFragment.setVisibility(View.GONE);
            interiorToggle.setImageResource(R.drawable.ic_icon);
        }
    }

    @Override
    public void onElectric() {
        if (electricFragment.getVisibility() == View.GONE) {
            electricFragment.setVisibility(View.VISIBLE);
            electricToggle.setImageResource(R.drawable.icon);
        } else if (electricFragment.getVisibility() == View.VISIBLE) {
            electricFragment.setVisibility(View.GONE);
            electricToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onRoadTest() {
        if (roadTestFragment.getVisibility() == View.GONE) {
            roadTestFragment.setVisibility(View.VISIBLE);
            roadTestToggle.setImageResource(R.drawable.icon);
        } else if (roadTestFragment.getVisibility() == View.VISIBLE) {
            roadTestFragment.setVisibility(View.GONE);
            roadTestToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onSignature() {
        if (signatureFragment.getVisibility() == View.GONE) {
            signatureFragment.setVisibility(View.VISIBLE);
            signatureToggle.setImageResource(R.drawable.icon);
        } else if (signatureFragment.getVisibility() == View.VISIBLE) {
            signatureFragment.setVisibility(View.GONE);
            signatureToggle.setImageResource(R.drawable.ic_icon);
        }

    }

    @Override
    public void onAddSignature() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(RepairsActivity.this);
        View bottomView = getLayoutInflater().inflate(R.layout.repair_signature_layout, null);
        Button repairSubmit = bottomView.findViewById(R.id.repairSubmit);
        SignaturePad supervisorSignature = bottomView.findViewById(R.id.supervisorSignature);
        SignaturePad mechanicSignature = bottomView.findViewById(R.id.mechanicSignature);
        TextView saveSupervisorSignature = bottomView.findViewById(R.id.saveSupervisorSignature);
        TextView clearSupervisorSignature = bottomView.findViewById(R.id.clearSupervisorSignature);
        TextView saveMechanicSignature = bottomView.findViewById(R.id.saveMechanicSignature);
        TextView clearMechanicSignature = bottomView.findViewById(R.id.clearMechanicSignature);
        saveMechanicSignature.setEnabled(false);
        saveSupervisorSignature.setEnabled(false);
        clearMechanicSignature.setEnabled(false);
        clearSupervisorSignature.setEnabled(false);
        bottomSheet.setContentView(bottomView);
        bottomSheet.show();

        supervisorSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveSupervisorSignature.setEnabled(true);
                clearSupervisorSignature.setEnabled(true);


            }

            @Override
            public void onClear() {
                saveSupervisorSignature.setEnabled(false);
                clearSupervisorSignature.setEnabled(false);

            }
        });

        mechanicSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveMechanicSignature.setEnabled(true);
                clearMechanicSignature.setEnabled(true);

            }

            @Override
            public void onClear() {
                saveMechanicSignature.setEnabled(false);
                clearMechanicSignature.setEnabled(false);

            }
        });

        saveSupervisorSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = supervisorSignature.getSignatureBitmap();
                Log.i("SUPERVISOE", String.valueOf(supervisorSignature.getSignatureBitmap()));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] supervisorByteArray = byteArrayOutputStream.toByteArray();
                showLoading();

                String requestId = MediaManager.get().upload(supervisorByteArray)
                        .unsigned("ht7lodiw")
                        .callback(new UploadCallback() {
                            @Override
                            public void onStart(String requestId) {
                                Log.i("START", "STARTTTTT");
                                showLoading();
//                                dialog = new ProgressDialog(RepairsActivity.this);
//                                dialog.setMessage("Saving......");
//                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                dialog.setCancelable(false);
//                                dialog.show();

                            }

                            @Override
                            public void onProgress(String requestId, long bytes, long totalBytes) {
                                Double progress = (double) bytes / totalBytes;
                                Log.i("PROGRESS", "PROGRESS");
//                                dialog.show();
                            }

                            @Override
                            public void onSuccess(String requestId, Map resultData) {
                                if (resultData != null) {
                                    Log.i("SUCCESS", "SUCCESS");
                                    imageURL = (String) resultData.get("url");
                                    hideLoading();
//                                    dialog.dismiss();
                                    Alert.showSuccess(getApplicationContext(),"Signature Saved");
//                                    Toast.makeText(RepairsActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                                    String cloudinaryID = (String) resultData.get("public_id");
                                    supervisorSignatureUrl = imageURL;
                                    Log.i("SupervisorSignature", supervisorSignatureUrl);

                                }

                            }

                            @Override
                            public void onError(String requestId, ErrorInfo error) {
                                Log.i("ERROR", "ERROR");
                                hideLoading();
                                Alert.showFailed(RepairsActivity.this, "Error Uploading Result, Please try agin later ");
                            }

                            @Override
                            public void onReschedule(String requestId, ErrorInfo error) {
                                hideLoading();
                                Alert.showFailed(RepairsActivity.this,"Signature upload is taking time,please sign again");
                                Log.i("SCHEDULE", "SCHEDULE");

                            }
                        })
                        .dispatch();


//                uploadToCloudinary(inspectorByteArray);


            }
        });

        clearSupervisorSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supervisorSignature.clear();

            }
        });

        saveMechanicSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = mechanicSignature.getSignatureBitmap();
                Log.i("MECHANIC", String.valueOf(mechanicSignature.getSignatureBitmap()));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] mechanicByteArray = byteArrayOutputStream.toByteArray();
                showLoading();

                String requestId = MediaManager.get().upload(mechanicByteArray)
                        .unsigned("ht7lodiw")
                        .callback(new UploadCallback() {
                            @Override
                            public void onStart(String requestId) {
                                Log.i("START", "STARTTTTT");
                                showLoading();
//                                dialog = new ProgressDialog(RepairsActivity.this);
//                                dialog.setMessage("Saving......");
//                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                dialog.setCancelable(false);
//                                dialog.show();

                            }

                            @Override
                            public void onProgress(String requestId, long bytes, long totalBytes) {
                                Double progress = (double) bytes / totalBytes;
                                Log.i("PROGRESS", "PROGRESS");
//                                dialog.show();
                            }

                            @Override
                            public void onSuccess(String requestId, Map resultData) {
                                if (resultData != null) {
                                    Log.i("SUCCESS", "SUCCESS");
                                    imageURL = (String) resultData.get("url");
                                   hideLoading();
                                    Alert.showSuccess(RepairsActivity.this,"Signature saved");
                                    String cloudinaryID = (String) resultData.get("public_id");
                                    mechanicSignatureUrl = imageURL;
                                    Log.i("mechanic", mechanicSignatureUrl);

                                }

                            }

                            @Override
                            public void onError(String requestId, ErrorInfo error) {
                                Log.i("ERROR", "ERROR");
                                hideLoading();
                                Alert.showFailed(RepairsActivity.this, "Error Uploading Result, Please try agin later ");
                            }

                            @Override
                            public void onReschedule(String requestId, ErrorInfo error) {
                               hideLoading();
                               Alert.showFailed(RepairsActivity.this,"Signature upload is taking time,please sign again");
                                Log.i("SCHEDULE", "SCHEDULE");

                            }
                        })
                        .dispatch();
            }
        });

        clearMechanicSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mechanicSignature.clear();

            }
        });

        repairSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VehicleRepairReport request = new VehicleRepairReport(supervisorSignatureUrl, mechanicSignatureUrl, repairsViewModel.getRepairReport());
                if (InternetConnection.getInstance(getApplicationContext()).isOnline())
                    repairsViewModel.sendRepair(request);
                else {
                    Alert.showFailed(getApplicationContext(), "Please Check Your Internet Connection and try again");
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repairsViewModel.onDispose();
    }


    public void saveSignature() {
        String supervisorSignature = imageURL;
        String mechanicSignature = imageURL;
    }


    @Override
    public void onBoxChecked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.hoodCheckBox:
                if (checked) {
                    hideKeyboard();
                    hoodComment.setVisibility(View.VISIBLE);
                } else {
                    hoodComment.setVisibility(View.GONE);
                    activityRepairsBinding.hoodEditText.getText().clear();
                }
                break;

            case R.id.frontBumperCheckBox:
                if (checked) {
                    hideKeyboard();
                    Log.i("BOX", "frontBumper is checked");

                    frontBumperComment.setVisibility(View.VISIBLE);
                } else {
                    frontBumperComment.setVisibility(View.GONE);
                    activityRepairsBinding.frontBumperEditText.getText().clear();
                }
                break;

            case R.id.fenderCheckBox:
                if (checked) {
                    hideKeyboard();
                    Log.i("BOX", "fender is checked");
                    fenderComment.setVisibility(View.VISIBLE);
                } else {
                    fenderComment.setVisibility(View.GONE);
                    activityRepairsBinding.fenderEditText.getText().clear();
                }
                break;

            case R.id.doorCheckBox:
                if (checked) {
                    hideKeyboard();
                    doorComment.setVisibility(View.VISIBLE);
                } else {
                    doorComment.setVisibility(View.GONE);
                    activityRepairsBinding.doorEditText.getText().clear();
                }
                break;

            case R.id.roofCheckBox:
                if (checked) {
                    hideKeyboard();
                    roofComment.setVisibility(View.VISIBLE);
                } else {
                    roofComment.setVisibility(View.GONE);
                    activityRepairsBinding.roofEditText.getText().clear();
                }
                break;

            case R.id.rearCheckBox:
                if (checked) {
                    hideKeyboard();
                    rearComment.setVisibility(View.VISIBLE);
                } else {
                    rearComment.setVisibility(View.GONE);
                    activityRepairsBinding.rearEditText.getText().clear();
                }
                break;

            case R.id.rearBumperCheckBox:
                if (checked) {
                    hideKeyboard();
                    rearBumperComment.setVisibility(View.VISIBLE);
                } else {
                    rearBumperComment.setVisibility(View.GONE);
                    activityRepairsBinding.rearBumperEditText.getText().clear();
                }
                break;

            case R.id.trunkCheckBox:
                if (checked) {
                    trunkComment.setVisibility(View.VISIBLE);
                } else {
                    trunkComment.setVisibility(View.GONE);
                    activityRepairsBinding.trunkEditText.getText().clear();
                }
                break;

            case R.id.trimCheckBox:
                if (checked) {
                    hideKeyboard();
                    trimComment.setVisibility(View.VISIBLE);
                } else {
                    trimComment.setVisibility(View.GONE);
                    activityRepairsBinding.trimEditText.getText().clear();
                }
                break;

            case R.id.fuelDoorCheckBox:
                if (checked) {
                    hideKeyboard();
                    fuelDoorComment.setVisibility(View.VISIBLE);
                } else {
                    fuelDoorComment.setVisibility(View.GONE);
                    activityRepairsBinding.fuelDoorEditText.getText().clear();
                }
                break;
            case R.id.paintCheckBox:
                if (checked) {
                    hideKeyboard();
                    paintComment.setVisibility(View.VISIBLE);
                } else {
                    paintComment.setVisibility(View.GONE);
                    activityRepairsBinding.paintEditText.getText().clear();
                }
                break;
            case R.id.windShieldCheckBox:
                if (checked) {
                    hideKeyboard();
                    windShieldComment.setVisibility(View.VISIBLE);
                } else {
                    windShieldComment.setVisibility(View.GONE);
                    activityRepairsBinding.windShieldEditText.getText().clear();
                }
                break;

            case R.id.windowCheckBox:
                if (checked) {
                    hideKeyboard();
                    windowComment.setVisibility(View.VISIBLE);
                } else {
                    windowComment.setVisibility(View.GONE);
                    activityRepairsBinding.windowEditText.getText().clear();
                }
                break;
            case R.id.mirrorCheckBox:
                if (checked) {
                    hideKeyboard();
                    mirrorComment.setVisibility(View.VISIBLE);
                } else {
                    mirrorComment.setVisibility(View.GONE);
                    activityRepairsBinding.mirrorEditText.getText().clear();
                }
                break;
            case R.id.rearWindowCheckBox:
                if (checked) {
                    hideKeyboard();
                    rearWindowComment.setVisibility(View.VISIBLE);
                } else {
                    rearWindowComment.setVisibility(View.GONE);
                    activityRepairsBinding.rearWindowEditText.getText().clear();
                }
                break;

            case R.id.tiresCheckBox:
                if (checked) {
                    hideKeyboard();
                    tireComment.setVisibility(View.VISIBLE);
                } else {
                    tireComment.setVisibility(View.GONE);
                    activityRepairsBinding.tiresEditText.getText().clear();
                }
                break;
            case R.id.wheelCheckBox:
                if (checked) {
                    hideKeyboard();
                    wheelComment.setVisibility(View.VISIBLE);
                } else {
                    wheelComment.setVisibility(View.GONE);
                    activityRepairsBinding.wheelEditText.getText().clear();
                }
                break;

            case R.id.spareTireCheckedBox:
                if (checked) {
                    hideKeyboard();
                    spareTireComment.setVisibility(View.VISIBLE);
                } else {
                    spareTireComment.setVisibility(View.GONE);
                    activityRepairsBinding.spareTireEditText.getText().clear();
                }
                break;

            case R.id.frameCheckBox:
                if (checked) {
                    hideKeyboard();
                    frameComment.setVisibility(View.VISIBLE);
                } else {
                    frameComment.setVisibility(View.GONE);
                    activityRepairsBinding.frameEditText.getText().clear();
                }
                break;

            case R.id.exhaustCheckBox:
                if (checked) {
                    hideKeyboard();
                    exhaustComment.setVisibility(View.VISIBLE);
                } else {
                    exhaustComment.setVisibility(View.GONE);
                    activityRepairsBinding.exhaustEditText.getText().clear();
                }
                break;

            case R.id.transmissionCheckBox:
                if (checked) {
                    hideKeyboard();
                    transmissionComment.setVisibility(View.VISIBLE);
                } else {
                    transmissionComment.setVisibility(View.GONE);
                    activityRepairsBinding.transmissionEditText.getText().clear();
                }
                break;

            case R.id.driveAxleCheckBox:
                if (checked) {
                    hideKeyboard();
                    driveAxleComment.setVisibility(View.VISIBLE);
                } else {
                    driveAxleComment.setVisibility(View.GONE);
                    activityRepairsBinding.driveAxleEditText.getText().clear();
                }
                break;

            case R.id.suspensionCheckbox:
                if (checked) {
                    hideKeyboard();
                    suspensionComment.setVisibility(View.VISIBLE);
                } else {
                    suspensionComment.setVisibility(View.GONE);
                    activityRepairsBinding.suspensionEditText.getText().clear();
                }
                break;

            case R.id.brakeSystemCheckBox:
                if (checked) {
                    hideKeyboard();
                    brakeSystemComment.setVisibility(View.VISIBLE);
                } else {
                    brakeSystemComment.setVisibility(View.GONE);
                    activityRepairsBinding.brakeSystemEditText.getText().clear();
                }
                break;

            case R.id.engCompCheckBox:
                if (checked) {
                    hideKeyboard();
                    engineCompComment.setVisibility(View.VISIBLE);
                } else {
                    engineCompComment.setVisibility(View.GONE);
                    activityRepairsBinding.engineCompEditText.getText().clear();
                }
                break;
            case R.id.batteryCheckBox:
                if (checked) {
                    hideKeyboard();
                    batteryComment.setVisibility(View.VISIBLE);
                } else {
                    batteryComment.setVisibility(View.GONE);
                    activityRepairsBinding.batteryEditText.getText().clear();
                }
                break;

            case R.id.oilCheckBox:
                if (checked) {
                    hideKeyboard();
                    oilComment.setVisibility(View.VISIBLE);
                } else {
                    oilComment.setVisibility(View.GONE);
                    activityRepairsBinding.oilEditText.getText().clear();
                }
                break;

            case R.id.fluidCheckBox:
                if (checked) {
                    hideKeyboard();
                    fluidComment.setVisibility(View.VISIBLE);
                } else {
                    fluidComment.setVisibility(View.GONE);
                    activityRepairsBinding.fluidEditText.getText().clear();
                }
                break;

            case R.id.wiringCheckBox:
                if (checked) {
                    hideKeyboard();
                    wiringComment.setVisibility(View.VISIBLE);
                } else {
                    wiringComment.setVisibility(View.GONE);
                    activityRepairsBinding.wiringEditText.getText().clear();
                }
                break;
            case R.id.beltCheckBox:
                if (checked) {
                    hideKeyboard();
                    beltComment.setVisibility(View.VISIBLE);
                } else {
                    beltComment.setVisibility(View.GONE);
                    activityRepairsBinding.beltEditText.getText().clear();
                }
                break;
            case R.id.hosesCheckBox:
                if (checked) {
                    hideKeyboard();
                    hosesComment.setVisibility(View.VISIBLE);
                } else {
                    hosesComment.setVisibility(View.GONE);
                    activityRepairsBinding.hosesEditText.getText().clear();
                }
                break;

            case R.id.seatCheckBox:
                if (checked) {
                    hideKeyboard();
                    seatsComment.setVisibility(View.VISIBLE);
                } else {
                    seatsComment.setVisibility(View.GONE);
                    activityRepairsBinding.seatEditText.getText().clear();
                }
                break;
            case R.id.headLinerCheckBox:
                if (checked) {
                    hideKeyboard();
                    headLinerComment.setVisibility(View.VISIBLE);
                } else {
                    headLinerComment.setVisibility(View.GONE);
                    activityRepairsBinding.headLinerEditText.getText().clear();
                }
                break;
            case R.id.carpetCheckBox:
                if (checked) {
                    hideKeyboard();
                    carpetComment.setVisibility(View.VISIBLE);
                } else {
                    carpetComment.setVisibility(View.GONE);
                    activityRepairsBinding.carpetEditText.getText().clear();
                }
                break;
            case R.id.doorPanelCheckBox:
                if (checked) {
                    hideKeyboard();
                    doorPanelComment.setVisibility(View.VISIBLE);
                } else {
                    doorPanelComment.setVisibility(View.GONE);
                    activityRepairsBinding.doorPanelEditText.getText().clear();
                }
                break;

            case R.id.gloveBoxCheckBox:
                if (checked) {
                    hideKeyboard();
                    gloveBoxComment.setVisibility(View.VISIBLE);
                } else {
                    gloveBoxComment.setVisibility(View.GONE);
                    activityRepairsBinding.gloveBoxEditText.getText().clear();
                }
                break;
            case R.id.vanityMirrorCheckBox:
                if (checked) {
                    hideKeyboard();
                    vanityMirrorComment.setVisibility(View.VISIBLE);
                } else {
                    vanityMirrorComment.setVisibility(View.GONE);
                    activityRepairsBinding.vanityMirrorEditText.getText().clear();
                }
                break;
            case R.id.interiorTrimCheckBox:
                if (checked) {
                    hideKeyboard();
                    interiorTrimComment.setVisibility(View.VISIBLE);
                } else {
                    interiorTrimComment.setVisibility(View.GONE);
                    activityRepairsBinding.interiorTrimEditText.getText().clear();
                }
                break;

            case R.id.dashBoardCheckBox:
                if (checked) {
                    hideKeyboard();
                    dashboardComment.setVisibility(View.VISIBLE);
                } else {
                    dashboardComment.setVisibility(View.GONE);
                    activityRepairsBinding.dashboardEditText.getText().clear();
                }
                break;
            case R.id.dashGuageCheckBox:
                if (checked) {
                    hideKeyboard();
                    dashGuageComment.setVisibility(View.VISIBLE);
                } else {
                    dashGuageComment.setVisibility(View.GONE);
                    activityRepairsBinding.dashGuageEditText.getText().clear();
                }
                break;
            case R.id.airCondCheckBox:
                if (checked) {
                    hideKeyboard();
                    airCondComment.setVisibility(View.VISIBLE);
                } else {
                    airCondComment.setVisibility(View.GONE);
                    activityRepairsBinding.airCondEditText.getText().clear();
                }
                break;

            case R.id.heaterCheckBox:
                if (checked) {
                    hideKeyboard();
                    heaterComment.setVisibility(View.VISIBLE);
                } else {
                    heaterComment.setVisibility(View.GONE);
                    activityRepairsBinding.heaterEditText.getText().clear();
                }
                break;
            case R.id.defrosterCheckBox:
                if (checked) {
                    hideKeyboard();
                    defrosterComment.setVisibility(View.VISIBLE);
                } else {
                    defrosterComment.setVisibility(View.GONE);
                    activityRepairsBinding.defrosterEditText.getText().clear();
                }
                break;
            case R.id.powerLockCheckOut:
                if (checked) {
                    hideKeyboard();
                    powerLockComment.setVisibility(View.VISIBLE);
                } else {
                    powerLockComment.setVisibility(View.GONE);
                    activityRepairsBinding.powerLockEditText.getText().clear();
                }
                break;

            case R.id.powerSeatCheckBox:
                if (checked) {
                    hideKeyboard();
                    powerSeatComment.setVisibility(View.VISIBLE);
                } else {
                    powerSeatComment.setVisibility(View.GONE);
                    activityRepairsBinding.powerSeatEditText.getText().clear();
                }
                break;

            case R.id.powerSteeringCheckBox:
                if (checked) {
                    hideKeyboard();
                    powerSteeringComment.setVisibility(View.VISIBLE);
                } else {
                    powerSteeringComment.setVisibility(View.GONE);
                    activityRepairsBinding.powerSteeringEditText.getText().clear();
                }
                break;

            case R.id.powerWindowCheckBox:
                if (checked) {
                    hideKeyboard();
                    powerWindowComment.setVisibility(View.VISIBLE);
                } else {
                    powerWindowComment.setVisibility(View.GONE);
                    activityRepairsBinding.powerWindowEditText.getText().clear();
                }
                break;
            case R.id.powerMirrorCheckBox:
                if (checked) {
                    hideKeyboard();
                    powerMirrorComment.setVisibility(View.VISIBLE);
                } else {
                    powerMirrorComment.setVisibility(View.GONE);
                    activityRepairsBinding.powerMirrorEditText.getText().clear();
                }
                break;
            case R.id.audioCheckBox:
                if (checked) {
                    hideKeyboard();
                    audioSystemComment.setVisibility(View.VISIBLE);
                } else {
                    audioSystemComment.setVisibility(View.GONE);
                    activityRepairsBinding.audioEditText.getText().clear();
                }
                break;
            case R.id.computerCheckBox:
                if (checked) {
                    hideKeyboard();
                    computerComment.setVisibility(View.VISIBLE);
                } else {
                    computerComment.setVisibility(View.GONE);
                    activityRepairsBinding.computerEditText.getText().clear();
                }
                break;
            case R.id.headLightCheckBox:
                if (checked) {
                    hideKeyboard();
                    headLightComment.setVisibility(View.VISIBLE);
                } else {
                    headLightComment.setVisibility(View.GONE);
                    activityRepairsBinding.headLightEditText.getText().clear();
                }
                break;

            case R.id.tailLightCheckBox:
                if (checked) {
                    hideKeyboard();
                    tailLightComment.setVisibility(View.VISIBLE);
                } else {
                    tailLightComment.setVisibility(View.GONE);
                    activityRepairsBinding.tailLightEditText.getText().clear();
                }
                break;

            case R.id.signalLightCheckBox:
                if (checked) {
                    hideKeyboard();
                    signalLightComment.setVisibility(View.VISIBLE);
                } else {
                    signalLightComment.setVisibility(View.GONE);
                    activityRepairsBinding.signalLightEditText.getText().clear();
                }
                break;

            case R.id.brakeLightCheckBox:
                if (checked) {
                    hideKeyboard();
                    brakeLightComment.setVisibility(View.VISIBLE);
                } else {
                    brakeLightComment.setVisibility(View.GONE);
                    activityRepairsBinding.brakeLightEditText.getText().clear();
                }
                break;

            case R.id.parkingLightCheckBox:
                if (checked) {
                    hideKeyboard();
                    parkingLightComment.setVisibility(View.VISIBLE);
                } else {
                    parkingLightComment.setVisibility(View.GONE);
                    activityRepairsBinding.parkingLightEditText.getText().clear();
                }
                break;

            case R.id.startingCheckBox:
                if (checked) {
                    hideKeyboard();
                    startingComment.setVisibility(View.VISIBLE);
                } else {
                    startingComment.setVisibility(View.GONE);
                    activityRepairsBinding.startingEditText.getText().clear();

                }
                break;

            case R.id.idlingCheckBox:
                if (checked) {
                    hideKeyboard();
                    idlingComment.setVisibility(View.VISIBLE);
                } else {
                    idlingComment.setVisibility(View.GONE);
                    activityRepairsBinding.idlingEditText.getText().clear();
                }
                break;
            case R.id.enginePerfCheckBox:
                if (checked) {
                    hideKeyboard();
                    enginePerfComment.setVisibility(View.VISIBLE);
                } else {
                    enginePerfComment.setVisibility(View.GONE);
                    activityRepairsBinding.enginePerfEditText.getText().clear();
                }
                break;

            case R.id.accelerationCheckBox:
                if (checked) {
                    hideKeyboard();
                    accelerationComment.setVisibility(View.VISIBLE);
                } else {
                    accelerationComment.setVisibility(View.GONE);
                    activityRepairsBinding.accelerationEditText.getText().clear();
                }
                break;
            case R.id.transmissionShiftCheckBox:
                if (checked) {
                    hideKeyboard();
                    transmissionShiftComment.setVisibility(View.VISIBLE);
                } else {
                    transmissionShiftComment.setVisibility(View.GONE);
                    activityRepairsBinding.transmissionShiftEditText.getText().clear();
                }
                break;

            case R.id.steeringCheckBox:
                if (checked) {
                    hideKeyboard();
                    steeringComment.setVisibility(View.VISIBLE);
                } else {
                    steeringComment.setVisibility(View.GONE);
                    activityRepairsBinding.steeringEditText.getText().clear();
                }
                break;

            case R.id.brakingCheckOut:
                if (checked) {
                    hideKeyboard();
                    brakingComment.setVisibility(View.VISIBLE);
                } else {
                    brakingComment.setVisibility(View.GONE);
                    activityRepairsBinding.brakingEditText.getText().clear();
                }
                break;

            case R.id.suspensionPefCheckOut:
                if (checked) {
                    hideKeyboard();
                    suspensionPrefComment.setVisibility(View.VISIBLE);
                } else {
                    suspensionPrefComment.setVisibility(View.GONE);
                    activityRepairsBinding.suspensionPrefEditText.getText().clear();
                }
                break;
                default:{

                }
        }
    }

    @Override
    public void onSaveHood() {
        hideKeyboard();

        hoodEditComment = activityRepairsBinding.hoodEditText.getText().toString();
        fontBumperEditComment = activityRepairsBinding.frontBumperEditText.getText().toString();
        fenderEditComment = activityRepairsBinding.fenderEditText.getText().toString();
        doorEditComment = activityRepairsBinding.doorEditText.getText().toString();
        roofEditComment = activityRepairsBinding.roofEditText.getText().toString();
        rearEditComment = activityRepairsBinding.rearEditText.getText().toString();
        rearBumperEditComment = activityRepairsBinding.rearBumperEditText.getText().toString();
        trunkEditComment = activityRepairsBinding.trunkEditText.getText().toString();
        trimEditComment = activityRepairsBinding.trimEditText.getText().toString();
        fuelDoorEditComment = activityRepairsBinding.fuelDoorEditText.getText().toString();
        paintEditComment = activityRepairsBinding.paintEditText.getText().toString();

        VehiclePartRepair hood = new VehiclePartRepair("hood", hoodEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(hood);

        VehiclePartRepair fontBumper = new VehiclePartRepair("front bumper", fontBumperEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(fontBumper);

        VehiclePartRepair fenders = new VehiclePartRepair("fenders", fenderEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(fenders);

        VehiclePartRepair doors = new VehiclePartRepair("doors", doorEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(doors);

        VehiclePartRepair roof = new VehiclePartRepair("roof", roofEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(roof);

        VehiclePartRepair rear = new VehiclePartRepair("rear", rearEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(rear);

        VehiclePartRepair rearBumper = new VehiclePartRepair("rear bumper", rearBumperEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(rearBumper);

        VehiclePartRepair trunk = new VehiclePartRepair("trunk", trunkEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(trunk);

        VehiclePartRepair trim = new VehiclePartRepair("trim", trimEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(trim);

        VehiclePartRepair fuelDoor = new VehiclePartRepair("fuel door", fuelDoorEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(fuelDoor);

        VehiclePartRepair paint = new VehiclePartRepair("paint", paintEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(paint);

        exteriorFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");


    }

    @Override
    public void onSaveGlass() {
        hideKeyboard();
        windShieldEditComment = activityRepairsBinding.windShieldEditText.getText().toString();
        windowEditComment = activityRepairsBinding.windowEditText.getText().toString();
        mirrorsEditComment = activityRepairsBinding.windowEditText.getText().toString();
        rearWindowEditComment = activityRepairsBinding.rearWindowEditText.getText().toString();

        VehiclePartRepair windShield = new VehiclePartRepair("windshield", windShieldEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(windShield);

        VehiclePartRepair windows = new VehiclePartRepair("windows", windowEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(windows);

        VehiclePartRepair mirror = new VehiclePartRepair("mirrors", mirrorsEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(mirror);

        VehiclePartRepair rearWindow = new VehiclePartRepair("rear window", hoodEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(rearWindow);

        glassFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");



    }

    @Override
    public void onSaveTires() {
        hideKeyboard();
        tiresEditComment = activityRepairsBinding.tiresEditText.getText().toString();
        wheelEditComment = activityRepairsBinding.wheelEditText.getText().toString();
        spareTireEditComment = activityRepairsBinding.spareTireEditText.getText().toString();

        VehiclePartRepair tires = new VehiclePartRepair("tires", tiresEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(tires);

        VehiclePartRepair wheels = new VehiclePartRepair("wheels", wheelEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(wheels);

        VehiclePartRepair spareTire = new VehiclePartRepair("spare tire", spareTireEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(spareTire);
        tiresFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");


    }

    @Override
    public void onSaveUnderBody() {
        hideKeyboard();
        frameEditComment = activityRepairsBinding.frameEditText.getText().toString();
        exhaustEditComment = activityRepairsBinding.exhaustEditText.getText().toString();
        transmissionEditComment = activityRepairsBinding.transmissionEditText.getText().toString();
        driveAxleEditComment = activityRepairsBinding.driveAxleEditText.getText().toString();
        suspensionEditComment = activityRepairsBinding.suspensionEditText.getText().toString();
        brakeSystemEditComment = activityRepairsBinding.brakeSystemEditText.getText().toString();

        VehiclePartRepair frame = new VehiclePartRepair("frame", frameEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(frame);

        VehiclePartRepair exhaust = new VehiclePartRepair("exhaust", exhaustEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(exhaust);

        VehiclePartRepair transmission = new VehiclePartRepair("transmission", transmissionEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(transmission);

        VehiclePartRepair driveAxle = new VehiclePartRepair("drive axle", driveAxleEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(driveAxle);

        VehiclePartRepair suspension = new VehiclePartRepair("exhaust", suspensionEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(suspension);

        VehiclePartRepair brakeSystem = new VehiclePartRepair("brake system", exhaustEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(brakeSystem);
        underbodyFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");


    }

    @Override
    public void onSaveUnderHood() {
        hideKeyboard();
        engineCompEditComment = activityRepairsBinding.engineCompEditText.getText().toString();
        batteryEditComment = activityRepairsBinding.batteryEditText.getText().toString();
        oilEditComment = activityRepairsBinding.oilEditText.getText().toString();
        fluidEditComment = activityRepairsBinding.fluidEditText.getText().toString();
        wiringEditComment = activityRepairsBinding.wiringEditText.getText().toString();
        beltEditComment = activityRepairsBinding.beltEditText.getText().toString();
        hosesEditComment = activityRepairsBinding.hosesEditText.getText().toString();

        VehiclePartRepair engineComp = new VehiclePartRepair("engine compartment", engineCompEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(engineComp);

        VehiclePartRepair battery = new VehiclePartRepair("battery", batteryEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(battery);

        VehiclePartRepair oil = new VehiclePartRepair("oil", oilEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(oil);

        VehiclePartRepair fluid = new VehiclePartRepair("fluids", fluidEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(fluid);

        VehiclePartRepair wiring = new VehiclePartRepair("wiring", wiringEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(wiring);

        VehiclePartRepair belt = new VehiclePartRepair("belt", beltEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(belt);

        VehiclePartRepair hoses = new VehiclePartRepair("hoses", hosesEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(hoses);
        underHoodFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");

    }

    @Override
    public void onSaveInterior() {
        hideKeyboard();
        seatsEditComment = activityRepairsBinding.seatEditText.getText().toString();
        headlinerEditComment = activityRepairsBinding.headLinerEditText.getText().toString();
        carpetEditComment = activityRepairsBinding.carpetEditText.getText().toString();
        doorPanelEditComment = activityRepairsBinding.doorPanelEditText.getText().toString();
        gloveBoxEditComment = activityRepairsBinding.gloveBoxEditText.getText().toString();
        vanityMirrorEditComment = activityRepairsBinding.vanityMirrorEditText.getText().toString();
        interiorTrimEdittComment = activityRepairsBinding.interiorTrimEditText.getText().toString();
        dashBoardEditComment = activityRepairsBinding.dashboardEditText.getText().toString();
        dashGuagesEditComment = activityRepairsBinding.dashGuageEditText.getText().toString();
        airCondEditComment = activityRepairsBinding.airCondEditText.getText().toString();
        heaterEditComment = activityRepairsBinding.heaterEditText.getText().toString();
        defrosterEditComment = activityRepairsBinding.defrosterEditText.getText().toString();

        VehiclePartRepair seats = new VehiclePartRepair("seats", seatsEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(seats);

        VehiclePartRepair headLiner = new VehiclePartRepair("headliner", headlinerEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(headLiner);

        VehiclePartRepair carpet = new VehiclePartRepair("carpet", carpetEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(carpet);

        VehiclePartRepair doorPanel = new VehiclePartRepair("door panel", doorPanelEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(doorPanel);

        VehiclePartRepair gloveBox = new VehiclePartRepair("glove box", gloveBoxEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(gloveBox);

        VehiclePartRepair vanityMirror = new VehiclePartRepair("vanity mirror", vanityMirrorEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(vanityMirror);

        VehiclePartRepair interiorTrim = new VehiclePartRepair("interiorTrim", interiorTrimEdittComment);
        repairsViewModel.saveRepairReportToLocalStorage(interiorTrim);

        VehiclePartRepair dashboard = new VehiclePartRepair("dashboard", dashBoardEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(dashboard);

        VehiclePartRepair dashGuages = new VehiclePartRepair("dash guages", dashGuagesEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(dashGuages);

        VehiclePartRepair airCond = new VehiclePartRepair("air conditioner", airCondEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(airCond);

        VehiclePartRepair heater = new VehiclePartRepair("heater", heaterEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(heater);

        VehiclePartRepair defroster = new VehiclePartRepair("defroster", defrosterEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(defroster);
        interiorFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");
    }

    @Override
    public void onSaveElectric() {
        hideKeyboard();
        powerLockEditComment = activityRepairsBinding.powerLockEditText.getText().toString();
        powerSeatEditComment = activityRepairsBinding.powerSeatEditText.getText().toString();
        powerSteeringEditComment = activityRepairsBinding.powerSteeringEditText.getText().toString();
        powerWindowEditComment = activityRepairsBinding.powerWindowEditText.getText().toString();
        powerMirrorEditComment = activityRepairsBinding.powerMirrorEditText.getText().toString();
        audioSystemEditComment = activityRepairsBinding.audioEditText.getText().toString();
        computerEditComment = activityRepairsBinding.computerEditText.getText().toString();
        headLightEditComment = activityRepairsBinding.headLightEditText.getText().toString();
        tailLightEditComment = activityRepairsBinding.tailLightEditText.getText().toString();
        signalLightEditComment = activityRepairsBinding.signalLightEditText.getText().toString();
        brakeLightEditComment = activityRepairsBinding.brakeLightEditText.getText().toString();
        parkingLightEditComment = activityRepairsBinding.parkingLightEditText.getText().toString();

        VehiclePartRepair powerLock = new VehiclePartRepair("power locks", powerLockEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(powerLock);

        VehiclePartRepair powerSeats = new VehiclePartRepair("power seats", powerSeatEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(powerSeats);

        VehiclePartRepair powerSteering = new VehiclePartRepair("power steering", powerSteeringEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(powerSteering);

        VehiclePartRepair powerWindows = new VehiclePartRepair("power windows", powerWindowEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(powerWindows);

        VehiclePartRepair powerMirrors = new VehiclePartRepair("power mirrors", powerMirrorEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(powerMirrors);

        VehiclePartRepair audioSystem = new VehiclePartRepair("audio system", audioSystemEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(audioSystem);

        VehiclePartRepair computer = new VehiclePartRepair("computer", computerEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(computer);

        VehiclePartRepair headlights = new VehiclePartRepair("headlights", headLightEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(headlights);

        VehiclePartRepair tailLights = new VehiclePartRepair("tail lights", tailLightEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(tailLights);

        VehiclePartRepair signalLight = new VehiclePartRepair("signal lights", signalLightEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(signalLight);

        VehiclePartRepair brakeLight = new VehiclePartRepair("brake lights", brakeLightEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(brakeLight);

        VehiclePartRepair parkingLight = new VehiclePartRepair("parking lights", parkingLightEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(parkingLight);
        electricFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");

    }

    @Override
    public void onSaveRoadTest() {
        hideKeyboard();
        startingEditComment = activityRepairsBinding.startingEditText.getText().toString();
        idlingEditComment = activityRepairsBinding.idlingEditText.getText().toString();
        enginePerfEditComment = activityRepairsBinding.enginePerfEditText.getText().toString();
        accelerationEditComment = activityRepairsBinding.accelerationEditText.getText().toString();
        transShiftEditComment = activityRepairsBinding.transmissionShiftEditText.getText().toString();
        steeringEditComment = activityRepairsBinding.steeringEditText.getText().toString();
        brakingEditComment = activityRepairsBinding.brakingEditText.getText().toString();
        suspensionPerfEditComment = activityRepairsBinding.suspensionPrefEditText.getText().toString();

        VehiclePartRepair starting = new VehiclePartRepair("starting", startingEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(starting);

        VehiclePartRepair idling = new VehiclePartRepair("idling", idlingEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(idling);

        VehiclePartRepair enginePerf = new VehiclePartRepair("engine performance", enginePerfEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(enginePerf);

        VehiclePartRepair acceleration = new VehiclePartRepair("acceleration", accelerationEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(acceleration);

        VehiclePartRepair transmissionShift = new VehiclePartRepair("transmission shift", transShiftEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(transmissionShift);

        VehiclePartRepair steering = new VehiclePartRepair("steering", steeringEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(steering);

        VehiclePartRepair braking = new VehiclePartRepair("braking", brakingEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(braking);

        VehiclePartRepair suspensionPerf = new VehiclePartRepair("suspension performance", suspensionPerfEditComment);
        repairsViewModel.saveRepairReportToLocalStorage(suspensionPerf);

        roadTestFragment.setVisibility(View.GONE);
        Alert.showSuccess(getApplicationContext(),"Repairs Report Saved Successfully");

    }

    @Override
    public void handleError(Throwable throwable) {
        Intent intent = new Intent(getApplicationContext(), FailedActivity.class);
        startActivity(intent);
        Alert.showFailed(getApplicationContext(),"Report cannot be submitted to the database");
//        if (throwable != null) {
//            ANError error = (ANError) throwable;
//            CreateReportResponse response = gson.fromJson(error.getErrorBody(), CreateReportResponse.class);
//            if (error.getErrorBody() != null) {
//                Alert.showFailed(getApplicationContext(), response.getMessage());
//            } else {
//                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
//            }
//        }
    }

    @Override
    public void onResponse() {
        Intent intent = new Intent(getApplicationContext(), ResponseActivity.class);
        startActivity(intent);
        deleteData();
        Log.i("NEW REPAIR REPORT", String.valueOf(repairsViewModel.getRepairReport()));


    }

    @Override
    public void onStarting() {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);

    }

    public void changeColor(TextView text){
        if (status.equals("good")){
            Spannable spannable = new SpannableString(status);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#13581D")), status.indexOf("good"), status.indexOf("good") + "good".length(),     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
         text.setText(spannable);
        }else if (status.equals("poor")){
            Spannable spannable = new SpannableString(status);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#E53012")), status.indexOf("poor"), status.indexOf("poor") + "poor".length(),     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setText(spannable);
        }else {
            Spannable spannable = new SpannableString(status);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3CC64F")), status.indexOf("fair"), status.indexOf("fair") + "fair".length(),     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setText(spannable);
        }
    }

    public void deleteData(){
        repairsViewModel.deleteRepairReport(repairsViewModel.getRepairReport());
    }

    @Override
    public void onInspectorResponse(InspectorDetailReport response) {
        Log.i("Status", "SUceesFul Request");
        List<InspectorDetailData> detailData = response.getData();
        for (int i = 0; i < detailData.size(); i++) {
            InspectorDetailData data = detailData.get(i);
            status = data.getRemark();


            switch (data.getPart()) {

                case "hood": {
                    hoodStatus =findViewById(R.id.hoodStatus);
                    hoodStatus.setText(status);
                    changeColor(hoodStatus);
                    break;
                }


                case "front bumper": {
                    fontBumperStatus =findViewById(R.id.frontBumperStatus) ;
                    fontBumperStatus.setText(status);
                    changeColor(fontBumperStatus);
                    break;

                }

                case "fenders": {
                    fenderStatus = findViewById(R.id.fenderStatus);
                    fenderStatus.setText(status);
                    changeColor(fenderStatus);
                    break;
                }

                case "doors": {
                    doorStatus = findViewById(R.id.doorStatus) ;
                    doorStatus.setText(status);
                    changeColor(doorStatus);
                    break;
                }
                case "roof": {
                    roofStatus = findViewById(R.id.roofStatus) ;
                    roofStatus.setText(status);
                    changeColor(roofStatus);
                    break;
                }
                case "rear": {
                    rearStatus = findViewById(R.id.rearStatus) ;
                    rearStatus.setText(status);
                    changeColor(rearStatus);
                    break;
                }
                case "rear bumper": {
                    rearBumperStatus = findViewById(R.id.rearBumperStatus);
                    rearBumperStatus.setText(status);
                    changeColor(rearBumperStatus);
                    break;
                }
                case "trunk": {
                    trunkStatus = findViewById(R.id.trunkStatus);
                    trunkStatus.setText(status);
                    changeColor(trunkStatus);
                    break;
                }
                case "trim": {
                    trimStatus = findViewById(R.id.trimStatus);
                    trimStatus.setText(status);
                    changeColor(trimStatus);
                    break;
                }
                case "fuel door": {
                    fuelDoorStatus = findViewById(R.id.fuelDoorStatus);
                    fuelDoorStatus.setText(status);
                    changeColor(fuelDoorStatus);
                    break;
                }
                case "paint": {
                    paintStatus = findViewById(R.id.paintStatus);
                    paintStatus.setText(status);
                    changeColor(paintStatus);
                    break;
                }
                case "windshield":{
                    windShieldStatus = findViewById(R.id.windShieldStatus);
                    windShieldStatus.setText(status);
                    changeColor(windShieldStatus);
                    break;
                }
                case "windows":{
                    windowStatus = findViewById(R.id.windowsStatus);
                    windowStatus.setText(status);
                    changeColor(windowStatus);
                    break;
                }
                case "mirrors":{
                    mirrorStatus = findViewById(R.id.mirrorStatus);
                    mirrorStatus.setText(status);
                    changeColor(mirrorStatus);
                    break;
                }
                case "rear window":{
                    rearWindowStatus = findViewById(R.id.rearWindowStatus);
                    rearWindowStatus.setText(status);
                    changeColor(rearWindowStatus);
                    break;
                }
                case "tyres":{
                    tyresStatus = findViewById(R.id.tiresStatus);
                    tyresStatus.setText(status);
                    changeColor(tyresStatus);
                    break;
                }
                case "wheels":{
                    wheelStatus = findViewById(R.id.wheelStatus);
                    wheelStatus.setText(status);
                    changeColor(wheelStatus);
                    break;
                }
                case "spare tyre":{
                    spareTyreStatus = findViewById(R.id.spareTireStatus);
                    spareTyreStatus.setText(status);
                    changeColor(spareTyreStatus);
                    break;
                }
                case "frame":{
                    frameStatus = findViewById(R.id.frameStatus);
                    frameStatus.setText(status);
                    changeColor(frameStatus);
                    break;
                }
                case "exhaust":{
                    exhaustStatus = findViewById(R.id.exhaustStatus);
                    exhaustStatus.setText(status);
                    changeColor(exhaustStatus);
                    break;
                }
                case "transmission":{
                    transmissionStatus = findViewById(R.id.transmissionStatus);
                    transmissionStatus.setText(status);
                    changeColor(transmissionStatus);
                    break;
                }
                case "drive axle":{
                    driveAxleStatus = findViewById(R.id.driveAxleStatus);
                    driveAxleStatus.setText(status);
                    changeColor(driveAxleStatus);
                    break;
                }case "suspension":{
                    suspensionStatus = findViewById(R.id.suspensionStatus);
                    suspensionStatus.setText(status);
                    changeColor(suspensionStatus);
                    break;
                }case "brake system":{
                    brakeSystemStatus = findViewById(R.id.brakeSystemStatus);
                    brakeSystemStatus.setText(status);
                    changeColor(brakeSystemStatus);
                    break;
                }case "engine compartment":{
                    engineCompartmentStatus = findViewById(R.id.engineCompStatus);
                    engineCompartmentStatus.setText(status);
                    changeColor(engineCompartmentStatus);
                    break;
                }case "battery":{
                    batteryStatus = findViewById(R.id.batteryStatus);
                    batteryStatus.setText(status);
                    changeColor(batteryStatus);
                    break;
                }case "oil":{
                    oilStatus = findViewById(R.id.oilStatus);
                    oilStatus.setText(status);
                    changeColor(oilStatus);
                    break;
                }case "fluids":{
                    fluidStatus = findViewById(R.id.fluidStatus);
                    fluidStatus.setText(status);
                    changeColor(fluidStatus);
                    break;
                }case "wiring":{
                    wiringStatus = findViewById(R.id.wiringStatus);
                    wiringStatus.setText(status);
                    changeColor(wiringStatus);
                    break;
                }case "belts":{
                    beltStatus = findViewById(R.id.beltStatus);
                    beltStatus.setText(status);
                    changeColor(beltStatus);
                    break;
                }case "hoses":{
                    hosesStatus = findViewById(R.id.hosesStatus);
                    hosesStatus.setText(status);
                    changeColor(hosesStatus);
                    break;
                }case "seats":{
                    seatStatus = findViewById(R.id.seatStatus);
                    seatStatus.setText(status);
                    changeColor(seatStatus);
                    break;
                }case "headliner":{
                    headlinerStatus = findViewById(R.id.headLinerStatus);
                    headlinerStatus.setText(status);
                    changeColor(headlinerStatus);
                    break;
                }case "carpets":{
                    carpetStatus = findViewById(R.id.carpetStatus);
                    carpetStatus.setText(status);
                    changeColor(carpetStatus);
                    break;
                }case "door panels":{
                    doorPanelStatus = findViewById(R.id.doorPanelStatus);
                    doorPanelStatus.setText(status);
                    changeColor(doorPanelStatus);
                    break;
                }case "glove box":{
                    gloveBoxStatus = findViewById(R.id.gloveBoxStatus);
                    gloveBoxStatus.setText(status);
                    changeColor(gloveBoxStatus);
                    break;
                }case "vanity mirror":{
                    vanityMirrorStatus = findViewById(R.id.vanityMirrorStatus);
                    vanityMirrorStatus.setText(status);
                    changeColor(vanityMirrorStatus);
                    break;
                }case "interior trim":{
                    interiorTrimStatus = findViewById(R.id.interiorTrimStatus);
                    interiorTrimStatus.setText(status);
                    changeColor(interiorTrimStatus);
                    break;
                }case "dashboard":{
                    dashboardStatus = findViewById(R.id.dashboardStatus);
                    dashboardStatus.setText(status);
                    changeColor(dashboardStatus);
                    break;
                }case "dash guages":{
                    dashGuagesStatus = findViewById(R.id.dashGuageStatus);
                    dashGuagesStatus.setText(status);
                    changeColor(dashGuagesStatus);
                    break;
                }case "air conditioner":{
                    airConditionerStatus = findViewById(R.id.airCondStatus);
                    airConditionerStatus.setText(status);
                    changeColor(airConditionerStatus);
                    break;
                }case "heater":{
                    heaterStatus = findViewById(R.id.heaterStatus);
                    heaterStatus.setText(status);
                    changeColor(heaterStatus);
                    break;
                }case "defroster":{
                    defrosterStatus = findViewById(R.id.defrosterStatus);
                    defrosterStatus.setText(status);
                    changeColor(defrosterStatus);
                    break;
                }
                case "power locks":{
                    powerLockStatus = findViewById(R.id.powerLockStatus);
                    powerLockStatus.setText(status);
                    changeColor(powerLockStatus);
                    break;
                }case "power seats":{
                    powerSeatStatus = findViewById(R.id.powerSeatStatus);
                    powerSeatStatus.setText(status);
                    changeColor(powerSeatStatus);
                    break;
                }case "power steering":{
                    powerSteeringStatus = findViewById(R.id.powerSteeringStatus);
                    powerSteeringStatus.setText(status);
                    changeColor(powerSteeringStatus);
                    break;
                }case "power windows":{
                    powerWindowStatus = findViewById(R.id.powerWindowStatus);
                    powerWindowStatus.setText(status);
                    changeColor(powerWindowStatus);
                    break;
                }case "power mirrors":{
                    powerMirrorStatus = findViewById(R.id.powerMirrorStatus);
                    powerMirrorStatus.setText(status);
                    changeColor(powerMirrorStatus);
                    break;
                }case "audio system":{
                    audioSystemStatus = findViewById(R.id.audioSystemStatus);
                    audioSystemStatus.setText(status);
                    changeColor(audioSystemStatus);
                    break;
                }case "computer":{
                    computerStatus = findViewById(R.id.computerStatus);
                    computerStatus.setText(status);
                    changeColor(computerStatus);
                    break;
                }case "headlights":{
                    headlightStatus = findViewById(R.id.headlightStatus);
                    headlightStatus.setText(status);
                    changeColor(headlightStatus);
                    break;
                }case "tail lights":{
                    tailLightStatus = findViewById(R.id.tailLightStatus);
                    tailLightStatus.setText(status);
                    changeColor(tailLightStatus);
                    break;
                }case "signal lights":{
                    signalLightStatus = findViewById(R.id.signalLightStatus);
                    signalLightStatus.setText(status);
                    changeColor(signalLightStatus);
                    break;
                }case "brake lights":{
                    brakeLightStatus = findViewById(R.id.brakeLightStatus);
                    brakeLightStatus.setText(status);
                    changeColor(brakeLightStatus);
                    break;
                }case "parking lights":{
                    parkingLightStatus = findViewById(R.id.parkingLightStatus);
                    parkingLightStatus.setText(status);
                    changeColor(parkingLightStatus);
                    break;
                }case "starting":{
                    startingStatus = findViewById(R.id.startingStatus);
                    startingStatus.setText(status);
                    changeColor(startingStatus);
                    break;
                }case "idling":{
                    idlingStatus = findViewById(R.id.idlingStatus);
                    idlingStatus.setText(status);
                    changeColor(idlingStatus);
                    break;
                }case "engine performance":{
                    enginePerformanceStatus = findViewById(R.id.enginePerfStatus);
                    enginePerformanceStatus.setText(status);
                    changeColor(enginePerformanceStatus);
                    break;
                }case "acceleration":{
                    accelerationStatus = findViewById(R.id.accelerationStatus);
                    accelerationStatus.setText(status);
                    changeColor(accelerationStatus);
                    break;
                }case "transmission shift":{
                    transmissionShiftStatus = findViewById(R.id.transShiftStatus);
                    transmissionShiftStatus.setText(status);
                    changeColor(transmissionShiftStatus);
                    break;
                }case "steering":{
                    steeringStatus = findViewById(R.id.steeringStatus);
                    steeringStatus.setText(status);
                    changeColor(steeringStatus);
                    break;
                }case "braking":{
                    brakingStatus = findViewById(R.id.brakingStatus);
                    brakingStatus.setText(status);
                    changeColor(brakingStatus);
                    break;
                }case "suspension performance":{
                    suspensionPerformanceStatus = findViewById(R.id.suspensionPerfStatus);
                    suspensionPerformanceStatus.setText(status);
                    changeColor(suspensionPerformanceStatus);
                    break;

                }
                default:{
                    status = "";
                }



                }


            }


        }


    }







