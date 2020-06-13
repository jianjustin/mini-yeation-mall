package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 模型 - 地址
 */
@Entity(tableName = "address")
public class Address {

    /**
     * 地址ID
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    /**
     * 详细地址
     */
    @ColumnInfo(name = "address")
    private String address;
    /**
     * 收货人
     */
    @ColumnInfo(name = "consignee")
    private String consignee;
    /**
     * 是否默认 0 否 1是
     */
    @ColumnInfo(name = "isDefault")
    private int isDefault;
    /**
     * 省份ID
     */
    @ColumnInfo(name = "province")
    private int province;
    /**
     * 市 ID
     */
    @ColumnInfo(name = "city")
    private int city;
    /**
     * 区 ID
     */
    @ColumnInfo(name = "district")
    private int district;
    /**
     * 地区名称 四川省,成都市,郫都区
     */
    @ColumnInfo(name = "areaName")
    private String areaName;
    /**
     * 用户手机号码
     */
    @ColumnInfo(name = "username")
    private String username;

    /**
     * 判断是否默认地址
     * @return
     */
    public boolean isDefault(){
        return isDefault == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
