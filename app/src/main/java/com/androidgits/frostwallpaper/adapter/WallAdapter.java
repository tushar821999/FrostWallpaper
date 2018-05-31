package com.androidgits.frostwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidgits.frostwallpaper.R;
import com.androidgits.frostwallpaper.model.Batman;
import com.androidgits.frostwallpaper.model.Wall;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 5/29/2018.
 */

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.MyViewHolder>{

    private Context mContext;
    private List<Wall> mData;

    public WallAdapter(Context mContext,List<Wall> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.row,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, com.androidgits.frostwallpaper.activities.Batman.class);
                intent.putExtra("Id",mData.get(viewHolder.getAdapterPosition()).getId());
                mContext.startActivity(intent);



            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(mData.get(position).getBig_Image())
                .into(holder.imv);
      holder.tv.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imv;
        RelativeLayout category;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            imv = (ImageView) itemView.findViewById(R.id.imv);
           tv = (TextView) itemView.findViewById(R.id.tv);
            category = (RelativeLayout) itemView.findViewById(R.id.category);
        }
    }
}
