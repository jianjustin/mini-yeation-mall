package org.mini.yeation.mall.domain.base;

import org.mini.yeation.mall.domain.Goods;

import java.util.List;

/**
 * 产品（根据商品和规格信息生成）
 */
public class GoodsProduct {
    // 规格产品ID
    private int id;
    // 商品编号
    private String sn;
    // 销售价
    private String price;
    // 市场价
    private String marketPrice;
    // 成本价
    private String cost;
    // 已分配库存
    private Integer allocatedStock;
    // 当前库存
    private Integer stock;
    // 赠送积分
    private long givePoints;
    // 是否默认
    private Integer isDefault;
    // 商品规格
    private String specificationValues;
    // 商品ID
    private int goodsId;

    /**
     * 根据商品信息和规格信息生成产品
     * @param goods
     * @param specValueList
     * @return
     */
    public static GoodsProduct getGoodsProduct(Goods goods, List<GoodsSpecificationValue> specValueList){
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.id = goodsProduct.goodsId = goods.getGoodsId();
        goodsProduct.sn = String.valueOf(goods.getGoodsId());
        goodsProduct.price = goodsProduct.marketPrice = goods.getGoodsPrice();
        return goodsProduct;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getAllocatedStock() {
        return allocatedStock;
    }

    public void setAllocatedStock(Integer allocatedStock) {
        this.allocatedStock = allocatedStock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public long getGivePoints() {
        return givePoints;
    }

    public void setGivePoints(long givePoints) {
        this.givePoints = givePoints;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(String specificationValues) {
        this.specificationValues = specificationValues;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
