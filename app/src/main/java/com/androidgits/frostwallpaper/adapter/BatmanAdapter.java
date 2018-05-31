package com.androidgits.frostwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidgits.frostwallpaper.R;
import com.androidgits.frostwallpaper.activities.Download;
import com.androidgits.frostwallpaper.model.Batman;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lenovo on 5/30/2018.
 */

public class BatmanAdapter extends RecyclerView.Adapter<BatmanAdapter.MyViewHolder> {
    private Context mContext;
    List<Batman> mData;

    public BatmanAdapter(Context mContext, List<Batman> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.batman_row,parent,false);

       final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(mContext,Download.class);
                inte.putExtra("ImageUrl",mData.get(viewHolder.getAdapterPosition()).getImage_Url());
                mContext.startActivity(inte);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(mData.get(position).getImage_Url())

                .into(holder.imv);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imv;
        RelativeLayout wallpaper;
        public MyViewHolder(View itemView) {
            super(itemView);

            imv = (ImageView) itemView.findViewById(R.id.imv);
            wallpaper = (RelativeLayout) itemView.findViewById(R.id.wallpaper);
        }
    }
}
