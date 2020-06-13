package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "goods_category")
public class GoodsCategory {

    /**
     * 商品分类
     */
    @PrimaryKey
    @ColumnInfo(name = "categoryid")
    private int categoryid;

    /**
     * 目录名称
     */
    @ColumnInfo(name = "category_name")
    private String categoryName;

    /**
     * 父分类
     */
    @ColumnInfo(name = "parent_id")
    private int parentId;

    /**
     * 分类图片
     */
    @ColumnInfo(name = "image")
    private String image;

    /**
     * 分类级别
     */
    @ColumnInfo(name = "level")
    private int level;

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
