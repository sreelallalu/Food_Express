package com.foodexpress.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodexpress.app.CancelOrder;
import com.foodexpress.app.R;

import java.util.List;

/**
 * Created by sreelal on 27/3/18.
 */



public class CancelOdersAdapter
        extends RecyclerView.Adapter<CancelOdersAdapter.MyViewHolder>
{
    private List<CancelOrder> arraylist;

    Context context;
    AlertDialog mDialog;

    public CancelOdersAdapter(List<CancelOrder> paramList, Context context)
    {
        this.arraylist = paramList;

        this.context = context;
    }

    public int getItemCount()
    {
        return this.arraylist.size();
    }

    public void onBindViewHolder(MyViewHolder paramMyViewHolder, final int paramInt)
    {
        CancelOrder data=arraylist.get(paramInt);


        /*paramMyViewHolder.item_cl.setText(data);
        paramMyViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraylist.remove(paramInt);
                notifyDataSetChanged();
            }
        });
*/




    }

    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
        return new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.temp, paramViewGroup, false));
    }

    public class MyViewHolder
            extends RecyclerView.ViewHolder
    {
        public TextView item_cl;
        public TextView date_cl;
        public TextView fee_cl;
        public TextView cancel_cl;
         public RelativeLayout layout;



        public MyViewHolder(View paramView)
        {
            super(paramView);
            this.item_cl = ((TextView)paramView.findViewById(R.id.item_cl));
            this.date_cl = ((TextView)paramView.findViewById(R.id.date_cl));
            this.fee_cl = ((TextView)paramView.findViewById(R.id.fee_cl));
            this.cancel_cl = ((TextView)paramView.findViewById(R.id.cancel_cl));




        }
    }
}
