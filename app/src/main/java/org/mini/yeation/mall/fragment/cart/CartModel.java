package org.mini.yeation.mall.fragment.cart;

import android.arch.lifecycle.LifecycleOwner;


import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.domain.CartGoods;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.presenter.base.IPresenter;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

class CartModel extends BaseModel implements IPresenter, IModel {

    CompositeDisposable mDisposable = new CompositeDisposable();

    void queryGoodsByLike(Map<String, Object> params) {
    }

    void queryAllCart(Consumer<List<CartGoods>> consumer) {
        mDisposable.add(BaseApplication.getInstance()
                .getCartDao()
                .queryAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(consumer)
                .subscribe());
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        mDisposable.clear();
    }
}
