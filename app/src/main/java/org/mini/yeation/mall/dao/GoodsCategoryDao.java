package org.mini.yeation.mall.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.mini.yeation.mall.domain.GoodsCategory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface GoodsCategoryDao {

    @Query("SELECT * FROM goods_category")
    Flowable<List<GoodsCategory>> getAll();

    @Query("SELECT * FROM goods_category WHERE parent_id = :parentId")
    Flowable<List<GoodsCategory>> getRootCategory(String parentId);

    @Update
    void update(GoodsCategory goodsCategory);

    @Insert
    void insertAll(GoodsCategory... goodsCategories);

    @Delete
    void delete(GoodsCategory goodsCategory);
}
