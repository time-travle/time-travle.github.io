package org.joven.webprotal.design.factory.factorymethod;

/**
 * 添加卡车实现接口
 */
public class TruckCar extends AbstractCar{
    private String name = "Trunk Car";
    public TruckCar(String name){
        this.name = name;
    }
    @Override
    public void runCar(){
        System.out.println("开动卡车："+name);
    }
    @Override
    public String getCarName() {
        return this.name;
    }
    @Override
    public void openDoor() {
        System.out.println("上个人");
    }
}
