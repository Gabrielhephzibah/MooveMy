package com.enyata.android.mvvm_java.ui.createReport.tires;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ui.createReport.glass.RearWindowFragment;

public class SpareTireFragment extends Fragment {
    public SpareTireFragment(){
        //leave it empty
    }


    public static SpareTireFragment newInstance() {
        return new SpareTireFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spare_tire_layout, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
