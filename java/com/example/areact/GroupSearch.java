package com.example.areact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GroupSearch extends AppCompatActivity implements View.OnClickListener{
    ListView listView = null;
    ImageButton back_btn;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_search);

        final GroupSearchAdapter adapter;
        adapter = new GroupSearchAdapter();

        back_btn = findViewById(R.id.group_search_back_btn);
        listView = findViewById(R.id.search_listView);
        listView.setAdapter(adapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupSearch.super.onBackPressed();
            }
        });

        //list 예시
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.test_image),
                "NL", "서울과학기술대학교 컴퓨터공학과\r\n");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.test_image),
                "SNL", "서울소재 연합 동아리\r\n");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.test_image),
                "QNL", "경희대학교 경영학과 \r\n");

        //item 클릭시 해당 뷰로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GroupSelectBottomSheet groupSelectBottomSheet = new GroupSelectBottomSheet();
                groupSelectBottomSheet.show(getSupportFragmentManager(), "group_select__bottom_sheet");
            }
        });


        EditText editTextFilter = findViewById(R.id.group_search_et);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString();

                ((GroupSearchAdapter)listView.getAdapter()).getFilter().filter(filterText);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
