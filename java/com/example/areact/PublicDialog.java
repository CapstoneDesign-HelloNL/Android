package com.example.areact;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PublicDialog {
    private Context context;

    public PublicDialog(Context context) {
        this.context = context;
    }

    public void callFunction(final String title, final String body) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.public_custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_custom_dialog);
        dialog.show();

        final TextView title_tv = dialog.findViewById(R.id.public_dialog_title);
        final TextView body_tv = dialog.findViewById(R.id.public_dialog_body);
        title_tv.setText(title);
        body_tv.setText(body);
        Button button = dialog.findViewById(R.id.public_dialog_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
