package com.library.base.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/02/05
 *      desc    : 字符串高亮处理
 *      version : 1.0
 * </pre>
 */
public class SpannableStringUtils {
    /**
     * 高亮处理
     *
     * @param s
     * @param compile
     * @return
     */
    public static SpannableString getHighlightString(String s, String compile, int colorRes) {
        SpannableString spannableString = new SpannableString(s);
        Pattern pattern = Pattern.compile(compile);
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new ForegroundColorSpan(colorRes), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public static SpannableString getHighlightWithBoldString(String s, List<String> compiles, int colorRes) {
        return getBaseHighlightString(s, compiles, colorRes, true);
    }

    /**
     * 高亮处理
     *
     * @param s
     * @param compiles
     * @return
     */
    public static SpannableString getHighlightString(String s, List<String> compiles, int colorRes) {
        return getBaseHighlightString(s, compiles, colorRes, false);
    }

    public static SpannableString getBaseHighlightString(String s, List<String> compiles, int colorRes, boolean isBold) {
        SpannableString spannableString = new SpannableString(s);
        for (int i = 0; i < compiles.size(); i++) {
            Pattern pattern = Pattern.compile(compiles.get(i));
            Matcher matcher = pattern.matcher(spannableString);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                spannableString.setSpan(new ForegroundColorSpan(colorRes), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (isBold) {
                    spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
                }
            }
        }

        return spannableString;
    }

    public static SpannableStringBuilder getSpannableString(String string, int size, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(string);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size, true);
        spannableString.setSpan(absoluteSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }


}
