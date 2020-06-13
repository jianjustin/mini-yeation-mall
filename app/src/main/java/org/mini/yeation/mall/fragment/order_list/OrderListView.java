package org.mini.yeation.mall.fragment.order_list;

import org.mini.yeation.mall.domain.Order;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface OrderListView extends BaseView {

    void setOrderList(long currPage, long totalPage, List<Order> orderList);
}
