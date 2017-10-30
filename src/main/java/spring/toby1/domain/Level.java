package spring.toby1.domain;

import sun.jvm.hotspot.utilities.AssertionFailure;

/**
 * Created by yuuuunmi on 2017. 10. 17..
 */
public enum Level {
    GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);

    private final int value;
    private final Level next;

    Level(int value, Level next) {
        this.value = value;
        this.next = next;
    }

    public int intValue() {
        return value;
    }

    public Level nextLevel(){
        return this.next;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1:
                return BASIC;
            case 2:
                return SILVER;
            case 3:
                return GOLD;
            default:
                throw new AssertionFailure("Unknown value: " + value);
        }
    }
}
