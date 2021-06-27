package com.mrgs.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);
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
}
