package org.joven.webprotal.design.singleton;

/**
 * 单例对象   当前线程不安全
 */
public class Singleton {
    private Singleton(){}
    private static Singleton instance = null;
    public static Singleton getInstance(){
        if (instance ==null){
            instance = new Singleton();
            System.out.println("初始化一次");
        }else {
            System.out.println("不用初始化");
        }
        return instance;
    }

    public int getCode(){
        return instance.hashCode();
    }
    public long getTime() {
        System.out.print("获取对象的hashCode："+this.hashCode());
        return System.currentTimeMillis();
    }
}
