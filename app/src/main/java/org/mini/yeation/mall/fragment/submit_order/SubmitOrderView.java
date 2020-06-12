package org.mini.yeation.mall.fragment.submit_order;

import org.mini.yeation.mall.entity.Address;
import org.mini.yeation.mall.entity.Order;
import org.mini.yeation.mall.view.BaseView;

public interface SubmitOrderView extends BaseView {

    void setAddress(Address address);

    void goPay(Order order);

}