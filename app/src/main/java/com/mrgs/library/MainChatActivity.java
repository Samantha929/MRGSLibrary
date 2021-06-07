package com.mrgs.library;

import android.os.Bundle;
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



    @Override
    public void onStop() {
        super.onStop();
        //Remove the firebase
        if(mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }

    }

}
