package com.mrgs.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SortRecyclerViewActivity extends RecyclerView.Adapter<SortRecyclerViewActivity.ViewHolder> {

    //Array list
    private ArrayList<Integer> mBookCover = new ArrayList<>();
    private ArrayList<String> mBookName = new ArrayList<>();
    private ArrayList<String> mBookAuthor = new ArrayList<>();
    private ArrayList<String> mBookYear = new ArrayList<>();
    private ArrayList<String> mBookType = new ArrayList<>();
    private ArrayList<String> mBookTheme = new ArrayList<>();
    private final Context mContext;

    public SortRecyclerViewActivity(Context context, ArrayList<Integer> bookCover, ArrayList<String> bookName,
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sort_recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("view holder", "View holder called");
        Glide.with(mContext).asBitmap().load(mBookCover.get(position)).into(holder.nBookCover);

        holder.nBookName.setText(mBookName.get(position));
        holder.nBookAuthor.setText(mBookAuthor.get(position));
        holder.nBookYear.setText(mBookYear.get(position));
        holder.nBookType.setText(mBookType.get(position));
        holder.nBookTheme.setText(mBookTheme.get(position));
    }

    @Override
    public int getItemCount() {
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
        LinearLayout nSortRecyclerViewLayout;


        //Item view
        public ViewHolder (View itemView) {
            super(itemView);
            nBookCover = itemView.findViewById(R.id.book_cover);
            nBookName = itemView.findViewById(R.id.book_name);
            nBookAuthor = itemView.findViewById(R.id.book_author);
            nBookYear = itemView.findViewById(R.id.book_year);
            nBookType = itemView.findViewById(R.id.book_type);
            nBookTheme = itemView.findViewById(R.id.book_theme);
            nSortRecyclerViewLayout = itemView.findViewById(R.id.sort_recyclerview_layout);

        }
    }
}
