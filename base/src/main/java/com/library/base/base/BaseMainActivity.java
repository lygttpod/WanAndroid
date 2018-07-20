package com.library.base.base;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.library.base.R;
import com.library.base.bean.Tab;
import com.library.base.permission.BasePermissionActivity;
import com.library.base.utils.ActivityCollector;
import com.library.base.widget.BottomTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/5/5.
 * <p>
 * 快速实现底部导航基础类
 */

public abstract class BaseMainActivity extends BasePermissionActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private long lastBackPressTime = 0;

    public BottomTabView bottomTabView;
    private List<Tab> mTabs = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> fragmentNames = new ArrayList<>();

    protected FragmentManager fm;

    public abstract List<Tab> setBottomTabs(List<Tab> tabs);

    public abstract List<String> setFragmentClassNames(List<String> fragmentClassNames);


    public abstract void setTabSelectListener(View v, int position);

    public abstract void doBusiness();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);
        fm = getSupportFragmentManager();
        fragmentNames = setFragmentClassNames(new ArrayList<String>());
        initTab();
        doBusiness();
    }


    /**
     * 初始化底部导航
     */
    private void initTab() {
        bottomTabView = findViewById(R.id.bottom_tab_view);
        mTabs = setBottomTabs(new ArrayList<Tab>());
        for (int i = 0; i < mTabs.size(); i++) {
            bottomTabView.addTab(mTabs.get(i));
        }
        initListener();
        bottomTabView.setCurrentItem(0);
    }

    private void initListener() {
        bottomTabView.setOnTabSelectedListener(new BottomTabView.OnTabSelectedListener() {
            @Override
            public void onTabSelected(View v, int position) {
                setTabSelectListener(v, position);
                showFragment(position);
            }
        });
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

    private void showFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        Fragment fragment = null;
        if (fragments.size() > position) {
            fragment = fragments.get(position);
        }
        if (null == fragment) {
            if (position < fragmentNames.size()) {
                fragment = createFragment(fragmentNames.get(position));
            } else {
                fragment = ErrorFragment.newInstance("fragment数量和tab不匹配");
                Log.e(TAG, "fragment数量和tab不匹配");
            }
            if (null != fragment) {
                fragments.add(fragment);
                ft.add(R.id.home_container, fragment);
            } else {
                fragment = ErrorFragment.newInstance("未能创建fragment实例");
                fragments.add(fragment);
                ft.add(R.id.home_container, fragment);
                Log.e(TAG, "未能创建fragment实例");
            }
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param ft FragmentTransaction
     */
    private void hideFragment(FragmentTransaction ft) {
        if (null != fragments && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
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
}
