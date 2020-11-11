package com.example.areact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupAddActivity extends Fragment {
    private ImageButton back_btn;
    private Button complete_btn;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private View bottomView;
    private ShareViewModel shareViewModel;

    BottomNavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_add, container,false);

        //네비게이션 바 일시적으로 감추기
        bottomView = getLayoutInflater().inflate(R.layout.activity_group_main,null,false);
        navigationView = getActivity().findViewById(R.id.bottom_navi_bar);
        navigationView.setVisibility(View.GONE);

        back_btn = rootView.findViewById(R.id.group_add_back_btn);
        complete_btn = rootView.findViewById(R.id.group_add_complete_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                navigationView.setVisibility(View.VISIBLE);
            }
        });


        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm = getParentFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new GroupFragment());
                navigationView.setVisibility(View.VISIBLE);
                ft.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
    }
}
