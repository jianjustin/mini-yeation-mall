package org.mini.yeation.mall.model;

import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.dao.GoodsDao;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.AppDatabaseHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GoodsListModel extends BaseModel implements IModel {

    private final GoodsDao goodsDao = AppDatabaseHelper.getInstance(BaseApplication.getInstance()).getGoodsDao();

    /**
     * 根据分类ID获取商品列表
     * @param params
     * @param success
     * @param error
     */
    public void queryGoodsByCategory(Map<String, Object> params, Consumer<List<Goods>> success, Consumer<Throwable> error) {
        String categoryId = params.get("categoryId").toString();
        int limit = new Integer(params.get("limit").toString()), offset = new Integer(params.get("offset").toString());

        goodsDao.getGoodsByCategory(categoryId,limit,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success,error);
    }

    /**
     * 获取该分类下商品总数
     * @param params
     * @param success
     * @param error
     */
    public void queryCountByCategory(Map<String, Object> params, Consumer<Integer> success, Consumer<Throwable> error){
        goodsDao.getCountByCategory(params.get("categoryId").toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success,error);
    }

}
