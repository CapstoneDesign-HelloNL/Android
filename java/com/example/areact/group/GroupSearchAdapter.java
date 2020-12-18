package com.example.areact.group;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;

import java.util.ArrayList;

public class GroupSearchAdapter extends RecyclerView.Adapter<GroupSearchAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<GroupSearchItem> unFilteredList;
    ArrayList<GroupSearchItem> filteredList;
    String groupName, univName;
    FragmentManager fragmentManager;

    GroupSearchAdapter(Context context, ArrayList<GroupSearchItem> list, FragmentManager fragmentManager) {
        super();
        this.context = context;
        this.unFilteredList = list;
        this.filteredList = list;
        this.fragmentManager = fragmentManager;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName, univName;

        ViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_search_item_g_n);
            univName = itemView.findViewById(R.id.group_search_item_s_n);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GroupSelectBottomSheet groupSelectBottomSheet = new GroupSelectBottomSheet();

                    Bundle bundle = new Bundle();
                    bundle.putString("groupName", groupName.getText().toString());
                    groupSelectBottomSheet.setArguments(bundle);
                    groupSelectBottomSheet.show(fragmentManager, "group_select_bottom_sheet");
                }
            });
        }
    }

    @NonNull
    @Override
    public GroupSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_search_item, parent, false);

        GroupSearchAdapter.ViewHolder vh = new GroupSearchAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupSearchAdapter.ViewHolder holder, int position) {
        GroupSearchItem searchItem = filteredList.get(position);
        holder.groupName.setText(searchItem.getGroupName());
        holder.univName.setText(searchItem.getUnivName());
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filteredList = unFilteredList;
                } else {
                    ArrayList<GroupSearchItem> filteringList = new ArrayList<>();
                    for (int i = 0; i < unFilteredList.size(); i++) {
                        if ((unFilteredList.get(i).getGroupName().toLowerCase()).contains(charString.toLowerCase())) {
                            groupName = unFilteredList.get(i).getGroupName();
                            univName = unFilteredList.get(i).getUnivName();
                            GroupSearchItem item = new GroupSearchItem(groupName, univName);
                            filteringList.add(item);
                        }
                    }
                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<GroupSearchItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}