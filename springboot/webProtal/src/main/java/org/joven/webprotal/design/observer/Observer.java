package org.joven.webprotal.design.observer;

/**
 * 定义一个观察对象的父类
 */
public interface Observer extends java.util.Observer {

    String getName();

    public void update(String message);
}
