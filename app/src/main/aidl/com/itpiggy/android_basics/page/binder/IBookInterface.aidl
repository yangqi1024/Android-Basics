// IBookInterface.aidl
package com.itpiggy.android_basics.page.binder;
import com.itpiggy.android_basics.page.binder.Book;

// Declare any non-default types here with import statements

interface IBookInterface {
     List<Book> getBookList();
     void insertBook(in Book book);
}