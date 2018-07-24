package com.library.base.permission;

import java.util.List;

/**
 * Created by Allen on 2017/4/18.
 * 权限管理回调接口
 */

public interface PermissionListener {

    /**
     * 权限已同意
     */
    void onGranted();

    /**
     * 拒绝权限
     *
     * @param deniedPermission
     */
    void onDenied(List<String> deniedPermission);
}
