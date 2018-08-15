package com.library.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.library.base.R;
import com.library.base.bean.Tab;
import com.library.base.utils.ActivityCollector;
import com.library.base.widget.BottomTabView;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/5/5.
 * <p>
 * 快速实现底部导航基础类
 */

public abstract class BaseMainActivity extends BaseActivity {
    private long lastBackPressTime = 0;

    public BottomTabView bottomTabView;
    private List<Tab> mTabs = new ArrayList<>();

    private Map<String, Fragment> fragmentMap = new HashMap<>();
    private List<String> fragmentNames = new ArrayList<>();

    protected FragmentManager fm;

    /**
     * 底部导航相关参数
     *
     * @param tabs tab
     * @return tab数组
     */
    public abstract List<Tab> setBottomTabs(List<Tab> tabs);

    /**
     * 外界传入需要显示的fragment类目用于创建事例使用
     *
     * @param fragmentClassNames 类名
     * @return 类目数组
     */
    public abstract List<String> setFragmentClassNames(List<String> fragmentClassNames);

    /**
     * 用于处理是否需要登录之后才能跳转的问题
     *
     * @return 默认不拦截 直接跳转
     */
    public abstract boolean isInterceptBeforeSkip(int position);

    /**
     * tab点击事件
     *
     * @param v        View
     * @param position 位置
     */
    public abstract void setTabInterceptSkip(View v, int position);


    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_base_bottom_tab;
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
    public boolean isSwipeBack() {
        return false;
    }

    @Override
    public void initUI(Context context) {

    }

    @Override
    public void doBusiness(Context context) {
        fm = getSupportFragmentManager();
        fragmentNames = setFragmentClassNames(new ArrayList<String>());
        initTab();
    }

    /**
     * 初始化底部导航
     */
    private void initTab() {
        bottomTabView = (BottomTabView) findViewById(R.id.bottom_tab_view);
        mTabs = setBottomTabs(new ArrayList<Tab>());
        for (int i = 0; i < mTabs.size(); i++) {
            bottomTabView.addTab(mTabs.get(i));
        }
        initListener();
        bottomTabView.setCurrentItem(0);
    }

    /**
     * tab点击事件回调
     */
    private void initListener() {
        bottomTabView.setOnTabSelectedListener(new BottomTabView.OnTabSelectedListener() {
            @Override
            public boolean onTabIsInterceptBeforeSelected(View v, int position) {
                return isInterceptBeforeSkip(position);
            }

            @Override
            public void onTabSelected(View v, int position) {
                showFragment(position);
            }

            @Override
            public void onTabInterceptBeforeSelected(View v, int position) {
                setTabInterceptSkip(v, position);
            }
        });
    }


    /**
     * 显示或添加fragment
     *
     * @param position 当前位置
     */
    protected void showFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        Fragment fragment = null;
        StringBuilder className = new StringBuilder();
        //类名前追加position作为唯一标示
        className.append(position);
        //说明创建的fragment数量在tab的范围之类，防止数组越界
        if (position < fragmentNames.size()) {
            className.append(fragmentNames.get(position));
            fragment = fragmentMap.get(className.toString());
            //为空说明还没有创建过实例
            if (null == fragment) {
                fragment = createFragment(fragmentNames.get(position));
                //动态实例化失败，生成默认错误页面提示
                if (null == fragment) {
                    fragment = ErrorFragment.newInstance("未能创建fragment实例 \n\t" +
                            "请检查类名是否传递正确 \n\t " +
                            "请使用XXX.class.getName()获取类名");
                }
                fragmentMap.put(className.toString(), fragment);
                ft.add(R.id.home_container, fragment);
            } else {
                ft.show(fragment);
            }
        } else {
            fragment = ErrorFragment.newInstance("fragment数量和tab数量不匹配 \n\t" +
                    "fragment.size()必须大于或等于tab.size()");
            fragmentMap.put(className.toString(), fragment);

            ft.add(R.id.home_container, fragment);
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param ft FragmentTransaction
     */
    private void hideFragment(FragmentTransaction ft) {
        if (null != fragmentMap && fragmentMap.size() > 0) {
            for (Fragment fragment : fragmentMap.values()) {
                ft.hide(fragment);
            }
        }
    }


    /**
     * 使用反射的方法来做创建实例化Fragment对象
     *
     * @param className 类名
     * @return 反射创建出来fragment
     */
    private Fragment createFragment(String className) {
        //包名+类型
        Fragment fragment = null;
        if (!TextUtils.isEmpty(className)) {
            //newInstance是Fragment中实例化fragment的方法
            try {
                fragment = (Fragment) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastBackPressTime > 1000) {
                Toast.makeText(this, "连按两次退出系统", Toast.LENGTH_SHORT).show();
                lastBackPressTime = System.currentTimeMillis();
            } else {
                ActivityCollector.finishAll();
                this.finish();
            }
        }
        return false;
    }
}
