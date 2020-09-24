package com.enyata.android.mvvm_java.ui.createReport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.ContactsContract;
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
import com.enyata.android.mvvm_java.data.model.api.myData.ImageData;
import com.enyata.android.mvvm_java.data.model.api.myData.ImageDataArray;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CheckIntakeRequest;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.GetAcceptanceResultRequest;
import com.enyata.android.mvvm_java.data.model.api.request.IntakeRuleRequest;
import com.enyata.android.mvvm_java.data.model.api.request.RegNumberCheckRequest;
import com.enyata.android.mvvm_java.data.model.api.request.Vehicle;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.glide.GlideApp;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreateReportViewModel extends BaseViewModel<CreateReportNavigator> {
    Context context;

    public CreateReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

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

    public void onGetIntakeResult(){
        getNavigator().onGetIntakeResult();
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

//    public void checkIntakeRule(IntakeRuleRequest.Request request){
//        getNavigator().onStarting();
//        getCompositeDisposable().add(getDataManager()
//                .checkIntakeRule(request)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(response -> {
//                    setIsLoading(false);
//                    getNavigator().validateData(response);
//                }, throwable -> {
//                    setIsLoading(false);
//                    getNavigator().handleValidateError(throwable);
//
//                }));
//    }


    public void checkIntakeReport(CheckIntakeRequest.Request request){
        getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                .checkIntakeReport(request)
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

    public ImageDataArray isVehicleSaved(VehicleCollection vehicle, RadioButton good, RadioButton fair, RadioButton bad, Fragment fragment, HashMap<String, ImageView> myImage, ImageDataArray imageDataArray) {
        if (checkIfIntakeVehicleReportIsEmpty()) {
        } else {
            String status;
            String  firstUrl;
            String secondUrl;
            String thirdUrl;

            List<VehicleCollection> part = getIntakeVehicleReport();
            for (int k = 0; k < part.size(); k++) {
                if (part.get(k).getPart().equals(vehicle.getPart())) {
                    part.get(k).getImageUrl();
                    part.get(k).getRemark();
                    part.get(k).getSection();

                    if (part.get(k).getRemark().equals("good")){
                        good.setChecked(true);
                        status = "good";
                    }else if (part.get(k).getRemark().equals("fair")){
                        fair.setChecked(true);
                        status = "fair";
                    }else {
                        bad.setChecked(true);
                        status = "poor";
                    }
                    List<String>images = part.get(k).getImageUrl();
                    if (images.isEmpty()){
                        Log.i("It's empty","It's empty");
                        myImage.get("image1").setImageResource(0);
                        myImage.get("image2").setImageResource(0);
                        myImage.get("image3").setImageResource(0);
//                        array[0].setImageResource(0);
//                        array[1].setImageResource(0);
//                        array[2].setImageResource(0);
                        imageDataArray.addStatus("status", status);
                    }else {
                        if (images.size()== 3){
                            GlideApp.with(fragment).load(images.get(0)).into(myImage.get("image1"));
                            GlideApp.with(fragment).load(images.get(1)).into(myImage.get("image2"));
                            GlideApp.with(fragment).load(images.get(2)).into(myImage.get("image3"));
                            imageDataArray.addUrl("image0",images.get(0));
                            imageDataArray.addUrl("image1", images.get(1));
                            imageDataArray.addUrl("image2",images.get(2));
                            imageDataArray.addStatus("status", status);
                            Log.i("ImageDataArrayWhen3", String.valueOf(imageDataArray));
                            return imageDataArray;


                        }else if (images.size() == 2){
                            GlideApp.with(fragment).load(images.get(0)).into(myImage.get("image1"));
                            GlideApp.with(fragment).load(images.get(1)).into(myImage.get("image2"));
                            imageDataArray.addUrl("image0",images.get(0));
                          imageDataArray.addUrl("image1", images.get(1));
//                          imageDataArray.addUrl("image2",images.get(2));
                           imageDataArray.addStatus("status", status);
                            Log.i("ImageDataArrayWhen2", String.valueOf(imageDataArray));
                            return imageDataArray;
                        }else  if (images.size() == 1){
                            GlideApp.with(fragment).load(images.get(0)).into(myImage.get("image1"));
                            imageDataArray.addUrl("image0",images.get(0));
                             imageDataArray.addStatus("status", status);
                            Log.i("ImageDataArrayWhen1", String.valueOf(imageDataArray));
                            return imageDataArray;
//
                        }


//                        if(images.size() == 3){
//                            GlideApp.with(fragment).load(images.get(0)).into(array.get("imahe+dkvknvkffjfj"));
//        //                  GlideApp.with(fragment).load(images.get(1)).into(secondImage);
//        //                  GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//                        }



//                    GlideApp.with(fragment).load(images.get(0)).into(firstImage);
//                    GlideApp.with(fragment).load(images.get(1)).into(secondImage);
//                    GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//
//                    imageDataArray.addUrl("image0",images.get(0));
//                    imageDataArray.addUrl("image1", images.get(1));
//                    imageDataArray.addUrl("image2",images.get(2));
//                    imageDataArray.addStatus("status", status);
                    }

                    Log.i("Part", String.valueOf(part.get(k).getImageUrl()));
                    Log.i("Image", String.valueOf(part.get(k).getRemark()));
                    Log.i("REmark", vehicle.getRemark());
                    return imageDataArray;
                }
            }
            return imageDataArray;
        }
        return imageDataArray;




    }




    public ImageDataArray isVehicleSave(VehicleCollection vehicle, RadioButton good, RadioButton fair, RadioButton bad, Fragment fragment, ImageView firstImage, ImageView secondImage, ImageView thirdImage, ImageDataArray imageDataArray) {
        if (checkIfIntakeVehicleReportIsEmpty()) {
        } else {
            String status;
            String  firstUrl;
            String secondUrl;
            String thirdUrl;

            List<VehicleCollection> part = getIntakeVehicleReport();
            for (int k = 0; k < part.size(); k++) {
                if (part.get(k).getPart().equals(vehicle.getPart())) {
                    part.get(k).getImageUrl();
                    part.get(k).getRemark();
                    part.get(k).getSection();

                    if (part.get(k).getRemark().equals("good")){
                        good.setChecked(true);
                        status = "good";
                    }else if (part.get(k).getRemark().equals("fair")){
                        fair.setChecked(true);
                        status = "fair";
                    }else {
                        bad.setChecked(true);
                        status = "poor";
                    }
                    List<String>images = part.get(k).getImageUrl();
                    if (images.isEmpty()){
                        firstImage.setImageResource(0);
                        secondImage.setImageResource(0);
                        thirdImage.setImageResource(0);
                        imageDataArray.addStatus("status", status);
                    }
//                    GlideApp.with(fragment).load(images.get(0)).into(firstImage);
//                    GlideApp.with(fragment).load(images.get(1)).into(secondImage);
//                    GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//
//                    imageDataArray.addUrl("image0",images.get(0));
//                    imageDataArray.addUrl("image1", images.get(1));
//                    imageDataArray.addUrl("image2",images.get(2));
//                    imageDataArray.addStatus("status", status);

                    Log.i("Part", String.valueOf(part.get(k).getImageUrl()));
                    Log.i("Image", String.valueOf(part.get(k).getRemark()));
                    Log.i("REmark", vehicle.getRemark());
                    return imageDataArray;
                }
            }
            return imageDataArray;
        }
        return imageDataArray;




    }

//    public ImageDataArray isVehicleSave(VehicleCollection vehicle, RadioButton good, RadioButton fair, RadioButton bad, Fragment fragment, ImageView firstImage, ImageView secondImage, ImageView thirdImage, ImageDataArray imageDataArray) {
//        if (checkIfIntakeVehicleReportIsEmpty()) {
//        } else {
//            String status;
//            String  firstUrl;
//            String secondUrl;
//            String thirdUrl;
//
//            List<VehicleCollection> part = getIntakeVehicleReport();
//            for (int k = 0; k < part.size(); k++) {
//                if (part.get(k).getPart().equals(vehicle.getPart())) {
//                    part.get(k).getImageUrl();
//                    part.get(k).getRemark();
//                    part.get(k).getSection();
//
//                    if (part.get(k).getRemark().equals("good")){
//                       good.setChecked(true);
//                       status = "good";
//                    }else if (part.get(k).getRemark().equals("fair")){
//                        fair.setChecked(true);
//                        status = "fair";
//                    }else {
//                        bad.setChecked(true);
//                        status = "poor";
//                    }
//
//                    List<String>images = part.get(k).getImageUrl();
////                    if (images.isEmpty()){
////                        Log.i("Nothing","Array is empty");
////                        firstImage.setImageResource(0);
////                        secondImage.setImageResource(0);
////                       thirdImage.setImageResource(0);
////                       imageDataArray.addStatus("status", status);
////
////                    }
//                        GlideApp.with(fragment).load(images.get(0)).into(firstImage);
//                        GlideApp.with(fragment).load(images.get(1)).into(secondImage);
//                        GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//                        imageDataArray.addUrl("image0",images.get(0));
//                        imageDataArray.addUrl("image1", images.get(1));
//                        imageDataArray.addUrl("image2",images.get(2));
//                        imageDataArray.addStatus("status", status);
//
//
////                        GlideApp.with(fragment).load(images.get(2)).into(firstImage);
////                        secondImage.setImageResource(0);
////                        thirdImage.setImageResource(0);
////                        imageDataArray.addStatus("status",status);
////                        imageDataArray.addUrl("image0",images.get(0));
//
//
////                    if (images.size() == 0){
////                       firstImage.setImageResource(0);
////                       secondImage.setImageResource(0);
////                       thirdImage.setImageResource(0);
////                        imageDataArray.addStatus("status", status);
////
////                    } else if (images.size() == 1){
////                        GlideApp.with(fragment).load(images.get(0)).into(firstImage);
////                        imageDataArray.addUrl("image0",images.get(0));
////                        imageDataArray.addStatus("status", status);
////                        secondImage.setImageResource(0);
////                        thirdImage.setImageResource(0);
////                    }else if (images.size()== 2){
////                        GlideApp.with(fragment).load(images.get(0)).into(firstImage);
////                        GlideApp.with(fragment).load(images.get(1)).into(secondImage);
////                        imageDataArray.addUrl("image0",images.get(0));
////                        imageDataArray.addUrl("image1", images.get(1));
////                        imageDataArray.addStatus("status", status);
//////                        GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//////                        imageDataArray.addUrl("image0",images.get(0));
//////                        imageDataArray.addUrl("image1", images.get(1));
//////                        imageDataArray.addUrl("image2",images.get(2));
//////                        imageDataArray.addStatus("status", status);
////
////                    }else if (images.size() == 3){
////                        GlideApp.with(fragment).load(images.get(0)).into(firstImage);
////                        GlideApp.with(fragment).load(images.get(1)).into(secondImage);
////                        GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
////                        imageDataArray.addUrl("image0",images.get(0));
////                        imageDataArray.addUrl("image1", images.get(1));
////                        imageDataArray.addUrl("image2",images.get(2));
////                        imageDataArray.addStatus("status", status);
////
////                    }
//
//                    Log.i("Part", String.valueOf(part.get(k).getImageUrl()));
//                    Log.i("Image", String.valueOf(part.get(k).getRemark()));
//                    Log.i("REmark", vehicle.getRemark());
//                    Log.i("BIGGERArray",String.valueOf(getIntakeVehicleReport()));
//                    return imageDataArray;
//
//                }
//            }
//            return imageDataArray;
//        }
//        return imageDataArray;
//
//
//
//
//    }

//     GlideApp.with(fragment).load(images.get(0)).into(firstImage);
//                        GlideApp.with(fragment).load(images.get(1)).into(secondImage);
//                        GlideApp.with(fragment).load(images.get(2)).into(thirdImage);
//                        imageDataArray.addUrl("image0",images.get(0));
//                        imageDataArray.addUrl("image1", images.get(1));
//                        imageDataArray.addUrl("image2",images.get(2));
//                        imageDataArray.addStatus("status", status);




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
                    oldArray.get(i).setSection(vehiclePart.getSection());
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
        return getDataManager().getPowerWindowTracking();
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

    public void onClear(){
        getNavigator().onClear();
    }

    public void deleteHoodTracking(boolean hoodTrackingStatus){ getDataManager().deleteHoodTracking(hoodTrackingStatus); }

    public void deleteDoorTrackingStatus(boolean doorTrackingStatus){ getDataManager().deleteDoorTracking(doorTrackingStatus); }

    public void deleteFenderTracking(boolean fenderTracking){getDataManager().deleteFenderTracking(fenderTracking);}

    public void deleteFrontBumperTracking(boolean fontBumperTracking){getDataManager().deleteFrontBumperTracking(fontBumperTracking);}

    public void deleteFuelDoorTracking(boolean fuelDoorTracking){getDataManager().deleteFuelDoorTracking(fuelDoorTracking);}

    public void deletePaintTracking(boolean paintTracking){ getDataManager().deletePaintTracking(paintTracking); }

    public void deleteRearBumperTracking(boolean rearBumperTracking){ getDataManager().deleteRearBumperTracking(rearBumperTracking); }

    public void deleteRearTracking(boolean rearTracking){ getDataManager().deleteRearTracking(rearTracking); }

    public void  deleteRoofTracking(boolean roofTracking){ getDataManager().deleteRoofTracking(roofTracking); }

    public  void  deleteTrimTracking(boolean trimTracking){ getDataManager().deleteTrimTracking(trimTracking); }

    public void deleteTrunkTracking(boolean trunkTracking) { getDataManager().deleteTrunkTracking(trunkTracking); }

    public void deleteMirrorTracking(boolean mirrorTracking){ getDataManager().deleteMirrorTracking(mirrorTracking); }

    public void deleteRearWindowTracking(boolean rearWindowTracking){ getDataManager().deleteRearWindowTracking(rearWindowTracking); }

    public void  deleteWindowTracking(boolean windowTracking){ getDataManager().deleteWindowTracking(windowTracking); }

    public void  deleteWinshieldTracking(boolean winshieldTracking){ getDataManager().deleteWindShieldTracking(winshieldTracking); }

    public void deleteSpareTireTracking(boolean spareTireTracking){ getDataManager().deleteSpareTireTracking(spareTireTracking); }

    public void deleteTiresTracking(boolean tiresTracking){ getDataManager().deleteTireTracking(tiresTracking); }

    public void deleteWheelTracking(boolean wheelTracking){ getDataManager().deleteWheelTracking(wheelTracking); }

    public void deleteBrakeSystemTracking(boolean brakeSystemTracking){ getDataManager().deleteBrakeSystemTracking(brakeSystemTracking); }

    public  void  deleteDriveAxleTracking(boolean driveAxleTracking){ getDataManager().deleteDriveAxleTracking(driveAxleTracking); }

    public void  deleteExhaustTracking(boolean exhaustTracking){ getDataManager().deleteExhaustTracking(exhaustTracking); }

    public void deleteFrameTracking(boolean frameTracking){ getDataManager().deleteFrameTracking(frameTracking); }

    public void  deleteSuspensionTracking(boolean suspensionTracking){ getDataManager().deleteSuspensionTracking(suspensionTracking); }

    public void deleteTransmissionTracking(boolean transmissionTracking){ getDataManager().deleteTransmissionTracking(transmissionTracking); }

    public void deleteBatteryTracking(boolean batteryTracking){ getDataManager().deleteBatteryTracking(batteryTracking); }

    public void deleteBeltTracking(boolean beltTracking){ getDataManager().deleteBeltTracking(beltTracking); }

    public void  deleteEngineCompTracking(boolean engineComp){ getDataManager().deleteEngineCompTracking(engineComp); }

    public  void  deleteFluidTracking(boolean fluidTracking){ getDataManager().deleteFluidTracking(fluidTracking); }

    public  void deleteHosesTracking(boolean hosesTracking){ getDataManager().deleteHosesTracking(hosesTracking); }

    public void deleteOilTracking(boolean oilTracking){ getDataManager().deleteOilTracking(oilTracking); }

    public void deleteWiringTracking(boolean wiringTracking){ getDataManager().deleteWiringTracking(wiringTracking); }

    public void deleteAccelerationTracking(boolean accelerationTracking){ getDataManager().deleteAccelerationTracking(accelerationTracking); }

    public  void deleteBrakingTracking(boolean brakingTracking){ getDataManager().deleteBrakingTraking(brakingTracking); }

    public void  deleteEnginePerfTracking(boolean enginePerfTracking){ getDataManager().deleteEnginePerfTracking(enginePerfTracking); }

    public  void  deleteIdlingTracking(boolean idlingTracking){ getDataManager().deleteIdlingTracking(idlingTracking); }

    public  void  deleteStartingTracking(boolean startingTracking){ getDataManager().deleteStartingTracking(startingTracking); }

    public  void  deleteSteeringTracking(boolean steeringTracking){ getDataManager().deleteSteeringTracking(steeringTracking); }

    public  void  deleteSuspensionPerfTracking(boolean suspensionPerfTracking){ getDataManager().deleteSuspensionPerfTracking(suspensionPerfTracking); }

    public  void  deleteTransmissionShiftTracking(boolean transmissionShiftTracking){ getDataManager().deleteTransmissionShiftTracking(transmissionShiftTracking); }

    public void  deleteAudioSystemTracking(boolean audioSystemTracking){ getDataManager().deleteAudioSystemTracking(audioSystemTracking); }

    public void  deleteBrakelightTracking(boolean brakelightTracking){ getDataManager().deleteBrakeLightTracking(brakelightTracking); }

    public void deleteComputerTracking(boolean computerTracking){ getDataManager().deleteComputerTracking(computerTracking); }

    public  void deleteHeadLightTracking(boolean headLightTracking){ getDataManager().deleteHeadlightTracking(headLightTracking); }

    public  void  deleteParkingLightTracking(boolean parkingLightTracking){ getDataManager().deleteParkingLightTracking(parkingLightTracking); }

    public  void  deletePowerLockTracking(boolean powerLockTracking){ getDataManager().deletePowerLockTracking(powerLockTracking); }

    public void  deletePowerMirrorTracking(boolean powerMirrorTracking){ getDataManager().deletePowerMirrorTracking(powerMirrorTracking); }

    public  void  deletePowerSeatTracking(boolean powerSeatTracking){ getDataManager().deletePowerSeatTracking(powerSeatTracking); }

    public void  deletePowerSteeringTracking(boolean powerSteeringTracking){ getDataManager().deletePowerSteeringTracking(powerSteeringTracking); }

    public void deletePowerWindowTracking(boolean powerWindowTracking){ getDataManager().deletePowerWindowTracking(powerWindowTracking); }

    public void  deleteSignalLightTracking(boolean signalLightTracking){ getDataManager().deleteSignalLightTracking(signalLightTracking); }

    public void  deleteTailLighTracking(boolean tailLighTracking){ getDataManager().deleteTailLightTracking(tailLighTracking); }

    public void deleteAircondTracking(boolean aircondTracking){ getDataManager().deleteAirCondTracking(aircondTracking); }

    public  void deleteCarpetTracking(boolean carpetTracking){ getDataManager().deleteCarpetTracking(carpetTracking); }

    public void  deleteDashBoardTracking(boolean dashBoardTracking){ getDataManager().deleteDashBoardTracking(dashBoardTracking); }

    public void  deleteDashGuageTracking(boolean dashGuageTracking){ getDataManager().deleteDashGuagesTracking(dashGuageTracking); }

    public  void  deleteDefrosterTracking(boolean defrosterTracking){ getDataManager().deleteDefrosterTracking(defrosterTracking); }

    public void deleteDoorPanelTracking(boolean doorPanelTracking){ getDataManager().deleteDoorPanelTracking(doorPanelTracking); }

    public void  deleteGloveBoxTracking(boolean gloveBoxTracking){ getDataManager().deleteGloveBoxTracking(gloveBoxTracking); }

    public void deleteHeadLinerTracking(boolean headLinerTracking){ getDataManager().deleteHeadLinerTracking(headLinerTracking); }

    public void  deleteHeaterTracking(boolean heaterTracking){ getDataManager().deleteHeaterTracking(heaterTracking); }

    public void deleteInteriorTrimTracking(boolean interiorTrimTracking){ getDataManager().deleteInteriorTrimTracking(interiorTrimTracking); }

    public void deleteSeatTracking(boolean seatTracking){ getDataManager().deleteSeatTracking(seatTracking); }

    public void  deleteVanityMirrorTracking(boolean vanityMirrorTracking){ getDataManager().deleteVanityMirror(vanityMirrorTracking); }

    public void deleteIntakeVehicleReportArray(List<VehicleCollection>vehicleCollections){ getDataManager().deleteIntakeReport(vehicleCollections); }









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
            if (getPowerLockTracking() && getPowerSeatTracking() && getPowerSteeringTracking() && getPowerWindowTracking() && getPowerMiirorTracking() && getAudioSystemTracking() && getComputerTracking() && getHeadLightTracking() && getTailLightTracking() && getSignalLightTracking()){
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



    public void getAcceptanceResult(GetAcceptanceResultRequest request) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();
        disposable.add(
                mAPIService.getAcceptanceResult(getDataManager().getAccessToken(), request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onAcceptanceResultResponse(response);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().onAcceptanceResultError(throwable);

                        }));
    }

    public  void setIntakeAcceptanceValue(String intakeAcceptanceValue){
        getDataManager().setIntakeAcceptanceValue(intakeAcceptanceValue);
    }



    public void onDispose(){
        onCleared();
}


}




