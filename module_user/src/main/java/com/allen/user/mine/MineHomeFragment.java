package com.allen.user.mine;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterPath;
import com.allen.library.SuperTextView;
import com.allen.user.R;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    : 我的页面
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleUserMineFragmentPath)
public class MineHomeFragment extends BaseMvpFragment<MineHomePresenter> implements MineHomeView, View.OnClickListener {

    private TextView mUserNameTv;
    private SuperTextView mCollectStv;
    private SuperTextView mSettingStv;
    private SuperTextView mAboutStv;

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
        return R.layout.module_user_fragment_mine;
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
        mUserNameTv = view.findViewById(R.id.user_name_tv);
        mCollectStv = view.findViewById(R.id.collect_stv);
        mCollectStv.setOnClickListener(this);
        mSettingStv = view.findViewById(R.id.setting_stv);
        mSettingStv.setOnClickListener(this);
        mAboutStv = view.findViewById(R.id.about_stv);
        mAboutStv.setOnClickListener(this);
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
        mUserNameTv.setText(userName);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.collect_stv) {
            mPresenter.gotoCollectList();
        } else if (v.getId() == R.id.setting_stv) {
            mPresenter.gotoSetting();
        } else if (v.getId() == R.id.about_stv) {
            mPresenter.aboutMe();
        }
    }
}
