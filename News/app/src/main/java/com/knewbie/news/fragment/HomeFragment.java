package com.knewbie.news.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.knewbie.news.R;
import com.knewbie.news.activity.NewsDetailActivity;
import com.knewbie.news.adapter.NewsForReadRecyclerViewAdapter;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.NewsBean;
import com.knewbie.news.entity.NewsForReadItem;
import com.knewbie.news.global.GlobalApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    /*
    http://zhouxunwang.cn/data/?id=75&key=U7zB+dRhHNT+ipiN9ooxRGbHOATgsJeZ/px16A&type=top
    U7zB+dRhHNT+ipiN9ooxRGbHOATgsJeZ/px16A
    */
    private View mRootView;
    private RecyclerView mRecyclerView;
    private NewsForReadRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;
    //private SearchView searchView;
    private List<NewsBean.ResultBean.DataBean> newsBeanList;
    private final int GET_NEWS_FROM_INTERNET = 0;
    private final int GET_NEWS_FROM_DB = 1;
    private int page = 0;
    private int len = 10;
    private Handler newsMessageHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //String uniquekey,title,date, category,author_name,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;

            //super.handleMessage(msg);
            switch (msg.what) {
                case GET_NEWS_FROM_INTERNET:
                    newsBeanList = ((NewsBean)msg.obj).getResult().getData();
					/*mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));    //设置为垂直布局
					adapter = new NewsForReadRecyclerViewAdapter(getActivity(), newsBeanList);
					mRecyclerView.setAdapter(adapter);*/
                    refresh();
                    adapter.notifyDataSetChanged();
                    break;
                case GET_NEWS_FROM_DB:
                    newsBeanList = ((NewsBean)msg.obj).getResult().getData();
                    refresh();
                    break;
            }
        }
    };


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.d("hello", "onCreateView");       //先于onActivityCreated运行
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        //initView();
        return mRootView;
    }

	@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //onAttach(getContext());
        initView();
        //Log.d("hello", "onActivityCreated");
    }
	
    private void initView() {
        /*searchView = mRootView.findViewById(R.id.home_searchView);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        swipeRefreshLayout = mRootView.findViewById(R.id.swipeRefreshLayout_NewsForRead);
        //swipeRefreshLayout.setOnScrollChangeListener(new RecyclerView.OnScrollListener());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        //Log.d("hello", "swipeRefresh-----1");
                        // 实现从数据库中读取数据刷新到listview适配器中
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int start = (page-1) * len;
                                NewsBean newsBean = new NewsBean();
                                //Log.d("hello", "swipeRefresh-----2");
                                GlobalApplication globalApplication = (GlobalApplication) getActivity().getApplication();
                                DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                                List<NewsBean.ResultBean.DataBean> dataBeanList = dbManager.getNewsDataBeanList(start, len);
                                newsBean.setResult(new NewsBean.ResultBean());
                                newsBean.getResult().setData(dataBeanList);
                                //Log.d("hello", "swipeRefresh-----3getDBList");
                                Message message = newsMessageHandler.obtainMessage();
                                message.what = GET_NEWS_FROM_INTERNET;
                                message.obj = newsBean;
                                newsMessageHandler.sendMessage(message);
                                //Log.d("hello", "swipeRefresh-----4sendMessage");
                            }
                        }).start();
                    }
                }, 1000);
            }
        });/**/
        fab = mRootView.findViewById(R.id.homeUpFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
                //refresh();
            }
        });
        getNewsDataFromInternet();
        //Log.d("hellFragment", "RunIn_initView");
        refresh();
    }

    public void refresh() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerViewHome);
        //Log.d("helloworld", "FragmentOnActivityResult");
        /*
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerViewHome);
        //final List<NsewsForReadItem> newsForReadItemList = dbManager.getNewsForReadItemList();
        final List<NewsForReadItem> newsForReadItemList = dbManager.getNewsForReadItemList();*/
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));    //设置为垂直布局
        //NewsForReadItemAdapter newsForReadItemAdapter = new NewsForReadItemAdapter(newsForReadItemList, getActivity());
        adapter = new NewsForReadRecyclerViewAdapter(this.getActivity(), newsBeanList);
        mRecyclerView.setAdapter(adapter);
        /*mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ItemClick", Toast.LENGTH_LONG).show();
            }
        });*/
        adapter.setOnItemClickListener(new NewsForReadRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getActivity(), "点击了 当前位置"+position+",  对应id"+newsForReadItemList.get(position).getId(), Toast.LENGTH_SHORT).show();
                String url = newsBeanList.get(position).getUrl();
                final String uniquekey = newsBeanList.get(position).getUniquekey();
                final NewsBean.ResultBean.DataBean dataBean = newsBeanList.get(position);
                //点击了一条新闻，加到数据库（news_table）中，后期会改为history_table中
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GlobalApplication app = (GlobalApplication) getActivity().getApplication();
                        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
                        if (!dbManager.findNews(uniquekey))
                            dbManager.addNewsDataBean(dataBean);
                    }
                }).start();
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("newsId", newsBeanList.get(position).getUniquekey());
                //final UserBean userBean = getActivity().;
                //intent.putExtra("uid", ((MainActivity)getActivity()).getUser().getId());
                Log.d("hello, url = ", url+" 。");
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new NewsForReadRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(getActivity(), "长按——当前位置"+position+",  对应id"+newsBeanList.get(position).getUniquekey(), Toast.LENGTH_SHORT).show();
                //deleteListItem(newsForReadItemList.get(position).getId());
            }
        });
    }

    //异步获取新闻数据
    private void getNewsDataFromInternet() {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String path = "http://v.juhe.cn/toutiao/index?type=top&key=547ee75ef186fc55a8f015e38dcfdb9a";
                //http://zhouxunwang.cn/data/?id=75&key=U7zB+dRhHNT+ipiN9ooxRGbHOATgsJeZ/px16A&type=top
                // http://v.juhe.cn/toutiao/index?type=top&key=547ee75ef186fc55a8f015e38dcfdb9a
                URL url;
                //String testJson = "{\"reason\":\"成功的返回\",\"result\":{\"stat\":\"1\",\"data\":[{\"uniquekey\":\"38cdded73c6411d4a85eb9db9f3525a8\",\"title\":\"开心一刻: 亲戚吃饭催婚时, 俺还在一副独立女性的样子…\",\"date\":\"2020-03-09 11:46\",\"category\":\"头条\",\"author_name\":\"平日笑谈\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309114606509.html\",\"thumbnail_pic_s\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309114606_b7cdb93ef6044a8ce41b4079f389142d_1_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309114606_b7cdb93ef6044a8ce41b4079f389142d_4_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309114606_b7cdb93ef6044a8ce41b4079f389142d_3_mwpm_03200403.jpg\"},{\"uniquekey\":\"a8d8ab4d6dbb955219bb5a24945b282c\",\"title\":\"火箭再遭完虐4连败跌至西部第六，休城众将消极怠战抗议德帅？\",\"date\":\"2020-03-09 11:39\",\"category\":\"头条\",\"author_name\":\"花歌Andy篮球\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309113909851.html\",\"thumbnail_pic_s\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/20200309113909_8554f9ce738b4f9eff5d0b44532bd797_4_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/20200309113909_8554f9ce738b4f9eff5d0b44532bd797_3_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/20200309113909_8554f9ce738b4f9eff5d0b44532bd797_2_mwpm_03200403.jpg\"},{\"uniquekey\":\"c1c208335f6294312d0ef3bf87e749d3\",\"title\":\"即日起，延吉市将开放餐饮单位堂食、洗浴中心等服务性经营场所\",\"date\":\"2020-03-09 11:26\",\"category\":\"头条\",\"author_name\":\"医药行业知识\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309112657553.html\",\"thumbnail_pic_s\":\"http:\\/\\/01imgmini.eastday.com\\/mobile\\/20200309\\/20200309112657_a859cb1d8f6acb07f25ff56ffaaff01a_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"9cbc3eeb6ac631e6ef42d80b36c0e336\",\"title\":\"历史重演？美国华盛顿州一疗养院成新冠感染集中地\",\"date\":\"2020-03-09 11:24\",\"category\":\"头条\",\"author_name\":\"纵相新闻\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309112443643.html\",\"thumbnail_pic_s\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_a4122bc8906848ef885419852da4c232_0871_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_f24112356ba8445f92fb87cd1e8c9f77_3345_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_a1b3a6ccafab4953bfc63a3c0b3fc8b4_1788_mwpm_03200403.jpg\"},{\"uniquekey\":\"c6d1fecbb427995e483ab92fd15e7641\",\"title\":\"土耳其还是要打？大批王牌坦克抵达停火区，俄：背叛后果很严重\",\"date\":\"2020-03-09 11:23\",\"category\":\"头条\",\"author_name\":\"东方头条 武器装备\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309112349701.html\",\"thumbnail_pic_s\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_dd19859756a34b3d90d8280ff19664ef_3233_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_9f58e774fdc349b2bce93a7584e2608f_1255_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_57d32ca7f45c4474a7d3fc99d619aef5_0225_cover_mwpm_03200403.jpg\"},{\"uniquekey\":\"897760c459e4572885e7addab3ac59b6\",\"title\":\"22岁关晓彤和28岁郑爽撞衫，同穿prada刺绣裙差距一目了然\",\"date\":\"2020-03-09 11:23\",\"category\":\"头条\",\"author_name\":\"魅力大烟台\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309112321243.html\",\"thumbnail_pic_s\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309112321_12a45eaf275ef24e285ab28e25fe78c5_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309112321_12a45eaf275ef24e285ab28e25fe78c5_1_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309112321_12a45eaf275ef24e285ab28e25fe78c5_4_mwpm_03200403.jpg\"},{\"uniquekey\":\"d75ee467fb6fab5796d391ccd72c7118\",\"title\":\"大批土耳其士兵遗体归国，数万民众沿途扶棺痛哭：还我们儿子！\",\"date\":\"2020-03-09 11:17\",\"category\":\"头条\",\"author_name\":\"东方头条 武器装备\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309111743607.html\",\"thumbnail_pic_s\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_71425ce860e344c4b105ec6eb1423dcd_8662_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_911d8db3a24a406dacdba8b80e72dc22_2729_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_6504ccd857cd4d4184d1c97cc4bb5911_7572_cover_mwpm_03200403.jpg\"},{\"uniquekey\":\"942d2a4ba8c717a05fbcb56f04e97872\",\"title\":\"颜值高、大空间、操控灵活，这是女神无法抗拒的一款车！\",\"date\":\"2020-03-09 11:12\",\"category\":\"头条\",\"author_name\":\"My车轱辘\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309111259233.html\",\"thumbnail_pic_s\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_b4cc44754fff459e8d580edd33138619_6822_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_50f4969fa1044ac5b548357370fe0bc0_4081_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_f8fe1995d3284d91a8d2337e49c0f36d_9178_mwpm_03200403.jpg\"},{\"uniquekey\":\"4c81501b6b84ca5fae470f9b2068939e\",\"title\":\"男子9次驾车往返湖北接送人员 警方：行拘10日\",\"date\":\"2020-03-09 11:12\",\"category\":\"头条\",\"author_name\":\"中国新闻网\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309111232859.html\",\"thumbnail_pic_s\":\"http:\\/\\/03imgmini.eastday.com\\/mobile\\/20200309\\/20200309111232_06e86bc6650783bfc1d1ae2aa4486c2b_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"305fbb76d3771d44a848241c6c55490a\",\"title\":\"省建行刘军行长一行到翠亨新区考察\",\"date\":\"2020-03-09 11:12\",\"category\":\"头条\",\"author_name\":\"乐居网\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309111216761.html\",\"thumbnail_pic_s\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309111216_9bfd38e15d636ed58abd897cc749c7ad_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309111216_9bfd38e15d636ed58abd897cc749c7ad_3_mwpm_03200403.jpg\"},{\"uniquekey\":\"f13b682ef61fc46b48afdbd607e0827a\",\"title\":\"土耳其不甘失败，送大批军火支援俄头号敌人，逆转俄家门口战局\",\"date\":\"2020-03-09 11:11\",\"category\":\"头条\",\"author_name\":\"东方头条 武器装备\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309111121037.html\",\"thumbnail_pic_s\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_16af3c19515541869cdc776f33f6a367_1452_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_0a2c92cb646b456d8f8e424cfac08cc0_2724_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_b3ffbc612b38460bb26ead04b4dfdf1f_4982_cover_mwpm_03200403.jpg\"},{\"uniquekey\":\"2111c54e0a72e8702382c262df1ac02e\",\"title\":\"癌症为什么会转移？身体释放出4个信号，当心：癌细胞正在扩散\",\"date\":\"2020-03-09 11:09\",\"category\":\"头条\",\"author_name\":\"吃喝更健康\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110950917.html\",\"thumbnail_pic_s\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_b9b8324bcd8b4189b4e82e0c79087b62_7950_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_f4c3ea1513a2411aa07decfb381257ba_4533_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_af8fe89c6b5146df95ba97ca11f0389f_0849_mwpm_03200403.jpg\"},{\"uniquekey\":\"456c3bd3fae71fe9d24a54cfd5deaf0d\",\"title\":\"齐达内：皇马本赛季最差的一场比赛 我为失利负责\",\"date\":\"2020-03-09 11:09\",\"category\":\"头条\",\"author_name\":\"足坛欧美汇\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110925471.html\",\"thumbnail_pic_s\":\"http:\\/\\/01imgmini.eastday.com\\/mobile\\/20200309\\/20200309110925_1c7599107943fc632f8cf597e05c8155_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"b130863cd05008059da1351baa708d86\",\"title\":\"双鱼女常常幻想自己是电视女主角，往往令恋人无法招架！\",\"date\":\"2020-03-09 11:08\",\"category\":\"头条\",\"author_name\":\"古时候\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110845654.html\",\"thumbnail_pic_s\":\"http:\\/\\/02imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_1b5a5f999f324f109440365fcb77b3ba_4941_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/02imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_0c85ce0ea2564e42a37f8584f66690b2_0773_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/02imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_af8cc8fc465440b59b391de73d158a11_7931_mwpm_03200403.jpg\"},{\"uniquekey\":\"d24bcd8421da1d2a9b8047d0af4babee\",\"title\":\"若谭嗣同的这一建议光绪帝同意了，那么，中国几百万领土就消失了\",\"date\":\"2020-03-09 11:08\",\"category\":\"头条\",\"author_name\":\"山川文社\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110812469.html\",\"thumbnail_pic_s\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_6f981ff0c96b4a49967323b937dde870_4944_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_2bb9d7abce0e4185a78249cc34210523_7567_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_08bc9f142c48406da61ea8b3db7153ca_4410_mwpm_03200403.jpg\"},{\"uniquekey\":\"8521e614fb2d342dae182d08e7dac612\",\"title\":\"想要人鱼线？坚持5式瑜伽练习，持之以恒，让小腹更有型\",\"date\":\"2020-03-09 11:07\",\"category\":\"头条\",\"author_name\":\"瑜伽坊\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110711200.html\",\"thumbnail_pic_s\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_d4a10f789ced4e9088b28b50b6884a4e_5035_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_3e877564bd6941b4b5846115906fce57_1058_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_5d95b4236564443bbb9de2c37e56cb8d_5251_cover_mwpm_03200403.jpg\"},{\"uniquekey\":\"3aa9b95a8b6e222d237fa98afcca69cf\",\"title\":\"长安UNI-T全球首发，搭载全新1.5T动力\",\"date\":\"2020-03-09 11:06\",\"category\":\"头条\",\"author_name\":\"My车轱辘\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110635267.html\",\"thumbnail_pic_s\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_d2b9ff3127704831bd746639638a3afc_8078_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_7b8f20b931a84f59b546bb89bc6233a0_1573_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/07imgmini.eastday.com\\/mobile\\/20200309\\/2020030911_bcf423d137a047c184a78c12f8b2f5a4_4980_mwpm_03200403.jpg\"},{\"uniquekey\":\"6e9619000bafdd7cdbfdf2264a5ca861\",\"title\":\"墨西哥女子抱着2岁儿子跳轨自杀，被围观者抱怨“阻碍交通”\",\"date\":\"2020-03-09 11:06\",\"category\":\"头条\",\"author_name\":\"国际人物志\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110623655.html\",\"thumbnail_pic_s\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309110623_ab47f3b23c6418b7b8163c626542dc05_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309110623_ab47f3b23c6418b7b8163c626542dc05_3_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309110623_ab47f3b23c6418b7b8163c626542dc05_4_mwpm_03200403.jpg\"},{\"uniquekey\":\"8a4fe544d154aadf0f003d63bb60f88f\",\"title\":\"这台10万出头的合资中型SUV，稳坐家用车王者之位\",\"date\":\"2020-03-09 11:05\",\"category\":\"头条\",\"author_name\":\"言車社TB\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110550909.html\",\"thumbnail_pic_s\":\"http:\\/\\/03imgmini.eastday.com\\/mobile\\/20200309\\/20200309110550_c1dc08a96253d7a10498f493716c5574_4_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/03imgmini.eastday.com\\/mobile\\/20200309\\/20200309110550_c1dc08a96253d7a10498f493716c5574_13_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/03imgmini.eastday.com\\/mobile\\/20200309\\/20200309110550_c1dc08a96253d7a10498f493716c5574_2_mwpm_03200403.jpg\"},{\"uniquekey\":\"c285955dc53e1a8c77a101de087b016d\",\"title\":\"CBA终于要开打了？知名记者透露重要消息，初步开赛时间确定\",\"date\":\"2020-03-09 11:03\",\"category\":\"头条\",\"author_name\":\"中国篮球迷妹\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110302524.html\",\"thumbnail_pic_s\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110302_68ebdda9e9d0de1434dc56a3e5245e2a_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110302_68ebdda9e9d0de1434dc56a3e5245e2a_3_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110302_68ebdda9e9d0de1434dc56a3e5245e2a_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"fe532c3ac41515acade58c9d647ed066\",\"title\":\"创新引领发展，中国俱乐部该如何走出财政亏损？\",\"date\":\"2020-03-09 11:01\",\"category\":\"头条\",\"author_name\":\"用右脚射门\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110157698.html\",\"thumbnail_pic_s\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110157_fab675a7b662015fde391d6e32cfcc22_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110157_fab675a7b662015fde391d6e32cfcc22_3_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/08imgmini.eastday.com\\/mobile\\/20200309\\/20200309110157_fab675a7b662015fde391d6e32cfcc22_4_mwpm_03200403.jpg\"},{\"uniquekey\":\"ab6b5a563bf4ccff7d4855715114e71e\",\"title\":\"我的世界：又一个神级种子“奥利给”，出门就是稀有地形极寒之地\",\"date\":\"2020-03-09 11:01\",\"category\":\"头条\",\"author_name\":\"电竞解说\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309110116673.html\",\"thumbnail_pic_s\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/20200309110116_3f143a44f9a5c5c4291e80931cea46d8_7_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/20200309110116_3f143a44f9a5c5c4291e80931cea46d8_5_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/20200309110116_3f143a44f9a5c5c4291e80931cea46d8_6_mwpm_03200403.jpg\"},{\"uniquekey\":\"a85e7395c011759eb352d49d5b309224\",\"title\":\"最新消息！唐山市供暖时间延长7天\",\"date\":\"2020-03-09 10:59\",\"category\":\"头条\",\"author_name\":\"环球网\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105931890.html\",\"thumbnail_pic_s\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/20200309105931_098a0529829b78049cb8a4153235ec5c_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"e2c5e3234226e8ddb8b9859b2195a7f4\",\"title\":\"政策“及时雨”提升直接融资动能\",\"date\":\"2020-03-09 10:58\",\"category\":\"头条\",\"author_name\":\"乐居财经\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105801877.html\",\"thumbnail_pic_s\":\"http:\\/\\/05imgmini.eastday.com\\/mobile\\/20200309\\/20200309105801_0819e44ccec5371538a674beb6495e6a_1_mwpm_03200403.jpg\"},{\"uniquekey\":\"6d51c54ea3e707d40780cecf8b13cc4f\",\"title\":\"原创 《延禧攻略》魏璎珞的上位，第一步功劳最大的是她，不是明玉？\",\"date\":\"2020-03-09 10:56\",\"category\":\"头条\",\"author_name\":\"老刘说娱乐\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105657662.html\",\"thumbnail_pic_s\":\"http:\\/\\/06imgmini.eastday.com\\/mobile\\/20200309\\/20200309105657_03feef305aa365512a1e1ac54b2e970f_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/06imgmini.eastday.com\\/mobile\\/20200309\\/20200309105657_03feef305aa365512a1e1ac54b2e970f_4_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/06imgmini.eastday.com\\/mobile\\/20200309\\/20200309105657_03feef305aa365512a1e1ac54b2e970f_10_mwpm_03200403.jpg\"},{\"uniquekey\":\"99693d73ed512f8465d1d67d9169be5c\",\"title\":\"街拍：打底裤散发个性魅力，展现时尚的装扮，更凸显女神气息\",\"date\":\"2020-03-09 10:56\",\"category\":\"头条\",\"author_name\":\"美格格聊时尚\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105618138.html\",\"thumbnail_pic_s\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030910_4cf3f4fdd67943f390dd5a02c235b15c_4597_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030910_77594ac0e13241a28a88f03152f68b76_0264_cover_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/2020030910_4ca58648254247eba9ed2d9b41360684_2628_cover_mwpm_03200403.jpg\"},{\"uniquekey\":\"c7cad126b07e4d71df83e25bcb2c313b\",\"title\":\"8万级紧凑型车横评，艾瑞泽5 Pro与全新远景哪款更值得购买？\",\"date\":\"2020-03-09 10:55\",\"category\":\"头条\",\"author_name\":\"搜狐\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105516608.html\",\"thumbnail_pic_s\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309105516_489e23228484f3897e87572d8336cba1_4_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309105516_489e23228484f3897e87572d8336cba1_1_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/04imgmini.eastday.com\\/mobile\\/20200309\\/20200309105516_489e23228484f3897e87572d8336cba1_5_mwpm_03200403.jpg\"},{\"uniquekey\":\"53a68ecfa37abac490a104be38c74b8e\",\"title\":\"珠宝玉石首饰搭配也有“心机”\",\"date\":\"2020-03-09 10:54\",\"category\":\"头条\",\"author_name\":\"原生态购物网\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105453573.html\",\"thumbnail_pic_s\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309105453_2cbc562500337ddedd5c96040fb790e4_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309105453_2cbc562500337ddedd5c96040fb790e4_6_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309105453_2cbc562500337ddedd5c96040fb790e4_8_mwpm_03200403.jpg\"},{\"uniquekey\":\"63a97a7b703f3e7c7a5f5c503dd29ca1\",\"title\":\"周瑜是“嫉贤妒能”吗？为何还被诸位文人歌颂？这才是真相\",\"date\":\"2020-03-09 10:52\",\"category\":\"头条\",\"author_name\":\"元始谈史\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105255232.html\",\"thumbnail_pic_s\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/20200309105255_ccfd9d30d8c5ddc5556b5107fff096f1_1_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/20200309105255_ccfd9d30d8c5ddc5556b5107fff096f1_4_mwpm_03200403.jpg\",\"thumbnail_pic_s03\":\"http:\\/\\/00imgmini.eastday.com\\/mobile\\/20200309\\/20200309105255_ccfd9d30d8c5ddc5556b5107fff096f1_3_mwpm_03200403.jpg\"},{\"uniquekey\":\"d1a27bb7c1dbc07971e54024ccb85318\",\"title\":\"广州：规模以上制造业企业基本复工\",\"date\":\"2020-03-09 10:52\",\"category\":\"头条\",\"author_name\":\"中国青年网\",\"url\":\"http:\\/\\/mini.eastday.com\\/mobile\\/200309105239969.html\",\"thumbnail_pic_s\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309105239_c247910a3e8af5545257b38fa17bbf04_2_mwpm_03200403.jpg\",\"thumbnail_pic_s02\":\"http:\\/\\/09imgmini.eastday.com\\/mobile\\/20200309\\/20200309105239_c247910a3e8af5545257b38fa17bbf04_1_mwpm_03200403.jpg\"}]},\"error_code\":0}";
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
                        return "今日访问次数已达上限";
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
                        Log.d("hello", "getNewsDataFromInternet------onPostExecute,s="+s+"。");
                        NewsBean newsBean = new Gson().fromJson(s, NewsBean.class);
                        //Log.d("hello", "getNewsDataFromInternet------onPostExecute,newsBean="+newsBean);
                        Log.d("hello", "getNewsDataFromInternet------onPostExecute,ErrorCode"+newsBean.getError_code());
                        if (10012 == newsBean.getError_code() || 10006 == newsBean.getError_code()) {
                            //访问次数上限，将从数据库加载数据
                            Log.d("hello", "10012， 10006");
                            GlobalApplication globalApplication = (GlobalApplication) getActivity().getApplication();
                            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                            List<NewsBean.ResultBean.DataBean> dataBeanList = dbManager.getNewsDataBeanList();
                            newsBean.setResult(new NewsBean.ResultBean());
                            newsBean.getResult().setData(dataBeanList);
                        }/* else {
                            GlobalApplication globalApplication = (GlobalApplication) getActivity().getApplication();
                            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                            NewsBean.ResultBean.DataBean dataBean = newsBean.getResult().getData().get(i);
                        }*/
                        Message message = newsMessageHandler.obtainMessage();
                        message.what = GET_NEWS_FROM_INTERNET;
                        message.obj = newsBean;
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

    private String streamToString(InputStream inputStream, String charSet) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSet);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            StringBuilder stringBuilder = new StringBuilder();
            //Log.d("hello", "steamToString,1 inputStream"+inputStream+", readline");
            while ((s=bufferedReader.readLine()) != null) {
                //Log.d("hello", "steamToString,2 s=readline= "+s);
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

    private void deleteListItem(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("提示");
        alertDialogBuilder.setMessage("确认删除新闻？");
        alertDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GlobalApplication app = (GlobalApplication) getActivity().getApplication();
                DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
                // -----------------待完善---------------------
                //  dbManager.deleteNewsForReadItem(position);
                // --------------------------------------------
                refresh();
            }
        });
        alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();
    }

    private List<NewsForReadItem> getTestData() {
        List<NewsForReadItem> result = new ArrayList<NewsForReadItem>();
        for (int i=0; i<5; i++) {
            NewsForReadItem item = new NewsForReadItem();
            item.setId(i);
            item.setTitle("标题"+i);
            item.setIntroduction("这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。");
            //item.setCategory("");
            //item.setContent("引言：...。内容：...。");
            item.setAuthor("News官方作者");
            item.setLastEditTime("2020-07-0"+(i+1));
            item.setReadAmount(30+2*i);
            item.setReviewAmount(5+i);
            item.setLikeAmount(9+i);
            //item.setPic();
            result.add(item);
        }
        return result;
    }
	
/*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(this.getActivity(), "Fragment", Toast.LENGTH_SHORT).show();
        //Log.d("helloworld", "fragmentOnActivityResult");
        refresh();
    }*/
}
