package com.enyata.android.mvvm_java.ui.createReport;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePart;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class CreateReportViewModel extends BaseViewModel<CreateReportNavigator> {
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

    public void setFinalStatus(String status){getDataManager().setIntakeFinalStatus(status);}

    public void setFinalComment(String comment){getDataManager().setIntakeFinalComment(comment);}




    public boolean checkIfIntakeVehicleReportIsEmpty() {
        return getIntakeVehicleReport() == null;
    }

    public void setIntakeVehincleReport(List<VehicleCollection> vehiclePart) {
        getDataManager().setInTakeVehicleReport(vehiclePart);
    }

    public List<VehicleCollection> getIntakeVehicleReport() {
        return getDataManager().getInTakeVehicleReport();
    }

    public boolean contain(Object object) {
        if (getIntakeVehicleReport().contains(object)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfObjectExist() {
        return getIntakeVehicleReport() == null;
    }

    public void saveReportToLocalStorage(VehicleCollection vehiclePart) {
        if (checkIfIntakeVehicleReportIsEmpty()){
            List<VehicleCollection>newArray = new ArrayList<>();
            newArray.add(vehiclePart);
            setIntakeVehincleReport(newArray);
        }else {
            List<VehicleCollection>oldArray = getIntakeVehicleReport();
            Log.i("jjjjjj","Already");
            for (int i = 0; i<oldArray.size(); i++){
                if (oldArray.get(i).getPart().equals(vehiclePart.getPart())) {
                oldArray.get(i).setRemark(vehiclePart.getRemark());
                oldArray.get(i).setImageUrl(vehiclePart.getImageUrl());
                Log.i("part", vehiclePart.getPart());
                setIntakeVehincleReport(oldArray);
                Log.i("NEW NEW", String.valueOf(getIntakeVehicleReport()));
                break;
                }else {
                    if (getIntakeVehicleReport().contains(vehiclePart)){}
                    List<VehicleCollection>sameArray = getIntakeVehicleReport();
                    setIntakeVehincleReport(sameArray);

                }
            }

        }if (!getIntakeVehicleReport().contains(vehiclePart)){
            List<VehicleCollection>addArray = getIntakeVehicleReport();
            addArray.add(vehiclePart);
            setIntakeVehincleReport(addArray);
        }




//        VehicleCollection door = new VehicleCollection("door", result, status);
////        VehicleCollection fender = new VehicleCollection("fenders", result, status);
//
//        if (createReportViewModel.checkIfIntakeVehicleReportIsEmpty()){
//            List<VehicleCollection>newAray = new ArrayList<>();
//            newAray.add(door);
//            createReportViewModel.setIntakeVehincleReport(newAray);
//        }else {
//            List<VehicleCollection>requests = createReportViewModel.getIntakeVehicleReport();
//            Log.i("jjjjjj","Already");
//            for (int i = 0; i< requests.size();i++){
//
//                if (requests.get(i).getPart().equals(door.getPart())){
//                    requests.get(i).setRemark(door.getRemark());
//                    requests.get(i).setImageUrl(door.getImageUrl());
//                    Log.i("part", door.getPart());
//
//                    createReportViewModel.setIntakeVehincleReport(requests);
//                    Log.i("NEW NEW", String.valueOf(createReportViewModel.getIntakeVehicleReport()));
//                    break;
//                }else {
//                    if (createReportViewModel.getIntakeVehicleReport().contains(door)){
//                        List<VehicleCollection>oldArray = createReportViewModel.getIntakeVehicleReport();
//                        createReportViewModel.setIntakeVehincleReport(oldArray);
//                    }
//                }
//            }
//        }if (!createReportViewModel.getIntakeVehicleReport().contains(door)){
//            List<VehicleCollection> arrayList = createReportViewModel.getIntakeVehicleReport();
//            arrayList.add(door);
//            createReportViewModel.setIntakeVehincleReport(arrayList);
//        }

        Log.i("Big Array", String.valueOf(getIntakeVehicleReport()));

//        if (checkIfIntakeVehicleReportIsEmpty()) {
//            List<VehicleCollection> data = new ArrayList<>();
//            data.add(vehiclePart);
//            setIntakeVehincleReport(data);
//        } else {
//            List<VehicleCollection> data = new ArrayList<>(getIntakeVehicleReport());
//            Log.i("GGGGGG","FFFFFFF");
////            for (int k = 0; k < data.size(); k++) {
////                if (data.get(k).getPart().equals(vehiclePart.getPart())) {
////                    data.get(k).setImageUrl(vehiclePart.getImageUrl());
////                    data.get(k).setRemark(vehiclePart.getRemark());
////                    setIntakeVehincleReport(data);
////                    data.add(vehiclePart);
////                }else{
//////                    data.get(k).setRemark(vehiclePart.getRemark());
//////                    data.get(k).setImageUrl(vehiclePart.getImageUrl());
//////                    data.get(k).setPart(vehiclePart.getPart());
//////                    data.add(vehiclePart);
////                }
////            }
////
////            setIntakeVehincleReport(data);
//        }
//
//        Log.i("HOOD", String.valueOf(getIntakeVehicleReport()));
//
//        // if not empty check if the part is the same with the new one, then update the images and status
//        // if not the same then get whats in storage and append vehicle part to it;
//    }


    }

}
