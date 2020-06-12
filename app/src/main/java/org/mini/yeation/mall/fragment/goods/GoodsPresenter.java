package org.mini.yeation.mall.fragment.goods;


import org.json.JSONObject;
import org.mini.yeation.mall.entity.GoodsCategory;
import org.mini.yeation.mall.entity.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.utils.network.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GoodsPresenter extends BasePresenter<GoodsModel, GoodsView> {

    GoodsPresenter(GoodsModel mModel, GoodsView mView) {
        super(mModel, mView);
    }

    void queryTreeCategory() {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", "1");
        getModel().getTreeCategory(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<GoodsCategory> categoryList = JsonUtils.toList(jsonList, GoodsCategory.class);
                getView().setCategoryTitle(categoryList);
            }
            @Override
            public void onError(String errMsg) {
                getView().setCategoryTitle(GoodsCategory.initDataList);
            }
        });
    }
}