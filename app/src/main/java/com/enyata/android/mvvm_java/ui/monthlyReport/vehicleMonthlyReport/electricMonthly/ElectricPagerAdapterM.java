package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.electricMonthly;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ElectricPagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public ElectricPagerAdapterM(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return PowerLockFragmentM.newInstance();
        }else if (position == 1){
            return  PowerSeatFragmentM.newInstance();
        }else if (position ==2){
            return  PowerSteeringFragmentM.newInstance();
        }else if (position == 3){
            return PowerWindowFragmentM.newInstance();
        }else if (position == 4){
            return PowerMirrorFragmentM.newInstance();
        }else if (position == 5){
            return AudioSystemFragmentM.newInstance();
        }else  if (position == 6){
            return ComputerFragmentM.newInstance();
        }else if (position == 7){
            return  HeadLightFragmentM.newInstance();
        }else  if (position == 8){
            return TailLightFragmentM.newInstance();
        }else if (position == 9){
            return SignalLightFragmentM.newInstance();
        }else if (position == 10){
            return BrakeLightFragmentM.newInstance();
        }else {
            return ParkingLightFragmentM.newInstance();
        }

    }



    @Override
    public int getCount() {
        return 12;
    }
}
