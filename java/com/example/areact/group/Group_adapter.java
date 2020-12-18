package com.example.areact.group;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.example.areact.group.detail.GroupDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Group_adapter extends RecyclerView.Adapter<Group_adapter.ViewHolder> {
    private ArrayList<GroupEntity> block;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView group_name, group_in_count, society_name;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.group_item_photo);
            group_name = itemView.findViewById(R.id.group_item_name_tv);
            group_in_count = itemView.findViewById(R.id.group_item_count_tv);
            society_name = itemView.findViewById(R.id.group_item_society_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), GroupDetailActivity.class);
                    intent.putExtra("groupName", group_name.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void setGroup(GroupEntity group) {
            group_name.setText(group.getGroup_name());
            group_in_count.setText(group.getGroup_in_count());
            society_name.setText(group.getSociety_name());
        }
    }

    Group_adapter(ArrayList<GroupEntity> list) {
        block = list;
    }

    @NotNull
    public Group_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_item, parent, false);

        Group_adapter.ViewHolder vb = new Group_adapter.ViewHolder(view);

        return vb;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull Group_adapter.ViewHolder holder, int position) {
        GroupEntity groupEntity = block.get(position);
        holder.setGroup(groupEntity);
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
