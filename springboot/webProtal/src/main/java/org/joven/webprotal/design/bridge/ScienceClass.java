package org.joven.webprotal.design.bridge;

/**
 * 自然科学班级
 */
public class ScienceClass  implements StuClass{
    private String name;
    public ScienceClass(String name){
        this.name=name;
    }
    @Override
    public String getType() {
        return "science";
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void handleClass(String className) {
        System.out.println("学生在班级"+name+"学习， 内容："+className+"内容");
    }

    @Override
    public void handleMeeting(String meetingName) {
        System.out.println("学生在班级"+name+"开班会， 主题："+meetingName+"家长会");
    }
}
