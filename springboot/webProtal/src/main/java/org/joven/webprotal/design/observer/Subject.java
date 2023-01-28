package org.joven.webprotal.design.observer;


/**
 * 定义一个被观察对象的父类
 */
public interface Subject {
    /**
     * 添加观察者
     * Observer 用自定义的类自定义的方法
     * @param observer 观察者类
     */
    public void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer 观察者类
     */
    public void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    public void notifyObserver();
}
