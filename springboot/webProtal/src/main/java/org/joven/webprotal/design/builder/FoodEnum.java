package org.joven.webprotal.design.builder;

/**
 * 食物枚举类
 */
public interface FoodEnum {
    enum PackType {
        box("盒子", "5毛一个"), Wrapper("袋子", "2毛一个"), noPack("不要", "不要钱");
        public String value;
        public String desc;

        PackType(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    enum MainFood {
        rice("米饭",2), bread("馒头",1);

        public String desc;
        public float price;
        MainFood(String desc,float price) {
            this.price = price;
            this.desc = desc;
        }
    }

    enum Soup {
        rice("米汤",20), xiaomi("小米粥",2), nanGua("南瓜粥",2);
        public String desc;
        public float price;
        Soup(String desc,float price) {
            this.desc = desc; this.price = price;
        }
    }

    enum Vegetable {
        tudou("土豆",2), fanque("番茄",4), qincai("芹菜",3);
        public String desc;
        public float price;
        Vegetable(String desc,float price) {
            this.desc = desc; this.price = price;
        }
    }

    enum Meat {
        beaf("牛肉",12), sheep("羊肉",14), pig("猪肉",10);
        public String desc;
        public float price;
        Meat(String desc,float price) {
            this.desc = desc; this.price = price;
        }
    }
}
