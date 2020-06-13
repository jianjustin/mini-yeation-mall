package org.mini.yeation.mall.utils.app;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;


import org.greenrobot.eventbus.EventBus;
import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.entity.Event;
import org.mini.yeation.mall.utils.BigDecimalUtils;
import org.mini.yeation.mall.utils.JsonUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AppUtils {

    public static String fromRMB(String text) {
        BigDecimal decimal = new BigDecimal(10);
        String value = decimal.pow(4).toPlainString();
        return BigDecimalUtils.mul(text, value);
    }

    public static String toRMB(String text) {
        BigDecimal decimal = new BigDecimal(10);
        String value = decimal.pow(4).toPlainString();
        return BigDecimalUtils.div(text, value, 2);
    }

    public static String toRMBFormat(String text) {
        return "￥" + toRMB(text);
    }


    public static String formatPhone(String phone) {
        int position = phone.indexOf(")");
        if (position >= 0) {
            phone = phone.substring(position);
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }


    public static boolean isValidateMobile(String phone) {
        String regex = "1[34578]([0-9]){9}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static void exitLogin() {
        Observable.empty()
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        BaseApplication.getInstance().getCartDao().deleteAll();
                        SPUtils.putJSONCache(BaseApplication.getInstance(), Constants.SP_USER_INFO, "");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        EventBus.getDefault().post(new Event.LoginEvent());
                        EventBus.getDefault().post(new Event.CartEvent());
                    }
                })
                .subscribe();
    }

    public static int getColor(int colorId) {
        return ContextCompat.getColor(BaseApplication.getInstance(), colorId);
    }

    /**
     * 缓存图片
     * @param url
     * @param image
     */
    public static void loadImage(String url, ImageView image){
       // GlideApp.with(BaseApplication.getInstance()).load(Constants.getImageUrl() + url).centerCrop().into(image);
    }

}
