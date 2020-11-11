package com.example.areact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpFragment extends Fragment {
    public SignUpFragment() {}
    private EditText name_et, email_et, password_et, stuNum_et;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment,container,false);

        name_et = rootView.findViewById(R.id.sign_up_name);
        email_et = rootView.findViewById(R.id.sign_up_email);
        password_et = rootView.findViewById(R.id.sign_up_pw);
        stuNum_et = rootView.findViewById(R.id.sign_up_stuId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.20.31.25:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        /*retrofitAPI.getData("1").enqueue(new Callback<List<Post>>() {
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
        });*/

        HashMap<String, Object> input = new HashMap<>();
        input.put("name",name_et.getText().toString());
        input.put("email",email_et.getText().toString());
        input.put("password", password_et.getText().toString());
        input.put("stuNum",stuNum_et.getText().toString());

        retrofitAPI.postData(input).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NotNull Call<Post> call, @NotNull Response<Post> response) {
                if(response.isSuccessful()) {
                    Post res_body = response.body();
                    Log.d("areact",res_body.getStatus());
                    Log.d("areact",res_body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return rootView;
    }
}
