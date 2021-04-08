package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowden.robert.friend_finder_app.CustomAdapters.InterestsAdapter;
import com.bowden.robert.friend_finder_app.CustomAdapters.SearchAdapter;
import com.bowden.robert.friend_finder_app.TestClasses.SearchFriendTest;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {

    // Members
    private View view;
    private List<String> interests = new ArrayList<>();
    private List<SearchFriendTest> friends = new ArrayList<>();
    // Props are held in the SearchAdapter.
    // The props are also initialised there.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        setTestPersons();
        setTestPersonInterests();
        swipeAnimation();
        return view;
    }
    /*
    * The below method is used to set up dummy data as a test to see how the SwipeFlingAdapterView
    * would work with any data that we have.
    * SearchFriendTest is a class that is stored in the TestClasses directory.
    * When changing the Type in the List Object; Remember to change the Type in the SearchAdapter members
    * and the Type of the List Object that is an argument in the Constructor.
    */
    private void setTestPersons() {
        SearchFriendTest f1 = new SearchFriendTest(R.drawable.test_person_image_1,
                R.drawable.youtube_meme, "Hey there sailor.",
                "James", "20", interests);
        SearchFriendTest f2 = new SearchFriendTest(R.drawable.test_person_image_2,
                R.drawable.youtube_meme, "Yes my name really is Qwerty. Yes my parents are nerds.",
                "Qwerty", "18", interests);
        SearchFriendTest f3 = new SearchFriendTest(R.drawable.test_person_image_3,
                R.drawable.youtube_meme, "I am named after a pokemon. My parents are also nerds.",
                "Tyrannosaur", "19", interests);
        SearchFriendTest f4 = new SearchFriendTest(R.drawable.test_person_image_4,
                R.drawable.youtube_meme, "Something, something, something....",
                "Sam", "22", interests);
        SearchFriendTest f5 = new SearchFriendTest(R.drawable.test_person_image_5,
                R.drawable.youtube_meme, "Call me a Karen. As I'm asking for your manager.",
                "Creme", "23", interests);
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friends.add(f4);
        friends.add(f5);
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friends.add(f4);
        friends.add(f5);
    }
    private void setTestPersonInterests() {
        interests.add("Video games");
        interests.add("Programming");
        interests.add("Photography");
        interests.add("Netflix");
        interests.add("Reading");
        interests.add("Mobile Dev");
    }
    /*
    * The below method is where the swipe magic comes into play.
    * You can change the code of the Listener methods e.g. onLeftCardExit can remove the person from the List
    * and change boolean avoid = true; or something like that...
    *
    * The method 'onAdapterAboutToEmpty' can be used to update the Queue of the List with new persons.
    */
    private void swipeAnimation() {
        SwipeFlingAdapterView flingContainer = view.findViewById(R.id.flingFrame);
        final SearchAdapter adapter = new SearchAdapter(getContext(), R.layout.layout_search_item, friends);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "Removed Object");
                friends.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                //Do something on the left!
                // e.g. remove them
                Toast.makeText(view.getContext(), "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                //Do something on the right!
                // e.g. add them to their friends list.
                Toast.makeText(view.getContext(), "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                // here you can add more people to the List
                // I'm just going to add this call to a method
                setTestPersons();
                adapter.notifyDataSetChanged();
                Log.d("LIST", "Notified");
            }
            // This method needs to be implemented. But it can be left blank. Therefore, no functionality.
            @Override
            public void onScroll(float v) {
            }
        });
    }

}
