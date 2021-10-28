package com.itpiggy.android_basics.page.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookService extends Service {

    private CopyOnWriteArrayList<Book> list ;

    @Override
    public void onCreate() {
        super.onCreate();
        list = new CopyOnWriteArrayList<>();
        list.add(new Book("Java虚拟机"));
        list.add(new Book("第一行代码"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IBookInterface.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return list;
        }

        @Override
        public void insertBook(Book book) throws RemoteException {
            list.add(book);
        }
    };
}
