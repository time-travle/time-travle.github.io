package org.joven.webprotal.design.observer;

import java.util.Observable;

/**
 * 定义观察者
 */

public class UserObserver implements Observer {
    private String name = "user";

    public UserObserver() {

    }

    public UserObserver(String name) {
        this.name = name;
    }

    // 自定义的
    @Override
    public void update(String message) {
        readNotify(message);
    }

    // 自带的
    @Override
    public void update(Observable o, Object arg) {

    }

    private void readNotify(String message) {
        System.out.println(name + "获得信息" + message);
    }

    @Override
    public String getName() {
        return this.name;
    }


}
