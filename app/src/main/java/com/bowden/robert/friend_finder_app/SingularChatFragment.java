package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SingularChatFragment extends Fragment {

    /*
    I understand that not much has been added here.
    However, I will do so with the implementation of the back-end.
    This will need to be a dynamic class that will change the outputted view dependent on the user chat item
    that is clicked on in the ListView found in 'fragment_chat.xml'.
     */

    // Prop
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_singular_chat, container, false);
        return view;
    }
}
