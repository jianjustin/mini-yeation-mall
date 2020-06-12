package org.mini.yeation.mall.utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import org.mini.yeation.mall.dao.CartDao;
import org.mini.yeation.mall.dao.UserDao;
import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.entity.Cart;

/**
 * 基于room抽象层的数据库初始化
 */
@Database(entities = {Cart.class, User.class}, version = 1)
public abstract class AppDatabaseHelper extends RoomDatabase {

    private static volatile AppDatabaseHelper database;

    public static AppDatabaseHelper getInstance(final Context context) {
        if (database == null) {
            synchronized (AppDatabaseHelper.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseHelper.class, "mallDB")
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    db.execSQL("insert into user (userid, username, password) values (1, '13800000000', '123456')");
                                }
                            }).build();
                }
            }
        }
        return database;
    }

    public abstract CartDao getCartDao();
    public abstract UserDao getUserDao();

}
