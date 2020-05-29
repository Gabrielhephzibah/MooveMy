package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance;

import android.util.Log;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.myData.MaintenanceListData;
import com.enyata.android.mvvm_java.data.model.api.request.CreateMaintenanceReportRequest;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.data.remote.ApiService;
import com.enyata.android.mvvm_java.data.remote.ApiUtils;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MaintenanceViewModel extends BaseViewModel<MaintenanceNavigator> {

    public MaintenanceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private ApiService mAPIService;

    public void onBack(){
        getNavigator().onBack();
    }

  public void onEngineOil(){
        getNavigator().onEngineOil();
  }


  public   void onOilFilter(){
        getNavigator().onOilFilter();
  }
  public   void onAirFilter(){
        getNavigator().onAirFilter();
  }
    public void onPollenFilter(){
        getNavigator().onPollenFilter();
    }
   public void onBrakePad(){
        getNavigator().onBrakePad();
   }
  public   void onSuspensionSystem(){
        getNavigator().onSuspensionSystem();
  }
   public void onVariousBelt(){
        getNavigator().onVariousBelt();
   }
   public void  onSparkPlug(){
        getNavigator().onSparkPlug();
   }
   public void onFuelFilter(){
        getNavigator().onFuelFilter();
   }
   public void  onEngineCooling(){
        getNavigator().onEngineCooling();
   }
  public   void onWiperBlade(){
        getNavigator().onWiperBlade();
  }
  public   void onChargingSystem(){
        getNavigator().onChargingSystem();
  }
  public   void onPowerSteering(){
        getNavigator().onPowerSteering();
  }
   public void onTransmissionOil(){
        getNavigator().onTransmissionOil();
   }
 public    void onBrakeFluid(){
        getNavigator().onBrakeFluid();
 }
   public void onFuelSystem(){
        getNavigator().onFuelSystem();
   }
    public void onTireRotation(){
        getNavigator().onTireRotation();
    }
   public void  onAlignment(){
        getNavigator().onAlignment();
   }
   public void onAirFlow(){
        getNavigator().onAirFlow();
   }
   public void  onAirCondition(){
        getNavigator().onAirCondition();
   }
   public void onElectronic(){
        getNavigator().onElectronic();
   }
  public   void  onExhaust(){
        getNavigator().onExhaustPipe();
  }
   public void onTireInflation(){
       getNavigator().onTireInflation();
    }
   public void onWheel(){
        getNavigator().onWheel();
   }
    public void  addCurrentMileage(){
        getNavigator().onAddCurrentMileage();

    }
   public void  onTransmissionFilter(){
        getNavigator().onTransmissionFilter();
   }

   public  String getMooveId(){
        return  getDataManager().getMooveId();
   }

    public  String getCarYearMaint(){
        return  getDataManager().getCarYearMaint();
    }

    public  String getCarModelMaint(){
        return  getDataManager().getCarModelMaint();
    }

    public  String getCarMakeMaint(){
        return  getDataManager().getCarMakeMaint();
    }

    public  String getVehicleIdMaint(){return getDataManager().getVehicleIdMaint();}

    public  String getInitialmileage(){
        return  getDataManager().getInitialMileage();
    }


    public void getMaintenanceSchedule(MaintenanceScheduleRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMaintenanceSchedule(request)
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

    public void createMaintenanceReport(CreateMaintenanceReportRequest request) {
        mAPIService = ApiUtils.getAPIService();
        getNavigator().onStarting();
        disposable.add(
                mAPIService.createMaintenanceReport(getDataManager().getAccessToken(),request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(reportRequest -> {
                            getNavigator().maintenanceResponse(reportRequest);
                        },throwable -> {
                            getNavigator().handleMaintError(throwable);
                        }));
    }


    public void checkMaintenanceReport(String vehicleId){
        getNavigator().onStartingCheck();
        getCompositeDisposable().add(getDataManager()
                .checkMaintenanceReport(vehicleId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onCheckMaintenanceResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleCheckMaintenanceError(throwable);

                }));

    }




    public boolean checkIfMaintenanceReportIsEmpty() {
        return getMaintenanceReport() == null;
    }

    public void setMaintenanceReport(List<MaintenanceListData> maintenanceData) {
        getDataManager().setMaintenanceReport(maintenanceData);
    }

    public List<MaintenanceListData> getMaintenanceReport() {
        return getDataManager().getMaintenanceReport();
    }

    public  void  deleteMaintenanceReportArray(List<MaintenanceListData>maintenanceListData){
        getDataManager().deleteMaintenanceReport(maintenanceListData);
    }


    public void saveMaintenanceReportToLocalStorage(MaintenanceListData maintenanceData){
        if (checkIfMaintenanceReportIsEmpty()) {
            List<MaintenanceListData> newArray = new ArrayList<>();
            newArray.add(maintenanceData);
            setMaintenanceReport(newArray);

        } else {
            List<MaintenanceListData> oldArray = getMaintenanceReport();
            Log.i("SaveAlready", "Already");
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getVehicleSystem().equals(maintenanceData.getVehicleSystem())) {
                    oldArray.get(i).setRoutineActivity(maintenanceData.getRoutineActivity());
                    Log.i("part", maintenanceData.getVehicleSystem());
                    setMaintenanceReport(oldArray);
                    Log.i("NEW Report updated", String.valueOf(getMaintenanceReport()));

                    break;
                } else {
                    if (getMaintenanceReport().contains(maintenanceData)) {
                    }
                    List<MaintenanceListData> sameArray = getMaintenanceReport();
                    setMaintenanceReport(sameArray);
                }
            }

        }
        if (!getMaintenanceReport().contains(maintenanceData)) {
            List<MaintenanceListData> addArray = getMaintenanceReport();
            addArray.add(maintenanceData);
            setMaintenanceReport(addArray);

        }

        Log.i("Maintenance Big Array", String.valueOf(getMaintenanceReport()));

    }

    public  void onSaveMaintenanceReport(){
        getNavigator().onSaveMaintenanceReport();
    }

    public void onDispose(){
        onCleared();
    }
}
