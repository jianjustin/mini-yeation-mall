package org.mini.yeation.mall.utils.network;

import android.content.Context;

import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.utils.UserSession;
import org.mini.yeation.mall.utils.app.NetworkUtils;
import org.mini.yeation.mall.utils.app.AppUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseInterceptor {

    public static class Retry implements Interceptor {

        private int maxRetry;//最大重试次数
        private int retryNum = 0; //假如设置为3次重试的话，则最大可能请求4次（默认1次 + 3次重试）

        public Retry(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

    /**
     * 设置没有网络的情况下的缓存时间
     * 通过：addInterceptor 设置
     */
    public static class CommonNoNetworkCache implements Interceptor {

        private int maxCacheTimeSecond;
        private Context applicationContext;

        public CommonNoNetworkCache(int maxCacheTimeSecond, Context applicationContext) {
            this.maxCacheTimeSecond = maxCacheTimeSecond;
            this.applicationContext = applicationContext;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(applicationContext)) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(maxCacheTimeSecond, TimeUnit.SECONDS)
                        .build();
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    /**
     * 设置在有网络的情况下的缓存时间
     * 在有网络的时候，会优先获取缓存
     * 通过：addNetworkInterceptor 设置
     */
    public static class CommonNetworkCache implements Interceptor {

        private int maxCacheTimeSecond;

        public CommonNetworkCache(int maxCacheTimeSecond) {
            this.maxCacheTimeSecond = maxCacheTimeSecond;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder mBuilder = request.newBuilder();
            Map<String, String> mHeaderMap = getHeaderMap();
            if (mHeaderMap != null) {
                for (Map.Entry<String, String> item : mHeaderMap.entrySet()) {
                    mBuilder.addHeader(item.getKey(), item.getValue());
                }
            }
            Response originalResponse = chain.proceed(mBuilder.build());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxCacheTimeSecond)
                    .build();
        }

        /**
         * 生成头部token
         * @return
         */
        private Map<String, String> getHeaderMap() {
            Map<String, String> mHeaderMap = new HashMap<>();
            User user = UserSession.getUser();
            if(null == user)return null;
            mHeaderMap.put("token", String.valueOf(user.getUserid()));
            return mHeaderMap;
        }
    }

}