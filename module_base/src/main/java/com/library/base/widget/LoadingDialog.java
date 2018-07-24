package com.library.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.library.base.R;


/**
 * Created by Allen on 2017/12/21.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading);
        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_layout, null);
        this.setCanceledOnTouchOutside(false);
        setContentView(mView);
    }

}
