package org.mini.yeation.mall.model;


import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;

import java.util.Map;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectSpecModel extends BaseModel implements IModel {

    public void getProductBySpec(Map<String, Object> params) {
    }

    public void getKeyCart(SingleObserver<String> observer) {
        BaseApplication.getInstance()
                .getCartDao()
                .getCartKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void saveCart(String cartKey, Map<String, Object> params) {
    }

}
