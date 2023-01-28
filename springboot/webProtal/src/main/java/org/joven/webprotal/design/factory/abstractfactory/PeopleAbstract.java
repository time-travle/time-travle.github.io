package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 添加一个中间层 使得实现类可以按需实现自己需要的
 */
public abstract class PeopleAbstract implements People {
    private String name;

    public void setName(String name) {
        this.name = name;
    }


    public People getInstance() {
        System.out.println(getName()+"未开始实现");
        return null;
    }

    public void ride() {
        System.out.println(getName()+"未开始实现");
    }

    public void drive() {
        System.out.println(getName()+"未开始实现");
    }

    public void eatFood() {
        System.out.println(getName()+"未开始实现");
    }

    public String getName() {
        System.out.println("未开始实现");
        return "未开始";
    }

    public String getHeight() {
        System.out.println(getName()+"未开始实现");
        return "";
    }

    public int getAge() {
        System.out.println(getName()+"未开始实现");
        return 0;
    }

    public String getWork() {
        System.out.println(getName()+"未开始实现");
        return "";
    }
    @Override
    public String getSex() {
        System.out.println(getName()+"未开始实现");
        return "";
    }
}
