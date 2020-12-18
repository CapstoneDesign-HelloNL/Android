package com.example.areact;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.areact.group.detail.GroupDetailActivity;
import com.example.areact.group.server.GroupDelete;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDialog {
    private Context context;
    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;
    private String groupName;
    private String accessToken;
    private String refreshToken;

    public DeleteDialog(Context context, String groupName, String accessToken, String refreshToken) {
        this.context = context;
        this.groupName = groupName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public void callFunction(final String title) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_custom_dialog);
        dialog.show();

        final TextView title_tv = dialog.findViewById(R.id.delete_dialog_title);
        title_tv.setText(title);
        Button okBtn = dialog.findViewById(R.id.delete_dialog_btn);
        Button cancelBtn = dialog.findViewById(R.id.delete_dialog_cancel_btn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGroupToServer();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void deleteGroupToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, accessToken);
        retrofitAPI.deleteGroupDel(groupName).enqueue(new Callback<GroupDelete>() {
            @Override
            public void onResponse(Call<GroupDelete> call, Response<GroupDelete> response) {
                if (response.isSuccessful()) {
                    GroupDelete groupDelete = response.body();
                    Log.d("groupDelete", groupDelete.getMsg());
                    Log.d("groupDelete", groupDelete.getStatus());

                    Intent intent = new Intent(context, MainActivity.class);
                    GroupDetailActivity activity = new GroupDetailActivity();
                    activity.finish();
                    context.startActivity(intent);
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
            public void onFailure(Call<GroupDelete> call, Throwable t) {
                t.printStackTrace();
                Log.d("groupDelete", "실패");
            }
        });
    }
}
