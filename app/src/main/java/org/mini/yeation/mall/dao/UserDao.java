package org.mini.yeation.mall.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.mini.yeation.mall.domain.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * 用户DAO接口
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Flowable<User> getAll();

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    Single<User> findByName(String username, String password);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
