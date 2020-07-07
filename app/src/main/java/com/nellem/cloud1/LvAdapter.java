package com.nellem.cloud1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LvAdapter extends BaseAdapter {
    ArrayList<LvItem> itemlist = new ArrayList<>();
    LvAdapter() {};

    @Override
    public int getCount() { return itemlist.size(); }

    @Override
    public Object getItem(int i) { return itemlist.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context c = viewGroup.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem, viewGroup, false);
        }

        TextView timeView = (TextView)view.findViewById(R.id.textView5);
        TextView tempView = (TextView)view.findViewById(R.id.textView6);
        TextView humiView = (TextView)view.findViewById(R.id.textView7);

        LvItem listitem = itemlist.get(i);

        timeView.setText(listitem.getTime());
        tempView.setText(listitem.getTemp());
        humiView.setText(listitem.getHumi());

        return view;
    }

    public void addItem(String time, String temp, String humi) {
        LvItem item = new LvItem();
        item.setTime(time);
        item.setTemp(temp);
        item.setHumi(humi);

        itemlist.add(item);

    }
}
