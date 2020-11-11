package com.example.areact;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FeedBottomSheet extends BottomSheetDialogFragment {
    private TextView feed_write,feed_msg;
    FragmentManager fm;
    FragmentTransaction ft;
    FeedPostWrite feedPostWrite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.a_feed_bottm_sheet, container, false);

        feed_write = rootView.findViewById(R.id.feed_ham_write);
        feed_msg = rootView.findViewById(R.id.feed_ham_msg);

        feed_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedPostWrite.class);
                startActivity(intent);
            }
        });

        feed_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedPostMsg.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
