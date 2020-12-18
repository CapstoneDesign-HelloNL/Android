package com.example.areact.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.R;

public class GroupHamAgendaFragment extends Fragment {
    ImageButton back_btn;
    Button send_btn;
    EditText et;
    View layoutInflater, view;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    private GroupHamAgendaFragment() {
    }

    public static class SingletonHolder {
        public static final GroupHamAgendaFragment INSTANCE = new GroupHamAgendaFragment();
    }

    public static GroupHamAgendaFragment getInstance() {
        return SingletonHolder.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group_ham_agenda, container, false);
        back_btn = rootView.findViewById(R.id.group_ham_agenda_back_btn);
        send_btn = rootView.findViewById(R.id.group_ham_agenda_send_btn);
        et = rootView.findViewById(R.id.group_ham_agenda_et);
        layoutInflater = getLayoutInflater().inflate(R.layout.activity_group_detail, null, false);
        view = layoutInflater.findViewById(R.id.frame_layout_viewID);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        et.requestFocus();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private String getAccessToken() {
        String token;
        sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    public String getRefreshToken() {
        String token;
        sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_refresh_token", "");
        return token;
    }
}
