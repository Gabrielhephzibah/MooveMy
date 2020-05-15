package com.enyata.android.mvvm_java.ui.repair.repairList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import com.enyata.android.mvvm_java.R;
import com.enyata.android.mvvm_java.data.model.api.response.InspectorData;
import com.enyata.android.mvvm_java.ui.createReport.interior.CarpetFragment;
import com.enyata.android.mvvm_java.ui.repair.repairs.RepairsActivity;

import java.util.ArrayList;
import java.util.List;

public class RepairListAdapter extends RecyclerView.Adapter<RepairListAdapter.RepairViewHolder> {
    public Context context;
    private List<RepairItemList> lists;
    ArrayList<RepairItemList> repairItemList;

    TextView mooveId, carMake, carModel, carYear;

    public RepairListAdapter(Context context, List<RepairItemList> lists) {
        this.context = context;
        this.lists = lists;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<RepairItemList> results = new ArrayList<RepairItemList>();
                if (repairItemList == null)
                    repairItemList = (ArrayList<RepairItemList>) lists;
                if (constraint != null) {
                    if (repairItemList != null && repairItemList.size() > 0) {
                        for (final RepairItemList g : repairItemList) {
                            if (g.getMake().toLowerCase()
                                    .contains(constraint.toString()) || g.getYear().toLowerCase().contains(constraint.toString()) || g.getModel().toLowerCase().contains(constraint.toString()) || g.getMooveId()
                                    .toLowerCase().contains(constraint.toString()))
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
                lists = (ArrayList<RepairItemList>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspector_history_layout, parent, false);
        return new RepairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepairViewHolder holder, int position) {
        RepairItemList itemList = lists.get(position);
        holder.mooveId.setText(itemList.getMooveId());
        holder.carYear.setText(itemList.getYear());
        holder.carModel.setText(itemList.getModel());
        holder.carMake.setText(itemList.getMake());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class RepairViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mooveId, carMake, carModel, carYear, vehincleId;

        public RepairViewHolder(@NonNull View itemView) {
            super(itemView);
            mooveId = itemView.findViewById(R.id.mooveId);
            carMake = itemView.findViewById(R.id.carMake);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            RepairItemList data = lists.get(getAdapterPosition());
            Intent intent = new Intent(context, RepairsActivity.class);
            intent.putExtra("data", data);
            context.startActivity(intent);

        }

    }


    public List<RepairItemList> getData() {
        return lists;
    }
}
