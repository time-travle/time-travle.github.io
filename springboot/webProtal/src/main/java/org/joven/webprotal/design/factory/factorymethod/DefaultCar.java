package org.joven.webprotal.design.factory.factorymethod;

/**
 * 添加卡车实现接口
 */
public class DefaultCar extends AbstractCar{
    private String name = "Default Car";
    public DefaultCar(String name){
        this.name = name;
    }
    @Override
    public void runCar(){
        System.out.println("开动默认车："+name);
    }
    @Override
    public String getCarName() {
        return this.name;
    }
}
