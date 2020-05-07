package com.enyata.android.mvvm_java.ui.createReport.exterior;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.myData.ImageDataArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.glide.GlideApp;
import com.enyata.android.mvvm_java.ui.cameraPicture.TakePicture;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportViewModel;
import com.enyata.android.mvvm_java.utils.Alert;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HoodFragment extends Fragment {
    String  status = "", imageURL, cloudinaryID;
    RadioGroup hoodRadioGroup;
    RadioButton badd, goodd, fairr;
    Context mContext;
    Uri uri;
    Bitmap bitmap;
    ImageView firstImage, secondImage, thirdImage, cancel1, cancel2, cancel3;
    File photoFile = null;
    Button saveHood,deleteData;
    CreateReportViewModel createReportViewModel;
    ImageDataArray imageDataArray;
    private String mCurrentPhotoPath;
    private static final int REQUEST_CAMERA = 1;
    private Uri mImageUri = null;
    ProgressBar progressBar;
    CharSequence radio;
    List<String>result;
    String cloudinaryImage;
    Map config;
    VehicleCollection hood;
    View fragment;
    String newStatus;
    ImageView imageView;
    Collection<String> value;
    RelativeLayout relativeLayout;

    TakePicture takePicture = new TakePicture();
    HashMap<String, String> imageArray = new HashMap<>();

    public HoodFragment() {
        //leave it empty
    }

    public static HoodFragment newInstance() {
        return new HoodFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createReportViewModel = ViewModelProviders.of(requireActivity()).get(CreateReportViewModel.class);
        imageDataArray = new ImageDataArray(imageArray);


        config = new HashMap();
            config.put("cloud_name", "dtt1nmogz");
            config.put("api_key", "754277299533971");
            config.put("api_secret", "hwuDlRgCtSpxKOg9rcY43AtsZvw");
            Log.d("oooooo", "ffffff");


    }


    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private boolean checkExternalPermission() {
        return (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA, WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.hood_layout, container, false);



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
        goodd = view.findViewById(R.id.good);
        badd = view.findViewById(R.id.poor);
        fairr = view.findViewById(R.id.fair);
        firstImage = view.findViewById(R.id.firstImage);
        secondImage = view.findViewById(R.id.secondImage);
        thirdImage = view.findViewById(R.id.thirdImage);



//
//        if (createReportViewModel.getHoodTrackingStatus()){
//            if (createReportViewModel.checkIfIntakeVehicleReportIsEmpty()){
//                goodd.setChecked(false);
//                fairr.setChecked(false);
//                badd.setChecked(false);
//                firstImage.setImageResource(0);
//                secondImage.setImageResource(0);
//                thirdImage.setImageResource(0);
//
//            }else {
//                List<VehicleCollection> myCollection = createReportViewModel.getIntakeVehicleReport();
//                for (int i = 0; i < myCollection.size(); i++) {
//                    if (myCollection.get(i).getPart().equals("hood")) {
//                        if (myCollection.get(i).getRemark().equals("good")) {
//                            goodd.setChecked(true);
//                        } else if (myCollection.get(i).getRemark().equals("fair")) {
//                            fairr.setChecked(true);
//                        } else {
//                            badd.setChecked(true);
//                        }
//                        List<String> images = myCollection.get(i).getImageUrl();
//                        GlideApp.with(HoodFragment.this).load(images.get(0)).into(firstImage);
//                        GlideApp.with(HoodFragment.this).load(images.get(1)).into(secondImage);
//                        GlideApp.with(HoodFragment.this).load(images.get(2)).into(thirdImage);
//
//                    }
//                }
//            }
//
//        }


        progressBar = (ProgressBar) view.findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        saveHood = view.findViewById(R.id.saveHood);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);

        saveHood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createReportViewModel.getHoodTrackingStatus()){
                    Alert.showSuccess(getActivity(), "Item already saved");
                }else {


                    saveReport();

                }

            }
        });



        cancel1 = view.findViewById(R.id.cancel1);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstImage.getDrawable() == null){
                    Alert.showFailed(getActivity(),"image is empty");
                }else {
                    takePicture.removefirstImage();
                    Alert.showSuccess(getActivity(), "this image has been removed");
                    firstImage.setImageResource(0);
                }
            }
        });
        cancel2 = view.findViewById(R.id.cancel2);
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondImage.getDrawable() == null){
                    Alert.showFailed(getActivity(),"Image is empty");
                }else {
                    takePicture.removeSecondImage();
                    Alert.showSuccess(getActivity(), "this image has been removed");
//                Toast.makeText(getActivity(), "this image has been removed", Toast.LENGTH_LONG).show();
                    secondImage.setImageResource(0);
                }
            }
        });
        cancel3 = view.findViewById(R.id.cancel3);
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thirdImage.getDrawable()==null){
                    Alert.showFailed(getActivity(), "Image is empty");
                }else {
                    takePicture.removeThirdImage();
                    Alert.showSuccess(getActivity(), "this image has been removed");
                    thirdImage.setImageResource(0);
                }
            }
        });
        relativeLayout = view.findViewById(R.id.takePicture);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (takePicture.whenImageIsThree(getActivity())){
                } else {
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
                        if (checkPermission() && checkExternalPermission()) {
                        } else {
                            requestPermission();
                        }
                    }
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
                    {
                        startActivityForResult(cameraIntent, 100);
                    }

                }
            }
        });


        hoodRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.good) {
                    status = "good";
                    goodd.setTextColor(Color.parseColor("#E53012"));

                } else if (checkedId == R.id.fair) {
                    status = "fair";
                    fairr.setTextColor(Color.parseColor("#E53012"));
                } else {
                    status = "poor";
                    badd.setTextColor(Color.parseColor("#E53012"));
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean externalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && externalStorage) {
                        Alert.showSuccess(getActivity().getApplicationContext(), "Permission Granted, Now you can access camera");
                    } else {
                        Alert.showSuccess(getActivity().getApplicationContext(), "Permission Denied, You cannot access camera");

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
        new AlertDialog.Builder(getActivity().getApplicationContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void onResume() {
        Log.i("IMAGEARRAY", String.valueOf(result));
        Log.i("Status", status);


            hood = new VehicleCollection("hood", result, status);
            createReportViewModel.isVehicleSave(hood, goodd, fairr, badd, HoodFragment.this, firstImage, secondImage, thirdImage);

//
//        if (createReportViewModel.getHoodTrackingStatus()){
//            if (createReportViewModel.checkIfIntakeVehicleReportIsEmpty()){
//                goodd.setChecked(false);
//                fairr.setChecked(false);
//                badd.setChecked(false);
//                firstImage.setImageResource(0);
//                secondImage.setImageResource(0);
//                thirdImage.setImageResource(0);
//
//            }else {
//                List<VehicleCollection> myCollection = createReportViewModel.getIntakeVehicleReport();
//                for (int i = 0; i < myCollection.size(); i++) {
//                    if (myCollection.get(i).getPart().equals("hood")) {
//                        if (myCollection.get(i).getRemark().equals("good")) {
//                            goodd.setChecked(true);
//                        } else if (myCollection.get(i).getRemark().equals("fair")) {
//                            fairr.setChecked(true);
//                        } else {
//                            badd.setChecked(true);
//                        }
//                        List<String> images = myCollection.get(i).getImageUrl();
//                        GlideApp.with(HoodFragment.this).load(images.get(0)).into(firstImage);
//                        GlideApp.with(HoodFragment.this).load(images.get(1)).into(secondImage);
//                        GlideApp.with(HoodFragment.this).load(images.get(2)).into(thirdImage);
//
//                    }
//                }
//            }
//
//        }
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            takePicture.pictureCapture(imageBitmap,HoodFragment.this,firstImage,secondImage,thirdImage,progressBar,getActivity());

        } else if (requestCode == RESULT_CANCELED) {
          Alert.showFailed(getActivity(),"The request has been cancelled");

        }
    }

    public void saveReport() {

        if (takePicture.areImagesNotComplete(getActivity())) {
            return;
        } else if (status.isEmpty()) {
            Alert.showFailed(getActivity(),"please fill all fields");
            return;
        }else {

            imageArray = takePicture.getPictureArray();
            value = imageArray.values();
            result = new ArrayList<>(value);
            hood = new VehicleCollection("hood", result, status);
            createReportViewModel.saveReportToLocalStorage(hood);
            createReportViewModel.setHoodTrackingStatus(true);
            Alert.showSuccess(getActivity(), "Item saved please swipe to proceed");
        }

    }

}
