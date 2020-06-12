package org.mini.yeation.mall;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.mini.yeation.mall.dao.CartDao;
import org.mini.yeation.mall.utils.AppDatabaseHelper;
import org.mini.yeation.mall.utils.app.ToastUtils;


/**
 * 全局应用
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    /**
     * 获取当前应用实例
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
        Log.i("debug", "--------- Thread Name1 = " + Thread.currentThread().getName());
    }



    public CartDao getCartDao() {
        return AppDatabaseHelper.getInstance(this).getCartDao();
    }


}
