package org.joven.webprotal.design.bridge;

/**
 * 学生班级接口
 */
public interface StuClass {
    String getName();
    String getType();
    void handleClass(String className);
    void handleMeeting(String meetingName);
}
