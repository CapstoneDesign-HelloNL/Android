package com.example.areact.feed;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Feed_adapter extends RecyclerView.Adapter<Feed_adapter.ViewHolder> {
    private ArrayList<FeedPost> block = null;
    Integer likeCount = 0;
    Boolean isSelected;
    FragmentManager fm;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, body;
        TextView like, comment;
        AppCompatImageView likeBtn;
        LinearLayout feedPostLayout;
        ImageButton commentBtn;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.feed_post_name);
            date = itemView.findViewById(R.id.feed_post_date);
            body = itemView.findViewById(R.id.feed_post_todo);
            like = itemView.findViewById(R.id.feed_post_like_tv);
            likeBtn = itemView.findViewById(R.id.feed_post_like_btn);
            comment = itemView.findViewById(R.id.feed_post_comment_tv);
            commentBtn = itemView.findViewById(R.id.feed_post_comment_btn);
            feedPostLayout = itemView.findViewById(R.id.feed_post);
        }

    }

    Feed_adapter(ArrayList<FeedPost> list, FragmentManager fm) {
        block = list;
        this.fm = fm;
    }

    @NotNull
    public Feed_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_feed_post, parent, false);
        Feed_adapter.ViewHolder vb = new Feed_adapter.ViewHolder(view);

        return vb;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final FeedPost post = block.get(position);
        holder.name.setText(post.getName());
        holder.body.setText(post.getBody());
        holder.date.setText(post.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.main_frame,
                        new FeedPostDetail(post.getName(), post.getDate(), post.getBody())).commit();
            }
        });

        likeCount = 0;
        isSelected = !holder.likeBtn.isSelected();

        if (position % 2 != 0 || position == 1) {
            holder.feedPostLayout.setBackgroundResource(R.drawable.shape_feed_blue);
            holder.comment.setTextColor(Color.parseColor("#ffffff"));
            holder.like.setTextColor(Color.parseColor("#ffffff"));
            holder.comment.setBackgroundColor(Color.parseColor("#4B9AC8"));
            holder.likeBtn.setImageResource(R.drawable.toggle_btn2);
            holder.commentBtn.setBackgroundResource(R.drawable.icons_comment_white);
            holder.name.setTextColor(Color.parseColor("#ffffff"));
            holder.date.setTextColor(Color.parseColor("#ffffff"));
            holder.body.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.feedPostLayout.setBackgroundResource(R.drawable.shape_feed);
        }

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeBtn.setSelected(!holder.likeBtn.isSelected());
                if (isSelected) {
                    likeCount++;
                    holder.like.setText(String.valueOf(likeCount));
                    isSelected = !holder.likeBtn.isSelected();
                    System.out.println("true : " + likeCount);

                } else {
                    likeCount--;
                    isSelected = !holder.likeBtn.isSelected();
                    holder.like.setText(String.valueOf(likeCount));
                    System.out.println("false : " + likeCount);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
