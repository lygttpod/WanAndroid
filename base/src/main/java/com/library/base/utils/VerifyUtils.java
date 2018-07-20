package com.library.base.utils;

import android.text.TextUtils;
import android.widget.EditText;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author allen
 */
public class VerifyUtils {

    /**
     * 验证手机号码格式是否正确
     *
     * @param phoneNum
     * @return
     */
    public static boolean isCorrectPhoneNum(String phoneNum) {
        // 是否为空
        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }
        // 使用正则验证
        String regEx = "^1[3|4|5|7|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * 验证身份证号格式是否正确
     *
     * @param idCardNum 身份证号
     * @return
     */
    public static boolean isCorrectIdCardNum(String idCardNum) {
        if (TextUtils.isEmpty(idCardNum))
            return false;
        String regEx = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(idCardNum);
        return matcher.matches();
    }

    /**
     * 验证银行卡号格式是否正确
     *
     * @param bankCardNum 银行卡号
     * @return
     */
    public static boolean isCorrectBankCardNum(String bankCardNum) {
        if (TextUtils.isEmpty(bankCardNum))
            return false;
        String regEx = "^(\\d{16}|\\d{19})$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(bankCardNum);
        return matcher.matches();
    }

    /**
     * 验证登录密码格式是否正确(6~16位，只准输入数字、英文字母和英文符号)
     *
     * @param loginPassword 登录密码
     * @return
     */
    public static boolean isCorrectLoginPassword(String loginPassword) {
        if (TextUtils.isEmpty(loginPassword))
            return false;
        String regex = "^[!-~]{6,16}$";        //根据ASCII码表顺序，限定只能输入!到~之间的字符
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(loginPassword);
        return matcher.matches();
    }

    /**
     * 验证给定的字符串能否转化为float
     *
     * @param inputStr
     * @return
     */
    public static boolean isCorrectFloat(String inputStr) {
        if (TextUtils.isEmpty(inputStr))
            return false;
        String regex = "^([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    /**
     * 验证输入的字符串是否能正确表示金额
     *
     * @param moneyStr
     * @return
     */
    public static boolean isCorrectMoney(String moneyStr) {
        if (TextUtils.isEmpty(moneyStr))
            return false;
        String regex = "^(0|[1-9][0-9]*)(\\.[0-9]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(moneyStr);
        return matcher.matches();
    }




    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }



    /**
     * 格式化数字(保留一位小数，整数补零  1->1.0   1.00->1.0)
     *
     * @param money
     * @return
     */
    public static String formatNumOne(double money) {
        return Double.valueOf(formatNumTwo(money)) + "";
    }

    /**
     * 格式化数字(保留两位小数)
     *
     * @param money
     * @return
     */
    public static String formatNumTwo(double money) {
        DecimalFormat format = new DecimalFormat("0.000");
        format.setRoundingMode(RoundingMode.FLOOR);
        String num = format.format(money);
        return num.substring(0, num.length() - 1);
    }
}
