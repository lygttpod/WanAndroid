package com.allen.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.allen.common.bean.HomeBean;
import com.allen.common.utils.GlideUtils;
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
public class ProjectArticleAdapter extends BaseQuickAdapter<HomeBean.DatasEntity, BaseViewHolder> {

    private Context context;

    public ProjectArticleAdapter(Context context, @Nullable List<HomeBean.DatasEntity> data) {
        super(R.layout.adapter_item_project_article, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DatasEntity item) {
        helper.setText(R.id.author_tv, "作者：" + item.getAuthor());
        helper.setText(R.id.time_tv, item.getNiceDate());
        helper.setText(R.id.title_tv, item.getTitle());
        helper.setImageResource(R.id.collect_iv,item.isCollect()?R.mipmap.icon_collected:R.mipmap.icon_uncollect);

        GlideUtils.loadImg(context, item.getEnvelopePic(), (ImageView) helper.getView(R.id.article_pic_iv));
        helper.addOnClickListener(R.id.collect_iv);
    }
}
