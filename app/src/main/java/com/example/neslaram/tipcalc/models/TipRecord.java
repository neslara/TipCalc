package com.example.neslaram.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neslaram on 12/06/16.
 */
public class TipRecord {
    private double bill;
    private int tipPercentage;
    private long timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip() {
        return bill * tipPercentage / 100;
    }

    public String getDateFormatted(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("MMM dd, yyyy HH:mm");
        return dateFormat.format(new Date(timestamp));

    }
}
