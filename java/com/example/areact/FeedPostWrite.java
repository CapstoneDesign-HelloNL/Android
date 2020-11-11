package com.example.areact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FeedPostWrite extends BottomSheetDialogFragment {
    private ImageButton back_btn;
    private Button complete_btn;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_feed_post_write, container,false);


        back_btn = rootView.findViewById(R.id.feed_write_back_btn);
        complete_btn = rootView.findViewById(R.id.feed_write_complete);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onAttachFragment(new SocialFragment());
            }
        });

        return rootView;
    }
}

