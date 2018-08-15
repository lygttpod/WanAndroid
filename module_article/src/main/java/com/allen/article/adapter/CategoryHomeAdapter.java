package com.allen.article.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import com.allen.article.R;
import com.allen.common.bean.CategoryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/24
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CategoryHomeAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {

    private Context context;
    private SparseArray<TagFlowLayout> tagFlowLayoutSparseArray;

    private onTagItemClick onItemClick;

    public void setOnItemClick(onTagItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public CategoryHomeAdapter(Context context, @Nullable List<CategoryBean> data) {
        super(R.layout.module_article_adapter_item_category, data);
        this.context = context;
        tagFlowLayoutSparseArray = new SparseArray<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, final CategoryBean item) {
        helper.setText(R.id.title_tv, item.getName());

        TagFlowLayout flowLayout = tagFlowLayoutSparseArray.get(helper.getView(R.id.tag_flow_layout).hashCode());
        if (flowLayout == null) {
            flowLayout = helper.getView(R.id.tag_flow_layout);
        }

        CategoryTagFlowAdapter adapter = new CategoryTagFlowAdapter(context, item.getChildren());
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (onItemClick != null) {
                    onItemClick.OnTagClickListener(item, position);
                }
                return true;
            }
        });
        flowLayout.setAdapter(adapter);

        tagFlowLayoutSparseArray.put(helper.getView(R.id.tag_flow_layout).hashCode(), flowLayout);
    }


    public interface onTagItemClick {
        void OnTagClickListener(CategoryBean categoryBean, int index);
    }
}
