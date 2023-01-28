package org.joven.webprotal.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class WechatServer implements Subject {
    private List<Observer> list;
    private String message;

    public WechatServer() {
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        list.add(o);
        System.out.println("添加观察者："+o.getName());
    }

    //
    @Override
    public void removeObserver(Observer o) {
        // TODO Auto-generated method stub
        if (!list.isEmpty()) {
            System.out.println("移除观察者："+o.getName());
            list.remove(o);
        }
    }

    //
    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        for (Observer o : list) {
            o.update(message);
        }
    }

    //
    public void updateMessage(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        // 消息更新，通知所有观察者
        notifyObserver();
    }


}
