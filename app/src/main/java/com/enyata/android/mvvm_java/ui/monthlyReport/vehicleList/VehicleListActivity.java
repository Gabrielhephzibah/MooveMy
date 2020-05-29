package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.BR;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorData;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.databinding.ActivityVehicleListBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListAdapter;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class VehicleListActivity extends BaseActivity<ActivityVehicleListBinding,VehicleListViewModel>implements VehicleListNavigator{
    @Inject
    ViewModelProviderFactory factory;
    ActivityVehicleListBinding activityVehicleListBinding;
    VehicleListViewModel vehicleListViewModel;
    RecyclerView recyclerView;
    VehicleListAdapter vehicleListAdapter;
    EditText searchEdit;
    List<VehicleListItem> vehicleListItems = new ArrayList<>();
    @Inject
    Gson gson;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_list;
    }

    @Override
    public VehicleListViewModel getViewModel() {
        vehicleListViewModel = ViewModelProviders.of(this, factory).get(VehicleListViewModel.class);
        return vehicleListViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vehicleListViewModel.setNavigator(this);
        activityVehicleListBinding =  getViewDataBinding();
        recyclerView = activityVehicleListBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (InternetConnection.getInstance(this).isOnline()) {
            vehicleListViewModel.getMonthlyVehicleList();
        } else {
            Alert.showFailed(getApplicationContext(), "Please Check your connection and try again");
        }

        searchEdit = activityVehicleListBinding.searchEditText;

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                vehicleListAdapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onBack() {
        Log.i("BACK","BACK");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onVehicleList() {
        Intent intent = new Intent(getApplicationContext(), MonthlyReportActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(InspectorListResponse response) {
        Log.i("RESPONSE", response.toString());
        List<InspectorData> array = response.getData();
        try {
            for (int i = 0; i < array.size(); i++) {
                InspectorData data = array.get(i);
                String mooveId = data.getMooveId();
                String carYear = data.getYear();
                String carMake = data.getMake();
                String carModel = data.getModel();
                String initialMileage = data.getMileage();
                String id = String.valueOf(data.getId());
                String vehincleId = data.getVehicleId();
                vehicleListItems.add(new VehicleListItem(mooveId, carYear, carMake, carModel, vehincleId, id,initialMileage));
                vehicleListAdapter = new VehicleListAdapter(VehicleListActivity.this, vehicleListItems);
                recyclerView.setAdapter(vehicleListAdapter);
            }
        }catch (IllegalStateException | JsonSyntaxException  | NullPointerException | ClassCastException exception ) {
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            try {

                ANError error = (ANError) throwable;
                InspectorListResponse response = gson.fromJson(error.getErrorBody(), InspectorListResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
                }
            }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception ) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        vehicleListViewModel.onDispose();
    }


}
