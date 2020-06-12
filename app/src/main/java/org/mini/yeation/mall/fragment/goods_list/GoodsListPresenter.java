package org.mini.yeation.mall.fragment.goods_list;


import org.mini.yeation.mall.entity.Goods;
import org.mini.yeation.mall.entity.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;

import org.json.JSONObject;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.utils.network.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GoodsListPresenter extends BasePresenter<GoodsListModel, GoodsListView> {

    GoodsListPresenter(GoodsListModel mModel, GoodsListView mView) {
        super(mModel, mView);
    }

    void queryGoodsByCategory(Long categoryId, int level, int page, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("level", level);
        params.put("page", page);
        params.put("limit", limit);
        getModel().queryGoodsByCategory(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                JSONObject page = data.optJSONObject("page");
                String jsonList = page.optString("list");
                long currPage = page.optLong("currPage");
                long totalPage = page.optLong("totalPage");
                List<Goods> goodsList = JsonUtils.toList(jsonList, Goods.class);
                getView().setGoodsList(currPage, totalPage, goodsList);
            }

            @Override
            public void onError(String errMsg) {
                List<Goods> goodsList = Goods.initDataList;
                getView().setGoodsList(1, 1, goodsList);
            }
        });
    }
}
