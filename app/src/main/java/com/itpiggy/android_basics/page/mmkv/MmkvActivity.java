package com.itpiggy.android_basics.page.mmkv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itpiggy.android_basics.R;
import com.itpiggy.android_basics.page.sharedpreferences.SharedPreferencesActivity;
import com.tencent.mmkv.MMKV;

public class MmkvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        SharedPreferences sharedPreferences = getSharedPreferences("MmkvActivity", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        MMKV mmkv = MMKV.defaultMMKV();
        AppCompatEditText etNumber = findViewById(R.id.et_number);
        AppCompatTextView tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String key = etNumber.getText().toString();
            if (TextUtils.isEmpty(key)) {
                Toast.makeText(MmkvActivity.this, "数量不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            int count = Integer.parseInt(key);
            long mmkvTime = castTime(() -> {
                for (int i = 0; i < count; i++) {
                    mmkv.encode("MmkvActivity-mmkv-key:" + i, "MmkvActivity-value:" + i);
                }
            });
            Log.i("MmkvActivity","mmkvTime:"+mmkvTime);
            long spTime = castTime(() -> {
                for (int i = 0; i < count; i++) {
                    edit.putString("MmkvActivity-sp-key:" + i, "MmkvActivity-value:" + i);
                }
                edit.commit();
            });
            Log.i("MmkvActivity","spTime:"+spTime);
            tvResult.setText(String.format("MMKV 耗时：%d ms，\nSharedPreference 耗时：%d ms", mmkvTime, spTime));
        });

        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            edit.clear();
            edit.commit();

            mmkv.clearAll();
            tvResult.setText("");
        });
        registerReceiver()
    }

    private long castTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }
}