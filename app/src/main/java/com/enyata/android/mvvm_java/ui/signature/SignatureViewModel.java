package com.enyata.android.mvvm_java.ui.signature;

import android.content.Intent;
import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.CreateReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MonthlyReportRequest;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;
import com.enyata.android.mvvm_java.ui.response.ResponseActivity;
import com.enyata.android.mvvm_java.ui.response.failedResponse.FailedActivity;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.net.PortUnreachableException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignatureViewModel extends BaseViewModel<SignatureNavigator> {
    public SignatureViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

    public void onSubmit(){
        getNavigator().onSubmit();
    }

    public void onSaveSupplierSign(){getNavigator().onSaveSupplierSign();}

    public void onClearSupplierSign(){getNavigator().onClearSupplierSign();}

    public void onSaveInspectorSign(){getNavigator().onSaveInspectorSign();}

    public void onClearInspectorSign(){getNavigator().onClearInspectorSign();}

    public String getCarModel() {
        return getDataManager().getCarModel();
    }

    public String getCarMake() {
        return getDataManager().getCarMake();
    }

    public String getCarColor() {
        return getDataManager().getCarColor();
    }

    public String getMilage() {
        return getDataManager().getCurrentMilege();
    }

    public String getregNo() {
        return getDataManager().getRegistrationNo();
    }

    public String getVin() {
        return getDataManager().getVin();
    }

    public String getCarYear() {
        return getDataManager().getCarYear();
    }

    public String getIntakeFinalComment(){return  getDataManager().getIntakeFinalComment();}

    public  String getIntakeFinalStatus(){return  getDataManager().getIntakeFinalStatus();}

    public boolean getVehincleInfoStatus(){
        return getDataManager().getVehicleInfo();
    }



    public boolean getHoodTrackingStatus(){
        return getDataManager().getHoodTrackingStatus();
    }

    public void deleteHoodTracking(boolean hoodTrackingStatus){ getDataManager().deleteHoodTracking(hoodTrackingStatus); }



    public boolean getDoorTrackingStatus(){
        return getDataManager().getDoorTrackingStatus();
    }

    public void deleteDoorTrackingStatus(boolean doorTrackingStatus){ getDataManager().deleteDoorTracking(doorTrackingStatus); }


    public void deleteFenderTracking(boolean fenderTracking){getDataManager().deleteFenderTracking(fenderTracking);}

    public boolean getFenderTracking(){return getDataManager().getFenderTraking();}



    public void deleteFrontBumperTracking(boolean fontBumperTracking){getDataManager().deleteFrontBumperTracking(fontBumperTracking);}

    public boolean getFrontBumperTracking(){return getDataManager().getFrontBumberTracking();}



    public void deleteFuelDoorTracking(boolean fuelDoorTracking){getDataManager().deleteFuelDoorTracking(fuelDoorTracking);}

    public boolean getFuelDoorTracking(){
        return getDataManager().getFuelDoorTracking();
    }





    public void deletePaintTracking(boolean paintTracking){ getDataManager().deletePaintTracking(paintTracking); }

    public boolean getPaintTracking(){
        return  getDataManager().getPaintTracking();
    }




    public void deleteRearBumperTracking(boolean rearBumperTracking){ getDataManager().deleteRearBumperTracking(rearBumperTracking); }

    public boolean getRearBumperTracking(){
        return getDataManager().getRearBumperTracking();
    }



    public void deleteRearTracking(boolean rearTracking){ getDataManager().deleteRearTracking(rearTracking); }

    public boolean getRearTracking(){
        return  getDataManager().getRearTracking();
    }




    public void  deleteRoofTracking(boolean roofTracking){ getDataManager().deleteRoofTracking(roofTracking); }

    public boolean getRoofTracking(){
        return getDataManager().getRoofTracking();
    }




    public  void  deleteTrimTracking(boolean trimTracking){
        getDataManager().deleteTrimTracking(trimTracking);
    }

    public boolean getTrimTracking() {
        return getDataManager().getTrimTracking();
    }





    public void deleteTrunkTracking(boolean trunkTracking) {
        getDataManager().deleteTrunkTracking(trunkTracking);
    }

    public boolean getTrunkTracking(){
        return getDataManager().getTrunkTracking();
    }





    public void deleteMirrorTracking(boolean mirrorTracking){
        getDataManager().deleteMirrorTracking(mirrorTracking);
    }

    public boolean getMirrorTracking(){return  getDataManager().getMirrorTracking();}




    public void deleteRearWindowTracking(boolean rearWindowTracking){
        getDataManager().deleteRearWindowTracking(rearWindowTracking);
    }

    public  boolean getRearWindowTracking(){return  getDataManager().getRearWindowTracking();}




    public void  deleteWindowTracking(boolean windowTracking){
        getDataManager().deleteWindowTracking(windowTracking);
    }

    public boolean getWindowTracking(){
        return  getDataManager().getWindowTracking();
    }



    public void  deleteWinshieldTracking(boolean winshieldTracking){
        getDataManager().deleteWindShieldTracking(winshieldTracking);
    }

    public boolean getWindShieldTracking(){
        return  getDataManager().getWindshieldTracking();
    }




    public void deleteSpareTireTracking(boolean spareTireTracking){
        getDataManager().deleteSpareTireTracking(spareTireTracking);
    }

    public boolean getSpareTireTracking(){
        return getDataManager().getSpareTireTraking();
    }






    public void deleteTiresTracking(boolean tiresTracking){
        getDataManager().deleteTireTracking(tiresTracking);
    }

    public boolean getTiresTracking(){
        return   getDataManager().getTireTracking();
    }






    public void deleteWheelTracking(boolean wheelTracking){
        getDataManager().deleteWheelTracking(wheelTracking);
    }

    public boolean getWheelTracking(){
        return getDataManager().getWheelTracking();
    }






    public void deleteBrakeSystemTracking(boolean brakeSystemTracking){
        getDataManager().deleteBrakeSystemTracking(brakeSystemTracking);
    }

    public boolean getBrakeSystemTracking(){
        return  getDataManager().getBrakeSystemTracking();
    }




    public  void  deleteDriveAxleTracking(boolean driveAxleTracking){
        getDataManager().deleteDriveAxleTracking(driveAxleTracking);
    }

    public boolean getDriveAxleTracking(){
        return getDataManager().getDriveAxleTracking();
    }





    public void  deleteExhaustTracking(boolean exhaustTracking){
        getDataManager().deleteExhaustTracking(exhaustTracking);
    }

    public boolean getExhaustTracking(){
        return getDataManager().getExhaustTracking();
    }





    public void deleteFrameTracking(boolean frameTracking){
        getDataManager().deleteFrameTracking(frameTracking);

    }

    public boolean getFrameTracking(){
        return  getDataManager().getFrameTracking();
    }





    public void  deleteSuspensionTracking(boolean suspensionTracking){
        getDataManager().deleteSuspensionTracking(suspensionTracking);

    }

    public boolean getSuspensionTracking(){
        return  getDataManager().getSuspensionTracking();
    }





    public void deleteTransmissionTracking(boolean transmissionTracking){
        getDataManager().deleteTransmissionTracking(transmissionTracking);

    }


    public  boolean getTransmissionTracking(){
        return  getDataManager().getTransmissonTracking();
    }




    public void deleteBatteryTracking(boolean batteryTracking){
        getDataManager().deleteBatteryTracking(batteryTracking);
    }

    public  boolean getBatteryTracking(){
        return  getDataManager().getBatteryTracking();
    }



    public void deleteBeltTracking(boolean beltTracking){
        getDataManager().deleteBeltTracking(beltTracking);
    }

    public  boolean getBeltTracking(){
        return  getDataManager().getBeltTracking();
    }




    public void  deleteEngineCompTracking(boolean engineComp){
        getDataManager().deleteEngineCompTracking(engineComp);
    }

    public  boolean getEngineCompTracking(){
        return  getDataManager().getEngineCompTracking();
    }





    public  void  deleteFluidTracking(boolean fluidTracking){
        getDataManager().deleteFluidTracking(fluidTracking);
    }

    public  boolean getFluidTracking(){
        return  getDataManager().getFluidTracking();
    }





    public  void deleteHosesTracking(boolean hosesTracking){
        getDataManager().deleteHosesTracking(hosesTracking);
    }

    public  boolean getHosesTracking(){
        return  getDataManager().getHosesTracking();
    }





    public void deleteOilTracking(boolean oilTracking){
        getDataManager().deleteOilTracking(oilTracking);


    }

    public  boolean getOilTracking(){
        return  getDataManager().getOilTracking();
    }





    public void deleteWiringTracking(boolean wiringTracking){
        getDataManager().deleteWiringTracking(wiringTracking);
    }

    public boolean getWiringTracking(){
        return  getDataManager().getWiringTracking();
    }





    public void deleteAccelerationTracking(boolean accelerationTracking){
        getDataManager().deleteAccelerationTracking(accelerationTracking);
    }

    public  boolean getAccelerationTracking(){
        return  getDataManager().getAccelerationTracking();
    }




    public  void deleteBrakingTracking(boolean brakingTracking){
        getDataManager().deleteBrakingTraking(brakingTracking);
    }

    public  boolean getBrakingTracking(){
        return  getDataManager().getBrakingTracking();
    }




    public void  deleteEnginePerfTracking(boolean enginePerfTracking){
        getDataManager().deleteEnginePerfTracking(enginePerfTracking);
    }


    public  boolean getEnginePerfTracking(){
        return getDataManager().getEnginePerfTracking();
    }





    public  void  deleteIdlingTracking(boolean idlingTracking){
        getDataManager().deleteIdlingTracking(idlingTracking);
    }

    public boolean  getIdlingTracking(){
        return  getDataManager().getIdlingTracking();
    }





    public  void  deleteStartingTracking(boolean startingTracking){
        getDataManager().deleteStartingTracking(startingTracking);
    }


    public  boolean getStartingTracking(){
        return  getDataManager().getStartingTracking();
    }





    public  void  deleteSteeringTracking(boolean steeringTracking){
        getDataManager().deleteSteeringTracking(steeringTracking);
    }


    public  boolean getSteeringTracking(){
        return  getDataManager().getSteeringTracking();
    }





    public  void  deleteSuspensionPerfTracking(boolean suspensionPerfTracking){
        getDataManager().deleteSuspensionPerfTracking(suspensionPerfTracking);
    }


    public  boolean getSuspensionPerfTracking(){
        return  getDataManager().getSuspensionPerfTracking();
    }





    public  void  deleteTransmissionShiftTracking(boolean transmissionShiftTracking){
        getDataManager().deleteTransmissionShiftTracking(transmissionShiftTracking);
    }


    public  boolean getTransmissionShiftTracking(){
        return  getDataManager().getTransmissionShiftTracking();
    }



    public void  deleteAudioSystemTracking(boolean audioSystemTracking){
        getDataManager().deleteAudioSystemTracking(audioSystemTracking);
    }




    public  boolean getAudioSystemTracking(){
        return  getDataManager().getAudioSystemTracking();
    }





    public void  deleteBrakelightTracking(boolean brakelightTracking){
        getDataManager().deleteBrakeLightTracking(brakelightTracking);
    }

    public boolean getBrakeLightTracking(){
        return getDataManager().getBrakeLightTracking();
    }





    public void deleteComputerTracking(boolean computerTracking){
        getDataManager().deleteComputerTracking(computerTracking);
    }

    public  boolean getComputerTracking(){
        return  getDataManager().getComputerTracking();
    }





    public  void deleteHeadLightTracking(boolean headLightTracking){
        getDataManager().deleteHeadlightTracking(headLightTracking);
    }

    public  boolean getHeadLightTracking(){
        return getDataManager().getHeadLightTracking();
    }

    public  void  deleteParkingLightTracking(boolean parkingLightTracking){
        getDataManager().deleteParkingLightTracking(parkingLightTracking);
    }




    public  boolean getParkingLightTracking(){
        return  getDataManager().getParkingLightTracking();
    }





    public  void  deletePowerLockTracking(boolean powerLockTracking){
        getDataManager().deletePowerLockTracking(powerLockTracking);
    }

    public  boolean getPowerLockTracking(){
        return  getDataManager().getPowerLockTracking();
    }





    public void  deletePowerMirrorTracking(boolean powerMirrorTracking){
        getDataManager().deletePowerMirrorTracking(powerMirrorTracking);
    }

    public  boolean getPowerMiirorTracking(){
        return  getDataManager().getPowerMirrorTracking();
    }




    public  void  deletePowerSeatTracking(boolean powerSeatTracking){
        getDataManager().deletePowerSeatTracking(powerSeatTracking);
    }

    public  boolean getPowerSeatTracking(){
        return getDataManager().getPowerSeatTracking();
    }





    public void  deletePowerSteeringTracking(boolean powerSteeringTracking){
        getDataManager().deletePowerSteeringTracking(powerSteeringTracking);
    }

    public  boolean getPowerSteeringTracking(){return getDataManager().getPowerSteeringTracking();
    }




    public void deletePowerWindowTracking(boolean powerWindowTracking){
        getDataManager().deletePowerWindowTracking(powerWindowTracking);
    }

    public  boolean getPowerWindowTracking(){
        return getDataManager().getPoweWindowTracking();
    }





    public void  deleteSignalLightTracking(boolean signalLightTracking){
        getDataManager().deleteSignalLightTracking(signalLightTracking);
    }

    public  boolean getSignalLightTracking(){
        return  getDataManager().getSignalLightTracking();
    }




    public void  deleteTailLighTracking(boolean tailLighTracking){
        getDataManager().deleteTailLightTracking(tailLighTracking);
    }

    public  boolean getTailLightTracking(){
        return  getDataManager().getTailLightTracking();

    }

    public void deleteAircondTracking(boolean aircondTracking){
        getDataManager().deleteAirCondTracking(aircondTracking);
    }

    public  boolean getAirCondTraking(){
        return  getDataManager().getAircondTracking();
    }

    public  void deleteCarpetTracking(boolean carpetTracking){
        getDataManager().deleteCarpetTracking(carpetTracking);



    }

    public  boolean getCarpetTracking(){
        return  getDataManager().getCarpetTrackin();
    }





    public void  deleteDashBoardTracking(boolean dashBoardTracking){
        getDataManager().deleteDashBoardTracking(dashBoardTracking);
    }

    public  boolean getdashBoardTracking(){
        return   getDataManager().getDashboardTracking();
    }




    public void  deleteDashGuageTracking(boolean dashGuageTracking){
        getDataManager().deleteDashGuagesTracking(dashGuageTracking);
    }

    public  boolean getDashGuageTracking(){
        return  getDataManager().getDashGuagesTracking();
    }





    public  void  deleteDefrosterTracking(boolean defrosterTracking){
        getDataManager().deleteDefrosterTracking(defrosterTracking);
    }

    public boolean getDefrosterTrcking(){
        return  getDataManager().getDefrosterTracking();
    }




    public void deleteDoorPanelTracking(boolean doorPanelTracking){
        getDataManager().deleteDoorPanelTracking(doorPanelTracking);
    }

    public  boolean getDoorPanelTracking(){
        return  getDataManager().getDoorPanelTracking();
    }





    public void  deleteGloveBoxTracking(boolean gloveBoxTracking){
        getDataManager().deleteGloveBoxTracking(gloveBoxTracking);
    }

    public boolean getGloveBoxTracking(){
        return  getDataManager().getGloveBoxTracking();
    }





    public void deleteHeadLinerTracking(boolean headLinerTracking){
        getDataManager().deleteHeadLinerTracking(headLinerTracking);
    }

    public  boolean getHeadLinerTracking(){
        return  getDataManager().getHeadlinerTracking();
    }




    public void  deleteHeaterTracking(boolean heaterTracking){
        getDataManager().deleteHeaterTracking(heaterTracking);
    }

    public boolean getHeaterTracking(){return getDataManager().getHeaterTracking();}




    public void deleteInteriorTrimTracking(boolean interiorTrimTracking){
        getDataManager().deleteInteriorTrimTracking(interiorTrimTracking);
    }

    public  boolean getInteriorTrimTracking(){
        return  getDataManager().getInteriorTrimTracking();


    }

    public void deleteSeatTracking(boolean seatTracking){
        getDataManager().deleteSeatTracking(seatTracking);
    }

    public boolean getSeatTracking(){
        return  getDataManager().getSeatTracking();
    }




    public void  deleteVanityMirrorTracking(boolean vanityMirrorTracking){
        getDataManager().deleteVanityMirror(vanityMirrorTracking);
    }

    public  boolean getVanityMirrorTracking(){
        return getDataManager().getVanityMirrorTracking();
    }









    public List<VehicleCollection>vehincleReportArray(){
      return   getDataManager().getInTakeVehicleReport();
    }

    public  String getReportType(){
        return getDataManager().getReportType();
    }

    public  void deleteAll(){
        getDataManager().deleteAll();
    }

    public void deleteIntakeVehicleReportArray(List<VehicleCollection>delete){
        getDataManager().deleteIntakeReport(delete);
    }

    public void deleteCarYear(String carYear){
        getDataManager().deleteCarYear(carYear);
    }

    public void deleteCarModel(String carModel){
        getDataManager().deleteCarModel(carModel);
    }

    public void deleteCarMake(String carMake){
        getDataManager().deleteCarMake(carMake);
    }

    public void deleteCarColor(String carColor){
        getDataManager().deleteCarColor(carColor);
    }

    public void deleteMileage(String mileage){
        getDataManager().deleteCurrentMileage(mileage);
    }

    public void deleteVin(String vin){
        getDataManager().deleteVin(vin);
    }

    public void deleteRegNo(String regNo){
        getDataManager().deleteRegistrationNo(regNo);
    }

    public void deleteVehicleInfo(boolean vehicleInfo){
        getDataManager().deleteVehicleInfo(vehicleInfo);
    }

    public void deleteFinalComment(String comment){
        getDataManager().deleteIntakeFinalComment(comment);
    }

    public void deleteFinalStatus(String status){
        getDataManager().deleteIntakeFinalStatus(status);
    }

    public void deleteReportType(String reportType){
        getDataManager().deleteReportType(reportType);
    }




    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }

    public void createVehicleReport(CreateReportRequest request) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();

//        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
////        startActivity(intent);// RxJava

        disposable.add(
                mAPIService.savePost(getDataManager().getAccessToken(), request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onResponse(response);
//                            Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
//                            startActivity(i);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().handleError(throwable);

//                            Intent intent1 = new Intent(getApplicationContext(), FailedActivity.class);
//                            startActivity(intent1);
                        }));
    }


    public  void createMonthlyReport(MonthlyReportRequest request){
        getNavigator().onStarting();
        mAPIService =ApiUtils.getAPIService();
        disposable.add(
                mAPIService.createMonthlyReport(getDataManager().getAccessToken(),request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    getNavigator().onResponse(response);
                    Log.i("MONTHLY","RESPONSE SUCCESSFUL");
                },throwable -> {
                    getNavigator().handleError(throwable);
                    Log.i("ERROR","ERRROE");
                })
        );
    }
}
