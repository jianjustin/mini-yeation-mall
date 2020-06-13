package org.mini.yeation.mall.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import org.mini.yeation.mall.domain.CartGoods;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CartGoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addOrReplaceList(List<CartGoods> cartList);

    @Delete
    void deleteList(List<CartGoods> cart);

    @Query("delete from cart_goods")
    void deleteAll();

    @Query("select * from cart_goods")
    Flowable<List<CartGoods>> queryAll();

    @Query("select count(*) as count from cart_goods")
    Single<Integer> getBadgeCount();

    @Query("select cart_key from cart_goods limit 1")
    Single<String> getCartKey();

}
