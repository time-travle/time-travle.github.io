package org.joven.webprotal.design.bridge;

/**
 * 高中老师
 */
public class HighSchoolTeacher extends TeacherClass {
    private String name;
    @Override
    public String getName() {
        return name;
    }

    public HighSchoolTeacher(String name) {
        this.name = name;
    }

    @Override
    public void handleClass(String className) {
        System.out.println(name + "去"+(stuClass.getType().equals("art")?"艺术":"科学")+"班级" + className + "教课");
        stuClass.handleClass(className);
    }

    @Override
    public void handleMeeting(String meetingName) {
        System.out.println(name + "去"+(stuClass.getType().equals("art")?"艺术":"科学")+"班级" + meetingName + "开会");
        stuClass.handleMeeting(meetingName);
    }
}
