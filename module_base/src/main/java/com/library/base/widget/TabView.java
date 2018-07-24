package com.library.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.library.base.R;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/02/02
 *      desc    :
 *      version : 1.0
 * </pre>
 */

public class TabView extends View {

    private int mDefaultColor = 0xFF45C01A;
    private float mDefaultTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    private int mColor;
    private int mSpace=5;
    private Bitmap mIconBitmap;
    private String mText;
    private int mTextSize;

    private Paint mTextPaint;

    private Rect mIconRect;
    private Rect mTextBound;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context, attrs);
        init();
    }

    private void init() {
        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mDefaultColor);

        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    private void getAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(R.styleable.TabView_tab_view_icon);
        if (drawable != null) {
            mIconBitmap = drawable.getBitmap();
        }

        mText = a.getString(R.styleable.TabView_tab_view_text);

        mTextSize = (int) a.getDimension(R.styleable.TabView_tab_view_text_size,
                mDefaultTextSize);

        mText = a.getString(R.styleable.TabView_tab_view_text);

        mColor = a.getColor(R.styleable.TabView_tab_view_color, mDefaultColor);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth / 2;

        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap,null,mIconRect,null);
        int x = getMeasuredWidth()/2-mTextBound.width()/2;
        int y = mIconRect.bottom+mTextBound.height()-mSpace;
        canvas.drawText(mText,x,y,mTextPaint);
//        drawText(canvas,mText);
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas, String textString) {

        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (mTextBound.bottom+ mIconRect.height()+ mTextBound.top+mIconRect.height() - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心位置
        canvas.drawText(textString, mTextBound.centerX(), baseline, mTextPaint);
    }
}
