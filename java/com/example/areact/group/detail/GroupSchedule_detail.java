package com.example.areact.group.detail;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Collections;

public class GroupSchedule_detail extends AppCompatActivity {
    private MaterialCalendarView mCalendarView;
    private RecyclerView recyclerView;
    String title_str, content_str;
    String fullDate = "";

    Integer mYear, mMonth, mDay;
    ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
    ScheduleEventDecorator eventDecorator = new ScheduleEventDecorator(Color.RED, calendarDayList);
    final ArrayList<ScheduleTodo> todo = new ArrayList<>();
    final ScheduleAdapter adapter = new ScheduleAdapter(todo);

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_schedule_detail);
        changeStatusBarColor("#D47FA6");

        final ImageButton back_btn = findViewById(R.id.group_schedule_back_btn);
        final ImageButton add_btn = findViewById(R.id.group_schedule_add_btn);
        mCalendarView = findViewById(R.id.calendar_view);
        recyclerView = findViewById(R.id.group_schedule_recycler);

        //recyclerview setting
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        //뒤로 가기 눌렀을 떄
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //날짜가 바뀔 때 저장된 데이터를 보여주기
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @SuppressLint("NewApi")
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                setmYear(widget.getSelectedDate().getYear());
                setmMonth(widget.getSelectedDate().getMonth() + 1);
                setmDay(mDay = widget.getSelectedDate().getDay());
                fullDate = getmYear() + "-" + getmMonth() + "-" + getmDay();
                adapter.notifyDataSetChanged();
            }
        });

        //추가 버튼 눌렀을 때 다이얼로그 호출

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fullDate.isEmpty()) {
                    callDialog(fullDate);
                }
            }
        });
    }

    //커스텀 다이얼로그 호출
    public void callDialog(final String date) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.group_custom_dialog);
        //dialog.setFeatureDrawable(R.id.custom_dialog,getResources().getDrawable(R.drawable.feed_form));
        dialog.show();

        final EditText title_et = dialog.findViewById(R.id.dialog_title_et);
        final EditText content_et = dialog.findViewById(R.id.dialog_content_et);
        ImageButton cancel_btn = dialog.findViewById(R.id.dialog_cancel_btn);
        ImageButton complete_btn = dialog.findViewById(R.id.dialog_complete_btn);

        //다이얼로그 취소 버튼
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //다이얼로그에서 입력 완료 시 recyclerview에 뿌려주기
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_str = title_et.getText().toString();
                content_str = content_et.getText().toString();
                ScheduleTodo scheduleTodo = new ScheduleTodo(title_str, date);
                todo.add(scheduleTodo);
                adapter.notifyDataSetChanged();
                mCalendarView.addDecorator(new ScheduleEventDecorator(Color.RED, Collections.singleton(CalendarDay.from(getmYear(), getmMonth() - 1, getmDay()))));
                dialog.dismiss();
            }
        });
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
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

    public void setmYear(Integer mYear) {
        this.mYear = mYear;
    }

    public void setmMonth(Integer mMonth) {
        this.mMonth = mMonth;
    }

    public void setmDay(Integer mDay) {
        this.mDay = mDay;
    }

    public Integer getmDay() {
        return mDay;
    }

    public Integer getmMonth() {
        return mMonth;
    }

    public Integer getmYear() {
        return mYear;
    }
}
