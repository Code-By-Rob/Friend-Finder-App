package com.bowden.robert.friend_finder_app;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowden.robert.friend_finder_app.CustomAdapters.InterestsAdapter;
import com.bowden.robert.friend_finder_app.ServerClasses.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static com.bowden.robert.friend_finder_app.ServerClasses.UserProfile.userProfile;
import static java.lang.System.out;

public class Signup2Fragment extends Fragment {

    private View view;
    String bio;
    String[] images = new String[2];
    int imageNum = 0;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private View mCardBackLayout;
    private View mCardFrontLayout;
    private boolean mIsBackVisible = false;
    // Props
    private EditText editTextBio;
    private Button buttonImage1;
    private Button buttonImage2;
    private Button buttonSaveImages;
    private Button buttonSaveProfile;
    // Test Display Props
    private ImageView userImage1;
    private ImageView userImage2;
    private TextView userBio;
    private TextView userName;
    private RecyclerView userInterestsRecycler;

    private static String url_all_persons = "http://192.168.1.67/friendfindertest-phpscripts/create_person.php";
    private static String emulatorUrl = "http://10.0.2.2:80/friendfindertest-phpscripts/create_person.php";
    private byte[] profile;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_signup2, container, false);

        setProps();
        findViews();
        loadAnimations();
        changeCameraDistance();
        setButtonImage1();
        setButtonImage2();
        setButtonSaveImages();
        setButtonSaveProfile();

        return view;
    }

    private void setProps() {
        editTextBio = view.findViewById(R.id.editTextBiography);
        buttonImage1 = view.findViewById(R.id.buttonAddImage1);
        buttonImage2 = view.findViewById(R.id.buttonAddImage2);
        buttonSaveImages = view.findViewById(R.id.buttonSaveImages);
        buttonSaveProfile = view.findViewById(R.id.buttonSaveProfile);

        userImage1 = view.findViewById(R.id.userImageTestDisplay);
        userImage2 = view.findViewById(R.id.userSecondImageTestDisplay);
        userBio = view.findViewById(R.id.personBioTestDisplay);
        userName = view.findViewById(R.id.userNameAndAgeTestDisplay);
        userInterestsRecycler = view.findViewById(R.id.interestsRecyclerTestDisplay);
    }

    private String JSONParser(){
        JSONObject userProfile = new JSONObject();
        try {
            userProfile.put("username", UserProfile.userProfile.getUsername());
            userProfile.put("PASSWORD", UserProfile.userProfile.getPassword());
            userProfile.put("namefirst", UserProfile.userProfile.getNamefirst());
            userProfile.put("namelast", UserProfile.userProfile.getNamelast());
            userProfile.put("email", UserProfile.userProfile.getEmail());
            userProfile.put("dob", UserProfile.userProfile.getDob());
            userProfile.put("image1", UserProfile.userProfile.getImage1());
//            userProfile.put("image2", UserProfile.userProfile.getImage2());
            userProfile.put("bio", UserProfile.userProfile.getBio());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userProfile.toString();
    }

    private byte[] textUrlEncoder() {
        try {
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userProfile.getUsername(), "UTF-8");
            data += "&" + URLEncoder.encode("PASSWORD", "UTF-8") + "=" + URLEncoder.encode(userProfile.getPassword(), "UTF-8");
            data += "&" + URLEncoder.encode("namefirst", "UTF-8") + "=" + URLEncoder.encode(userProfile.getNamefirst(), "UTF-8");
            data += "&" + URLEncoder.encode("namelast", "UTF-8") + "=" + URLEncoder.encode(userProfile.getNamelast(), "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(userProfile.getEmail(), "UTF-8");
            data += "&" + URLEncoder.encode("dob", "UTF-8") + "=" + URLEncoder.encode(userProfile.getDob(), "UTF-8");
            data += "&" + URLEncoder.encode("image1", "UTF-8") + "=" + URLEncoder.encode(userProfile.getImage1(), "UTF-8");
            data += "&" + URLEncoder.encode("bio", "UTF-8") + "=" + URLEncoder.encode(userProfile.getBio(), "UTF-8");

            byte[] postDataBytes = null;
            postDataBytes = data.getBytes("UTF-8");

            return postDataBytes;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//        }
//    }

    // I might have to try and use the volley api.

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

    class SendUserProfile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                String response = post(emulatorUrl, JSONParser());
                Log.i("USERPROFILE", "--->" + JSONParser());
                Log.i("RESPONSE", response);
            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent main = new Intent(getActivity(), MainActivity.class);
            startActivity(main);
        }
    }

    private void setButtonSaveProfile() {
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile = textUrlEncoder();
                new SendUserProfile().execute();
            }
        });
    }

    private void setButtonImage1() {
        buttonImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();
            }
        });
    }

    private void setButtonImage2() {
        buttonImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();
            }
        });
    }

    private void setPreferences() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                // permission is not granted and we must request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                // show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                pickImageFromGallery();
            }
        } else {
            // system os is less than marshmallow
            pickImageFromGallery();
        }
    }

    private void pickImageFromGallery() {
        // intent to pick image from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    // to handle the runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void setButtonSaveImages() {
        buttonSaveImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bio = editTextBio.getText().toString();
                userProfile.setImage1(images[0]);
                userProfile.setImage2(images[1]);
                userProfile.setBio(bio);
                setTestDisplay();
                flipCard(v);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            try {
                Uri imageUri = data.getData();
                InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                Bitmap bm = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                images[imageNum] = Base64.encodeToString(b, Base64.DEFAULT);
                Log.i("Base64 Image File", "The String: " + images[imageNum]);
                imageNum++;
            } catch (FileNotFoundException | NullPointerException e) {
                // The file does not exist...
                e.printStackTrace();
                Toast.makeText(getContext(), "Please choose an image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setTestDisplay() {
        userImage1.setImageBitmap(userProfile.getImage1Bitmap());
        userImage2.setImageBitmap(userProfile.getImage2Bitmap());
        userBio.setText(userProfile.getBio());
        userName.setText(userProfile.getNamefirst());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        userInterestsRecycler.setLayoutManager(manager);
        InterestsAdapter adapter = new InterestsAdapter(userProfile.getInterests(), getContext(), R.layout.layout_interest_item);
        userInterestsRecycler.setAdapter(adapter);
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.in_animation);
    }

    private void findViews() {
        mCardBackLayout = view.findViewById(R.id.cardSignup2Back);
        mCardFrontLayout = view.findViewById(R.id.cardSignup2Front);
        mCardBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard(v);
            }
        });
        mCardFrontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard(v);
            }
        });
    }

    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
            mCardBackLayout.bringToFront();
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
            mCardFrontLayout.bringToFront();
        }
    }

}
//    RequestQueue queue = Volley.newRequestQueue(getContext());
//
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, emulatorUrl,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//
//        }
//    }){
//        protected Map<String, String> getParams() {
//            Map<String, String> param = new HashMap<>();
//            param.put("username", userProfile.getUsername());
//            param.put("PASSWORD", userProfile.getPassword());
//            param.put("namefirst", userProfile.getNamefirst());
//            param.put("namelast", userProfile.getNamelast());
//            param.put("email", userProfile.getEmail());
//            param.put("dob", userProfile.getDob());
//            param.put("image1", userProfile.getImage1());
//            param.put("bio", userProfile.getBio());
//            return param;
//        }
//    };
//            queue.add(stringRequest);
//                    queue.start();