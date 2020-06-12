package org.mini.yeation.mall.fragment.select_pay;


import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.utils.network.RetrofitManager;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectPayModel extends BaseModel implements IModel {

    void alipay(Map<String, Object> params, BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.alipay(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
