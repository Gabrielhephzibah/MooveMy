package com.enyata.android.mvvm_java.data.model.api.myData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signature {

    @Expose
    @SerializedName("image_url")
    private String[] imageUrl;

    public Signature(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }
}
