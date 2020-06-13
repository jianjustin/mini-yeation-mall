package org.mini.yeation.mall.utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import org.mini.yeation.mall.dao.CartGoodsDao;
import org.mini.yeation.mall.dao.GoodsCategoryDao;
import org.mini.yeation.mall.dao.GoodsDao;
import org.mini.yeation.mall.dao.UserDao;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.domain.GoodsCategory;
import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.domain.CartGoods;

/**
 * 基于room抽象层的数据库初始化
 */
@Database(entities = {CartGoods.class, User.class, GoodsCategory.class, Goods.class}, version = 1)
public abstract class AppDatabaseHelper extends RoomDatabase {

    private static volatile AppDatabaseHelper database;

    public static AppDatabaseHelper getInstance(final Context context) {
        if (database == null) {
            synchronized (AppDatabaseHelper.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseHelper.class, "mallDB2")
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    /*初始化内部数据*/

                                    //初始化用户
                                    db.execSQL("insert into user (userid, username, password) values (1, '13800000000', '123456')");

                                    //初始化分类
                                    db.execSQL("insert into goods_category (categoryid, category_name, parent_id, image, level) values (1, '美食酒水', '-1', '', 1)");
                                    db.execSQL("insert into goods_category (categoryid, category_name, parent_id, image, level) values (2, '服饰鞋包', '-1', '', 1)");
                                    db.execSQL("insert into goods_category (categoryid, category_name, parent_id, image, level) values (3, '居家生活', '-1', '', 1)");
                                    db.execSQL("insert into goods_category (categoryid, category_name, parent_id, image, level) values (4, '个护清洁', '-1', '', 1)");

                                    //初始化商品数据
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (1, 1, '酸梅汤', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (2, 1, '酸梅汤1', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (3, 1, '酸梅汤2', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (4, 1, '酸梅汤3', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (5, 1, '酸梅汤4', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (6, 1, '酸梅汤5', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (7, 1, '酸梅汤6', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (8, 1, '酸梅汤7', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (9, 2, '酸梅汤8', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (10, 2, '酸梅汤9', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (11, 2, '酸梅汤10', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (12, 2, '酸梅汤11', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (13, 2, '酸梅汤12', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (14, 3, '酸梅汤13', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (15, 3, '酸梅汤14', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                    db.execSQL("insert into goods (goods_id, goods_category, goods_name, goods_caption, goods_price, image, goods_images, goods_parameter, goods_specification) values (16, 3, '酸梅汤15', '传统配方，宫廷味道', '250000','1.png','[]','保质期：1个月',null)");
                                }
                            }).build();
                }
            }
        }
        return database;
    }

    public abstract CartGoodsDao getCartGoodsDao();
    public abstract UserDao getUserDao();
    public abstract GoodsCategoryDao getGoodsCategoryDao();
    public abstract GoodsDao getGoodsDao();

}
