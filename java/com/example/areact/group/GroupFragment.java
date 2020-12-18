package com.example.areact.group;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.example.areact.group.server.GroupListGet;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {
    private ImageButton search_btn, plus_btn;
    private RecyclerView recyclerView;
    private TextView tv;

    private final ArrayList<GroupEntity> groupList = new ArrayList<>();
    private Group_adapter adapter = new Group_adapter(groupList);

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_fragment, container, false);

        search_btn = rootView.findViewById(R.id.group_searh_btn);
        plus_btn = rootView.findViewById(R.id.group_add_btn);
        recyclerView = rootView.findViewById(R.id.group_recyclerView);
        tv = rootView.findViewById(R.id.group_test);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new Group_adapter(groupList);
        tv.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        //그룹 생성 후, 현재 fragment에 보여주기

        connectionServer();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroupSearchActivity.class);
                startActivity(intent);
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new GroupAddActivity());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        groupList.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        groupList.clear();
    }

    //다음 fragment로 이동
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //서버로부터 그룹 아이템 불러오기
    private void connectionServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getGroupData().enqueue(new Callback<GroupListGet>() {
            @Override
            public void onResponse(Call<GroupListGet> call, Response<GroupListGet> response) {
                if (response.isSuccessful()) {
                    GroupListGet data = response.body();

                    assert data != null;
                    for (int i = 0; i < data.getData().size(); i++) {
                        String name = data.getData().get(i).getName();
                        String join_count = data.getData().get(i).getJoin_count();
                        String univ_name = data.getData().get(i).getUnivName();
                        GroupEntity newGroup = new GroupEntity(name, univ_name, join_count);
                        groupList.add(newGroup);
                        tv.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();

                    Bundle bundle = getArguments();
                    if (bundle != null) {
                        if (!groupList.isEmpty()) {
                            tv.setVisibility(View.GONE);

                            if ("back".equals(bundle.getString("back_btn_click"))) {
                                tv.setVisibility(View.GONE);
                            }
                        } else {
                            tv.setVisibility(View.VISIBLE);
                        }
                    }

                    Log.d("Server GroupFragment", data.getMsg());
                    Log.d("Server GroupFragment", data.getStatus());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        System.out.println(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupListGet> call, Throwable t) {
                t.printStackTrace();
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