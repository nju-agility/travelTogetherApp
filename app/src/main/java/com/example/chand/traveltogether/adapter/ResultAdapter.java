package com.example.chand.traveltogether.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.model.ActivityEntity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ActivityEntity> activities;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;
    private final int NO_DATA = 0, OFFICIAL = 1, UNOFFICIAL = 2;
    private WeakReference<Context> mcontext;

    public ResultAdapter(List<ActivityEntity> list, Context context) {
        activities = list;
        mcontext = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        RecyclerView.ViewHolder mHolder = null;
        switch (i) {
            case NO_DATA:
                mHolder = new ImageViewHolder(mInflater.inflate(R.layout.cardview_nodata_history, viewGroup, false));
                break;
            case OFFICIAL:
                mHolder = new ImageViewHolder(mInflater.inflate(R.layout.cardview_official, viewGroup, false));
                break;
            case UNOFFICIAL:
                mHolder = new UnOfficialHolder(mInflater.inflate(R.layout.cardview_unofficial, viewGroup, false));
                break;
            default:
                mHolder = new ImageViewHolder(mInflater.inflate(R.layout.cardview_nodata_history, viewGroup, false));
                break;
        }
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (activities.size() <= 0) { //无数据的情况
            return;
        }
        switch (getItemViewType(i)) {
            case OFFICIAL:
//                ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
//                imageViewHolder.mIvIcon.setImageResource(activities.get(i).getIcon());
//                imageViewHolder.mIvIcon2.setImageResource(activities.get(i).getIcon());
//                imageViewHolder.mIvIcon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(onItemClickListener != null){
//                            onItemClickListener.OnItemClick(v, i);
//                        }
//                    }
//                });
                break;
            case UNOFFICIAL:
                UnOfficialHolder holder = (UnOfficialHolder) viewHolder;
//                holder.activity_pic.setImageResource(R.drawable.testactivitypic);
                String url = activities.get(i).getActivityURL();

                boolean exits = url != null;
                if (!exits||url.equals("/image/Dont't find image!") || url.equals("")) {
                    Glide.with(mcontext.get()).load(R.drawable.testactivitypic).into(holder.activity_pic);
                } else {
                    Glide.with(mcontext.get()).load(mcontext.get().getString(R.string.base_url) + url).into(holder.activity_pic);
                }
                holder.activity_title.setText(activities.get(i).getTitle());
                holder.activity_city.setText(activities.get(i).getCity() + "  " + activities.get(i).getLocation());
                holder.activity_time.setText(activities.get(i).getTime_start() + " -- " + activities.get(i).getTime_end());
                holder.activity_owner.setText(activities.get(i).getOwner());
                holder.activity_theme.setText(activities.get(i).getType());
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onItemClickListener) {
                            onItemClickListener.OnItemClick(v, i);
                        }
                    }
                });
                break;
        }
    }

    public void updateSourceData(ArrayList<ActivityEntity> list) {
        activities.clear();
        activities.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return activities.size() > 0 ? activities.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (activities.size() <= 0) { //无数据情况处理
            return NO_DATA;
        }
//        if(position % 2 == 0){
//            return OFFICIAL;
//        }
        return UNOFFICIAL;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 图片item的holder
     */
    class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    class UnOfficialHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activity_pic_small)
        ImageView activity_pic;
        @BindView(R.id.activity_title)
        TextView activity_title;
        @BindView(R.id.activity_time)
        TextView activity_time;
        @BindView(R.id.activity_owner)
        TextView activity_owner;
        @BindView(R.id.activity_city)
        TextView activity_city;
        @BindView(R.id.activity_theme)
        TextView activity_theme;
        @BindView(R.id.card_container)
        CardView cardView;

        public UnOfficialHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 适配器的点击事件接口
     */
    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }
}
