package org.mini.yeation.mall.fragment.cart;

import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.domain.Cart;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface CartView extends BaseView {

    void setCartList(List<Cart> cartList);

    void setGoodsList(List<Goods> goodsList);
}
