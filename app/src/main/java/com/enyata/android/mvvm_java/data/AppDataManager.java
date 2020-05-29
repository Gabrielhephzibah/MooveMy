package com.enyata.android.mvvm_java.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.android.mvvm_java.data.local.prefs.PreferencesHelper;
import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceListData;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CheckIntakeRequest;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.IntakeRuleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.LoginRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.RegNumberCheckRequest;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.data.model.api.response.CreateReportResponse;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorDetailReport;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.LoginResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleResponse;
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
    public void setMonthlyVehicleReport(List<VehicleCollection> monthlyReport) {
        mPreferencesHelper.setMonthlyVehicleReport(monthlyReport);
    }

    @Override
    public List<VehicleCollection> getMonthlyVehicleReport() {
        return mPreferencesHelper.getMonthlyVehicleReport();
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
    public void deleteRepairReport(List<VehiclePartRepair> repair) {
        mPreferencesHelper.deleteRepairReport(repair);
    }

    @Override
    public void deleteIntakeReport(List<VehicleCollection> delete) {
        mPreferencesHelper.deleteIntakeReport(delete);

    }

    @Override
    public void deleteCarYear(String carYear) {
        mPreferencesHelper.deleteCarYear(carYear);

    }

    @Override
    public void deleteCarModel(String carModel) {
        mPreferencesHelper.deleteCarModel(carModel);

    }

    @Override
    public void deleteCarMake(String carMake) {
        mPreferencesHelper.deleteCarMake(carMake);

    }

    @Override
    public void deleteCurrentMileage(String CurrentMileage) {
        mPreferencesHelper.deleteCurrentMileage(CurrentMileage);

    }

    @Override
    public void deleteRegistrationNo(String regNo) {
        mPreferencesHelper.deleteRegistrationNo(regNo);

    }

    @Override
    public void deleteVin(String vin) {
        mPreferencesHelper.deleteVin(vin);

    }

    @Override
    public void deleteCarColor(String carColor) {
        mPreferencesHelper.deleteCarColor(carColor);

    }

    @Override
    public void deleteIntakeFinalStatus(String status) {
        mPreferencesHelper.deleteIntakeFinalStatus(status);

    }

    @Override
    public void deleteIntakeFinalComment(String comment) {
        mPreferencesHelper.deleteIntakeFinalComment(comment);

    }

    @Override
    public void deleteReportType(String reportType) {
        mPreferencesHelper.deleteReportType(reportType);

    }

    @Override
    public void setHoodTrackingStatus(boolean hoodTrackingStatus) {
        mPreferencesHelper.setHoodTrackingStatus(hoodTrackingStatus);
    }

    @Override
    public boolean getHoodTrackingStatus() {
        return mPreferencesHelper.getHoodTrackingStatus();
    }

    @Override
    public void setDoorTrackingStatus(boolean doorTrackingStatus) {
        mPreferencesHelper.setDoorTrackingStatus(doorTrackingStatus);
    }

    @Override
    public boolean getDoorTrackingStatus() {
        return mPreferencesHelper.getDoorTrackingStatus();
    }

    @Override
    public void setFenderTracking(boolean fendersTracking) {
        mPreferencesHelper.setFenderTracking(fendersTracking);
    }

    @Override
    public boolean getFenderTraking() {
        return mPreferencesHelper.getFenderTraking();
    }

    @Override
    public void setFrontBumperTracking(boolean frontBumperTracking) {
        mPreferencesHelper.setFrontBumperTracking(frontBumperTracking);

    }

    @Override
    public boolean getFrontBumberTracking() {
        return mPreferencesHelper.getFrontBumberTracking();
    }

    @Override
    public void setFuelDoorTracking(boolean fuelDoorTracking) {
        mPreferencesHelper.setFuelDoorTracking(fuelDoorTracking);

    }

    @Override
    public boolean getFuelDoorTracking() {
        return mPreferencesHelper.getFuelDoorTracking();
    }

    @Override
    public void setPaintTracking(boolean paintTracking) {
        mPreferencesHelper.setPaintTracking(paintTracking);

    }

    @Override
    public boolean getPaintTracking() {
        return mPreferencesHelper.getPaintTracking();
    }

    @Override
    public void setRearBumperTracking(boolean rearBumperTracking) {
        mPreferencesHelper.setRearBumperTracking(rearBumperTracking);

    }

    @Override
    public boolean getRearBumperTracking() {
        return mPreferencesHelper.getRearBumperTracking();
    }

    @Override
    public void setRearTracking(boolean rearTracking) {
        mPreferencesHelper.setRearTracking(rearTracking);

    }

    @Override
    public boolean getRearTracking() {
        return mPreferencesHelper.getRearTracking();
    }

    @Override
    public void setRoofTracking(boolean roofTracking) {
        mPreferencesHelper.setRoofTracking(roofTracking);

    }

    @Override
    public boolean getRoofTracking() {
        return mPreferencesHelper.getRoofTracking();
    }

    @Override
    public void setTrimTracking(boolean trimTracking) {
        mPreferencesHelper.setTrimTracking(trimTracking);

    }

    @Override
    public boolean getTrimTracking() {
        return mPreferencesHelper.getTrimTracking();
    }

    @Override
    public void setTrunkTracking(boolean trunkTracking) {
        mPreferencesHelper.setTrunkTracking(trunkTracking);

    }

    @Override
    public boolean getTrunkTracking() {
        return mPreferencesHelper.getTrunkTracking();
    }

    @Override
    public void setVehicleInfo(boolean allVehicleInfo) {
        mPreferencesHelper.setVehicleInfo(allVehicleInfo);
    }

    @Override
    public boolean getVehicleInfo() {
        return mPreferencesHelper.getVehicleInfo();
    }

    @Override
    public void deleteVehicleInfo(boolean deleteVehicleInfo) {
        mPreferencesHelper.deleteVehicleInfo(deleteVehicleInfo);
    }

    @Override
    public void setMirrorTracking(boolean mirrorTracking) {
        mPreferencesHelper.setMirrorTracking(mirrorTracking);
    }

    @Override
    public boolean getMirrorTracking() {
        return mPreferencesHelper.getMirrorTracking();
    }

    @Override
    public void setRearWindowTracking(boolean rearWindowTracking) {
        mPreferencesHelper.setRearWindowTracking(rearWindowTracking);

    }

    @Override
    public boolean getRearWindowTracking() {
        return mPreferencesHelper.getRearWindowTracking();
    }

    @Override
    public void setWindowTracking(boolean windowTracking) {
        mPreferencesHelper.setWindowTracking(windowTracking);

    }

    @Override
    public boolean getWindowTracking() {
        return mPreferencesHelper.getWindowTracking();
    }

    @Override
    public void setWindshieldTracking(boolean windshieldTracking) {
        mPreferencesHelper.setWindshieldTracking(windshieldTracking);


    }

    @Override
    public boolean getWindshieldTracking() {
        return mPreferencesHelper.getWindshieldTracking();
    }

    @Override
    public void setSpareTireTracking(boolean spareTireTracking) {
        mPreferencesHelper.setSpareTireTracking(spareTireTracking);
    }

    @Override
    public boolean getSpareTireTraking() {
        return mPreferencesHelper.getSpareTireTraking();
    }

    @Override
    public void setTireTracking(boolean tireTracking) {
        mPreferencesHelper.setTireTracking(tireTracking);

    }

    @Override
    public boolean getTireTracking() {
        return mPreferencesHelper.getTireTracking();
    }


    @Override
    public void setWheelTracking(boolean wheelTracking) {
        mPreferencesHelper.setWheelTracking(wheelTracking);

    }

    @Override
    public boolean getWheelTracking() {
        return mPreferencesHelper.getWheelTracking();
    }

    @Override
    public void setBrakeSystemTracking(boolean brakeSystemTracking) {
        mPreferencesHelper.setBrakeSystemTracking(brakeSystemTracking);
    }

    @Override
    public boolean getBrakeSystemTracking() {
        return mPreferencesHelper.getBrakeSystemTracking();
    }

    @Override
    public void setDriveAxleTracking(boolean driveAxleTracking) {
        mPreferencesHelper.setDriveAxleTracking(driveAxleTracking);

    }

    @Override
    public boolean getDriveAxleTracking() {
        return mPreferencesHelper.getDriveAxleTracking();
    }

    @Override
    public void setExhaustTracking(boolean exhaustTracking) {
        mPreferencesHelper.setExhaustTracking(exhaustTracking);

    }

    @Override
    public boolean getExhaustTracking() {
        return mPreferencesHelper.getExhaustTracking();
    }

    @Override
    public void setFrameTracking(boolean frameTracking) {
        mPreferencesHelper.setFrameTracking(frameTracking);

    }

    @Override
    public boolean getFrameTracking() {
        return mPreferencesHelper.getFrameTracking();
    }

    @Override
    public void setSuspensionTracking(boolean suspensionTracking) {
        mPreferencesHelper.setSuspensionTracking(suspensionTracking);

    }

    @Override
    public boolean getSuspensionTracking() {
        return mPreferencesHelper.getSuspensionTracking();
    }

    @Override
    public void setTransmissionTracking(boolean transmissionTracking) {
        mPreferencesHelper.setTransmissionTracking(transmissionTracking);

    }

    @Override
    public boolean getTransmissonTracking() {
        return mPreferencesHelper.getTransmissonTracking();
    }

    @Override
    public void setBatteryTracking(boolean batteryTracking) {
        mPreferencesHelper.setBatteryTracking(batteryTracking);

    }

    @Override
    public boolean getBatteryTracking() {
        return mPreferencesHelper.getBatteryTracking();
    }

    @Override
    public void setBeltTracking(boolean beltTracking) {
        mPreferencesHelper.setBeltTracking(beltTracking);

    }

    @Override
    public boolean getBeltTracking() {
        return mPreferencesHelper.getBeltTracking();
    }

    @Override
    public void setEngineCompTracking(boolean engineCompTracking) {
        mPreferencesHelper.setEngineCompTracking(engineCompTracking);

    }

    @Override
    public boolean getEngineCompTracking() {
        return mPreferencesHelper.getEngineCompTracking();
    }

    @Override
    public void setFluidTracking(boolean fluidTracking) {
        mPreferencesHelper.setFluidTracking(fluidTracking);

    }

    @Override
    public boolean getFluidTracking() {
        return mPreferencesHelper.getFluidTracking();
    }

    @Override
    public void setHosesTracking(boolean hosesTracking) {
        mPreferencesHelper.setHosesTracking(hosesTracking);

    }

    @Override
    public boolean getHosesTracking() {
        return mPreferencesHelper.getHosesTracking();
    }

    @Override
    public void setOilTracking(boolean oilTracking) {
        mPreferencesHelper.setOilTracking(oilTracking);

    }

    @Override
    public boolean getOilTracking() {
        return mPreferencesHelper.getOilTracking();
    }

    @Override
    public void setWiringTracking(boolean wiringTracking) {
        mPreferencesHelper.setWiringTracking(wiringTracking);

    }

    @Override
    public boolean getWiringTracking() {
        return mPreferencesHelper.getWiringTracking();
    }

    @Override
    public void setAccelerationTracking(boolean accelerationTracking) {
        mPreferencesHelper.setAccelerationTracking(accelerationTracking);
    }

    @Override
    public boolean getAccelerationTracking() {
        return mPreferencesHelper.getAccelerationTracking();
    }

    @Override
    public void setBrakingTracking(boolean brakingTracking) {
        mPreferencesHelper.setBrakingTracking(brakingTracking);

    }

    @Override
    public boolean getBrakingTracking() {
        return mPreferencesHelper.getBrakingTracking();
    }

    @Override
    public void setEnginePerfTracking(boolean enginePerfTracking) {
        mPreferencesHelper.setEnginePerfTracking(enginePerfTracking);
    }

    @Override
    public boolean getEnginePerfTracking() {
        return mPreferencesHelper.getEnginePerfTracking();
    }


    @Override
    public void setIdlingTracking(boolean idlingTracking) {
        mPreferencesHelper.setIdlingTracking(idlingTracking);

    }

    @Override
    public boolean getIdlingTracking() {
        return mPreferencesHelper.getIdlingTracking();
    }

    @Override
    public void setStartingTracking(boolean startingTracking) {
        mPreferencesHelper.setStartingTracking(startingTracking);

    }

    @Override
    public boolean getStartingTracking() {
        return mPreferencesHelper.getStartingTracking();
    }

    @Override
    public void setSteeringTracking(boolean steeringTracking) {
        mPreferencesHelper.setSteeringTracking(steeringTracking);

    }

    @Override
    public boolean getSteeringTracking() {
        return mPreferencesHelper.getSteeringTracking();
    }

    @Override
    public void setSuspensionPerfTracking(boolean suspensionPerfTracking) {
        mPreferencesHelper.setSuspensionPerfTracking(suspensionPerfTracking);

    }

    @Override
    public boolean getSuspensionPerfTracking() {
        return mPreferencesHelper.getSuspensionPerfTracking();
    }

    @Override
    public void setTransmissionShiftTracking(boolean transmissionShiftTracking) {
        mPreferencesHelper.setTransmissionShiftTracking(transmissionShiftTracking);

    }

    @Override
    public boolean getTransmissionShiftTracking() {
        return mPreferencesHelper.getTransmissionShiftTracking();
    }

    @Override
    public void setAudioSystemTracking(boolean audioSystemTracking) {
        mPreferencesHelper.setAudioSystemTracking(audioSystemTracking);
    }

    @Override
    public boolean getAudioSystemTracking() {
        return mPreferencesHelper.getAudioSystemTracking();
    }

    @Override
    public void setBrakeLightTracking(boolean brakeLightTracking) {
        mPreferencesHelper.setBrakeLightTracking(brakeLightTracking);

    }

    @Override
    public boolean getBrakeLightTracking() {
        return mPreferencesHelper.getBrakeLightTracking();
    }

    @Override
    public void setComputerTracking(boolean computerTracking) {
        mPreferencesHelper.setComputerTracking(computerTracking);

    }

    @Override
    public boolean getComputerTracking() {
        return mPreferencesHelper.getComputerTracking();
    }

    @Override
    public void setHeadlightTracking(boolean headlightTracking) {
        mPreferencesHelper.setHeadlightTracking(headlightTracking);

    }

    @Override
    public boolean getHeadLightTracking() {
        return mPreferencesHelper.getHeadLightTracking();
    }

    @Override
    public void setParkingLightTracking(boolean parkingLightTracking) {
        mPreferencesHelper.setParkingLightTracking(parkingLightTracking);

    }

    @Override
    public boolean getParkingLightTracking() {
        return mPreferencesHelper.getParkingLightTracking();
    }

    @Override
    public void setPowerLockTracking(boolean powerLockTracking) {
        mPreferencesHelper.setPowerLockTracking(powerLockTracking);

    }

    @Override
    public boolean getPowerLockTracking() {
        return mPreferencesHelper.getPowerLockTracking();
    }

    @Override
    public void setPowerMirrorTracking(boolean powerMirrorTracking) {
        mPreferencesHelper.setPowerMirrorTracking(powerMirrorTracking);

    }

    @Override
    public boolean getPowerMirrorTracking() {
        return mPreferencesHelper.getPowerMirrorTracking();
    }

    @Override
    public void setPowerSeatTracking(boolean powerSeatTracking) {
        mPreferencesHelper.setPowerSeatTracking(powerSeatTracking);

    }

    @Override
    public boolean getPowerSeatTracking() {
        return mPreferencesHelper.getPowerSeatTracking();
    }

    @Override
    public void setPowerSteeringTracking(boolean powerSteeringTracking) {
        mPreferencesHelper.setPowerSteeringTracking(powerSteeringTracking);

    }

    @Override
    public boolean getPowerSteeringTracking() {
        return mPreferencesHelper.getPowerSteeringTracking();
    }

    @Override
    public void setPowerWindowTracking(boolean powerWindowTracking) {
        mPreferencesHelper.setPowerWindowTracking(powerWindowTracking);

    }

    @Override
    public boolean getPowerWindowTracking() {
        return mPreferencesHelper.getPowerWindowTracking();
    }

    @Override
    public void setSignalLightTracking(boolean signalLightTracking) {
        mPreferencesHelper.setSignalLightTracking(signalLightTracking);

    }

    @Override
    public boolean getSignalLightTracking() {
        return mPreferencesHelper.getSignalLightTracking();
    }

    @Override
    public void setTailLightTracking(boolean tailLightTracking) {
        mPreferencesHelper.setTailLightTracking(tailLightTracking);

    }

    @Override
    public boolean getTailLightTracking() {
        return mPreferencesHelper.getTailLightTracking();
    }

    @Override
    public void setAirCondTracking(boolean airCondTracking) {
        mPreferencesHelper.setAirCondTracking(airCondTracking);
    }

    @Override
    public boolean getAircondTracking() {
        return mPreferencesHelper.getAircondTracking();
    }

    @Override
    public void setCarpetTracking(boolean carpetTracking) {
        mPreferencesHelper.setCarpetTracking(carpetTracking);

    }

    @Override
    public boolean getCarpetTrackin() {
        return mPreferencesHelper.getCarpetTrackin();
    }

    @Override
    public void setDashboardTracking(boolean dashboardTracking) {
        mPreferencesHelper.setDashboardTracking(dashboardTracking);

    }

    @Override
    public boolean getDashboardTracking() {
        return mPreferencesHelper.getDashboardTracking();
    }

    @Override
    public void setDashGuagesTracking(boolean dashGuagesTracking) {
        mPreferencesHelper.setDashGuagesTracking(dashGuagesTracking);

    }

    @Override
    public boolean getDashGuagesTracking() {
        return mPreferencesHelper.getDashGuagesTracking();
    }

    @Override
    public void setDefrosterTracking(boolean defrosterTracking) {
        mPreferencesHelper.setDefrosterTracking(defrosterTracking);

    }

    @Override
    public boolean getDefrosterTracking() {
        return mPreferencesHelper.getDefrosterTracking();
    }

    @Override
    public void setDoorPanelTracking(boolean doorPanelTracking) {
        mPreferencesHelper.setDoorPanelTracking(doorPanelTracking);

    }

    @Override
    public boolean getDoorPanelTracking() {
        return mPreferencesHelper.getDoorPanelTracking();
    }

    @Override
    public void setGloveBoxTracking(boolean gloveBoxTracking) {
        mPreferencesHelper.setGloveBoxTracking(gloveBoxTracking);

    }

    @Override
    public boolean getGloveBoxTracking() {
        return mPreferencesHelper.getGloveBoxTracking();
    }

    @Override
    public void setHeadLinerTracking(boolean headLinerTracking) {
        mPreferencesHelper.setHeadLinerTracking(headLinerTracking);

    }

    @Override
    public boolean getHeadlinerTracking() {
        return mPreferencesHelper.getHeadlinerTracking();
    }

    @Override
    public void setHeaterTracking(boolean heaterTracking) {
        mPreferencesHelper.setHeaterTracking(heaterTracking);

    }

    @Override
    public boolean getHeaterTracking() {
        return mPreferencesHelper.getHeaterTracking();
    }

    @Override
    public void setInteriorTrimTracking(boolean interiorTrimTracking) {
        mPreferencesHelper.setInteriorTrimTracking(interiorTrimTracking);

    }

    @Override
    public boolean getInteriorTrimTracking() {
        return mPreferencesHelper.getInteriorTrimTracking();
    }

    @Override
    public void setSeatTracking(boolean seatTracking) {
        mPreferencesHelper.setSeatTracking(seatTracking);

    }

    @Override
    public boolean getSeatTracking() {
        return mPreferencesHelper.getSeatTracking();
    }

    @Override
    public void setVanityMirrorTracking(boolean vanityMirrorTracking) {
        mPreferencesHelper.setVanityMirrorTracking(vanityMirrorTracking);

    }

    @Override
    public boolean getVanityMirrorTracking() {
        return mPreferencesHelper.getVanityMirrorTracking();
    }

    @Override
    public void setImageArraySaved(boolean imageArraySaved) {
        mPreferencesHelper.setImageArraySaved(imageArraySaved);
    }

    @Override
    public boolean getImageArraySaved() {
        return mPreferencesHelper.getImageArraySaved();
    }

    @Override
    public void deleteAudioSystemTracking(boolean audioSystemTracking) {
        mPreferencesHelper.deleteAudioSystemTracking(audioSystemTracking);
    }

    @Override
    public void deleteBrakeLightTracking(boolean brakelightTracking) {
        mPreferencesHelper.deleteBrakeLightTracking(brakelightTracking);

    }

    @Override
    public void deleteComputerTracking(boolean computerTracking) {
        mPreferencesHelper.deleteComputerTracking(computerTracking);

    }

    @Override
    public void deleteHeadlightTracking(boolean headLightTracking) {
        mPreferencesHelper.deleteHeadlightTracking(headLightTracking);

    }

    @Override
    public void deleteParkingLightTracking(boolean parkingLightTracking) {
        mPreferencesHelper.deleteParkingLightTracking(parkingLightTracking);

    }

    @Override
    public void deletePowerLockTracking(boolean powerLockTracking) {
        mPreferencesHelper.deletePowerLockTracking(powerLockTracking);

    }

    @Override
    public void deletePowerMirrorTracking(boolean powerMirrorTracking) {
        mPreferencesHelper.deletePowerMirrorTracking(powerMirrorTracking);

    }

    @Override
    public void deletePowerSeatTracking(boolean powerSeatTracking) {
        mPreferencesHelper.deletePowerSeatTracking(powerSeatTracking);

    }

    @Override
    public void deletePowerSteeringTracking(boolean powerSteeringTracking) {
        mPreferencesHelper.deletePowerSteeringTracking(powerSteeringTracking);

    }

    @Override
    public void deletePowerWindowTracking(boolean powerWindowTracking) {
        mPreferencesHelper.deletePowerWindowTracking(powerWindowTracking);

    }

    @Override
    public void deleteSignalLightTracking(boolean signalLightTracking) {
        mPreferencesHelper.deleteSignalLightTracking(signalLightTracking);

    }

    @Override
    public void deleteTailLightTracking(boolean brakeLightTracking) {
        mPreferencesHelper.deleteTailLightTracking(brakeLightTracking);

    }

    @Override
    public void deleteDoorTracking(boolean doorTracking) {
        mPreferencesHelper.deleteDoorTracking(doorTracking);

    }

    @Override
    public void deleteFenderTracking(boolean fenderTracking) {
        mPreferencesHelper.deleteFenderTracking(fenderTracking);

    }

    @Override
    public void deleteFrontBumperTracking(boolean frontBumperTracking) {
        mPreferencesHelper.deleteFrontBumperTracking(frontBumperTracking);

    }

    @Override
    public void deleteFuelDoorTracking(boolean fuelDoorTracking) {
        mPreferencesHelper.deleteFuelDoorTracking(fuelDoorTracking);

    }

    @Override
    public void deleteHoodTracking(boolean hoodTracking) {
        mPreferencesHelper.deleteHoodTracking(hoodTracking);

    }

    @Override
    public void deletePaintTracking(boolean paintTracking) {
        mPreferencesHelper.deletePaintTracking(paintTracking);

    }

    @Override
    public void deleteRearBumperTracking(boolean rearBumperTracking) {
        mPreferencesHelper.deleteRearBumperTracking(rearBumperTracking);

    }

    @Override
    public void deleteRearTracking(boolean rearTracking) {
        mPreferencesHelper.deleteRearTracking(rearTracking);

    }

    @Override
    public void deleteRoofTracking(boolean roofTracking) {
        mPreferencesHelper.deleteRoofTracking(roofTracking);

    }

    @Override
    public void deleteTrimTracking(boolean trimTracking) {
        mPreferencesHelper.deleteTrimTracking(trimTracking);

    }

    @Override
    public void deleteTrunkTracking(boolean trunkTracking) {
        mPreferencesHelper.deleteTrunkTracking(trunkTracking);

    }

    @Override
    public void deleteMirrorTracking(boolean mirrorTracking) {
        mPreferencesHelper.deleteMirrorTracking(mirrorTracking);

    }

    @Override
    public void deleteRearWindowTracking(boolean rearWindowTracking) {
        mPreferencesHelper.deleteRearWindowTracking(rearWindowTracking);

    }

    @Override
    public void deleteWindowTracking(boolean windowTracking) {
        mPreferencesHelper.deleteWindowTracking(windowTracking);

    }

    @Override
    public void deleteWindShieldTracking(boolean windShieldTracking) {
        mPreferencesHelper.deleteWindShieldTracking(windShieldTracking);

    }

    @Override
    public void deleteAirCondTracking(boolean airCondTracking) {
        mPreferencesHelper.deleteAirCondTracking(airCondTracking);

    }

    @Override
    public void deleteCarpetTracking(boolean carpetTracking) {
        mPreferencesHelper.deleteCarpetTracking(carpetTracking);

    }

    @Override
    public void deleteDashBoardTracking(boolean dashboardTracking) {
        mPreferencesHelper.deleteDashBoardTracking(dashboardTracking);

    }

    @Override
    public void deleteDashGuagesTracking(boolean dashGuageTracking) {
        mPreferencesHelper.deleteDashGuagesTracking(dashGuageTracking);

    }

    @Override
    public void deleteDefrosterTracking(boolean defrosterTracking) {
        mPreferencesHelper.deleteDefrosterTracking(defrosterTracking);

    }

    @Override
    public void deleteDoorPanelTracking(boolean doorPanelTracking) {
        mPreferencesHelper.deleteDoorPanelTracking(doorPanelTracking);

    }

    @Override
    public void deleteGloveBoxTracking(boolean gloveBoxTracking) {
        mPreferencesHelper.deleteGloveBoxTracking(gloveBoxTracking);

    }

    @Override
    public void deleteHeadLinerTracking(boolean headLinerTracking) {
        mPreferencesHelper.deleteHeadLinerTracking(headLinerTracking);

    }

    @Override
    public void deleteHeaterTracking(boolean heaterTracking) {
        mPreferencesHelper.deleteHeaterTracking(heaterTracking);

    }

    @Override
    public void deleteInteriorTrimTracking(boolean interiorTrimTracking) {
        mPreferencesHelper.deleteInteriorTrimTracking(interiorTrimTracking);

    }

    @Override
    public void deleteSeatTracking(boolean seatTracking) {
        mPreferencesHelper.deleteSeatTracking(seatTracking);

    }

    @Override
    public void deleteVanityMirror(boolean vanityMirrorTracking) {
        mPreferencesHelper.deleteVanityMirror(vanityMirrorTracking);

    }

    @Override
    public void deleteAccelerationTracking(boolean accelerationTracking) {
        mPreferencesHelper.deleteAccelerationTracking(accelerationTracking);

    }

    @Override
    public void deleteBrakingTraking(boolean brakingTracking) {
        mPreferencesHelper.deleteBrakingTraking(brakingTracking);

    }

    @Override
    public void deleteEnginePerfTracking(boolean enginePerfTracking) {
        mPreferencesHelper.deleteEnginePerfTracking(enginePerfTracking);

    }

    @Override
    public void deleteIdlingTracking(boolean idlingTracking) {
        mPreferencesHelper.deleteIdlingTracking(idlingTracking);

    }

    @Override
    public void deleteStartingTracking(boolean startingTracking) {
        mPreferencesHelper.deleteStartingTracking(startingTracking);

    }

    @Override
    public void deleteSteeringTracking(boolean steeringTracking) {
        mPreferencesHelper.deleteSteeringTracking(steeringTracking);

    }

    @Override
    public void deleteSuspensionPerfTracking(boolean suspensionPerfTracking) {
        mPreferencesHelper.deleteSuspensionPerfTracking(suspensionPerfTracking);

    }

    @Override
    public void deleteTransmissionShiftTracking(boolean transmissionShiftTracking) {
        mPreferencesHelper.deleteTransmissionShiftTracking(transmissionShiftTracking);

    }

    @Override
    public void deleteSpareTireTracking(boolean spareTireTracking) {
        mPreferencesHelper.deleteSpareTireTracking(spareTireTracking);

    }

    @Override
    public void deleteTireTracking(boolean tireTracking) {
        mPreferencesHelper.deleteTireTracking(tireTracking);

    }

    @Override
    public void deleteWheelTracking(boolean wheelTracking) {
        mPreferencesHelper.deleteWheelTracking(wheelTracking);

    }

    @Override
    public void deleteBrakeSystemTracking(boolean brakeSystemTracking) {
        mPreferencesHelper.deleteBrakeSystemTracking(brakeSystemTracking);

    }

    @Override
    public void deleteDriveAxleTracking(boolean driveAxleTracking) {
        mPreferencesHelper.deleteDriveAxleTracking(driveAxleTracking);

    }

    @Override
    public void deleteExhaustTracking(boolean exhaustTracking) {
        mPreferencesHelper.deleteExhaustTracking(exhaustTracking);

    }

    @Override
    public void deleteFrameTracking(boolean frameTracking) {
        mPreferencesHelper.deleteFrameTracking(frameTracking);

    }

    @Override
    public void deleteSuspensionTracking(boolean suspensionTracking) {
        mPreferencesHelper.deleteSuspensionTracking(suspensionTracking);

    }

    @Override
    public void deleteTransmissionTracking(boolean transmissionTracking) {
        mPreferencesHelper.deleteTransmissionTracking(transmissionTracking);

    }

    @Override
    public void deleteBatteryTracking(boolean batteryTracking) {
        mPreferencesHelper.deleteBatteryTracking(batteryTracking);

    }

    @Override
    public void deleteBeltTracking(boolean beltTracking) {
        mPreferencesHelper.deleteBeltTracking(beltTracking);

    }

    @Override
    public void deleteEngineCompTracking(boolean engineCompTracking) {
        mPreferencesHelper.deleteEngineCompTracking(engineCompTracking);

    }

    @Override
    public void deleteFluidTracking(boolean fluidTracking) {
        mPreferencesHelper.deleteFluidTracking(fluidTracking);

    }

    @Override
    public void deleteHosesTracking(boolean hosesTracking) {
        mPreferencesHelper.deleteHosesTracking(hosesTracking);

    }

    @Override
    public void deleteOilTracking(boolean oilTracking) {
        mPreferencesHelper.deleteOilTracking(oilTracking);

    }

    @Override
    public void deleteWiringTracking(boolean wiringTracking) {
        mPreferencesHelper.deleteWiringTracking(wiringTracking);

    }

    @Override
    public void setMooveId(String mooveId) {
        mPreferencesHelper.setMooveId(mooveId);
    }

    @Override
    public String getMooveId() {
        return mPreferencesHelper.getMooveId();
    }

    @Override
    public void setCarYearMaint(String carYearMaint) {
        mPreferencesHelper.setCarYearMaint(carYearMaint);

    }

    @Override
    public String getCarYearMaint() {
        return mPreferencesHelper.getCarYearMaint();
    }

    @Override
    public void setCarMakeMaint(String carMakeMaint) {
        mPreferencesHelper.setCarMakeMaint(carMakeMaint);

    }

    @Override
    public String getCarMakeMaint() {
        return mPreferencesHelper.getCarMakeMaint();
    }

    @Override
    public void setCarModelMaint(String carModelMaint) {
        mPreferencesHelper.setCarModelMaint(carModelMaint);

    }

    @Override
    public String getCarModelMaint() {
        return mPreferencesHelper.getCarModelMaint();
    }

    @Override
    public void setVehicleIdMaint(String vehicleIdMaint) {
        mPreferencesHelper.setVehicleIdMaint(vehicleIdMaint);
    }

    @Override
    public String getVehicleIdMaint() {
        return mPreferencesHelper.getVehicleIdMaint();
    }

    @Override
    public void setTimeOnStop(long currentTimeOnStop) {
        mPreferencesHelper.setTimeOnStop(currentTimeOnStop);
    }

    @Override
    public long getTimeOnStop() {
        return mPreferencesHelper.getTimeOnStop();
    }

    @Override
    public void setMaintenanceReport(List<MaintenanceListData> maintenanceData) {
        mPreferencesHelper.setMaintenanceReport(maintenanceData);
    }

    @Override
    public List<MaintenanceListData> getMaintenanceReport() {
        return mPreferencesHelper.getMaintenanceReport();
    }

    @Override
    public void deleteMaintenanceReport(List<MaintenanceListData> maintenanceListData) {
        mPreferencesHelper.deleteMaintenanceReport(maintenanceListData);

    }

    @Override
    public void deleteMonthlyReport(List<VehicleCollection> vehicleCollections) {
        mPreferencesHelper.deleteMonthlyReport(vehicleCollections);
    }

    @Override
    public void setMonthlyFinalAssessment(String finalAssessment) {
        mPreferencesHelper.setMonthlyFinalAssessment(finalAssessment);
    }

    @Override
    public String getMonthlyFinalAssessment() {
        return mPreferencesHelper.getMonthlyFinalAssessment();
    }

    @Override
    public void setMonthlyFinalComment(String finalComment) {
        mPreferencesHelper.setMonthlyFinalComment(finalComment);

    }

    @Override
    public String getMonthlyFinalComment() {
        return mPreferencesHelper.getMonthlyFinalComment();
    }

    @Override
    public void setIntakeAcceptanceValue(String intakeAcceptanceValue) {
        mPreferencesHelper.setIntakeAcceptanceValue(intakeAcceptanceValue);
    }

    @Override
    public String getIntakeAcceptanceValue() {
        return mPreferencesHelper.getIntakeAcceptanceValue();
    }

    @Override
    public void setMonthlyAcceptanceValue(String monthlyAcceptanceValue) {
        mPreferencesHelper.setMonthlyAcceptanceValue(monthlyAcceptanceValue);

    }

    @Override
    public String getMonthlyAcceptanceValue() {
        return mPreferencesHelper.getMonthlyAcceptanceValue();
    }

    @Override
    public void deleteIntakeAcceptanceValue(String intakeAcceptance) {
        mPreferencesHelper.deleteIntakeAcceptanceValue(intakeAcceptance);
    }

    @Override
    public void deleteMonthlyAcceptanceValue(String monthlyAcceptance) {
        mPreferencesHelper.deleteMonthlyAcceptanceValue(monthlyAcceptance);

    }

    @Override
    public void setInitialMileage(String initialMileage) {
        mPreferencesHelper.setInitialMileage(initialMileage);
    }

    @Override
    public String getInitialMileage() {
        return mPreferencesHelper.getInitialMileage();
    }

//    @Override
//    public void deleteArray(List<VehicleCollection> delete) {
//         mPreferencesHelper.deleteArray(delete);
//    }

    @Override
    public void deleteAll() {
        mPreferencesHelper.deleteAll();
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
    public Single<CreateReportResponse> checkRegNo(RegNumberCheckRequest.Request request) {
        return mApiHelper.checkRegNo(request);
    }

    @Override
    public Single<CreateReportResponse> checkIntakeRule(IntakeRuleRequest.Request request) {
        return mApiHelper.checkIntakeRule(request);
    }

    @Override
    public Flowable<InspectorListResponse> getMonthlyVehicleList() {
        return mApiHelper.getMonthlyVehicleList();
    }

    @Override
    public Flowable<MaintenanceScheduleResponse> getMaintenanceSchedule(MaintenanceScheduleRequest.Request request) {
        return mApiHelper.getMaintenanceSchedule(request);
    }

    @Override
    public Single<CreateReportResponse> checkIntakeReport(CheckIntakeRequest.Request request) {
        return mApiHelper.checkIntakeReport(request);
    }

    @Override
    public Single<CreateReportResponse>checkMonthlyReport(String vehicleId) {
        return mApiHelper.checkMonthlyReport(vehicleId);
    }

    @Override
    public Single<CreateReportResponse> checkMaintenanceReport(String vehicleId) {
        return mApiHelper.checkMaintenanceReport(vehicleId);
    }

    @Override
    public Single<CreateReportResponse> checkRepairsReport(String vehicleId) {
        return mApiHelper.checkRepairsReport(vehicleId);
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
    public void updateLoginStatus(LoggedInMode loggedInMode) {
        setCurrentUserLoggedInMode(loggedInMode);
    }


    @Override
    public void updateUserInfo(String accessToken, String firstname,String email
            ) {

        setAccessToken(accessToken);
        setCurrentUserName(firstname);
        setCurrentUserEmail(email);

    }


}
