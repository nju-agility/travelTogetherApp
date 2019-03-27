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
import com.example.chand.traveltogether.model.ArticalEntity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ArticalEntity> articals;
    private LayoutInflater mInflater;
    private ArticalAdapter.OnItemClickListener onItemClickListener;
    private final int NO_DATA = 0, OFFICIAL = 1, UNOFFICIAL = 2;
    private WeakReference<Context> mcontext;

    public ArticalAdapter(List<ArticalEntity> list, Context context) {
        articals = list;
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
                mHolder = new ArticalAdapter.ImageViewHolder(mInflater.inflate(R.layout.cardview_nodata_artical, viewGroup, false));
                break;
            case OFFICIAL:
                mHolder = new ArticalAdapter.ImageViewHolder(mInflater.inflate(R.layout.cardview_official, viewGroup, false));
                break;
            case UNOFFICIAL:
                mHolder = new ArticalAdapter.UnOfficialHolder(mInflater.inflate(R.layout.cardview_unofficial_artical, viewGroup, false));
                break;
            default:
                mHolder = new ArticalAdapter.ImageViewHolder(mInflater.inflate(R.layout.cardview_nodata_artical, viewGroup, false));
                break;
        }
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (articals.size() <= 0) { //无数据的情况
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
                ArticalAdapter.UnOfficialHolder holder = (ArticalAdapter.UnOfficialHolder) viewHolder;
                String url = articals.get(i).getImgPath();
                boolean exits = url != null;
                if (!exits||url.equals("/image/Dont't find image!") || url.equals("")) {
                    Glide.with(mcontext.get()).load(R.drawable.testactivitypic).into(holder.artical_pic);
                } else {
                    Glide.with(mcontext.get()).load(mcontext.get().getString(R.string.base_url) + url).into(holder.artical_pic);
                }
                holder.artical_title.setText(articals.get(i).getTitle());
                holder.artical_city.setText(articals.get(i).getCity() + "  " + articals.get(i).getLocation());
                holder.artical_time.setText(articals.get(i).getSubmission_date());
                holder.artical_owner.setText(articals.get(i).getAccount());
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

    public void updateSourceData(ArrayList<ArticalEntity> list) {
        articals.clear();
        articals.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articals.size() > 0 ? articals.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (articals.size() <= 0) { //无数据情况处理
            return NO_DATA;
        }
//        if(position % 2 == 0){
//            return OFFICIAL;
//        }
        return UNOFFICIAL;
    }


    public void setOnItemClickListener(ArticalAdapter.OnItemClickListener onItemClickListener) {
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
        @BindView(R.id.artical_pic_small)
        ImageView artical_pic;
        @BindView(R.id.artical_title)
        TextView artical_title;
        @BindView(R.id.artical_time)
        TextView artical_time;
        @BindView(R.id.artical_owner)
        TextView artical_owner;
        @BindView(R.id.artical_city)
        TextView artical_city;
        @BindView(R.id.artical_card_container)
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
