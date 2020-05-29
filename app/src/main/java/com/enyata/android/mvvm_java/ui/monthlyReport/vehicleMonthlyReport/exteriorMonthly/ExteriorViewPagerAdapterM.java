package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.exteriorMonthly;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.exterior.DoorFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.FendersFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.FrontBumperFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.FuelDoorFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.HoodFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.PaintFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.RearBumperFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.RearFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.RoofFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.TrimFragment;
import com.enyata.android.mvvm_java.ui.createReport.exterior.TrunkFragment;

public class ExteriorViewPagerAdapterM extends FragmentPagerAdapter {

    Context context;

    public ExteriorViewPagerAdapterM(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return HoodFragmentM.newInstance();
        }else if (position == 1){
            return  FrontBumperFragmentM.newInstance();
        }else if (position ==2){
            return  FenderFragmentM.newInstance();
        }else if (position == 3){
            return DoorFragmentM.newInstance();
        }else if (position == 4){
            return RoofFragmentM.newInstance();
        }else if (position == 5){
            return RearFragmentM.newInstance();
        }else  if (position == 6){
            return  RearBumperFragmentM.newInstance();
        }else if (position == 7){
            return  TrunkFragmentM.newInstance();
        }else  if (position == 8){
            return  TrimFragmentM.newInstance();
        }else if (position == 9){
            return FuelDoorFragmentM.newInstance();
        }else {
            return  PaintFragmentM.newInstance();
        }

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    //    @Override
//    public float getPageWidth(int position) {
//        return 0.93f;
//    }

    @Override
    public int getCount() {
        return 11;
    }


}
