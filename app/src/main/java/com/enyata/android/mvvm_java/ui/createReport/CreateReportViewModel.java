package com.enyata.android.mvvm_java.ui.createReport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.IntakeRuleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.RegNumberCheckRequest;
import com.enyata.android.mvvm_java.data.model.api.request.Vehicle;
import com.enyata.android.mvvm_java.glide.GlideApp;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class CreateReportViewModel extends BaseViewModel<CreateReportNavigator> {
    Context context;

    public CreateReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack() {
        getNavigator().back();
    }

    public void onInspectFeature() {
        getNavigator().onInspectFeature();
    }

    public void onAddSignature() {
        getNavigator().onAddSignature();
    }

    public void onExteriorFeature() {
        getNavigator().onExteriorFeature();
    }

    public void onGlassFeature() {
        getNavigator().onGlassFeature();
    }

    public void onTiresFeature() {
        getNavigator().onTiresFeature();
    }

    public void onUnderBodyFeature() {
        getNavigator().onUnderBodyFeature();
    }

    public void onUnderHoodFeature() {
        getNavigator().onUnderHoodFeature();
    }

    public void onInteriorFeature() {
        getNavigator().onInteriorFeature();
    }

    public void onElectricFeature() {
        getNavigator().onElectricFeature();
    }

    public void onRoadTestFeature() {
        getNavigator().onRoadTestFeature();
    }

    public void onSignatureFeature() {
        getNavigator().onSignatureFeature();
    }

    public void onSaveVehicleInfo() {
        getNavigator().onSaveVehicleInfo();
    }

    public void setCarYear(String carYear) {
        getDataManager().setCarYear(carYear);
    }

    public String getCarYear() {
        return getDataManager().getCarYear();
    }

    public void setCarModel(String carModel) {

        getDataManager().setCarModel(carModel);
    }

    public String getCarModel() {
        return getDataManager().getCarModel();
    }

    public void setCarMake(String carMake) {
        getDataManager().setCarMake(carMake);
    }

    public String getCarMake() {
        return getDataManager().getCarMake();
    }

    public void setCarColor(String carColor) {
        getDataManager().setCarColor(carColor);
    }

    public String getCarColor() {
        return getDataManager().getCarColor();
    }

    public void setMillage(String mileage) {
        getDataManager().setCurrentMileage(mileage);
    }

    public String getMilage() {
        return getDataManager().getCurrentMilege();
    }

    public void setRegNo(String regNo) {
        getDataManager().setRegistrationNo(regNo);
    }

    public String getregNo() {
        return getDataManager().getRegistrationNo();
    }

    public void setVin(String vin) {
        getDataManager().setVin(vin);
    }

    public String getVin() {
        return getDataManager().getVin();
    }

    public void setFinalStatus(String status) {
        getDataManager().setIntakeFinalStatus(status);
    }

    public void setFinalComment(String comment) {
        getDataManager().setIntakeFinalComment(comment);
    }

    public String getReportType() {
        return getDataManager().getReportType();
    }

    public void onSubmitVin() {
        getNavigator().onSubmitVin();
    }

    public void setVehicleInfo(boolean vehicleInfo){
        getDataManager().setVehicleInfo(vehicleInfo);
    }

    public boolean getVehincleInfo(){
        return getDataManager().getVehicleInfo();
    }


    public void getCarVin(String vinNo) {
        getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                .getVinData(vinNo)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }


    public void checkRegNumber(RegNumberCheckRequest.Request request){
        getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                 .checkRegNo(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().validateData(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleValidateError(throwable);

                }));

    }

    public void checkIntakeRule(IntakeRuleRequest.Request request){
        getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                .checkIntakeRule(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().validateData(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleValidateError(throwable);

                }));
    }


    public boolean checkIfIntakeVehicleReportIsEmpty() {
        return getIntakeVehicleReport() == null;
    }

    public void setIntakeVehincleReport(List<VehicleCollection> vehiclePart) {
        getDataManager().setInTakeVehicleReport(vehiclePart);
    }

    public List<VehicleCollection> getIntakeVehicleReport() {
        return getDataManager().getInTakeVehicleReport();
    }

//    public void deleteData(List<VehicleCollection>delete){
//        getDataManager().deleteArray(delete);
//    }

    public void deleteAll() {
        getDataManager().deleteAll();
    }

    public boolean contain(Object object) {
        if (getIntakeVehicleReport().contains(object)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean arrayImagesEmpty(Activity activity,VehicleCollection vehicleCollection){
        if (checkIfIntakeVehicleReportIsEmpty()){
        }else {
            List<VehicleCollection>myPart = getIntakeVehicleReport();
            for (int j = 0; j<myPart.size(); j++){
                if (myPart.get(j).getPart().equals(vehicleCollection.getPart())){
                    myPart.get(j).getImageUrl();
                    if (myPart.get(j).getImageUrl()!= null){
                        Alert.showSuccess(activity,"Item saved");
                    }else {
                        Alert.showFailed(activity,"Please fill all fields and save again ");
                    }
                    return true;
                }
            }


        }
        return false;

    }

    Bitmap bitmap;

    public boolean isVehicleSave(VehicleCollection vehicle, RadioButton good, RadioButton fair, RadioButton bad,Fragment fragment,ImageView firstImage, ImageView secondImage, ImageView thirdImage) {


        if (checkIfIntakeVehicleReportIsEmpty()) {
        } else {
            List<VehicleCollection> part = getIntakeVehicleReport();
            for (int k = 0; k < part.size(); k++) {
                if (part.get(k).getPart().equals(vehicle.getPart())) {
                    part.get(k).getImageUrl();
                    part.get(k).getRemark();

                    if (part.get(k).getRemark().equals("good")){
                       good.setChecked(true); ;
                    }else if (part.get(k).getRemark().equals("fair")){
                        fair.setChecked(true);
                    }else {
                        bad.setChecked(true);
                    }
                    List<String>images = part.get(k).getImageUrl();
                    GlideApp.with(fragment).load(images.get(0)).into(firstImage);
                    GlideApp.with(fragment).load(images.get(1)).into(secondImage);
                    GlideApp.with(fragment).load(images.get(2)).into(thirdImage);

                    Log.i("Part", String.valueOf(part.get(k).getImageUrl()));
                    Log.i("Image", String.valueOf(part.get(k).getRemark()));
                    Log.i("REmark", vehicle.getRemark());
                    return true;
                }
            }
            return false;
        }
        return false;
    }



    public boolean checkIfObjectExist() {
        return getIntakeVehicleReport() == null;
    }

    public boolean vehincleSave;

    public void saveReportToLocalStorage(VehicleCollection vehiclePart) {
        if (checkIfIntakeVehicleReportIsEmpty()) {
            List<VehicleCollection> newArray = new ArrayList<>();
            newArray.add(vehiclePart);
            setIntakeVehincleReport(newArray);

        } else {
            List<VehicleCollection> oldArray = getIntakeVehicleReport();
            Log.i("jjjjjj", "Already");
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getPart().equals(vehiclePart.getPart())) {
                    oldArray.get(i).setRemark(vehiclePart.getRemark());
                    oldArray.get(i).setImageUrl(vehiclePart.getImageUrl());
                    Log.i("part", vehiclePart.getPart());
                    setIntakeVehincleReport(oldArray);
                    Log.i("NEW NEW", String.valueOf(getIntakeVehicleReport()));

                    break;
                } else {
                    if (getIntakeVehicleReport().contains(vehiclePart)) {
                    }
                    List<VehicleCollection> sameArray = getIntakeVehicleReport();
                    setIntakeVehincleReport(sameArray);


                }
            }

        }
        if (!getIntakeVehicleReport().contains(vehiclePart)) {
            List<VehicleCollection> addArray = getIntakeVehicleReport();
            addArray.add(vehiclePart);
            setIntakeVehincleReport(addArray);
            vehincleSave = true;
        }


        Log.i("Big Array", String.valueOf(getIntakeVehicleReport()));

    }



    public boolean getHoodTrackingStatus(){
        return getDataManager().getHoodTrackingStatus();
    }

    public void setHoodTrackingStatus(boolean hoodTrackingStatus){ getDataManager().setHoodTrackingStatus(hoodTrackingStatus); }

    public boolean getDoorTrackingStatus(){
        return getDataManager().getDoorTrackingStatus();
    }

    public void setDoorTrackingStatus(boolean doorTrackingStatus){ getDataManager().setDoorTrackingStatus(doorTrackingStatus); }

    public void setFenderTracking(boolean fenderTracking){getDataManager().setFenderTracking(fenderTracking);}

    public boolean getFenderTracking(){return getDataManager().getFenderTraking();}

    public void setFrontBumperTracking(boolean fontBumperTracking){getDataManager().setFrontBumperTracking(fontBumperTracking);}

    public boolean getFrontBumperTracking(){return getDataManager().getFrontBumberTracking();}

    public void setFuelDoorTracking(boolean fuelDoorTracking){getDataManager().setFuelDoorTracking(fuelDoorTracking);}

    public boolean getFuelDoorTracking(){
        return getDataManager().getFuelDoorTracking();
    }

    public void setPaintTracking(boolean paintTracking){ getDataManager().setPaintTracking(paintTracking); }

    public boolean getPaintTracking(){
        return  getDataManager().getPaintTracking();
    }

    public void setRearBumperTracking(boolean rearBumperTracking){ getDataManager().setRearBumperTracking(rearBumperTracking); }

    public boolean getRearBumperTracking(){
        return getDataManager().getRearBumperTracking();
    }

    public void setRearTracking(boolean rearTracking){ getDataManager().setRearTracking(rearTracking); }

    public boolean getRearTracking(){
        return  getDataManager().getRearTracking();
    }

    public void  setRoofTracking(boolean roofTracking){ getDataManager().setRoofTracking(roofTracking); }

    public boolean getRoofTracking(){
        return getDataManager().getRoofTracking();
    }

    public  void  setTrimTracking(boolean trimTracking){
        getDataManager().setTrimTracking(trimTracking);
    }

    public boolean getTrimTracking() {
        return getDataManager().getTrimTracking();
    }

    public void setTrunkTracking(boolean trunkTracking) {
        getDataManager().setTrunkTracking(trunkTracking);
    }

    public boolean getTrunkTracking(){
        return getDataManager().getTrunkTracking();
    }

    public void setMirrorTracking(boolean mirrorTracking){
        getDataManager().setMirrorTracking(mirrorTracking);
    }

    public boolean getMirrorTracking(){return  getDataManager().getMirrorTracking();}

    public void setRearWindowTracking(boolean rearWindowTracking){
        getDataManager().setRearWindowTracking(rearWindowTracking);
    }

    public  boolean getRearWindowTracking(){return  getDataManager().getRearWindowTracking();}

    public void  setWindowTracking(boolean windowTracking){
        getDataManager().setWindowTracking(windowTracking);
    }

    public boolean getWindowTracking(){
        return  getDataManager().getWindowTracking();
    }

    public void  setWinshieldTracking(boolean winshieldTracking){
        getDataManager().setWindshieldTracking(winshieldTracking);
    }

    public boolean getWindShieldTracking(){
        return  getDataManager().getWindshieldTracking();
    }

    public void setSpareTireTracking(boolean spareTireTracking){
        getDataManager().setSpareTireTracking(spareTireTracking);
    }

    public boolean getSpareTireTracking(){
        return getDataManager().getSpareTireTraking();
    }

    public void setTiresTracking(boolean tiresTracking){
        getDataManager().setTireTracking(tiresTracking);
    }

    public boolean getTiresTracking(){
      return   getDataManager().getTireTracking();
    }

    public void setWheelTracking(boolean wheelTracking){
        getDataManager().setWheelTracking(wheelTracking);
    }

    public boolean getWheelTracking(){
        return getDataManager().getWheelTracking();
    }

    public void setBrakeSystemTracking(boolean brakeSystemTracking){
        getDataManager().setBrakeSystemTracking(brakeSystemTracking);
    }

    public boolean getBrakeSystemTracking(){
        return  getDataManager().getBrakeSystemTracking();
    }

    public  void  setDriveAxleTracking(boolean driveAxleTracking){
        getDataManager().setDriveAxleTracking(driveAxleTracking);
    }

    public boolean getDriveAxleTracking(){
        return getDataManager().getDriveAxleTracking();
    }

    public void  setExhaustTracking(boolean exhaustTracking){
        getDataManager().setExhaustTracking(exhaustTracking);
    }

    public boolean getExhaustTracking(){
        return getDataManager().getExhaustTracking();
    }

    public void setFrameTracking(boolean frameTracking){
        getDataManager().setFrameTracking(frameTracking);

    }

    public boolean getFrameTracking(){
        return  getDataManager().getFrameTracking();
    }

    public void  setSuspensionTracking(boolean suspensionTracking){
        getDataManager().setSuspensionTracking(suspensionTracking);

    }

    public boolean getSuspensionTracking(){
        return  getDataManager().getSuspensionTracking();
    }

    public void setTransmissionTracking(boolean transmissionTracking){
        getDataManager().setTransmissionTracking(transmissionTracking);

    }

    public  boolean getTransmissionTracking(){
        return  getDataManager().getTransmissonTracking();
    }

    public void setBatteryTracking(boolean batteryTracking){
        getDataManager().setBatteryTracking(batteryTracking);
    }

    public  boolean getBatteryTracking(){
        return  getDataManager().getBatteryTracking();
    }

    public void setBeltTracking(boolean beltTracking){
        getDataManager().setBeltTracking(beltTracking);
    }

    public  boolean getBeltTracking(){
        return  getDataManager().getBeltTracking();
    }

    public void  setEngineCompTracking(boolean engineComp){
        getDataManager().setEngineCompTracking(engineComp);
    }

    public  boolean getEngineCompTracking(){
        return  getDataManager().getEngineCompTracking();
    }

    public  void  setFluidTracking(boolean fluidTracking){
        getDataManager().setFluidTracking(fluidTracking);
    }

    public  boolean getFluidTracking(){
        return  getDataManager().getFluidTracking();
    }

    public  void setHosesTracking(boolean hosesTracking){
        getDataManager().setHosesTracking(hosesTracking);
    }

    public  boolean getHosesTracking(){
        return  getDataManager().getHosesTracking();
    }

    public void setOilTracking(boolean oilTracking){
        getDataManager().setOilTracking(oilTracking);
    }

    public  boolean getOilTracking(){
        return  getDataManager().getOilTracking();
    }

    public void setWiringTracking(boolean wiringTracking){
        getDataManager().setWiringTracking(wiringTracking);
    }

    public boolean getWiringTracking(){
        return  getDataManager().getWiringTracking();
    }

    public void setAccelerationTracking(boolean accelerationTracking){
        getDataManager().setAccelerationTracking(accelerationTracking);
    }

    public  boolean getAccelerationTracking(){
        return  getDataManager().getAccelerationTracking();
    }

    public  void setBrakingTracking(boolean brakingTracking){
        getDataManager().setBrakingTracking(brakingTracking);
    }

    public  boolean getBrakingTracking(){
        return  getDataManager().getBrakingTracking();
    }

    public void  setEnginePerfTracking(boolean enginePerfTracking){
        getDataManager().setEnginePerfTracking(enginePerfTracking);
    }


    public  boolean getEnginePerfTracking(){
        return getDataManager().getEnginePerfTracking();
    }


    public  void  setIdlingTracking(boolean idlingTracking){
        getDataManager().setIdlingTracking(idlingTracking);
    }

    public boolean  getIdlingTracking(){
        return  getDataManager().getIdlingTracking();
    }


    public  void  setStartingTracking(boolean startingTracking){
        getDataManager().setStartingTracking(startingTracking);
    }


    public  boolean getStartingTracking(){
        return  getDataManager().getStartingTracking();
    }


    public  void  setSteeringTracking(boolean steeringTracking){
        getDataManager().setSteeringTracking(steeringTracking);
    }


    public  boolean getSteeringTracking(){
        return  getDataManager().getSteeringTracking();
    }


    public  void  setSuspensionPerfTracking(boolean suspensionPerfTracking){
        getDataManager().setSuspensionPerfTracking(suspensionPerfTracking);
    }


    public  boolean getSuspensionPerfTracking(){
        return  getDataManager().getSuspensionPerfTracking();
    }


    public  void  setTransmissionShiftTracking(boolean transmissionShiftTracking){
        getDataManager().setTransmissionShiftTracking(transmissionShiftTracking);
    }


    public  boolean getTransmissionShiftTracking(){
        return  getDataManager().getTransmissionShiftTracking();
    }

    public void  setAudioSystemTracking(boolean audioSystemTracking){
        getDataManager().setAudioSystemTracking(audioSystemTracking);
    }

    public  boolean getAudioSystemTracking(){
        return  getDataManager().getAudioSystemTracking();
    }

    public void  setBrakelightTracking(boolean brakelightTracking){
        getDataManager().setBrakeLightTracking(brakelightTracking);
    }

    public boolean getBrakeLightTracking(){
        return getDataManager().getBrakeLightTracking();
    }

    public void setComputerTracking(boolean computerTracking){
        getDataManager().setComputerTracking(computerTracking);
    }

    public  boolean getComputerTracking(){
        return  getDataManager().getComputerTracking();
    }

    public  void setHeadLightTracking(boolean headLightTracking){
        getDataManager().setHeadlightTracking(headLightTracking);
    }

    public  boolean getHeadLightTracking(){
        return getDataManager().getHeadLightTracking();
    }

    public  void  setParkingLightTracking(boolean parkingLightTracking){
        getDataManager().setParkingLightTracking(parkingLightTracking);
    }


    public  boolean getParkingLightTracking(){
        return  getDataManager().getParkingLightTracking();
    }

    public  void  setPowerLockTracking(boolean powerLockTracking){
        getDataManager().setPowerLockTracking(powerLockTracking);
    }

    public  boolean getPowerLockTracking(){
        return  getDataManager().getPowerLockTracking();
    }

    public void  setPowerMirrorTracking(boolean powerMirrorTracking){
        getDataManager().setPowerMirrorTracking(powerMirrorTracking);
    }

    public  boolean getPowerMiirorTracking(){
        return  getDataManager().getPowerMirrorTracking();
    }

    public  void  setPowerSeatTracking(boolean powerSeatTracking){
        getDataManager().setPowerSeatTracking(powerSeatTracking);
    }

    public  boolean getPowerSeatTracking(){
        return getDataManager().getPowerSeatTracking();
    }

    public void  setPowerSteeringTracking(boolean powerSteeringTracking){
        getDataManager().setPowerSteeringTracking(powerSteeringTracking);
    }

    public  boolean getPowerSteeringTracking(){return getDataManager().getPowerSteeringTracking();
    }

    public void setPowerWindowTracking(boolean powerWindowTracking){
        getDataManager().setPowerWindowTracking(powerWindowTracking);
    }

    public  boolean getPowerWindowTracking(){
        return getDataManager().getPoweWindowTracking();
    }

    public void  setSignalLightTracking(boolean signalLightTracking){
        getDataManager().setSignalLightTracking(signalLightTracking);
    }

    public  boolean getSignalLightTracking(){
        return  getDataManager().getSignalLightTracking();
    }

    public void  setTailLighTracking(boolean tailLighTracking){
        getDataManager().setTailLightTracking(tailLighTracking);
    }

    public  boolean getTailLightTracking(){
        return  getDataManager().getTailLightTracking();

    }

    public void setAircondTracking(boolean aircondTracking){
        getDataManager().setAirCondTracking(aircondTracking);
    }

    public  boolean getAirCondTraking(){
        return  getDataManager().getAircondTracking();
    }

    public  void setCarpetTracking(boolean carpetTracking){
        getDataManager().setCarpetTracking(carpetTracking);
    }

    public  boolean getCarpetTracking(){
        return  getDataManager().getCarpetTrackin();
    }
    public void  setDashBoardTracking(boolean dashBoardTracking){
        getDataManager().setDashboardTracking(dashBoardTracking);
    }

    public  boolean getdashBoardTracking(){
        return   getDataManager().getDashboardTracking();
    }

    public void  setDashGuageTracking(boolean dashGuageTracking){
        getDataManager().setDashGuagesTracking(dashGuageTracking);
    }

    public  boolean getDashGuageTracking(){
        return  getDataManager().getDashGuagesTracking();
    }

    public  void  setDefrosterTracking(boolean defrosterTracking){
        getDataManager().setDefrosterTracking(defrosterTracking);
    }

    public boolean getDefrosterTrcking(){
        return  getDataManager().getDefrosterTracking();
    }

    public void setDoorPanelTracking(boolean doorPanelTracking){
        getDataManager().setDoorPanelTracking(doorPanelTracking);
    }

    public  boolean getDoorPanelTracking(){
        return  getDataManager().getDoorPanelTracking();
    }

    public void  setGloveBoxTracking(boolean gloveBoxTracking){
        getDataManager().setGloveBoxTracking(gloveBoxTracking);
    }

    public boolean getGloveBoxTracking(){
        return  getDataManager().getGloveBoxTracking();
    }

    public void setHeadLinerTracking(boolean headLinerTracking){
        getDataManager().setHeadLinerTracking(headLinerTracking);
    }

    public  boolean getHeadLinerTracking(){
        return  getDataManager().getHeadlinerTracking();
    }

    public void  setHeaterTracking(boolean heaterTracking){
        getDataManager().setHeaterTracking(heaterTracking);
    }

    public boolean getHeaterTracking(){return getDataManager().getHeaterTracking();}

    public void setInteriorTrimTracking(boolean interiorTrimTracking){
        getDataManager().setInteriorTrimTracking(interiorTrimTracking);
    }

    public  boolean getInteriorTrimTracking(){
        return  getDataManager().getInteriorTrimTracking();
    }

    public void setSeatTracking(boolean seatTracking){
        getDataManager().setSeatTracking(seatTracking);
    }

    public boolean getSeatTracking(){
        return  getDataManager().getSeatTracking();
    }

    public void  setVanityMirrorTracking(boolean vanityMirrorTracking){
        getDataManager().setVanityMirrorTracking(vanityMirrorTracking);
    }

    public  boolean getVanityMirrorTracking(){
       return getDataManager().getVanityMirrorTracking();
    }

















    public void setImageArraySaved(boolean imageArraySaved ){
        getDataManager().setImageArraySaved(imageArraySaved);
    }

    public  boolean getImageArraySaved(){
        return  getDataManager().getImageArraySaved();
    }





    public boolean checkExterior(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getHoodTrackingStatus()&& getDoorTrackingStatus()&&getFenderTracking()&&getFrontBumperTracking()&&getFuelDoorTracking()&&getPaintTracking()&&getRearBumperTracking()&&getRearTracking()&&getRoofTracking()&&getTrimTracking()&&getTrunkTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }
      return false;
    }


    public boolean checkGlass(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getMirrorTracking() && getRearWindowTracking()&&getWindowTracking()&&getWindShieldTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }


    public boolean checkTires(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getTiresTracking()&&getWheelTracking()&&getSpareTireTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }


    public boolean checkUnderBody(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getFrameTracking()&&getExhaustTracking()&&getTransmissionTracking()&&getDriveAxleTracking()&&getSuspensionTracking()&&getBrakeSystemTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }


    public boolean checkUnderHood(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getEngineCompTracking()&&getBatteryTracking()&&getOilTracking()&& getFluidTracking()&&getWiringTracking()&&getBeltTracking()&&getHosesTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }


   public  boolean checkRoadTest(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getStartingTracking() && getIdlingTracking() && getEnginePerfTracking() && getAccelerationTracking() && getTransmissionShiftTracking() && getSteeringTracking() && getBrakingTracking() && getSuspensionPerfTracking() ){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }

        return  false;
   }


    public  boolean checkElectric(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getPowerLockTracking() && getPowerSeatTracking() && getPowerSteeringTracking() && getPowerWindowTracking() && getPowerMiirorTracking() && getAudioSystemTracking() && getComputerTracking() && getHeadLightTracking() && getTailLightTracking() && getSignalLightTracking() && getBrakeLightTracking() && getParkingLightTracking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }

        return  false;
    }


    public  boolean checkInterior(ImageView imageView){
        if (checkIfIntakeVehicleReportIsEmpty()){

        }else {
            if (getSeatTracking() && getHeadLinerTracking() && getCarpetTracking() && getDoorPanelTracking() && getGloveBoxTracking() && getVanityMirrorTracking() && getInteriorTrimTracking() && getdashBoardTracking() && getDashGuageTracking() && getAirCondTraking() && getHeaterTracking() && getDefrosterTrcking()){
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        }

        return  false;
    }












    public void checkifExteriorComplete(ImageView imageView) {
        if (checkIfIntakeVehicleReportIsEmpty()) {
            imageView.setVisibility(View.GONE);
        } else {
            List<VehicleCollection> oldArray = getIntakeVehicleReport();
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getPart().equals("hood") && oldArray.get(i).getPart().equals("front bumper") && oldArray.get(i).getPart().equals("fenders'") && oldArray.get(i).getPart().equals("doors") && oldArray.get(i).getPart().equals("roof'")
                        | oldArray.get(i).getPart().equals("rear") || oldArray.get(i).getPart().equals("rear bumper") || oldArray.get(i).getPart().equals("trunk") || oldArray.get(i).getPart().equals("trim") || oldArray.get(i).getPart().equals("fuel door") || oldArray.get(i).getPart().equals("paint")) {

                        imageView.setVisibility(View.VISIBLE);

                    break;
                }

            }
        }
    }




//    for(let k = 0; k<parts.length; k++){
//        for(let j = 0; j<result.length; j++){
//            if(result[j].class == "exterior"){
//                if(parts[k] == result[j].part){
//                    // Set the remark here O(N)
//
//                    console.log(result[j].remark)
//                }
//            }
//        }

    public boolean checkIfTireComplete(ImageView imageView){
        String[] tire = {"wheels","tires","spare tyre"};
        List<String>list = Arrays.asList(tire);
        if (checkIfIntakeVehicleReportIsEmpty()){
        }else {
            List<VehicleCollection> oldArray = getIntakeVehicleReport();
            for (VehicleCollection collection :oldArray){
                if (!collection.getPart().equals("tires") || !collection.getPart().equals("wheels") || !collection.getPart().equals("spare tyre")){
                    imageView.setVisibility(View.GONE);
                    return false;


                }else {
                    imageView.setVisibility(View.VISIBLE);
                    return  true;

                }
            }

            }
        return false;
        }




        public void checkIfUnderBodyComplete (ImageView imageView){
            if (checkIfIntakeVehicleReportIsEmpty()) {
                imageView.setVisibility(View.GONE);
            } else {
                List<VehicleCollection> oldArray = getIntakeVehicleReport();
                for (int i = 0; i < oldArray.size(); i++) {
                    if (oldArray.get(i).getPart().equals("frame") || oldArray.get(i).getPart().equals("exhaust") || oldArray.get(i).getPart().equals("transmission") || oldArray.get(i).getPart().equals("drive axle") || oldArray.get(i).getPart().equals("suspension") || oldArray.get(i).getPart().equals("brake system")) {
                        if (imageView.getVisibility() == View.GONE) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                        break;
                    }

                }
            }

        }

        public void checkUnderHoodComplete (ImageView imageView){
            if (checkIfIntakeVehicleReportIsEmpty()) {
                imageView.setVisibility(View.GONE);
            } else {
                List<VehicleCollection> oldArray = getIntakeVehicleReport();
                for (int i = 0; i < oldArray.size(); i++) {
                    if (oldArray.get(i).getPart().equals("engine compartment") || oldArray.get(i).getPart().equals("battery") || oldArray.get(i).getPart().equals("oil") || oldArray.get(i).getPart().equals("fluids") || oldArray.get(i).getPart().equals("wiring") || oldArray.get(i).getPart().equals("belts") || oldArray.get(i).getPart().equals("hoses")) {
                        if (imageView.getVisibility() == View.GONE) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                        break;
                    }

                }
            }

        }

        public void checkInteriorComplete (ImageView imageView){
            if (checkIfIntakeVehicleReportIsEmpty()) {
                imageView.setVisibility(View.GONE);
            } else {
                List<VehicleCollection> oldArray = getIntakeVehicleReport();
                for (int i = 0; i < oldArray.size(); i++) {
                    if (oldArray.get(i).getPart().equals("seats") || oldArray.get(i).getPart().equals("headliner") || oldArray.get(i).getPart().equals("carpets") || oldArray.get(i).getPart().equals("door panels") || oldArray.get(i).getPart().equals("glove box")
                            || oldArray.get(i).getPart().equals("vanity mirror") || oldArray.get(i).getPart().equals("interior trim") || oldArray.get(i).getPart().equals("dashboard") || oldArray.get(i).getPart().equals("dash guages") || oldArray.get(i).getPart().equals("air conditioner") || oldArray.get(i).getPart().equals("heater") || oldArray.get(i).getPart().equals("defroster")) {
                        if (imageView.getVisibility() == View.GONE) {
                            imageView.setVisibility(View.VISIBLE);
                        }
                        break;
                    }

                }
            }

        }


    }




