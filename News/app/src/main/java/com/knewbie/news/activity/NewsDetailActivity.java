package com.knewbie.news.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.knewbie.news.R;

public class NewsDetailActivity extends AppCompatActivity {
    private Toolbar toolbarTop, toolbarBottom;
    private WebView webViewNewDetail;
    private String url;
    private boolean hasRun=false;
    private String selectorAd[] = {"body > div.top-wrap.gg-item.J-gg-item", "#J_in_list", "body > div.articledown-wrap.gg-item.J-gg-item",
            "body > div.articledown2-wrap.gg-item.J-gg-item", "#news_check", "#J_interest_news", "#J_hot_news", "#J_interest_news > div.three-wrap.gg-item.J-gg-item", "#J_in_list > div:nth-child(1)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
    }

    private void initView() {
        toolbarTop = findViewById(R.id.toolbarTop_NewsDetail);
        toolbarBottom = findViewById(R.id.toolbarBottom_NewsDetail);
        webViewNewDetail = findViewById(R.id.webView_newsDetail);
        url = getIntent().getStringExtra("url");
        WebSettings webSettings = webViewNewDetail.getSettings();
        final String selector = "body > div.top-wrap.gg-item.J-gg-item";
        webViewNewDetail.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (hasRun) {
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        hasRun = true;
                        while (hasRun) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handlerClearAd.sendEmptyMessage(1);
                        }
                    }
                }).start();

            }

            Handler handlerClearAd = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    String js = clearAdJs()+"function setTop() {document.querySelector('"+ selector +"').style.display=\"none\";}setTop();"
                            +"function setTop2() {document.querySelector('body > div.articledown-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop2();" +
                            "function setTop3() {document.querySelector('body > div.articledown2-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop3();" +
                            "function setTop4() {document.querySelector('#news_check').style.display=\"none\";}setTop4();";
                    webViewNewDetail.loadUrl(js);
                    Log.d("hello", "clearAd");
                }
            };

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //view.loadUrl("javascript:function setTop() {document.querySelector('"+ selector +"').style.display=\"none\";}setTop();");
                //clearAd();
                /*String js = clearAdJs()+"function setTop() {document.querySelector('"+ selector +"').style.display=\"none\";}setTop();"
                        +"";
                view.loadUrl(js);*/
                hasRun = false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        webSettingsConfig(webSettings);
        webViewNewDetail.loadUrl(url);
        setSupportActionBar(toolbarBottom);
        toolbarBottom.setTitle("");
        setSupportActionBar(toolbarTop);

        //toolbarTop.inflateMenu(R.menu.toolbar_news_detail_top);
        toolbarBottom.inflateMenu(R.menu.toolbar_news_detail_bottom);
        toolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbarItem_like:
                        break;
                    case R.id.toolbarItem_review:
                        break;
                    case R.id.toolbarItem_collect:
                        break;
                    case R.id.toolbarItem_share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT, url);
                        intent.setType("text/plain");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent, getTitle()));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable);
            }
        }

    private String clearAdJs() {
        String js = "javascript:";
        for (int i=0; i<selectorAd.length; i++) {
            js += "var adDiv" + i + " = document.getElementById('" + selectorAd[i] + "');" +
                    "if (adDiv" + i + " != null)" +
                    "adDiv" + i + ".parentNode.removeChild(adDiv" + i + ");";
        }
        return js;
    }

    private void webSettingsConfig(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);// Api 12已不再使用SINGLE_COLUMN
        //webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_news_detail_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.toolbarItem_nightModel:
                break;
            case R.id.toolbarItem_complain:
                break;
            default:
                break;
        }
        return true;
    }
}
