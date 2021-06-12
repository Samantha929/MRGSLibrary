package com.mrgs.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;

public class MeActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_me);
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
