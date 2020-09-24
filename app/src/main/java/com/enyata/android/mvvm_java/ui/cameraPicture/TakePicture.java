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
import com.enyata.android.mvvm_java.BuildConfig;
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
import static android.content.Context.LOCATION_SERVICE;

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
                .unsigned(BuildConfig.CLOUDINARY_UPLOAD_PRESET)
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
            Log.i("ImageDataIsEmpty","EmptyImageArray");
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
                Log.i("NoImage0", "Image0IsEmpty");
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
                    Log.i("Image1Pressent", "Image1IsEmpty");
                    if (!imageDataArray.containKey("image2")) {
                        Log.i("NoImage2", "Image2IsEmpty");
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
                    Log.i("AddImage1", "AddEmpty1");
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


    public void pictureCaptureNew(Bitmap bitmap, Fragment fragment, ImageView image1, ImageView image2, ImageView image3, ProgressBar progressBar, Activity activity, ImageDataArray arrayNew) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] partImage = byteArrayOutputStream.toByteArray();
        MediaManager.get().upload(partImage)
                .option("resource_type", "image")
                .unsigned(BuildConfig.CLOUDINARY_UPLOAD_PRESET)
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
                            cloudinaryImage = MediaManager.get().url().transformation(new Transformation().quality(40)).resourceType("image").generate(cloudinaryID + ".jpg");
                            Alert.showSuccess(activity, "Image uploaded Successfully! Proceed");
                            Log.i("imageURL", imageURL);
                            Log.i("cloudinaryID", cloudinaryID);

                            if (!arrayNew.isArrayEmpty()){

                                if (arrayNew.sizeOfHashMap() == 2 && arrayNew.getUrl("image0")!=null){
                                    imageDataArray.addUrl("image0", arrayNew.getUrl("image0"));
                                    HashMap<String, String> newImages = new HashMap<>();
                                    newImages.put("image0", imageDataArray.getUrl("image0"));
                                    Log.i("NewImagesAt0", String.valueOf(newImages));
                                    Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                                    imageDataArray.updateImageArray(newImages);

                                }else if (arrayNew.sizeOfHashMap() == 3 && arrayNew.getUrl("image0")!=null && arrayNew.getUrl("image1")!=null){
                                    imageDataArray.addUrl("image0", arrayNew.getUrl("image0"));
                                    imageDataArray.addUrl("image1", arrayNew.getUrl("image1"));
                                    HashMap<String, String> newImages = new HashMap<>();
                                    newImages.put("image0", imageDataArray.getUrl("image0"));
                                    newImages.put("image1", imageDataArray.getUrl("image1"));
                                    Log.i("NewImagesAt1", String.valueOf(newImages));
                                    Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                                    imageDataArray.updateImageArray(newImages);

                                } else if (arrayNew.sizeOfHashMap()==4 && arrayNew.getUrl("image0")!=null && arrayNew.getUrl("image1")!=null && arrayNew.getUrl("image2")!=null){
                                    imageDataArray.addUrl("image0", arrayNew.getUrl("image0"));
                                    imageDataArray.addUrl("image1", arrayNew.getUrl("image1"));
                                    imageDataArray.addUrl("image2",arrayNew.getUrl("image2"));
                                    HashMap<String, String> newImages = new HashMap<>();
                                    newImages.put("image0", imageDataArray.getUrl("image0"));
                                    newImages.put("image1", imageDataArray.getUrl("image1"));
                                    newImages.put("image2", imageDataArray.getUrl("image2"));
                                    Log.i("NewImagesAt2", String.valueOf(newImages));
                                    Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                                    imageDataArray.updateImageArray(newImages);

                                }

                                Log.i("FragmentImageData", String.valueOf(imageDataArray));
                            }
                            showImageNew(fragment, image1, image2, image3, activity,arrayNew);
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

    public void showImageNew(Fragment fragment, ImageView imageView1, ImageView imageView2, ImageView imageView3, Activity activity, ImageDataArray myArray) {
        Log.i("SecondImageArray", String.valueOf(myArray));
        Log.i("FirstImageArray", String.valueOf(imageArray));
        Log.i("IMAGEARRAY", String.valueOf(imageDataArray));
        if (!myArray.isArrayEmpty() && !imageDataArray.isArrayEmpty()){
            Log.i("Both Are Not Empty", "Both are not empty");
            if (!imageDataArray.containKey("image0")){
                imageDataArray.addUrl("image0", imageURL);
                myArray.addUrl("image0",imageURL);
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

            }else if (imageDataArray.containKey("image1")){
                Log.i("Image1Pressent", "Image1IsEmpty");
                if (!imageDataArray.containKey("image2")) {
                    Log.i("NoImage2", "Image2IsEmpty");
                    imageDataArray.addUrl("image2", imageURL);
                    myArray.addUrl("image2",imageURL);
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

            }else if (!imageDataArray.containKey("image1")){
                Log.i("AddImage1", "AddEmpty1");
                imageDataArray.addUrl("image1", imageURL);
                myArray.addUrl("image1",imageURL);
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

            Log.i("SecondImageArray", String.valueOf(myArray));
            Log.i("FirstImageArray", String.valueOf(imageArray));
            Log.i("IMAGEARRAY", String.valueOf(imageDataArray));


        }else if (!myArray.isArrayEmpty()){
            Log.i("SecondArrayNotEmpty", "SecondArrayNotEmpty");
            if (!myArray.containKey("image0")){
                imageDataArray.addUrl("image0", imageURL);
                myArray.addUrl("image0",imageURL);
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

            }else if (!myArray.containKey("image1")){
                imageDataArray.addUrl("image1", imageURL);
                myArray.addUrl("image1",imageURL);
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
            }else if (!myArray.containKey("image2")){
                imageDataArray.addUrl("image2", imageURL);
                myArray.addUrl("image2",imageURL);
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

            Log.i("SecondImageArray", String.valueOf(myArray));
            Log.i("FirstImageArray", String.valueOf(imageArray));
            Log.i("IMAGEARRAY", String.valueOf(imageDataArray));

        } else if (imageDataArray.isArrayEmpty()) {
            Log.i("ImageDataIsEmpty","EmptyImageArray");
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
                Log.i("NoImage0", "Image0IsEmpty");
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
                    Log.i("Image1Pressent", "Image1IsEmpty");
                    if (!imageDataArray.containKey("image2")) {
                        Log.i("NoImage2", "Image2IsEmpty");
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
                    Log.i("AddImage1", "AddEmpty1");
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



        Log.i("SecondImageArray", String.valueOf(myArray));
        Log.i("FirstImageArray", String.valueOf(imageArray));
        Log.i("IMAGEARRAY", String.valueOf(imageDataArray));


        Log.i("IMAGE ARRAY", String.valueOf(imageDataArray.getImageArray()));
    }




    public void removefirstImage() {
        imageDataArray.removeUrl("image0");
        Log.i("removedImage0", String.valueOf(imageDataArray.getImageArray()));

    }

    public void removeSecondImage() {
        imageDataArray.removeUrl("image1");
        Log.i("removedImage1", String.valueOf(imageDataArray.getImageArray()));
    }

    public void removeThirdImage(){
        imageDataArray.removeUrl("image2");
        Log.i("removedImage2", String.valueOf(imageDataArray.getImageArray()));
    }

    public void removeFirstImagee(ImageDataArray remove){
        if (!imageDataArray.isArrayEmpty() && remove.isArrayEmpty()){
            imageDataArray.removeUrl("image0");
            Log.i("removedImage0", String.valueOf(imageDataArray.getImageArray()));

        }else if (!remove.isArrayEmpty()){
            Log.i("here", "second block");
           Log.i("oldArray", String.valueOf(imageDataArray.getImageArray()));
           Log.i("NewArray", String.valueOf(remove));

            if (remove.containKey("image0") && !remove.containKey("image1") && !remove.containKey("image2") ){
                Log.i("HERE1","HERE1");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image1") && !remove.containKey("image2") ){
                Log.i("HERE2","HERE2");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            } else if (remove.containKey("image0") && remove.containKey("image1") && remove.containKey("image2")){
                Log.i("HERE3","HERE3");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2",remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt2", String.valueOf(newImages));
                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") && !remove.containKey("image2") && !remove.containKey("image0")) {
                Log.i("HERE4","HERE4");
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else  if (remove.containKey("image2") && !remove.containKey("image1") && !remove.containKey("image0") ) {
                Log.i("HERE5","HERE5");
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") && remove.containKey("image2") && !remove.containKey("image0") ) {
                Log.i("HERE6","HERE6");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image2") && !remove.containKey("image1")) {
                Log.i("HERE7","HERE7");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }
                remove.removeUrl("image0");
                imageDataArray.removeUrl("image0");

                Log.i("oldArrayNow", String.valueOf(imageDataArray.getImageArray()));
                Log.i("SizeOFHahmap", String.valueOf(remove.sizeOfHashMap()));
                Log.i("RemoveNew", String.valueOf(remove));




        }else if (!imageDataArray.isArrayEmpty() && !remove.isArrayEmpty()){
            Log.i("THird block", "Third block");
        }
    }


    public void removeSecondImagee(ImageDataArray remove){
        if (!imageDataArray.isArrayEmpty() && remove.isArrayEmpty()){
            Log.i("FirstBlock","FirstBlock");
            imageDataArray.removeUrl("image1");
        }else if (!remove.isArrayEmpty()){
            Log.i("oldArray", String.valueOf(imageDataArray.getImageArray()));
            Log.i("NewArray", String.valueOf(remove));
            Log.i("secondBlock","secondBlock");
            if (remove.containKey("image0") && !remove.containKey("image1") && !remove.containKey("image2")){
                Log.i("HERE1","HERE1");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image1") && !remove.containKey("image2")){
                Log.i("HERE2","HERE2");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            } else if (remove.containKey("image0") && remove.containKey("image1") && remove.containKey("image2")){
                Log.i("HERE3","HERE3");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2",remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt2", String.valueOf(newImages));
                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") && !remove.containKey("image0") && !remove.containKey("image2")) {
                Log.i("HERE4","HERE4");
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else  if (remove.containKey("image2") && !remove.containKey("image1") && !remove.containKey("image0") ) {
                Log.i("HERE5","HERE5");
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") && remove.containKey("image2") && !remove.containKey("image0")  ) {
                Log.i("HERE6","HERE6");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image2")  && !remove.containKey("image1") ) {
                Log.i("HERE7","HERE7");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }

            remove.removeUrl("image1");
            imageDataArray.removeUrl("image1");


            Log.i("oldArrayNow", String.valueOf(imageDataArray.getImageArray()));
            Log.i("SizeOFHahmap",String.valueOf(remove.sizeOfHashMap()));
            Log.i("RemoveNew",String.valueOf(remove));

        }else  if (!imageDataArray.isArrayEmpty() && !remove.isArrayEmpty()){
            Log.i("thirdblock","thirdblock");
        }
        }


    public void removethirdImagee(ImageDataArray remove){
        if (!imageDataArray.isArrayEmpty() && remove.isArrayEmpty()){
            imageDataArray.removeUrl("image2");
        }else if (!remove.isArrayEmpty()){
            Log.i("NewArray", String.valueOf(remove));
            Log.i("secondBlock","secondBlock");
            Log.i("oldArray", String.valueOf(imageDataArray.getImageArray()));
            if (remove.containKey("image0") &&!remove.containKey("image1") && !remove.containKey("image2") ){
                Log.i("HERE1","HERE1");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image1") && !remove.containKey("image2") ){
                Log.i("HERE2","HERE2");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            } else if (remove.containKey("image0") && remove.containKey("image1") && remove.containKey("image2")){
                Log.i("HERE3","HERE3");
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2",remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt2", String.valueOf(newImages));
                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") &&  !remove.containKey("image2") && !remove.containKey("image0")) {
                Log.i("HERE4","HERE4");
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else  if (remove.containKey("image2") && !remove.containKey("image0") && !remove.containKey("image1") ) {
                Log.i("HERE5","HERE5");
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image1") && remove.containKey("image2") && !remove.containKey("image0")  ) {
                Log.i("HERE6","HERE6");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image1", remove.getUrl("image1"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (remove.containKey("image0") && remove.containKey("image2") && !remove.containKey("image1") ) {
                Log.i("HERE7","HERE7");
                Log.i("imagedata", String.valueOf(imageDataArray));
                Log.i("sizeOFremove", String.valueOf(remove));
                imageDataArray.addUrl("image0", remove.getUrl("image0"));
                imageDataArray.addUrl("image2", remove.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }

            remove.removeUrl("image2");
            imageDataArray.removeUrl("image2");

            Log.i("oldArrayNow", String.valueOf(imageDataArray.getImageArray()));
            Log.i("SizeOFHahmap", String.valueOf(remove.sizeOfHashMap()));
            Log.i("RemoveNew", String.valueOf(remove));

//            if (remove.sizeOfHashMap() == 2){
//                imageDataArray.addUrl("image0", remove.getUrl("image0"));
//                HashMap<String, String> newImages = new HashMap<>();
//                newImages.put("image0", imageDataArray.getUrl("image0"));
//                Log.i("NewImagesAt0", String.valueOf(newImages));
//                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
//                imageDataArray.updateImageArray(newImages);
//
//            }else if (remove.sizeOfHashMap() == 3){
//                imageDataArray.addUrl("image0", remove.getUrl("image0"));
//                imageDataArray.addUrl("image1", remove.getUrl("image1"));
//                HashMap<String, String> newImages = new HashMap<>();
//                newImages.put("image0", imageDataArray.getUrl("image0"));
//                newImages.put("image1", imageDataArray.getUrl("image1"));
//                Log.i("NewImagesAt1", String.valueOf(newImages));
//                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
//                imageDataArray.updateImageArray(newImages);
//
//            } else if (remove.sizeOfHashMap()==4){
//                imageDataArray.addUrl("image0", remove.getUrl("image0"));
//                imageDataArray.addUrl("image1", remove.getUrl("image1"));
//                imageDataArray.addUrl("image2",remove.getUrl("image2"));
//                HashMap<String, String> newImages = new HashMap<>();
//                newImages.put("image0", imageDataArray.getUrl("image0"));
//                newImages.put("image1", imageDataArray.getUrl("image1"));
//                newImages.put("image2", imageDataArray.getUrl("image2"));
//                Log.i("NewImagesAt2", String.valueOf(newImages));
//                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
//                imageDataArray.updateImageArray(newImages);
//
//            }
//            remove.removeUrl("image2");
//            imageDataArray.removeUrl("image2");


            Log.i("oldArrayNow", String.valueOf(imageDataArray.getImageArray()));
            Log.i("SizeOFHahmap",String.valueOf(remove.sizeOfHashMap()));
            Log.i("RemoveNew",String.valueOf(remove));

        }

    }


    public boolean whenImageIsThreeNew(Activity activity, ImageDataArray deleteArray) {
        Log.i("ImageArrayData", String.valueOf(imageDataArray));
        Log.i("NewImageArray", String.valueOf(imageDataArray));
        if (!imageDataArray.isArrayEmpty()){
            Log.i("imageArrayempty", "it's empty");
            if (imageDataArray.containKey("image1") && imageDataArray.containKey("image2") && imageDataArray.containKey("image0")) {
                Log.i("Key is present","Key is pressent");
                Alert.showFailed(activity, "you have uploaded three pictures! Proceed or delete any of the picture");
                return  true;
            }

        }else  if (!deleteArray.isArrayEmpty()){
            Log.i("imageArrayNotempty", "it's not empty");
            if (deleteArray.sizeOfHashMap() == 2){
                imageDataArray.addUrl("image0", deleteArray.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
                Log.i("IMAGE ARRAY OLD ", String.valueOf(imageDataArray.getImageArray()));
            }else if (deleteArray.sizeOfHashMap() == 3){
                imageDataArray.addUrl("image0", deleteArray.getUrl("image0"));
                imageDataArray.addUrl("image1", deleteArray.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
                Log.i("IMAGE ARRAY OLD ", String.valueOf(imageDataArray.getImageArray()));
            }else if (deleteArray.sizeOfHashMap() == 4){
                imageDataArray.addUrl("image0", deleteArray.getUrl("image0"));
                imageDataArray.addUrl("image1", deleteArray.getUrl("image1"));
                imageDataArray.addUrl("image2",deleteArray.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt2", String.valueOf(newImages));
                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
                Log.i("IMAGE ARRAY OLD ", String.valueOf(imageDataArray.getImageArray()));
            }
            if (imageDataArray.containKey("image1") && imageDataArray.containKey("image2") && imageDataArray.containKey("image0")) {
                Log.i("Key is present","Key is pressent");
                Alert.showFailed(activity, "you have uploaded three pictures! Proceed or delete any of the picture");
                return  true;
            }



        }

        return false;
    }


    public boolean whenImageIsThree(Activity activity) {
        Log.i("ImageArrayData", String.valueOf(imageDataArray));
        Log.i("NewImageArray", String.valueOf(imageDataArray));

            if (imageDataArray.containKey("image1") && imageDataArray.containKey("image2") && imageDataArray.containKey("image0")) {
                Log.i("Key is present", "Key is pressent");
                Alert.showFailed(activity, "you have uploaded three pictures! Proceed or delete any of the picture");
                return true;
            }else {
                return  false;
            }

    }


    public boolean areAllImagesNotUploaded(Activity activity, ImageDataArray dataArray) {
        //Check if local storage is empty,this method will take to params (type of object)
        //if local storage is empty show this methods that checks for hashmap key
        //if local storage is not empty get
        if (!dataArray.isArrayEmpty() && !imageDataArray.isArrayEmpty()) {
            Log.i("I'm Here","I'm Here");
            Log.i("ImageDataArrayNew",String.valueOf(imageDataArray));
            Log.i("DataArrayNew",String.valueOf(dataArray));
            if (dataArray.sizeOfHashMap() == 2 && imageDataArray.sizeOfHashMap()==1 && dataArray.getUrl("image0") != null){
                Log.i("AT0", "AT0");
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                imageDataArray.updateImageArray(newImages);

            }else if (dataArray.sizeOfHashMap() == 3 && imageDataArray.sizeOfHashMap()==2 && dataArray.getUrl("image0") != null && dataArray.getUrl("image1")!= null){
                Log.i("AT1", "AT1");
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                imageDataArray.updateImageArray(newImages);


            }else if (dataArray.sizeOfHashMap() == 4 && imageDataArray.sizeOfHashMap() == 3 && dataArray.getUrl("image0") != null && dataArray.getUrl("image1")!= null && dataArray.getUrl("image2")!=null){
                Log.i("AT2", "AT2");
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
                imageDataArray.addUrl("image2",dataArray.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                imageDataArray.updateImageArray(newImages);
            }

            Log.i("NEWEST", String.valueOf(imageDataArray));

        } else if (!dataArray.isArrayEmpty()) {
            if (dataArray.sizeOfHashMap() == 2){
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                Log.i("NewImagesAt0", String.valueOf(newImages));
                Log.i("ImageDataArray0", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }else if (dataArray.sizeOfHashMap() == 3){
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                Log.i("NewImagesAt1", String.valueOf(newImages));
                Log.i("ImageDataArray1", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
                Log.i("IMAGE ARRAY OLD ", String.valueOf(imageDataArray.getImageArray()));
            }else if (dataArray.sizeOfHashMap() == 4){
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
                imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
                imageDataArray.addUrl("image2",dataArray.getUrl("image2"));
                HashMap<String, String> newImages = new HashMap<>();
                newImages.put("image0", imageDataArray.getUrl("image0"));
                newImages.put("image1", imageDataArray.getUrl("image1"));
                newImages.put("image2", imageDataArray.getUrl("image2"));
                Log.i("NewImagesAt2", String.valueOf(newImages));
                Log.i("ImageDataArray2", String.valueOf(imageDataArray));
                imageDataArray.updateImageArray(newImages);
            }


        } else if (!imageDataArray.containKey("image1") || !imageDataArray.containKey("image2") || !imageDataArray.containKey("image0")) {
            Log.i("Entered her","Entered here");
            return false;

        }

            Log.i("GotHere2", "GOT HERE2");

            Log.i("NEWIMAGEARRAY", String.valueOf(imageDataArray.getImageArray()));
            return false;




    }





    public boolean areAllImagesNotUploadedNew(Activity activity, ImageDataArray dataArray) {
        //Check if local storage is empty,this method will take to params (type of object)
        //if local storage is empty show this methods that checks for hashmap key
        //if local storage is not empty get
        if (!dataArray.isArrayEmpty() && !imageDataArray.isArrayEmpty()) {
            if (!imageDataArray.containKey("image0")) {
                imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
            } else if (!imageDataArray.containKey("image1")) {
                imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
            } else if (!imageDataArray.containKey("image2")) {
                imageDataArray.addUrl("image2", dataArray.getUrl("image2"));
            }
            HashMap<String, String> newImages = new HashMap<>();
            newImages.put("image0", imageDataArray.getUrl("image0"));
            newImages.put("image1", imageDataArray.getUrl("image1"));
            newImages.put("image2", imageDataArray.getUrl("image2"));
            imageDataArray.updateImageArray(newImages);


        } else if (!dataArray.isArrayEmpty()) {

            imageDataArray.addUrl("image0", dataArray.getUrl("image0"));
            imageDataArray.addUrl("image1", dataArray.getUrl("image1"));
            imageDataArray.addUrl("image2", dataArray.getUrl("image2"));
            Log.i("IMAGE ARRAY OLD ", String.valueOf(imageDataArray.getImageArray()));

            HashMap<String, String> newImages = new HashMap<>();
            newImages.put("image0", imageDataArray.getUrl("image0"));
            newImages.put("image1", imageDataArray.getUrl("image1"));
            newImages.put("image2", imageDataArray.getUrl("image2"));
            imageDataArray.updateImageArray(newImages);

        } else if (!imageDataArray.containKey("image1") || !imageDataArray.containKey("image2") || !imageDataArray.containKey("image0")) {
            return false;

        }

        Log.i("NEWIMAGEARRAY", String.valueOf(imageDataArray.getImageArray()));

        return false;


    }

    public HashMap<String, String> getPictureArray() {
        return imageDataArray.getImageArray();
    }


}
