package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.exteriorMonthly;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.myData.ImageDataArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.ui.cameraPicture.TakePicture;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportViewModel;
import com.enyata.android.mvvm_java.utils.Alert;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class RearFragmentM extends Fragment {
    String status = "", imageURL, cloudinaryID;
    RadioGroup hoodRadioGroup;
    RadioButton badd, goodd, fairr;
    Context mContext;
    Uri uri;
    Bitmap bitmap;
    ImageView firstImage, secondImage, thirdImage, cancel1, cancel2, cancel3;
    File photoFile = null;
    Button saveHood,deleteData;
    MonthlyReportViewModel monthlyReportViewModel;
    ImageDataArray imageDataArray;
    private String mCurrentPhotoPath;
    private static final int REQUEST_CAMERA = 1;
    private Uri mImageUri = null;
    ProgressBar progressBar;
    CharSequence radio;
    List<String> result;
    String cloudinaryImage;
    MonthlyReportActivity activity;
    Map config;
    View fragment;
    VehicleCollection rear;
    RelativeLayout relativeLayout;
    TakePicture takePicture = new TakePicture();
    HashMap<String, String> imageArray = new HashMap<>();

    public RearFragmentM(){
        //leave it empty
    }

    public static RearFragmentM newInstance() {
        return new RearFragmentM();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlyReportViewModel = ViewModelProviders.of(requireActivity()).get(MonthlyReportViewModel.class);
        imageDataArray = new ImageDataArray(imageArray);
        activity = (MonthlyReportActivity) getActivity();



    }


    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rear_layout, container, false);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        saveHood = view.findViewById(R.id.saveHood);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
        firstImage = view.findViewById(R.id.firstImage);
        secondImage = view.findViewById(R.id.secondImage);
        thirdImage = view.findViewById(R.id.thirdImage);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
        goodd = view.findViewById(R.id.good);
        badd = view.findViewById(R.id.poor);
        fairr = view.findViewById(R.id.fair);
        setRetainInstance(true);




        saveHood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.i("STATUSSS", status);
                    saveReport();
            }
        });



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
        relativeLayout = view.findViewById(R.id.takePicture);
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
            takePicture.pictureCapture(imageBitmap,RearFragmentM.this,firstImage,secondImage,thirdImage,progressBar,getActivity());
        } else if (requestCode == RESULT_CANCELED) {
            Alert.showFailed(getActivity(), "The request was cancelled");


        }
    }

    public void saveReport() {
        if (takePicture.areImagesNotComplete(getActivity())) {
            return;
        } else if (status.isEmpty()) {
            Alert.showFailed(getActivity(), "please fill all fields");
            return;
        } else {
            activity.rear = true;
            activity.checkExteriorFragment();
            imageArray = takePicture.getPictureArray();
            Collection<String> value = imageArray.values();
            result = new ArrayList<>(value);

            rear = new VehicleCollection("rear", "Exterior", result, status);
            monthlyReportViewModel.saveMonthlyReportToLocalStorage(rear);

//        monthlyReportViewModel.setRearTracking(true);
            Alert.showSuccess(getActivity(), "Item saved! Proceed");

        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
