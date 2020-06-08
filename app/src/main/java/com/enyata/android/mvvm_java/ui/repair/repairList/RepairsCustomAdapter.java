package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.android.mvvm_java.R;

import java.util.ArrayList;
import java.util.List;

public class RepairsCustomAdapter extends RecyclerView.Adapter<RepairsCustomAdapter.CustomViewHolder> {
    private List<RepairItemList> lists;
   ArrayList<RepairItemList> repairItemList;
    private final int VIEW_TYPE_ITEM = 0;
   private final int VIEW_TYPE_LOADING = 1;



    public RepairsCustomAdapter(List<RepairItemList> lists) {
//       this.context = context;
        this.lists = lists;
   }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = null;
        if (viewType == VIEW_TYPE_ITEM) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspector_history_layout, parent, false);
            return new DataViewHolder(root);
        } else {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_progress_dialog, parent, false);
            return new LoadingViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        if (holder instanceof DataViewHolder) {
            RepairItemList itemList = lists.get(position);
            holder.mooveId.setText(itemList.getMooveId());
            holder.carYear.setText(itemList.getYear());
            holder.carModel.setText(itemList.getModel());
            holder.carMake.setText(itemList.getMake());
        }else{
            holder.progressbar.setVisibility(View.VISIBLE);

            //Do whatever you want. Or nothing !!
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void addNullData() {
        lists.add(null);
        notifyItemInserted(lists.size() - 1);
    }

    public void removeNull() {
        lists.remove(lists.size() - 1);
        notifyItemRemoved(lists.size());
    }

    public void addData(List<RepairItemList>newList) {
        lists.addAll(newList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (lists.get(position) != null)
            return VIEW_TYPE_ITEM;
        else
            return VIEW_TYPE_LOADING;
    }

    class DataViewHolder extends CustomViewHolder{

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class LoadingViewHolder extends CustomViewHolder{

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class CustomViewHolder extends  RecyclerView.ViewHolder{
        TextView mooveId, carMake, carModel, carYear, vehincleId,progressbar;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mooveId = itemView.findViewById(R.id.mooveId);
            carMake = itemView.findViewById(R.id.carMake);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);
            progressbar = itemView.findViewById(R.id.progressBar);
        }
    }

//    private final int VIEW_TYPE_ITEM = 0;
//    private final int VIEW_TYPE_LOADING = 1;
//    public Context context;
//    private List<RepairItemList> lists;
//    ArrayList<RepairItemList> repairItemList;
//
//
//    public Filter getFilter() {
//        return new Filter() {
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                final FilterResults oReturn = new FilterResults();
//                final ArrayList<RepairItemList> results = new ArrayList<RepairItemList>();
//                if (repairItemList == null)
//                    repairItemList = (ArrayList<RepairItemList>) lists;
//                if (constraint != null) {
//                    if (repairItemList != null && repairItemList.size() > 0) {
//                        for (final RepairItemList g : repairItemList) {
//                            if (g.getMake().toLowerCase()
//                                    .contains(constraint.toString().toLowerCase()) || g.getYear().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getModel().toLowerCase().contains(constraint.toString().toLowerCase()) || g.getMooveId()
//                                    .toLowerCase().contains(constraint.toString().toLowerCase()))
//                                results.add(g);
//                        }
//                    }
//                    oReturn.values = results;
//                }
//                return oReturn;
//            }
//
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint,
//                                          FilterResults results) {
//                lists = (ArrayList<RepairItemList>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//
//    public RepairsCustomAdapter(Context context, List<RepairItemList> lists) {
//        this.context = context;
//        this.lists = lists;
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspector_history_layout, parent, false);
////        return new RepairListAdapter.RepairViewHolder(view);
//        if (viewType == VIEW_TYPE_ITEM) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspector_history_layout, parent, false);
//            return new ItemViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_progress_dialog, parent, false);
//            return new LoadingViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof ItemViewHolder) {
//
//            populateItemRows((ItemViewHolder) holder, position);
//        } else if (holder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) holder, position);
//        }
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return lists == null ? 0 : lists.size();
//    }
//
//    private void populateItemRows(ItemViewHolder viewHolder, int position) {
//        RepairItemList itemList = lists.get(position);
//        viewHolder.mooveId.setText(itemList.getMooveId());
//        viewHolder.carYear.setText(itemList.getYear());
//        viewHolder.carModel.setText(itemList.getModel());
//        viewHolder.carMake.setText(itemList.getMake());
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return lists.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
//    }
//
//
//    private class ItemViewHolder extends RecyclerView.ViewHolder  {
//        TextView mooveId, carMake, carModel, carYear, vehincleId;
//
//        public ItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mooveId = itemView.findViewById(R.id.mooveId);
//            carMake = itemView.findViewById(R.id.carMake);
//            carModel = itemView.findViewById(R.id.carModel);
//            carYear = itemView.findViewById(R.id.carYear);
////            itemView.setOnClickListener(this);
//        }
//    }
//
//
//    private class LoadingViewHolder extends RecyclerView.ViewHolder {
//
//        ProgressBar progressBar;
//
//        public LoadingViewHolder(@NonNull View itemView) {
//            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
//        }
//    }
//
//
//    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
//        viewHolder.progressBar.setVisibility(View.VISIBLE);
//        //ProgressBar would be displayed
//
//    }

}
