package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance;

import com.enyata.android.mvvm_java.data.DataManager;
import com.enyata.android.mvvm_java.data.model.api.request.MaintenanceScheduleRequest;
import com.enyata.android.mvvm_java.ui.base.BaseViewModel;
import com.enyata.android.mvvm_java.utils.rx.SchedulerProvider;

public class MaintenanceViewModel extends BaseViewModel<MaintenanceNavigator> {

    public MaintenanceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

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
}
