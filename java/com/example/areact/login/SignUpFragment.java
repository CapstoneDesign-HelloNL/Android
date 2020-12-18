package com.example.areact.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.PublicDialog;
import com.example.areact.R;
import com.example.areact.login.server.SignUpPost;
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

public class SignUpFragment extends Fragment {
    buttonClick click;

    interface buttonClick {
        void buttonClicked(View v);
    }

    private static final String BASE_URL = "http://3.34.33.156";
    private EditText name_et, email_et, pw_et, univ_et, stdNum_et;
    private Button complete_btn;
    private InputMethodManager imm;
    private PublicDialog publicDialog;
    private String dialog_title, dialog_body;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        click = (buttonClick) activity;
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        publicDialog = new PublicDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);

        name_et = rootView.findViewById(R.id.sign_up_name);
        email_et = rootView.findViewById(R.id.sign_up_email);
        pw_et = rootView.findViewById(R.id.sign_up_pw);
        univ_et = rootView.findViewById(R.id.sign_up_univ);
        stdNum_et = rootView.findViewById(R.id.sign_up_stuId);
        complete_btn = rootView.findViewById(R.id.sign_up_complete_btn);

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_et.getText().toString().equals("")) {
                    dialog_title = "입력 오류";
                    dialog_body = "이름을 입력하세요.\n";
                    publicDialog.callFunction(dialog_title, dialog_body);
                } else if (email_et.getText().toString().equals("")) {
                    dialog_title = "입력 오류";
                    dialog_body = "이메일을 입력하세요.\n";
                    publicDialog.callFunction(dialog_title, dialog_body);
                } else if (pw_et.getText().toString().equals("")) {
                    dialog_title = "입력 오류";
                    dialog_body = "비밀번호를 입력하세요.\n";
                    publicDialog.callFunction(dialog_title, dialog_body);
                } else if (univ_et.getText().toString().equals("")) {
                    dialog_title = "입력 오류";
                    dialog_body = "학교를 입력하세요.\n";
                    publicDialog.callFunction(dialog_title, dialog_body);
                } else if (stdNum_et.getText().toString().equals("")) {
                    dialog_title = "입력 오류";
                    dialog_body = "학번을 입력하세요.\n";
                    publicDialog.callFunction(dialog_title, dialog_body);
                } else {
                    toAccessServer();
                }
            }
        });

        return rootView;
    }

    public void toAccessServer() {
        //retrofit 초기화하기
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        //SignUp data 설정
        HashMap<String, Object> input = new HashMap<>();
        input.put("name", name_et.getText().toString());
        input.put("pwd", pw_et.getText().toString());
        input.put("email", email_et.getText().toString());
        input.put("stdNum", stdNum_et.getText().toString());

        //서버와 연결 성공 -> 회원가입 완료
        //서버와 연결 실패 -> 화면 그대로
        retrofitAPI.postSignUp(input).enqueue(new Callback<SignUpPost>() {
            @Override
            public void onResponse(@NotNull Call<SignUpPost> call, @NotNull Response<SignUpPost> response) {
                if (response.isSuccessful()) {
                    SignUpPost res_body = response.body();
                    Log.d("test_signup", res_body.getStatus());
                    Log.d("test_signup", res_body.getMsg());

                    if (Integer.parseInt(res_body.getStatus()) == 200) {
                        click.buttonClicked(complete_btn.getRootView());
                        dialog_title = "AREACT";
                        dialog_body = "회원이 되신 것을 축하합니다!\n";
                        publicDialog.callFunction(dialog_title, dialog_body);
                        name_et.setText("");
                        email_et.setText("");
                        pw_et.setText("");
                        univ_et.setText("");
                        stdNum_et.setText("");
                    }
                } else {
                    ResponseBody a = response.errorBody();
                    try {
                        if (a.string().contains("409")) {
                            dialog_title = "회원가입 오류";
                            dialog_body = "이미 등록된 계정입니다";
                            publicDialog.callFunction(dialog_title, dialog_body);
                            imm.toggleSoftInput(0, 0);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpPost> call, Throwable t) {
                t.printStackTrace();
                Log.d("test_signup", "실패");
            }
        });
    }

    private void hideKeyboard(EditText editText) {
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
