package com.mrgs.library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyBookActivity extends AppCompatActivity {

    //Array list
    private final ArrayList<Integer> mBookCover = new ArrayList<Integer>();
    private final ArrayList<String> mBookName = new ArrayList<>();
    private final ArrayList<String> mBookAuthor = new ArrayList<>();
    private final ArrayList<String> mBookYear = new ArrayList<>();
    private final ArrayList<String> mBookType = new ArrayList<>();
    private final ArrayList<String> mBookTheme = new ArrayList<>();
    public static int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        //Call dataBook() here so it can show in the activity_sort.xml
        dataBook();
    }

    // Executed when Home button pressed
    public void openHomefromMyBook(View v) {
        Intent home = new Intent(MyBookActivity.this, com.mrgs.library.MainChatActivity.class);
        finish();
        startActivity(home);
    }

    // Executed when Sort button pressed
    public void openSortfromMyBook(View v) {
        Intent sort = new Intent(MyBookActivity.this, com.mrgs.library.SortActivity.class);
        finish();
        startActivity(sort);
    }

    // Executed when Me button pressed
    public void openMefromMyBook(View v) {
        Intent me = new Intent(MyBookActivity.this, com.mrgs.library.MeActivity.class);
        finish();
        startActivity(me);
    }

    //Data for the book
    @SuppressLint("LongLogTag")
    private void dataBook() {
        Log.d("Data", "Loading for data......");

        mBookCover.add(R.drawable.harrypotter_bookcover);
        mBookName.add("Harry Potter and the Philosopher's Stone");
        mBookAuthor.add("J.K.Rowling");
        mBookYear.add("1997");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Fantasy");

        mBookCover.add(R.drawable.thesecretlifeofbees_bookcover);
        mBookName.add("The Secret Life of Bees");
        mBookAuthor.add("Sue Monk Kidd");
        mBookYear.add("2001");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Historical");

        mBookCover.add(R.drawable.yourname_bookcover);
        mBookName.add("Your Name");
        mBookAuthor.add("Makoto Shinkai");
        mBookYear.add("2016");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Love");

        mBookCover.add(R.drawable.thegiver_bookcover);
        mBookName.add("The Giver");
        mBookAuthor.add("Lois Lowry");
        mBookYear.add("1993");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Suspense");

        mBookCover.add(R.drawable.trash_bookcover);
        mBookName.add("Trash");
        mBookAuthor.add("Andy Mulligan");
        mBookYear.add("2010");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Imaginary");

        mBookCover.add(R.drawable.hanako_bookcover);
        mBookName.add("Jibaku Shonen Hanako-kun");
        mBookAuthor.add("Aidairo");
        mBookYear.add("2015");
        mBookType.add("Manga, Fiction");
        mBookTheme.add("Imaginary");

        mBookCover.add(R.drawable.thehungergames_bookcover);
        mBookName.add("The Hunger Games");
        mBookAuthor.add("Suzanne Collins");
        mBookYear.add("2008");
        mBookType.add("Novel, Fiction");
        mBookTheme.add("Adventure");

        recyclerview();
    }

    //Set the book to display in the recycler view
    private void recyclerview() {
        Log.d("recycler view", "Connecting to recycler view");

        //Find the variables from the id in xml
        RecyclerView issuedRecyclerView = findViewById(R.id.issued_recyclerview);
        RecyclerView overdueRecyclerView = findViewById(R.id.overdue_recyclerview);

        //If function for when end-user click Issued button or Overdue button
        if (i==1){
            //Set the visibility for the recycler view
            issuedRecyclerView.setVisibility(View.VISIBLE);
            overdueRecyclerView.setVisibility(View.GONE);

            //The adapter for the issued recyclerview
            IssuedRecyclerViewActivity adapter = new IssuedRecyclerViewActivity(this,
                    mBookCover, mBookName, mBookAuthor, mBookYear, mBookType, mBookTheme);
            issuedRecyclerView.setAdapter(adapter);
            issuedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else if (i==2){
            //Set the visibility for the recycler view
            overdueRecyclerView.setVisibility(View.VISIBLE);
            issuedRecyclerView.setVisibility(View.GONE);

            //The adapter for the issued recyclerview
            OverdueRecyclerViewActivity adapter = new OverdueRecyclerViewActivity(this,
                    mBookCover, mBookName, mBookAuthor, mBookYear, mBookType, mBookTheme);
            overdueRecyclerView.setAdapter(adapter);
            overdueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    // Executed when Issued button pressed
    public void issuedBook(View v){
        i = 1;
        recyclerview();
    }

    // Executed when Overdue button pressed
    public void overdueBook(View v){
        i = 2;
        recyclerview();
    }
}
