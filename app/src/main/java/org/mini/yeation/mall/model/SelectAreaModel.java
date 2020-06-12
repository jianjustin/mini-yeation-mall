package org.mini.yeation.mall.model;

import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.utils.network.RetrofitManager;

import java.util.Map;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectAreaModel extends BaseModel implements IModel {

    public void queryAreaByLevel(Map<String, Object> params, BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.queryAreaByLevel(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void queryAreaByParentId(Map<String, Object> params, BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.queryAreaByParentId(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
