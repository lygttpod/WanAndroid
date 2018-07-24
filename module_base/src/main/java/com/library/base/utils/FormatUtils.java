package com.library.base.utils;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by xiaoyao on 2017/8/2.
 * <p>
 * 格式化数据工具类
 */

public class FormatUtils {

    /**
     * 隐藏部分字符，以*代替对应位置的明文
     *
     * @param originStr  明文字符串
     * @param startIndex 要隐藏的起始位置(包含)
     * @param endIndex   要隐藏的结束位置(包含)
     * @return 如果没有异常，返回带*的字符串；否则返回原字符串
     * 例：(ABCDEFG, 3, 5)-->ABC***G
     */
    public static String hideChars(String originStr, int startIndex, int endIndex) {
        if (TextUtils.isEmpty(originStr))
            return originStr;
        if (startIndex < 0 || endIndex >= originStr.length())
            return originStr;
        if (startIndex > endIndex)
            return originStr;

        StringBuilder sb = new StringBuilder(originStr.length());
        for (int i = 0; i <= startIndex - 1; i++) {
            sb.append(originStr.charAt(i));
        }
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append('*');
        }
        for (int i = endIndex + 1; i <= originStr.length() - 1; i++) {
            sb.append(originStr.charAt(i));
        }
        return sb.toString();
    }


    /**
     * 格式化手机号码 空格分割
     */
    public static String formatPhoneString(String phone) {
        if (phone.length() == 11) {
            StringBuffer sb = new StringBuffer(phone);
            sb.insert(3, " ");
            sb.insert(8, " ");
            return sb.toString();
        } else {
            return phone;
        }
    }

    /**
     * 格式化手机号码 空格分割
     */
    public static String formatPhoneStringWith_(String phone) {
        if (phone.length() == 11) {
            StringBuffer sb = new StringBuffer(phone);
            sb.insert(3, "-");
            sb.insert(8, "-");
            return sb.toString();
        } else {
            return phone;
        }
    }


    /**
     * 格式化银行卡 空格分割
     */
    public static String formatBankCardString(String bankCard) {
        if (bankCard.length() >= 12) {
            StringBuffer sb = new StringBuffer(bankCard);
            sb.insert(4, " ");
            sb.insert(9, " ");
            if (sb.length() >= 14) {
                sb.insert(14, " ");
            }
            if (sb.length() >= 19) {
                sb.insert(19, " ");
            }
            return sb.toString();
        } else {
            return bankCard;
        }
    }


    /**
     * 格式化数字(保留两位小数)
     *
     * @param money
     * @return
     */
    public static String formatNumTwo(double money) {
        DecimalFormat format = new DecimalFormat("0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        return format.format(money);
    }


    /**
     * 格式化数字(保留整数)
     *
     * @param money
     * @return
     */
    public static String formatInt(double money) {
        DecimalFormat format = new DecimalFormat("0");
        format.setRoundingMode(RoundingMode.DOWN);
        return format.format(money);
    }


    /**
     * 格式化数字(保留两位  加--千分符)
     *
     * @param money
     * @return
     */
    public static String formatNumTwoWithComma(double money) {
        DecimalFormat format = new DecimalFormat("###,##0.00");
        format.setRoundingMode(RoundingMode.DOWN);
//		format.parseObject(string)
        return format.format(money);
    }


    /**
     * 格式化数字(加--千分符)
     *
     * @param money
     * @return
     */
    public static String formatNumForIntWithComma(double money) {
        DecimalFormat format = new DecimalFormat("###,##0");
        format.setRoundingMode(RoundingMode.DOWN);
        return format.format(money);
    }


}
