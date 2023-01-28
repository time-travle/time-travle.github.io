package org.joven.webprotal.design.builder;

/**
 * 添加馒头
 */
public class Bread implements Food {
    private int num;

    public Bread(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.MainFood.bread.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.MainFood.bread.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.Wrapper.value;
    }
}
