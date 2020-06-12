package org.mini.yeation.mall.fragment.address_edit;


import org.json.JSONObject;
import org.mini.yeation.mall.entity.Area;
import org.mini.yeation.mall.entity.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.utils.network.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressEditPresenter extends BasePresenter<AddressEditModel, AddressEditView> {

    public AddressEditPresenter(AddressEditModel mModel, AddressEditView mView) {
        super(mModel, mView);
    }

    void saveAddress(long areaId, String address, String consignee, String phone, int isDefault) {
        Map<String, Object> params = new HashMap<>();
        params.put("areaId", areaId);
        params.put("address", address);
        params.put("consignee", consignee);
        params.put("phone", phone);
        params.put("isDefault", isDefault);
        getModel().saveAddress(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                getView().saveUpdateSuccess();
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

    void updateAddress(long id, long areaId, String address, String consignee, String phone, int isDefault) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("areaId", areaId);
        params.put("address", address);
        params.put("consignee", consignee);
        params.put("phone", phone);
        params.put("isDefault", isDefault);
        getModel().updateAddress(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                getView().saveUpdateSuccess();
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

    void queryCurrentLevelAreaById(long areaId) {
        Map<String, Object> params = new HashMap<>();
        params.put("areaId", areaId);
        getModel().queryCurrentLevelAreaById(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<Area> areaList = JsonUtils.toList(jsonList, Area.class);
                getView().setSelectAreaList(areaList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

}
