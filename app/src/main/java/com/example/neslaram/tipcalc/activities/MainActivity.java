package com.example.neslaram.tipcalc.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neslaram.tipcalc.R;
import com.example.neslaram.tipcalc.application.TipCalcApplication;
import com.example.neslaram.tipcalc.fragments.TipHistoryFragment;
import com.example.neslaram.tipcalc.interfaces.TipHistoryListListener;
import com.example.neslaram.tipcalc.models.TipRecord;
import com.example.neslaram.tipcalc.utils.Utils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int DEFAULT_TIP_PERCENTAGE = 10;
    private TipHistoryListListener fragmentListener;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputBill)
    EditText inputBill;
    @BindView(R.id.inputPercentage)
    EditText inputPercentage;
    @BindView(R.id.txtVwTip)
    TextView txtTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        TipHistoryFragment fragment = (TipHistoryFragment) getSupportFragmentManager().findFragmentById(R.id.historyFragment);
        fragment.setRetainInstance(true);
        fragmentListener = fragment;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.bttnCalculate)
    public void calculateOnClick() {
        Utils.hideKeyboard(this);
        String totalBill = inputBill.getText().toString().trim();
        if (!totalBill.isEmpty()) {
            double total = Double.parseDouble(totalBill);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord= new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date().getTime());


            String tipStr = String.format(getString(R.string.global_message_tip, tipRecord.getTip()));

            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(tipStr);

        }
    }

    @OnClick(R.id.bttnClear)
    public void clearOnClick(){
        fragmentListener.clearList();

    }

    @OnClick(R.id.bttnDecrease)
    public void decreaseOnClick() {
        Utils.hideKeyboard(this);
        int percentage = getTipPercentage();
        if (percentage > 0) {
            inputPercentage.setText(String.valueOf(percentage - 1));
        }
    }

    @OnClick(R.id.bttnIncrease)
    public void increaseOnClick() {
        Utils.hideKeyboard(this);
        inputPercentage.setText(String.valueOf(getTipPercentage() + 1));
    }

    private void about() {
        TipCalcApplication application = (TipCalcApplication) getApplication();
        String aboutUrl = application.getAboutUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(aboutUrl));
        startActivity(intent);
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String percentageStr = inputPercentage.getText().toString();
        if (!percentageStr.isEmpty()) {
            return Integer.parseInt(percentageStr);
        } else {
            inputPercentage.setText(String.valueOf(tipPercentage));
            return tipPercentage;
        }
    }
}
