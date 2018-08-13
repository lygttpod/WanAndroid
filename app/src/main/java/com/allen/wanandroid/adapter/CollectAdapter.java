package com.allen.wanandroid.adapter;

import android.support.annotation.Nullable;

import com.allen.common.bean.CollectBean;
import com.allen.wanandroid.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CollectAdapter extends BaseQuickAdapter<CollectBean.DatasBean, BaseViewHolder> {

    public CollectAdapter(@Nullable List<CollectBean.DatasBean> data) {
        super(R.layout.adapter_item_collect, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBean.DatasBean item) {
        helper.setText(R.id.author_tv, "作者：" + item.getAuthor());
        helper.setText(R.id.time_tv, item.getNiceDate());
        helper.setText(R.id.title_tv, item.getTitle());
        helper.setText(R.id.chapter_name_tv, "分类：" + item.getChapterName());
        helper.addOnClickListener(R.id.collect_iv);
    }
}
