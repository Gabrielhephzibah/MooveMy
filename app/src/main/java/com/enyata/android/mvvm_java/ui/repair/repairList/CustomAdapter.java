package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.request.VehiclePartRepair;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListActivity;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListItem;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleList.VehicleListViewModel;
import com.enyata.android.mvvm_java.ui.monthlyReport.vehicleMonthlyReport.MonthlyReportActivity;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int TYPE_ITEM = 0;
    public final int TYPE_LOAD = 1;

    Context context;
    private List<RepairItemList> items;
    ArrayList<RepairItemList> repairItemList;
    RepairListViewModel repairListViewModel;
    List<VehiclePartRepair> getRepairReport;

    //    onLoadMoreListener loadMoreListener;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private int currentPage = 0;
    private OnLoadMoreListener onLoadMoreListener;
//   private OnItemListener onItemListener;

    boolean isMoreDataAvailable = true;


    public CustomAdapter(Context context, List<RepairItemList> items, RecyclerView recyclerView) {
        this.context = context;
        this.items = items;
        repairListViewModel = ViewModelProviders.of((RepairListActivity) context).get(RepairListViewModel.class);
        getRepairReport = repairListViewModel.getRepairReport();


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
                    Log.i("Loading status", String.valueOf(loading));

                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                            Log.i("ADD MORE", "YOU CAN ADD MORE");
                        }
                        loading = true;
//


                    }
                }


            });

        }

    }


    public Filter getFilter() {
        Log.i("GETFILTER","ENTER GET FILTER");
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<RepairItemList> results = new ArrayList<RepairItemList>();
                if (repairItemList == null)
                    repairItemList = (ArrayList<RepairItemList>) items;
                if (constraint != null) {
                    if (repairItemList != null && repairItemList.size() >= 0) {
                        Log.i("REPAIRLIST", "ENTER REPAIR LIST");
                            for (final RepairItemList g : repairItemList) {
                                if (g.getMake().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getYear().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getModel().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getMooveId()
                                        .toLowerCase().contains(constraint.toString().toLowerCase()))
                                    results.add(g);
                            }

                    }

                    oReturn.values = results;
                }
                Log.i("REPAIRLIST", "ENTER REPAIR LIST");
                return oReturn;

            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items = (ArrayList<RepairItemList>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspector_history_layout, parent, false);
            viewHolder = new ItemViewHolder(view);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_progress_dialog, parent, false);
            viewHolder = new ProgressViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            RepairItemList itemList = (RepairItemList) items.get(position);
            ((ItemViewHolder) holder).mooveId.setText(itemList.getMooveId());
            ((ItemViewHolder) holder).carYear.setText(itemList.getYear());
            ((ItemViewHolder) holder).carModel.setText(itemList.getModel());
            ((ItemViewHolder) holder).carMake.setText(itemList.getMake());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RepairItemList data = items.get(position);
                    repairListViewModel.deleteRepairReport(getRepairReport);
                    repairListViewModel.setMooveIdRepairs(data.getMooveId());
                    repairListViewModel.setCarMakeRepairs(data.getMake());
                    repairListViewModel.setCarYearRepairs(data.getYear());
                    repairListViewModel.setCarModelRepairs(data.getModel());
                    repairListViewModel.setVehicleIdRepairs(data.getId());
                    Intent intent = new Intent(context, RepairsActivity.class);
                    context.startActivity(intent);
                    Log.i("Year", data.getYear());
                    Log.i("Model", data.getModel());
                    Log.i("id", data.getId());
                    Log.i("Make", data.getMake());
                    Log.i("mooveID", data.getMooveId());
                    Log.i("ITEMCLICK", "ITEMCLICK" + position);
                }
            });


        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }

    }


    public void setLoaded() {
        Log.i("LOADING", String.valueOf(loading));
        loading = false;

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) != null ? TYPE_ITEM : TYPE_LOAD;
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mooveId, carMake, carModel, carYear, vehincleId;
        OnItemListener onItemListener;
        RepairItemList repairItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mooveId = itemView.findViewById(R.id.mooveId);
            carMake = itemView.findViewById(R.id.carMake);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);

        }

    }

    public interface OnItemListener {
        void onItemClick(int position);
    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public List<RepairItemList> getData() {
        return items;
    }


    public void setMoreDataAvailable(boolean moreDataAvailable) {
        loading = true;
        isMoreDataAvailable = moreDataAvailable;
    }


}
