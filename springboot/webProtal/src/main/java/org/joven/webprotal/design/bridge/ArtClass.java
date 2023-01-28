package org.joven.webprotal.design.bridge;

/**
 * 艺术生班级
 */
public class ArtClass implements StuClass {
    private String name;

    public ArtClass(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "art";
    }

    @Override
    public void handleClass(String className) {
        System.out.println("艺术生在班级" + name + "学习， 内容：" + className+"内容");
    }

    @Override
    public void handleMeeting(String meetingName) {
        System.out.println("艺术生生在班级" + name + "开班会， 主题：" + meetingName+"家长会");
    }
}
