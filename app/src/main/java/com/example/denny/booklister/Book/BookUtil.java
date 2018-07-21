package com.example.denny.booklister.Book;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.denny.booklister.BookData.Book;
import com.example.denny.booklister.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookUtil {
    Book mBook;
    Context mContext;

    public BookUtil(Context context){
        mContext = context;
        mBook = new Book();
    }

    public Book getBookJSON(String bookTitle) {
        //Getting the JSON Object with Retrofit
        BooksInterface booksInterface = RetrofitClient.getRetrofitInstance();
        Call<Book> book = booksInterface.getBooks(bookTitle);

        book.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body().getItems() != null) {
                    mBook = response.body();
                    }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(mContext, R.string.error_message_retrofit, Toast.LENGTH_SHORT).show();
                Log.v("Retrofit has failed ", t.getMessage());
            }
        });

        return mBook;
    }

}
