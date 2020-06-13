package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 模型 - 商品
 */
@Entity(tableName = "goods")
public class Goods {
    /**
     * 商品ID
     */
    @PrimaryKey
    @ColumnInfo(name = "goods_id")
    private int goodsId;
    /**
     * 商品分类ID
     */
    @ColumnInfo(name = "goods_category")
    private int goodsCategory;
    /**
     * 商品名称
     */
    @ColumnInfo(name = "goods_name")
    private String goodsName;
    /**
     * 商品简介
     */
    @ColumnInfo(name = "goods_caption")
    private String goodsCaption;
    /**
     * 商品价格
     */
    @ColumnInfo(name = "goods_price")
    private String goodsPrice;
    /**
     * 商品主图
     */
    @ColumnInfo(name = "image")
    private String image;
    /**
     * 商品展示图片组
     */
    @ColumnInfo(name = "goods_images")
    private String goodsImages;
    /**
     * 商品参数
     */
    @ColumnInfo(name = "goods_parameter")
    private String parameterValues;
    /**
     * 商品规格
     */
    @ColumnInfo(name = "goods_specification")
    private String goodsSpecification;


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCaption() {
        return goodsCaption;
    }

    public void setGoodsCaption(String goodsCaption) {
        this.goodsCaption = goodsCaption;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public String getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(String parameterValues) {
        this.parameterValues = parameterValues;
    }
    public int getGoodsCategory() {
        return goodsCategory;
    }
    public void setGoodsCategory(int goodsCategory) {
        this.goodsCategory = goodsCategory;
    }
    public String getGoodsSpecification() {
        return goodsSpecification;
    }
    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }
}
