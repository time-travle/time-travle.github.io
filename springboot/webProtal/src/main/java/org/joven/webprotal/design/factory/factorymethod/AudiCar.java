package org.joven.webprotal.design.factory.factorymethod;

/**
 * 添加卡车实现接口
 */
public class AudiCar extends AbstractCar{
    private String name = "Audi Car";
    public AudiCar(String name){
        this.name = name;
    }
    @Override
    public void runCar(){
        System.out.println("开动奥迪车："+name);
    }
    @Override
    public String getCarName() {
        return this.name;
    }
    @Override
    public void addOil(){
        System.out.println("又该开车加油去了");
    }
}
