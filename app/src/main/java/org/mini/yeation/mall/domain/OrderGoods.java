package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * 订单商品 - 模型
 */
@Entity(tableName = "order_goods")
public class OrderGoods {
    /**
     * 订单商品ID
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    /**
     * 用户编号
     */
    @ColumnInfo(name = "sn")
    private String sn;
    /**
     * 商品名称
     */
    @ColumnInfo(name = "goods_name")
    private String goodsName;
    /**
     * 商品价格
     */
    @ColumnInfo(name = "goods_price")
    private String goodsPrice;
    /**
     * 商品数量
     */
    @ColumnInfo(name = "quantity")
    private int quantity;
    /**
     * 商品图片
     */
    @ColumnInfo(name = "goods_image")
    private String goodsImage;
    /**
     * 商品重量
     */
    @ColumnInfo(name = "goods_weight")
    private String goodsWeight;

    @ColumnInfo(name = "isComment")
    private String isComment;
    /**
     * 规格属性
     */
    @ColumnInfo(name = "specificationValues")
    private String specificationValues;
    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    private Date createTime;
    /**
     * 订单编号
     */
    @ColumnInfo(name = "order_id")
    private int orderId;
    /**
     * 产品ID
     */
    @ColumnInfo(name = "product_id")
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(String specificationValues) {
        this.specificationValues = specificationValues;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
