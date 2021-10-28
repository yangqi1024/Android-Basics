package com.itpiggy.android_basics.page.binder;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name;

    public Book(String name) {
    this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public void readFromParcel(Parcel parcel) {
        this.name = parcel.readString();
    }


    public static Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };

    private Book(Parcel in) {
        this.name = in.readString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
