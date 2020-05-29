package com.enyata.android.mvvm_java.ui.createReport.exterior;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExteriorViewPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public ExteriorViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return HoodFragment.newInstance();
        }else if (position == 1){
            return  FrontBumperFragment.newInstance();
        }else if (position ==2){
            return  FendersFragment.newInstance();
        }else if (position == 3){
            return DoorFragment.newInstance();
        }else if (position == 4){
            return RoofFragment.newInstance();
        }else if (position == 5){
            return RearFragment.newInstance();
        }else  if (position == 6){
            return  RearBumperFragment.newInstance();
        }else if (position == 7){
            return  TrunkFragment.newInstance();
        }else  if (position == 8){
            return  TrimFragment.newInstance();
        }else if (position == 9){
            return FuelDoorFragment.newInstance();
        }else {
            return  PaintFragment.newInstance();
        }

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    @Override
    public int getCount() {
        return 11;
    }
}
