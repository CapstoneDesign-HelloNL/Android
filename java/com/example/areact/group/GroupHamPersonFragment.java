package com.example.areact.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.areact.R;
import com.example.areact.group.detail.server.GroupHamPersonGet;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupHamPersonFragment extends Fragment {
    RecyclerView recyclerView;
    ImageView backBtn;

    private String name, stdNum, memberRank;
    private String groupName;
    ArrayList<GroupHamPerson> list = new ArrayList<>();
    GroupHamPersonAdapter adapter = new GroupHamPersonAdapter(list);

    View backgroundView;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            groupName = bundle.getString("groupName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group_ham_person, container, false);
        recyclerView = rootView.findViewById(R.id.group_ham_person_recyclerview);
        backBtn = rootView.findViewById(R.id.group_ham_person_back_btn);
        View view = getLayoutInflater().inflate(R.layout.activity_group_detail, null, false);
        backgroundView = view.findViewById(R.id.frame_layout_viewID);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        connectToServer();
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getGroupHamPerson(groupName).enqueue(new Callback<GroupHamPersonGet>() {
            @Override
            public void onResponse(Call<GroupHamPersonGet> call, Response<GroupHamPersonGet> response) {
                if (response.isSuccessful()) {
                    GroupHamPersonGet personGet = response.body();

                    String[] stdNum = {"14109331", "15109325",};
                    Log.d("Server HamPerson", personGet.getMsg());
                    Log.d("Server HamPerson", personGet.getStatus());

                    for (int i = 0; i < personGet.getData().size(); i++) {
                        name = personGet.getData().get(i).getMemberEmail();
                        memberRank = personGet.getData().get(i).getMemberRank();

                        GroupHamPerson person = new GroupHamPerson(name, stdNum[i], memberRank);
                        list.add(person);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Log.d("Server HamPerson", responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupHamPersonGet> call, Throwable t) {
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
