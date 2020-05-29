package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.roadtestMonthly;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.roadtest.AccelerationFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.BrakingFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.EnginePerformanceFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.IdlingFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.StartingFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.SteeringFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.SuspensionPerformanceFragment;
import com.enyata.android.mvvm_java.ui.createReport.roadtest.TransmissionShiftFragment;

public class RoadTestPagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public RoadTestPagerAdapterM(Context context, FragmentManager fm) {
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
            return StartingFragmentM.newInstance();
        } else if (position == 1) {
            return IdlingFragmentM.newInstance();
        } else if (position == 2) {
            return EnginePerformanceFragmentM.newInstance();
        } else if (position == 3) {
            return AccelerationFragmentM.newInstance();
        } else if (position == 4) {
            return TransmissionShiftFragmentM.newInstance();
        } else if(position ==5) {
            return SteeringFragmentM.newInstance();
        }else if (position == 6){
            return BrakingFragmentM.newInstance();
        }else {
            return SuspensionPerformanceFragmentM.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
