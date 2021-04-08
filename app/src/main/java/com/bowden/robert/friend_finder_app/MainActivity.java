package com.bowden.robert.friend_finder_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    /*
    Welcome!
    This is the MainActivity.
    You most likely won't spend much time here.
    But if you have been coding for a long time,
    think of this as a break spot.
    Have a rest. Get your drink of choice or whatever you like :)
     */

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation();
    }

    // This is the code for the navigation bar at the bottom which opens up the fragments like search, chat and profile.
    // The navigation listener is where the real magic is at.
    // This just sets the first fragment to appear which is the search fragment.
    public void bottomNavigation() {
        bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_search);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SearchFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_chat:
                            selectedFragment = new ChatFragment();
                            Log.i("BottomNav Switch", "Chat Fragment Selected");
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            Log.i("BottomNav Switch", "Search Fragment Selected");
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            Log.i("BottomNav Switch", "Profile Fragment Selected");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}
