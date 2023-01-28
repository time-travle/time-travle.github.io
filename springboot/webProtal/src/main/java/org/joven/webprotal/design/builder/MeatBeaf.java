package org.joven.webprotal.design.builder;

/**
 * 添加肉菜 牛肉
 */
public class MeatBeaf implements Food {
    private int num;

    public MeatBeaf(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.Meat.beaf.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.Meat.beaf.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
