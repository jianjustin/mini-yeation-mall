package org.mini.yeation.mall.domain.base;

public class GoodsTag {

    private int id;

    private String name;

    public GoodsTag(){}

    public GoodsTag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
