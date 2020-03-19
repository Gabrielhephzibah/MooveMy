package com.enyata.android.mvvm_java.data.model.api.myData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignatureImage {
    @Expose
    @SerializedName("signature")
    private String signatureImage;

    public SignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "signature='" + signatureImage + '\'' +
                '}';
    }
}
