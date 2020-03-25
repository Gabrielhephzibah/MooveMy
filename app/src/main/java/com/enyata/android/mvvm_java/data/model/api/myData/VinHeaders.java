package com.enyata.android.mvvm_java.data.model.api.myData;

public class VinHeaders {
    private  String accept;
    private  String accept_encoding;
    private  String accept_language;
    private String authorization;
    private  String partner_token;



    public VinHeaders(String accept,String accept_encoding, String accept_language, String authorization, String partner_token) {
        this.accept = accept;
        this.accept_encoding = accept_encoding;
        this.accept_language = accept_language;
        this.authorization = authorization;
        this.partner_token = partner_token;

    }


    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getPartner_token() {
        return partner_token;
    }

    public void setPartner_token(String partner_token) {
        this.partner_token = partner_token;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAccept_encoding() {
        return accept_encoding;
    }

    public void setAccept_encoding(String accept_encoding) {
        this.accept_encoding = accept_encoding;
    }

    public String getAccept_language() {
        return accept_language;
    }

    public void setAccept_language(String accept_language) {
        this.accept_language = accept_language;
    }
}
