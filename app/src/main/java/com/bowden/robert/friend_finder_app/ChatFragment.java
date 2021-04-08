package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.bowden.robert.friend_finder_app.CustomAdapters.ChatsAdapter;
import com.bowden.robert.friend_finder_app.CustomAdapters.FriendsAdapter;
import com.bowden.robert.friend_finder_app.CustomAdapters.WhosNewAdapter;
import com.bowden.robert.friend_finder_app.TestClasses.FriendsChatTest;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    // Members
    private List<Integer> newMatches = new ArrayList<>();
    private List<FriendsChatTest> friends = new ArrayList<>();

    // Props
    private View view;
    private RecyclerView chatNewRecycler;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ListView chatView;
    private GridView friendView;

    // Necessary method used to create the view layout, i.e. the front end style/ look.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatProps();
        newMatchesTest();
        initTestChatRecycler();
        chatAdapterTest();
        initChatsAdapter();
        initFriendsAdapter();
        return view;
    }

    /*
    friendView is initialised in the method chatProps().
    and here I've set the adapter for the friendsView (GridView)
    See: CustomAdapters > FriendsAdapter for more clarity.
     */
    private void initFriendsAdapter() {
        FriendsAdapter friendsAdapter = new FriendsAdapter(friends, getActivity());
        friendView.setAdapter(friendsAdapter);
    }

    // initialises the props of the 'fragment_chat.xml' file
    private void chatProps() {
        chatNewRecycler = view.findViewById(R.id.chatNewRecycler);
        bottomSheet = view.findViewById(R.id.chatBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        chatView = view.findViewById(R.id.chats);
        friendView = view.findViewById(R.id.friendsGridView);
    }

    private void newMatchesTest() {
        newMatches.add(R.drawable.test_person_image_1);
        newMatches.add(R.drawable.test_person_image_2);
        newMatches.add(R.drawable.test_person_image_3);
        newMatches.add(R.drawable.test_person_image_4);
        newMatches.add(R.drawable.test_person_image_5);
        newMatches.add(R.drawable.test_person_image_1);
        newMatches.add(R.drawable.test_person_image_2);
        newMatches.add(R.drawable.test_person_image_3);
        newMatches.add(R.drawable.test_person_image_4);
        newMatches.add(R.drawable.test_person_image_5);
    }
    // sets the layout manager and the adapter for the RecyclerView.
    private void initTestChatRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        chatNewRecycler.setLayoutManager(layoutManager);
        WhosNewAdapter adapter = new WhosNewAdapter(newMatches, view.getContext(), R.layout.layout_whosnew_item);
        chatNewRecycler.setAdapter(adapter);
    }

    // sets the ListView custom adapter for the chats part of the app.
    private void initChatsAdapter() {
        ChatsAdapter chatsAdapter = new ChatsAdapter(friends, getActivity());
        chatView.setAdapter(chatsAdapter);
    }

    // This was a test for the ChatsAdapter. I recommend commenting this out when attempting to add any
    // real tests, rather than hard coded.
    private void chatAdapterTest() {
        FriendsChatTest f1 = new FriendsChatTest("Sally", "Hey!", R.drawable.test_person_image_1);
        FriendsChatTest f2 = new FriendsChatTest("Samuel", "Coming to the pub?", R.drawable.test_person_image_2);
        FriendsChatTest f3 = new FriendsChatTest("Brad", "Hey ;)", R.drawable.test_person_image_3);
        FriendsChatTest f4 = new FriendsChatTest("Sarah", "Why is that?", R.drawable.test_person_image_4);
        FriendsChatTest f5 = new FriendsChatTest("Lewis", "Hey!", R.drawable.test_person_image_5);
        FriendsChatTest f6 = new FriendsChatTest("Sally", "Hey!", R.drawable.test_person_image_1);
        FriendsChatTest f7 = new FriendsChatTest("Samuel", "Coming to the pub?", R.drawable.test_person_image_2);
        FriendsChatTest f8 = new FriendsChatTest("Brad", "Hey ;)", R.drawable.test_person_image_3);
        FriendsChatTest f9 = new FriendsChatTest("Sarah", "Why is that?", R.drawable.test_person_image_4);
        FriendsChatTest f10 = new FriendsChatTest("Lewis", "Hey!", R.drawable.test_person_image_5);
        friends.add(f1);
        friends.add(f2);
        friends.add(f3);
        friends.add(f4);
        friends.add(f5);
        friends.add(f6);
        friends.add(f7);
        friends.add(f8);
        friends.add(f9);
        friends.add(f10);
    }
}
