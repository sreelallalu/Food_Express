package com.foodexpress.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodexpress.app.R;

import java.util.List;

/**
 * Created by sreelal on 27/3/18.
 */



public class StudentAdapter
        extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>
{
    private List<String> arraylist;

    Context context;
    AlertDialog mDialog;

    public StudentAdapter(List<String> paramList, Context context)
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
        final String data=arraylist.get(paramInt);
        paramMyViewHolder.name.setText(data);
        paramMyViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraylist.remove(paramInt);
                notifyDataSetChanged();
            }
        });





    }

    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
        return new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.studnet_singlelist, paramViewGroup, false));
    }

    public class MyViewHolder
            extends RecyclerView.ViewHolder
    {
        public TextView name;
         public RelativeLayout layout;



        public MyViewHolder(View paramView)
        {
            super(paramView);
            this.name = ((TextView)paramView.findViewById(R.id.name));
            this.layout = ((RelativeLayout)paramView.findViewById(R.id.delete_oldpass));




        }
    }
}
