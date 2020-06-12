package org.mini.yeation.mall.utils;

import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.utils.app.SPUtils;

/**
 * 用户会话管理
 */
public class UserSession {

    public final static String USER_INFO = "USER_INFO";//用户信息标识
    public final static String IS_LOGIN = "IS_LOGIN";//是否登录

    /**
     * 保存用户会话（用于登录成功后调用）
     */
    public static boolean saveSession(User user){
        SPUtils.putJSONCache(BaseApplication.getInstance(), USER_INFO, JsonUtils.toString(user));
        SPUtils.putBoolean(BaseApplication.getInstance(), IS_LOGIN, true);
        return true;
    }

    /**
     * 清除会话（用于注销登录）
     */
    public static boolean clearSession(){
        SPUtils.putJSONCache(BaseApplication.getInstance(), USER_INFO, null);
        SPUtils.putBoolean(BaseApplication.getInstance(), IS_LOGIN, false);
        return true;
    }

    /**
     * 判断用户是否登录状态
     */
    public static boolean isLogin(){
        return SPUtils.getBoolean(BaseApplication.getInstance(), IS_LOGIN);
    }

    /**
     * 登录用户信息
     */
    public static User getUser(){
        return JsonUtils.toObject(SPUtils.getJSONCache(BaseApplication.getInstance(),USER_INFO),User.class);
    }

}
