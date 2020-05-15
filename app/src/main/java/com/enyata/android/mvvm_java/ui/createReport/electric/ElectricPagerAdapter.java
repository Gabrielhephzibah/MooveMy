package com.enyata.android.mvvm_java.ui.createReport.electric;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ElectricPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public ElectricPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return PowerLockFragment.newInstance();
        }else if (position == 1){
            return  PowerSeatFragment.newInstance();
        }else if (position ==2){
            return  PowerSteeringFragment.newInstance();
        }else if (position == 3){
            return PowerWindowFragment.newInstance();
        }else if (position == 4){
            return PowerMirrorFragment.newInstance();
        }else if (position == 5){
            return AudioSystemFragment.newInstance();
        }else  if (position == 6){
            return ComputerFragment.newInstance();
        }else if (position == 7){
            return  HeadlightFragment.newInstance();
        }else  if (position == 8){
            return TailLightFragment.newInstance();
        }else if (position == 9){
            return SignalLightFragment.newInstance();
        }else if (position == 10){
            return BrakeLightFragment.newInstance();
        }else {
            return ParkingLightFragment.newInstance();
        }

    }



    @Override
    public int getCount() {
        return 12;
    }
}
