package org.joven.webprotal.design.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 做外卖
 */
public class BuildOutFood {
    // 一次点单一个列表
    List<Menu> menuList = new ArrayList<Menu>();

    private BuildOutFood() {
    }

    public static BuildOutFood getInstance() {
        return new BuildOutFood();
    }

    public void addOneOrder(Menu menu) {
        menuList.add(menu);
    }

    public void removeOneOrder(Menu menu) {
        menuList.remove(menu);
    }

    public float getTotalPrice() {
        float total = 0l;
        for (Menu menu : menuList) {
            total += (menu.getSinglePrice()* menu.getNum());
        }
        return total;
    }

    public Map<String, Object> showMenuInfo() {
        Map<String, Object> res = new HashMap<String, Object>();
        for (final Menu menu : menuList) {
            res.put(menu.getName(), new HashMap<String, Object>() {
                {
                    put("数量", menu.getNum());
                    put("单价", menu.getSinglePrice());
                    put("打包方式", menu.getPackType());
                }
            });
        }
        System.out.println("序号\t\t菜品\t\t单价\t\t数量\t\t打包方式");
        int order =1;
        for (Map.Entry<String, Object> entry : res.entrySet()) {
            String mapKey = entry.getKey();
            System.out.print(order+++"\t\t"+mapKey + "\t\t");
            Map<String, Object> mapValue = (Map<String, Object>) entry.getValue();
            for(String key : mapValue.keySet()){
                Object value = mapValue.get(key);
                System.out.print(value+"\t\t");
            }
            System.out.println();
        }
        return res;
    }


    List<Food> foodList = new ArrayList<Food>();
    public void addOneFoodOrder(Food food) {
        foodList.add(food);
    }
    public void removeOneFood(Food food) {
        foodList.remove(food);
    }

    public float getFoodTotalPrice() {
        float total = 0l;
        for (Food food : foodList) {
            total += (food.getSinglePrice()* food.getNum());
        }
        return total;
    }

    public Map<String, Object> showFoodsInfo() {
        Map<String, Object> res = new HashMap<String, Object>();
        for (final Food food : foodList) {
            res.put(food.getName(), new HashMap<String, Object>() {
                {
                    put("数量", food.getNum());
                    put("单价", food.getSinglePrice());
                    put("打包方式", food.getPackType());
                }
            });
        }
        System.out.println("序号\t\t\t菜品\t\t\t单价\t\t\t数量\t\t\t打包方式");
        int order =1;
        for (Map.Entry<String, Object> entry : res.entrySet()) {
            String mapKey = entry.getKey();
            System.out.print(order+++"\t\t\t"+mapKey + "\t\t\t");
            Map<String, Object> mapValue = (Map<String, Object>) entry.getValue();
            for(String key : mapValue.keySet()){
                Object value = mapValue.get(key);
                System.out.print(value+"\t\t\t");
            }
            System.out.println();
        }
        return res;
    }
}
