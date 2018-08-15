package com.allen.user.debug;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.allen.user.R;
import com.allen.user.mine.MineHomeFragment;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/15
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class UserMineActivity extends BaseActivity {

    private FrameLayout frameLayout;

    private FragmentTransaction ft;

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_user_test_mine;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        hideTopBar();
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void initUI(Context context) {
        frameLayout = (FrameLayout) findViewById(R.id.user_content_fl);
    }

    @Override
    public void doBusiness(Context context) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.user_content_fl, new MineHomeFragment());
        ft.commit();
    }
}
