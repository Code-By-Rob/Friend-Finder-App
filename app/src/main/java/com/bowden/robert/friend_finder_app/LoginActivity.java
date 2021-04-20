package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Props
    EditText username;
    EditText password;
    Button buttonLogin;
    Button buttonSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setProps();
        setButtonSignup();
    }

    private void setProps() {
        username = findViewById(R.id.editTextUserLogin);
        password = findViewById(R.id.editTextUserPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);
    }

    private void setButtonSignup() {
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.login_container,
                        new SignupFragment()).commit();
            }
        });
    }

}
