package com.enyata.android.mvvm_java.ui.createReport.tires;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.ui.createReport.glass.MirrorFragment;
import com.enyata.android.mvvm_java.ui.createReport.glass.RearWindowFragment;
import com.enyata.android.mvvm_java.ui.createReport.glass.WindShieldFragment;
import com.enyata.android.mvvm_java.ui.createReport.glass.WindowsFragment;

public class TirePagerAdapter extends FragmentPagerAdapter {
    Context context;

    public TirePagerAdapter(Context context, FragmentManager fm) {
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
            return TiresFragment.newInstance();
        } else if(position == 1) {
            return WheelFragment.newInstance();
        }else {
            return SpareTireFragment.newInstance();
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
