package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.ViewModelProviderFactory;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.data.model.api.request.Vehicle;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListAdapter;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;

import java.util.ArrayList;
import java.util.List;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleListViewHolder> {

    public Context context;
    private List<VehicleListItem> lists;
    ArrayList<VehicleListItem> vehicleListItems;
    VehicleListViewModel vehicleListViewModel;
    List<VehicleCollection>monthlyVehicleReport;

    ViewModelProviderFactory factory;

    TextView mooveId, carMake, carModel, carYear;

    public VehicleListAdapter(Context context, List<VehicleListItem> lists) {
        this.context = context;
        this.lists = lists;
        vehicleListViewModel = ViewModelProviders.of((VehicleListActivity) context).get(VehicleListViewModel.class);
//        monthlyVehicleReport = vehicleListViewModel.getMonthlyVehicleReport();
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<VehicleListItem> results = new ArrayList<VehicleListItem>();
                if (vehicleListItems == null)
                    vehicleListItems = (ArrayList<VehicleListItem>) lists;
                if (constraint != null) {
                    if (vehicleListItems != null && vehicleListItems.size() > 0) {
                        for (final VehicleListItem g : vehicleListItems) {
                            if (g.getMake().toLowerCase()
                                    .contains(constraint.toString().toLowerCase()) || g.getYear().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getModel().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getMooveId()
                                    .toLowerCase().contains(constraint.toString().toLowerCase()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                lists = (ArrayList<VehicleListItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public VehicleListAdapter.VehicleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_layout, parent, false);
        return new VehicleListAdapter.VehicleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VehicleListAdapter.VehicleListViewHolder holder, int position) {
        VehicleListItem itemList = lists.get(position);
        holder.mooveId.setText(itemList.getMooveId());
        holder.carYear.setText(itemList.getYear());
        holder.carModel.setText(itemList.getModel());
        holder.carMake.setText(itemList.getMake());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class VehicleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mooveId, carMake, carModel, carYear, vehincleId;

        public VehicleListViewHolder(@NonNull View itemView) {
            super(itemView);
            mooveId = itemView.findViewById(R.id.mooveId);
            carMake = itemView.findViewById(R.id.carMake);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            VehicleListItem data = lists.get(getAdapterPosition());
            vehicleListViewModel.deleteMonthlyReport(monthlyVehicleReport);
            vehicleListViewModel.setMooveId(data.getMooveId());
            vehicleListViewModel.setCarMakeMaint(data.getMake());
            vehicleListViewModel.setCarYearMaint(data.getYear());
            vehicleListViewModel.setcarModelMaint(data.getModel());
            vehicleListViewModel.setVehicleIdMaint(data.getVehicleId());
            vehicleListViewModel.setInitialMileage(data.getMileage());
            Intent intent = new Intent(context, MonthlyReportActivity.class);
//            intent.putExtra("data", data);
            context.startActivity(intent);

        }

    }


    public List<VehicleListItem> getData() {
        return lists;
    }

}
