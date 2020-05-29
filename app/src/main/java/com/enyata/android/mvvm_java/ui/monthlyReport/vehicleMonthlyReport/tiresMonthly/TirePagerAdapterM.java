package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.tiresMonthly;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.tires.SpareTireFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.TiresFragment;
import com.enyata.android.mvvm_java.ui.createReport.tires.WheelFragment;

public class TirePagerAdapterM extends FragmentPagerAdapter {
    Context context;

    public TirePagerAdapterM(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return TiresFragmentM.newInstance();
        } else if(position == 1) {
            return WheelFragmentM.newInstance();
        }else {
            return SpareTireFragmentM.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
}
