package org.joven.webprotal.design.builder;

/**
 * 添加肉菜 牛肉
 */
public class MeatPig implements Food {
    private int num;

    public MeatPig(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.Meat.pig.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.Meat.pig.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
