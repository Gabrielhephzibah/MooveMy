package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.underhodMonthly;

import android.content.Context;
import android.view.ViewGroup;

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

public class UnderHoodPagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public UnderHoodPagerAdapterM(Context context, FragmentManager fm) {
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
            return EngineCompFragmentM.newInstance();
        } else if (position == 1) {
            return BatteryFragmentM.newInstance();
        } else if (position == 2) {
            return OilFragmentM.newInstance();
        } else if (position == 3) {
            return FluidFragmentM.newInstance();
        } else if (position == 4) {
            return WiringFragmentM.newInstance();
        } else if(position ==5) {
            return BeltFragmentM.newInstance();
        }else {
            return HosesFragmentM.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
