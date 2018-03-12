package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener{
   static EditText passwordEditText;
    RelativeLayout bRelativeLayout;
    ImageView logoImageView;
    Button button;
    static  EditText usernameEditText;


    public void showUserList(){
        if(ParseUser.getCurrentUser() != null){
       Intent intent= new Intent(getApplicationContext(),ListActivity.class);
        startActivity(intent);
        Log.i("Info", "newActivity wil start now");
    }
    }


    public void signUp(View view) {

     usernameEditText = (EditText) findViewById(R.id.usernameEditText);

        if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Username or Password field is required.", Toast.LENGTH_LONG).show();
        } else {
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(),
                    new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Congratulations! You have Logged in successfully", Toast.LENGTH_SHORT).show();
                        Log.i("LoginStatus", "Successful");
                        showUserList();
                    } else {
                       ParseUser  user2 = new ParseUser();
                        user2.setUsername(usernameEditText.getText().toString());
                        user2.setPassword(passwordEditText.getText().toString());
                        user2.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException ee) {
                                if (ee == null) {
                                    Toast.makeText(getApplicationContext(), "Congratulations! You have signed in successfully", Toast.LENGTH_SHORT).show();
                                     Log.i("SignupStatus", "Successful");
                                    showUserList();
                                } else {
                                    Toast.makeText(getApplicationContext(), ee.getMessage().substring(ee.getMessage().indexOf(" ")), Toast.LENGTH_SHORT).show();
                                    Log.i("errorMsg", ee.getMessage());
                                }

                            }
                        });
                    }
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showUserList();
      //  ParseUser.getCurrentUser().logOut();

        passwordEditText= (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setOnKeyListener(this);
        bRelativeLayout=(RelativeLayout)findViewById(R.id.bRelativeLayout);
        logoImageView= (ImageView)findViewById(R.id.logoImageView);
        bRelativeLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);
        button=(Button) findViewById(R.id.button);

        setTitle("StartTweetUp");

        if(ParseUser.getCurrentUser() != null){
            showUserList();

        }




        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()== KeyEvent.ACTION_DOWN){
            signUp(view);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.bRelativeLayout || view.getId()== R.id.logoImageView){
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
    }
}
