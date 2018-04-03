package com.foodexpress.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.foodexpress.app.R;
import com.foodexpress.app.model.HotelModel;

import java.util.List;


public class HotelListAdapter extends BaseAdapter {


    List<HotelModel> arraylist;
    Context context;
    boolean state;
    private int langType;

    public HotelListAdapter(Context context,
                      List<HotelModel> arrayofUsers) {

        arraylist=arrayofUsers;
        this.context=context;

    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int positon, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.spinner_item_view, parent,false);

        HotelModel users = arraylist.get(positon);

        TextView textView=view.findViewById(R.id.spinneride);
        textView.setText(users.getName());




        return view;
    }


}

