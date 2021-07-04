package com.mrgs.library;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IssuedRecyclerViewActivity extends RecyclerView.Adapter<IssuedRecyclerViewActivity.ViewHolder> {

    //Array list
    private ArrayList<Integer> mBookCover = new ArrayList<>();
    private ArrayList<String> mBookName = new ArrayList<>();
    private ArrayList<String> mBookAuthor = new ArrayList<>();
    private ArrayList<String> mBookYear = new ArrayList<>();
    private ArrayList<String> mBookType = new ArrayList<>();
    private ArrayList<String> mBookTheme = new ArrayList<>();
    private final Context mContext;

    //Constructor
    public IssuedRecyclerViewActivity(Context context, ArrayList<Integer> bookCover, ArrayList<String> bookName,
                                      ArrayList<String> bookAuthor, ArrayList<String> bookYear,
                                      ArrayList<String> bookType, ArrayList<String> bookTheme) {

        mBookCover = bookCover;
        mBookName = bookName;
        mBookAuthor = bookAuthor;
        mBookYear = bookYear;
        mBookType = bookType;
        mBookTheme = bookTheme;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Recycling the view holder to put in the position where I want to place in
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_issued_recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("view holder", "View holder called");

        //Hold book cover in the position I want to placed
        Integer nBookCover = mBookCover.get(position);
        Drawable drawable = mContext.getResources().getDrawable(nBookCover);

        //Hold to the book data in the position I want to placed
        holder.nBookCover.setImageDrawable(drawable);
        holder.nBookName.setText(mBookName.get(position));
        holder.nBookAuthor.setText(mBookAuthor.get(position));
        holder.nBookYear.setText(mBookYear.get(position));
        holder.nBookType.setText(mBookType.get(position));
        holder.nBookTheme.setText(mBookTheme.get(position));
    }

    @Override
    public int getItemCount() {
        //To tell how item in the list need to be display in the app
        return mBookName.size();

    }

    //Store each book's data using ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Constant
        ImageView nBookCover;
        TextView nBookName;
        TextView nBookAuthor;
        TextView nBookYear;
        TextView nBookType;
        TextView nBookTheme;
        LinearLayout nIssuedRecyclerViewLayout;


        //Item view
        public ViewHolder (View itemView) {
            super(itemView);
            //Find the variables from the id in xml and make it into item view
            nBookCover = itemView.findViewById(R.id.issued_book_cover);
            nBookName = itemView.findViewById(R.id.issued_book_name);
            nBookAuthor = itemView.findViewById(R.id.issued_book_author);
            nBookYear = itemView.findViewById(R.id.issued_book_year);
            nBookType = itemView.findViewById(R.id.issued_book_type);
            nBookTheme = itemView.findViewById(R.id.issued_book_theme);
            nIssuedRecyclerViewLayout = itemView.findViewById(R.id.issued_recyclerview_layout);
        }
    }
}
