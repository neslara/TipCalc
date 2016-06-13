package com.example.neslaram.tipcalc.interfaces;

import com.example.neslaram.tipcalc.models.TipRecord;

public interface TipHistoryListListener {
    void addToList(TipRecord record);

    void clearList();
}
