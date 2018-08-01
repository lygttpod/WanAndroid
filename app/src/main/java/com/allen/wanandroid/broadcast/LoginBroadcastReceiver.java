package com.allen.wanandroid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.allen.library.utils.SPUtils;
import com.allen.wanandroid.ui.login.LoginActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/01
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class LoginBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String s = intent.getAction();
            if (s.equals("com.allen.wan_android.need_login_action")) {
                SPUtils.clearAll();
                Intent login = new Intent();
                login.setClass(context, LoginActivity.class);
                login.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(login);
            }
        }
    }
}
