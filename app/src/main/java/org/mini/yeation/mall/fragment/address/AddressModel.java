package org.mini.yeation.mall.fragment.address;


import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.utils.network.RetrofitManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddressModel extends BaseModel implements IModel {

    void queryAddressList(BaseResponse observer) {
        RetrofitManager.getInstance().mNetwrokService.queryAddressList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
