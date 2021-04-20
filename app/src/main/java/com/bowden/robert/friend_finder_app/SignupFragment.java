package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class SignupFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        SectionsPageAdapter pageAdapter = new SectionsPageAdapter(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ViewPager viewPager = view.findViewById(R.id.signupViewpager);
        viewPager.setAdapter(pageAdapter);

        return view;
    }


    public class SectionsPageAdapter extends FragmentPagerAdapter {

        public SectionsPageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch(position) {
                case 0:
                    fragment = new Signup1Fragment();
                    break;
                case 1:
                    fragment = new Signup2Fragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
