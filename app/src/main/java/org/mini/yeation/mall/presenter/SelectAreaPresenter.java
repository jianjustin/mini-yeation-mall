package org.mini.yeation.mall.presenter;

import org.mini.yeation.mall.entity.Area;
import org.mini.yeation.mall.entity.ResultBean;
import org.mini.yeation.mall.model.SelectAreaModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;

import org.json.JSONObject;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.utils.network.BaseResponse;
import org.mini.yeation.mall.view.SelectAreaView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectAreaPresenter extends BasePresenter<SelectAreaModel, SelectAreaView> {

    public SelectAreaPresenter(SelectAreaModel mModel, SelectAreaView mView) {
        super(mModel, mView);
    }

    public void queryAreaByLevel(int level) {
        Map<String, Object> params = new HashMap<>();
        params.put("level", level);
        getModel().queryAreaByLevel(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<Area> areaList = JsonUtils.toList(jsonList, Area.class);
                getView().setLevelAreaList(areaList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

    public void queryAreaByParentId(long parentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        getModel().queryAreaByParentId(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<Area> areaList = JsonUtils.toList(jsonList, Area.class);
                getView().setParentAreaList(areaList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

}
