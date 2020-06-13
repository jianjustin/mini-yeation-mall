package org.mini.yeation.mall.domain.base;

/**
 * 商品属性值
 */
public class GoodsSpecificationValue{
    private int specificationValueId;
    private String specificationValueName;

    public int getSpecificationValueId() {
        return specificationValueId;
    }

    public void setSpecificationValueId(int specificationValueId) {
        this.specificationValueId = specificationValueId;
    }

    public String getSpecificationValueName() {
        return specificationValueName;
    }

    public void setSpecificationValueName(String specificationValueName) {
        this.specificationValueName = specificationValueName;
    }
}