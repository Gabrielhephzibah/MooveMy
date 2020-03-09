package com.enyata.android.mvvm_java.ui.createReport.roadtest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.underhood.BatteryFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.BeltFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.EngineCompFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.FluidFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.HosesFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.OilFragment;
import com.enyata.android.mvvm_java.ui.createReport.underhood.WiringFragment;

public class RoadTestPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public RoadTestPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return StartingFragment.newInstance();
        } else if (position == 1) {
            return IdlingFragment.newInstance();
        } else if (position == 2) {
            return EnginePerformanceFragment.newInstance();
        } else if (position == 3) {
            return AccelerationFragment.newInstance();
        } else if (position == 4) {
            return TransmissionShiftFragment.newInstance();
        } else if(position ==5) {
            return SteeringFragment.newInstance();
        }else if (position == 6){
            return BrakingFragment.newInstance();
        }else {
            return SuspensionPerformanceFragment.newInstance();
        }
    }
}
