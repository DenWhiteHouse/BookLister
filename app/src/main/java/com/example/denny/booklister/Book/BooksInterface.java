package com.example.denny.booklister.Book;


import com.example.denny.booklister.BookData.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksInterface {
    @GET("volumes")
    public Call<Book> getBooks(
            @Query("q") String booktitle
    );
}
