package com.example.areact.group.detail;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.areact.DeleteDialog;
import com.example.areact.R;
import com.example.areact.group.GroupHamAgendaFragment;
import com.example.areact.group.GroupHamInfoFragment;
import com.example.areact.group.GroupHamPersonFragment;

public class GroupDetailActivity extends FragmentActivity {
    private DrawerLayout drawerLayout;
    private TextView groupName;
    private View drawerView;
    private ImageButton del_btn;
    private DeleteDialog deleteDialog;

    private String title;
    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        //상태바 색상 변경
        changeStatusBarColor();

        //스케줄, 공지사항, 갤러리 탭 + Button binding
        LinearLayout schedule_layout = findViewById(R.id.group_detail_layout_schedule);
        LinearLayout notice_layout = findViewById(R.id.group_detail_layout_notice);
        LinearLayout gallery_layout = findViewById(R.id.group_detail_layout_gallery);
        ImageButton back_btn = findViewById(R.id.group_detail_back_btn);
        ImageButton ham_btn = findViewById(R.id.group_detail_ham);
        groupName = findViewById(R.id.group_detail_name);
        del_btn = findViewById(R.id.group_ham_delete_btn);

        //hamberger bar element binding
        TextView group_info = findViewById(R.id.group_ham_info);
        TextView group_person = findViewById(R.id.group_ham_person);
        TextView group_agenda = findViewById(R.id.group_ham_agenda);
        TextView group_name_top = findViewById(R.id.group_ham_name);

        //어댑터에서 그룹 이름 받아와서 설정하기
        Intent intent = getIntent();
        final String groupNameStr = intent.getStringExtra("groupName");
        groupName.setText(groupNameStr);

        //햄버거 바 상단 이름 설정
        group_name_top.setText(groupNameStr);

        //deleteDialog setting
        String getAccessToken = getAccessToken();
        String getRefreshToken = getRefreshToken();
        deleteDialog = new DeleteDialog(GroupDetailActivity.this, groupNameStr, getAccessToken, getRefreshToken);

        //hamberger bar binding
        drawerLayout = findViewById(R.id.group_fragment);
        drawerView = findViewById(R.id.group_drawer);
        final View view1 = findViewById(R.id.frame_layout_viewID);

        //hamberger bar Open
        ham_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
                view1.setClickable(true);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                view1.setClickable(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        //동아리 정보 클릭 시
        group_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(GroupHamInfoFragment.getInstance());
                drawerLayout.closeDrawer(drawerView);
            }
        });

        //명단 클릭 시
        group_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new GroupHamPersonFragment());
                drawerLayout.closeDrawer(drawerView);
            }
        });

        //마음의 소리함 클릭 시
        group_agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(GroupHamAgendaFragment.getInstance());
                drawerLayout.closeDrawer(drawerView);
            }
        });

        //그룹 삭제 버튼 클릭 시
        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "정말 그룹을 삭제하시겠습니까?";
                deleteDialog.callFunction(title);
            }
        });

        //일정 메뉴 클릭 시
        schedule_layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroupSchedule_detail.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GroupDetailActivity.this,
                        Pair.create(view.findViewById(R.id.group_detail_schedule_en_tv), "sche_en_transition"),
                        Pair.create(view.findViewById(R.id.group_detail_schedule_tv), "sche_transition"));
                startActivity(intent, options.toBundle());
            }
        });

        //공지사항 메뉴 클릭 시
        notice_layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroupNotice_detail.class);
                intent.putExtra("groupName", groupNameStr);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GroupDetailActivity.this,
                        Pair.create(view.findViewById(R.id.group_detail_notice_en_tv), "notice_en_transition"),
                        Pair.create(view.findViewById(R.id.group_detail_notice_tv), "notice_transition"));
                startActivity(intent, options.toBundle());
            }
        });

        //갤러리 메뉴 클릭 시
        gallery_layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroupGallery_detail.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GroupDetailActivity.this,
                        Pair.create(view.findViewById(R.id.group_detail_gallery_en_tv), "gallery_en_transition"),
                        Pair.create(view.findViewById(R.id.group_detail_gallery_tv), "gallery_transition"));
                startActivity(intent, options.toBundle());
            }
        });

        //뒤로 가기 버튼 누를 시
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

 /*   @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if(intent==null) {
            intent = new Intent();
        }
        super.startActivityForResult(intent, requestCode);
    }*/

    private void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("groupName", groupName.getText().toString());
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.group_fragment, fragment);
        ft.addToBackStack(null);

        ft.commit();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FCFCFC"));
        }
    }

    private String getAccessToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    public String getRefreshToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_refresh_token", "");
        return token;
    }
}
