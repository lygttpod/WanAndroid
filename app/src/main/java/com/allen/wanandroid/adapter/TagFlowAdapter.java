package com.allen.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.allen.wanandroid.R;
import com.allen.wanandroid.bean.HotBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

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
        return view;
    }
}
