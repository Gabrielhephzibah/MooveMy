package com.enyata.android.mvvm_java.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.android.mvvm_java.data.local.prefs.PreferencesHelper;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.model.api.response.VinResponseData;
import com.enyata.android.mvvm_java.data.remote.ApiHeader;
import com.enyata.android.mvvm_java.data.remote.ApiHelper;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
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
    public void setInTakeVehicleReport(List<VehicleCollection> vehiclePart) {
        mPreferencesHelper.setInTakeVehicleReport(vehiclePart);
    }

    @Override
    public List<VehicleCollection> getInTakeVehicleReport() {
        return mPreferencesHelper.getInTakeVehicleReport();
    }

    @Override
    public void setIntakeFinalStatus(String status) {
        mPreferencesHelper.setIntakeFinalStatus(status);
    }

    @Override
    public String getIntakeFinalStatus() {
        return mPreferencesHelper.getIntakeFinalStatus();
    }

    @Override
    public void setIntakeFinalComment(String comment) {
        mPreferencesHelper.setIntakeFinalComment(comment);
    }

    @Override
    public String getIntakeFinalComment() {
        return mPreferencesHelper.getIntakeFinalComment();
    }

    @Override
    public void setReportType(String reportType) {
        mPreferencesHelper.setReportType(reportType);
    }

    @Override
    public String getReportType() {
        return mPreferencesHelper.getReportType();
    }

    @Override
    public void setVehicleId(String vehicleId) {
        mPreferencesHelper.setVehicleId(vehicleId);
    }

    @Override
    public String getVehicleId() {
        return mPreferencesHelper.getVehicleId();
    }

    @Override
    public void setRepairReport(List<VehiclePartRepair> partRepair) {
        mPreferencesHelper.setRepairReport(partRepair);
    }

    @Override
    public List<VehiclePartRepair> getRepairReport() {
        return mPreferencesHelper.getRepairReport();
    }

    @Override
    public SharedPreferences.Editor deleteIntakeVehicleReport() {
        return mPreferencesHelper.deleteIntakeVehicleReport();
    }



    @Override
    public Single<LoginResponse>loginInspector(LoginRequest.Request request) {
        return mApiHelper.loginInspector(request);
    }

    @Override
    public Single<CreateReportResponse> createIntakeReport(CreateReportRequest request) {
        return mApiHelper.createIntakeReport(request);
    }

    @Override
    public Flowable<InspectorListResponse> getInspectorHistory() {
        return mApiHelper.getInspectorHistory();
    }

    @Override
    public Flowable<InspectorDetailReport> getInspectorDetail(String id) {
        return mApiHelper.getInspectorDetail(id);
    }

    @Override
    public Flowable<VinResponseData> getVinData(String vinNo) {
        return mApiHelper.getVinData(vinNo);
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
    public void updateUserInfo(String accessToken, String firstname, LoggedInMode loggedInMode,String email
            ) {

        setAccessToken(accessToken);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(firstname);
        setCurrentUserEmail(email);

    }


}
