package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 选择要建立的工厂
 */
public class FactoryProd {
    public static AbstractFactory getFactory(String factory){
        if ("P".equals(factory)){
            return new PeopleFactory();
        }
        if ("S".equals(factory)){
            return new SchoolFactory();
        }
        return null;
    }
}
