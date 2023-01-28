package org.joven.webprotal.design.factory.abstractfactory;

/**
 * 构建学校工厂
 */
public class SchoolFactory extends AbstractFactory {
    public School getSchoolInfo(EnumParam.School school) {
        School sc = null;
        if (school == null) {
            return sc;
        }

        if ("High".equals(school.name())) {
            sc = new HighSchool(500, "济南", "一中");
        } else if ("Colleague".equals(school.name())) {
            sc = new ColleagueSchool(2000, "北京", "北大");
        } else {
            sc = new School() {
                @Override
                public int getStuNum() {
                    return 0;
                }

                @Override
                public String getLocation() {
                    return "不知道";
                }

                @Override
                public String getSchoolName() {
                    return "未知";
                }
            };
        }
        return sc;
    }
}
