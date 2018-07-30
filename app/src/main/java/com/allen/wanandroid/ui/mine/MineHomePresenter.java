package com.allen.wanandroid.ui.mine;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.utils.SPUtils;
import com.allen.wanandroid.bean.UserBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.utils.DbUtils;
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

    public void setUserName(){
        UserBean userBean = DbUtils.queryUserInfo();
        if (null==userBean){
            return;
        }
        mView.setUserName(userBean.getUsername());
    }

    public void gotoCollectList(){
        ARouter.getInstance().build(ARouterPath.userCollectAcPath).navigation();
    }

    public void gotoHistory(){

    }

    public void aboutMe(){

    }

    public void logout(){
        SPUtils.clearAll();
        UserBean userBean = DbUtils.queryUserInfo();
        if (null!=userBean){
            DbUtils.deleteUserInfo(userBean);
        }
        mView.logout();
    }

}
