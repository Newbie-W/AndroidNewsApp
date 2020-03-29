package com.knewbie.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.knewbie.news.R;
import com.knewbie.news.widget.MyJzvdStd;
import com.knewbie.news.entity.VideoBean;

import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoViewHolder> {
    private List<VideoBean.BodyListBean> item;
    private final LayoutInflater layoutInflater;
    private Context context;
    //private OnItemClickListener onItemClickListener;
    //private OnItemLongClickListener onItemLongClickListener;

    public VideoRecyclerViewAdapter(Context context, List<VideoBean.BodyListBean> item) {
        this.item = item;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        // 定义组件
        private MyJzvdStd video;
        private TextView title, author, time;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            // 初始化
            video = itemView.findViewById(R.id.jz_video);
            title = itemView.findViewById(R.id.textView_video_title);
            author = itemView.findViewById(R.id.textView_video_author);
            time = itemView.findViewById(R.id.textView_video_time);
        }
    }

    @NonNull
    @Override
    public VideoRecyclerViewAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(layoutInflater.inflate(R.layout.recyclerview_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerViewAdapter.VideoViewHolder holder, int position) {
        VideoBean.BodyListBean videoBean = item.get(position);
        holder.title.setText(videoBean.getMemberItem().getName());
        holder.time.setText(videoBean.getCreateDate());
        holder.author.setText(videoBean.getWeMedia().getName());
        List<VideoBean.BodyListBean.MemberItemBean.VideoFilesBean> videoFilesBeanList = videoBean.getMemberItem().getVideoFiles();
        if(videoFilesBeanList!= null)
            holder.video.setUp(videoFilesBeanList.get(0).getMediaUrl(), videoBean.getTitle());    // , JzvdStd.SCREEN_NORMAL
        Glide.with(context).load(videoBean.getMemberItem().getImage()).into(holder.video.posterImageView);   //tinyBackImageView
    }

    @Override
    public int getItemCount() {
        return item==null?0:item.size();
    }

    /*public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }*/
}
