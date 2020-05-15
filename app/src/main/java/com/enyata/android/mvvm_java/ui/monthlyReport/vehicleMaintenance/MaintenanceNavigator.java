package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMaintenance;

import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceScheduleResponse;

public interface MaintenanceNavigator {
    void  onBack();
    void onEngineOil();
    void onOilFilter();
    void onAirFilter();
    void onPollenFilter();
    void onBrakePad();
    void onSuspensionSystem();
    void onVariousBelt();
    void  onSparkPlug();
    void onFuelFilter();
    void  onEngineCooling();
    void onWiperBlade();
    void onChargingSystem();
    void onPowerSteering();
    void onTransmissionOil();
    void onBrakeFluid();
    void onFuelSystem();
    void onTireRotation();
    void  onAlignment();
    void onAirFlow();
    void  onAirCondition();
    void onElectronic();
    void  onExhaustPipe();
    void onTireInflation();
    void onWheel();
    void  onAddCurrentMileage();
    void  onTransmissionFilter();
    void onResponse(MaintenanceScheduleResponse response);
    void handleError(Throwable throwable);

}
