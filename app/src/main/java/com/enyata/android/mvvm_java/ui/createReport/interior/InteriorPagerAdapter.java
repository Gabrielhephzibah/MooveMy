package com.enyata.android.mvvm_java.ui.createReport.interior;

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

public class InteriorPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public InteriorPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return SeatsFragment.newInstance();
        }else if (position == 1){
            return  HeadLinerFragment.newInstance();
        }else if (position ==2){
            return  CarpetFragment.newInstance();
        }else if (position == 3){
            return DoorPanelFragment.newInstance();
        }else if (position == 4){
            return GloveBoxFragment.newInstance();
        }else if (position == 5){
            return VanityMirrorFragment.newInstance();
        }else  if (position == 6){
            return  InteriorTrimFragment.newInstance();
        }else if (position == 7){
            return  DashboardFragment.newInstance();
        }else  if (position == 8){
            return  DashGuagesFragment.newInstance();
        }else if (position == 9){
            return AirCondFragment.newInstance();
        }else if (position == 10){
            return HeaterFragment.newInstance();
        }else {
            return DefrosterFragment.newInstance();
        }

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        
    }

    @Override
    public int getCount() {
        return 12;
    }

}
