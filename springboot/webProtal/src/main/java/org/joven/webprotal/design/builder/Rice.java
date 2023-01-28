package org.joven.webprotal.design.builder;

/**
 * 添加大米饭
 */
public class Rice implements Food {
    private int num;

    public Rice(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.MainFood.rice.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.MainFood.rice.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
