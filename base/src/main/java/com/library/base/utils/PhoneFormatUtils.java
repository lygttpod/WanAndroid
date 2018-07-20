package com.library.base.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/03/14
 *      desc    :
 *      version : 1.0
 * </pre>
 */

public class PhoneFormatUtils {


    /**
     * 电话3 4 4格式(即：xxx xxxx xxxx)
     * 电话长度11位数字
     * @param view 输入框
     * @param text 文本
     */
    public static void onTextChanged344(EditText view, String text) {
        if (view== null || text == null || text.length() == 0) return;
        char space = ' ';
        int indexSpace1 = 3;
        int indexSpace2 = 8;
        StringBuilder sb = new StringBuilder();
        //1.取出所有字符，去掉' '和非法字符
        for (int i = 0; i < text.length(); i++) {
            //如果数字数大于11位，去掉后面的数字
            if(sb.length() >= 11){
                break;
            }

            //是否合法字符(0~9)
            Pattern pattern = Pattern.compile("^[0-9]*$");
            Matcher matcher = pattern.matcher(String.valueOf(text.charAt(i)));
            if (text.charAt(i) != space && matcher.matches()) {
                sb.append(text.charAt(i));
            }
        }

        //2.根据长度追加' '
        if(sb.length() > indexSpace1){
            sb.insert(indexSpace1, space);
        }
        if(sb.length() > indexSpace2){
            sb.insert(indexSpace2, space);
        }
        //3.设置文本和光标位置
        if(!sb.toString().equals(text)){
            view.setText(sb.toString());
            view.setSelection(sb.length());
        }
    }

    /**
     * 获得已输入的电话号，不包括空格
     * @param editText 输入控件
     * @return 电话号
     */
    public static String getPhoneNumber(EditText editText){
        if (editText== null || editText.getText() == null) return "";
        String text = editText.getText().toString();
        char space = ' ';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != space) {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }

}
