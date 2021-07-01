package com.mrgs.library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;

import static com.mrgs.library.RegisterActivity.DISPLAY_EMAIL_KEY;
import static com.mrgs.library.RegisterActivity.DISPLAY_NAME_KEY;
import static com.mrgs.library.RegisterActivity.ME_PREFS;

public class MeActivity extends AppCompatActivity {

    // UI references variables
    TextView mMe_username;
    TextView mMe_email;

        @SuppressLint("LongLogTag")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_me);
            Intent intent = getIntent();

            //Find the variables from the id in xml
            mMe_username = findViewById(R.id.me_username);
            mMe_email = findViewById(R.id.me_email);

            //Using key to get Shared Preferences
            SharedPreferences sharedPref = getSharedPreferences(ME_PREFS, MODE_PRIVATE);

            //Set the text from user's input into the enter box
            mMe_username.setText(sharedPref.getString(DISPLAY_NAME_KEY, ""));
            mMe_email.setText(sharedPref.getString(DISPLAY_EMAIL_KEY, ""));

        }

    // Executed when Home button pressed
    public void openHomefromMe(View v) {
        Intent home = new Intent(MeActivity.this, com.mrgs.library.MainChatActivity.class);
        finish();
        startActivity(home);
    }

    // Executed when Sort button pressed
    public void openSortfromMe(View v) {
        Intent sort = new Intent(MeActivity.this, com.mrgs.library.SortActivity.class);
        finish();
        startActivity(sort);
    }

    // Executed when My Book button pressed
    public void openMyBookfromMe(View v) {
        Intent mybook = new Intent(MeActivity.this, com.mrgs.library.MyBookActivity.class);
        finish();
        startActivity(mybook);
    }

    // Executed when logout button pressed
    public void attemptLogout(View v) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MeActivity.this, com.mrgs.library.LoginActivity.class);
        finish();
        startActivity(intent);
    }
}
