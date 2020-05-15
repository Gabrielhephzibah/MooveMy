package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.interiorMonthly;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.interior.AirCondFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.CarpetFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.DashGuagesFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.DashboardFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.DefrosterFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.DoorPanelFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.GloveBoxFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.HeadLinerFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.HeaterFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.InteriorTrimFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.SeatsFragment;
import com.enyata.android.mvvm_java.ui.createReport.interior.VanityMirrorFragment;

public class InteriorPagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public InteriorPagerAdapterM(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return SeatFragmentM.newInstance();
        }else if (position == 1){
            return  HeadLinerFragmentM.newInstance();
        }else if (position ==2){
            return  CarpetFragmentM.newInstance();
        }else if (position == 3){
            return DoorPanelFragmentM.newInstance();
        }else if (position == 4){
            return GloveBoxFragmentM.newInstance();
        }else if (position == 5){
            return VanityMirrorFragmentM.newInstance();
        }else  if (position == 6){
            return  InteriorTrimFragmentM.newInstance();
        }else if (position == 7){
            return  DashboardFragmentM.newInstance();
        }else  if (position == 8){
            return  DashGuagesFragmentM.newInstance();
        }else if (position == 9){
            return AirCondFragmentM.newInstance();
        }else if (position == 10){
            return HeaterFragmentM.newInstance();
        }else {
            return DefrosterFragmentM.newInstance();
        }

    }



    @Override
    public int getCount() {
        return 12;
    }
}
