package org.joven.webprotal.design.factory.factorymethod;

/**
 * 建立车的工厂类
 */
public class CarFactory {
    private CarFactory(){}
    public static Car getCar(CarTypeEnum carType) {
        Car car = null;
        if (carType ==null){
            return car;
        }
        if ("TRUCK".equals(carType.name())) {
            car = new TruckCar("卡车");
        } else if ("AUDI".equals(carType.name())) {
            car = new AudiCar("奥迪");
        } else {
            car = new DefaultCar("还未起名");
        }
        return car;
    }
}
