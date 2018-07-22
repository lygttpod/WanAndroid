package com.allen.wanandroid.adapter;

import android.support.annotation.Nullable;

import com.allen.wanandroid.R;
import com.allen.wanandroid.bean.HomeBean;
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
public class HomeAdapter extends BaseQuickAdapter<HomeBean.DatasEntity, BaseViewHolder> {

    public HomeAdapter(@Nullable List<HomeBean.DatasEntity> data) {
        super(R.layout.adapter_item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DatasEntity item) {
        helper.setText(R.id.author_tv, "作者："+item.getAuthor());
        helper.setText(R.id.time_tv, item.getNiceDate());
        helper.setText(R.id.title_tv, item.getTitle());
        helper.setText(R.id.chapter_name_tv, "分类：" + item.getSuperChapterName() + "/" + item.getChapterName());
    }
}
