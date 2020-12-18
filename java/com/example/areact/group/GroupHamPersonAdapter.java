package com.example.areact.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import java.util.ArrayList;

public class GroupHamPersonAdapter extends RecyclerView.Adapter<GroupHamPersonAdapter.ViewHolder> {
    private ArrayList<GroupHamPerson> block;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, stdNum, memberRank;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.group_ham_person_name);
            stdNum = view.findViewById(R.id.group_ham_person_stdnum);
            memberRank = view.findViewById(R.id.group_ham_person_memberRank);
        }
    }

    GroupHamPersonAdapter(ArrayList<GroupHamPerson> list) {
        block = list;
    }

    @NonNull
    @Override
    public GroupHamPersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_ham_person, parent, false);

        GroupHamPersonAdapter.ViewHolder header = new GroupHamPersonAdapter.ViewHolder(view);
        return header;

    }

    @Override
    public void onBindViewHolder(@NonNull final GroupHamPersonAdapter.ViewHolder holder, int position) {
        GroupHamPerson groupNotice = block.get(position);

        holder.name.setText(groupNotice.getName());
        holder.stdNum.setText(groupNotice.getStdNum());
        holder.memberRank.setText(groupNotice.getMemberRank());
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
