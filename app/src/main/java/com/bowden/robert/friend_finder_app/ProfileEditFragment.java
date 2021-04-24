package com.bowden.robert.friend_finder_app;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowden.robert.friend_finder_app.CustomAdapters.EditInterestsAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.bowden.robert.friend_finder_app.ServerClasses.UserProfile.userProfile;

public class ProfileEditFragment extends Fragment {

    /*
    This is the profile edit fragment. I didn't spend much time here and there is work that needs to be done
    However, I can do this alongside the back-end as it is not essential right now.
     */

    // Properties of fragment_profile_edit
    private View view;
    private ImageView editProfileImage;
    private EditText editProfileName;
    private EditText editProfileBio;
    private EditText editProfileInterestSearch;
    private GridView editInterests;
    private Button buttonSave;

    // Members
    private List<String> interests = new ArrayList<>();
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    String[] images = new String[1];
    int imageNum = 0;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    private static String url_all_persons = "http://192.168.1.67/friendfindertest-phpscripts/create_person.php";
    private static String emulatorUrl = "http://10.0.2.2:80/friendfindertest-phpscripts/update_person_row.php";
    /*
     There will most likely be way more members here and also an interface to be able to communicate with any other fragments (if needed).
     These other members will be able to store the data inputted into the EditTexts for the user name and bio, etc.
     This will most likely be put into a JSON object or something of the sort to be communicated to other clients.
     So that will need to be added here when I add the onClick method for the save button.
     */

    // The onCreateView -- used to create the view inflated from the layout file in xml.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        editProfileProps();
        setEditProfileDisplay();
        setImageChange();
        setSaveButton();
        interestsTest();
        initEditInterestsAdapter();
        return view;
    }

    // this is used to initialise the properties of the xml file inflated in onCreateView.
    // Essential to changing texts and images, etc.
    private void editProfileProps() {
        editProfileImage = view.findViewById(R.id.editProfileImage);
        editProfileName = view.findViewById(R.id.editProfileName);
        editProfileBio = view.findViewById(R.id.editProfileBio);
        editProfileInterestSearch = view.findViewById(R.id.editProfileSearchInterests);
        editInterests = view.findViewById(R.id.editProfileInterestsGrid);
        buttonSave = view.findViewById(R.id.buttonSaveProfileEdit);
    }

    private void setSaveButton() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the method that sets the new userProfile details.
                setUserProfile();
                // call the Asynctask class that will update the database.
                new UpdateUserProfile().execute();
            }
        });
    }

    private String JSONParser() {
        JSONObject newUserProfile = new JSONObject();
        try {
            newUserProfile.put("id", userProfile.getID());
            newUserProfile.put("username", userProfile.getUsername());
            newUserProfile.put("namefirst", userProfile.getNamefirst());
            newUserProfile.put("namelast", userProfile.getNamelast());
            newUserProfile.put("image1", userProfile.getImage1());
            newUserProfile.put("bio", userProfile.getBio());

            return newUserProfile.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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

    class UpdateUserProfile extends AsyncTask<String, String, String> {
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
    }

    private void setUserProfile() {
        if (images[imageNum] != null) {
            userProfile.setImage1(images[imageNum]);
        }
        userProfile.setNamefirst(editProfileName.getText().toString());
        userProfile.setBio(editProfileBio.getText().toString());
        // set the user interests here.
    }

    private void setEditProfileDisplay() {
        editProfileImage.setImageBitmap(userProfile.getImage1Bitmap());
        editProfileName.setText(userProfile.getNamefirst());
        editProfileBio.setText(userProfile.getBio());
    }

    private void setImageChange() {
        editProfileImage.setOnClickListener(new View.OnClickListener() {
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

    private void pickImageFromGallery() {
        // intent to pick image from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
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
//                imageNum++;
            } catch (FileNotFoundException | NullPointerException e) {
                // The file does not exist...
                e.printStackTrace();
                Toast.makeText(getContext(), "Please choose an image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // The adapter that is set to the GridView here is explained in greater detail in the EditInterestsAdapter
    // found CustomAdapters > EditInterestsAdapter.class
    // There is a few things that need to be added here and will be done in during the back-end.
    private void initEditInterestsAdapter() {
        EditInterestsAdapter adapter = new EditInterestsAdapter(interests, view.getContext());
        editInterests.setAdapter(adapter);
        editInterests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // here you can add the interest to the user's profile
                Toast.makeText(view.getContext(), "Added interest", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // NOTE: Make sure that only 20 interests are being displayed to allow the layout to look semi-normal
    // This was added to test the adapter to see if things worked as expected.
    private void interestsTest() {
        interests.add("Gaming");
        interests.add("True Crime");
        interests.add("Fitness");
        interests.add("Travelling");
        interests.add("Xbox");
        interests.add("Lord Of The Rings");
        interests.add("Skateboarding");
        interests.add("Horror Movies");
        interests.add("Netflix");
        interests.add("Reading");
        interests.add("Programming");
        interests.add("Video Games");
        interests.add("YouTube");
        interests.add("Streaming");
        interests.add("Photography");
        interests.add("Mobile Development");
        interests.add("Skiing");
        interests.add("Snowboarding");
        interests.add("Surfing");
        interests.add("Ice Skating");
    }

}
