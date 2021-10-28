package com.itpiggy.android_basics.page.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.itpiggy.android_basics.R;

public class LocalReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_receiver);
       AppCompatTextView tvResult =  findViewById(R.id.tv_result);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        LocalReceiver localReceiver = new LocalReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                tvResult.setText("接收广播："+intent.getAction());
            }
        };
        findViewById(R.id.btn_register).setOnClickListener((view)->{
            manager.registerReceiver(localReceiver,new IntentFilter("LocalReceiver"));
        });

        findViewById(R.id.btn_unregister).setOnClickListener((view)->{
            manager.unregisterReceiver(localReceiver);
            tvResult.setText("");
        });

        findViewById(R.id.btn_send).setOnClickListener((view)->{
            manager.sendBroadcast(new Intent("LocalReceiver"));
        });
    }
}