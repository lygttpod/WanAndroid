package com.allen.wanandroid.ui.mine;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.arouter.ARouterPath;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    : 我的页面
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.mainMinePath)
public class MineHomeFragment extends BaseMvpFragment<MineHomePresenter> implements MineHomeView {
    @BindView(R.id.user_name_tv)
    TextView userNameTv;

    @Override
    protected int setStatusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected MineHomePresenter createPresenter() {
        return new MineHomePresenter();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initTopBar(TopBar topBar) {
        topBar.isShowLeftImg(false).setCenterText("我的");
    }

    @Override
    public void initView(View view) {

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
        mPresenter.setUserName();
    }

    @Override
    public void setUserName(String userName) {
        userNameTv.setText(userName);
    }

    @OnClick({R.id.collect_stv, R.id.setting_stv, R.id.about_stv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect_stv:
                mPresenter.gotoCollectList();
                break;
            case R.id.setting_stv:
                mPresenter.gotoSetting();
                break;
            case R.id.about_stv:
                mPresenter.aboutMe();
                break;
        }
    }
}
