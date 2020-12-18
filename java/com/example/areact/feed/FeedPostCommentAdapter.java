package com.example.areact.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedPostCommentAdapter extends RecyclerView.Adapter<FeedPostCommentAdapter.ViewHolder> {
    private ArrayList<FeedPostComment> block;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, comment;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.feed_post_detail_comment_name);
            date = itemView.findViewById(R.id.feed_post_detail_comment_date);
            comment = itemView.findViewById(R.id.feed_post_detail_comment_body);
        }

    }

    FeedPostCommentAdapter(ArrayList<FeedPostComment> list) {
        block = list;
    }

    @NotNull
    public FeedPostCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.feed_post_detail_comment, parent, false);
        FeedPostCommentAdapter.ViewHolder vb = new FeedPostCommentAdapter.ViewHolder(view);

        return vb;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        FeedPostComment post = block.get(position);
        holder.name.setText(post.getName());
        holder.comment.setText(post.getComment());
        holder.date.setText(post.getDate());
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}

