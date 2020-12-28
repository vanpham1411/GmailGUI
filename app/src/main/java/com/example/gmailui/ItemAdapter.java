package com.example.gmailui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<ItemModel> items;
    List<ItemModel> listfull;
    private SearchFilter searchFilter;
    public ItemAdapter(Context context, List<ItemModel> items) {
        this.context = context;
        this.items = items;
        listfull = new ArrayList<>(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.sender =  view.findViewById(R.id.sender);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.title = view.findViewById(R.id.title);
            viewHolder.content = view.findViewById(R.id.content);
            viewHolder.time = view.findViewById(R.id.time);
            viewHolder.favorite = view.findViewById(R.id.favorite);
            view.setTag(viewHolder);

        }
        else viewHolder = (ViewHolder) view.getTag();

        ItemModel item = items.get(i);
        viewHolder.favorite.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                // return false to let list's context menu show
                return false;
            }
        });

        viewHolder.sender.getBackground().setColorFilter(Color.parseColor(item.getMyHexColor()),PorterDuff.Mode.SRC_ATOP);
        viewHolder.name.setText(item.getName());
        viewHolder.sender.setText(String.valueOf(item.getName().charAt(0)));
        viewHolder.title.setText(item.getTitle());
        viewHolder.content.setText(item.getContent());
        viewHolder.time.setText(item.getTime());
        if(viewHolder.favorite.isChecked()) viewHolder.favorite.setButtonDrawable(  R.drawable.ic_baseline_star_24);
        else viewHolder.favorite.setButtonDrawable(  R.drawable.ic_baseline_star_outline_24);
        viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setFavorite(viewHolder.favorite.isChecked());
                notifyDataSetChanged();
            }
        });
        return view;

    }

    @Override
    public Filter getFilter() {
        if(searchFilter==null) {
            searchFilter=new SearchFilter();
        }
        return searchFilter;
    }
    private class SearchFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            //listfull
            if(constraint == null || constraint.length() == 0){
                result.values = listfull;
                result.values = listfull.size();
            }else {
                ArrayList<ItemModel> filterlist = new ArrayList<>();
                for(ItemModel j: listfull ){
                    if(j.getTitle().toLowerCase().contains(constraint) ||j.getName().contains(constraint)){
                        filterlist.add(j);
                    }
                }
                result.values = filterlist;
                result.count = filterlist.size();
            }
            return result;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results.count == 0) {
                notifyDataSetChanged();
            }else {
                items = (ArrayList<ItemModel>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    private class ViewHolder {
        Drawable oval;
        TextView sender;
        TextView name;
        TextView title;
        TextView content;
        TextView time;
        CheckBox favorite;
    }

}