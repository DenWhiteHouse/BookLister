package com.example.denny.booklister.Book;

import android.content.Context;
import android.view.View;

import com.example.denny.booklister.BookData.Book;

public class AddBookPresenter {
    Book mBook;
    BookUtil mBookUtil;
    Context mContext;
    View mView;

    public AddBookPresenter(Context context, View view){
        mBookUtil = new BookUtil(context);
        mContext =context;
        mView =view;
    }

    public void fetchBookAPIResults(String bookTitle){
     mBookUtil.getBookJSON(bookTitle);
    }

    public interface View{
        void updateResults();
    }
}
