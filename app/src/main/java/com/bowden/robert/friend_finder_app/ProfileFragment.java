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

import static com.bowden.robert.friend_finder_app.ServerClasses.UserProfile.userProfile;

public class ProfileFragment extends Fragment {
    // Members
    private View view;

    // Properties of fragment_profile
    private ImageButton buttonEditProfile;
    private ImageView profileImage1;
    private TextView profileName;
    private TextView profileBio;
    private RecyclerView profileInterests;
    private ImageView profileImage2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileProps();
        setProfileTest();
        initProfileRecyclerTest();
        editProfileButton();
        return view;
    }

    // Initialises all of the properties that will change from the fragment_profile.xml file
    private void profileProps() {
        buttonEditProfile = view.findViewById(R.id.editProfileButton);
        profileImage1 = view.findViewById(R.id.profileImage);
        profileName = view.findViewById(R.id.profileName);
        profileBio = view.findViewById(R.id.profileBio);
        profileInterests = view.findViewById(R.id.profileInterestsRecycler);
        profileImage2 = view.findViewById(R.id.profileMyMeme);
    }

    // Changes the current fragment to the editProfileFragment
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
        profileImage1.setImageBitmap(userProfile.getImage1Bitmap());
        profileImage2.setImageBitmap(userProfile.getImage2Bitmap());
        profileBio.setText(userProfile.getBio());
    }

    // The initialisation of the RecyclerView.
    private void initProfileRecyclerTest() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        profileInterests.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter(userProfile.getInterests(), view.getContext(), R.layout.layout_interest_item);
        profileInterests.setAdapter(adapter);
    }

}
