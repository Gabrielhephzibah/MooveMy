package com.enyata.android.mvvm_java.data.model.api.myData;

import com.enyata.android.mvvm_java.ui.createReport.roadtest.IdlingFragment;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class ImageDataArray {


    @Expose
    @SerializedName("imageArray")
    private HashMap<String, String> imageArray;


    public ImageDataArray(HashMap<String, String> imageArray) {
        this.imageArray = imageArray;
    }

    public HashMap<String, String> getImageArray() {
        return this.imageArray;
    }

    public void setImageArray(HashMap<String, String> imageArray) {
        this.imageArray = imageArray;
    }

    @Override
    public String toString() {
        return "VehiclePart{" +
                "part='" + imageArray + '\'' +
                '}';
    }


    public boolean isArrayEmpty() {
        if (imageArray.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void addUrl(String key, String value) {
        imageArray.put(key, value);

    }

    public void removeUrl(String key) {
        imageArray.remove(key);

    }

    public String getUrl(String key) {
       return imageArray.get(key);
    }

    public  String getStatus(String key){
        return imageArray.get(key);
    }

    public void addStatus(String key, String value) {
        imageArray.put(key, value);

    }


    public boolean containKey(String url) {
        if (imageArray.containsKey(url)) {
            return true;
        } else {
            return false;
        }
    }

    public void updateImageArray(HashMap<String, String> imageArray){
        this.imageArray = imageArray;
    }


}
