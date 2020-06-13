package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cart_goods")
public class CartGoods {

    /**
     * 购物车商品ID
     */
    @PrimaryKey
    @ColumnInfo(name = "cart_item_id")
    private Long cartItemId;
    /**
     * 产品ID（用于后续关联SKU）
     */
    @ColumnInfo(name = "product_id")
    private Long productId;
    /**
     * 商品数量
     */
    @ColumnInfo(name = "quantity")
    private int quantity;

    /**
     * 购物车标识 - 用于连接账号
     */
    @ColumnInfo(name = "cart_key")
    private String cartKey;
    /**
     * 商品编号
     */
    @ColumnInfo(name = "goods_id")
    private Long goodsId;
    /**
     * 商品名称
     */
    @ColumnInfo(name = "goods_name")
    private String goodsName;
    /**
     * 商品图片
     */
    @ColumnInfo(name = "image")
    private String image;
    /**
     * 商品价格
     */
    @ColumnInfo(name = "goods_price")
    private String goodsPrice;
    /**
     * 商品规格
     */
    @ColumnInfo(name = "specification_values")
    private String specificationValues;

    private int isSelect;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCartKey() {
        return cartKey;
    }

    public void setCartKey(String cartKey) {
        this.cartKey = cartKey;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(String specificationValues) {
        this.specificationValues = specificationValues;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }
}
