package org.mini.yeation.mall.fragment.goods;

import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.utils.network.RetrofitManager;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class GoodsModel extends BaseModel implements IModel {

    void getTreeCategory(Map<String, Object> params, BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.queryTreeCategory(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
