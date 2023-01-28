package org.joven.webprotal.design.builder;

/**
 * 添加肉菜 牛肉
 */
public class MeatSheep implements Food {
    private int num;

    public MeatSheep(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.Meat.sheep.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.Meat.sheep.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
