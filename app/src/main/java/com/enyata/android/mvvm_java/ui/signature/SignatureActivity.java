package com.enyata.android.mvvm_java.ui.signature;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.SignatureImageArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MonthlyReportRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.databinding.ActivitySignatureBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignatureActivity extends BaseActivity<ActivitySignatureBinding,SignatureViewModel>implements SignatureNavigator {

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    Gson gson;
    SignatureViewModel signatureViewModel;
    SignaturePad supplierSignature, inspectorSignature;
    ActivitySignatureBinding activitySignatureBinding;
    TextView inspectorClear,inspectorSave,supplierClear,supplierSave;
    ProgressDialog dialog;
    SignatureImageArray signatureImageArray;
    String  imageURL,carYear,carMake,carModel,carColor,mileage,vin,regno,intakeFinalComment,intakeFinalStatus,reportType;
    List<String> signatureResult;
    boolean audioSystem,brakelight, computer,headlight,parkingLight,powerLock,powerMirror,powerSeat,powerSteering,powerWindow,signalLight,tailLight,door, fenders,frontBumper,fuelDoor,hood,paint,rearBumper,rear, roof,trim,trunk,mirror,rearWindow,window,winshield,
    airCond,carpet,dashboard,dashguages,defroster,doorPanel,gloveBox,headLiner,heater,interiorTrim,seats,vanityMirror,acceleration,braking,enginePerf,idling,starting,steering,suspensionPerf,transmissionShift,spareTire,tires,wheel,brakeSystem,driveAxle,exhaust,frame,suspension,transmission,
    battery,belt,engineComp,fluid,hoses,oil,wiring;

    boolean vehicleInfo;
    List<VehicleCollection> intakeVehicleDetails;
    HashMap<String, String> signatureImage = new HashMap<>();
    List<VehicleCollection>requests;
    TextView supplierTextView;
    String supplierSignatureUrl, inspectorSignatureUrl;

    private ApiService mAPIService;
    CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signature;
    }

    @Override
    public SignatureViewModel getViewModel() {
        signatureViewModel = ViewModelProviders.of(this,factory).get(SignatureViewModel.class);
        return signatureViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signatureViewModel.setNavigator(this);
        signatureViewModel.getReportType();
        Log.i("REportType",signatureViewModel.getReportType());
        activitySignatureBinding = getViewDataBinding();
        signatureImageArray = new SignatureImageArray(signatureImage);
        carYear = signatureViewModel.getCarYear();
        carMake = signatureViewModel.getCarMake();
        carColor = signatureViewModel.getCarColor();
        carModel = signatureViewModel.getCarModel();
        mileage = signatureViewModel.getMilage();
        regno = signatureViewModel.getregNo();
        vin =signatureViewModel.getVin();
        reportType = signatureViewModel.getReportType();
        audioSystem = signatureViewModel.getAudioSystemTracking();
        brakelight = signatureViewModel.getBrakeLightTracking();
        computer = signatureViewModel.getComputerTracking();
        headlight = signatureViewModel.getHeadLightTracking();
        parkingLight = signatureViewModel.getParkingLightTracking();
        powerLock = signatureViewModel.getPowerLockTracking();
        powerMirror = signatureViewModel.getPowerMiirorTracking();
        powerSeat = signatureViewModel.getPowerSeatTracking();
        powerSteering = signatureViewModel.getPowerSteeringTracking();
        powerWindow = signatureViewModel.getPowerWindowTracking();
        signalLight = signatureViewModel.getSignalLightTracking();
        tailLight = signatureViewModel.getTailLightTracking();
        door = signatureViewModel.getDoorTrackingStatus();
        fenders = signatureViewModel.getFenderTracking();
        frontBumper = signatureViewModel.getFrontBumperTracking();
        fuelDoor = signatureViewModel.getFuelDoorTracking();
        hood = signatureViewModel.getHoodTrackingStatus();
        paint = signatureViewModel.getPaintTracking();
        rearBumper= signatureViewModel.getRearBumperTracking();
        rear = signatureViewModel.getRearTracking();
        roof = signatureViewModel.getRoofTracking();
        trim = signatureViewModel.getTrimTracking();
        trunk = signatureViewModel.getTrunkTracking();
        mirror = signatureViewModel.getMirrorTracking();
        rearWindow = signatureViewModel.getRearWindowTracking();
        window = signatureViewModel.getWindowTracking();
        winshield = signatureViewModel.getWindShieldTracking();
        airCond = signatureViewModel.getAirCondTraking();
        carpet = signatureViewModel.getCarpetTracking();
        dashboard = signatureViewModel.getdashBoardTracking();
        dashguages = signatureViewModel.getDashGuageTracking();
        defroster = signatureViewModel.getDefrosterTrcking();
        doorPanel = signatureViewModel.getDoorPanelTracking();
        gloveBox = signatureViewModel.getGloveBoxTracking();
        headLiner = signatureViewModel.getHeadLinerTracking();
        heater = signatureViewModel.getHeaterTracking();
        interiorTrim = signatureViewModel.getInteriorTrimTracking();
        seats = signatureViewModel.getSeatTracking();
        vanityMirror = signatureViewModel.getVanityMirrorTracking();
        acceleration = signatureViewModel.getAccelerationTracking();
        braking = signatureViewModel.getBrakingTracking();
        enginePerf = signatureViewModel.getEnginePerfTracking();
        idling = signatureViewModel.getIdlingTracking();
        starting = signatureViewModel.getStartingTracking();
        steering = signatureViewModel.getSteeringTracking();
        suspensionPerf = signatureViewModel.getSuspensionPerfTracking();
        transmissionShift = signatureViewModel.getTransmissionShiftTracking();
        spareTire = signatureViewModel.getSpareTireTracking();
        tires = signatureViewModel.getTiresTracking();
        wheel = signatureViewModel.getWheelTracking();
        brakeSystem = signatureViewModel.getBrakeSystemTracking();
        driveAxle = signatureViewModel.getDriveAxleTracking();
        exhaust = signatureViewModel.getExhaustTracking();
        frame = signatureViewModel.getFrameTracking();
        suspension = signatureViewModel.getSuspensionTracking();
        transmission = signatureViewModel.getTransmissionTracking();
        battery = signatureViewModel.getBatteryTracking();
        belt = signatureViewModel.getBeltTracking();
        engineComp = signatureViewModel.getEngineCompTracking();
        fluid = signatureViewModel.getFluidTracking();
        hoses = signatureViewModel.getHosesTracking();
        oil = signatureViewModel.getOilTracking();
        wiring = signatureViewModel.getWiringTracking();






        vehicleInfo = signatureViewModel.getVehincleInfoStatus();
        intakeFinalComment = signatureViewModel.getIntakeFinalComment();
        intakeFinalStatus = signatureViewModel.getIntakeFinalStatus();
        intakeVehicleDetails = signatureViewModel.vehincleReportArray();
        supplierSignature = activitySignatureBinding.supplierSignature;
        inspectorSignature = activitySignatureBinding.inspectorSignature;
        inspectorClear = activitySignatureBinding.clearInspectorSignature;
        inspectorSave = activitySignatureBinding.saveInspectorSignature;
        supplierSave = activitySignatureBinding.saveSupplierSignature;
        supplierClear = activitySignatureBinding.clearSupplierSignature;
        supplierTextView = activitySignatureBinding.supplierTextView;

        supplierSave.setEnabled(false);
        inspectorSave.setEnabled(false);
        inspectorClear.setEnabled(false);
        supplierClear.setEnabled(false);

        Log.i("VehicleInfo", String.valueOf(vehicleInfo));

        switch (signatureViewModel.getReportType()){
            case "intake":{
                supplierTextView.setText("Supplier Signature");
                break;
            }case "monthly":{
                supplierTextView.setText("Driver Signature");
                break;
            }
            default:{
                supplierTextView.setText("Supplier Signature / Driver Signature");
                break;
            }
        }

        mAPIService = ApiUtils.getAPIService();

        Map config = new HashMap();
        config.put("cloud_name", "dtt1nmogz");
        config.put("api_key", "754277299533971");
        config.put("api_secret", "hwuDlRgCtSpxKOg9rcY43AtsZvw");
        Log.d("oooooo", "ffffff");
        supplierSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Log.i("StartSigning", "Start Signing");
            }

            @Override
            public void onSigned() {
                supplierClear.setEnabled(true);
                supplierSave.setEnabled(true);


            }

            @Override
            public void onClear() {
                supplierClear.setEnabled(false);
                supplierSave.setEnabled(false);


            }
        });

        inspectorSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Log.i("StartSigning", "Start Signing");
            }

            @Override
            public void onSigned() {
                inspectorSave.setEnabled(true);
                inspectorClear.setEnabled(true);

            }

            @Override
            public void onClear() {
                inspectorClear.setEnabled(false);
                inspectorSave.setEnabled(false);

            }
        });


    }


    @Override
    public void onSaveInspectorSign() {
        Log.i("save","SAVE");
      Bitmap bitmap =  inspectorSignature.getSignatureBitmap();
        Log.i("SignatureBitmap", inspectorSignature.getSignatureBitmap().toString());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] inspectorByteArray = byteArrayOutputStream .toByteArray();
        showLoading();
        String requestId = MediaManager.get().upload(inspectorByteArray)
                .unsigned("ht7lodiw")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        showLoading();
//                        dialog = new ProgressDialog(SignatureActivity.this);
//                        dialog.setMessage("Saving......");
//                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                        dialog.setCancelable(false);
//                        dialog.show();

                    }
                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;
                        Log.i("PROGRESS", "PROGRESS");

//                        dialog.show();
                    }
                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (resultData != null) {
                            Log.i("SUCCESS", "SUCCESS");
                            imageURL = (String) resultData.get("url");
                            hideLoading();
                            Alert.showSuccess(SignatureActivity.this,"Signature Saved");
//                            dialog.dismiss();
//                            Toast.makeText(SignatureActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                            String cloudinaryID = (String) resultData.get("public_id");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);
                            inspectorSignatureUrl =  imageURL;

                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("ERROR", "ERROR");
                       hideLoading();
                        Alert.showFailed(SignatureActivity.this,"Error Uploading Result, Please try again later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        hideLoading();
                        Alert.showFailed(SignatureActivity.this,"signature upload is taking time,Please take picture again");
                        Log.i("SCHEDULE", "SCHEDULE");

                    }
                })
                .dispatch();
//        uploadToCloudinary(inspectorByteArray);

    }

    @Override
    public void onClearInspectorSign() {
        Log.i("CLEAR","CLEAR");
        inspectorSignature.clear();

    }

    @Override
    public void onSaveSupplierSign() {
        Bitmap bitmap =  supplierSignature.getSignatureBitmap();
        Log.i("SignatureBitmap", supplierSignature.getSignatureBitmap().toString());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] suplierByteArray = byteArrayOutputStream .toByteArray();
        showLoading();
        String requestId = MediaManager.get().upload(suplierByteArray)
                .unsigned("ht7lodiw")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        showLoading();
//                        dialog = new ProgressDialog(SignatureActivity.this);
//                        dialog.setMessage("Saving......");
//                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                        dialog.setCancelable(false);
//                        dialog.show();

                    }
                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;
                        Log.i("PROGRESS", "PROGRESS");
//                        dialog.show();
                    }
                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (resultData != null) {
                            Log.i("SUCCESS", "SUCCESS");
                            imageURL = (String) resultData.get("url");
                            hideLoading();
//                            dialog.dismiss();
                            Alert.showSuccess(SignatureActivity.this,"Signature Saved");
//                            Toast.makeText(SignatureActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                            String cloudinaryID = (String) resultData.get("public_id");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);
                            supplierSignatureUrl = imageURL;

                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("ERROR", "ERROR");
                        hideLoading();
//                        dialog.dismiss();
                        Alert.showFailed(SignatureActivity.this,"Error Uploading Result, Please try agin later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        hideLoading();
                        Alert.showFailed(SignatureActivity.this,"Image upload is taking time,please take picture again");
                        Log.i("SCHEDULE", "SCHEDULE");

                    }
                })
                .dispatch();
//        uploadToCloudinary(suplierByteArray);
    }

    @Override
    public void onClearSupplierSign() {
        Log.i("CLEAR","CLEAR");
        supplierSignature.clear();

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.i("RESPONSEEE","REQUEST FAILEDDDD");
        Alert.showFailed(getApplicationContext(),"Monthly report could not be saved to database");
        Intent intent = new Intent(getApplicationContext(), FailedActivity.class);
        startActivity(intent);



    }

    @Override
    public void onStarting() {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
       startActivity(intent);
    }

    @Override
    public void onResponse(CreateReportResponse response) {
        Log.i("RESPONSEEE","REQUEST WAS SUCCESSFULLLL");

        Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
        startActivity(i);
        deleteData();
        Log.i("NEW BIG ARRAY", String.valueOf(signatureViewModel.vehincleReportArray()));
        Log.i("CARYEAR", String.valueOf(signatureViewModel.getCarYear()));
        Log.i("CARMODEL", String.valueOf(signatureViewModel.getCarModel()));
        Log.i("CarMake",String.valueOf(signatureViewModel.getCarMake()));
        Log.i("HOODTRACKIN", String.valueOf(signatureViewModel.getHoodTrackingStatus()));
        Log.i("FENDERS",String.valueOf(signatureViewModel.getFenderTracking()));



    }


    public void saveSignatureUrl(){
            if (signatureImageArray.isSignatureArrayEmpty()){
                signatureImageArray.addUrl("image1",imageURL);
            }else {
                if (signatureImageArray.containKey("image1")){
                    signatureImageArray.addUrl("image2",imageURL);
                }
            }

    }


    @Override
    public void onSubmit() {


        Log.i("Carmake",carMake);
        Log.i("CarYear",carYear);
        Log.i("CarColor",carColor);
        Log.i("Carmodel",carModel);
        Log.i("Carmillage",mileage);
        Log.i("regno",regno);
        Log.i("vin",vin);
        Log.i("finalComment",intakeFinalComment);
        Log.i("finalStatus",intakeFinalStatus);
        Log.i("Carmake", String.valueOf(intakeVehicleDetails));

        vehicleInfo = signatureViewModel.getVehincleInfoStatus();
        carYear = signatureViewModel.getCarYear();
        carMake = signatureViewModel.getCarMake();
        carColor = signatureViewModel.getCarColor();
        carModel = signatureViewModel.getCarModel();
        mileage = signatureViewModel.getMilage();
        regno = signatureViewModel.getregNo();
        vin =signatureViewModel.getVin();
        intakeFinalComment = signatureViewModel.getIntakeFinalComment();
        intakeFinalStatus = signatureViewModel.getIntakeFinalStatus();
        intakeVehicleDetails = signatureViewModel.vehincleReportArray();
        Collection<String> value = signatureImage.values();
        signatureResult = new ArrayList<>(value);
        Log.i("SignatureImage", String.valueOf(signatureResult));


        switch (signatureViewModel.getReportType()){
            case "intake":{
                CreateReportRequest request = new CreateReportRequest(carMake, carModel, carYear, carColor, vin, mileage, regno, supplierSignatureUrl,inspectorSignatureUrl, intakeFinalStatus, intakeFinalComment, "intake",
                        intakeVehicleDetails);
                if (InternetConnection.getInstance(this).isOnline()) {
                    signatureViewModel.createVehicleReport(request);
                }else {
                    Alert.showFailed(getApplicationContext(), "Please Check your internet connection and try again");
                }
                break;
            }case "monthly":{
                MonthlyReportRequest request = new MonthlyReportRequest(carMake, carModel, carYear, carColor, vin, mileage, regno, supplierSignatureUrl,inspectorSignatureUrl, intakeFinalStatus, intakeFinalComment, "monthly",
                        intakeVehicleDetails);
                if (InternetConnection.getInstance(this).isOnline()) {
                    signatureViewModel.createMonthlyReport(request);
                }else {
                    Alert.showFailed(getApplicationContext(), "Please Check your internet connection and try again");
                }
                break;
            }
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    public void deleteData(){
        signatureViewModel.deleteCarYear(carYear);
        signatureViewModel.deleteCarMake(carMake);
        signatureViewModel.deleteCarModel(carModel);
        signatureViewModel.deleteCarColor(carColor);
        signatureViewModel.deleteFinalComment(intakeFinalComment);
        signatureViewModel.deleteFinalStatus(intakeFinalStatus);
        signatureViewModel.deleteMileage(mileage);
        signatureViewModel.deleteIntakeVehicleReportArray(intakeVehicleDetails);
        signatureViewModel.deleteVin(vin);
        signatureViewModel.deleteRegNo(regno);
        signatureViewModel.deleteReportType(reportType);
        signatureViewModel.deleteVehicleInfo(vehicleInfo);
        signatureViewModel.deleteAudioSystemTracking(audioSystem);
        signatureViewModel.deleteBrakelightTracking(brakelight);
        signatureViewModel.deleteComputerTracking(computer);
        signatureViewModel.deleteHeadLightTracking(headlight);
        signatureViewModel.deleteParkingLightTracking(parkingLight);
        signatureViewModel.deletePowerLockTracking(powerLock);
        signatureViewModel.deletePowerMirrorTracking(powerMirror);
        signatureViewModel.deleteSeatTracking(powerSeat);
        signatureViewModel.deletePowerSteeringTracking(powerSteering);
        signatureViewModel.deletePowerWindowTracking(powerWindow);
        signatureViewModel.deleteSignalLightTracking(signalLight);
        signatureViewModel.deleteTailLighTracking(tailLight);
        signatureViewModel.deleteDoorTrackingStatus(door);
        signatureViewModel.deleteFenderTracking(fenders);
        signatureViewModel.deleteFrontBumperTracking(frontBumper);
        signatureViewModel.deleteFuelDoorTracking(fuelDoor);
        signatureViewModel.deleteHoodTracking(hood);
        signatureViewModel.deletePaintTracking(paint);
        signatureViewModel.deleteRearBumperTracking(rearBumper);
        signatureViewModel.deleteRearTracking(rear);
        signatureViewModel.deleteRoofTracking(roof);
        signatureViewModel.deleteTrimTracking(trim);
        signatureViewModel.deleteTrunkTracking(trunk);
        signatureViewModel.deleteMirrorTracking(mirror);
        signatureViewModel.deleteRearWindowTracking(rearWindow);
        signatureViewModel.deleteWindowTracking(window);
        signatureViewModel.deleteWinshieldTracking(winshield);
        signatureViewModel.deleteAircondTracking(airCond);
        signatureViewModel.deleteCarpetTracking(carpet);
        signatureViewModel.deleteDashBoardTracking(dashboard);
        signatureViewModel.deleteDashGuageTracking(dashguages);
        signatureViewModel.deleteDefrosterTracking(defroster);
        signatureViewModel.deleteDoorPanelTracking(doorPanel);
        signatureViewModel.deleteGloveBoxTracking(gloveBox);
        signatureViewModel.deleteHeadLinerTracking(headLiner);
        signatureViewModel.deleteHeaterTracking(heater);
        signatureViewModel.deleteInteriorTrimTracking(interiorTrim);
        signatureViewModel.deleteSeatTracking(seats);
        signatureViewModel.deleteVanityMirrorTracking(vanityMirror);
        signatureViewModel.deleteAccelerationTracking(acceleration);
        signatureViewModel.deleteBrakingTracking(braking);
        signatureViewModel.deleteEnginePerfTracking(enginePerf);
        signatureViewModel.deleteIdlingTracking(idling);
        signatureViewModel.deleteStartingTracking(starting);
        signatureViewModel.deleteSteeringTracking(steering);
        signatureViewModel.deleteSuspensionPerfTracking(suspensionPerf);
        signatureViewModel.deleteTransmissionShiftTracking(transmissionShift);
        signatureViewModel.deleteSpareTireTracking(spareTire);
        signatureViewModel.deleteTiresTracking(tires);
        signatureViewModel.deleteWheelTracking(wheel);
        signatureViewModel.deleteBrakeSystemTracking(brakeSystem);
        signatureViewModel.deleteDriveAxleTracking(driveAxle);
        signatureViewModel.deleteExhaustTracking(exhaust);
        signatureViewModel.deleteFrameTracking(frame);
        signatureViewModel.deleteSuspensionTracking(suspension);
        signatureViewModel.deleteTransmissionTracking(transmission);
        signatureViewModel.deleteBatteryTracking(battery);
        signatureViewModel.deleteBeltTracking(belt);
        signatureViewModel.deleteEngineCompTracking(engineComp);
        signatureViewModel.deleteFluidTracking(fluid);
        signatureViewModel.deleteHosesTracking(hoses);
        signatureViewModel.deleteOilTracking(oil);
        signatureViewModel.deleteWiringTracking(wiring);








    }

}
