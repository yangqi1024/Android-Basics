package com.itpiggy.android_basics.page.hilt;

import javax.inject.Inject;

public class AnalyticsAdapter {
     AnalyticsService service;

    @Inject
    AnalyticsAdapter(AnalyticsService service){
        this.service = service;
    }

}
