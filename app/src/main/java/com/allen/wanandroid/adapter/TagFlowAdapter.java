package com.allen.wanandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.allen.wanandroid.R;
import com.allen.wanandroid.bean.HotBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;
import java.util.Random;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class TagFlowAdapter extends TagAdapter<HotBean> {
    private Context context;

    public TagFlowAdapter(Context context, List<HotBean> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, HotBean hotBean) {
        TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false);
        view.setText(hotBean.getName());
        view.setTextColor(getRandomColor());
        return view;
    }

    /**
     * 随机颜色
     * @return 随机颜色值
     */
    private int getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }
}
