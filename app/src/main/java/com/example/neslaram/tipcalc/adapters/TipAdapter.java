package com.example.neslaram.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neslaram.tipcalc.R;
import com.example.neslaram.tipcalc.models.TipRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private List<TipRecord> dataSet;
    private Context context;

    public TipAdapter(Context context, List<TipRecord> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
