package org.joven.webprotal.design.builder;

/**
 * 添加小米汤
 */
public class SoupXiaomi implements Food {
    private int num;

    public SoupXiaomi(int num) {
        this.num = num;
    }

    @Override
    public String getName() {
        return FoodEnum.Soup.xiaomi.desc;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public float getSinglePrice() {
        return FoodEnum.Soup.xiaomi.price;
    }

    @Override
    public String getPackType() {
        return FoodEnum.PackType.box.value;
    }
}
