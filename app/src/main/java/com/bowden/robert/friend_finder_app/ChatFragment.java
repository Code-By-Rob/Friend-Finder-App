package com.bowden.robert.friend_finder_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bowden.robert.friend_finder_app.CustomAdapters.WhosNewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatFragment extends Fragment {

    private View view;
    private List<Integer> newMatches = new ArrayList<>();

    private RecyclerView chatNewRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatProps();
        initTestChatRecycler();
        return view;
    }

    private void chatProps() {
        chatNewRecycler = view.findViewById(R.id.chatNewRecycler);
    }
    private void initTestChatRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        chatNewRecycler.setLayoutManager(layoutManager);
        WhosNewAdapter adapter = new WhosNewAdapter(newMatches, view.getContext(), R.layout.layout_whosnew_item);
        chatNewRecycler.setAdapter(adapter);
    }
}
