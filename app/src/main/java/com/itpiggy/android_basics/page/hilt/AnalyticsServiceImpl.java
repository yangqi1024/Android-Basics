package com.itpiggy.android_basics.page.hilt;

import android.util.Log;

import javax.inject.Inject;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Inject
    AnalyticsServiceImpl() {

    }

    @Override
    public void analyticsMethods() {
        Log.d("AnalyticsServiceImpl","analyticsMethods");
    }
}
