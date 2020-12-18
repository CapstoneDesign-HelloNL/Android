package com.example.areact.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.MainActivity;
import com.example.areact.PublicDialog;
import com.example.areact.R;
import com.example.areact.login.server.SignInPost;
import com.example.areact.server.RetrofitAPI;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInFragment extends Fragment {
    private static final String BASE_URL = "http://3.34.33.156";
    private EditText email_et, pw_et;
    private Button btn;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private InputMethodManager imm;
    private PublicDialog publicDialog;

    private String dialog_title, dialog_body;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        publicDialog = new PublicDialog(context);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.sign_in_fragment, container, false);
        btn = rootView.findViewById(R.id.sign_in_continue_btn);
        email_et = rootView.findViewById(R.id.sign_in_id);
        pw_et = rootView.findViewById(R.id.sign_in_pw);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
                editor = sharedPref.edit();

                connectToServer();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        pw_et.setText("");
    }

    private void connectToServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        HashMap<String, Object> input = new HashMap<>();
        input.put("email", email_et.getText().toString());
        input.put("pwd", pw_et.getText().toString());

        retrofitAPI.postSignIn(input).enqueue(new Callback<SignInPost>() {
            @Override
            public void onResponse(Call<SignInPost> call, @NotNull Response<SignInPost> response) {
                if (response.isSuccessful()) {
                    final SignInPost res_body = response.body();

                    Log.d("Server SignIn", res_body.getStatus());
                    Log.d("Server SignIn", res_body.getMsg());

                    //로그인 성공 시
                    editor.putString("s_access_token", res_body.getData().getAccessToken());
                    editor.putString("s_refresh_token", res_body.getData().getRefreshToken());
                    editor.apply();

                    hideKeyboard(pw_et);
                    hideKeyboard(email_et);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    email_et.requestFocus();

                } else {
                    ResponseBody a = response.errorBody();
                    try {
                        if (a.string().contains("404")) {
                            hideKeyboard(email_et);
                            dialog_title = "로그인 오류";
                            dialog_body = "아이디를 다시 확인해주세요.";
                            publicDialog.callFunction(dialog_title, dialog_body);
                        } else {
                            hideKeyboard(pw_et);
                            dialog_title = "로그인 오류";
                            dialog_body = "패스워드를 다시 확인해주세요";
                            publicDialog.callFunction(dialog_title, dialog_body);
                            pw_et.setText("");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignInPost> call, Throwable t) {
                t.printStackTrace();
                Log.d("SigninTest1", "실패");
            }
        });
    }

    private void hideKeyboard(EditText editText) {
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
