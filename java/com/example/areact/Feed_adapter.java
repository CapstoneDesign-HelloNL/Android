package com.example.areact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Feed_adapter extends RecyclerView.Adapter<Feed_adapter.ViewHolder> {
    private ArrayList<FeedPost> block = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }

    }
    Feed_adapter(ArrayList<FeedPost> list) {
        block = list;
    }

    public Feed_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_feed_post,parent,false);
        Feed_adapter.ViewHolder vb = new Feed_adapter.ViewHolder(view);

        return vb;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedPost text = block.get(position);
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
