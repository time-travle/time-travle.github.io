package org.joven.webprotal.design.adapter;

/**
 * 授课老师
 */
public interface InstructorTeacher {

    String getTeacherName();
    /**
     * 授课
     */
    void lecture(String subject);

    /**
     * 批作业
     */
    void homeworkCheck(String workType);

    /**
     * 留作业
     */
    void homeworkPublish(String workType);
}
