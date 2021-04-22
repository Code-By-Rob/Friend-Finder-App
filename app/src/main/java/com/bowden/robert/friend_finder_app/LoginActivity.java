package com.bowden.robert.friend_finder_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bowden.robert.friend_finder_app.ServerClasses.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    // Props
    EditText username;
    EditText password;
    Button buttonLogin;
    Button buttonSignup;

    private static String url_all_persons = "http://192.168.1.67/friendfindertest-phpscripts/create_person.php";
    private static String emulatorUrl = "http://10.0.2.2:80/friendfindertest-phpscripts/get_user_login.php";
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setProps();
        setButtonSignup();
        setButtonLogin();
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

    private String JSONParser() {
        JSONObject login = new JSONObject();
        try {
            login.put("username", username.getText().toString());
            login.put("PASSWORD", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return login.toString();
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    class LoginUserProfile extends AsyncTask<String, String, String> {

        String response;

        @Override
        protected String doInBackground(String... strings) {

            try {
                response = post(emulatorUrl, JSONParser());
                Log.i("USERPROFILE", "--->" + JSONParser());
                Log.i("RESPONSE", response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // here I will call a method that will parse the responded JSON to the userProfile.
            super.onPostExecute(s);
            try {
                JSONResponse(response);
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void JSONResponse(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONArray userProfile = root.getJSONArray("userProfile");
        JSONArray profileInfo = userProfile.getJSONArray(0);
//        String id = (String) profileInfo.get(0);
        UserProfile.userProfile.setID(profileInfo.getInt(0));
        UserProfile.userProfile.setUsername(profileInfo.getString(1));
        UserProfile.userProfile.setPassword(profileInfo.getString(2));
        UserProfile.userProfile.setNamefirst(profileInfo.getString(3));
        UserProfile.userProfile.setNamelast(profileInfo.getString(4));
        UserProfile.userProfile.setEmail(profileInfo.getString(5));
        UserProfile.userProfile.setDob(profileInfo.getString(6));
        UserProfile.userProfile.setImage1(profileInfo.getString(7));
        UserProfile.userProfile.setBio(profileInfo.getString(8));
    }

    private void setButtonLogin() {
        // here I need to run a background task that will run the get_user_login.php
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginUserProfile().execute();
            }
        });
    }

}
