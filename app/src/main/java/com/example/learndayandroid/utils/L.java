package com.example.learndayandroid.utils;

import android.util.Log;

/**
 * @author By skytofire
 * @explain 打印日志
 * @date 创建时间:2020/4/9.
 */
public class L {
    private static final String TAG = "skytofire";
    private static boolean sDeBug = true;

    public static void d(String msg, Object... args){
        if (!sDeBug){
            return;
        }
        Log.d(TAG, String.format(msg, args));
    }
}
