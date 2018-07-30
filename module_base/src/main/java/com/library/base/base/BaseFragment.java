package com.library.base.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.library.base.R;
import com.library.base.widget.LoadingDialog;
import com.library.base.widget.TopBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Allen on 2017/5/2.
 * 封装BaseFragment
 */

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    public Dialog loadingDialog;

    private Unbinder unbinder;
    /**
     * 带TopBar的跟布局
     */
    private View baseView;
    /**
     * 传入的布局
     */
    private View rootView;

    private FrameLayout viewContent;

    public TopBar topBar;

    /**
     * 判断fragment是否创建完并可见
     */
    private boolean isViewCreated = false;

    /**
     * 消息提示
     */
    private Toast mToast;

    /**
     * 懒加载 配合viewPager生效
     */
    protected abstract void lazyLoad();

    /**
     * 绑定布局
     */
    public abstract int bindLayout();

    /**
     * 初始化参数
     */
    public abstract void initParams();

    /**
     * 初始化TopBar
     */
    public abstract void initTopBar(TopBar topBar);

    /**
     * 自定义TopBar
     */
    public View customTopBarLayout() {
        return null;
    }

    /**
     * 初始化view
     *
     * @param view view
     */
    public abstract void initView(View view);


    /**
     * 初始化ButterKnife
     *
     * @param target 当前页面
     * @param view   view
     */
    public void initButterKnife(Object target, View view) {
        unbinder = ButterKnife.bind(target, view);
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
        return new LoadingDialog(getActivity());
    }

    public LinearLayout statusBarHeightView;

    public SwipeRefreshLayout swipeRefreshLayout;

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated) {
            isViewCreated = false;
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        initParams();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        setRootView(inflater, container);

        setTopBar();

        setView();

        initView(rootView);

        initButterKnife(this, rootView);

        initPresenter();

        initLazyLoad();

        return baseView;
    }

    /**
     * 实现懒加载，在页面创建之后在加载
     */
    private void initLazyLoad() {
        isViewCreated = true;
        //判断Fragment是否可见
        if (getUserVisibleHint()) {
            lazyLoad();
            isViewCreated = false;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated()");
        loadingDialog = setLoadingView();
        doBusiness(getActivity());
    }


    /*************************内部方法**************************/

    private void setRootView(LayoutInflater inflater, ViewGroup container) {
        if (baseView == null) {
            baseView = inflater.inflate(R.layout.fragment_base, container, false);
            statusBarHeightView = baseView.findViewById(R.id.statusBarHeight);

            swipeRefreshLayout = baseView.findViewById(R.id.swipe_refresh_layout);

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
    }

    /**
     * 设置view
     */
    private void setView() {
        viewContent = baseView.findViewById(R.id.viewContent);
        rootView = LayoutInflater.from(getActivity()).inflate(bindLayout(), viewContent);
    }

    /**
     * 设置TopBar
     */
    private void setTopBar() {
        topBar = baseView.findViewById(R.id.topBar);
        initTopBar(topBar);
        if (customTopBarLayout() != null) {
            FrameLayout customTopBar = baseView.findViewById(R.id.custom_top_bar);
            customTopBar.addView(customTopBarLayout());
        }

    }

    /**
     * fragment可见的时候调用
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * fragment不可见的时候调用
     */
    protected void onInVisible() {
    }


    /**********************常用方法***********************/

    /**
     * 页面跳转
     *
     * @param clz 目标类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz    目标类
     * @param bundle Bundle参数
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
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
    public void showToastMsg(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
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
     * 显示刷新
     */
    public void showRefreshView() {
        swipeRefreshLayout.setRefreshing(true);
    }

    /**
     * 刷新完成
     */
    public void hideRefreshView() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void showLoadingDialog() {
        if (null != loadingDialog) {
            if (!getActivity().isFinishing() && !loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    public void hideLoadingDialog() {
        if (null != loadingDialog) {
            if (!getActivity().isFinishing()) {
                loadingDialog.dismiss();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        isViewCreated = false;//视图销毁将变量置为false

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyView()");
        if (null != unbinder) {
            unbinder.unbind();
        }
    }
}
