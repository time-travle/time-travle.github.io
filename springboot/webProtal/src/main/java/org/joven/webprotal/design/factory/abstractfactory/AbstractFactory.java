package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 抽象工厂类  暂时不在抽象类中添加 抽象方法这样 其实现类就不用全部重写了 只需要按需重写就可以了
 */
public abstract class AbstractFactory {
    // 获取 人
    public People getPeopleInfo(EnumParam.People enumParam) {
        System.out.println("no impl");
        return null;
    }

    /**
     * 获取学校
     */
    public School getSchoolInfo(EnumParam.School enumParam) {
        System.out.println("no impl");
        return null;
    }
}
