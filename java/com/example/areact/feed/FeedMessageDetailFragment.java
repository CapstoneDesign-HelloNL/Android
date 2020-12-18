package com.example.areact.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FeedMessageDetailFragment extends Fragment {

    public FeedMessageDetailFragment() {
        // Required empty public constructor
    }

    TextView name, content;
    EditText editText;
    ImageButton sendBtn, backBtn;
    ScrollView scrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_feed_message_detail, container, false);
        name = rootView.findViewById(R.id.chat_tv4);
        content = rootView.findViewById(R.id.chat_tv4_1);
        editText = rootView.findViewById(R.id.feed_message_detail_et);
        sendBtn = rootView.findViewById(R.id.feed_message_detail_send_btn);
        backBtn = rootView.findViewById(R.id.feed_message_detail_back_btn);
        scrollView = rootView.findViewById(R.id.feed_message_detail_scroll);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final BottomNavigationView navigationView = getActivity().findViewById(R.id.bottom_navi_bar);
        navigationView.setVisibility(View.GONE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                content.setText(editText.getText().toString());
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }
}
