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

    // Members
    private List<String> interests = new ArrayList<>();
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
