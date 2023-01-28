package org.joven.webprotal.design.adapter;

/**
 * 化学老师
 */
public class ChemistryTeacher implements InstructorTeacher {
    private String teacherName;

    public ChemistryTeacher(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public void lecture(String subject) {
        System.out.println(getTeacherName()+" 教授化学");
    }

    @Override
    public void homeworkCheck(String workType) {
        System.out.println(getTeacherName()+" 批改化学作业");
    }

    @Override
    public void homeworkPublish(String workType) {
        System.out.println(getTeacherName()+" 部化学作业");
    }
}
