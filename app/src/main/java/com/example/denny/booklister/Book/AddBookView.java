package com.example.denny.booklister.Book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.denny.booklister.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBookView extends AppCompatActivity implements AddBookPresenter.View{
    @BindView(R.id.bookTitleInput)
    EditText mEditText;
    @BindView(R.id.search_book_button)
    Button mSearchButton;
    @BindView(R.id.bookResults_RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_book_progress_bar)
    ProgressBar mProgressBar;
    private AddBookPresenter mPresenter;
    private BookAdapter mAdapter;

    public AddBookView(){
        mPresenter = new AddBookPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
        setViewButtons();
    }

    public void setRecyclerView(){
        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.bookResults_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);
        //Set the Adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setViewButtons(){
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText.getText().toString().isEmpty()) {
                    mRecyclerView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), R.string.warning_missing_title, Toast.LENGTH_SHORT).show();
                } else {
                    setProgressBarVisibile();
                    mPresenter.fetchBookAPIResults(mEditText.getText().toString());
                }
            }
        });
    }

    @Override
    public void updateResults() {
        mAdapter = new BookAdapter(this);
        setRecyclerView();
        mAdapter.setBook(mPresenter.mBook);
        setProgressBarVisibilityGone();
    }

    public void setProgressBarVisibile(){
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    public void setProgressBarVisibilityGone(){
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
