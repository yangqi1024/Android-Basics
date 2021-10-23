package com.itpiggy.android_basics.page.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.itpiggy.android_basics.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    public static final String TAG = SharedPreferencesActivity.class.getSimpleName();
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);
        SharedPreferences sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        AppCompatTextView tvListenResult = findViewById(R.id.tv_listen_result);
        AppCompatTextView tvResult = findViewById(R.id.tv_result);
        AppCompatEditText etKey = findViewById(R.id.et_key);
        AppCompatEditText etValue = findViewById(R.id.et_value);
        AppCompatEditText etGetKey = findViewById(R.id.et_get_key);
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String key = etKey.getText().toString();
            if (TextUtils.isEmpty(key)) {
                Toast.makeText(SharedPreferencesActivity.this, "Key不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String value = etValue.getText().toString();
            if (TextUtils.isEmpty(value)) {
                Toast.makeText(SharedPreferencesActivity.this, "Value不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            edit.putString(key, value).apply();
        });

        findViewById(R.id.btn_get).setOnClickListener(v -> {
            String key = etGetKey.getText().toString();
            if (TextUtils.isEmpty(key)) {
                Toast.makeText(SharedPreferencesActivity.this, "Key不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String value = sharedPreferences.getString(key, "默认值");
            tvResult.setText(String.format("Key:%s,Value:%s", key, value));
        });

        findViewById(R.id.btn_listen).setOnClickListener(v -> {
            listener = (sharedPreferences1, key) -> {
                String value = sharedPreferences1.getString(key, "默认值");
                tvListenResult.setText(String.format("Key:%s,Value:%s", key, value));
            };
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        });

        findViewById(R.id.btn_remove_listen).setOnClickListener(v -> {
            if (listener != null) {
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
            }
        });
    }


}
