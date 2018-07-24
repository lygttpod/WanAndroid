package com.library.base.base;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.library.base.R;
import com.library.base.permission.BasePermissionActivity;
import com.library.base.widget.LoadingDialog;
import com.library.base.widget.TopBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/19
 *      desc    : activity基类
 *      version : 1.1
 * </pre>
 */
public abstract class BaseActivity extends BasePermissionActivity {

    protected final String TAG = this.getClass().getSimpleName();

    public Dialog loadingDialog;

    private Unbinder unbinder;

    private FrameLayout viewContent;

    private FrameLayout customTopBar;

    public TopBar topBar;

    public SwipeRefreshLayout swipeRefreshLayout;

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    /**
     * Toast提示
     */
    private Toast mToast;


    /**
     * 获取传递的Bundle参数
     *
     * @param params Bundle
     */
    protected abstract void getBundleData(Bundle params);

    /**
     * 绑定布局
     */
    public abstract int bindLayout();

    /**
     * 初始化TopBar
     *
     * @param topBar 设置topBar
     */
    public abstract void setTopBar(TopBar topBar);


    /**
     * 自定义TopBar
     *
     * @return 默认为空使用默认的
     */
    public View customTopBarLayout() {
        return null;
    }

    /**
     * 初始化ButterKnife
     *
     * @param context 上下文对象
     */
    public void initButterKnife(Context context) {
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 初始化MVP架构的P层
     */
    protected void initPresenter() {
    }

    /**
     * 设置是否允许刷新
     */
    public abstract boolean isCanRefresh();

    /**
     * 设置fitsSystemWindows属性值
     *
     * @return 默认true
     */
    public boolean isFitsSystemWindows() {
        return true;
    }

    /**
     * 设置刷新请求事件
     */
    public abstract void doOnRefresh();

    /**
     * 处理业务逻辑的方法
     *
     * @param context 上下文对象
     */
    public abstract void doBusiness(Context context);

    public Dialog setLoadingView() {
        return new LoadingDialog(this);
    }


    /**
     * 自定义状态栏高度
     */
    public LinearLayout statusBarHeightView;
    /**
     * 跟布局
     * 用于设置fitsSystemWindows属性
     */
    public LinearLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = setLoadingView();

        Log.d(TAG, "onCreate()");

        setBundleData();

        setContentView(R.layout.activity_base);

        rootLayout = findViewById(R.id.root_layout);
        rootLayout.setFitsSystemWindows(isFitsSystemWindows());

        initRefresh();

        initTopBar();

        initView();

        initButterKnife(this);

        initPresenter();

        doBusiness(this);

    }


    /**
     * 设置bundle数据
     */
    private void setBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getBundleData(bundle);
        }
    }

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initRefresh() {
        statusBarHeightView = findViewById(R.id.statusBarHeight);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setColorSchemeResources(R.color.base_refresh_color,
                R.color.base_refresh_color);
        swipeRefreshLayout.setEnabled(isCanRefresh());
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doOnRefresh();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    /**
     * 设置统一的toolbar
     */
    private void initTopBar() {
        topBar = findViewById(R.id.topBar);
        setTopBar(topBar);
        if (customTopBarLayout() != null) {
            customTopBar = findViewById(R.id.custom_top_bar);
            customTopBar.addView(customTopBarLayout());
        }
    }


    /**
     * 设置view
     */
    private void initView() {
        viewContent = findViewById(R.id.viewContent);
        LayoutInflater.from(this).inflate(bindLayout(), viewContent);
    }


    /**********************常用方法***********************/

    /**
     * 页面跳转
     *
     * @param clz 目标类
     */
    protected void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz    目标类
     * @param bundle Bundle参数
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 简化Toast
     *
     * @param msg 消息内容
     */
    protected void showToastMsg(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 隐藏TopBar
     */
    public void hideTopBar() {
        if (topBar != null) {
            topBar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示TopBar
     */
    public void showTopBar() {
        if (topBar != null) {
            topBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view View
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 刷新完成关闭刷新样式
     */
    public void refreshComplete() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    public void showLoading() {
        if (null != loadingDialog) {
            if (!isFinishing() && !loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    public void dismissLoading() {
        if (null != loadingDialog) {
            if (!isFinishing()) {
                loadingDialog.dismiss();
            }
        }
    }

    /**********************在这里设置全局的页面跳转动画***********************/

    /**
     * 在这里写页面跳转动画
     *
     * @param intent Intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    /**
     * 页面结束动画
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**********************生命周期方法***********************/

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

}
