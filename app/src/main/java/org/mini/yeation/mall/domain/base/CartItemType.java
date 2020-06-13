package org.mini.yeation.mall.domain.base;

import org.mini.yeation.mall.domain.CartGoods;

public class CartItemType {

    private CartGoods cart; //购物车

    private ItemType itemType; //列表类型

    public enum ItemType {

        ItemTypeEmpty(1), //空列表
        ItemTypeCart(2);  //购物车

        int value;

        ItemType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public CartItemType(CartGoods cart) {
        this.cart = cart;
        this.itemType = ItemType.ItemTypeCart;
    }

    public CartItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public CartGoods getCart() {
        return cart;
    }

    public void setCart(CartGoods cart) {
        this.cart = cart;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
