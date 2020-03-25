package com.enyata.android.mvvm_java.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.di.PreferenceInfo;
import com.enyata.android.mvvm_java.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {


    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";

    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_CAR_YEAR = "PREF_KEY_CAR_YEAR";

    private static final String PREF_KEY_CAR_MODEL = "PREF_KEY_CAR_MODEL";

    private static final String PREF_KEY_CURRENT_MILEAGE = "PREF_KEY_CURRENT_MILEAGE";

    private static final String PREF_KEY_REGISTRATION_NUMBER = "PREF_KEY_REGISTRATION_NUMBER";

    private static final String PREF_KEY_VIN = "PREF_KEY_VIN";

    private static final String PREF_KEY_COLOUR = "PREF_KEY_COLOUR";

    private static final String PREF_KEY_CAR_MAKE = "PREF_KEY_CAR_MILEAGE";

    private static final String PREF_INTAKE_VEHICLE_REPORT = "INTAKE_VEHICLE_REPORT";

    private static final String PREF_INTAKE_FINAL_STATUS = "INTAKE_FINAL_STATUS";

    private static final String PREF_INTAKE_FINAL_COMMENT = "INTAKE_FINAL_COMMENT";

    private static final  String PREF_REPORT_TYPE = "PREF_KEY_REPORT_TYPE";

    private  static  final String PREF_KEY_VEHICLE_ID = "PREF_KEY_VEHICLE_ID";

    private static  final String PREF_KEY_REPAIR_REPORT = "PREF_KEY_REPAIR_REPORT";



    private final SharedPreferences mPrefs;

    public SharedPreferences getmPrefs() {
        return mPrefs;
    }

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);

    }

    @Override
    public SharedPreferences.Editor deleteIntakeVehicleReport() {
        return  mPrefs.edit().clear();
    }


//    public void deleteIntakeVehicleReport() {
//
//
//    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public void setCarYear(String carYear) {
        mPrefs.edit().putString(PREF_KEY_CAR_YEAR,carYear).apply();
    }

    @Override
    public String getCarYear() {
        return mPrefs.getString(PREF_KEY_CAR_YEAR,null);
    }

    @Override
    public void setCarModel(String carModel) {
        mPrefs.edit().putString(PREF_KEY_CAR_MODEL,carModel).apply();

    }

    @Override
    public String getCarModel() {
        return mPrefs.getString(PREF_KEY_CAR_MODEL,null);
    }

    @Override
    public void setCarMake(String carMake) {
        mPrefs.edit().putString(PREF_KEY_CAR_MAKE,carMake).apply();

    }

    @Override
    public String getCarMake() {
        return mPrefs.getString(PREF_KEY_CAR_MAKE,null);
    }

    @Override
    public void setCurrentMileage(String currentMileage) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_MILEAGE,currentMileage).apply();

    }

    @Override
    public String getCurrentMilege() {
        return mPrefs.getString(PREF_KEY_CURRENT_MILEAGE,null);
    }

    @Override
    public void setRegistrationNo(String registrationNo) {
        mPrefs.edit().putString(PREF_KEY_REGISTRATION_NUMBER,registrationNo).apply();

    }

    @Override
    public String getRegistrationNo() {
        return mPrefs.getString(PREF_KEY_REGISTRATION_NUMBER,null);
    }

    @Override
    public void setVin(String vin) {
        mPrefs.edit().putString(PREF_KEY_VIN,vin).apply();

    }

    @Override
    public String getVin() {
        return mPrefs.getString(PREF_KEY_VIN,null);
    }

    @Override
    public void setCarColor(String carColor) {
        mPrefs.edit().putString(PREF_KEY_COLOUR,carColor).apply();

    }

    @Override
    public String getCarColor() {
        return mPrefs.getString(PREF_KEY_COLOUR,null);
    }

    @Override
    public void setInTakeVehicleReport(List<VehicleCollection> vehiclePart) {
        Gson gson = new Gson();
        String json = gson.toJson(vehiclePart);
        mPrefs.edit().putString(PREF_INTAKE_VEHICLE_REPORT, json).apply();
    }

    @Override
    public List<VehicleCollection> getInTakeVehicleReport() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_INTAKE_VEHICLE_REPORT, null);
        Type type = new TypeToken<List<VehicleCollection>>() {
        }.getType();
        return gson.fromJson(json, type);

    }

    @Override
    public void setIntakeFinalStatus(String status) {
        mPrefs.edit().putString(PREF_INTAKE_FINAL_STATUS,status).apply();
    }

    @Override
    public String getIntakeFinalStatus() {
        return mPrefs.getString(PREF_INTAKE_FINAL_STATUS,null);
    }

    @Override
    public void setIntakeFinalComment(String comment) {
        mPrefs.edit().putString(PREF_INTAKE_FINAL_COMMENT,comment).apply();

    }

    @Override
    public String getIntakeFinalComment() {
        return mPrefs.getString(PREF_INTAKE_FINAL_COMMENT,null);
    }

    @Override
    public void setReportType(String reportType) {
        mPrefs.edit().putString(PREF_REPORT_TYPE,reportType).apply();
    }

    @Override
    public String getReportType() {
        return mPrefs.getString(PREF_REPORT_TYPE,null);
    }

    @Override
    public void setVehicleId(String vehicleId) {
        mPrefs.edit().putString(PREF_KEY_VEHICLE_ID,vehicleId).apply();
    }

    @Override
    public String getVehicleId() {
        return mPrefs.getString(PREF_KEY_VEHICLE_ID,null);
    }

    @Override
    public void setRepairReport(List<VehiclePartRepair> partRepair) {
        Gson gson = new Gson();
        String json = gson.toJson(partRepair);
        mPrefs.edit().putString(PREF_KEY_REPAIR_REPORT, json).apply();
    }

    @Override
    public List<VehiclePartRepair> getRepairReport() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_REPAIR_REPORT, null);
        Type type = new TypeToken<List<VehiclePartRepair>>() {
        }.getType();
        return gson.fromJson(json, type);

    }



}
