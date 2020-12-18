package com.example.areact.feed;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedMessageAdapter extends RecyclerView.Adapter<FeedMessageAdapter.ViewHolder> {
    private ArrayList<FeedMessage> block;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView content;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.feed_message_name);
            content = itemView.findViewById(R.id.feed_message_content);

        }
    }

    FeedMessageAdapter(ArrayList<FeedMessage> list) {
        block = list;
    }

    @NotNull
    public FeedMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.feed_message, parent, false);
        FeedMessageAdapter.ViewHolder vh = new FeedMessageAdapter.ViewHolder(view);

        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull FeedMessageAdapter.ViewHolder holder, int position) {
        FeedMessage text = block.get(position);
        holder.name.setText(text.getName());
        holder.content.setText(text.getContent());

        holder.itemView.setElevation(30.0f * position);
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}

