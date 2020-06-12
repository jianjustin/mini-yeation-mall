package org.mini.yeation.mall.fragment.goods_detail;

import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class GoodsDetailModel extends BaseModel implements IModel {

    void getBadgeCount(SingleObserver<Integer> observer) {
        BaseApplication.getInstance()
                .getCartDao()
                .getBadgeCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
