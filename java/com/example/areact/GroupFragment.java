package com.example.areact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class GroupFragment extends Fragment {
    public GroupFragment() {}
    private ImageButton plus_btn;
    private ImageButton search_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_fragment,container,false);

        search_btn = rootView.findViewById(R.id.group_searh_btn);
        plus_btn = rootView.findViewById(R.id.group_add_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroupSearch.class);
                startActivity(intent);
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_top,R.anim.exit_to_down,R.anim.enter_from_down,R.anim.exit_to_top);
                ft.replace(R.id.main_frame, new GroupAddActivity());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return rootView;
    }
}
