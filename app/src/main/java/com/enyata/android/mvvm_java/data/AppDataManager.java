package com.enyata.android.mvvm_java.data;

import android.content.Context;

import com.enyata.android.mvvm_java.data.local.db.dao.DbHelper;
import com.enyata.android.mvvm_java.data.local.prefs.PreferencesHelper;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePart;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.remote.ApiHeader;
import com.enyata.android.mvvm_java.data.remote.ApiHelper;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AppDataManager implements  DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(accessToken);


    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }



    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void setCarYear(String carYear) {
        mPreferencesHelper.setCarYear(carYear);
    }

    @Override
    public String getCarYear() {
        return mPreferencesHelper.getCarYear();
    }

    @Override
    public void setCarModel(String carModel) {
        mPreferencesHelper.setCarModel(carModel);


    }

    @Override
    public String getCarModel() {
        return mPreferencesHelper.getCarModel();
    }

    @Override
    public void setCarMake(String carMake) {
        mPreferencesHelper.setCarMake(carMake);

    }

    @Override
    public String getCarMake() {
        return mPreferencesHelper.getCarMake();
    }

    @Override
    public void setCurrentMileage(String currentMileage) {
        mPreferencesHelper.setCurrentMileage(currentMileage);

    }

    @Override
    public String getCurrentMilege() {
        return mPreferencesHelper.getCurrentMilege();
    }

    @Override
    public void setRegistrationNo(String registrationNo) {
        mPreferencesHelper.setRegistrationNo(registrationNo);

    }

    @Override
    public String getRegistrationNo() {
        return mPreferencesHelper.getRegistrationNo();
    }

    @Override
    public void setVin(String vin) {
        mPreferencesHelper.setVin(vin);

    }

    @Override
    public String getVin() {
        return mPreferencesHelper.getVin();
    }

    @Override
    public void setCarColor(String carColor) {
        mPreferencesHelper.setCarColor(carColor);

    }

    @Override
    public String getCarColor() {
        return mPreferencesHelper.getCarColor();
    }

    @Override
    public void setInTakeVehicleReport(List<VehicleCollection.Request> vehiclePart) {
        mPreferencesHelper.setInTakeVehicleReport(vehiclePart);
    }

    @Override
    public List<VehicleCollection.Request> getInTakeVehicleReport() {
        return mPreferencesHelper.getInTakeVehicleReport();
    }


    @Override
    public Single<LoginResponse>loginInspector(LoginRequest.Request request) {
        return mApiHelper.loginInspector(request);
    }


    @Override
    public void setUserAsLoggedOut() {
//        updateUserInfo(
////                null,
////                null,
////                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
////                null,
////                null,
////                null);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(accessToken);

    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String firstname,
            String email
            ) {

        setAccessToken(accessToken);
        setCurrentUserName(firstname);
        setCurrentUserEmail(email);

    }


}
