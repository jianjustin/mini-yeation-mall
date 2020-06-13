package org.mini.yeation.mall.domain.base;

import org.mini.yeation.mall.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性
 */
public class GoodsSpecification{
    private int specificationId;
    private String specificationName;
    //商品属性对应值列表
    private List<GoodsSpecificationValue> goodsSpecificationValueList;

    /**
     * 获取规格值信息
     * @param specificationValue
     * @return
     */
    public static String getSelectSpecValue(String specificationValue) {
        if (specificationValue == null) return "默认";
        List<GoodsSpecificationValue> specValueList = JsonUtils.toList(specificationValue, GoodsSpecificationValue.class);
        StringBuilder specValues = new StringBuilder();
        assert specValueList != null;
        for (GoodsSpecificationValue item : specValueList) {
            specValues.append(",");
            specValues.append(item.getSpecificationValueName());
        }
        return specValues.substring(1);
    }

    /**
     * 获取规格信息 - 例如：规格1,规格2,规格3
     * @param specificationItem
     * @return
     */
    public static String getSelectSpecItem(String specificationItem) {
        if (specificationItem == null) return "规格";
        List<GoodsSpecification> specValueList = JsonUtils.toList(specificationItem, GoodsSpecification.class);
        StringBuilder specValues = new StringBuilder();
        assert specValueList != null;
        for (GoodsSpecification item : specValueList) {
            specValues.append(",");
            specValues.append(item.getSpecificationName());
        }
        return specValues.substring(1);
    }

    /**
     * 根据属性值列表获取商品属性值标签（用于展示商品属性值）
     * @param item
     * @return
     */
    public static List<String> getGoodsSpecificationTagList(GoodsSpecification item){
        List<String> mTagList = new ArrayList<>();
        for (GoodsSpecificationValue spec : item.getGoodsSpecificationValueList())
            mTagList.add(spec.getSpecificationValueName());
        return mTagList;
    }

    /**
     * 生成默认规格信息
     * @return
     */
    public static GoodsSpecification createDefaultGoodsSpecification(){
        GoodsSpecification goodsSpecification = new GoodsSpecification();
        goodsSpecification.setSpecificationId(1);
        goodsSpecification.setSpecificationName("规格");

        List<GoodsSpecificationValue> goodsSpecificationValueList1 = new ArrayList<>();
        GoodsSpecificationValue defaultValue = new GoodsSpecificationValue();
        defaultValue.setSpecificationValueId(1);
        defaultValue.setSpecificationValueName("默认");
        goodsSpecificationValueList1.add(defaultValue);
        goodsSpecification.setGoodsSpecificationValueList(goodsSpecificationValueList1);

        return goodsSpecification;
    }

    public int getSpecificationId() {
        return specificationId;
    }
    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }
    public String getSpecificationName() {
        return specificationName;
    }
    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
    public List<GoodsSpecificationValue> getGoodsSpecificationValueList() {
        return goodsSpecificationValueList;
    }
    public void setGoodsSpecificationValueList(List<GoodsSpecificationValue> goodsSpecificationValueList) {
        this.goodsSpecificationValueList = goodsSpecificationValueList;
    }
}
