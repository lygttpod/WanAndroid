package com.allen.wanandroid.ui.mine;

import com.allen.common.arouter.ARouterHelper;
import com.allen.common.bean.UserBean;
import com.allen.common.utils.DbUtils;
import com.library.base.mvp.BaseMvpPresenter;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class MineHomePresenter extends BaseMvpPresenter<MineHomeView> {

    public void setUserName() {
        UserBean userBean = DbUtils.queryUserInfo();
        if (null == userBean) {
            return;
        }
        mView.setUserName(userBean.getUsername());
    }

    public void gotoCollectList() {
        ARouterHelper.gotoCollectList();
    }

    public void gotoSetting() {
        ARouterHelper.setting();
    }

    public void aboutMe() {
        ARouterHelper.about();
    }


}
