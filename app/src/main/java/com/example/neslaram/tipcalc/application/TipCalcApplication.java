package com.example.neslaram.tipcalc.application;

import android.app.Application;

public class TipCalcApplication extends Application {
    private final static String ABOUT_URL="https://about.me/";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
