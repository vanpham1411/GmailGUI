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
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    Context context;
    List<ItemModel> items;

    public ItemAdapter(Context context, List<ItemModel> items) {
        this.context = context;
        this.items = items;
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
