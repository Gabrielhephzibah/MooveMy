package com.enyata.android.mvvm_java.ui.createReport.glass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class GlassPagerAdater extends FragmentPagerAdapter {
    Context context;

    public GlassPagerAdater(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return WindShieldFragment.newInstance();
        } else if(position == 1) {
            return WindowsFragment.newInstance();
        }else if (position ==2){
            return MirrorFragment.newInstance();
        }else {
            return RearWindowFragment.newInstance();
        }
    }
}
