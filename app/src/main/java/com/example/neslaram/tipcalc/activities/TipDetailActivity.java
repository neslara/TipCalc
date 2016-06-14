package com.example.neslaram.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.neslaram.tipcalc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @BindView(R.id.txtVwBillTotal)
    TextView txtVwBillTotal;
    @BindView(R.id.txtVwTip)
    TextView txtVwTip;
    @BindView(R.id.txtVwTimeStamp)
    TextView txtVwTimeStamp;

    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String totalStr = String.format(getString(R.string.tipdetail_message_bill), intent.getDoubleExtra(BILL_TOTAL_KEY, 0));
        String tipStr = String.format(getString(R.string.tipdetail_message_bill), intent.getDoubleExtra(TIP_KEY, 0));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        txtVwTimeStamp.setText(intent.getStringExtra(DATE_KEY));
        txtVwBillTotal.setText(totalStr);
        txtVwTip.setText(tipStr);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
