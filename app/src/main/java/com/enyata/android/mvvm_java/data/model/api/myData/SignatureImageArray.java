package com.enyata.android.mvvm_java.data.model.api.myData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class SignatureImageArray {
    @Expose
    @SerializedName("signatureArray")
    private HashMap<String,String> signatureImageArray;

    public SignatureImageArray(HashMap<String, String> image) {
        this.signatureImageArray = image;
    }

    public HashMap<String, String> getImageArray() {

        return signatureImageArray;
    }

    public void setImageArray(HashMap<String, String> image) {
        this.signatureImageArray = image;
    }

    public boolean isSignatureArrayEmpty() {
        if (signatureImageArray.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void addUrl(String key, String value) {
        signatureImageArray.put(key, value);

    }

    public boolean containKey(String url) {
        if (signatureImageArray.containsKey(url)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "signature{" +
                "part='" + signatureImageArray + '\'' +
                '}';
    }

}
