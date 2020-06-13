package org.mini.yeation.mall.fragment.address;


import org.json.JSONObject;
import org.mini.yeation.mall.domain.Address;
import org.mini.yeation.mall.utils.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.JsonUtils;

import java.util.List;

public class AddressPresenter extends BasePresenter<AddressModel, AddressView> {

    public AddressPresenter(AddressModel mModel, AddressView mView) {
        super(mModel, mView);
    }

    void queryAddressList() {
        /**
        getModel().queryAddressList(new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<Address> addressList = JsonUtils.toList(jsonList, Address.class);
                getView().setAddressList(addressList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
         **/
    }

}
