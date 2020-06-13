package org.mini.yeation.mall.presenter;

import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.model.GoodsListModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.view.GoodsListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class GoodsListPresenter extends BasePresenter<GoodsListModel, GoodsListView> {

    public GoodsListPresenter(GoodsListModel mModel, GoodsListView mView) {
        super(mModel, mView);
    }

    /**
     * 根据分类ID获取对应的商品列表
     * @param categoryId
     * @param page
     * @param limit
     */
    public void queryGoodsByCategory(int categoryId, int page, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("page",page);
        params.put("offset", (page-1)*limit);//获取起始位置
        params.put("limit", limit);//每页数量

        getModel().queryGoodsByCategory(params, new Consumer<List<Goods>>() {
            @Override
            public void accept(List<Goods> goods) throws Exception {
                queryCountByCategory(params,goods);//获取分类下商品数量
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

    }

    public void queryCountByCategory( Map<String, Object> params,List<Goods> goodsList){
        getModel().queryCountByCategory(params, new Consumer<Integer>() {
            @Override
            public void accept(Integer total) throws Exception {
                int page = Integer.valueOf(params.get("page").toString());
                int limit = Integer.valueOf(params.get("limit").toString());
                //根据分页信息和商品数据生成页面
                getView().setGoodsList(page, total/limit, goodsList);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }


}
