package com.enyata.android.mvvm_java.data.remote;

import com.androidnetworking.utils.ParseUtil;

public class RetrofitErrorHandler extends  Exception {
    private String errorBody;

    private int errorCode = 0;


    public RetrofitErrorHandler() {
    }


    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }

    public <T> T getErrorAsObject(Class<T> objectClass) {
        try {
            return (T) (ParseUtil
                    .getParserFactory()
                    .getObject(errorBody, objectClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
