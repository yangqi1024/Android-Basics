package com.itpiggy.android_basics.page.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.itpiggy.android_basics.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandlerThreadActivity extends AppCompatActivity {
    public static final int UPDATE_DATE = 100;
    private AppCompatTextView tvDate;
    private AppCompatButton sendMessage;
    private AppCompatButton removeMessage;
    private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private Handler handler;
    private HandlerThread handlerThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        tvDate = findViewById(R.id.tv_date);
        sendMessage = findViewById(R.id.send_message);
        removeMessage = findViewById(R.id.remove_message);
        Date date = new Date();
        tvDate.setText(sdf.format(date));


        handlerThread = new HandlerThread(HandlerThreadActivity.class.getSimpleName());
        handlerThread.start();
        handler = new InnerHandler(this,handlerThread.getLooper());
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = handler.obtainMessage();
                message.what = UPDATE_DATE;
                handler.sendMessageDelayed(message,3000);
            }
        });

        removeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.quit();
            }
        });
    }

    public void updateText(){

        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("updateText",Thread.currentThread().getName());
                Date date = new Date();
                tvDate.setText(sdf.format(date));
            }
        });

    }

    public static class InnerHandler extends Handler{
        WeakReference<HandlerThreadActivity> weakReference;

        public InnerHandler(HandlerThreadActivity activity, Looper looper) {
            super(looper);
            weakReference = new WeakReference(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_DATE:
                    HandlerThreadActivity handlerActivity = weakReference.get();
                    if(handlerActivity != null){
                        handlerActivity.updateText();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
