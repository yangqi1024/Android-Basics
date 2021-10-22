package com.itpiggy.android_basics.page.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itpiggy.android_basics.R;

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;

    public MainRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }
}
