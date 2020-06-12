package org.mini.yeation.mall.fragment.goods;

import org.mini.yeation.mall.entity.GoodsCategory;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface GoodsView extends BaseView {

    void setCategoryTitle(List<GoodsCategory> list);

}
