package org.joven.webprotal.design.adapter;

/**
 * 语文老师
 */
public class ChineseTeacher implements InstructorTeacher{
    private String teacherName;

    public ChineseTeacher(String teacherName) {
        this.teacherName = teacherName;
    }
    @Override
    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public void lecture(String subject) {
        System.out.println(getTeacherName()+" 教授语文");
    }

    @Override
    public void homeworkCheck(String workType) {
        System.out.println(getTeacherName()+" 批改语文作业");
    }

    @Override
    public void homeworkPublish(String workType) {
        System.out.println(getTeacherName()+" 部语文作业");
    }
}
