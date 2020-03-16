package com.enyata.android.mvvm_java.data.model.api.myData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageData {
    @Expose
    @SerializedName("image")
    private String image;


    public ImageData(String image) {
        this.image = image;

    }


    public String getImage() {
        return image;
    }

    public void setImage0(String image) {
        this.image = image;
    }



    @Override
    public String toString() {
        return "VehiclePart{" +
                "part='" + image + '\'' +

                '}';
    }
}



