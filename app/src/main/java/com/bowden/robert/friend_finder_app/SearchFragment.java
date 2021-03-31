package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.CustomAdapters.InterestsAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {

    private View view;
    private String bio;
    private List<String> interests = new ArrayList<>();

    private TextView personBiography;
    private ImageView personImage;
    private ImageView personBestMeme;
    private RecyclerView interestsRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        props();
        setTestPerson();
        setTestPersonInterests();
        initTestPersonRecycler();
        return view;
    }

    public void props() {
        personImage = view.findViewById(R.id.searchPersonImage);
        personBestMeme = view.findViewById(R.id.searchBestMeme);
        personBiography = view.findViewById(R.id.searchBiography);
        bio = ("I love to show people my middle finger. It's my passion, " +
                "one day I will be a middle finger model and the whole world " +
                "will know what my middle finger looks like");
        interestsRecycler = view.findViewById(R.id.interestsRecycler);
    }
    private void setTestPerson() {
        personImage.setImageResource(R.drawable.myimage);
        personBestMeme.setImageResource(R.drawable.youtube_meme);
        personBiography.setText(bio);
    }
    private void setTestPersonInterests() {
        interests.add("Video games");
        interests.add("Programming");
        interests.add("Photography");
        interests.add("Netflix");
        interests.add("Reading");
        interests.add("Mobile Dev");
    }
    private void initTestPersonRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        interestsRecycler.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter(interests, view.getContext(), R.layout.layout_interest_item);
        interestsRecycler.setAdapter(adapter);
    }
}
