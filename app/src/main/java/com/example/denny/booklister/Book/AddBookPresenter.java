package com.example.denny.booklister.Book;

import android.content.Context;
import android.view.View;

import com.example.denny.booklister.BookData.Book;

import java.util.List;

public class AddBookPresenter {
    Book mBook;
    List<Book> mBookList;
    BookUtil mBookUtil;
    Context mContext;
    View mView;

    public AddBookPresenter(Context context, View view){
        mContext =context;
        mView =view;
        mBookUtil = new BookUtil(context,this);
    }

    public void fetchBookAPIResults(String bookTitle){
        mBookUtil.getBookJSON(bookTitle);
    }

    public interface View{
        void updateResults();
    }
}
