package com.allen.login.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.UserBean;
import com.allen.login.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.library.base.mvp.BaseMvpActivity;
import com.library.base.widget.TopBar;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/25
 *      desc    : 登录页面
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleLoginLoginActivityPath)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    private boolean isLoginStatus = true;

    private Disposable userNamDisposable, psdDisposable, rePsdDisposable;

    private CompositeDisposable compositeDisposable;
    /**
     * 请输入用户名
     */
    private TextInputEditText mUserNameEt;
    private TextInputLayout mUserNameTil;
    /**
     * 请输入密码
     */
    private TextInputEditText mPasswordEt;
    private TextInputLayout mPasswordTil;
    /**
     * 请再次输入密码
     */
    private TextInputEditText mRePasswordEt;
    private TextInputLayout mRePasswordTil;
    /**
     * 没有账号？
     */
    private TextView mOptionStatus;
    /**
     * 登录
     */
    private Button mLoginBt;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_login_activity_login;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setLeftText("登录/注册");
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void initUI(Context context) {
        initView();
    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context context) {
        compositeDisposable = new CompositeDisposable();

        compositeDisposable = new CompositeDisposable();

        userNamDisposable = RxTextView
                .textChanges(mUserNameEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 0) {
                            mUserNameTil.setError("");
                        }
                    }
                });
        psdDisposable = RxTextView
                .textChanges(mPasswordEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 12) {
                            mPasswordTil.setError("密码长度错误");
                        } else {
                            mPasswordTil.setError("");
                        }
                    }
                });

        rePsdDisposable = RxTextView
                .textChanges(mRePasswordEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 12) {
                            mRePasswordTil.setError("密码长度错误");
                        } else {
                            mRePasswordTil.setError("");
                        }
                    }
                });
        compositeDisposable.add(userNamDisposable);
        compositeDisposable.add(psdDisposable);
        compositeDisposable.add(rePsdDisposable);

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getUserName() {
        return mUserNameEt.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return mPasswordEt.getText().toString().trim();
    }

    @Override
    public String getRePassWord() {
        return mRePasswordEt.getText().toString().trim();
    }

    @Override
    public void optionSuccess(UserBean userBean) {
        finish();
    }

    @Override
    public void optionError(String error) {
        if (isLoginStatus) {
            mPasswordTil.setError(error);
        } else {
            mRePasswordTil.setError(error);
        }
    }

    public void initView() {
        mUserNameEt = (TextInputEditText) findViewById(R.id.module_user_user_name_et);
        mUserNameTil = (TextInputLayout) findViewById(R.id.module_user_user_name_til);
        mPasswordEt = (TextInputEditText) findViewById(R.id.module_user_password_et);
        mPasswordTil = (TextInputLayout) findViewById(R.id.module_user_password_til);
        mRePasswordEt = (TextInputEditText) findViewById(R.id.module_user_re_password_et);
        mRePasswordTil = (TextInputLayout) findViewById(R.id.module_user_re_password_til);
        mOptionStatus = (TextView) findViewById(R.id.module_user_option_status);
        mOptionStatus.setOnClickListener(this);
        mLoginBt = (Button) findViewById(R.id.module_user_login_bt);
        mLoginBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.module_user_option_status) {
            mOptionStatus.setText(isLoginStatus ? "没有账号？" : "去登录");
            mLoginBt.setText(isLoginStatus ? "登录" : "注册");
            mRePasswordTil.setVisibility(isLoginStatus ? View.GONE : View.VISIBLE);
            isLoginStatus = !isLoginStatus;
        } else if (v.getId() == R.id.module_user_login_bt) {
            if (TextUtils.isEmpty(getUserName())) {
                mUserNameTil.setError("用户名不能为空");
            } else if (TextUtils.isEmpty(getPassWord())) {
                mPasswordTil.setError("密码不能为空");
            } else {
                if (isLoginStatus) {
                    mPresenter.login();
                } else {
                    if (TextUtils.isEmpty(getRePassWord())) {
                        mRePasswordTil.setError("密码不能为空");
                    } else {
                        mPresenter.register();
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
