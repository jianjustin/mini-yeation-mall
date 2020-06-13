package org.mini.yeation.mall.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.mini.yeation.mall.domain.Goods;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface GoodsDao {

    @Query("SELECT * FROM goods")
    Flowable<List<Goods>> getAll();

    @Query("SELECT * FROM goods WHERE goods_category = :goodsCategory LIMIT :limit OFFSET :offset")
    Flowable<List<Goods>> getGoodsByCategory(String goodsCategory, int limit, int offset);

    @Query("SELECT count(*) FROM goods")
    Single<Integer> getCount();

    @Query("SELECT count(*) FROM goods WHERE goods_category = :goodsCategory")
    Single<Integer> getCountByCategory(String goodsCategory);

    @Update
    void update(Goods goods);

    @Insert
    void insertAll(Goods... goods);

    @Delete
    void delete(Goods goods);
}
