package com.allen.wanandroid.adapter;

import android.view.View;

import com.allen.wanandroid.R;
import com.allen.wanandroid.bean.CategoryBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

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
public class CategoryHomeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public CategoryHomeAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_ONE, R.layout.adapter_item_category_type_one);
        addItemType(TYPE_TWO, R.layout.adapter_item_category_type_two);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TYPE_ONE:
                final CategoryBean.TagBean categoryBean = (CategoryBean.TagBean) item;
                helper.setText(R.id.title_tv, categoryBean.getName())
                        .setImageResource(R.id.arrow_iv, categoryBean.isExpanded() ? R.mipmap.icon_arrow_b : R.mipmap.icon_arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (categoryBean.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_TWO:
                CategoryBean.ChildrenBean childrenBean = (CategoryBean.ChildrenBean) item;
                helper.setText(R.id.title_tv, childrenBean.getName());
                break;
        }
    }
}
