package com.enyata.android.mvvm_java.ui.repair.repairList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorData;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorListResponse;
import com.enyata.android.mvvm_java.data.model.api.response.MaintenanceErrorResponse;
import com.enyata.android.mvvm_java.databinding.ActivityRepairListBinding;
import com.enyata.android.mvvm_java.ui.base.BaseActivity;
import com.enyata.android.mvvm_java.ui.createReport.CreateReportActivity;
import com.enyata.android.mvvm_java.ui.mainActivity.MainActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListAdapter;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListItem;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;
import com.enyata.android.mvvm_java.utils.Alert;
import com.enyata.android.mvvm_java.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RepairListActivity extends BaseActivity<ActivityRepairListBinding, RepairListViewModel> implements RepairListNavigator{
    @Inject
    Gson gson;

    @Inject
    ViewModelProviderFactory factory;
    RepairListViewModel repairListViewModel;
    RecyclerView recyclerView;
    RepairListAdapter repairListAdapter;
    EditText searchEdit;
    CustomAdapter customAdapter;
    protected Handler handler;
    private int limit = 20;
    private int currentPage = 0;
    private int offset;

    int pastVisiblesItems, visibleItemCount, totalItemCount;

    ActivityRepairListBinding activityRepairListBinding;
//    List<RepairItemList> repairItemList = new ArrayList<>();
    List<RepairItemList> repairList = new ArrayList<>();
    LinearLayoutManager mLayoutManager;
    RepairsCustomAdapter repairsCustomAdapter;
    ProgressBar progressBar;
    ScrollListener scrollListener;



    @Override
    public int getBindingVariable() {
        return com.enyata.android.mvvm_java.BR.viewModel;
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

        if (InternetConnection.getInstance(this).isOnline()) {
            repairListViewModel.getAllVehicleInDataBase(limit, 0);
        }else {
            Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
        }
//        repairList = new ArrayList<>();
        handler = new Handler();

        recyclerView = activityRepairListBinding.recyclerView;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        customAdapter = new CustomAdapter(this,repairList, recyclerView);
        recyclerView.setAdapter(customAdapter);

//        progressBar.setVisibility(View.VISIBLE);
//        recyclerView.setVisibility(View.GONE);

        customAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                repairList.add(null);
                customAdapter.notifyItemInserted(repairList.size() - 1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        repairList.remove(repairList.size() - 1);
                        customAdapter.notifyItemRemoved(repairList.size());
                        offset +=20;
                        repairListViewModel.getAllVehicleInDataBase(limit,offset);
                        customAdapter.notifyDataSetChanged();
                        customAdapter.notifyItemInserted(repairList.size());

                    }
                },2000);
                customAdapter.setLoaded();
                customAdapter.notifyDataSetChanged();
            }
        });


        searchEdit = activityRepairListBinding.searchEditText;

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customAdapter.getFilter().filter(charSequence.toString());
                if (charSequence.toString().length()== 0){
                    customAdapter = new CustomAdapter(RepairListActivity.this,repairList, recyclerView);
                  recyclerView.setAdapter(customAdapter);
                    hideKeyboard();

                    customAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore() {
                            repairList.add(null);
                            customAdapter.notifyItemInserted(repairList.size() - 1);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    repairList.remove(repairList.size() - 1);
                                    customAdapter.notifyItemRemoved(repairList.size());
                                    offset +=20;
                                    if (InternetConnection.getInstance(RepairListActivity.this).isOnline()) {
                                        repairListViewModel.getAllVehicleInDataBase(limit, offset);
                                    }else {
                                        Alert.showFailed(getApplicationContext(),"Unable to connect to the internet");
                                    }
                                    customAdapter.notifyDataSetChanged();
                                    customAdapter.notifyItemInserted(repairList.size());

                                }
                            },2000);
                            customAdapter.setLoaded();
                            customAdapter.notifyDataSetChanged();
                        }
                    });




                }



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
//        progressBar.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);

        if (throwable != null ) {
            throwable.printStackTrace();
            try {
                ANError error = (ANError) throwable;
                InspectorListResponse response = gson.fromJson(error.getErrorBody(), InspectorListResponse.class);
                if (error.getErrorBody() != null) {
                    Alert.showFailed(getApplicationContext(), response.getMessage());
                } else {
                    Alert.showFailed(getApplicationContext(), "Unable to Connect to the Internet");
                }
            } catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception) {
                Alert.showFailed(getApplicationContext(), "An unknown error occurred");
            }
        }
    }

    @Override
    public void onResponse(InspectorListResponse response) {
        try {
            List<InspectorData> array = response.getData();
            if (array.size() > 0){
                for (int i = 0; i < array.size(); i++) {
                InspectorData data = array.get(i);
                String mooveId = data.getMooveId();
                String carYear = data.getYear();
                String carMake = data.getMake();
                String carModel = data.getModel();
                String id = String.valueOf(data.getId());
                String vehincleId = data.getVehicleId();
                repairList.add(new RepairItemList(mooveId, carYear, carMake, carModel, vehincleId, id));
                customAdapter.notifyDataSetChanged();
                customAdapter.notifyItemInserted(repairList.size());
                customAdapter.setLoaded();

                }
            }else {
                customAdapter.setLoaded();
                customAdapter.setMoreDataAvailable(false);
                Toast toast = Toast.makeText(this,"No More Data Available",Toast.LENGTH_LONG);
                View view = toast.getView();
                TextView text = (TextView) view.findViewById(android.R.id.message);
                text.setTextColor(getResources().getColor(R.color.white));
                view.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
                toast.show();

            }


        }catch (IllegalStateException | JsonSyntaxException | NullPointerException | ClassCastException exception){
            Alert.showFailed(getApplicationContext(), "An unknown error occurred");
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        repairListViewModel.onDispose();
    }


}
