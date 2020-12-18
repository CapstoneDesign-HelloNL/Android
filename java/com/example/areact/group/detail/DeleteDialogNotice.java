package com.example.areact.group.detail;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.areact.R;
import com.example.areact.group.detail.server.GroupNoticeDel;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDialogNotice {
    private Context context;
    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;
    private String groupName;
    private Integer id;
    private String accessToken;
    private String refreshToken;

    public DeleteDialogNotice(Context context, String groupName, Integer id, String accessToken, String refreshToken) {
        this.context = context;
        this.groupName = groupName;
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void callFunction(final String title) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_custom_dialog);
        dialog.show();

        //context2 = GroupNotice_detail.;
        final TextView title_tv = dialog.findViewById(R.id.delete_dialog_title);
        title_tv.setText(title);
        Button okBtn = dialog.findViewById(R.id.delete_dialog_btn);
        Button cancelBtn = dialog.findViewById(R.id.delete_dialog_cancel_btn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGroupNoticeToServer();
                dialog.dismiss();
                ((GroupNoticePostsActivity) context).onBackPressed();
                //context로 액티비티 화면 불러오기/ onBackPressed
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void deleteGroupNoticeToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, accessToken);
        retrofitAPI.delNoticeData(groupName, id).enqueue(new Callback<GroupNoticeDel>() {
            @Override
            public void onResponse(Call<GroupNoticeDel> call, Response<GroupNoticeDel> response) {
                if (response.isSuccessful()) {
                    GroupNoticeDel body = response.body();

                    Log.d("Server NoticeDel", body.getMsg());
                    Log.d("Server NoticeDel", body.getStatus());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Log.d("Server NoticeDel", responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupNoticeDel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
