package com.example.areact.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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

public class GroupSearchActivity extends AppCompatActivity implements TextWatcher {
    RecyclerView recyclerView;
    ImageButton back_btn;
    EditText editTextFilter;

    private String groupName;
    private String univName;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    final ArrayList<GroupSearchItem> items = new ArrayList<>();
    FragmentManager fm = getSupportFragmentManager();
    GroupSearchAdapter adapter = new GroupSearchAdapter(GroupSearchActivity.this, items, fm);
    ;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_search);

        back_btn = findViewById(R.id.group_search_back_btn);
        recyclerView = findViewById(R.id.search_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupSearchActivity.this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adapter);

        editTextFilter = findViewById(R.id.group_search_et);
        editTextFilter.addTextChangedListener(this);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupSearchActivity.super.onBackPressed();
            }
        });

        connectToServer();
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getGroupData().enqueue(new Callback<GroupListGet>() {
            @Override
            public void onResponse(Call<GroupListGet> call, Response<GroupListGet> response) {
                if (response.isSuccessful()) {
                    GroupListGet body = response.body();
                    assert body != null;
                    Log.d("Server GroupSearch", body.getMsg());
                    Log.d("Server GroupSearch", body.getStatus());
                    for (int i = 0; i < body.getData().size(); i++) {
                        groupName = body.getData().get(i).getName();
                        univName = body.getData().get(i).getUnivName();
                        GroupSearchItem list = new GroupSearchItem(groupName, univName);
                        items.add(list);
                    }

                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        assert responseBody != null;
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence == null) {
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.getFilter().filter(charSequence);

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private String getAccessToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    private String getRefreshToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_refresh_token", "");
        return token;
    }
}
