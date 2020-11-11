package com.example.areact;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;

import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupSelectBottomSheet extends BottomSheetDialogFragment {
    private Button cancel_btn, request_btn;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_select, container, false);
        cancel_btn = rootView.findViewById(R.id.group_select_cancel_btn);
        request_btn = rootView.findViewById(R.id.group_select_request_btn);

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog d = (BottomSheetDialog) dialogInterface;
                View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm = getChildFragmentManager();
                ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                ft.replace(R.id.group_select_layout, new GroupRequestJoin());
                ft.commit();

            }
        });

        return rootView;
    }
}