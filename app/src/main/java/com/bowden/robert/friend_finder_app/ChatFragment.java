package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bowden.robert.friend_finder_app.CustomAdapters.WhosNewAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    private View view;
    private List<String> newMatches = new ArrayList<>();

    private RecyclerView chatNewRecycler;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatProps();
        newMatchesTest();
        initTestChatRecycler();
        return view;
    }

    private void chatProps() {
        chatNewRecycler = view.findViewById(R.id.chatNewRecycler);
        bottomSheet = view.findViewById(R.id.chatBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
    }
    private void newMatchesTest() {
        newMatches.add("https://images.unsplash.com/photo-1617143174038-7bdfd1189a23?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617127891019-0e038034dd14?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617121437933-489b82b3abe5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617143592861-7b632fc109be?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617188017502-fc2882a4a09f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617143174038-7bdfd1189a23?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617127891019-0e038034dd14?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617121437933-489b82b3abe5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=975&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617143592861-7b632fc109be?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80");
        newMatches.add("https://images.unsplash.com/photo-1617188017502-fc2882a4a09f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80");
    }
    private void initTestChatRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        chatNewRecycler.setLayoutManager(layoutManager);
        WhosNewAdapter adapter = new WhosNewAdapter(newMatches, view.getContext(), R.layout.layout_whosnew_item);
        chatNewRecycler.setAdapter(adapter);
    }
}
