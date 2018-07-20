package com.library.base.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaoyao on 2017/6/6.
 * <p>
 * 安装更新的apk
 */

public class InstallUtil {


    /**
     * 通过非Root模式安装
     *
     * @param context
     * @param apkPath
     * @param applicationId BuildConfig.APPLICATION_ID
     */
    public static void install(Context context, String applicationId, String apkPath) {
        //提升读写权限,否则可能出现解析异常
        setPermission(apkPath);
        installNormal(context, applicationId, apkPath);
    }

    /**
     * 提升读写权限
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static void setPermission(String filePath) {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //普通安装
    private static void installNormal(Context context, String applicationId, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = (new File(apkPath));
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, applicationId + ".fileprovider", file);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
        ActivityCollector.finishAll();
//        Process.killProcess(Process.myPid());
    }
}
