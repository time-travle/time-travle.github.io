package org.joven.webprotal.design;

import org.joven.webprotal.design.adapter.SchoolMaster;
import org.joven.webprotal.design.bridge.ArtClass;
import org.joven.webprotal.design.bridge.HighSchoolTeacher;
import org.joven.webprotal.design.bridge.ScienceClass;
import org.joven.webprotal.design.bridge.TeacherClass;
import org.joven.webprotal.design.builder.*;
import org.joven.webprotal.design.factory.abstractfactory.*;
import org.joven.webprotal.design.factory.factorymethod.Car;
import org.joven.webprotal.design.factory.factorymethod.CarFactory;
import org.joven.webprotal.design.factory.factorymethod.CarTypeEnum;
import org.joven.webprotal.design.observer.UserObserver;
import org.joven.webprotal.design.observer.WechatServer;
import org.joven.webprotal.design.singleton.Singleton;
import org.junit.Test;

/**
 * 设计模式简单测试类
 */
public class DesignTest {

    @Test
    public void testBridge(){
        System.out.println("===========================================");
        TeacherClass teacher = new HighSchoolTeacher("王老师");
        teacher.setStuClass(new ArtClass("艺术班"));
        teacher.handleClass("高一");
        teacher.handleMeeting("高二");
        System.out.println("===========================================");
        TeacherClass teacher2 = new HighSchoolTeacher("张老师");
        teacher2.setStuClass(new ScienceClass("科技班"));
        teacher2.handleClass("高三");
        teacher2.handleMeeting("高四");
        System.out.println("===========================================");
    }


    // 客户只需要调用传入值 后面去哪个类查询 不用关注 且后面改了客户端也不知道
    @Test
    public void testAdapter() {
        SchoolMaster sc = new SchoolMaster("陈校");
        sc.getInfo("Stu","stu","李四");
        sc.getInfo("Stu","class","李四");

        sc.getInfo("tea","math","李四");
        sc.getInfo("tea","chinese","李四");
        sc.getInfo("tea","chemistry","李四");
        sc.getInfo("tea","","李四");
    }
    // 根据需要动态添加新的组成
    @Test
    public void testBuilder() {
        BuildOutFood order = BuildOutFood.getInstance();
        // 第一种方式 若想添加菜品直接通过new Menu即可 价格啥的比较活动自由  但是若是想为为一中菜品添加单独的属性时 需要改动主类
        order.addOneOrder(new Menu(FoodEnum.MainFood.rice.desc, 3, FoodEnum.MainFood.rice.price, FoodEnum.PackType.Wrapper.value));
        order.addOneOrder(new Menu(FoodEnum.MainFood.bread.desc, 4, FoodEnum.MainFood.bread.price, FoodEnum.PackType.Wrapper.value));
        System.out.println(order.getTotalPrice());
        order.showMenuInfo();

        // 第二种方式 想添加一个菜品时直接继承Food后实现即可 新增菜品比较独立不影响现有的 若是想修改一类菜品直接改对应的菜品就可以其他的不用动
        order.addOneFoodOrder(new MeatBeaf(3));
        order.addOneFoodOrder(new MeatPig(2));
        order.addOneFoodOrder(new SoupRice(2));
        System.out.println(order.getFoodTotalPrice());
        order.showFoodsInfo();
    }

    public static void main(String[] args) {
        testSingleton();
    }

    public static void testSingleton() {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Singleton.getInstance().getTime();
                    System.out.println(Thread.currentThread().getName());
                }
            };
            thread.setName("线层" + (i + 1));
            thread.start();
        }
    }

    @Test
    public void testAbstractFactory() {
        AbstractFactory factoryS = FactoryProd.getFactory("S");
        AbstractFactory factoryP = FactoryProd.getFactory("P");
        People stu = factoryP.getPeopleInfo(EnumParam.People.Student);
        System.out.println(stu.getName());

        System.out.println(factoryS.getSchoolInfo(EnumParam.School.Colleague).getSchoolName());
        School s = factoryS.getSchoolInfo(EnumParam.School.High);

        System.out.println(s.getSchoolName());
    }

    @Test
    public void testFactoryMethod() {
        Car audi = CarFactory.getCar(CarTypeEnum.AUDI);
        Car truck = CarFactory.getCar(CarTypeEnum.TRUCK);
        Car other = CarFactory.getCar(null);

        audi.runCar();
        audi.addOil();
        audi.openDoor();

        truck.runCar();
        truck.addOil();
        truck.openDoor();
    }

    @Test
    public void testObserver() {
        // 定义一个被观察者
        WechatServer server = new WechatServer();

        // 定义被观察者
        UserObserver user1 = new UserObserver("观察者一号");
        UserObserver user2 = new UserObserver("观察者二号");
        UserObserver user3 = new UserObserver("观察者三号");

        // 添加观察者 到被观察的列表中
        server.registerObserver(user1);
        server.registerObserver(user2);
        server.registerObserver(user3);
        // 更新被观察对象
        server.updateMessage("开始 通知一个");
        //移除一个观察者
        server.removeObserver(user3);
        // 更新被观察对象
        server.updateMessage("开始 通知二个");
    }
}
