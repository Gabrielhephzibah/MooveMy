package com.enyata.android.mvvm_java.ui.signature;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.SignatureImageArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
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
    SignatureViewModel signatureViewModel;
    SignaturePad supplierSignature, inspectorSignature;
    ActivitySignatureBinding activitySignatureBinding;
    TextView inspectorClear,inspectorSave,supplierClear,supplierSave;
    ProgressDialog dialog;
    SignatureImageArray signatureImageArray;
    String  imageURL,carYear,carMake,carModel,carColor,mileage,vin,regno,intakeFinalComment,intakeFinalStatus;
    List<String> signatureResult;
    List<VehicleCollection> intakeVehicleDetails;
    HashMap<String, String> signatureImage = new HashMap<>();
    List<VehicleCollection>requests;
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
        activitySignatureBinding = getViewDataBinding();
        signatureImageArray = new SignatureImageArray(signatureImage);
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
        supplierSignature = activitySignatureBinding.supplierSignature;
        inspectorSignature = activitySignatureBinding.inspectorSignature;
        inspectorClear = activitySignatureBinding.clearInspectorSignature;
        inspectorSave = activitySignatureBinding.saveInspectorSignature;
        supplierSave = activitySignatureBinding.saveSupplierSignature;
        supplierClear = activitySignatureBinding.clearSupplierSignature;


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


            }

            @Override
            public void onClear() {


            }
        });

        inspectorSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Log.i("StartSigning", "Start Signing");
            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {

            }
        });


    }


    @Override
    public void onSaveInspectorSign() {
        Log.i("save","SAVE");
      Bitmap bitmap =  inspectorSignature.getSignatureBitmap();
        Toast.makeText(SignatureActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
        Log.i("SignatureBitmap", inspectorSignature.getSignatureBitmap().toString());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] inspectorByteArray = byteArrayOutputStream .toByteArray();
        uploadToCloudinary(inspectorByteArray);

    }

    @Override
    public void onClearInspectorSign() {
        Log.i("CLEAR","CLEAR");
        inspectorSignature.clear();

    }

    @Override
    public void onSaveSupplierSign() {
        Log.i("save","SAVE");
        Bitmap bitmap =  supplierSignature.getSignatureBitmap();
        Log.i("SignatureBitmap", supplierSignature.getSignatureBitmap().toString());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] suplierByteArray = byteArrayOutputStream .toByteArray();
        uploadToCloudinary(suplierByteArray);
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

    }

    @Override
    public void onResponse(CreateReportResponse response) {
        Log.i("RESPONSEEE","REQUEST WAS SUCCESSFULLLL");

    }

    public void uploadToCloudinary(byte [] image){
        String requestId = MediaManager.get().upload(image)
                .unsigned("ht7lodiw")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        dialog = new ProgressDialog(SignatureActivity.this);
                        dialog.setMessage("Saving......");
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setCancelable(false);
                        dialog.show();

                    }
                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Double progress = (double) bytes / totalBytes;
                        Log.i("PROGRESS", "PROGRESS");
                        dialog.show();
                    }
                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (resultData != null) {
                            Log.i("SUCCESS", "SUCCESS");
                            imageURL = (String) resultData.get("url");
                            dialog.dismiss();
                            Toast.makeText(SignatureActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
                            String cloudinaryID = (String) resultData.get("public_id");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);
                            saveSignatureUrl();
                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("ERROR", "ERROR");
                        dialog.dismiss();
                        Alert.showFailed(SignatureActivity.this,"Error Uploading Result, Please try agin later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        dialog.dismiss();
                        Log.i("SCHEDULE", "SCHEDULE");

                    }
                })
                .dispatch();

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

       CreateReportRequest request = new CreateReportRequest(carMake,carModel,carYear,carColor,vin,mileage,regno,signatureResult,intakeFinalStatus,intakeFinalComment,"intake",
               intakeVehicleDetails);

       if (InternetConnection.getInstance(this).isOnline()) {
           sendPost(request);
       }else {
           Alert.showFailed(getApplicationContext(), "Please Check your internet connection and try again");
       }
//                "intake",intakeVehicleDetails);
//        signatureViewModel.createIntakeReport(request);`


    }



    public void sendPost(CreateReportRequest request) {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);// RxJava
        disposable.add(
        mAPIService.savePost(signatureViewModel.getAccessToken(), request )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportRequest -> {
                    Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
                    startActivity(i);
                    Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                },throwable -> {
                    Log.i("Error","ERRROR");
                    Intent intent1 = new Intent(getApplicationContext(), FailedActivity.class);
                    startActivity(intent1);
                }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

}
