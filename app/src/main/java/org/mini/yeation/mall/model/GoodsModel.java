package org.mini.yeation.mall.model;

import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.dao.GoodsCategoryDao;
import org.mini.yeation.mall.domain.GoodsCategory;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.utils.AppDatabaseHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GoodsModel extends BaseModel implements IModel {

    private final GoodsCategoryDao goodsCategoryDao = AppDatabaseHelper.getInstance(BaseApplication.getInstance()).getGoodsCategoryDao();

    /**
     * 获取根分类
     * @param params
     * @param success
     * @param error
     */
    public void queryRootCategory(Map<String,Object> params, Consumer<List<GoodsCategory>> success, Consumer<Throwable> error){
        goodsCategoryDao.getRootCategory("-1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success,error);
    }

}
