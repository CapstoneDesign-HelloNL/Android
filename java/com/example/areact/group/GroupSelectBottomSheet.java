package com.example.areact.group;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.areact.R;
import com.example.areact.group.server.GroupSearchOneGet;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupSelectBottomSheet extends BottomSheetDialogFragment {
    private TextView createDate, groupName, schoolName;
    private FragmentManager fm;
    private FragmentTransaction ft;

    String groupNameStr;
    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if (bundle != null) {
            groupNameStr = bundle.getString("groupName");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_select, container, false);
        Button cancel_btn = rootView.findViewById(R.id.group_select_cancel_btn);
        Button request_btn = rootView.findViewById(R.id.group_select_request_btn);
        createDate = rootView.findViewById(R.id.group_select_date);
        groupName = rootView.findViewById(R.id.group_select_group);
        schoolName = rootView.findViewById(R.id.group_select_school);

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog d = (BottomSheetDialog) dialogInterface;
                View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
                connectToServer();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new GroupRequestJoin());
            }
        });

        return rootView;
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getGroupSearchOne(groupNameStr).enqueue(new Callback<GroupSearchOneGet>() {
            @Override
            public void onResponse(Call<GroupSearchOneGet> call, Response<GroupSearchOneGet> response) {
                if (response.isSuccessful()) {
                    GroupSearchOneGet body = response.body();

                    String mDate = body.getData().getCreateAt();
                    groupName.setText(body.getData().getName());
                    schoolName.setText(body.getData().getUnivName());

                    formattingDate(mDate);

                    Log.d("Server GroupSelect", body.getMsg());
                    Log.d("Server GroupSelect", body.getStatus());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Log.d("Server GroupSelect", responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupSearchOneGet> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void formattingDate(String sDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(sDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String getDate = simpleDateFormat.format(date);
            createDate.setText(getDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void replaceFragment(Fragment fragment) {
        fm = getChildFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(R.id.group_select_layout, fragment);
        ft.commit();
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