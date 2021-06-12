package com.mrgs.library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    // UI references variables
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mSignIn;
    ProgressBar login_progressBar;

    //Firebase authentication (auth)
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        mSignIn = findViewById(R.id.login_sign_in_button);
        login_progressBar = findViewById(R.id.login_progressBar);

        //Set on editor action listener for keyboard sign in action
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //Instance firebase auth
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   { attemptLogin(); }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.mrgs.library.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    //Attempt login function
    private void attemptLogin() {

        //Using firebase auth to sign with email and password
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                //Showing visible login progress bar when signing in
                login_progressBar.setVisibility((View.VISIBLE));

                //Executed when is success to sign in
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Sign in Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, com.mrgs.library.MainChatActivity.class);
                                    finish();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Sign in Failed", Toast.LENGTH_SHORT).show();
                                    login_progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });

        //Error message

        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_too_short));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            login();
        }
    }
    private boolean isEmailValid(String email) {
        //Checking logic here.
        return email.contains("@students.mrgs.school.nz");
    }

    private boolean isPasswordValid(String password) {
        password = mPasswordView.getText().toString();
        //Only return true if its the same as first entry and length is over or equal to 8 characters
        return password.equals(password) && password.length() >= 8;

    }

    //Using firebase auth to sign with email and password
        private void login() {
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            //Showing visible login progress bar when signing in
            login_progressBar.setVisibility((View.VISIBLE));

            //Executed when is success to sign in
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Sign in Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, com.mrgs.library.MainChatActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Sign in Failed", Toast.LENGTH_SHORT).show();
                                login_progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }

}