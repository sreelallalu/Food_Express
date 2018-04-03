package com.foodexpress.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.foodexpress.app.R;
import com.foodexpress.app.model.ItemModel;

import java.util.List;

/**
 * Created by sreelal on 27/3/18.
 */


public class ItemsAdapter extends BaseAdapter {


    List<ItemModel> arraylist;
    Context context;
    boolean state;
    private int langType;

    public ItemsAdapter(Context context,
                        List<ItemModel> arrayofUsers) {

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

        String users = arraylist.get(positon).getName();

        TextView textView=view.findViewById(R.id.spinneride);
        textView.setText(users);




        return view;
    }


}

