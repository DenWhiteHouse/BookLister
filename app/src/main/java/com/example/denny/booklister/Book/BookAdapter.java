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
    private Book mBook;

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
        if(!mBook.getItems().get(position).getVolumeInfo().getTitle().isEmpty()) {
            holder.mTitle.setText(mBook.getItems().get(position).getVolumeInfo().getTitle());
        }
        else{
            holder.mTitle.setText("");
        }
        if(mBook.getItems().get(position).getVolumeInfo().getAuthors() != null) {
            holder.mAuthor.setText(mBook.getItems().get(position).getVolumeInfo().getAuthors().get(0));
        }
        else{
            holder.mAuthor.setText("");
        }
        if(!mBook.getItems().get(position).getVolumeInfo().getPageCount().toString().isEmpty()) {
            holder.mPages.setText(mBook.getItems().get(position).getVolumeInfo().getPageCount().toString());
        }
        else{
            holder.mPages.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if (mBook == null) {
            return 0;
        }
        return mBook.getItems().size();
    }

    /**
     * When data changes, this method updates the list of books
     * and notifies the adapter to use the new values on it
     */
    public void setBook(Book book) {
        mBook = book;
        notifyDataSetChanged();
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
