package com.enyata.android.mvvm_java.ui.createReport.underhood;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.underbody.BrakeSystemFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.DriveAxleFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.ExhaustFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.FrameFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.SuspensionFragment;
import com.enyata.android.mvvm_java.ui.createReport.underbody.TransmissionFragment;

public class UnderHoodPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public UnderHoodPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return EngineCompFragment.newInstance();
        } else if (position == 1) {
            return BatteryFragment.newInstance();
        } else if (position == 2) {
            return OilFragment.newInstance();
        } else if (position == 3) {
            return FluidFragment.newInstance();
        } else if (position == 4) {
            return WiringFragment.newInstance();
        } else if(position ==5) {
            return BeltFragment.newInstance();
        }else {
            return HosesFragment.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
