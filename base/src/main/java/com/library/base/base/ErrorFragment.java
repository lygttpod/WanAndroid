package com.library.base.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.library.base.R;
import com.library.base.widget.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/20
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class ErrorFragment extends BaseFragment {
    private TextView errorTipTv;

    private String errorMsg;

    public static ErrorFragment newInstance(String errorMsg) {
        ErrorFragment fragment = new ErrorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("MSG", errorMsg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_error;
    }

    @Override
    public void initParams() {
        errorMsg = getArguments().getString("MSG");
    }

    @Override
    public void initTopBar(TopBar topBar) {
        hideTopBar();
    }

    @Override
    public void initView(View view) {
        errorTipTv = view.findViewById(R.id.error_tip_tv);
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void doBusiness(Context context) {
        errorTipTv.setText(errorMsg);
    }

}
