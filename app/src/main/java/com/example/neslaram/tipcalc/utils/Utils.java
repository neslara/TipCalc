package com.example.neslaram.tipcalc.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    private static final String TAG = Utils.class.getName();

    public static void hideKeyboard(Activity context){
        InputMethodManager manager= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            manager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (NullPointerException ex){
            Log.e(TAG,Log.getStackTraceString(ex));
        }
    }
}
