package com.mrgs.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainChatActivity extends AppCompatActivity {

    //Firebase authentication (auth)
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        //Instance firebase auth
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sort button pressed
    public void openSortfromHome(View v) {
        Intent sort = new Intent(MainChatActivity.this, com.mrgs.library.SortActivity.class);
        finish();
        startActivity(sort);
    }

    // Executed when My Book button pressed
    public void openMyBookfromHome(View v) {
        Intent mybook = new Intent(MainChatActivity.this, com.mrgs.library.MyBookActivity.class);
        finish();
        startActivity(mybook);
    }

    // Executed when Me button pressed
    public void openMefromHome(View v) {
        Intent me = new Intent(MainChatActivity.this, com.mrgs.library.MeActivity.class);
        finish();
        startActivity(me);
    }

    @Override
    public void onStop() {
        super.onStop();
        //Remove the firebase
        if(mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }

    }

}
