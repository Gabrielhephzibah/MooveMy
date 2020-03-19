package com.enyata.android.mvvm_java.ui.createReport.underhood;

import android.content.Context;
import android.content.Intent;
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
import com.enyata.android.mvvm_java.ui.createReport.CreateReportViewModel;
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

public class BeltFragment extends Fragment {
    String status = "",imageURL,cloudinaryID;
    RadioGroup hoodRadioGroup;
    RadioButton badd, goodd, fairr;
    Context mContext;
    Uri uri;
    ImageView firstImage, secondImage, thirdImage, cancel1, cancel2, cancel3;
    File photoFile = null;
    Button saveHood;
    CreateReportViewModel createReportViewModel;
    ImageDataArray imageDataArray;
    private String mCurrentPhotoPath;
    private static final int REQUEST_CAMERA = 1;
    private Uri mImageUri = null;
    ProgressBar progressBar;
    List<String> result;
    HashMap<String, String> imageArray = new HashMap<>();

    public BeltFragment(){
        //leave it empty
    }

    public static BeltFragment newInstance() {
        return new BeltFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createReportViewModel = ViewModelProviders.of(requireActivity()).get(CreateReportViewModel.class);

        imageDataArray = new ImageDataArray(imageArray);

        Map config = new HashMap();
        config.put("cloud_name", "dtt1nmogz");
        config.put("api_key", "754277299533971");
        config.put("api_secret", "hwuDlRgCtSpxKOg9rcY43AtsZvw");
//      MediaManager.init(getActivity().getApplicationContext(), config);
        Log.d("oooooo", "ffffff");

    }


    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        mContext = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.belt_layout, container, false);


    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        saveHood = view.findViewById(R.id.saveHood);
        firstImage = view.findViewById(R.id.firstImage);
        secondImage = view.findViewById(R.id.secondImage);
        thirdImage = view.findViewById(R.id.thirdImage);
        cancel1 = view.findViewById(R.id.cancel1);
        hoodRadioGroup = view.findViewById(R.id.hoodRadioGroup);
        goodd = view.findViewById(R.id.good);
        badd = view.findViewById(R.id.poor);
        cancel2 = view.findViewById(R.id.cancel2);
        cancel3 = view.findViewById(R.id.cancel3);
        RelativeLayout relativeLayout = view.findViewById(R.id.takePicture);
        fairr = view.findViewById(R.id.fair);
        saveHood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReport();
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

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageDataArray.containKey("image1") && imageDataArray.containKey("image2") && imageDataArray.containKey("image0")) {
                    imageDataArray.removeUrl("image0");
                    imageDataArray.removeUrl("image1");
                    imageDataArray.removeUrl("image2");
                    firstImage.setImageResource(0);

                    secondImage.setImageResource(0);

                    thirdImage.setImageResource(0);
                    Alert.showFailed(getActivity(),"You have alraedy uploaded three picture,Click again to take pictures again");
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {


                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            Log.i("kkkkkkk", "IOException");
                        }
                        if (photoFile != null) {

                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            try {
                                uri = FileProvider.getUriForFile(getActivity().getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", createImageFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);


                            startActivityForResult(cameraIntent, 100);
                        }


                    }
                }
            }
        });

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageDataArray.removeUrl("image0");
                Toast.makeText(getActivity(), "this image has been removed", Toast.LENGTH_LONG).show();
                firstImage.setImageResource(0);
            }
        });

        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDataArray.removeUrl("image1");
                Toast.makeText(getActivity(), "this image has been removed", Toast.LENGTH_LONG).show();
                secondImage.setImageResource(0);

            }
        });

        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDataArray.removeUrl("image2");
                Toast.makeText(getActivity(), "This image has been removed", Toast.LENGTH_LONG).show();
                thirdImage.setImageResource(0);
            }
        });

    }

    public void saveReport() {
        if (!imageDataArray.containKey("image1") || !imageDataArray.containKey("image2") || !imageDataArray.containKey("image0")) {
            Toast.makeText(getActivity(), "please make sure you upload all images", Toast.LENGTH_LONG).show();
            return;
        } else if (status.isEmpty()) {
            Toast.makeText(getActivity(), "please fill all fields", Toast.LENGTH_LONG).show();
            return;
        } else {
            Collection<String> value = imageArray.values();
            result = new ArrayList<>(value);
        }


        VehicleCollection belt = new VehicleCollection("belt", result, status);
        createReportViewModel.saveReportToLocalStorage(belt);
        Toast.makeText(getActivity(), "Item saved please swipe to proceed ", Toast.LENGTH_SHORT).show();

    }

    public void showImage() {
        if (imageDataArray.isArrayEmpty()) {
            Log.i("ISEMPTY", "ISWMPTRRRR");
            imageDataArray.addUrl("image0", imageURL);
            Picasso.get().load(imageURL).fit().into(firstImage);

        } else {
            if (!imageDataArray.containKey("image0")) {
                imageDataArray.addUrl("image0", imageURL);
                Picasso.get().load(imageURL).fit().into(firstImage);
            }else{
                if (imageDataArray.containKey("image1")) {
                    if (!imageDataArray.containKey("image2")) {
                        imageDataArray.addUrl("image2", imageURL);
                        Picasso.get().load(imageURL).fit().into(thirdImage);

                    }
                } else {
                    imageDataArray.addUrl("image1", imageURL);
                    Picasso.get().load(imageURL).fit().into(secondImage);

                }
            }

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            Log.d("tttttt", String.valueOf(requestCode));
            Log.d("tttttt", mCurrentPhotoPath);
            String requestId = MediaManager.get().upload(uri)
                    .option("resource_type", "image")
                    .unsigned("ht7lodiw")
                    .callback(new UploadCallback() {
                        @Override
                        public void onStart(String requestId) {
                            Log.i("START", "STARTTTTT");
                            progressBar.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {
                            Double progress = (double) bytes / totalBytes;
                            progressBar.setVisibility(View.VISIBLE);
                            Log.i("PROGRESS", "PROGRESS");
                        }
                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            if (resultData != null) {
                                Log.i("SUCCESS", "SUCCESS");
                                progressBar.setVisibility(View.GONE);
                                imageURL = (String) resultData.get("url");
                                cloudinaryID = (String) resultData.get("public_id");
                                Toast.makeText(getActivity(), "Image uploaded Successfully, You can take another picture now", Toast.LENGTH_LONG).show();
                                Log.i("imageURL", imageURL);
                                Log.i("cloudinaryID", cloudinaryID);
                                showImage();
                            }

                        }

                        @Override
                        public void onError(String requestId, ErrorInfo error) {
                            progressBar.setVisibility(View.GONE);
                            Log.i("ERROR", "ERROR");
                            Alert.showFailed(getActivity(),"Error Uploading Result, Please try agin later ");
                        }

                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {
                            Log.i("SCHEDULE", "SCHEDULE");

                        }
                    })
                    .dispatch();

        } else if (requestCode == RESULT_CANCELED) {
            Log.i("AAAAAAA", "Error");

        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "moove " + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".png",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


}
