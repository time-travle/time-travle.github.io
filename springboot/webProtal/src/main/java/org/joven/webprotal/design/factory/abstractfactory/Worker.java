package org.joven.webprotal.design.factory.abstractfactory;
/**
 * 人子类 打工人
 */
public class Worker extends PeopleAbstract{
    public void drive() {
        System.out.println("开摩多");
    }
    public String getWork() {
        System.out.println("工人打工");
        return "打工";
    }
}
