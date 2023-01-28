package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 添加大学学校
 */
public class ColleagueSchool implements School {
    private int stuNum;
    private String location;
    private String name;
    public ColleagueSchool(int stuNum,String location,String name){
        this.location = location;
        this.name = name;
        this.stuNum = stuNum;
    }
    @Override
    public int getStuNum() {
        return stuNum;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSchoolName() {
        return name;
    }
}
