package com.example.neslaram.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neslaram.tipcalc.R;
import com.example.neslaram.tipcalc.activities.TipDetailActivity;
import com.example.neslaram.tipcalc.adapters.TipAdapter;
import com.example.neslaram.tipcalc.interfaces.OnItemClickListener;
import com.example.neslaram.tipcalc.interfaces.TipHistoryListListener;
import com.example.neslaram.tipcalc.models.TipRecord;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TipHistoryFragment extends Fragment implements TipHistoryListListener, OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdapter adapter;
    private Bundle args;

    public TipHistoryFragment() {
        // Required empty public constructor
    }

    public static TipHistoryFragment newInstance(Bundle bundle) {
        TipHistoryFragment fragment = new TipHistoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.addItem(record);
    }

    @Override
    public void clearList() {
        adapter.clearItems();

    }

    @Override
    public void onItemClick(TipRecord record, int position) {
        Intent intent= new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, record.getTip());
        intent.putExtra(TipDetailActivity.DATE_KEY, record.getDateFormatted());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, record.getBill());
        startActivity(intent);
    }
}
