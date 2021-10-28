package com.itpiggy.android_basics.page.binder;

import static com.itpiggy.android_basics.page.binder.BookMessengerService.MSG_KEY_DATA;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.itpiggy.android_basics.R;

import java.util.List;

public class MessengerActivity extends AppCompatActivity {
    private Messenger remoteMessenger;
    private Messenger clientMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_client);
        AppCompatTextView tvResult = findViewById(R.id.tv_result);
        AppCompatEditText etName = findViewById(R.id.et_name);
        clientMessenger = new Messenger(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == BookMessengerService.MSG_QUERY) {
                    Bundle data = msg.getData();
                    data.setClassLoader(getClass().getClassLoader());
                    List<Book> bookList = data.getParcelableArrayList(MSG_KEY_DATA);
                    if (bookList != null) {
                        StringBuilder sb = new StringBuilder();
                        for (Book book : bookList) {
                            sb.append(book).append("\n");
                        }
                        tvResult.setText(sb.toString());
                    }
                }
            }
        });

        bindService(new Intent(this, BookMessengerService.class), connection, BIND_AUTO_CREATE);

        findViewById(R.id.btn_query).setOnClickListener(view -> {
            try {

                Message obtain = Message.obtain();
                obtain.what = BookMessengerService.MSG_QUERY;
                obtain.replyTo = clientMessenger;
                remoteMessenger.send(obtain);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });


        findViewById(R.id.btn_insert).setOnClickListener(view -> {
            String name = etName.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(MessengerActivity.this, "书名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Message obtain = Message.obtain();
                obtain.what = BookMessengerService.MSG_INSERT;
                Bundle bundle = new Bundle();
                bundle.putParcelable(MSG_KEY_DATA,new Book(name));
                obtain.setData(bundle);
                remoteMessenger.send(obtain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MessengerActivity ", "onServiceConnected");
            remoteMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}