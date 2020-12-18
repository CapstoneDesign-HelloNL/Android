package com.example.areact.group.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<ScheduleTodo> block;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView todo_tv;
        TextView date_tv;
        ImageButton delete_btn;

        ViewHolder(View itemView) {
            super(itemView);

            todo_tv = itemView.findViewById(R.id.schedule_item_tv);
            date_tv = itemView.findViewById(R.id.schedule_item_date_tv);
            delete_btn = itemView.findViewById(R.id.schedule_item_delete_btn);

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        block.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

    ScheduleAdapter(ArrayList<ScheduleTodo> list) {
        block = list;
    }

    @NotNull
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.schedule_item, parent, false);
        ScheduleAdapter.ViewHolder vh = new ScheduleAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleTodo text = block.get(position);
        holder.todo_tv.setText(text.getTitle());
        holder.date_tv.setText(text.getDate());
    }

    @Override
    public int getItemCount() {
        return block.size();
    }
}
