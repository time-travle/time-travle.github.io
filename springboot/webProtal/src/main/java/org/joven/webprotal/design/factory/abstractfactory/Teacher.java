package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 人子类 老师
 */
public class Teacher extends PeopleAbstract{
    public String getWork() {
        System.out.println("教师讲课");
        return "授课";
    }
}
