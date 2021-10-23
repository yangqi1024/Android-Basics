package com.itpiggy.android_basics.page.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.itpiggy.android_basics.R;
import com.itpiggy.android_basics.model.MainItemModel;
import com.itpiggy.android_basics.page.handler.HandlerActivity;
import com.itpiggy.android_basics.page.handler.HandlerThreadActivity;
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
        return list;
    }
}