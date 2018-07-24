package com.library.base.widget;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.library.base.R;


/**
 * 自定义TopBar
 * Created by Allen on 16/4/10.
 */
public class TopBar extends RelativeLayout {

    private Context context;

    /**
     * 左边图片
     */
    private Drawable leftImg;

    /**
     * 左边文字内容
     */
    private String leftText;

    /**
     * 左边文字字体大小
     */
    private int leftTextSize;

    /**
     * 左边文字字体颜色
     */
    private int leftTextColor;

    /**
     * 右边图片
     */
    private Drawable rightImg;

    /**
     * 右边文字内容
     */
    private String rightText;

    /**
     * 右边文字字体大小
     */
    private float rightTextSize;

    /**
     * 右边文字字体颜色
     */
    private int rightTextColor;

    /**
     * 中间图片
     */
    private Drawable centerImg;

    /**
     * 中间文字内容
     */
    private String centerText;

    /**
     * 中间文字字体大小
     */
    private float centerTextSize;

    /**
     * 中间文字字体颜色
     */
    private int centerTextColor;

    private ImageView leftImageView;
    private TextView leftTextView;

    private ImageView rightImageView;
    private TextView rightTextView;

    private ImageView centerImageView;
    private TextView centerTextView;

    private View bottomLine;

    private int bottomLineColor = 0xffe1e5e8;

    private OnTopBarClickListener mTopBarClickListener;
    private OnTopBarRightClickListener mTopBarRightClickListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        getAttr(attrs);

        if (leftImg != null) {
            installLeftImg();
        }
        if (leftText != null) {
            installLeftTextView();
        }
        if (rightImg != null) {
            installRightImg();
        }
        if (rightText != null) {
            installRightTextView();
        }
        if (centerImg != null) {
            installCenterImg();
        }
        if (centerText != null) {
            installCenterTextView();
        }

        if (bottomLine == null) {
            installBottomLine();
        }

    }

    /**
     * 获取自定义属性值
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        leftImg = typedArray.getDrawable(R.styleable.TopBar_topBar_leftIcon);

        leftText = typedArray.getString(R.styleable.TopBar_topBar_leftText);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_topBar_leftTextSize, sp2px(15));
        leftTextColor = typedArray.getColor(R.styleable.TopBar_topBar_leftTextColor, 0xffffffff);

        rightImg = typedArray.getDrawable(R.styleable.TopBar_topBar_rightIcon);

        rightText = typedArray.getString(R.styleable.TopBar_topBar_rightText);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_topBar_rightTextSize, sp2px(13));
        rightTextColor = typedArray.getColor(R.styleable.TopBar_topBar_rightTextColor, 0xffffffff);

        centerImg = typedArray.getDrawable(R.styleable.TopBar_topBar_centerBackgroundIcon);

        centerText = typedArray.getString(R.styleable.TopBar_topBar_centerText);
        centerTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_topBar_centerTextSize, sp2px(15));
        centerTextColor = typedArray.getColor(R.styleable.TopBar_topBar_centerTextColor, 0xffffffff);

        typedArray.recycle();
    }

    /**
     * 添加左侧图片
     */
    private void installLeftImg() {
        leftImageView = new ImageView(context);

        leftImageView.setId(R.id.topBar_leftImg_id);
        LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftImageView.setPadding(dp2px(15), dp2px(3), dp2px(5), dp2px(3));
        leftImageView.setScaleType(ImageView.ScaleType.CENTER);
        if (leftImg != null) {
            leftImageView.setImageDrawable(leftImg);
        }
        leftImageView.setBackgroundResource(R.drawable.selector_topbar_back);
        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopBarClickListener != null) {
                    mTopBarClickListener.onLeftIconClick();
                } else {
                    ((Activity) context).finish();
                }
            }
        });

        addView(leftImageView, leftParams);
    }

    /**
     * 添加左侧标题
     */
    private void installLeftTextView() {
        leftTextView = new TextView(context);

        LayoutParams leftTextViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (leftImageView != null) {
            leftTextViewParams.addRule(RelativeLayout.RIGHT_OF, R.id.topBar_leftImg_id);
            leftTextViewParams.leftMargin = dp2px(16);

        } else {
            leftTextViewParams.leftMargin = dp2px(16);
        }
        leftTextView.setGravity(Gravity.CENTER);
        leftTextView.setText(leftText);
        leftTextView.setTextColor(leftTextColor);
        leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);

        leftTextView.setSingleLine(true);
        leftTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(leftTextView, leftTextViewParams);
    }

    /**
     * 设置右侧图片
     */
    private void installRightImg() {
        rightImageView = new ImageView(context);
        LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightImageView.setPadding(dp2px(10), 0, dp2px(15), 0);
        rightImageView.setScaleType(ImageView.ScaleType.CENTER);
        rightImageView.setImageDrawable(rightImg);
        rightImageView.setBackgroundResource(R.drawable.selector_topbar_back);
        rightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopBarClickListener != null) {
                    mTopBarClickListener.onRightIconClick();
                }
            }
        });

        addView(rightImageView, rightParams);
    }

    /**
     * 设置右侧标题
     */
    private void installRightTextView() {
        rightTextView = new TextView(context);
        LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightTextView.setPadding(dp2px(10), 0, dp2px(20), 0);
        rightTextView.setGravity(Gravity.CENTER);
        rightTextView.setText(rightText);
        rightTextView.setTextColor(rightTextColor);
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        rightTextView.setBackgroundResource(R.drawable.selector_topbar_back);
        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopBarRightClickListener != null) {
                    mTopBarRightClickListener.onRightTextClick();
                }
            }
        });

        addView(rightTextView, rightParams);
    }

    /**
     * 设置中间的ImageView
     */
    private void installCenterImg() {
        centerImageView = new ImageView(context);

        LayoutParams centerParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        centerParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        centerImageView.setScaleType(ImageView.ScaleType.CENTER);
        centerImageView.setImageDrawable(centerImg);

        addView(centerImageView, centerParams);
    }

    /**
     * 设置中间文字
     */
    private void installCenterTextView() {
        centerTextView = new TextView(context);

        centerTextView.setId(R.id.topBar_center_title_id);
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        centerTextView.setGravity(Gravity.CENTER);
        centerTextView.setText(centerText);
        centerTextView.setTextColor(centerTextColor);
        centerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, centerTextSize);
        centerTextView.getPaint().setFakeBoldText(true);

        addView(centerTextView, titleParams);

    }

    /**
     * 底部分割线
     */
    private void installBottomLine() {
        bottomLine = new View(context);
        LayoutParams bottomLineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1));
        bottomLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
        bottomLine.setBackgroundColor(bottomLineColor);
        bottomLine.setVisibility(GONE);

        addView(bottomLine, bottomLineParams);
    }

    public TopBar isShowBottomLine(boolean isShow) {
        if (isShow) {
            bottomLine.setVisibility(VISIBLE);
        } else {
            bottomLine.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 设置左边图标的icon
     *
     * @param leftImg
     * @return
     */
    public TopBar setLeftIcon(Drawable leftImg) {
        this.leftImg = leftImg;
        if (leftImageView == null) {
            installLeftImg();
        } else {
            leftImageView.setImageDrawable(leftImg);
        }
        return this;
    }

    /**
     * 设置左边图标icon  传入资源id
     *
     * @param leftImgId
     * @return
     */
    public TopBar setLeftIcon(int leftImgId) {
        if (leftImageView == null) {
            installLeftImg();
        }
        leftImageView.setImageResource(leftImgId);

        return this;
    }

    public TopBar isShowLeftImg(boolean isShow) {
        if (!isShow) {
            leftImageView.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 设置左边标题文字
     *
     * @param text
     * @return
     */
    public TopBar setLeftText(String text) {
        this.leftText = text;
        if (leftTextView == null) {
            installLeftTextView();
        } else {
            leftTextView.setText(text);
        }
        return this;
    }

    public String getLeftText() {
        return leftText;
    }

    public TopBar setLeftText(int text) {
        return setLeftText(getContext().getString(text));
    }

    /**
     * 设置左边文字的字体大小，以sp为单位
     *
     * @param size
     * @return
     */
    public TopBar setLeftTextSize(float size) {
        this.leftTextSize = sp2px(size);
        if (leftTextView != null) {
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        }
        return this;
    }

    /**
     * 设置左边文字的字体颜色
     *
     * @param color
     * @return
     */
    public TopBar setLeftTextColor(int color) {
        if (leftTextColor != color) {
            leftTextColor = color;
            if (leftTextView != null) {
                leftTextView.setTextColor(leftTextColor);
            }
        }
        return this;
    }

    /**
     * 设置右边图标的icon
     *
     * @param rightImg
     * @return
     */
    public TopBar setRightIcon(Drawable rightImg) {
        this.rightImg = rightImg;
        if (rightImageView == null) {
            installRightImg();
        } else {
            rightImageView.setImageDrawable(rightImg);
        }
        return this;
    }

    /**
     * 设置右边标题文字
     *
     * @param text
     * @return
     */
    public TopBar setRightText(String text) {
        this.rightText = text;
        if (rightTextView == null) {
            installRightTextView();
        } else {
            rightTextView.setText(text);
        }
        return this;
    }

    public TextView getRightTextView(){
        return rightTextView;
    }

    /**
     * 设置右边文字的字体大小，以sp为单位
     *
     * @param size
     * @return
     */
    public TopBar setRightTextSize(float size) {
        this.rightTextSize = sp2px(size);
        if (rightTextView != null) {
            rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        }
        return this;
    }

    /**
     * 设置右边文字的字体颜色
     *
     * @param color
     * @return
     */
    public TopBar setRightTextColor(int color) {
        if (rightTextColor != color) {
            rightTextColor = color;
            if (rightTextView != null) {
                rightTextView.setTextColor(rightTextColor);
            }
        }
        return this;
    }

    /**
     * 设置居中标题文字
     *
     * @param text
     * @return
     */
    public TopBar setCenterText(String text) {
        this.centerText = text;
        if (centerTextView == null) {
            installCenterTextView();
        } else {
            centerTextView.setText(text);
        }
        return this;
    }

    /**
     * 设置中间文字的字体大小
     *
     * @param size
     * @return
     */
    public TopBar setCenterTextSize(float size) {
        this.centerTextSize = sp2px(size);
        if (centerTextView != null) {
            centerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, centerTextSize);
        }
        return this;
    }

    /**
     * 设置中间文字的字体颜色
     *
     * @param color
     * @return
     */
    public TopBar setCenterTextColor(int color) {
        this.centerTextColor = color;
        if (centerTextView != null) {
            centerTextView.setTextColor(color);
        }
        return this;
    }

    public void setTopBarClickListener(OnTopBarClickListener listener) {
        mTopBarClickListener = listener;
    }

    public void setTopBarRightClickListener(OnTopBarRightClickListener listener) {
        mTopBarRightClickListener = listener;
    }

    /**
     * 左右图标点击事件
     */
    public interface OnTopBarClickListener {

        void onLeftIconClick();

        void onRightIconClick();
    }

    /**
     * 右侧文字点击事件
     */
    public interface OnTopBarRightClickListener {

        void onRightTextClick();

    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }
}