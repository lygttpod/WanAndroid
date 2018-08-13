package com.allen.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.allen.common.bean.CategoryBean;
import com.allen.wanandroid.R;
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
public class CategoryTagFlowAdapter extends TagAdapter<CategoryBean> {
    private Context context;

    public CategoryTagFlowAdapter(Context context,List<CategoryBean> datas) {
        super(datas);
        this.context = context;
    }


    @Override
    public View getView(FlowLayout parent, int position, CategoryBean childrenBean) {
        TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.adapter_item_category_tag, parent, false);
        view.setText(childrenBean.getName());
        return view;
    }
}
