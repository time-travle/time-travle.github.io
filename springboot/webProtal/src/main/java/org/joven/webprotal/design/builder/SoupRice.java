package org.joven.webprotal.design.builder;

/**
 * 添加米汤
 */
public class SoupRice implements Food {
    private int num;

    public SoupRice(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.Soup.rice.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.Soup.rice.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
