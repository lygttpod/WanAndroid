package com.allen.article.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.allen.article.R;
import com.allen.common.bean.HomeBean;
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
        super(R.layout.module_article_adapter_item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DatasEntity item) {
        helper.setText(R.id.author_tv, "作者：" + item.getAuthor());
        helper.setText(R.id.time_tv, item.getNiceDate());
        helper.setText(R.id.title_tv, Html.fromHtml(item.getTitle()));
        StringBuilder stringBuilder = new StringBuilder();
        String superChapterName = item.getSuperChapterName();
        String chapterName = item.getChapterName();
        if (!TextUtils.isEmpty(superChapterName)) {
            stringBuilder.append(superChapterName);
        }
        if (!TextUtils.isEmpty(chapterName)) {
            if (!TextUtils.isEmpty(superChapterName)) {
                stringBuilder.append("/");
            }
            stringBuilder.append(chapterName);
        }
        helper.setText(R.id.chapter_name_tv, stringBuilder.toString());
        helper.setImageResource(R.id.collect_iv, item.isCollect() ? R.drawable.icon_collected : R.drawable.icon_uncollect);
        helper.addOnClickListener(R.id.collect_iv);
    }
}
