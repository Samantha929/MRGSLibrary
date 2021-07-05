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
    Button mLogin;
    ProgressBar login_progressBar;

    //Firebase authentication (auth)
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Find the variables from the id in xml
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        mLogin = findViewById(R.id.login_button);
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
        Intent registerPage = new Intent(this, com.mrgs.library.RegisterActivity.class);
        finish();
        startActivity(registerPage);
    }

    //Attempt login function
    private void attemptLogin() {

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
        }else if (emailLengthShort(email)) {
            mEmailView.setError(getString(R.string.email_short));
            focusView = mEmailView;
            cancel = true;
        } else if (emailLengthLong(email)) {
            mEmailView.setError(getString(R.string.email_long));
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
    //Check if the email is valid
    private boolean isEmailValid(String email) {
        //Checking logic here.
        return email.contains("@students.mrgs.school.nz");
    }

    //Check if the email length are too long
    private boolean emailLengthLong(String email) {
        return email.length() > 29;
    }

    //Check if the email length are too sjort
    private boolean emailLengthShort(String email) {
        return email.length() < 29;
    }

    //Check if the password is longer than 8 characters
    private boolean isPasswordValid(String password) {
        password = mPasswordView.getText().toString();
        //Only return true if its the same as first entry and length is over or equal to 8 characters
        return password.length() >= 8;

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
                                //Message to tell the end-user that they login success
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                                //Go to MainChatActivity page
                                Intent homePage = new Intent(LoginActivity.this, com.mrgs.library.MainChatActivity.class);
                                finish();
                                startActivity(homePage);
                            } else {
                                //Message to tell the end-user that they login fail
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                //Make the progress bar gone after shower the toast message
                                login_progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }

}