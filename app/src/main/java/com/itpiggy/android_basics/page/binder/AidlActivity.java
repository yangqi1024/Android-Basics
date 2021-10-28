package com.itpiggy.android_basics.page.binder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.Toast;

import com.itpiggy.android_basics.R;

import java.util.List;

public class AidlActivity extends AppCompatActivity {
    private IBookInterface bookInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_client);
        AppCompatTextView tvResult = findViewById(R.id.tv_result);
        AppCompatEditText etName = findViewById(R.id.et_name);


        bindService(new Intent(this, BookService.class), connection,BIND_AUTO_CREATE);

        findViewById(R.id.btn_query).setOnClickListener(view->{
            try {
                List<Book> bookList = bookInterface.getBookList();
                if(bookList != null){
                    StringBuilder sb = new StringBuilder();
                    for (Book book : bookList) {
                        sb.append(book).append("\n");
                    }
                    tvResult.setText(sb.toString());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });


        findViewById(R.id.btn_insert).setOnClickListener(view->{
            String name = etName.getText().toString();
            if(TextUtils.isEmpty(name)){
                Toast.makeText(AidlActivity.this, "书名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                bookInterface.insertBook(new Book(name));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookInterface = IBookInterface.Stub.asInterface(service);
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