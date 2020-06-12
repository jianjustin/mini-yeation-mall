package org.mini.yeation.mall.fragment.goods_list;

import org.mini.yeation.mall.entity.Goods;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface GoodsListView extends BaseView {

    void setGoodsList(long currPage, long totalPage, List<Goods> goodsList);

}
