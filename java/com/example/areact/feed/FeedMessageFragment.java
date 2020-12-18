package com.example.areact.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FeedMessageFragment extends BottomSheetDialogFragment {
    public FeedMessageFragment() {
        // Required empty public constructor
    }

    RelativeLayout relativeLayout;
    TextView name, content;
    RecyclerView recyclerView;
    ImageButton backBtn;

    private ArrayList<FeedMessage> list = new ArrayList<>();
    FeedMessageAdapter adapter = new FeedMessageAdapter(list);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_feed_message, container, false);
        recyclerView = rootView.findViewById(R.id.feed_message_recyclerview);
        name = rootView.findViewById(R.id.feed_message_name);
        content = rootView.findViewById(R.id.feed_message_content);
        relativeLayout = rootView.findViewById(R.id.feed_message_go_detail);
        backBtn = rootView.findViewById(R.id.feed_message_back_btn);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedMessageDetailFragment fragment = new FeedMessageDetailFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        /*layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new OverlapDecoration(-200));
        recyclerView.setAdapter(adapter);*/

        //list.add(new FeedMessage("양정훈","안녕하세요"));
        //list.add(new FeedMessage("채종희", "하이하이"));

        //adapter.notifyDataSetChanged();
    }
}
