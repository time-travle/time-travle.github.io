package org.joven.webprotal.design.adapter;

/**
 * 班主任
 */
public class HeadMaster extends TeachingDirector implements InstructorTeacher {
    private String teacherName;

    public HeadMaster(String teacherName) {
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
    }

    @Override
    public void getTeacherInfo(String checker, String checkTarget) {
        System.out.println(checker + "想通过 " + getDailyTeacherName() + "老师查询 " + checkTarget + "老师的信息");
    }

    @Override
    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public void lecture(String subject) {
        System.out.println(getTeacherName() + "班主任 主持班会");
    }

    @Override
    public void homeworkCheck(String workType) {
        System.out.println(getTeacherName() + "班主任  开家长会");
    }

    @Override
    public void homeworkPublish(String workType) {
        System.out.println(getTeacherName() + "班主任 通知开家长会");
    }
}
