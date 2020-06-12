package org.mini.yeation.mall.fragment.submit_order;


import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.utils.network.RetrofitManager;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SubmitOrderModel extends BaseModel implements IModel {

    void getDefaultAddress(BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.getDefaultAddress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    void submitOrder(Map<String, Object> params, BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.submitOrder(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
