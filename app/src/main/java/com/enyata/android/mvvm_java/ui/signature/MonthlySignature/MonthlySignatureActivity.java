package com.enyata.android.mvvm_java.ui.signature.MonthlySignature;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.BuildConfig;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.MonthlyReportRequest;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.remote.RetrofitClient;
import com.enyata.android.mvvm_java.data.remote.RetrofitErrorHandler;
import com.enyata.android.mvvm_java.databinding.ActivityMonthlySignatureBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class MonthlySignatureActivity extends BaseActivity<ActivityMonthlySignatureBinding, MonthlySignatureViewModel>implements MonthlySignatureNavigator {
    @Inject
    ViewModelProviderFactory factory;
    ActivityMonthlySignatureBinding activityMonthlySignatureBinding;
    MonthlySignatureViewModel monthlySignatureViewModel;
    TextView inspectorClear,inspectorSave,supplierClear,supplierSave;
    SignaturePad supplierSignature, inspectorSignature;
    TextView supplierTextView;
    List<VehicleCollection> monthlyVehicleDetails;
    String monthlyFinalComment, monthlyFinalStatus,vehicleId;
    String supplierSignatureUrl, inspectorSignatureUrl,imageURL;
    String monthlyAcceptanceValue;
    Button submitButton;
    RetrofitClient retrofitClient = new RetrofitClient();


    @Inject

    Gson gson;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_monthly_signature;
    }

    @Override
    public MonthlySignatureViewModel getViewModel() {
        monthlySignatureViewModel = ViewModelProviders.of(this, factory).get(MonthlySignatureViewModel.class);
        return monthlySignatureViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlySignatureViewModel.setNavigator(this);

        activityMonthlySignatureBinding = getViewDataBinding();
        supplierSignature = activityMonthlySignatureBinding.supplierSignature;
        inspectorSignature = activityMonthlySignatureBinding.inspectorSignature;
        inspectorClear = activityMonthlySignatureBinding.clearInspectorSignature;
        inspectorSave = activityMonthlySignatureBinding.saveInspectorSignature;
        supplierSave = activityMonthlySignatureBinding.saveSupplierSignature;
        supplierClear = activityMonthlySignatureBinding.clearSupplierSignature;
        supplierTextView = activityMonthlySignatureBinding.supplierTextView;
        submitButton = activityMonthlySignatureBinding.submitReport;
        monthlyAcceptanceValue = monthlySignatureViewModel.getMonthlyAcceptanceValue();
        Log.i("Monthly", monthlyAcceptanceValue);

        monthlyFinalComment = monthlySignatureViewModel.getFinalComment();
        Log.i("INSpector Comment", monthlyFinalComment);
        monthlyFinalStatus = monthlySignatureViewModel.getFinalAssesMent();
        monthlyVehicleDetails = monthlySignatureViewModel.getMonthlyVehicleReport();
        vehicleId = monthlySignatureViewModel.getVehicleId();
        supplierSave.setEnabled(false);
        inspectorSave.setEnabled(false);
        inspectorClear.setEnabled(false);
        supplierClear.setEnabled(false);

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
    public void onSubmit() {
        if (inspectorSignatureUrl != null && supplierSignatureUrl != null){
            Log.i("IMspector Signature", inspectorSignatureUrl);
            Log.i("Driver Signature", supplierSignatureUrl);
            if (InternetConnection.getInstance(this).isOnline()) {
                MonthlyReportRequest request = new MonthlyReportRequest(inspectorSignatureUrl, supplierSignatureUrl, monthlyFinalStatus, monthlyFinalComment, "monthly",monthlyAcceptanceValue, monthlyVehicleDetails);
                monthlySignatureViewModel.createMonthlyReportt(request, vehicleId);
            }else {
                Alert.showFailed(getApplicationContext(),"No Internet Connection");
            }

        }else {
            Alert.showFailed(getApplicationContext(),"Signature is required");
        }

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
                .unsigned(BuildConfig.CLOUDINARY_UPLOAD_PRESET)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        showLoading();
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
                            Alert.showSuccess(MonthlySignatureActivity.this,"Signature Saved");
//
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
                        Alert.showFailed(MonthlySignatureActivity.this,"Error Uploading Result, Please try again later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        hideLoading();
                        Alert.showFailed(MonthlySignatureActivity.this,"signature upload is taking time,Please take picture again");
                        Log.i("SCHEDULE", "SCHEDULE");

                    }
                })
                .dispatch();



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
                .unsigned(BuildConfig.CLOUDINARY_UPLOAD_PRESET)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.i("START", "STARTTTTT");
                        showLoading();
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
                            Alert.showSuccess(MonthlySignatureActivity.this,"Signature Saved");
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
                        Alert.showFailed(MonthlySignatureActivity.this,"Error Uploading Result, Please try agin later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        hideLoading();
                        Alert.showFailed(MonthlySignatureActivity.this,"Image upload is taking time,please take picture again");
                        Log.i("SCHEDULE", "SCHEDULE");

                    }
                })
                .dispatch();

    }

    @Override
    public void onClearSupplierSign() {
        supplierSignature.clear();

    }

    @Override
    public void onStarting() {
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(CreateReportResponse response) {
//        hideLoading();
        Alert.showSuccess(getApplicationContext(), response.getMessage());
        Intent intent = new Intent(getApplicationContext(), ResponseActivity.class);
        startActivity(intent);
        monthlySignatureViewModel.deleteMonthlyReport(monthlyVehicleDetails);
        monthlySignatureViewModel.deleteMonthlyAcceptanceValue(monthlyAcceptanceValue);

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            Intent intent = new Intent(getApplicationContext(), FailedActivity.class);
            startActivity(intent);
            monthlySignatureViewModel.deleteMonthlyReport(monthlyVehicleDetails);
            monthlySignatureViewModel.deleteMonthlyAcceptanceValue(monthlyAcceptanceValue);
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
            } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        monthlySignatureViewModel.onDispose();
    }
}
