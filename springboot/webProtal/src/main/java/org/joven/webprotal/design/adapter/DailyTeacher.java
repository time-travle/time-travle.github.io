package org.joven.webprotal.design.adapter;

/**
 * 日常管理老师
 */
public interface DailyTeacher {
    String getDailyTeacherName();
    //查询班级信息
    void getClassesInfo(String checker,String checkTarget);
    //查询学生信息
    void getStudentInfo(String checker,String checkTarget);
    //查询老师信息
    void getTeacherInfo(String checker,String checkTarget);
}
