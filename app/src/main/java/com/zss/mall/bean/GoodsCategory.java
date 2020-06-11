package com.zss.mall.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品目录
 * @author zm
 */
public class GoodsCategory {

    public Long id; //商品目录

    public String name; //目录名称

    public Long parentId; //父节点ID

    public String image; //目录图片

    public Integer level; //级别

    public List<GoodsCategory> list; //子目录

    //=====测试数据========
    public static List<GoodsCategory> initDataList;

    static{
        initDataList = new ArrayList<>();
        GoodsCategory goodsCategory1 = new GoodsCategory();
        goodsCategory1.id = 1L;
        goodsCategory1.name = "居家生活";
        goodsCategory1.parentId = 0L;
        goodsCategory1.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory1.level = 1;
        GoodsCategory goodsCategory2 = new GoodsCategory();
        goodsCategory2.id = 2L;
        goodsCategory2.name = "服饰鞋包";
        goodsCategory2.parentId = 0L;
        goodsCategory2.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory2.level = 1;
        GoodsCategory goodsCategory3 = new GoodsCategory();
        goodsCategory3.id = 3L;
        goodsCategory3.name = "服饰鞋包";
        goodsCategory3.parentId = 1L;
        goodsCategory3.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory3.level = 2;
        GoodsCategory goodsCategory4 = new GoodsCategory();
        goodsCategory4.id = 4L;
        goodsCategory4.name = "服饰鞋包";
        goodsCategory4.parentId = 1L;
        goodsCategory4.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory4.level = 2;
        goodsCategory1.list = new ArrayList<>();
        goodsCategory1.list.add(goodsCategory3);
        goodsCategory1.list.add(goodsCategory4);
        GoodsCategory goodsCategory5 = new GoodsCategory();
        goodsCategory5.id = 5L;
        goodsCategory5.name = "服饰鞋包";
        goodsCategory5.parentId = 2L;
        goodsCategory5.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory5.level = 2;
        GoodsCategory goodsCategory6 = new GoodsCategory();
        goodsCategory6.id = 6L;
        goodsCategory6.name = "服饰鞋包";
        goodsCategory6.parentId = 2L;
        goodsCategory6.image = "https://yanxuan.nosdn.127.net/288d84fe691240d9f87271b293f39b67.png";
        goodsCategory6.level = 2;
        goodsCategory2.list = new ArrayList<>();
        goodsCategory2.list.add(goodsCategory5);
        goodsCategory2.list.add(goodsCategory6);
        initDataList.add(goodsCategory1);
        initDataList.add(goodsCategory2);
    }
}
