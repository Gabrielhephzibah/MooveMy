package com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.myData.VehicleCollection;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairList.CustomAdapter;
import com.enyata.android.mvvm_java.ui.repair.repairList.OnLoadMoreListener;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairItemList;
import com.enyata.android.mvvm_java.ui.repair.repairList.RepairListViewModel;

import java.util.ArrayList;
import java.util.List;

public class VehicleCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_ITEM = 0;
    public final int TYPE_LOAD = 1;
    private OnLoadMoreListener onLoadMoreListener;

    Context context;
    private List<VehicleListItem> vehicleList;
    ArrayList<VehicleListItem> vehicleListItems;
    VehicleListViewModel vehicleListViewModel;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    List<VehicleCollection>monthlyVehicleReport;
    private int currentPage = 0;
    boolean  isMoreDataAvailable = true;

    public VehicleCustomAdapter(Context context, List<VehicleListItem> vehicleList,RecyclerView recyclerView){
        this.context = context;
        this.vehicleList = vehicleList;
        vehicleListViewModel = ViewModelProviders.of((VehicleListActivity) context).get(VehicleListViewModel.class);
        monthlyVehicleReport = vehicleListViewModel.getMonthlyVehicleReport();



        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Log.i("LOoading status",String.valueOf(loading));

                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                        if (onLoadMoreListener != null){
                            onLoadMoreListener.onLoadMore();
                            Log.i("ADD MORE","YOU CAN ADD MORE");
                        }
                        loading = true;
//                         loading = true;



                    }
                }


            });

        }


    }




    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<VehicleListItem> results = new ArrayList<VehicleListItem>();
                if (vehicleListItems == null)
                    vehicleListItems = (ArrayList<VehicleListItem>) vehicleList;
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
                vehicleList = (ArrayList<VehicleListItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_layout, parent, false);
            viewHolder = new VehicleItemViewHolder(view);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_progress_dialog, parent, false);
            viewHolder =  new VehicleProgressViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VehicleItemViewHolder)  {
            VehicleListItem itemList = (VehicleListItem) vehicleList.get(position);
            ((VehicleItemViewHolder) holder).mooveId.setText(itemList.getMooveId());
            ((VehicleItemViewHolder) holder).carYear.setText(itemList.getYear());
            ((VehicleItemViewHolder) holder).carModel.setText(itemList.getModel());
            ((VehicleItemViewHolder) holder).carMake.setText(itemList.getMake());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   VehicleListItem data = vehicleList.get(position);
                  vehicleListViewModel.deleteMonthlyReport(monthlyVehicleReport);
                   vehicleListViewModel.setMooveId(data.getMooveId());
                   vehicleListViewModel.setCarMakeMaint(data.getMake());
                   vehicleListViewModel.setCarYearMaint(data.getYear());
                   vehicleListViewModel.setcarModelMaint(data.getModel());
                   vehicleListViewModel.setVehicleIdMaint(data.getId());
                   vehicleListViewModel.setInitialMileage(data.getMileage());
                   Intent intent = new Intent(context, MonthlyReportActivity.class);
                   context.startActivity(intent);

                   Log.i("Year", data.getYear());
                   Log.i("Model", data.getModel());
                   Log.i("id", data.getId());
                   Log.i("Make", data.getMake());
                   Log.i("mooveID", data.getMooveId());
                   Log.i("ITEMCLICK","ITEMCLICK" +position);

               }
           });

        }else {
            ((VehicleProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return vehicleList == null ? 0 : vehicleList.size();
    }

    public class VehicleItemViewHolder extends RecyclerView.ViewHolder{
        TextView mooveId, carMake, carModel, carYear, vehincleId;
        public VehicleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mooveId = itemView.findViewById(R.id.mooveId);
            carMake = itemView.findViewById(R.id.carMake);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);
        }
    }

    public void setLoaded() {
        Log.i("LOADING",String.valueOf(loading) );
        loading = false;

    }


    @Override
    public int getItemViewType(int position) {
        return vehicleList.get(position) != null ? TYPE_ITEM : TYPE_LOAD;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)  {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }




    public static class VehicleProgressViewHolder extends  RecyclerView.ViewHolder{
        public ProgressBar progressBar;

        public VehicleProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
    public List<VehicleListItem> getData() {
        return vehicleList;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        loading = true;
        isMoreDataAvailable = moreDataAvailable;
    }
}
