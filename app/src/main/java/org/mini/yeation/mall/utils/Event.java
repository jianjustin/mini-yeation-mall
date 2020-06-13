package org.mini.yeation.mall.utils;

import org.mini.yeation.mall.domain.Address;
import org.mini.yeation.mall.domain.Area;

import java.util.List;

/**
 * 系统事件
 */
public class Event {
    public static class LoginEvent {} //登录事件
    public static class CartEvent {} //购物车事件
    public static class OrderEvent {} //订单事件
    public static class ClearCacheEvent {} //清理缓存
    public static class ReloadAddressEvent {} //重新加载地址事件
    public static class ClickAreaEvent { //点击区域事件
        public Area area;
    }
    public static class SelectAreaEvent { //选择区域事件
        public List<Area> areaList;
    }
    public static class SelectAddressEvent { //选择地址事件
        public Address address;
    }
} 