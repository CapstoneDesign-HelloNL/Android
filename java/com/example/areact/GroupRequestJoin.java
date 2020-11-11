package com.example.areact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class GroupRequestJoin extends Fragment {
    private ImageButton back_btn;
    private Button complete_btn;
    private FragmentManager fm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_request_join, container, false);


        back_btn =rootView.findViewById(R.id.group_request_join_back_btn);
        complete_btn = rootView.findViewById(R.id.group_request_complete_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm = getParentFragmentManager();
                fm.beginTransaction().remove(GroupRequestJoin.this).commit();
                fm.popBackStack();
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }
}
