package com.itpiggy.android_basics.page.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookMessengerService extends Service {
    public static final int MSG_QUERY = 0;
    public static final int MSG_INSERT = 1;
    public static final String MSG_KEY_DATA = "books";
    private ArrayList<Book> list;

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
        list.add(new Book("Java虚拟机"));
        list.add(new Book("第一行代码"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case MSG_INSERT:
                    Bundle data = msg.getData();
                    data.setClassLoader(getClass().getClassLoader());
                    Book book = data.getParcelable(MSG_KEY_DATA);
                    list.add(book);
                    break;
                case MSG_QUERY:
                    Messenger replyTo = msg.replyTo;
                    Message obtain = Message.obtain();
                    try {
                        obtain.what = MSG_QUERY;
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(MSG_KEY_DATA, list);
                        obtain.setData(bundle);
                        replyTo.send(obtain);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    });
}
