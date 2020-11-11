package com.example.areact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SocialFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private View drawerView;
    private TextView tv,feed_write, feed_msg;
    private RecyclerView recyclerView;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_social_fragment, container, false);

        final ArrayList<FeedPost> post = new ArrayList<>();
        final Feed_adapter adapter = new Feed_adapter(post);
        recyclerView = rootView.findViewById(R.id.feed_post_recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        feed_write = rootView.findViewById(R.id.feed_ham_write);
        feed_msg= rootView.findViewById(R.id.feed_ham_msg);
        btn = rootView.findViewById(R.id.feed_post_test_btn);

        ImageButton ham_btn = rootView.findViewById(R.id.feed_hamburger_main);
        //tv = rootView.findViewById(R.id.feed_post);

        drawerLayout = rootView.findViewById(R.id.feed_drawer_layout_main);
        drawerView = rootView.findViewById(R.id.feed_drawer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todo = null;
                FeedPost newPost = new FeedPost();
                post.add(newPost);
                adapter.notifyDataSetChanged();
            }
        });

        //게시물 클릭 시
       /* tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedSub_detail.class);
                startActivity(intent);
            }
        });*/

        ham_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        feed_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new FeedPostWrite());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        feed_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return rootView;
    }
}
