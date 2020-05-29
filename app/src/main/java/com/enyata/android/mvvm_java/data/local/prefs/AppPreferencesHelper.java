package com.enyata.android.mvvm_java.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceListData;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.di.PreferenceInfo;
import com.enyata.android.mvvm_java.ui.createReport.exterior.FendersFragment;
import com.enyata.android.mvvm_java.ui.createReport.glass.MirrorFragment;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
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

    private static final String PREF_KEY_INTAKE_VEHICLE_REPORT = "PREF_KEY_INTAKE_VEHICLE_REPORT";

    private static final String PREF_KEY_INTAKE_FINAL_STATUS = "PREF_KEY_INTAKE_FINAL_STATUS";

    private static final String PREF_KEY_INTAKE_FINAL_COMMENT = "PREF_KEY_INTAKE_FINAL_COMMENT";

    private static final  String PREF_KEY_REPORT_TYPE = "PREF_KEY_REPORT_TYPE";

    private  static  final String PREF_KEY_VEHICLE_ID = "PREF_KEY_VEHICLE_ID";

    private static  final String PREF_KEY_REPAIR_REPORT = "PREF_KEY_REPAIR_REPORT";

    private static  final  String PREF_KEY_HOOD_TRACKING_STATUS = "PREF_KEY_HOOD_TRACKING_STATUS";

    private static  final  String PREF_KEY_DOOR_TRACKING_STATUS = "PREF_KEY_DOOR_TRACKING_STATUS";

    private static  final  String PREF_KEY_FENDERS_TRACKING_STATUS = "PREF_KEY_FENDERS_TRACKING_STATUS";

    private static  final  String PREF_KEY_FRONT_BUMPER_TRACKING_STATUS = "PREF_KEY_FRONT_BUMPER_TRACKING_STATUS";

    private static  final  String PREF_KEY_FUEL_DOOR_TRACKING_STATUS = "PREF_KEY_FUEL_DOOR_TRACKING_STATUS";

    private static  final  String PREF_KEY_PAINT_TRACKING_STATUS = "PREF_KEY_PAINT_TRACKING_STATUS";

    private static  final  String PREF_KEY_REAR_BUMPER_TRACKING_STATUS = "PREF_KEY_REAR_BUMPER_TRACKING_STATUS";

    private static  final  String PREF_KEY_REAR_TRACKING_STATUS = "PREF_KEY_REAR_TRACKING_STATUS";

    private static  final  String PREF_KEY_ROOF_TRACKING_STATUS = "PREF_KEY_ROOF_TRACKING_STATUS";

    private static  final  String PREF_KEY_TRIM_TRACKING_STATUS = "PREF_KEY_TRIM_TRACKING_STATUS";

    private static  final  String PREF_KEY_TRUNK_TRACKING_STATUS = "PREF_KEY_TRUNK_TRACKING_STATUS";

    private static  final  String PREF_KEY_VEHICLE_INFO = "PREF_KEY_VEHICLE_INFO";

    private  static  final  String PREF_KEY_MIRROR_TRACKING = "PREF_MIRROR_TRACKING";

    private  static  final  String PREF_KEY_REAR_WINDOW_TRACKING = "PREF_KEY_REAR_WINDOW_TRACKING";

    private  static  final  String PREF_KEY_WINDOW_TRACKING = "PREF_KEY_WINDOW_TRACKING";

    private  static  final  String PREF_KEY_WIND_SHIELD_TRACKING = "PREF_KEY_WIND_SHIELD_TRACKING";

    private  static  final  String PREF_KEY_SPARE_TYRE_TRACKING = "PREF_KEY_SPARE_TYRE_TRACKING";

    private  static  final  String PREF_KEY_TIRES_TRACKING = "PREF_KEY_TIRES_TRACKING";

    private  static  final  String PREF_KEY_WHEEL_TRACKING = "PREF_KEY_WHEEL_TRACKING";

    private  static  final  String PREF_KEY_BRAKE_SYSTEM_TRACKING = "PREF_KEY_BRAKE_SYSTEM_TRACKING";

    private  static  final  String PREF_KEY_DRIVE_AXLE_TRACKING = "PREF_KEY_DRIVE_AXLE_TRACKING";

    private  static  final  String PREF_KEY_EXHAUST_TRACKING = "PREF_KEY_EXHAUST_TRACKING";

    private  static  final  String PREF_KEY_FRAME_TRACKING = "PREF_KEY_FRAME_TRACKING";

    private  static  final  String PREF_KEY_SUSPENSION_TRACKING = "PREF_KEY_SUSPENSION_TRACKING";

    private  static  final  String PREF_KEY_TRANSMISSION_TRACKING = "PREF_KEY_TRANSMISSION_TRACKING";

    private  static  final  String PREF_KEY_BATTERY_TRACKING = "PREF_KEY_BATTERY_TRACKING";

    private  static  final  String PREF_KEY_BELT_TRACKING = "PREF_KEY_BELT_TRACKING";

    private  static  final  String PREF_KEY_ENGINE_COMP_TRACKING = "PREF_KEY_ENGINE_COMP_TRACKING";

    private  static  final  String PREF_KEY_HOSES_TRACKING = "PREF_KEY_HOSES_TRACKING";

    private  static  final  String PREF_KEY_FLUID_TRACKING = "PREF_KEY_FLUID_TRACKING";

    private  static  final  String PREF_KEY_OIL_TRACKING = "PREF_KEY_OIL_TRACKING";

    private  static  final  String PREF_KEY_WIRING_TRACKING = "PREF_KEY_WIRING_TRACKING";

    private  static  final  String PREF_KEY_ACCELERATION_TRACKING = "PREF_KEY_ACCELERATION_TRACKING";

    private  static  final  String PREF_KEY_BRAKING_TRACKING = "PREF_KEY_BRAKING_TRACKING";

    private  static  final  String PREF_KEY_ENGINE_PERF_TRACKING = "PREF_KEY_ENGINE_PERF_TRACKING";

    private  static  final  String PREF_KEY_STARTING_TRACKING = "PREF_KEY_STARTING_TRACKING";

    private  static  final  String PREF_KEY_STEERING_TRACKING = "PREF_KEY_STEERING_TRACKING";

    private  static  final  String PREF_KEY_SUSPENSION_PERF_TRACKING = "PREF_KEY_SUSPENSION_PERF_TRACKING";

    private  static  final  String PREF_KEY_TRANSMISSION_SHIFT_TRACKING = "PREF_KEY_TRANSMISSION_SHIFT_TRACKING";

    private  static  final  String PREF_KEY_IDLING_TRACKING = "PREF_KEY_IDLING_TRACKING";

    private  static  final  String PREF_KEY_AUDIO_SYSTEM_TRACKING = "PREF_KEY_AUDIO_SYSTEM_TRACKING";

    private  static  final  String PREF_KEY_BRAKE_LIGHT_TRACKING = "PREF_KEY_BRAKE_LIGHT_TRACKING";

    private  static  final  String PREF_KEY_COMPUTER_TRACKING = "PREF_KEY_COMPUTER_TRACKING";

    private  static  final  String PREF_KEY_HEAD_LIGHT_TRACKING = "PREF_KEY_HEAD_LIGHT_TRACKING";

    private  static  final  String PREF_KEY_PARKING_LIGHT_TRACKING = "PREF_KEY_PARKING_LIGHT_TRACKING";

    private  static  final  String PREF_KEY_POWER_LOCK_TRACKING = "PREF_KEY_POWER_LOCK_TRACKING";

    private  static  final  String PREF_KEY_POWER_MIRROR_TRACKING = "PREF_KEY_POWER_MIRROR_TRACKING";

    private  static  final  String PREF_KEY_POWER_SEAT_TRACKING = "PREF_KEY_POWER_SEAT_TRACKING";

    private  static  final  String PREF_KEY_POWER_STEERING_TRACKING = "PREF_KEY_POWER_STEERING_TRACKING";

    private  static  final  String PREF_KEY_POWER_WINDOW_TRACKING = "PREF_KEY_POWER_WINDOW_TRACKING";

    private  static  final  String PREF_KEY_SIGNAL_LIGHT_TRACKING = "PREF_KEY_SIGNAL_LIGHT_TRACKING";

    private  static  final  String PREF_KEY_TAIL_LIGHT_TRACKING = "PREF_KEY_TAIL_LIGHT_TRACKING";

    private  static  final  String PREF_KEY_AIR_COND_TRACKING = "PREF_KEY_AIR_COND_TRACKING";

    private  static  final  String PREF_KEY_CARPET_TRACKING = "PREF_KEY_CARPET_TRACKING";

    private  static  final  String PREF_KEY_DASHBOARD_TRACKING = "PREF_KEY_DASHBOARD_TRACKING";

    private  static  final  String PREF_KEY_DASH_GUAGES_TRACKING = "PREF_KEY_DASH_GUAGES_TRACKING";

    private  static  final  String PREF_KEY_DEFROSTER_TRACKING = "PREF_KEY_DEFROSTER_TRACKING";

    private  static  final  String PREF_KEY_DOOR_PANEL_TRACKING = "PREF_KEY_DOOR_PANEL_TRACKING";

    private  static  final  String PREF_KEY_GLOVE_BOX_TRACKING = "PREF_KEY_GLOVE_BOX_TRACKING";

    private  static  final  String PREF_KEY_HEAD_LINER_TRACKING = "PREF_KEY_HEAD_LINER_TRACKING";

    private  static  final  String PREF_KEY_HEATER_TRACKING = "PREF_KEY_HEATER_TRACKING";

    private  static  final  String PREF_KEY_INTERIOR_TRIM_TRACKING = "PREF_KEY_INTERIOR_TRIM_TRACKING";

    private  static  final  String PREF_KEY_SEATS_TRACKING = "PREF_KEY_SEATS_TRACKING";

    private  static  final  String PREF_KEY_VANITY_MIRROR_TRACKING = "PREF_KEY_VANITY_MIRROR_TRACKING";

    private  static  final  String PREF_KEY_IMAGE_ARRAY__SAVED = "PREF_KEY_IMAGE_ARRAY_SAVED";

    private  static  final  String PREF_KEY_MOOVE_ID = "PREF_KEY_MOOVE_ID";

    private  static  final  String PREF_KEY_CAR_YEAR_MAINT = "PREF_KEY_CAR_YEAR_MAINT";

    private  static  final  String PREF_KEY_CAR_MODEL_MAINT = "PREF_KEY_CAR_MODEL_MAINT";

    private  static  final  String PREF_KEY_CAR_MAKE_MAINT = "PREF_KEY_CAR_MAKE_MAINT";

    private  static  final  String PREF_KEY_TIME_ON_STOP = "PREF_KEY_TIME_ON_STOP";

    private static  final  String PREF_KEY_MAINTENANCE_REPORT = "PREF_KEY_MAINTENANCE_REPORT";

    private static  final  String PREF_KEY_VEHICLE_ID_MAINT= "PREF_KEY_VEHICLE_ID_MAINT";

    private static  final  String PREF_KEY_VEHICLE_MONTHLY_REPORT= "PREF_KEY_VEHICLE_MONTHLY_REPORT";

    private static  final  String PREF_KEY_MONTHLY_FINAL_ASSESSMENT = "PREF_KEY_MONTHLY_FINAL_ASSESSMENT";

    private static  final  String PREF_KEY_MONTHLY_FINAL_COMMENT = "PREF_KEY_MONTHLY_FINAL_COMMENT";

    private static  final  String PREF_KEY_INTAKE_ACCEPTANCE_VALUE = "PREF_KEY_INTAKE_ACCEPTANCE_VALUE";

    private static  final  String PREF_KEY_MONTHLY_ACCEPTANCE_VALUE = "PREF_KEY_MONTHLY_ACCEPTANCE_VALUE";

    private static  final  String PREF_KEY_INITIAL_MILEAGE = "PREF_KEY_INITIAL_MILEAGE";



    private final SharedPreferences mPrefs;

    public SharedPreferences getmPrefs() {
        return mPrefs;
    }

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);

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
        mPrefs.edit().putString(PREF_KEY_INTAKE_VEHICLE_REPORT, json).apply();
    }

    @Override
    public List<VehicleCollection> getInTakeVehicleReport() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_INTAKE_VEHICLE_REPORT, null);
        Type type = new TypeToken<List<VehicleCollection>>() {
        }.getType();
        return gson.fromJson(json, type);

    }

    @Override
    public void setMonthlyVehicleReport(List<VehicleCollection> monthlyReport) {
        Gson gson = new Gson();
        String json = gson.toJson(monthlyReport);
        mPrefs.edit().putString(PREF_KEY_VEHICLE_MONTHLY_REPORT, json).apply();
    }

    @Override
    public List<VehicleCollection> getMonthlyVehicleReport() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_VEHICLE_MONTHLY_REPORT, null);
        Type type = new TypeToken<List<VehicleCollection>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void setIntakeFinalStatus(String status) {
        mPrefs.edit().putString(PREF_KEY_INTAKE_FINAL_STATUS,status).apply();
    }

    @Override
    public String getIntakeFinalStatus() {
        return mPrefs.getString(PREF_KEY_INTAKE_FINAL_STATUS,null);
    }

    @Override
    public void setIntakeFinalComment(String comment) {
        mPrefs.edit().putString(PREF_KEY_INTAKE_FINAL_COMMENT,comment).apply();

    }

    @Override
    public String getIntakeFinalComment() {
        return mPrefs.getString(PREF_KEY_INTAKE_FINAL_COMMENT,null);
    }

    @Override
    public void setReportType(String reportType) {
        mPrefs.edit().putString(PREF_KEY_REPORT_TYPE,reportType).apply();
    }

    @Override
    public String getReportType() {
        return mPrefs.getString(PREF_KEY_REPORT_TYPE,null);
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
    public void setHoodTrackingStatus(boolean hoodTrackingStatus) {
        mPrefs.edit().putBoolean(PREF_KEY_HOOD_TRACKING_STATUS,hoodTrackingStatus).apply();

    }

    @Override
    public boolean getHoodTrackingStatus() {
        return mPrefs.getBoolean(PREF_KEY_HOOD_TRACKING_STATUS,false);
    }

    @Override
    public void setDoorTrackingStatus(boolean doorTrackingStatus) {
        mPrefs.edit().putBoolean(PREF_KEY_DOOR_TRACKING_STATUS,doorTrackingStatus).apply();

    }

    @Override
    public boolean getDoorTrackingStatus() {
        return mPrefs.getBoolean(PREF_KEY_DOOR_TRACKING_STATUS,false);
    }

    @Override
    public void setFenderTracking(boolean fendersTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_FENDERS_TRACKING_STATUS,fendersTracking).apply();

    }

    @Override
    public boolean getFenderTraking() {
        return mPrefs.getBoolean(PREF_KEY_FENDERS_TRACKING_STATUS, false);
    }

    @Override
    public void setFrontBumperTracking(boolean frontBumperTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_FRONT_BUMPER_TRACKING_STATUS,frontBumperTracking).apply();

    }

    @Override
    public boolean getFrontBumberTracking() {
        return mPrefs.getBoolean(PREF_KEY_FRONT_BUMPER_TRACKING_STATUS,false);
    }

    @Override
    public void setFuelDoorTracking(boolean fuelDoorTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_FUEL_DOOR_TRACKING_STATUS,fuelDoorTracking).apply();

    }

    @Override
    public boolean getFuelDoorTracking() {
        return mPrefs.getBoolean(PREF_KEY_FUEL_DOOR_TRACKING_STATUS,false);
    }

    @Override
    public void setPaintTracking(boolean paintTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_PAINT_TRACKING_STATUS,paintTracking).apply();

    }

    @Override
    public boolean getPaintTracking() {
        return mPrefs.getBoolean(PREF_KEY_PAINT_TRACKING_STATUS,false);
    }

    @Override
    public void setRearBumperTracking(boolean rearBumperTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_REAR_BUMPER_TRACKING_STATUS,rearBumperTracking).apply();

    }

    @Override
    public boolean getRearBumperTracking() {
        return mPrefs.getBoolean(PREF_KEY_REAR_BUMPER_TRACKING_STATUS,false);
    }

    @Override
    public void setRearTracking(boolean rearTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_REAR_TRACKING_STATUS,rearTracking).apply();

    }

    @Override
    public boolean getRearTracking() {
        return mPrefs.getBoolean(PREF_KEY_REAR_TRACKING_STATUS,false);
    }

    @Override
    public void setRoofTracking(boolean roofTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_ROOF_TRACKING_STATUS,roofTracking).apply();

    }

    @Override
    public boolean getRoofTracking() {
        return mPrefs.getBoolean(PREF_KEY_ROOF_TRACKING_STATUS,false);
    }

    @Override
    public void setTrimTracking(boolean trimTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TRIM_TRACKING_STATUS,trimTracking).apply();

    }

    @Override
    public boolean getTrimTracking() {
        return mPrefs.getBoolean(PREF_KEY_TRIM_TRACKING_STATUS,false);
    }

    @Override
    public void setTrunkTracking(boolean trunkTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TRUNK_TRACKING_STATUS,trunkTracking).apply();

    }

    @Override
    public boolean getTrunkTracking() {
        return mPrefs.getBoolean(PREF_KEY_TRUNK_TRACKING_STATUS,false);
    }

    @Override
    public void setVehicleInfo(boolean allVehicleInfo) {
        mPrefs.edit().putBoolean(PREF_KEY_VEHICLE_INFO,allVehicleInfo).apply();
    }

    @Override
    public boolean getVehicleInfo() {
        return mPrefs.getBoolean(PREF_KEY_VEHICLE_INFO,false);
    }

    @Override
    public void deleteVehicleInfo(boolean deleteVehicleInfo) {
        mPrefs.edit().remove("PREF_KEY_VEHICLE_INFO").apply();

    }

    @Override
    public void setMirrorTracking(boolean mirrorTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_MIRROR_TRACKING, mirrorTracking).apply();
    }

    @Override
    public boolean getMirrorTracking() {
        return mPrefs.getBoolean(PREF_KEY_MIRROR_TRACKING,false);
    }

    @Override
    public void setRearWindowTracking(boolean rearWindowTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_REAR_WINDOW_TRACKING,rearWindowTracking).apply();

    }

    @Override
    public boolean getRearWindowTracking() {
        return mPrefs.getBoolean(PREF_KEY_REAR_WINDOW_TRACKING,false);
    }

    @Override
    public void setWindowTracking(boolean windowTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_WINDOW_TRACKING,windowTracking).apply();
    }

    @Override
    public boolean getWindowTracking() {
        return mPrefs.getBoolean(PREF_KEY_WINDOW_TRACKING,false);
    }

    @Override
    public void setWindshieldTracking(boolean windshieldTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_WIND_SHIELD_TRACKING,windshieldTracking).apply();

    }

    @Override
    public boolean getWindshieldTracking() {
        return mPrefs.getBoolean(PREF_KEY_WIND_SHIELD_TRACKING,false);
    }

    @Override
    public void setSpareTireTracking(boolean spareTireTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_SPARE_TYRE_TRACKING,spareTireTracking).apply();
    }

    @Override
    public boolean getSpareTireTraking() {
        return mPrefs.getBoolean(PREF_KEY_SPARE_TYRE_TRACKING,false);
    }

    @Override
    public void setTireTracking(boolean tireTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TIRES_TRACKING,tireTracking).apply();

    }

    @Override
    public boolean getTireTracking() {
        return mPrefs.getBoolean(PREF_KEY_TIRES_TRACKING,false);
    }


    @Override
    public void setWheelTracking(boolean wheelTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_WHEEL_TRACKING,wheelTracking).apply();

    }

    @Override
    public boolean getWheelTracking() {
        return mPrefs.getBoolean(PREF_KEY_WHEEL_TRACKING,false);
    }

    @Override
    public void setBrakeSystemTracking(boolean brakeSystemTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_BRAKE_SYSTEM_TRACKING,brakeSystemTracking).apply();
    }

    @Override
    public boolean getBrakeSystemTracking() {
        return mPrefs.getBoolean(PREF_KEY_BRAKE_SYSTEM_TRACKING,false);
    }

    @Override
    public void setDriveAxleTracking(boolean driveAxleTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_DRIVE_AXLE_TRACKING,driveAxleTracking).apply();

    }

    @Override
    public boolean getDriveAxleTracking() {
        return mPrefs.getBoolean(PREF_KEY_DRIVE_AXLE_TRACKING,false);
    }

    @Override
    public void setExhaustTracking(boolean exhaustTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_EXHAUST_TRACKING,exhaustTracking).apply();

    }

    @Override
    public boolean getExhaustTracking() {
        return mPrefs.getBoolean(PREF_KEY_EXHAUST_TRACKING,false);
    }

    @Override
    public void setFrameTracking(boolean frameTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_FRAME_TRACKING,frameTracking).apply();

    }

    @Override
    public boolean getFrameTracking() {
        return mPrefs.getBoolean(PREF_KEY_FRAME_TRACKING,false);
    }

    @Override
    public void setSuspensionTracking(boolean suspensionTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_SUSPENSION_TRACKING,suspensionTracking).apply();

    }

    @Override
    public boolean getSuspensionTracking() {
        return mPrefs.getBoolean(PREF_KEY_SUSPENSION_TRACKING,false);
    }

    @Override
    public void setTransmissionTracking(boolean transmissionTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TRANSMISSION_TRACKING, transmissionTracking).apply();

    }

    @Override
    public boolean getTransmissonTracking() {
        return mPrefs.getBoolean(PREF_KEY_TRANSMISSION_TRACKING, false);
    }

    @Override
    public void setBatteryTracking(boolean batteryTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_BATTERY_TRACKING,batteryTracking).apply();
    }

    @Override
    public boolean getBatteryTracking() {
        return mPrefs.getBoolean(PREF_KEY_BATTERY_TRACKING,false);
    }

    @Override
    public void setBeltTracking(boolean beltTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_BELT_TRACKING,beltTracking).apply();

    }

    @Override
    public boolean getBeltTracking() {
        return mPrefs.getBoolean(PREF_KEY_BELT_TRACKING,false);
    }

    @Override
    public void setEngineCompTracking(boolean engineCompTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_ENGINE_COMP_TRACKING,engineCompTracking).apply();

    }

    @Override
    public boolean getEngineCompTracking() {
        return mPrefs.getBoolean(PREF_KEY_ENGINE_COMP_TRACKING,false);
    }

    @Override
    public void setFluidTracking(boolean fluidTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_FLUID_TRACKING,fluidTracking).apply();

    }

    @Override
    public boolean getFluidTracking() {
        return mPrefs.getBoolean(PREF_KEY_FLUID_TRACKING,false);
    }

    @Override
    public void setHosesTracking(boolean hosesTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_HOSES_TRACKING,hosesTracking).apply();

    }

    @Override
    public boolean getHosesTracking() {
        return mPrefs.getBoolean(PREF_KEY_HOSES_TRACKING,false);
    }

    @Override
    public void setOilTracking(boolean oilTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_OIL_TRACKING,oilTracking).apply();

    }

    @Override
    public boolean getOilTracking() {
        return mPrefs.getBoolean(PREF_KEY_OIL_TRACKING,false);
    }

    @Override
    public void setWiringTracking(boolean wiringTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_WIRING_TRACKING,wiringTracking).apply();

    }

    @Override
    public boolean getWiringTracking() {
        return mPrefs.getBoolean(PREF_KEY_WIRING_TRACKING,false);
    }

    @Override
    public void setAccelerationTracking(boolean accelerationTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_ACCELERATION_TRACKING,accelerationTracking).apply();
    }

    @Override
    public boolean getAccelerationTracking() {
        return mPrefs.getBoolean(PREF_KEY_ACCELERATION_TRACKING,false);
    }

    @Override
    public void setBrakingTracking(boolean brakingTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_BRAKING_TRACKING,brakingTracking).apply();

    }

    @Override
    public boolean getBrakingTracking() {
        return mPrefs.getBoolean(PREF_KEY_BRAKING_TRACKING,false);
    }

    @Override
    public void setEnginePerfTracking(boolean enginePerfTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_ENGINE_PERF_TRACKING,enginePerfTracking).apply();
    }

    @Override
    public boolean getEnginePerfTracking() {
        return mPrefs.getBoolean(PREF_KEY_ENGINE_PERF_TRACKING,false);
    }


    @Override
    public void setIdlingTracking(boolean idlingTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_IDLING_TRACKING,idlingTracking).apply();

    }

    @Override
    public boolean getIdlingTracking() {
        return mPrefs.getBoolean(PREF_KEY_IDLING_TRACKING,false);
    }

    @Override
    public void setStartingTracking(boolean startingTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_STARTING_TRACKING,startingTracking).apply();

    }

    @Override
    public boolean getStartingTracking() {
        return mPrefs.getBoolean(PREF_KEY_STARTING_TRACKING,false);
    }

    @Override
    public void setSteeringTracking(boolean steeringTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_STEERING_TRACKING,steeringTracking).apply();

    }

    @Override
    public boolean getSteeringTracking() {
        return mPrefs.getBoolean(PREF_KEY_STEERING_TRACKING,false);
    }

    @Override
    public void setSuspensionPerfTracking(boolean suspensionPerfTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_SUSPENSION_PERF_TRACKING,suspensionPerfTracking).apply();

    }

    @Override
    public boolean getSuspensionPerfTracking() {
        return mPrefs.getBoolean(PREF_KEY_SUSPENSION_PERF_TRACKING,false);
    }

    @Override
    public void setTransmissionShiftTracking(boolean transmissionShiftTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TRANSMISSION_SHIFT_TRACKING,transmissionShiftTracking).apply();

    }

    @Override
    public boolean getTransmissionShiftTracking() {
        return mPrefs.getBoolean(PREF_KEY_TRANSMISSION_SHIFT_TRACKING,false);
    }

    @Override
    public void setAudioSystemTracking(boolean audioSystemTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_AUDIO_SYSTEM_TRACKING,audioSystemTracking).apply();
    }

    @Override
    public boolean getAudioSystemTracking() {
        return mPrefs.getBoolean(PREF_KEY_AUDIO_SYSTEM_TRACKING,false);
    }

    @Override
    public void setBrakeLightTracking(boolean brakeLightTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_BRAKE_LIGHT_TRACKING,brakeLightTracking).apply();

    }

    @Override
    public boolean getBrakeLightTracking() {
        return mPrefs.getBoolean(PREF_KEY_BRAKE_LIGHT_TRACKING,false);
    }

    @Override
    public void setComputerTracking(boolean computerTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_COMPUTER_TRACKING, computerTracking).apply();

    }

    @Override
    public boolean getComputerTracking() {
        return mPrefs.getBoolean(PREF_KEY_COMPUTER_TRACKING,false);
    }

    @Override
    public void setHeadlightTracking(boolean headlightTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_HEAD_LIGHT_TRACKING,headlightTracking).apply();

    }

    @Override
    public boolean getHeadLightTracking() {
        return mPrefs.getBoolean(PREF_KEY_HEAD_LIGHT_TRACKING,false);
    }

    @Override
    public void setParkingLightTracking(boolean parkingLightTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_PARKING_LIGHT_TRACKING,parkingLightTracking).apply();

    }

    @Override
    public boolean getParkingLightTracking() {
        return mPrefs.getBoolean(PREF_KEY_PARKING_LIGHT_TRACKING,false);
    }

    @Override
    public void setPowerLockTracking(boolean powerLockTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_POWER_LOCK_TRACKING,powerLockTracking).apply();

    }

    @Override
    public boolean getPowerLockTracking() {
        return mPrefs.getBoolean(PREF_KEY_POWER_LOCK_TRACKING,false);
    }

    @Override
    public void setPowerMirrorTracking(boolean powerMirrorTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_POWER_MIRROR_TRACKING,powerMirrorTracking).apply();

    }

    @Override
    public boolean getPowerMirrorTracking() {
        return mPrefs.getBoolean(PREF_KEY_POWER_MIRROR_TRACKING,false);
    }

    @Override
    public void setPowerSeatTracking(boolean powerSeatTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_POWER_SEAT_TRACKING,powerSeatTracking).apply();

    }

    @Override
    public boolean getPowerSeatTracking() {
        return mPrefs.getBoolean(PREF_KEY_POWER_SEAT_TRACKING,false);
    }

    @Override
    public void setPowerSteeringTracking(boolean powerSteeringTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_POWER_STEERING_TRACKING,powerSteeringTracking).apply();

    }

    @Override
    public boolean getPowerSteeringTracking() {
        return mPrefs.getBoolean(PREF_KEY_POWER_STEERING_TRACKING,false);
    }

    @Override
    public void setPowerWindowTracking(boolean powerWindowTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_POWER_WINDOW_TRACKING,powerWindowTracking).apply();

    }

    @Override
    public boolean getPowerWindowTracking() {
        return mPrefs.getBoolean(PREF_KEY_POWER_WINDOW_TRACKING,false);
    }

    @Override
    public void setSignalLightTracking(boolean signalLightTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_SIGNAL_LIGHT_TRACKING,signalLightTracking).apply();

    }

    @Override
    public boolean getSignalLightTracking() {
        return mPrefs.getBoolean(PREF_KEY_SIGNAL_LIGHT_TRACKING,false);
    }

    @Override
    public void setTailLightTracking(boolean tailLightTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_TAIL_LIGHT_TRACKING,tailLightTracking).apply();

    }

    @Override
    public boolean getTailLightTracking() {
        return mPrefs.getBoolean(PREF_KEY_TAIL_LIGHT_TRACKING,false);
    }

    @Override
    public void setAirCondTracking(boolean airCondTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_AIR_COND_TRACKING,airCondTracking).apply();
    }

    @Override
    public boolean getAircondTracking() {
        return mPrefs.getBoolean(PREF_KEY_AIR_COND_TRACKING,false);
    }

    @Override
    public void setCarpetTracking(boolean carpetTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_CARPET_TRACKING,carpetTracking).apply();

    }

    @Override
    public boolean getCarpetTrackin() {
        return mPrefs.getBoolean(PREF_KEY_CARPET_TRACKING,false);
    }

    @Override
    public void setDashboardTracking(boolean dashboardTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_DASHBOARD_TRACKING,dashboardTracking).apply();

    }

    @Override
    public boolean getDashboardTracking() {
        return mPrefs.getBoolean(PREF_KEY_DASHBOARD_TRACKING,false);
    }

    @Override
    public void setDashGuagesTracking(boolean dashGuagesTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_DASH_GUAGES_TRACKING,dashGuagesTracking).apply();

    }

    @Override
    public boolean getDashGuagesTracking() {
        return mPrefs.getBoolean(PREF_KEY_DASH_GUAGES_TRACKING,false);
    }

    @Override
    public void setDefrosterTracking(boolean defrosterTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_DEFROSTER_TRACKING,defrosterTracking).apply();

    }

    @Override
    public boolean getDefrosterTracking() {
        return mPrefs.getBoolean(PREF_KEY_DEFROSTER_TRACKING,false);
    }

    @Override
    public void setDoorPanelTracking(boolean doorPanelTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_DOOR_PANEL_TRACKING, doorPanelTracking).apply();

    }

    @Override
    public boolean getDoorPanelTracking() {
        return mPrefs.getBoolean(PREF_KEY_DOOR_PANEL_TRACKING,false);
    }

    @Override
    public void setGloveBoxTracking(boolean gloveBoxTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_GLOVE_BOX_TRACKING,gloveBoxTracking).apply();

    }

    @Override
    public boolean getGloveBoxTracking() {
        return mPrefs.getBoolean(PREF_KEY_GLOVE_BOX_TRACKING,false);
    }

    @Override
    public void setHeadLinerTracking(boolean headLinerTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_HEAD_LINER_TRACKING,headLinerTracking).apply();

    }

    @Override
    public boolean getHeadlinerTracking() {
        return mPrefs.getBoolean(PREF_KEY_HEAD_LINER_TRACKING,false);
    }

    @Override
    public void setHeaterTracking(boolean heaterTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_HEATER_TRACKING,heaterTracking).apply();

    }

    @Override
    public boolean getHeaterTracking() {
        return mPrefs.getBoolean(PREF_KEY_HEATER_TRACKING,false);
    }

    @Override
    public void setInteriorTrimTracking(boolean interiorTrimTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_INTERIOR_TRIM_TRACKING,interiorTrimTracking).apply();

    }

    @Override
    public boolean getInteriorTrimTracking() {
        return mPrefs.getBoolean(PREF_KEY_INTERIOR_TRIM_TRACKING,false);
    }

    @Override
    public void setSeatTracking(boolean seatTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_SEATS_TRACKING,seatTracking).apply();

    }

    @Override
    public boolean getSeatTracking() {
        return mPrefs.getBoolean(PREF_KEY_SEATS_TRACKING,false);
    }

    @Override
    public void setVanityMirrorTracking(boolean vanityMirrorTracking) {
        mPrefs.edit().putBoolean(PREF_KEY_VANITY_MIRROR_TRACKING,vanityMirrorTracking).apply();

    }

    @Override
    public boolean getVanityMirrorTracking() {
        return mPrefs.getBoolean(PREF_KEY_VANITY_MIRROR_TRACKING,false);
    }

    @Override
    public void setImageArraySaved(boolean imageArraySaved) {
        mPrefs.edit().putBoolean(PREF_KEY_IMAGE_ARRAY__SAVED,imageArraySaved).apply();
    }

    @Override
    public boolean getImageArraySaved() {
        return mPrefs.getBoolean(PREF_KEY_IMAGE_ARRAY__SAVED,false);
    }

    @Override
    public void deleteAudioSystemTracking(boolean audioSystemTracking) {
        mPrefs.edit().remove("PREF_KEY_AUDIO_SYSTEM_TRACKING").apply();

    }

    @Override
    public void deleteBrakeLightTracking(boolean brakelightTracking) {
        mPrefs.edit().remove("PREF_KEY_BRAKE_LIGHT_TRACKING").apply();

    }

    @Override
    public void deleteComputerTracking(boolean computerTracking) {
        mPrefs.edit().remove("PREF_KEY_COMPUTER_TRACKING").apply();

    }

    @Override
    public void deleteHeadlightTracking(boolean headLightTracking) {
        mPrefs.edit().remove("PREF_KEY_HEAD_LIGHT_TRACKING").apply();

    }

    @Override
    public void deleteParkingLightTracking(boolean parkingLightTracking) {
        mPrefs.edit().remove("PREF_KEY_PARKING_LIGHT_TRACKING").apply();

    }

    @Override
    public void deletePowerLockTracking(boolean powerLockTracking) {
        mPrefs.edit().remove("PREF_KEY_POWER_LOCK_TRACKING").apply();

    }

    @Override
    public void deletePowerMirrorTracking(boolean powerMirrorTracking) {
        mPrefs.edit().remove("PREF_KEY_POWER_MIRROR_TRACKING").apply();

    }

    @Override
    public void deletePowerSeatTracking(boolean powerSeatTracking) {
        mPrefs.edit().remove("PREF_KEY_POWER_SEAT_TRACKING").apply();

    }

    @Override
    public void deletePowerSteeringTracking(boolean powerSteeringTracking) {
        mPrefs.edit().remove("PREF_KEY_POWER_STEERING_TRACKING").apply();

    }

    @Override
    public void deletePowerWindowTracking(boolean powerWindowTracking) {
        mPrefs.edit().remove("PREF_KEY_POWER_WINDOW_TRACKING").apply();

    }

    @Override
    public void deleteSignalLightTracking(boolean signalLightTracking) {
        mPrefs.edit().remove("PREF_KEY_SIGNAL_LIGHT_TRACKING").apply();

    }

    @Override
    public void deleteTailLightTracking(boolean tailLightTracking) {
        mPrefs.edit().remove("PREF_KEY_TAIL_LIGHT_TRACKING").apply();

    }

    @Override
    public void deleteDoorTracking(boolean doorTracking) {
        mPrefs.edit().remove("PREF_KEY_DOOR_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteFenderTracking(boolean fenderTracking) {
        mPrefs.edit().remove("PREF_KEY_FENDERS_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteFrontBumperTracking(boolean frontBumperTracking) {
        mPrefs.edit().remove("PREF_KEY_FRONT_BUMPER_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteFuelDoorTracking(boolean fuelDoorTracking) {
        mPrefs.edit().remove("PREF_KEY_FUEL_DOOR_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteHoodTracking(boolean hoodTracking) {
        mPrefs.edit().remove("PREF_KEY_HOOD_TRACKING_STATUS").apply();

    }

    @Override
    public void deletePaintTracking(boolean paintTracking) {
        mPrefs.edit().remove("PREF_KEY_PAINT_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteRearBumperTracking(boolean rearBumperTracking) {
        mPrefs.edit().remove("PREF_KEY_REAR_BUMPER_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteRearTracking(boolean rearTracking) {
        mPrefs.edit().remove("PREF_KEY_REAR_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteRoofTracking(boolean roofTracking) {
        mPrefs.edit().remove("PREF_KEY_ROOF_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteTrimTracking(boolean trimTracking) {
        mPrefs.edit().remove("PREF_KEY_TRIM_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteTrunkTracking(boolean trunkTracking) {
        mPrefs.edit().remove("PREF_KEY_TRUNK_TRACKING_STATUS").apply();

    }

    @Override
    public void deleteMirrorTracking(boolean mirrorTracking) {
        mPrefs.edit().remove("PREF_MIRROR_TRACKING").apply();

    }

    @Override
    public void deleteRearWindowTracking(boolean rearWindowTracking) {
        mPrefs.edit().remove("PREF_KEY_REAR_WINDOW_TRACKING").apply();

    }

    @Override
    public void deleteWindowTracking(boolean windowTracking) {
        mPrefs.edit().remove("PREF_KEY_WINDOW_TRACKING").apply();

    }

    @Override
    public void deleteWindShieldTracking(boolean windShieldTracking) {
        mPrefs.edit().remove("PREF_KEY_WIND_SHIELD_TRACKING").apply();

    }

    @Override
    public void deleteAirCondTracking(boolean airCondTracking) {
        mPrefs.edit().remove("PREF_KEY_AIR_COND_TRACKING").apply();

    }

    @Override
    public void deleteCarpetTracking(boolean carpetTracking) {
        mPrefs.edit().remove("PREF_KEY_CARPET_TRACKING").apply();

    }

    @Override
    public void deleteDashBoardTracking(boolean dashboardTracking) {
        mPrefs.edit().remove("PREF_KEY_DASHBOARD_TRACKING").apply();

    }

    @Override
    public void deleteDashGuagesTracking(boolean dashGuageTracking) {
        mPrefs.edit().remove("PREF_KEY_DASH_GUAGES_TRACKING").apply();

    }

    @Override
    public void deleteDefrosterTracking(boolean defrosterTracking) {
        mPrefs.edit().remove("PREF_KEY_DEFROSTER_TRACKING").apply();

    }

    @Override
    public void deleteDoorPanelTracking(boolean doorPanelTracking) {
        mPrefs.edit().remove("PREF_KEY_DOOR_PANEL_TRACKING").apply();

    }

    @Override
    public void deleteGloveBoxTracking(boolean gloveBoxTracking) {
        mPrefs.edit().remove("PREF_KEY_GLOVE_BOX_TRACKING").apply();

    }

    @Override
    public void deleteHeadLinerTracking(boolean headLinerTracking) {
        mPrefs.edit().remove("PREF_KEY_HEAD_LINER_TRACKING").apply();

    }

    @Override
    public void deleteHeaterTracking(boolean heaterTracking) {
        mPrefs.edit().remove("PREF_KEY_HEATER_TRACKING").apply();

    }

    @Override
    public void deleteInteriorTrimTracking(boolean interiorTrimTracking) {
        mPrefs.edit().remove("PREF_KEY_INTERIOR_TRIM_TRACKING").apply();

    }

    @Override
    public void deleteSeatTracking(boolean seatTracking) {
        mPrefs.edit().remove("PREF_KEY_SEATS_TRACKING").apply();

    }

    @Override
    public void deleteVanityMirror(boolean vanityMirrorTracking) {
        mPrefs.edit().remove("PREF_KEY_VANITY_MIRROR_TRACKING").apply();

    }

    @Override
    public void deleteAccelerationTracking(boolean accelerationTracking) {
        mPrefs.edit().remove("PREF_KEY_ACCELERATION_TRACKING").apply();

    }

    @Override
    public void deleteBrakingTraking(boolean brakingTracking) {
        mPrefs.edit().remove("PREF_KEY_BRAKING_TRACKING").apply();

    }

    @Override
    public void deleteEnginePerfTracking(boolean enginePerfTracking) {
        mPrefs.edit().remove("PREF_KEY_ENGINE_PERF_TRACKING").apply();

    }

    @Override
    public void deleteIdlingTracking(boolean idlingTracking) {
        mPrefs.edit().remove("PREF_KEY_IDLING_TRACKING").apply();

    }

    @Override
    public void deleteStartingTracking(boolean startingTracking) {
        mPrefs.edit().remove("PREF_KEY_STARTING_TRACKING").apply();

    }

    @Override
    public void deleteSteeringTracking(boolean steeringTracking) {
        mPrefs.edit().remove("PREF_KEY_STEERING_TRACKING").apply();

    }

    @Override
    public void deleteSuspensionPerfTracking(boolean suspensionPerfTracking) {
        mPrefs.edit().remove("PREF_KEY_SUSPENSION_PERF_TRACKING").apply();

    }

    @Override
    public void deleteTransmissionShiftTracking(boolean transmissionShiftTracking) {
        mPrefs.edit().remove("PREF_KEY_TRANSMISSION_SHIFT_TRACKING").apply();

    }

    @Override
    public void deleteSpareTireTracking(boolean spareTireTracking) {
        mPrefs.edit().remove("PREF_KEY_SPARE_TYRE_TRACKING").apply();

    }

    @Override
    public void deleteTireTracking(boolean tireTracking) {
        mPrefs.edit().remove("PREF_KEY_TIRES_TRACKING").apply();

    }

    @Override
    public void deleteWheelTracking(boolean wheelTracking) {
        mPrefs.edit().remove("PREF_KEY_WHEEL_TRACKING").apply();

    }

    @Override
    public void deleteBrakeSystemTracking(boolean brakeSystemTracking) {
        mPrefs.edit().remove("PREF_KEY_BRAKE_SYSTEM_TRACKING").apply();

    }

    @Override
    public void deleteDriveAxleTracking(boolean driveAxleTracking) {
        mPrefs.edit().remove("PREF_KEY_DRIVE_AXLE_TRACKING").apply();

    }

    @Override
    public void deleteExhaustTracking(boolean exhaustTracking) {
        mPrefs.edit().remove("PREF_KEY_EXHAUST_TRACKING").apply();

    }

    @Override
    public void deleteFrameTracking(boolean frameTracking) {
        mPrefs.edit().remove("PREF_KEY_FRAME_TRACKING").apply();

    }

    @Override
    public void deleteSuspensionTracking(boolean suspensionTracking) {
        mPrefs.edit().remove("PREF_KEY_SUSPENSION_TRACKING").apply();

    }

    @Override
    public void deleteTransmissionTracking(boolean transmissionTracking) {
        mPrefs.edit().remove("PREF_KEY_TRANSMISSION_TRACKING").apply();

    }

    @Override
    public void deleteBatteryTracking(boolean batteryTracking) {
        mPrefs.edit().remove("PREF_KEY_BATTERY_TRACKING").apply();

    }

    @Override
    public void deleteBeltTracking(boolean beltTracking) {
        mPrefs.edit().remove("PREF_KEY_BELT_TRACKING").apply();

    }

    @Override
    public void deleteEngineCompTracking(boolean engineCompTracking) {
        mPrefs.edit().remove("PREF_KEY_ENGINE_COMP_TRACKING").apply();

    }

    @Override
    public void deleteFluidTracking(boolean fluidTracking) {
        mPrefs.edit().remove("PREF_KEY_FLUID_TRACKING").apply();

    }

    @Override
    public void deleteHosesTracking(boolean hosesTracking) {
        mPrefs.edit().remove("PREF_KEY_HOSES_TRACKING").apply();

    }

    @Override
    public void deleteOilTracking(boolean oilTracking) {
        mPrefs.edit().remove("PREF_KEY_OIL_TRACKING").apply();

    }

    @Override
    public void deleteWiringTracking(boolean wiringTracking) {
        mPrefs.edit().remove("PREF_KEY_WIRING_TRACKING").apply();

    }

    @Override
    public void setMooveId(String mooveId) {
        mPrefs.edit().putString(PREF_KEY_MOOVE_ID,mooveId).apply();
    }

    @Override
    public String getMooveId() {
        return mPrefs.getString(PREF_KEY_MOOVE_ID, null);
    }

    @Override
    public void setCarYearMaint(String carYearMaint) {
        mPrefs.edit().putString(PREF_KEY_CAR_YEAR_MAINT,carYearMaint).apply();

    }

    @Override
    public String getCarYearMaint() {
        return mPrefs.getString(PREF_KEY_CAR_YEAR_MAINT,null);
    }

    @Override
    public void setCarMakeMaint(String carMakeMaint) {
        mPrefs.edit().putString(PREF_KEY_CAR_MAKE_MAINT, carMakeMaint).apply();

    }

    @Override
    public String getCarMakeMaint() {
        return mPrefs.getString(PREF_KEY_CAR_MAKE_MAINT,null);
    }

    @Override
    public void setCarModelMaint(String carModelMaint) {
        mPrefs.edit().putString(PREF_KEY_CAR_MODEL_MAINT, carModelMaint).apply();

    }

    @Override
    public String getCarModelMaint() {
        return mPrefs.getString(PREF_KEY_CAR_MODEL_MAINT,null);
    }

    @Override
    public void setVehicleIdMaint(String vehicleIdMaint) {
        mPrefs.edit().putString(PREF_KEY_VEHICLE_ID_MAINT, vehicleIdMaint).apply();
    }

    @Override
    public String getVehicleIdMaint() {
        return mPrefs.getString(PREF_KEY_VEHICLE_ID_MAINT,null);
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

    @Override
    public void deleteRepairReport(List<VehiclePartRepair> repair) {
        mPrefs.edit().remove("PREF_KEY_REPAIR_REPORT").apply();
    }

    @Override
    public void deleteIntakeReport(List<VehicleCollection> vehicleCollections) {
        mPrefs.edit().remove("PREF_KEY_INTAKE_VEHICLE_REPORT").apply();

    }

    @Override
    public void deleteCarYear(String carYear) {
        mPrefs.edit().remove("PREF_KEY_CAR_YEAR").apply();

    }

    @Override
    public void deleteCarModel(String carModel) {
        mPrefs.edit().remove("PREF_KEY_CAR_MODEL").apply();

    }

    @Override
    public void deleteCarMake(String carMake) {
        mPrefs.edit().remove("PREF_KEY_CAR_MILEAGE").apply();

    }

    @Override
    public void deleteCurrentMileage(String CurrentMileage) {
        mPrefs.edit().remove("PREF_KEY_CURRENT_MILEAGE").apply();

    }

    @Override
    public void deleteRegistrationNo(String regNo) {
        mPrefs.edit().remove("PREF_KEY_REGISTRATION_NUMBER").apply();

    }

    @Override
    public void deleteVin(String vin) {
        mPrefs.edit().remove("PREF_KEY_VIN").apply();

    }

    @Override
    public void deleteCarColor(String carColor) {
        mPrefs.edit().remove("PREF_KEY_COLOUR").apply();

    }

    @Override
    public void deleteIntakeFinalStatus(String status) {
        mPrefs.edit().remove("PREF_KEY_INTAKE_FINAL_STATUS").apply();

    }

    @Override
    public void deleteIntakeFinalComment(String comment) {
        mPrefs.edit().remove("PREF_KEY_INTAKE_FINAL_COMMENT").apply();

    }

    @Override
    public void deleteReportType(String reportType) {
        mPrefs.edit().remove("PREF_KEY_REPORT_TYPE").apply();

    }

    @Override
    public void setTimeOnStop(long currentTimeOnStop) {
        mPrefs.edit().putLong(PREF_KEY_TIME_ON_STOP,currentTimeOnStop).apply();
    }

    @Override
    public long getTimeOnStop() {
        return mPrefs.getLong(PREF_KEY_TIME_ON_STOP,0);
    }

    @Override
    public void setMaintenanceReport(List<MaintenanceListData> maintenanceData) {
        Gson gson = new Gson();
        String json = gson.toJson(maintenanceData);
        mPrefs.edit().putString(PREF_KEY_MAINTENANCE_REPORT, json).apply();
    }

    @Override
    public List<MaintenanceListData> getMaintenanceReport() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_MAINTENANCE_REPORT, null);
        Type type = new TypeToken<List<MaintenanceListData>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void deleteMaintenanceReport(List<MaintenanceListData> maintenanceListData) {
        mPrefs.edit().remove("PREF_KEY_MAINTENANCE_REPORT").apply();

    }

    @Override
    public void deleteMonthlyReport(List<VehicleCollection> vehicleCollections) {
        mPrefs.edit().remove("PREF_KEY_VEHICLE_MONTHLY_REPORT").apply();
    }

    @Override
    public void setMonthlyFinalAssessment(String finalAssessment) {
        mPrefs.edit().putString(PREF_KEY_MONTHLY_FINAL_ASSESSMENT,finalAssessment).apply();
    }

    @Override
    public String getMonthlyFinalAssessment() {
        return mPrefs.getString(PREF_KEY_MONTHLY_FINAL_ASSESSMENT, null);
    }

    @Override
    public void setMonthlyFinalComment(String finalComment) {
        mPrefs.edit().putString(PREF_KEY_MONTHLY_FINAL_COMMENT, finalComment).apply();

    }

    @Override
    public String getMonthlyFinalComment() {
        return mPrefs.getString(PREF_KEY_MONTHLY_FINAL_COMMENT,null);
    }

    @Override
    public void setIntakeAcceptanceValue(String intakeAcceptanceValue) {
        mPrefs.edit().putString(PREF_KEY_INTAKE_ACCEPTANCE_VALUE,intakeAcceptanceValue).apply();
    }

    @Override
    public String getIntakeAcceptanceValue() {
        return mPrefs.getString(PREF_KEY_INTAKE_ACCEPTANCE_VALUE,null);
    }

    @Override
    public void setMonthlyAcceptanceValue(String monthlyAcceptanceValue) {
        mPrefs.edit().putString(PREF_KEY_MONTHLY_ACCEPTANCE_VALUE,monthlyAcceptanceValue).apply();

    }

    @Override
    public String getMonthlyAcceptanceValue() {
        return mPrefs.getString(PREF_KEY_MONTHLY_ACCEPTANCE_VALUE,null);
    }

    @Override
    public void deleteIntakeAcceptanceValue(String intakeAcceptance) {
        mPrefs.edit().remove("PREF_KEY_INTAKE_ACCEPTANCE_VALUE").apply();

    }

    @Override
    public void deleteMonthlyAcceptanceValue(String monthlyAcceptance) {
        mPrefs.edit().remove("PREF_KEY_MONTHLY_ACCEPTANCE_VALUE").apply();

    }

    @Override
    public void setInitialMileage(String initialMileage) {
        mPrefs.edit().putString(PREF_KEY_INITIAL_MILEAGE, initialMileage).apply();
    }

    @Override
    public String getInitialMileage() {
        return mPrefs.getString(PREF_KEY_INITIAL_MILEAGE, null);
    }


//    @Override
//    public void deleteArray(List<VehicleCollection> delete) {
//        mPrefs.edit().remove("INTAKE_VEHICLE_REPORT").apply();
//    }

    @Override
    public void deleteAll() {
        mPrefs.edit().clear().apply();
    }


}
