package org.mini.yeation.mall.fragment.cart;


import org.json.JSONObject;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.utils.ResultBean;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.domain.CartGoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

class CartPresenter extends BasePresenter<CartModel, CartView> {

    CartPresenter(CartModel mModel, CartView mView) {
        super(mModel, mView);
    }

    void queryGoodsByLike(long page, long limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        /**
        getModel().queryGoodsByLike(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                JSONObject page = data.optJSONObject("page");
                String jsonList = page.optString("list");
                List<Goods> goodsList = JsonUtils.toList(jsonList, Goods.class);
                getView().setGoodsList(goodsList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
         **/
    }

    void queryAllCart() {
        getModel().queryAllCart(new Consumer<List<CartGoods>>() {
            @Override
            public void accept(List<CartGoods> cartList) throws Exception {
                getView().setCartList(cartList);
            }
        });
    }
}
