package com.allen.wanandroid.utils;

import com.allen.wanandroid.app.App;
import com.allen.wanandroid.bean.UserBean;
import com.allen.wanandroid.db.UserBeanDao;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class DbUtils {
    public static UserBeanDao getUserDao() {
        return App.getDaoSession().getUserBeanDao();
    }

    /**
     * 插入数据库操作
     */
    public static void insertUserInfo(UserBean userBean) {
        UserBean user = getUserDao()
                .queryBuilder()
                .where(UserBeanDao.Properties.Id.eq(userBean.getId()))
                .build()
                .unique();
        if (null != user) {
            deleteUserInfo(userBean);
        }
        getUserDao().insert(userBean);
    }

    /**
     * 删除某行数据
     *
     * @param userBean
     */
    public static void deleteUserInfo(UserBean userBean) {
        getUserDao().delete(userBean);
    }

    public static UserBean queryUserInfo(){
        UserBean user = getUserDao()
                .queryBuilder()
                .build()
                .unique();
        return user;
    }
    public static boolean isQueryUser(){
        return null!=queryUserInfo();
    }
}
