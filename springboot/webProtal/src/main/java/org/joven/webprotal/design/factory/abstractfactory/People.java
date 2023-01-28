package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 定义一个描述人的接口
 */
public interface People {
    public People getInstance();

    void ride();

    void drive();

    void eatFood();

    String getName();
    String getSex();

    String getHeight();

    int getAge();

    String getWork();

}
