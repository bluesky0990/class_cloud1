package com.nellem.cloud1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LvAdapter extends BaseAdapter {
    // 리스트뷰 내에 목록이 될 값들을 ArrayList로 선언
    ArrayList<LvItem> itemlist = new ArrayList<>();
    LvAdapter() {};

    //  커스텀리스트뷰에 입력된 목록의 갯수를 구할 메소드
    @Override
    public int getCount() { return itemlist.size(); }


    // 커스텀리스트뷰에 입력된 목록중 index를 입력하여 받을 값
    @Override
    public Object getItem(int i) { return itemlist.get(i); }


    // 입력 된 목록의 index 값 받기
    @Override
    public long getItemId(int i) { return i; }


    // 목록을 표시해줄 레이아웃 구성
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

    //  메인클래스에서 받은 값들을 addItem()에 입력하여 ArrayList에 추가
    //  (MainActivity.java - 57번줄)
    public void addItem(String time, String temp, String humi) {
        LvItem item = new LvItem();
        item.setTime(time);
        item.setTemp(temp);
        item.setHumi(humi);

        itemlist.add(item);

    }
}
