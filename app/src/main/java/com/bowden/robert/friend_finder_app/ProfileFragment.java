package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.CustomAdapters.InterestsAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends Fragment {
    // Members
    private View view;
    private String Bio;
    private List<String> interests = new ArrayList<>();

    // Properties of fragment_profile
    private ImageButton buttonEditProfile;
    private ImageView profileImage;
    private TextView profileName;
    private TextView profileBio;
    private RecyclerView profileInterests;
    private ImageView profileMeme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileProps();
        setProfileTest();
        setProfileInterestsTest();
        initProfileRecyclerTest();
        editProfileButton();
        return view;
    }

    // Initialises all of the properties that will change from the fragment_profile.xml file
    private void profileProps() {
        buttonEditProfile = view.findViewById(R.id.editProfileButton);
        profileImage = view.findViewById(R.id.profileImage);
        profileName = view.findViewById(R.id.profileName);
        profileBio = view.findViewById(R.id.profileBio);
        profileInterests = view.findViewById(R.id.profileInterestsRecycler);
        profileMeme = view.findViewById(R.id.profileMyMeme);
        Bio = ("I love to show people my middle finger. It's my passion, " +
                "one day I will be a middle finger model and the whole world " +
                "will know what my middle finger looks like");
    }

    // Chnages the current fragment to the editProfileFragment
    private void editProfileButton() {
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                                new ProfileEditFragment()).commit();
            }
        });
    }

    // used for testing
    private void setProfileTest() {
        profileImage.setImageResource(R.drawable.myimage);
        profileBio.setText(Bio);
        profileMeme.setImageResource(R.drawable.youtube_meme);
    }

    // used to test the interests RecyclerView
    private void setProfileInterestsTest() {
        interests.add("Video games");
        interests.add("Programming");
        interests.add("Photography");
        interests.add("Netflix");
        interests.add("Reading");
        interests.add("Mobile Dev");
    }

    // The initialisation of the RecyclerView.
    private void initProfileRecyclerTest() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        profileInterests.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter(interests, view.getContext(), R.layout.layout_interest_item);
        profileInterests.setAdapter(adapter);
    }

}
