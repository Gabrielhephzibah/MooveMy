package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underbodMonthly;

import android.content.Context;
import android.util.Log;
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
import com.enyata.android.mvvm_java.ui.loading.LoadingActivity;

public class UnderbodyPagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public UnderbodyPagerAdapterM(Context context, FragmentManager fm) {
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
            return FrameFragmentM.newInstance();
        } else if (position == 1) {
            return ExhaustFragmentM.newInstance();
        } else if (position == 2) {
            return TransmissionFragmentM.newInstance();
        } else if (position == 3) {
            return DriveAxleFragmentM.newInstance();
        } else if (position == 4) {
            return SuspensionFragmentM.newInstance();
        } else {
            return BrakeSystemFragmentM.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i("DESTROYED","NOT DESTROYED");
    }
}
