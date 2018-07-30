package com.library.base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.base.R;
import com.library.base.bean.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/02/02
 *      desc    : 自定义底部导航
 *      version : 1.0
 * </pre>
 */

public class BottomTabView extends LinearLayout implements View.OnClickListener {

    private List<View> mTabViews;//保存TabView
    private List<Tab> mTabs;// 保存Tab

    private OnTabSelectedListener mOnTabSelectedListener;

    private int currentItemPosition;

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mOnTabSelectedListener = onTabSelectedListener;
    }

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<>();
    }

    /**
     * 添加Tab
     *
     * @param tab tab
     */
    public void addTab(Tab tab) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_custom_tab_item_layout, null);
        TextView textView = view.findViewById(R.id.custom_tab_text);
        ImageView imageView = view.findViewById(R.id.custom_tab_icon);
        imageView.setImageResource(tab.getIconNormalResId());
        textView.setText(tab.getTitle());
        textView.setTextColor(tab.getTextNormalColor());

        view.setTag(mTabViews.size());
        view.setOnClickListener(this);

        mTabViews.add(view);
        mTabs.add(tab);

        addView(view);

    }


    /**
     * 设置选中Tab
     *
     * @param position 位置
     */
    public void setCurrentItem(int position) {
        if (position >= mTabs.size() || position < 0) {
            position = 0;
        }

        mTabViews.get(position).performClick();

//        updateState(position);

    }

    public int getCurrentItemPosition(){
        return currentItemPosition;
    }

    /**
     * 更新状态
     *
     * @param position 位置
     */
    private void updateState(int position) {
        currentItemPosition = position;
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            TextView textView = view.findViewById(R.id.custom_tab_text);
            ImageView imageView = view.findViewById(R.id.custom_tab_icon);
            if (i == position) {
                imageView.setImageResource(mTabs.get(i).getIconPressedResId());
                textView.setTextColor(getResources().getColor(mTabs.get(i).getTextSelectColor()));
            } else {
                imageView.setImageResource(mTabs.get(i).getIconNormalResId());
                textView.setTextColor(getResources().getColor(mTabs.get(i).getTextNormalColor()));
            }
        }
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        if (mOnTabSelectedListener != null) {
            if (mOnTabSelectedListener.onTabIsInterceptBeforeSelected(view,position)){
                mOnTabSelectedListener.onTabInterceptBeforeSelected(view,position);
            }else {
                mOnTabSelectedListener.onTabSelected(view,position);
                updateState(position);
            }
        }
    }

    public interface OnTabSelectedListener {
        boolean onTabIsInterceptBeforeSelected(View v, int position);
        void onTabSelected(View v, int position);
        void onTabInterceptBeforeSelected(View v, int position);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mTabViews!=null){
            mTabViews.clear();
        }
        if(mTabs!=null){
            mTabs.clear();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for(int i=0;i<mTabViews.size();i++){
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabs.size());
            LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

            view.setLayoutParams(params);
        }

    }
}
