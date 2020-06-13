package org.mini.yeation.mall.presenter;


import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.domain.base.GoodsSpecificationValue;
import org.mini.yeation.mall.utils.Event;
import org.mini.yeation.mall.utils.ResultBean;
import org.mini.yeation.mall.model.SelectSpecModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.mini.yeation.mall.domain.CartGoods;
import org.mini.yeation.mall.view.SelectSpecView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SelectSpecPresenter extends BasePresenter<SelectSpecModel, SelectSpecView> {

    public SelectSpecPresenter(SelectSpecModel mModel, SelectSpecView mView) {
        super(mModel, mView);
    }

    /**
     * 保存商品到购物车
     */
    public void saveCartGoods(){

    }

    public void getProductBySpec(Long goodsId, List<GoodsSpecificationValue> specValueList) {
        Map<String, Object> params = new HashMap<>();
        params.put("goodsId", goodsId);
        params.put("specValueList", specValueList);
        /**
        getModel().getProductBySpec(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String json = data.optString("product");
                GoodsProduct product = JsonUtils.toObject(json, GoodsProduct.class);
                getView().setProduct(product);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
         **/
    }

    public void saveCartWithDB(Long productId, int quantity) {
        getModel().getKeyCart(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {}
            @Override
            public void onSuccess(String cartKey) { // 有数据时
                saveCart(cartKey, productId, quantity);
            }

            @Override
            public void onError(Throwable e) { // 没有数据时
                saveCart("", productId, quantity);
            }
        });
    }

    private void saveCart(String cartKey, Long productId, int quantity) {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("quantity", quantity);
        /**
        getModel().saveCart(cartKey, params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<Cart> cartList = new ArrayList<>();
                List<CartItemProduct> cartItemProductList = JsonUtils.toList(jsonList, CartItemProduct.class);
                for (CartItemProduct item : cartItemProductList) {
                    if (!productId.equals(item.productId)) {
                        continue;
                    }
                    Cart cart = new Cart();
                    cart.setCartItemId(item.id);
                    cart.setProductId(item.productId);
                    cart.setQuantity(item.quantity);
                    cart.setCartKey(cartKey);
                    cart.setName(item.name);
                    cart.setImage(item.image);
                    cart.setPrice(item.price);
                    cart.setSpecificationValues(item.specificationValues);
                    cartList.add(cart);
                }
                saveDB(cartList);
            }

            @Override
            public void onError(String errMsg) {

            }
        });
         */
    }

    private void saveDB(List<CartGoods> cartList) {
        Observable.empty()
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        BaseApplication.getInstance().getCartDao().addOrReplaceList(cartList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        EventBus.getDefault().post(new Event.CartEvent());
                        getView().goBack();
                    }
                })
                .subscribe();
    }
}
