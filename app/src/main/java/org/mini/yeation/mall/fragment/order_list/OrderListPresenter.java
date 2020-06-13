package org.mini.yeation.mall.fragment.order_list;


import org.json.JSONObject;
import org.mini.yeation.mall.domain.Order;
import org.mini.yeation.mall.utils.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListPresenter extends BasePresenter<OrderListModel, OrderListView> {

    public OrderListPresenter(OrderListModel mModel, OrderListView mView) {
        super(mModel, mView);
    }

    void queryOrderList(int status, int page, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("page", page);
        params.put("limit", limit);
        /**
        getModel().queryOrderByStatus(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                JSONObject page = data.optJSONObject("page");
                String jsonList = page.optString("list");
                long currPage = page.optLong("currPage");
                long totalPage = page.optLong("totalPage");
                List<Order> orderList = JsonUtils.toList(jsonList, Order.class);
                getView().setOrderList(currPage, totalPage, orderList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
         **/
    }

}
