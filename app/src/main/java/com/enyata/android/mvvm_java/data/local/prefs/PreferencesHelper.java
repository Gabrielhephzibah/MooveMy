package com.enyata.android.mvvm_java.data.local.prefs;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePart;

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

    void setInTakeVehicleReport(List<VehicleCollection.Request>vehiclePart);

    List<VehicleCollection.Request> getInTakeVehicleReport();




}
