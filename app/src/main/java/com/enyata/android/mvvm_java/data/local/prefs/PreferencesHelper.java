package com.enyata.android.mvvm_java.data.local.prefs;

import android.content.SharedPreferences;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;

import java.util.List;

public interface PreferencesHelper {
    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    void  setCarYear(String carYear);

    String getCarYear();

    void  setCarModel(String carModel);

    String getCarModel();

    void setCarMake(String carMake);

    String getCarMake();

    void  setCurrentMileage(String currentMileage);

    String getCurrentMilege();

    void  setRegistrationNo(String registrationNo);

    String getRegistrationNo();

    void  setVin(String vin);

    String getVin();

    void  setCarColor(String carColor);

    String getCarColor();

    void setInTakeVehicleReport(List<VehicleCollection>vehiclePart);

    List<VehicleCollection> getInTakeVehicleReport();

    void setIntakeFinalStatus(String status);

    String getIntakeFinalStatus();

    void setIntakeFinalComment(String comment);

    String getIntakeFinalComment();

    void  setReportType(String reportType);

    String getReportType();

    void setVehicleId(String vehicleId);

    String getVehicleId();

    void  setRepairReport(List<VehiclePartRepair>partRepair);

    List<VehiclePartRepair> getRepairReport();

    void deleteRepairReport(List<VehiclePartRepair>repair);

    void deleteIntakeReport(List<VehicleCollection>delete);

    void deleteCarYear(String carYear);

    void deleteCarModel(String carModel);

    void deleteCarMake(String carMake);

    void deleteCurrentMileage(String CurrentMileage);

    void deleteRegistrationNo(String regNo);

    void deleteVin(String vin);

    void  deleteCarColor(String carColor);

    void  deleteIntakeFinalStatus(String status);

    void  deleteIntakeFinalComment(String comment);

    void deleteReportType(String reportType);

    void setHoodTrackingStatus( boolean hoodTrackingStatus);

    boolean getHoodTrackingStatus();

    void  setDoorTrackingStatus(boolean doorTrackingStatus);

    boolean getDoorTrackingStatus();

    void setFenderTracking(boolean fendersTracking);

    boolean getFenderTraking();

    void setFrontBumperTracking(boolean frontBumperTracking);

    boolean getFrontBumberTracking();

    void setFuelDoorTracking(boolean fuelDoorTracking);

    boolean getFuelDoorTracking();

    void setPaintTracking(boolean paintTracking);

    boolean getPaintTracking();

    void setRearBumperTracking(boolean rearBumperTracking);

    boolean getRearBumperTracking();

    void setRearTracking(boolean rearTracking);

    boolean getRearTracking();

    void  setRoofTracking(boolean roofTracking);

    boolean getRoofTracking();

    void setTrimTracking(boolean trimTracking);

    boolean getTrimTracking();

    void  setTrunkTracking(boolean trunkTracking);

    boolean getTrunkTracking();

    void  setVehicleInfo(boolean allVehicleInfo);

    boolean getVehicleInfo();

    void  deleteVehicleInfo(boolean deleteVehicleInfo);

    void setMirrorTracking(boolean mirrorTracking);

    boolean getMirrorTracking();

    void  setRearWindowTracking(boolean rearWindowTracking);

    boolean getRearWindowTracking();

    void  setWindowTracking(boolean windowTracking);

    boolean getWindowTracking();

    void  setWindshieldTracking(boolean windshieldTracking);

    boolean getWindshieldTracking();

    void setSpareTireTracking (boolean spareTireTracking);

    boolean getSpareTireTraking();

    void  setTireTracking(boolean tireTracking);

    boolean getTireTracking();

    void  setWheelTracking(boolean wheelTracking);

    boolean getWheelTracking();

    void setBrakeSystemTracking(boolean brakeSystemTracking);

    boolean getBrakeSystemTracking();

    void  setDriveAxleTracking(boolean driveAxleTracking);

    boolean getDriveAxleTracking();

    void  setExhaustTracking(boolean exhaustTracking);

    boolean getExhaustTracking();

    void  setFrameTracking(boolean frameTracking);

    boolean getFrameTracking();

    void  setSuspensionTracking(boolean suspensionTracking);

    boolean getSuspensionTracking();

    void  setTransmissionTracking(boolean transmissionTracking);

    boolean getTransmissonTracking();

    void  setBatteryTracking(boolean batteryTracking);

    boolean getBatteryTracking();

    void  setBeltTracking(boolean beltTracking);

    boolean getBeltTracking();

    void  setEngineCompTracking(boolean engineCompTracking);

    boolean getEngineCompTracking();

    void  setFluidTracking(boolean fluidTracking);

    boolean getFluidTracking();

    void  setHosesTracking(boolean hosesTracking);

    boolean getHosesTracking();

    void  setOilTracking(boolean oilTracking);

    boolean getOilTracking();

    void  setWiringTracking(boolean wiringTracking);

    boolean getWiringTracking();

    void setAccelerationTracking(boolean accelerationTracking);

    boolean getAccelerationTracking();

    void  setBrakingTracking(boolean brakingTracking);

    boolean getBrakingTracking();

    void  setEnginePerfTracking(boolean enginePerfTracking);

    boolean getEnginePerfTracking();

    void  setIdlingTracking(boolean idlingTracking);

    boolean getIdlingTracking();

    void  setStartingTracking(boolean startingTracking);

    boolean getStartingTracking();

    void  setSteeringTracking(boolean steeringTracking);

    boolean getSteeringTracking();

    void  setSuspensionPerfTracking(boolean suspensionPerfTracking);

    boolean getSuspensionPerfTracking();

    void  setTransmissionShiftTracking(boolean transmissionShiftTracking);

    boolean getTransmissionShiftTracking();

    void setAudioSystemTracking(boolean audioSystemTracking);

    boolean getAudioSystemTracking();

    void  setBrakeLightTracking(boolean brakeLightTracking);

    boolean getBrakeLightTracking();

    void  setComputerTracking(boolean computerTracking);

    boolean getComputerTracking();

    void  setHeadlightTracking(boolean headlightTracking);

    boolean getHeadLightTracking();

    void setParkingLightTracking(boolean parkingLightTracking);

    boolean getParkingLightTracking();

    void  setPowerLockTracking(boolean powerLockTracking);

    boolean getPowerLockTracking();

    void  setPowerMirrorTracking(boolean powerMirrorTracking);

    boolean getPowerMirrorTracking();

    void  setPowerSeatTracking(boolean powerSeatTracking);

    boolean getPowerSeatTracking();

    void  setPowerSteeringTracking(boolean powerSteeringTracking);

    boolean getPowerSteeringTracking();

    void  setPowerWindowTracking(boolean powerWindowTracking);

    boolean getPoweWindowTracking();

    void  setSignalLightTracking(boolean signalLightTracking);

    boolean getSignalLightTracking();

    void  setTailLightTracking(boolean tailLightTracking);

    boolean getTailLightTracking();

    void setAirCondTracking(boolean airCondTracking);

    boolean getAircondTracking();

    void  setCarpetTracking(boolean carpetTracking);

    boolean getCarpetTrackin();

    void  setDashboardTracking(boolean dashboardTracking);

    boolean getDashboardTracking();

    void  setDashGuagesTracking(boolean dashGuagesTracking);

    boolean getDashGuagesTracking();

    void  setDefrosterTracking(boolean defrosterTracking);

    boolean getDefrosterTracking();

    void  setDoorPanelTracking(boolean doorPanelTracking);

    boolean getDoorPanelTracking();

    void  setGloveBoxTracking(boolean gloveBoxTracking);

    boolean getGloveBoxTracking();

    void  setHeadLinerTracking(boolean headLinerTracking);

    boolean getHeadlinerTracking();

    void  setHeaterTracking(boolean heaterTracking);

    boolean getHeaterTracking();

    void  setInteriorTrimTracking(boolean interiorTrimTracking);

    boolean getInteriorTrimTracking();

    void  setSeatTracking(boolean seatTracking);

    boolean getSeatTracking();

    void  setVanityMirrorTracking(boolean vanityMirrorTracking);

    boolean getVanityMirrorTracking();

    void setImageArraySaved(boolean imageArraySaved);

    boolean getImageArraySaved();

    void deleteAudioSystemTracking(boolean audioSystemTracking);

    void  deleteBrakeLightTracking(boolean brakelightTracking);

    void  deleteComputerTracking(boolean computerTracking);

    void  deleteHeadlightTracking(boolean headLightTracking);

    void  deleteParkingLightTracking(boolean parkingLightTracking);

    void  deletePowerLockTracking(boolean powerLockTracking);

    void deletePowerMirrorTracking(boolean powerMirrorTracking);

    void deletePowerSeatTracking(boolean powerSeatTracking);

    void  deletePowerSteeringTracking(boolean powerSteeringTracking);

    void  deletePowerWindowTracking(boolean powerWindowTracking);

    void  deleteSignalLightTracking(boolean signalLightTracking);

    void  deleteTailLightTracking(boolean tailLightTracking);

    void  deleteDoorTracking(boolean doorTracking);

    void  deleteFenderTracking(boolean fenderTracking);

    void  deleteFrontBumperTracking(boolean frontBumperTracking);

    void  deleteFuelDoorTracking(boolean fuelDoorTracking);

    void  deleteHoodTracking(boolean hoodTracking);

    void  deletePaintTracking(boolean paintTracking);

    void  deleteRearBumperTracking(boolean rearBumperTracking);

    void  deleteRearTracking(boolean rearTracking);

    void  deleteRoofTracking(boolean roofTracking);

    void  deleteTrimTracking(boolean trimTracking);

    void  deleteTrunkTracking(boolean trunkTracking);

    void deleteMirrorTracking(boolean mirrorTracking);

    void  deleteRearWindowTracking(boolean rearWindowTracking);

    void deleteWindowTracking(boolean windowTracking);

    void  deleteWindShieldTracking(boolean windShieldTracking);

    void deleteAirCondTracking(boolean airCondTracking);

    void deleteCarpetTracking(boolean carpetTracking);

    void  deleteDashBoardTracking(boolean dashboardTracking);

    void  deleteDashGuagesTracking(boolean dashGuageTracking);

    void  deleteDefrosterTracking(boolean defrosterTracking);

    void  deleteDoorPanelTracking(boolean doorPanelTracking);

    void  deleteGloveBoxTracking(boolean gloveBoxTracking);

    void deleteHeadLinerTracking(boolean headLinerTracking);

    void  deleteHeaterTracking(boolean heaterTracking);

    void  deleteInteriorTrimTracking(boolean interiorTrimTracking);

    void  deleteSeatTracking(boolean seatTracking);

    void  deleteVanityMirror(boolean vanityMirrorTracking);

    void deleteAccelerationTracking(boolean accelerationTracking);

    void  deleteBrakingTraking(boolean brakingTracking);

    void  deleteEnginePerfTracking(boolean enginePerfTracking);

    void deleteIdlingTracking(boolean idlingTracking);

    void  deleteStartingTracking(boolean startingTracking);

    void  deleteSteeringTracking(boolean steeringTracking);

    void  deleteSuspensionPerfTracking(boolean suspensionPerfTracking);

    void  deleteTransmissionShiftTracking(boolean transmissionShiftTracking);

    void deleteSpareTireTracking(boolean spareTireTracking);

    void  deleteTireTracking(boolean tireTracking);

    void  deleteWheelTracking(boolean wheelTracking);

    void deleteBrakeSystemTracking(boolean brakeSystemTracking);

    void deleteDriveAxleTracking(boolean driveAxleTracking);

    void  deleteExhaustTracking(boolean exhaustTracking);

    void deleteFrameTracking(boolean frameTracking);

    void  deleteSuspensionTracking(boolean suspensionTracking);

    void deleteTransmissionTracking(boolean transmissionTracking);

    void deleteBatteryTracking(boolean batteryTracking);

    void  deleteBeltTracking(boolean beltTracking);

    void  deleteEngineCompTracking(boolean engineCompTracking);

    void  deleteFluidTracking(boolean fluidTracking);

    void deleteHosesTracking(boolean hosesTracking);

    void deleteOilTracking(boolean oilTracking);

    void  deleteWiringTracking(boolean wiringTracking);

    void  setMooveId (String mooveId);

    String getMooveId();

    void  setCarYearMaint (String carYearMaint);

    String getCarYearMaint();

    void  setCarMakeMaint (String carMakeMaint);

    String getCarMakeMaint();

    void  setCarModelMaint (String carModelMaint);

    String getCarModelMaint();

    void setTimeOnStop(long currentTimeOnStop);

    long getTimeOnStop();









    void deleteAll();






}
