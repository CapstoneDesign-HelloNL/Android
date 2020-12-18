package com.example.areact.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.R;
import com.example.areact.login.server.LogoutAccountPost;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupFragment extends Fragment {
    private SetupFragment() {
    }

    public static class SingletonHolder {
        public static final SetupFragment INSTANCE = new SetupFragment();
    }

    public static SetupFragment getInstance() {
        return SetupFragment.SingletonHolder.INSTANCE;
    }


    private LinearLayout logout, dropout;
    private Switch alert;
    private Context mContext;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_setup_fragment, container, false);
        logout = rootView.findViewById(R.id.setup_admin_logout);
        dropout = rootView.findViewById(R.id.setup_admin_dropout);
        alert = rootView.findViewById(R.id.setup_admin_switch);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout.setFocusable(true);
                logout.setClickable(true);
                connectToServerAboutLogout();
            }
        });

        dropout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropOutDialog dialog = new DropOutDialog(mContext, getAccessToken());
                String str = "계정을 삭제하시겠습니까?";
                dialog.callFunction(str);
            }
        });


    }

    private void connectToServerAboutLogout() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.postLogout().enqueue(new Callback<LogoutAccountPost>() {
            @Override
            public void onResponse(Call<LogoutAccountPost> call, Response<LogoutAccountPost> response) {
                if (response.isSuccessful()) {
                    LogoutAccountPost body = response.body();

                    Log.d("Server account Logout", body.getMsg());
                    Log.d("Server account Logout", body.getStatus());
                    getActivity().finish();
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Log.d("Server account Logout", responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutAccountPost> call, Throwable t) {
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
