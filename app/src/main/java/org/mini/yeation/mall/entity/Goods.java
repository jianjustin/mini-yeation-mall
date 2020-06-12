package org.mini.yeation.mall.entity;

import org.mini.yeation.mall.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class Goods {

    public Long id; //商品ID

    public String name; //商品名称

    public String caption; //商品简介

    public String price; //商品价格

    public String image; //商品图片

    public String goodsImages; //商品展示图片

    public String parameterValues; //商品参数

    public String specificationItems; //商品规格

    public List<SpecificationItem> specificationItemList;


    public boolean hasSpecification() {
        if (this.specificationItemList == null) {
            this.specificationItemList = JsonUtils.toList(specificationItems, SpecificationItem.class);
        }
        return this.specificationItemList != null && !this.specificationItemList.isEmpty();
    }

    public static List<Goods> initDataList;

    static{
        Goods goods1 = new Goods();
        goods1.name = "氮化镓PD快充充电器";
        goods1.caption = "黑科技氮化镓加持，难以置信的小巧轻便，配备61W/65W/100W大功率PD快充，为你的生活提提速";
        goods1.image = "https://yanxuan-item.nosdn.127.net/7ebdc283305d39bebe2cc896b43ceee8.png";
        goods1.goodsImages = "[{name:\"pic1\",url:\"https://yanxuan-item.nosdn.127.net/edd1cf69e97a67ffd150fd37d60d90f7.jpg\"}]";
        goods1.price = "100";
        goods1.parameterValues = "保质期:11个月";

        Goods goods2 = new Goods();
        goods2.name = "宠物毛发吸尘器";
        goods2.caption = "小小身材，大爆发力，家庭清扫全面手";
        goods2.image = "https://yanxuan-item.nosdn.127.net/e13a305c1e6d22a865c9a53f08d0ad9c.jpg";
        goods2.goodsImages = "[{name:\"pic1\",url:\"https://yanxuan-item.nosdn.127.net/edd1cf69e97a67ffd150fd37d60d90f7.jpg\"}]";
        goods2.price = "120";
        goods2.parameterValues = "保质期:12个月";

        initDataList = new ArrayList<>();
        initDataList.add(goods1);
        initDataList.add(goods2);
    }

}
