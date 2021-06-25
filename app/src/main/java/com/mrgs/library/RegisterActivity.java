package com.mrgs.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    // Constants
    public static final String ME_PREFS = "MePrefs";
    public static final String DISPLAY_NAME_KEY = "username";
    public static final String DISPLAY_EMAIL_KEY = "email";

    public static final String Email = "";

    // UI references variables
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    ProgressBar register_progressBar;

    //Firebase authentication (auth)
    private FirebaseAuth mAuth;

    //Shared preferences
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = findViewById(R.id.register_email);
        mPasswordView = findViewById(R.id.register_password);
        mConfirmPasswordView = findViewById(R.id.register_confirm_password);
        mUsernameView = findViewById(R.id.register_username);
        register_progressBar = findViewById(R.id.register_progressBar);

        // Keyboard sign in action
        mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.register_form_finished || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });

        //Instance firebase auth
        mAuth = FirebaseAuth.getInstance();

        //Using key to get Shared Preferences
        sharedPref = getSharedPreferences(ME_PREFS, MODE_PRIVATE);
    }

    // Executed when Sign in button pressed
    public void backLoginActivity(View v) {
        Intent loginPage = new Intent(this, com.mrgs.library.LoginActivity.class);
        finish();
        startActivity(loginPage);
    }

    // Executed when Sign Up button is pressed.
    public void signUp(View v) {
        attemptRegistration();
    }

    private void attemptRegistration() {

        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mUsernameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String username = mUsernameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //Check if the username are empty
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_too_short));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordNotMatch(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
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
            createFirebaseUser();

        }
    }

    private boolean isUsernameValid(String username) {
        username = mUsernameView.getText().toString();
        //Cannot have name longer than 20
        return username.length()<=20;
    }

    private boolean isEmailValid(String email) {
        //Checking logic here
        return email.contains("@students.mrgs.school.nz");
    }

    private boolean isPasswordValid(String password) {
        //Only return true if its the same as first entry and length is over or equal to 8 characters
        return password.length()>=8;
    }

    private boolean isPasswordNotMatch(String password) {
        String confirmPassword = mConfirmPasswordView.getText().toString();
        return confirmPassword.equals(password);
    }

    //Create firebase user
    private void createFirebaseUser() {
        String username = mUsernameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        //Showing visible register progress bar when signing up
        register_progressBar.setVisibility(View.VISIBLE);

        //Executed when is complete to sign in
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Get the information that user input, then use Shared preferences to display in other activity
                           sharedPref.edit().putString(DISPLAY_NAME_KEY, username).apply();
                           sharedPref.edit().putString(DISPLAY_EMAIL_KEY, email).apply();

                            Log.d("firebase", "createUser onComplete;" + task.isSuccessful());
                            Toast.makeText(getApplicationContext(), "Registered Success", Toast.LENGTH_SHORT).show();
                            Intent homePage = new Intent(RegisterActivity.this, com.mrgs.library.MainChatActivity.class);
                            finish();
                            startActivity(homePage);
                        } else {
                            Log.d("firebase", "user create failed");
                        Toast.makeText(getApplicationContext(), "Registered Failed", Toast.LENGTH_SHORT).show();
                        register_progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}
