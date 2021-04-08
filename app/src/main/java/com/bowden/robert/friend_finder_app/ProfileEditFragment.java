package com.bowden.robert.friend_finder_app;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowden.robert.friend_finder_app.CustomAdapters.EditInterestsAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditFragment extends Fragment {

    // Properties of fragment_profile_edit
    private View view;
    private ImageView editProfileImage;
    private EditText editProfileName;
    private EditText editProfileBio;
    private EditText editProfileInterestSearch;
    private GridView editInterests;

    // Members
    private List<String> interests = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        editProfileProps();
        interestsTest();
        initEditInterestsAdapter();
        return view;
    }

    private void editProfileProps() {
        editProfileImage = view.findViewById(R.id.editProfileImage);
        editProfileName = view.findViewById(R.id.editProfileName);
        editProfileBio = view.findViewById(R.id.editProfileBio);
        editProfileInterestSearch = view.findViewById(R.id.editProfileSearchInterests);
        editInterests = view.findViewById(R.id.editProfileInterestsGrid);
    }

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
