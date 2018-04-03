package com.foodexpress.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodexpress.app.OrderAddress;
import com.foodexpress.app.R;
import com.foodexpress.app.model.ItemoderModel;

import java.util.List;

/**
 * Created by sreelal on 2/4/18.
 */


public class OderListAdapter
        extends RecyclerView.Adapter<OderListAdapter.MyViewHolder> {
    private List<ItemoderModel> arraylist;

    Context context;
    AlertDialog mDialog;

    public OderListAdapter(List<ItemoderModel> paramList, Context context) {
        this.arraylist = paramList;

        this.context = context;
    }

    public int getItemCount() {
        return this.arraylist.size();
    }

    public void onBindViewHolder(MyViewHolder paramMyViewHolder, final int paramInt) {
        final ItemoderModel data = arraylist.get(paramInt);
        paramMyViewHolder.date.setText(data.getOr_date());
        paramMyViewHolder.item.setText(data.getItem());
        //paramMyViewHolder.address.setText(data);
        paramMyViewHolder.delivery.setText(data.getOr_delivery());
        paramMyViewHolder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderAddress.class);
                intent.putExtra("address",data.getOr_address());
                intent.putExtra("or_delivery",data.getOr_delivery());
                context.startActivity(intent);
            }
        });

    }

    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.itemlist, paramViewGroup, false));
    }

    public class MyViewHolder
            extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView item;
        public TextView delivery;
        public TextView address;

        public RelativeLayout layout;


        public MyViewHolder(View paramView) {
            super(paramView);
            this.date = ((TextView) paramView.findViewById(R.id.date_or));
            this.item = ((TextView) paramView.findViewById(R.id.item_or));
            this.delivery = ((TextView) paramView.findViewById(R.id.delivery_or));
            this.address = ((TextView) paramView.findViewById(R.id.address_or));


        }
    }
}
