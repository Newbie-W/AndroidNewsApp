package com.knewbie.news.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.NewsBean;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import static android.content.Context.MODE_MULTI_PROCESS;

public class NewsForReadRecyclerViewAdapter extends RecyclerView.Adapter<NewsForReadRecyclerViewAdapter.NewsForReadItemViewHolder> {
    //private List<NewsForReadItem> mItems;
    private List<NewsBean.ResultBean.DataBean> mItems;
    private final LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener = null;
    //注意类OnItemClickListener，用的不是AdapterView.OnItemClickListener
    private OnItemLongClickListener mOnItemLongClickListener= null;
    private Context context;
    private RequestOptions options;
    private static final int ONE_PIC = 0;
    private static final int TWO_PIC = 1;
    private static final int THREE_PIC = 2;
    private boolean likeFlag = false;
    private String unameSp;
    private String pwdSp;
    private GlobalApplication application;
    private DatabaseOperationDao dbManager;
    private UserBean userBean;
    private String newsId;

    public NewsForReadRecyclerViewAdapter(Activity context, List<NewsBean.ResultBean.DataBean> mItems) {     //NewsForReadItem
        this.mItems = mItems;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        /*if(!ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));*/
    }

    public interface OnItemClickListener {      //回调接口
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public class NewsForReadItemViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewAuthor;
        //TextView textViewIntroduction;
        ImageView imageViewNewsPic;
        ImageView imageViewNewsPicSecond = itemView.findViewById(R.id.imageViewNewsPic2_recyclerview);;
        ImageView imageViewNewsPicThird = itemView.findViewById(R.id.imageViewNewsPic3_recyclerview);;
        TextView textViewReadAmount;
        TextView textViewLikeAmount = itemView.findViewById(R.id.textViewLikeAmount_recyclerview);;
        TextView textViewReviewAmount;
        TextView textViewLastEditTime;
        ImageView likeImg = itemView.findViewById(R.id.imageViewLike_recyclerview);
        public NewsForReadItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //Log.d("hello", "ViewHolder---init");
            switch (getItemViewType()) {
                case ONE_PIC:
                    initOnePicListParam();
                    break;
                case TWO_PIC:
                    initTwoPicListParam();
                    break;
                case THREE_PIC:
                    initThreePicListParam();
                    break;
                default:
                    initOnePicListParam();
                    break;
            }
        }

        private void initThreePicListParam() {
            textViewTitle = itemView.findViewById(R.id.textViewTitle_recyclerview);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor_recyclerview);
            //textViewIntroduction = itemView.findViewById(R.id.textViewIntroduction_recyclerview);
            imageViewNewsPic = itemView.findViewById(R.id.imageViewNewsPic1_recyclerview);
            imageViewNewsPicSecond = itemView.findViewById(R.id.imageViewNewsPic2_recyclerview);
            imageViewNewsPicThird = itemView.findViewById(R.id.imageViewNewsPic3_recyclerview);
            textViewReadAmount = itemView.findViewById(R.id.textViewReadAmount_recyclerview);
            //textViewLikeAmount = itemView.findViewById(R.id.textViewLikeAmount_recyclerview);
            textViewReviewAmount = itemView.findViewById(R.id.textViewReviewAmount_recyclerview);
            textViewLastEditTime = itemView.findViewById(R.id.textViewLastEditTime_recyclerview);
        }

        private void initTwoPicListParam() {
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewTitle_recyclerview);
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor_recyclerview);
            //textViewIntroduction = (TextView) itemView.findViewById(R.id.textViewIntroduction_recyclerview);
            imageViewNewsPic = (ImageView) itemView.findViewById(R.id.imageViewNewsPic1_recyclerview);
            imageViewNewsPicSecond = itemView.findViewById(R.id.imageViewNewsPic2_recyclerview);
            textViewReadAmount = (TextView) itemView.findViewById(R.id.textViewReadAmount_recyclerview);
            //textViewLikeAmount = (TextView) itemView.findViewById(R.id.textViewLikeAmount_recyclerview);
            textViewReviewAmount = (TextView) itemView.findViewById(R.id.textViewReviewAmount_recyclerview);
            textViewLastEditTime = (TextView) itemView.findViewById(R.id.textViewLastEditTime_recyclerview);
        }

        private void initOnePicListParam() {
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewTitle_recyclerview);
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor_recyclerview);
            //textViewIntroduction = (TextView) itemView.findViewById(R.id.textViewIntroduction_recyclerview);
            imageViewNewsPic = (ImageView) itemView.findViewById(R.id.imageViewNewsPic1_recyclerview);
            textViewReadAmount = (TextView) itemView.findViewById(R.id.textViewReadAmount_recyclerview);
            //textViewLikeAmount = (TextView) itemView.findViewById(R.id.textViewLikeAmount_recyclerview);
            textViewReviewAmount = (TextView) itemView.findViewById(R.id.textViewReviewAmount_recyclerview);
            textViewLastEditTime = (TextView) itemView.findViewById(R.id.textViewLastEditTime_recyclerview);
        }
    }

    @NonNull
    @Override
    public NewsForReadItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsForReadItemViewHolder h;
        if (viewType == ONE_PIC) {
            //if (h == null) {
                h = new NewsForReadItemViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_news_for_read_item, parent, false));
            //}
        } else if (viewType == TWO_PIC) {
            h = new NewsForReadItemViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_news_for_read_item_two_pics, parent, false));
        } else if (viewType == THREE_PIC) {
            h = new NewsForReadItemViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_news_for_read_item_three_pics, parent, false));
        } else 
            h = new NewsForReadItemViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_news_for_read_item, parent, false));
        return h;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position).getThumbnail_pic_s() != null &&
                mItems.get(position).getThumbnail_pic_s02() != null &&
                mItems.get(position).getThumbnail_pic_s03() != null) {
            return THREE_PIC;
        } else if (mItems.get(position).getThumbnail_pic_s() != null &&
                mItems.get(position).getThumbnail_pic_s02() != null) {
            return TWO_PIC;
        }
        return ONE_PIC;
    }/**/

    public static DisplayImageOptions getOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.logo_news_fill_2)    // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)         // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)              // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(true)                       // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(100)                            // 下载前的延迟时间
                .cacheInMemory(true)                                // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)                                  // default  设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)   // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                // default 设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(20))  // 是否设置为圆角，弧度为多少
                .build();
        return options;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsForReadItemViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);
        unameSp = sharedPreferences.getString("username", null);
        pwdSp = sharedPreferences.getString("user_password", null);
        application = (GlobalApplication) context.getApplicationContext();
        dbManager = application.getDatabaseOperationDao();
        userBean = dbManager.findUser(unameSp, pwdSp);
        newsId = mItems.get(position).getUniquekey();
        //dbManager.getNewsThumbUpItemList();
        int id = dbManager.findNewsThumbUp(userBean.getId(), newsId);
        if (id != -1) {
            if (R.drawable.ic_home_like_fill > 0) {
                holder.likeImg.setImageResource(R.drawable.ic_home_like_fill);
            }
            //setLikeFlag(true);
        } else {
            if (R.drawable.ic_home_like_down > 0) {
                holder.likeImg.setImageResource(R.drawable.ic_home_like_down);
            }
        }
        //Log.d("hello", "ViewHolder---bind(load)");
        if (getItemViewType(position) == ONE_PIC) {
            loadOnePicListItem(holder, position);
        } else if (getItemViewType(position) == TWO_PIC) {
            loadTwoPicListItem(holder, position);
        } else if (getItemViewType(position) == THREE_PIC)
            loadThreePicListItem(holder, position);
        else loadOnePicListItem(holder, position);



        holder.textViewLikeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("hello", "likeClick");
                clickLike(holder, v, position);
            }
        });
        holder.likeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("hello", "likeClick");
                clickLike(holder, v, position);
            }
        });
        /*NewsForReadItem item = mItems.get(position);
        holder.textViewTitle.setText(item.getTitle());
        //Log.d("helloFragment", ""+item.getTitle());
        holder.textViewAuthor.setText(item.getAuthor());
        holder.textViewIntroduction.setText(item.getIntroduction());
        holder.textViewLikeAmount.setText(String.valueOf(item.getLikeAmount()));
        holder.textViewReadAmount.setText(String.valueOf(item.getReadAmount()));
        holder.textViewReviewAmount.setText(String.valueOf(item.getReviewAmount()));
        holder.textViewLastEditTime.setText(item.getLastEditTime());
        int icon = R.drawable.logo_news_fill_2;
        if (icon > 0)   //有此图片，就设置出来
            holder.imageViewNewsPic.setImageResource(icon);
        */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemLongClickListener.onItemLongClick(v, position);
                return true;
            }
        });
    }

    private void clickLike(NewsForReadItemViewHolder holder, View v, int position) {
        if (userBean==null || newsId==null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);
            String unameSp = sharedPreferences.getString("username", null);
            String pwdSp = sharedPreferences.getString("user_password", null);
            GlobalApplication application = (GlobalApplication) context.getApplicationContext();
            DatabaseOperationDao dbManager = application.getDatabaseOperationDao();
            UserBean userBean;
        }
        newsId = mItems.get(position).getUniquekey();
        int likeCount;
        likeCount = dbManager.getNewsDataBean(newsId).getLikeAmount();
        //Log.d("hello", "likeCount--------"+likeCount);
        if (unameSp != null && pwdSp != null) {
            userBean = dbManager.findUser(unameSp, pwdSp);
            int id = dbManager.findNewsThumbUp(userBean.getId(), newsId);
            holder.textViewLikeAmount.setClickable(false);
            holder.likeImg.setClickable(false);
            if (id != -1) {
                //Log.d("hello", "----------before unlike--------");
                //dbManager.getNewsThumbUpItemList();
                dbManager.deleteNewsThumbUpDisplayItem(id);
                //setLikeFlag(false);
                likeCount -= 1;
                dbManager.updateTable("news_table", "like_amount =  "+likeCount, "news_id = '"+newsId+"'");
                //Log.d("hello", "----------unlike--------");
                //dbManager.getNewsThumbUpItemList();
                Handler handler = new Handler();
                int finalLikeCount = likeCount;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Log.d("hello", "likeCount--------取消点赞"+dbManager.getNewsDataBean(newsId).getLikeAmount());
                        if (R.drawable.ic_home_like_down > 0) {
                            holder.likeImg.setImageResource(R.drawable.ic_home_like_down);
                        }
                        holder.textViewLikeAmount.setText(String.valueOf(finalLikeCount));
                        //Log.d("hello", "likeCount--------取消点赞"+(finalLikeCount)+"-----"+holder.textViewLikeAmount.getText());

                    }
                });

            } else {
                //Log.d("hello", "----------before like--------");
                //dbManager.getNewsThumbUpItemList();
                dbManager.addNewsThumbUpDisplayItem(userBean.getId(), newsId);
                //setLikeFlag(true);
                likeCount += 1;

                dbManager.updateTable("news_table", "like_amount =  "+likeCount, "news_id = '"+newsId+"'");
                //Log.d("hello", "----------like--------");
                //dbManager.getNewsThumbUpItemList();
                Handler handler = new Handler();
                int finalLikeCount1 = likeCount;
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (R.drawable.ic_home_like_fill > 0) {
                            holder.likeImg.setImageResource(R.drawable.ic_home_like_fill);
                        }
                        holder.textViewLikeAmount.setText(String.valueOf(finalLikeCount1));
                    }
                });
                //Log.d("hello", "likeCount-------点赞"+(likeCount));
                //Log.d("hello", "likeCount-------点赞"+dbManager.getNewsDataBean(newsId).getLikeAmount());
            }
            holder.textViewLikeAmount.setClickable(true);
            holder.likeImg.setClickable(true);
        } else {
            Toast.makeText(context, "点赞失败", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadThreePicListItem(final NewsForReadItemViewHolder holder, final int position) {
        NewsBean.ResultBean.DataBean item = mItems.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewAuthor.setText(item.getAuthor_name());
        //holder.textViewIntroduction.setText(item.get);
        holder.textViewLikeAmount.setText(String.valueOf(item.getLikeAmount()));
        holder.textViewReadAmount.setText(String.valueOf(item.getReadAmount()));
        holder.textViewReviewAmount.setText(String.valueOf(item.getReviewAmount()));
        holder.textViewLastEditTime.setText(item.getDate());
        if (options == null) {
            options = new RequestOptions()
                    .placeholder(R.drawable.logo_news_fill_2)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.logo_news_fill_2)
                    .bitmapTransform(new RoundedCorners(24))
                    .dontAnimate();
        }
        Glide.with(context)
                .load(item.getThumbnail_pic_s())
                .apply(options)
                .into(holder.imageViewNewsPic);
                //.
        Glide.with(context)
                .load(item.getThumbnail_pic_s02())
                .apply(options)
                .into(holder.imageViewNewsPicSecond);
        Glide.with(context)
                .load(item.getThumbnail_pic_s03())
                .apply(options)
                .into(holder.imageViewNewsPicThird);
        /*ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(), holder.imageViewNewsPic, getOption());
        ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s02(), holder.imageViewNewsPicSecond, getOption());
        ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s03(), holder.imageViewNewsPicThird, getOption());*/
    }

    private void loadTwoPicListItem(final NewsForReadItemViewHolder holder, final int position) {
        if (options == null) {
            options = new RequestOptions()
                    .placeholder(R.drawable.logo_news_fill_2)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.logo_news_fill_2)
                    .dontAnimate();
        }
        NewsBean.ResultBean.DataBean item = mItems.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewAuthor.setText(item.getAuthor_name());
        //holder.textViewIntroduction.setText(item.get);
        holder.textViewLikeAmount.setText(String.valueOf(item.getLikeAmount()));
        holder.textViewReadAmount.setText(String.valueOf(item.getReadAmount()));
        holder.textViewReviewAmount.setText(String.valueOf(item.getReviewAmount()));
        holder.textViewLastEditTime.setText(item.getDate());
        Glide.with(context)
                .load(item.getThumbnail_pic_s())
                .apply(options)
                .into(holder.imageViewNewsPic);
        Glide.with(context)
                .load(item.getThumbnail_pic_s02())
                .apply(options)
                .into(holder.imageViewNewsPicSecond);
        //ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(), holder.imageViewNewsPic, getOption());
    }

    private void loadOnePicListItem(final NewsForReadItemViewHolder holder, final int position) {
        if (options == null) {
            options = new RequestOptions()
                    .placeholder(R.drawable.logo_news_fill_2)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.logo_news_fill_2)
                    .dontAnimate();
        }
        NewsBean.ResultBean.DataBean item = mItems.get(position);
        holder.textViewTitle.setText(item.getTitle());
        //Log.d("helloFragment", ""+item.getTitle());
        holder.textViewAuthor.setText(item.getAuthor_name());
        //holder.textViewIntroduction.setText(item.get);
        holder.textViewLikeAmount.setText(String.valueOf(item.getLikeAmount()));
        holder.textViewReadAmount.setText(String.valueOf(item.getReadAmount()));
        holder.textViewReviewAmount.setText(String.valueOf(item.getReviewAmount()));
        holder.textViewLastEditTime.setText(item.getDate());
        Glide.with(context)
                .load(item.getThumbnail_pic_s())
                .apply(options)
                .into(holder.imageViewNewsPic);
        //ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(), holder.imageViewNewsPic, getOption());
        
    }

    @Override
    public int getItemCount() {
        return mItems==null?0:mItems.size();
    }

    public boolean isLikeFlag() {
        return likeFlag;
    }

    public void setLikeFlag(boolean likeFlag) {
        this.likeFlag = likeFlag;
    }
}
