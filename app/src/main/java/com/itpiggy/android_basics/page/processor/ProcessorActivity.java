package com.itpiggy.android_basics.page.processor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.itpiggy.android_basics.R;

import cn.unicom.viewbind.BindLayout;
import cn.unicom.viewbind.BindView;

@BindLayout
public class ProcessorActivity extends AppCompatActivity {
    @BindView(R.id.tv_processor)
    AppCompatTextView process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);
        ButterKnife.bind(this);
    }
}