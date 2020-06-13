package org.mini.yeation.mall.presenter;

import org.mini.yeation.mall.model.GoodsModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.view.GoodsView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class GoodsPresenter extends BasePresenter<GoodsModel, GoodsView> {

    public GoodsPresenter(GoodsModel mModel, GoodsView mView) {
        super(mModel, mView);
    }

    /**
     * 获取根分类
     */
    public void queryRootCategory(){
        Map<String, Object> params = new HashMap<>();
        getModel().queryRootCategory(params, new Consumer<List<org.mini.yeation.mall.domain.GoodsCategory>>() {
            @Override
            public void accept(List<org.mini.yeation.mall.domain.GoodsCategory> goodsCategories) throws Exception {
                getView().setCategoryTitle(goodsCategories);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }
}