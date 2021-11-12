package com.itpiggy.android_basics.page.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.itpiggy.android_basics.R;
import com.itpiggy.android_basics.model.MainItemModel;
import com.itpiggy.android_basics.page.binder.AidlActivity;
import com.itpiggy.android_basics.page.binder.MessengerActivity;
import com.itpiggy.android_basics.page.broadcastreceiver.LocalReceiverActivity;
import com.itpiggy.android_basics.page.handler.HandlerActivity;
import com.itpiggy.android_basics.page.handler.HandlerThreadActivity;
import com.itpiggy.android_basics.page.mmkv.MmkvActivity;
import com.itpiggy.android_basics.page.sharedpreferences.SharedPreferencesActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        List<MainItemModel> list = createData();
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(this,list);
        recyclerView.setAdapter(mainRecyclerAdapter);
    }

    private  List<MainItemModel> createData(){
        List<MainItemModel> list = new ArrayList();
        list.add(new MainItemModel("Handler", HandlerActivity.class));
        list.add(new MainItemModel("HandlerThread", HandlerThreadActivity.class));
        list.add(new MainItemModel("SharedPreference", SharedPreferencesActivity.class));
        list.add(new MainItemModel("MMKV", MmkvActivity.class));
        list.add(new MainItemModel("LocalBroadcastReceiver", LocalReceiverActivity.class));
        list.add(new MainItemModel("AIDL", AidlActivity.class));
        list.add(new MainItemModel("Messenger", MessengerActivity.class));
        return list;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("MainActivity","onSaveInstanceState");
    }
}