package com.example.areact;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupSearchAdapter extends BaseAdapter implements Filterable {
    private ArrayList<GroupSearchItem> listViewItemList = new ArrayList<GroupSearchItem>();
    private ArrayList<GroupSearchItem> filteredItemList = listViewItemList;
    Filter listFilter;

    public GroupSearchAdapter() {}

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_search_item,viewGroup,false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.group_search_item_image);
        TextView groupTextView = convertView.findViewById(R.id.group_search_item_g_n);
        TextView societyTextView = convertView.findViewById(R.id.group_search_item_s_n);

        GroupSearchItem searchItem = filteredItemList.get(i);

        iconImageView.setImageDrawable(searchItem.getIcon());
        groupTextView.setText(searchItem.getGroup_name());
        societyTextView.setText(searchItem.getSociety_name());


        return convertView;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return filteredItemList.get(i);
    }

    public void addItem(Drawable icon, String group, String society) {
        GroupSearchItem item = new GroupSearchItem();

        item.setIcon(icon);
        item.setGroup_name(group);
        item.setSociety_name(society);

        listViewItemList.add(item);
    }

    @Override
    public Filter getFilter() {
        if(listFilter ==null) {
            listFilter = new ListFilter();
    }
        return listFilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if(charSequence ==null || charSequence.length() == 0) {
                results.values = listViewItemList;
                results.count = listViewItemList.size();
            } else {
                ArrayList<GroupSearchItem> itemList = new ArrayList<>();

                for(GroupSearchItem item : listViewItemList) {
                    if(item.getGroup_name().toUpperCase().contains(charSequence.toString().toUpperCase()))
                    {
                        itemList.add(item);
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredItemList = (ArrayList<GroupSearchItem>) filterResults.values;

            if(filterResults.count >0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
        }
        }
    }
}