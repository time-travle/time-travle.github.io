package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 接口常量类
 */
public interface EnumParam {
    enum Season{
        Spring,Summer,Autumn,Winter;     //最舒服的季节
        public static Season getComfortableSeason(){
            return Spring;
        }
    }
    enum People{
        Student,Teacher,Worker
    }
    enum School{
        High,Colleague
    }
}
