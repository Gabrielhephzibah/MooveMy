package com.enyata.android.mvvm_java.ui.createReport.interior;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ui.createReport.tires.SpareTireFragment;

public class AirCondFragment extends Fragment {
    public AirCondFragment(){
        //leave it empty
    }


    public static AirCondFragment newInstance() {
        return new AirCondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.air_cond_layout, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
