package com.knewbie.news.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.knewbie.news.R;
import com.knewbie.news.adapter.VideoRecyclerViewAdapter;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.VideoBean;
import com.knewbie.news.global.GlobalApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.jzvd.Jzvd;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private VideoRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;
    //private SearchView searchView;
    public List<VideoBean.BodyListBean> videoBeanList;
    private final int GET_NEWS_FROM_INTERNET = 0;
    private Handler newsMessageHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //String uniquekey,title,date, category,author_name,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;

            //super.handleMessage(msg);
            switch (msg.what) {
                case GET_NEWS_FROM_INTERNET:
                    //videoBeanList = (List<VideoBean.DataBean>)msg.obj;
                    videoBeanList = ((VideoBean)msg.obj).getBodyList();
					/*mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));    //设置为垂直布局
					adapter = new NewsForReadRecyclerViewAdapter(getActivity(), newsBeanList);
					mRecyclerView.setAdapter(adapter);*/
                    refresh();
                    adapter.notifyDataSetChanged();
                    break;
                /*case GET_NEWS_FROM_DB:
                    videoBeanList = (List<VideoBean>)msg.obj;
                    refresh();
                    break;*/
            }
        }
    };

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.recyclerView_video);
        getDataFromInternet();
        refresh();
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                Jzvd jzvd = view.findViewById(R.id.jz_video);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl()))
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN)
                        Jzvd.releaseAllVideos();
            }
        });
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout_video);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        //Log.d("hello", "swipeRefresh-----1");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                getDataFromInternet();
                            }
                        }).start();
                    }
                }, 1000);
            }
        });
        fab = rootView.findViewById(R.id.fab_up_video);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    public void refresh() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));    //设置为垂直布局
        //NewsForReadItemAdapter newsForReadItemAdapter = new NewsForReadItemAdapter(newsForReadItemList, getActivity());
        adapter = new VideoRecyclerViewAdapter(this.getActivity(), videoBeanList);
        recyclerView.setAdapter(adapter);
        /*adapter.setOnItemClickListener(new VideoRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getActivity(), "点击了 当前位置"+position+",  对应id"+newsForReadItemList.get(position).getId(), Toast.LENGTH_SHORT).show();
                final VideoBean.DataBean videoBean = videoBeanList.get(position);
                //点击了一条新闻，加到数据库（news_table）中，后期会改为history_table中
                //Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                //intent.putExtra("dataBean", dataBean);
                //Log.d("hello, url = ", url+" 。");
                //startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new VideoRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(getActivity(), "长按——当前位置"+position+",  对应id"+videoBeanList.get(position).getVid(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void getDataFromInternet() {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String path = "https://vcis.ifeng.com/api/homePageList?channelId=20&pageSize=20&protocol=1.1.0&from=share&isShowAd=0&positionId=0";
                // https://vcis.ifeng.com/api/homePageList?channelId=20&pageSize=20&protocol=1.1.0&from=share&isShowAd=0&positionId=0
                // https://3g.163.com/touch/nc/api/video/recommend/Video_Recom/0-10.do?callback=videoList
                // https://pacaio.match.qq.com/xw/rcdVideo?num=20&refresh=0&_t=1585413547
                // https://cre.dp.sina.cn/api/v3/get?cateid=uM&cre=tianyi&mod=wvideo&merge=3&statics=1&length=20&tm=1489716199&ch=video&action=0&up=0&down=0&tm=1585360307&jsoncallback=listData&callback=listData&_=1585360307722&callback=listData
                URL url;
                try {
                    url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    //Log.d("hello", "getNewsDataFromInternet------connection.responseCode"+responseCode);
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        //Log.d("hello", "getNewsDataFromInternet------connection.content"+connection.getContent());
                        String json = streamToString(inputStream, "utf-8");
                        //Log.d("hello","getNewsDataFromInternet------HomeFragment："+json);
                        return json;
                    } else {
                        Log.d("hello", "getNewsDataFromInternet------else,非200 "+responseCode);
                        return "访问出错";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("hello", "getNewsDataFromInternet------AsyncNullException"+e.getMessage());
                }
                //Log.d("hello", "getNewsDataFromInternet-----AsyncNull----");
                return "";
            }

            @Override
            protected void onPostExecute(final String s) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //int firstCutLen = "listData(".length();
                        //int lastCutLen = ")".length();   //1
                        //Log.d("hello", "getNewsDataFromInternet------onPostExecute,str="+str+"。"+firstCutLen);
                        //String s = str.substring(firstCutLen, str.length());
                        //String s =
                        //Log.d("hello", "getNewsDataFromInternet------onPostExecute,s="+s+"。"+firstCutLen);

                        GlobalApplication globalApplication = (GlobalApplication) getActivity().getApplication();
                        DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                        VideoBean videoBean;
                        //dbManager.getVideoAllList();
                        videoBean = new Gson().fromJson(s, VideoBean.class);
                        videoBeanList = videoBean.getBodyList();
                        //Log.d("hello", "getNewsDataFromInternet------onPostExecute,newsBean="+newsBean);
                        //Log.d("hello", "getNewsDataFromInternet------onPostExecute,ErrorCode"+videoBean.getError_code());
                        if (videoBeanList != null && videoBeanList.size()>0) {
                            /*Log.d("hello", "VideoListSize-----id "+videoBeanList.get(0).getInfoId()+", title"+videoBeanList.get(0).getMemberItem().getName()+", url"+videoBeanList.get(0).getMemberItem().getVideoFiles());
                            Log.d("hello", "VideoListBean-----bodyList"+videoBean.getBodyList());
                            Log.d("hello", "VideoListBean-----bodyList.get0.title"+videoBean.getBodyList().get(0).getTitle());
                            Log.d("hello", "VideoListBean-----bodyList.get0.MemberItem().getVideoFiles"+videoBean.getBodyList().get(0).getMemberItem().getVideoFiles());
                            Log.d("hello", "VideoListBean-----bodyList.get0.MemberItem().getVideoFiles.size"+videoBean.getBodyList().get(0).getMemberItem().getVideoFiles().size());
                            Log.d("hello", "VideoListBean-----bodyList.get0.MemberItem().getVideoFiles.get(0)"+videoBean.getBodyList().get(0).getMemberItem().getVideoFiles().get(0));
                            */
                            for (int i=0; i<videoBeanList.size(); i++) {
                                VideoBean.BodyListBean video = videoBeanList.get(i);
                                if (video.getInfoId()!= null && video.getMemberItem().getVideoFiles()!=null && video.getMemberItem().getVideoFiles().size()!=0 && !dbManager.findVideo(""+video.getInfoId())) {   //不知为何，会有VideoFile为null的情况？加上条件，则不报错
                                    //此条不能insert ，video----id:survey8----null----0----null----null，且video.getMemberItem().getVideoFiles()是[]（size=0）
                                    //Log.d("hello", "addVideo-----"+video.getInfoId()+","+(video.getMemberItem().getVideoFiles()));
                                    dbManager.addVideoBean(video);
                                    //Log.d("hello", "addVideo-----"+video.getInfoId());
                                }
                                //Log.d("hello", ""+dataBean.getUniquekey());
                            }
                            videoBean.setBodyList(videoBeanList);
                            //NewsBean.ResultBean.DataBean dataBean = newsBean.getResult().getData().get(i);
                        } else {
                            videoBeanList = dbManager.getVideoBeanList();
                            videoBean.setBodyList(videoBeanList);
                        }
                        /**/
                        Message message = newsMessageHandler.obtainMessage();
                        message.what = GET_NEWS_FROM_INTERNET;
                        message.obj = videoBean;
                        newsMessageHandler.sendMessage(message);
                    }
                }).start();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        };
        task.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    private String streamToString(InputStream inputStream, String charSet) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSet);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            //Log.d("hello", "steamToString,1 inputStream"+bufferedReader.read()+", readline"+"。");
            StringBuilder stringBuilder = new StringBuilder();
            //Log.d("hello", "steamToString,1 inputStream"+inputStream+", readline"+"。");
            while ((s=bufferedReader.readLine()) != null) {
                //Log.d("hello", "steamToString,2 "+s.length()+"s=readline= "+s);
                stringBuilder.append(s);
                //Log.d("hello", "steamToString,3 stringBuilder= "+stringBuilder.toString());
            }
            //Log.d("hello", "steamToString,4 final readline="+s);

            bufferedReader.close();
            //Log.d("hello", "steamToString,5 string"+stringBuilder.toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("hello", "steamToString,6--Exception"+e.getMessage());
        }
        return null;
    }

}
