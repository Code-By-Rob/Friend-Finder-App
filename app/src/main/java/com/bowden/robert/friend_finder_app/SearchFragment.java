package com.bowden.robert.friend_finder_app;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowden.robert.friend_finder_app.CustomAdapters.InterestsAdapter;
import com.bowden.robert.friend_finder_app.CustomAdapters.SearchAdapter;
import com.bowden.robert.friend_finder_app.ServerClasses.Profile;
import com.bowden.robert.friend_finder_app.TestClasses.SearchFriendTest;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {

    // Members
    private View view;
    private List<String> interests = new ArrayList<>();
    private List<SearchFriendTest> friends = new ArrayList<>();
    private SearchAdapter adapter = null;
    // Props are held in the SearchAdapter.
    // The props are also initialised there.

    // List to store the profiles obtained from the server
    private List<Profile> profiles = new ArrayList<>();
    // below are the URLs that have been used to obtain the profiles from the server
    private static String url_all_persons = "http://192.168.1.67:80/friendfindertest-phpscripts/get_all_persons.php";
    private static String emulatorUrl = "http://10.0.2.2:80/friendfindertest-phpscripts/get_all_persons.php";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        setTestPersons();
        setTestPersonInterests();
        new LoadAllPersons().execute();
        swipeAnimation();
        return view;
    }

    /*
     * Below I am implementing the code to be able to retrieve data from a locally hosted database
     * --> Firstly, I am going to send a request for get_all_persons.php by a Background ASync task thread
     * --> Secondly, after getting the JSON from get_all_persons.php
     *     I am going to parse it and store it in an array of Profile class.
     * --> Finally I am going to change the type of the List above to Profile and have it hold the profiles
     */
    /*
    * Background ASync Task to load all persons via a http request.
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void jsonParser(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONArray persons = root.getJSONArray("persons");
        for (int i = 0; i < persons.length(); i++) {
            Log.i("persons", "1" + persons.getJSONArray(i));
            JSONArray person = persons.getJSONArray(i);
            String id = (String) person.get(0);
            int pID = Integer.parseInt(id);
            String pNameFirst = (String) person.get(1);
            String pNameLast = (String) person.get(2);
            String pDob = (String) person.get(3);
            String pImage = (String) person.get(4);
            String pBio = (String) person.get(5);

            Profile profile = new Profile(pID, pNameFirst, pNameLast, pDob, pImage, pImage, pBio);
            profile.setInterests(interests);
            profiles.add(profile);

//            Log.i("Person" + i, "Object: " + profile.getID());
//            Log.i("Person" + i, "Object: " + profile.getNamefirst());
//            Log.i("Person" + i, "Object: " + profile.getNamelast());
//            Log.i("Person" + i, "Object: " + profile.getDob());
//            Log.i("Person" + i, "Object: " + profile.getImage());
//            Log.i("Person" + i, "Object: " + profile.getBio());

        }

        for (int i = 0; i < profiles.size(); i++) {
            Log.i("Person " + i, "Object: " + profiles.get(i).getID());
            Log.i("Person " + i, "Object: " + profiles.get(i).getNamefirst());
            Log.i("Person " + i, "Object: " + profiles.get(i).getNamelast());
        }

        Log.i("Profiles List", "----> " + profiles.size());
    }

    class LoadAllPersons extends AsyncTask<String, String, String> {

        /*
        Getting all products from the url
        Below code works :)
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection;
            try {
                URL url = new URL(emulatorUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    Log.i("JSON", "--->" + line.length());
                    sb.append(line + "\n");
                    Log.i("JSON", "--->" + sb.length());
                } // I am receiving the first portion of data. But not all of the data.
                br.close();
                urlConnection.disconnect();

                String jsonString = sb.toString();
                Log.d("JSON", "Begining: " + jsonString);
                jsonParser(jsonString);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.addAll(profiles);
            adapter.notifyDataSetChanged();
        }
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
        adapter = new SearchAdapter(getContext(), R.layout.layout_search_item, profiles);
        flingContainer.setAdapter(adapter);
        Log.i("SearchAdapter", "---> " + adapter.getCount());
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "Removed Object");
                profiles.remove(0);
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
                adapter.addAll(profiles);
                adapter.notifyDataSetChanged();
                Log.d("LIST", "Notified");
            }
            // This method needs to be implemented. But it can be left blank. Therefore, no functionality.
            @Override
            public void onScroll(float v) {
            }
        });
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

}
