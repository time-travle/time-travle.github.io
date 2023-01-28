package org.joven.webprotal.design.adapter;

/**
 * 教导主任老师
 */
public class TeachingDirector implements DailyTeacher {
    private String teacherName;
    public TeachingDirector() {}

    public TeachingDirector(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String getDailyTeacherName() {
        return teacherName;
    }

    @Override
    public void getClassesInfo(String checker, String checkTarget) {
        System.out.println(checker + "想通过 " + getDailyTeacherName() + "老师查询 " + checkTarget + "班级的信息");

    }

    @Override
    public void getStudentInfo(String checker, String checkTarget) {
        System.out.println(checker + "想通过 " + getDailyTeacherName() + "老师查询 " + checkTarget + "学生的信息");
        System.out.println("教导主任差学生太累了还是班主任查吧");
    }

    @Override
    public void getTeacherInfo(String checker, String checkTarget) {
        System.out.println(checker + "想通过 " + getDailyTeacherName() + "老师查询 " + checkTarget + "老师的信息");
    }
}
