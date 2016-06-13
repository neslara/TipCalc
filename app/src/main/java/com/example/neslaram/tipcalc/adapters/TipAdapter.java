package com.example.neslaram.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neslaram.tipcalc.R;
import com.example.neslaram.tipcalc.interfaces.OnItemClickListener;
import com.example.neslaram.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private List<TipRecord> dataSet;
    private Context context;
    private OnItemClickListener clickListener;

    public TipAdapter(Context context, OnItemClickListener clickListener) {
        this.context = context;
        this.dataSet = new ArrayList<>();
        this.clickListener= clickListener;
    }

    public TipAdapter(Context context, List<TipRecord> dataSet, OnItemClickListener clickListener) {
        this.context = context;
        this.dataSet = dataSet;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord tipRecord = dataSet.get(position);

        String tipStr = String.format(context.getString(R.string.global_message_tip), tipRecord.getTip());
        holder.txtContent.setText(tipStr);
        holder.txtVwDate.setText(tipRecord.getDateFormatted());
        holder.setOnClickListener(tipRecord, position, clickListener);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addItem(TipRecord tipRecord) {
        dataSet.add(0, tipRecord);
        notifyItemInserted(0);
    }

    public void clearItems() {
        dataSet.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtContent)
        TextView txtContent;
        @BindView(R.id.txtVwDate)
        TextView txtVwDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setOnClickListener(final TipRecord record, final int position, final OnItemClickListener clickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(record, position);
                }
            });
        }
    }
}
