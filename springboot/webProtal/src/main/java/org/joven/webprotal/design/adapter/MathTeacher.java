package org.joven.webprotal.design.adapter;

/**
 * 数学老师
 */

public class MathTeacher implements InstructorTeacher{
    private String teacherName;

    public MathTeacher(String teacherName) {
        this.teacherName = teacherName;
    }
    @Override
    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public void lecture(String subject) {
        System.out.println(getTeacherName()+" 教授 "+subject+" 数学");
    }

    @Override
    public void homeworkCheck(String workType) {
        System.out.println(getTeacherName()+" 批改 "+workType+" 数学作业");
    }

    @Override
    public void homeworkPublish(String workType) {
        System.out.println(getTeacherName()+" 部 "+workType+" 数学作业");
    }
}
