package org.joven.webprotal.design.factory.factorymethod;

/**
 * 添加一革抽象类将做一下过滤使得实现类不用全部的实现接口的方法
 */
public abstract class AbstractCar implements Car {
    public String getCarName() {
        System.out.println("未真正实现");
        return "";
    }

    public void runCar() {
        System.out.println("未真正实现");
    }

    public void stopCar() {
        System.out.println("未真正实现");
    }

    public void addOil() {
        System.out.println("未真正实现");
    }

    public void openDoor() {
        System.out.println("未真正实现");
    }

    public void closeDoor() {
        System.out.println("未真正实现");
    }
}
