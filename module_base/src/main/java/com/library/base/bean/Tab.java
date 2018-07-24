package com.library.base.bean;

/**
 * Created by Allen on 2017/4/28.
 * 封装底部导航数据信息
 */
public class Tab {

    private String title;

    private int iconNormalResId;
    private int iconPressedResId;

    private int textNormalColor;
    private int textSelectColor;


    public Tab(String title, int iconNormalResId, int iconPressedResId, int textNormalColor, int textSelectColor) {
        this.title = title;
        this.iconNormalResId = iconNormalResId;
        this.iconPressedResId = iconPressedResId;
        this.textNormalColor = textNormalColor;
        this.textSelectColor = textSelectColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconNormalResId() {
        return iconNormalResId;
    }

    public void setIconNormalResId(int iconNormalResId) {
        this.iconNormalResId = iconNormalResId;
    }

    public int getIconPressedResId() {
        return iconPressedResId;
    }

    public void setIconPressedResId(int iconPressedResId) {
        this.iconPressedResId = iconPressedResId;
    }

    public int getTextNormalColor() {
        return textNormalColor;
    }

    public void setTextNormalColor(int textNormalColor) {
        this.textNormalColor = textNormalColor;
    }

    public int getTextSelectColor() {
        return textSelectColor;
    }

    public void setTextSelectColor(int textSelectColor) {
        this.textSelectColor = textSelectColor;
    }
}
