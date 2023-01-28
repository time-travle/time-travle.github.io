package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 构建人的工厂
 */
public class PeopleFactory extends AbstractFactory{


    public People getPeopleInfo(EnumParam.People people) {
        People p = null;
        if (people==null){
            return p;
        }
        if ("Student".equals(people.name())){
            p =new Student();
        }else if ("Teacher".equals(people.name())){
            p= new Teacher();
        }else if ("Worker".equals(people.name())){
            p= new Worker();
        }
        return p;
    }
}
