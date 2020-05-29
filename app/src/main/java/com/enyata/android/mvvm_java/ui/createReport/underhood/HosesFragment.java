package com.enyata.android.mvvm_java.ui.createReport.underhood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.BuildConfig;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.myData.ImageDataArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.ui.cameraPicture.TakePicture;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportViewModel;
import com.enyata.android.mvvm_java.ui.createReport.exterior.DoorFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.StartingFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.BrakeSystemFragment;
import com.enyata.android.mvvm_java.utils.Alert;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HosesFragment extends Fragment {
    String status = "", imageURL, cloudinaryID;
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
    VehicleCollection hoses;
    View fragment;
    TakePicture takePicture = new TakePicture();
    HashMap<String, String> imageArray = new HashMap<>();

    public HosesFragment(){
        //leave it empty
    }

    public static HosesFragment newInstance() {
        return new HosesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createReportViewModel = ViewModelProviders.of(requireActivity()).get(CreateReportViewModel.class);
        imageDataArray = new ImageDataArray(imageArray);



    }


    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hoses_layout, container, false);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        saveHood = view.findViewById(R.id.saveHood);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
//        deleteData = view.findViewById(R.id.deletedata);
//        deleteData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("DELETE", "Delete data");
//                createReportViewModel.deleteAll();
////                createReportViewModel.deleteData(createReportViewModel.getIntakeVehicleReport());
//                Log.i("NEWARRAY", String.valueOf(createReportViewModel.getIntakeVehicleReport()));
//                Log.i("MAKE",String.valueOf(createReportViewModel.getCarMake()));
//                Log.i("MODEL",String.valueOf(createReportViewModel.getCarModel()));
//                Log.i("Color",String.valueOf(createReportViewModel.getCarColor()));
//                Log.i("Year", String.valueOf(createReportViewModel.getCarYear()));
//            }
//        });

        saveHood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createReportViewModel.getHosesTracking()){
                    Alert.showSuccess(getActivity(), "Item already saved");
                }else {
                    Log.i("STATUSSS", status);
                    saveReport();
                }
            }
        });


        firstImage = view.findViewById(R.id.firstImage);
        secondImage = view.findViewById(R.id.secondImage);
        thirdImage = view.findViewById(R.id.thirdImage);
        cancel1 = view.findViewById(R.id.cancel1);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstImage.getDrawable()==null){
                    Alert.showFailed(getActivity(),"Image is empty");
                }else {
                    takePicture.removefirstImage();
                    Alert.showSuccess(getActivity(), "Image removed");
                    firstImage.setImageResource(0);
                }
            }
        });
        cancel2 = view.findViewById(R.id.cancel2);
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondImage.getDrawable()==null){
                    Alert.showFailed(getActivity(),"Image is empty");
                }else {
                    takePicture.removeSecondImage();
                    Alert.showSuccess(getActivity(), "Image removed");
                    secondImage.setImageResource(0);
                }
            }
        });
        cancel3 = view.findViewById(R.id.cancel3);
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thirdImage.getDrawable()==null){
                    Alert.showFailed(getActivity(),"Image is empty");
                }else {
                    takePicture.removeThirdImage();
                    Alert.showSuccess(getActivity(), "Image removed");
                    thirdImage.setImageResource(0);
                }
            }
        });
        RelativeLayout relativeLayout = view.findViewById(R.id.takePicture);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (takePicture.whenImageIsThree(getActivity())){
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
                    {
                        startActivityForResult(cameraIntent, 100);
                    }

                }
            }
        });

        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
        goodd = view.findViewById(R.id.good);
        badd = view.findViewById(R.id.poor);
        fairr = view.findViewById(R.id.fair);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            takePicture.pictureCapture(imageBitmap,HosesFragment.this,firstImage,secondImage,thirdImage,progressBar,getActivity());
        } else if (requestCode == RESULT_CANCELED) {
            Alert.showFailed(getActivity(),"The request was cancelled");

        }
    }

    public void saveReport() {

        if (takePicture.areImagesNotComplete(getActivity())) {
            return;
        } else if (status.isEmpty()) {
            Alert.showFailed(getActivity(),"please fill all fields");
            return;
        }

        imageArray = takePicture.getPictureArray();
        Collection<String> value = imageArray.values();
        result = new ArrayList<>(value);

         hoses = new VehicleCollection("hoses","Underhood", result, status);
        createReportViewModel.saveReportToLocalStorage(hoses);
        createReportViewModel.setHosesTracking(true);
        Alert.showSuccess(getActivity(),"Item saved! Proceed");

    }

    @Override
    public void onResume() {
        super.onResume();
        hoses = new VehicleCollection("hoses","Underhood", result, status);
        createReportViewModel.isVehicleSave(hoses,goodd,fairr,badd, HosesFragment.this,firstImage,secondImage,thirdImage);
    }
}
