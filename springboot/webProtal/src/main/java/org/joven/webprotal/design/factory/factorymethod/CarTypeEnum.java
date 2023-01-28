package org.joven.webprotal.design.factory.factorymethod;

public enum CarTypeEnum {
    OTHER("other", "不知道谁开的"), TRUCK("truck", "不知道谁开的"), AUDI("audi", "不知道谁开的");

    public String getValue() {
        return value;
    }

    private final String value;
    private final String desc;

    public String getDesc() {
        return desc;
    }

    CarTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
