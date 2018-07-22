package com.allen.library.bean;

/**
 * Created by Allen on 2017/10/23.
 *
 * @author Allen
 * <p>
 * 返回数据基类
 */

public class BaseData<T> {
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误描述
     */
    private String errorMsg;

    /**
     * 数据
     */
    private T data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
