package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 人子类 学生
 */
public class Student extends PeopleAbstract{
    public void ride() {
        System.out.println("学生骑车");
    }
    public String getWork() {
        System.out.println("学生学洗");
        return "学习";
    }
}
