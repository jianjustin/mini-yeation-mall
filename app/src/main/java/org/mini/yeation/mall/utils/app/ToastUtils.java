package org.mini.yeation.mall.utils.app;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示消息工具类
 */
public class ToastUtils {
    private static Context mContext;
    private static Toast mToast;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    public static void showToast(String text) {
        if(null == mToast)return;
        mToast.setText(text);
        mToast.show();
    }

    public static void showToast(int resId) {
        if(null == mToast)return;
        mToast.setText(resId);
        mToast.show();
    }

}
