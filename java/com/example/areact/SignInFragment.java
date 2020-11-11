package com.example.areact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class SignInFragment extends Fragment{
    public SignInFragment() {}
    private Button btn;
    private EditText id_et, pw_et;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.sign_in_fragment,container,false);
        btn=rootView.findViewById(R.id.continue_btn);
        id_et = rootView.findViewById(R.id.sign_in_id);
        pw_et = rootView.findViewById(R.id.sign_in_pw);

/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://10.20.31.25:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        *//*retrofitAPI.getData("1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()) {
                    List<Post> data = response.body();
                    Log.d("ABC","성공성공1");
                    Log.d("ABC",data.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                Log.d("ABC","실패");
            }
        });*//*

        HashMap<String, Object> input = new HashMap<>();
        input.put("userId",1);
        input.put("title","title title");
        input.put("body","당근 당근");

        retrofitAPI.postData(input).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    Post data = response.body();
                    Log.d("ABC","POST 성공");
                    Log.d("ABC",data.getBody());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroupMainActivity.class);
                startActivity(intent);
            }
        });

       return rootView;
    }
}
