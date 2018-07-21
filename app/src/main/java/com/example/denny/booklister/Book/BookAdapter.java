package com.example.denny.booklister.Book;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.denny.booklister.BookData.Book;
import com.example.denny.booklister.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context mContext;
    private List<Book> mBookList;

    public BookAdapter(Context context){
        mContext = context;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        final Book book = mBookList.get(position);
        final Integer listposition = position;
        holder.mTitle.setText(book.getItems().get(0).getVolumeInfo().getTitle().toString());
        holder.mAuthor.setText(book.getItems().get(0).getVolumeInfo().getAuthors().get(0).toString());
        holder.mPages.setText(book.getItems().get(0).getVolumeInfo().getPageCount().toString());
    }

    @Override
    public int getItemCount() {
        if (mBookList == null) {
            return 0;
        }
        return mBookList.size();
    }

    /**
     * When data changes, this method updates the list of books
     * and notifies the adapter to use the new values on it
     */
    public void setBooks(List<Book> books) {
        mBookList = books;
        notifyDataSetChanged();
    }

    public List<Book> getBookList() {
        return mBookList;
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mPages;

        public BookViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.bookTitle);
            mAuthor = itemView.findViewById(R.id.bookAuthor);
            mPages = itemView.findViewById(R.id.bookPages);
        }
    }

}
