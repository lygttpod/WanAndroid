package com.allen.quickbuildandroid.movie;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.allen.quickbuildandroid.R;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.mvp.IBaseMvpView;
import com.library.base.widget.TopBar;


/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/20
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class MovieFragment extends BaseMvpFragment<MoviePresenter> implements IBaseMvpView {
    private TextView textView;

    @Override
    protected MoviePresenter createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initTopBar(TopBar topBar) {
        hideTopBar();
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.text_tv);
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
        textView.setText("测试数据");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
