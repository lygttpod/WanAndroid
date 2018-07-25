package com.allen.wanandroid.ui.presenter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.allen.wanandroid.ui.view.WebViewView;
import com.library.base.mvp.BaseMvpPresenter;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/25
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class WebViewPresenter extends BaseMvpPresenter<WebViewView> {

    public void loadUrl(WebView webView, String url) {
        initWebView(webView, mView.getProgressBar());
        webView.loadUrl(url);
    }

    private void initWebView(WebView webView, final ProgressBar progressBar) {
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
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(new MyWebChromeClient(progressBar));
    }

    private class MyWebChromeClient extends WebChromeClient {
        private ProgressBar progressBar;

        public MyWebChromeClient(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (null != progressBar) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (progressBar.getVisibility() == View.INVISIBLE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (TextUtils.isEmpty(title)) {
                mView.setTitle(title);
            }
        }
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
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
