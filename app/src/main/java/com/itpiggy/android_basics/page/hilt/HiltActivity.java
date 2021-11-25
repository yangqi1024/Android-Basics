package com.itpiggy.android_basics.page.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itpiggy.android_basics.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HiltActivity extends AppCompatActivity {
    @Inject
    AnalyticsAdapter analyticsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        analyticsAdapter.service.analyticsMethods();
    }
}