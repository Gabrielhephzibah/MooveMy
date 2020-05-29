package com.enyata.android.mvvm_java.ui.cameraPicture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.ImageDataArray;
import com.enyata.android.mvvm_java.glide.GlideApp;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportViewModel;
import com.enyata.android.mvvm_java.ui.createReport.exterior.HoodFragment;
import com.enyata.android.mvvm_java.utils.Alert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class TakePicture extends Fragment {
    ProgressBar progressBar;
    ImageDataArray imageDataArray;
    ImageView firstImage, secondImage, thirdImage;
    String cloudinaryImage, imageURL, cloudinaryID;
    Fragment fragment;
    CreateReportViewModel createReportViewModel;
    DataManager mDataManager;
    HashMap<String, String> imageArray = new HashMap<>();
    public boolean isImageArraysave = false;



    public TakePicture() {
        imageDataArray = new ImageDataArray(imageArray);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void pictureCapture(Bitmap bitmap, Fragment fragment, ImageView image1, ImageView image2, ImageView image3, ProgressBar progressBar, Activity activity) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] partImage = byteArrayOutputStream.toByteArray();
        MediaManager.get().upload(partImage)
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
                            cloudinaryID = (String) resultData.get("public_id").toString();
                            cloudinaryImage = MediaManager.get().url().transformation(new Transformation()).resourceType("image").generate(cloudinaryID + ".jpg");
                            Alert.showSuccess(activity, "Image uploaded Successfully! Proceed");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);

                            showImage(fragment, image1, image2, image3, activity);
                        }

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        progressBar.setVisibility(View.GONE);
                        Log.i("ERROR", "ERROR");
                        Alert.showFailed(activity, "Error Uploading Result, Please try again later ");
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.i("SCHEDULE", "SCHEDULE");
                        progressBar.setVisibility(View.GONE);
                        Alert.showFailed(activity, "Uploading is taking time,take picture again");

                    }
                })
                .dispatch();



    }



    public void showImage(Fragment fragment, ImageView imageView1, ImageView imageView2, ImageView imageView3, Activity activity) {

        if (imageDataArray.isArrayEmpty()) {

            Log.i("ISEMPTY", "ISWMPTRRRR");
            imageDataArray.addUrl("image0", imageURL);
            GlideApp.with(fragment)
                    .load(cloudinaryImage)
                    .error(R.drawable.error_image)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Alert.showFailed(activity,"Error while loading image");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    }).into(imageView1);

        } else {
            if (!imageDataArray.containKey("image0")) {

                imageDataArray.addUrl("image0", imageURL);
                GlideApp.with(fragment)
                        .load(cloudinaryImage)
                        .error(R.drawable.error_image)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Alert.showFailed(activity,"Error while loading image");
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(imageView1);



            } else {
                if (imageDataArray.containKey("image1")) {
                    if (!imageDataArray.containKey("image2")) {
                        imageDataArray.addUrl("image2", imageURL);
                        GlideApp.with(fragment)
                                .load(cloudinaryImage)
                                .error(R.drawable.error_image)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        Alert.showFailed(activity,"Error while loading image");
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                })
                                .into(imageView3);



                    }
                } else {
                    imageDataArray.addUrl("image1", imageURL);
                    GlideApp.with(fragment)
                            .load(cloudinaryImage)
                            .error(R.drawable.error_image)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    Alert.showFailed(activity,"Error while loading image");
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(imageView2);



                }
            }

        }






        Log.i("IMAGE ARRAY", String.valueOf(imageArray));
    }

    public void removefirstImage(){
        imageDataArray.removeUrl("image0");
    }

    public void removeSecondImage(){
        imageDataArray.removeUrl("image1");
    }

    public void removeThirdImage(){
        imageDataArray.removeUrl("image2");
    }


    public boolean whenImageIsThree(Activity activity) {
        if (imageDataArray.containKey("image1") && imageDataArray.containKey("image2") && imageDataArray.containKey("image0")) {
            Alert.showFailed(activity, "you have uploaded three pictures! Proceed or delete any of the picture");
            return true;

        } else {
            return false;

        }

    }


    public boolean areImagesNotComplete(Activity activity){
        if (!imageDataArray.containKey("image1") || !imageDataArray.containKey("image2") || !imageDataArray.containKey("image0")){
            Alert.showFailed(activity,"Upload all images");
            return true;

        }else {
            return false;
        }
    }



    public HashMap<String,String>getPictureArray(){
        return imageArray;
}


}
