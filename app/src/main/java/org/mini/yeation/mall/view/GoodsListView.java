package org.mini.yeation.mall.view;

import org.mini.yeation.mall.domain.Goods;

import java.util.List;

public interface GoodsListView extends BaseView {

    void setGoodsList(long currPage, long totalPage, List<Goods> goodsList);

}
