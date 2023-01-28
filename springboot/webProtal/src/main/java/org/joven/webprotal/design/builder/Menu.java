package org.joven.webprotal.design.builder;

/**
 * 菜单名称
 */
public class Menu {
    // 菜品名称
    private String name;
    // 菜品数量
    private int num;
    // 菜品价格
    private float singlePrice;
    // 菜品打包方式
    private String packType;

    public Menu() {
    }

    public Menu(String name, int num, float singlePrice, String packType) {
        this.num = num;
        this.name = name;
        this.packType = packType;
        this.singlePrice = singlePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(float price) {
        this.singlePrice = price;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }


}
