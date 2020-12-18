package com.example.areact.setup;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.areact.R;
import com.example.areact.group.detail.GroupNotice_detail;
import com.example.areact.login.server.DropOutAccountDel;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DropOutDialog {
    private Context context;
    private String accessToken;

    public DropOutDialog(Context context, String accessToken) {
        this.context = context;
        this.accessToken = accessToken;
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
                deleteAdminToServer();
                context.startActivity(new GroupNotice_detail().getIntent());
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void deleteAdminToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, accessToken);
        retrofitAPI.delDroupout().enqueue(new Callback<DropOutAccountDel>() {
            @Override
            public void onResponse(Call<DropOutAccountDel> call, Response<DropOutAccountDel> response) {
                if (response.isSuccessful()) {
                    DropOutAccountDel adminDelete = response.body();

                    Log.d("AdminDelete", adminDelete.getMsg());
                    Log.d("AdminDelete", adminDelete.getStatus());
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
            public void onFailure(Call<DropOutAccountDel> call, Throwable t) {
                t.printStackTrace();
                Log.d("adminDelete", "실패");
            }
        });
    }
}
