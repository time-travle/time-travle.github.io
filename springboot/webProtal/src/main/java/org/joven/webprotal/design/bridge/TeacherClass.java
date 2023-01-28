package org.joven.webprotal.design.bridge;

/**
 * 老师接口
 */
public abstract class TeacherClass {
    public StuClass stuClass;
    public void setStuClass(StuClass stuClass){
        this.stuClass =stuClass;

    }

    protected TeacherClass(){}

    public abstract String getName();

    public abstract void handleClass(String className);
    public abstract void handleMeeting(String meetingName);
}
