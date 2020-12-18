package com.example.areact.group.detail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import java.util.ArrayList;

public class GroupNoticeAdapter extends RecyclerView.Adapter<GroupNoticeAdapter.ViewHolder> {
    private ArrayList<GroupNotice> block;
    private String groupName;
    private Integer id;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_tv, keyword_tv, date_tv;
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.group_notice_important);
            title_tv = view.findViewById(R.id.notice_item_title);
            keyword_tv = view.findViewById(R.id.notice_item_keyword);
            date_tv = view.findViewById(R.id.notice_item_date);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), GroupNoticePostsActivity.class);
                    intent.putExtra("groupName", groupName);
                    intent.putExtra("id", id);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    GroupNoticeAdapter(ArrayList<GroupNotice> list, String groupName, Integer id) {
        this.groupName = groupName;
        this.id = id;
        block = list;
    }

    @NonNull
    @Override
    public GroupNoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_notice_item, parent, false);

        GroupNoticeAdapter.ViewHolder header = new GroupNoticeAdapter.ViewHolder(view);
        return header;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        GroupNotice groupNotice = block.get(position);

        id = holder.getAdapterPosition();
        holder.imageView.setImageDrawable(groupNotice.getImageView());
        holder.keyword_tv.setText(groupNotice.getKeyword());
        holder.title_tv.setText(groupNotice.getTitle());
        holder.date_tv.setText(groupNotice.getDate());
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
