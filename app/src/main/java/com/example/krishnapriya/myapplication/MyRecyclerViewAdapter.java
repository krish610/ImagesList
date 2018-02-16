package com.example.krishnapriya.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishnapriya on 2/15/18.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private ArrayList<ItemData> ItemDataList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(Context context, List<ItemData> ItemDataList) {
        this.ItemDataList = (ArrayList<ItemData>) ItemDataList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ItemData itemData = ItemDataList.get(i);
        Log.d("Adapter", "Size: "+ItemDataList.size());

//        //Render image using Picasso library
        if (!TextUtils.isEmpty(itemData.getThumbnail())) {
            Picasso.with(mContext).load(itemData.getThumbnail())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(60, 60)
                    .into(customViewHolder.thumbnailImageView);
        }


        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(itemData.getTitle()));

/*
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(itemData);
            }
        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.textView.setOnClickListener(listener);*/
    }

    @Override
    public int getItemCount() {
        return (null != ItemDataList ? ItemDataList.size() : 0);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnailImageView;
        private TextView textView;
        private ImageView imageView;

        public CustomViewHolder(View view) {
            super(view);
            this.thumbnailImageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ItemData item);
    }
}
