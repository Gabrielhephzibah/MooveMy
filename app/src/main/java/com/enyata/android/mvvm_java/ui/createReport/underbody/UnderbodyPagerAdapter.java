package com.enyata.android.mvvm_java.ui.createReport.underbody;

import android.content.Context;

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
import com.enyata.android.mvvm_java.ui.createReport.tires.SpareTireFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.TiresFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.WheelFragment;

public class UnderbodyPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public UnderbodyPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return FrameFragment.newInstance();
        } else if (position == 1) {
            return ExhaustFragment.newInstance();
        } else if (position == 2) {
            return TransmissionFragment.newInstance();
        } else if (position == 3) {
            return DriveAxleFragment.newInstance();
        } else if (position == 4) {
            return SuspensionFragment.newInstance();
        } else {
            return BrakeSystemFragment.newInstance();
        }
    }
}
