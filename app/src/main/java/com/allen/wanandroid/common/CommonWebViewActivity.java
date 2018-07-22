package com.allen.wanandroid.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.allen.wanandroid.R;
import com.allen.wanandroid.base.BaseAppActivity;
import com.allen.wanandroid.constant.BundleKey;
import com.library.base.widget.TopBar;

import butterknife.BindView;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/22
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CommonWebViewActivity extends BaseAppActivity {
    @BindView(R.id.web_view)
    WebView webView;

    private String title;
    private String url;

    @Override
    protected void getBundleData(Bundle params) {
        title = params.getString(BundleKey.TITLE);
        url = params.getString(BundleKey.URL);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_common_webview;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setLeftText(title);
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void doBusiness(Context context) {
        initWebView();
        webView.loadUrl(url);
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 开启Javascript支持
        webSettings.setLoadsImagesAutomatically(true);// 设置可以自动加载图片

        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webView.setHorizontalScrollBarEnabled(true);// 设置水平滚动条
        webView.setVerticalScrollBarEnabled(false);// 设置竖直滚动条
        webView.setWebViewClient(new MyWebViewClient());

        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.isEmpty(title)) {
                    topBar.setLeftText(title);
                }
            }

        };
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(webChromeClient);
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dismissLoading();
        }
    }
}
