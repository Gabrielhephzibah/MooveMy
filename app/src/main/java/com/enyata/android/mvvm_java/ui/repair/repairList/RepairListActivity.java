package com.enyata.android.mvvm_java.ui.repair.repairList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorData;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.databinding.ActivityRepairListBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RepairListActivity extends BaseActivity<ActivityRepairListBinding, RepairListViewModel> implements RepairListNavigator {
    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    RepairListViewModel repairListViewModel;
    RecyclerView recyclerView;
    RepairListAdapter repairListAdapter;
    EditText searchEdit;

    ActivityRepairListBinding activityRepairListBinding;
    List<RepairItemList> repairItemList = new ArrayList<>();

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_list;
    }

    @Override
    public RepairListViewModel getViewModel() {
        repairListViewModel = ViewModelProviders.of(this, factory).get(RepairListViewModel.class);
        return repairListViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repairListViewModel.setNavigator(this);
        activityRepairListBinding = getViewDataBinding();
        recyclerView = activityRepairListBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (InternetConnection.getInstance(this).isOnline()) {
            repairListViewModel.getInspectorHistoty();
        } else {
            Alert.showFailed(getApplicationContext(), "Please Check your connection and try again");
        }

        searchEdit = activityRepairListBinding.searchEditText;

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                repairListAdapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public void onProceedRepair() {
        Intent intent = new Intent(getApplicationContext(), RepairsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onExterior() {

    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable != null) {
            ANError error = (ANError) throwable;
            InspectorListResponse response = gson.fromJson(error.getErrorBody(), InspectorListResponse.class);
            if (error.getErrorBody() != null) {
                Alert.showFailed(getApplicationContext(), response.getMessage());
            } else {
                Alert.showFailed(getApplicationContext(), "Unable to connect to the internet");
            }
        }
    }


    @Override
    public void onResponse(InspectorListResponse response) {
        Log.i("RRRR", "respoinse is successful");
        Log.i("RRRR", String.valueOf(response));

        List<InspectorData> array = response.getData();
        for (int i = 0; i < array.size(); i++) {
            InspectorData data = array.get(i);
            String mooveId = data.getMooveId();
            String carYear = data.getYear();
            String carMake = data.getMake();
            String carModel = data.getModel();
            String id = String.valueOf(data.getId());
            String vehincleId = data.getVehicleId();
            repairItemList.add(new RepairItemList(mooveId, carYear, carMake, carModel,vehincleId, id));
            repairListAdapter = new RepairListAdapter(RepairListActivity.this, repairItemList);
            recyclerView.setAdapter(repairListAdapter);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repairListViewModel.onDispose();
    }
}
