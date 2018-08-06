package com.allen.wanandroid.ui.login;

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
import com.allen.wanandroid.R;
import com.allen.wanandroid.bean.UserBean;
import com.allen.wanandroid.arouter.ARouterPath;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.library.base.mvp.BaseMvpActivity;
import com.library.base.widget.TopBar;

import butterknife.BindView;
import butterknife.OnClick;
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
@Route(path = ARouterPath.loginPath)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.user_name_et)
    TextInputEditText userNameEt;
    @BindView(R.id.user_name_til)
    TextInputLayout userNameTil;
    @BindView(R.id.password_et)
    TextInputEditText passwordEt;
    @BindView(R.id.password_til)
    TextInputLayout passwordTil;
    @BindView(R.id.re_password_et)
    TextInputEditText rePasswordEt;
    @BindView(R.id.re_password_til)
    TextInputLayout rePasswordTil;
    @BindView(R.id.option_status)
    TextView optionStatus;
    @BindView(R.id.login_bt)
    Button loginBt;


    private boolean isLoginStatus = true;

    private Disposable userNamDisposable, psdDisposable, rePsdDisposable;

    private CompositeDisposable compositeDisposable;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
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

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context context) {
        compositeDisposable = new CompositeDisposable();

        compositeDisposable = new CompositeDisposable();

        userNamDisposable = RxTextView
                .textChanges(userNameEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 0) {
                            userNameTil.setError("");
                        }
                    }
                });
        psdDisposable = RxTextView
                .textChanges(passwordEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 12) {
                            passwordTil.setError("密码长度错误");
                        }else {
                            passwordTil.setError("");
                        }
                    }
                });

        rePsdDisposable = RxTextView
                .textChanges(rePasswordEt)
                .skip(1)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 12) {
                            rePasswordTil.setError("密码长度错误");
                        }else {
                            passwordTil.setError("");
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
        return userNameEt.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return passwordEt.getText().toString().trim();
    }

    @Override
    public String getRePassWord() {
        return rePasswordEt.getText().toString().trim();
    }

    @Override
    public void optionSuccess(UserBean userBean) {
        finish();
    }

    @Override
    public void optionError(String error) {
        if (isLoginStatus){
            passwordTil.setError(error);
        }else {
            rePasswordTil.setError(error);
        }
    }


    @OnClick({R.id.option_status, R.id.login_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.option_status:
                optionStatus.setText(isLoginStatus ? "没有账号？" : "去登录");
                loginBt.setText(isLoginStatus ? "登录" : "注册");
                rePasswordTil.setVisibility(isLoginStatus ? View.GONE : View.VISIBLE);
                isLoginStatus = !isLoginStatus;
                break;
            case R.id.login_bt:
                if (TextUtils.isEmpty(getUserName())) {
                    userNameTil.setError("用户名不能为空");
                } else if (TextUtils.isEmpty(getPassWord())) {
                    passwordTil.setError("密码不能为空");
                } else {
                    if (isLoginStatus) {
                        mPresenter.login();
                    } else {
                        if (TextUtils.isEmpty(getRePassWord())) {
                            rePasswordTil.setError("密码不能为空");
                        } else {
                            mPresenter.register();
                        }
                    }
                }
                break;
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
