package org.mini.yeation.mall.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

/**
 * 订单 - 模型
 */
@Entity(tableName = "order")
public class Order {
    /**
     * 订单编号
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    /**
     * 关联用户编号
     */
    @ColumnInfo(name = "sn")
    private String sn;
    /**
     * 收货人
     */
    @ColumnInfo(name = "consignee")
    private String consignee;
    /**
     * 省市区信息
     */
    @ColumnInfo(name = "area_name")
    private String areaName;
    /**
     * 详细地址
     */
    @ColumnInfo(name = "address")
    private String address;
    /**
     * 收货手机号码
     */
    @ColumnInfo(name = "phone")
    private String phone;
    /**
     * 订单金额
     */
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "offset_amount")
    private String offsetAmount;
    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    private Date createTime;
    /**
     * 订单过期时间
     */
    @ColumnInfo(name = "expire")
    private Date expire;
    @ColumnInfo(name = "exchange_point")
    private String exchangePoint;
    @ColumnInfo(name = "freight")
    private String freight;
    /**
     * 发票抬头
     */
    @ColumnInfo(name = "invoice_title")
    private String invoiceTitle;
    /**
     * 发票内容
     */
    @ColumnInfo(name = "invoice_content")
    private String invoiceContent;
    /**
     * 订单数量
     */
    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "memo")
    private String memo;
    /**
     * 订单状态
     */
    @ColumnInfo(name = "status")
    private int status;
    /**
     * 用户编号
     */
    @ColumnInfo(name = "member_id")
    private int memberId;

    public List<OrderGoods> list;

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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(String offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(String exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
